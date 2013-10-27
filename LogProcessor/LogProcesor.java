import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;


import java.util.TreeSet;
import com.google.common.collect.TreeMultiset;
import com.google.common.collect.Multiset;

interface LogProcessor { //there is also a LogProcessorSlow that uses this interface
  public void add(String url, int time);
  public List<String> getOrderedUrlsInWindow(int K);
}

class PageTime implements Comparable {
  String url;
  int time;

  public int compareTo(Object o) {
    PageTime pt = (PageTime) o;
    int diff = time - pt.time;
    if ( diff != 0 ) {
      return diff;
    } else {
      return url.compareTo( pt.url );
    }
  }

  @Override 
  public String toString() {
    return url + ":" + time;
  }

  @Override 
  public boolean equals(Object o) {
    PageTime obj = (PageTime) o;
    return obj.url == url && obj.time == time;
  }

  public PageTime(String url, int time) {
    this.url = url;
    this.time = time;
  }
}

class PageCount implements Comparable {
  String url;
  int count; 
  public int compareTo(Object o) {
    PageCount pc = (PageCount) o;
    // sort by descending counts
    int diff = -(count - pc.count);
    if ( diff != 0 ) {
      return diff;
    } else {
      return url.compareTo( pc.url );
    }
  }

  @Override
  public boolean equals(Object o) {
    PageCount pc = (PageCount) o;
    return pc.url == url && pc.count == count;
  }

  public PageCount(String url, int count) {
    this.url = url;
    this.count = count;
  }
}

class LogProcessorFast implements LogProcessor {
  TreeMultiset<PageTime> queue;
  TreeSet<PageCount> counts;
  HashMap<String,PageCount> urlToCount;
  int W;
  int mostRecentTime;

  public LogProcessorFast(int W) {
    queue = TreeMultiset.create();
    counts = new TreeSet<PageCount>();
    urlToCount = new HashMap<String,PageCount>();
    this.W = W;
    this.mostRecentTime = 0;
  }

  public void add(String url, int time) {
	  
	if (time > mostRecentTime) {//keep track of our most recent time for culling
		mostRecentTime = time; 
	}
	  
    PageTime aPT = new PageTime(url,time);
    queue.add(aPT);
    if (urlToCount.containsKey(url)) {
    	PageCount oldPC = urlToCount.get(url);
    	oldPC.count = oldPC.count + 1;
    } else {
    	PageCount newPC = new PageCount(url,1);
    	urlToCount.put(url,newPC);
    	counts.add(newPC);
    }
    
    //remove old stuff
    Multiset.Entry<PageTime> oldestPTEntry = queue.firstEntry();
    PageTime oldestPT = oldestPTEntry.getElement();
    
    while (mostRecentTime - oldestPT.time > W )
    {
    	queue.pollFirstEntry(); 

    	PageCount hitToRemove = urlToCount.get(oldestPT.url);
    	hitToRemove.count = hitToRemove.count -1;

    	oldestPTEntry = queue.firstEntry();
    	oldestPT = oldestPTEntry.getElement();
    }

  }

  public List<String> getOrderedUrlsInWindow(int K) {
	TreeSet<PageCount> countsRefresh;
	countsRefresh = new TreeSet<PageCount>();
	
	//counts TreeSet is rebuilt to make sure it is ordered properly
	Iterator<PageCount> itr = counts.iterator();
	while(itr.hasNext()) {
		PageCount nextPC = itr.next();
		if (!(nextPC.count == 0))
		countsRefresh.add(nextPC);
	}
	  
	 itr = countsRefresh.iterator();
     List<String> result = new ArrayList<String>();
     while(itr.hasNext() && result.size() < K) {
    	 PageCount nextPC = itr.next();
    	 result.add( nextPC.url + ":" + nextPC.count);

	 }
	 return result;
  }
	     
}