import java.util.HashMap;
import java.util.Arrays;


public class PickCoins {
	/**
	 * Take an array of nonnegative ints representing the initial state of the
	 * pick up coins game, and return the maximum amount of coins the first player
	 * can pick up.
	 *
	 */
	public static int maxVictory(int[] C) {
		//using two separate caches made the program much faster, for reasons not entirely clear
		//to me.
		HashMap<Long, int[]> p1solutionBank = new HashMap<Long, int[]>(); 
		HashMap<Long, int[]> p2solutionBank = new HashMap<Long, int[]>();
		int scores[] = recurMaxVictory(C, 0, C.length - 1, 0, 0, true, p1solutionBank, p2solutionBank);
		return scores[0];
	}

	public static int[] recurMaxVictory(int[]C, int start, int end, int p1score, int p2score, boolean p1turn,
			HashMap<Long, int[]> p1solutionBank, HashMap<Long,int[]> p2solutionBank) {

		int [] result = {p1score, p2score};
		//basecase, returns the one coin remaining;
		if (end == start) {
			if (p1turn) {
				result[0] = result[0] + C[end];
				return result;
			} else {
				result[1] = result[1] + C[end];	
				return result;
			}		
		}
		//basecase, returns the better of two coins remaining
		//the code doesn't work if i delete the code for the one basecase above
		//but i don't understand why, since this case should take care of it
		if (end - start == 1) {
			if (p1turn) {
				if (start > end) {
					result[0] = result[0] + C[start];
					result[1] = result[1] + C[end];
					return result;
				} else {
					result[0] = result[0] + C[end];
					result[1] = result[1] + C[start];
				}
			} else {
				if (start > end) {
					result[1] = result[1] + C[start];
					result[0] = result[0] + C[end];
					return result;
				} else {
					result[1] = result[1] + C[end];
					result[0] = result[0] + C[start];
				}
			}

		}
		
		

		//World's ugliest hash function, but faster than using a string lookup
		long key = start + 100000*end + 100000*100000*result[0] + 100000*100000*100000*result[1];
		//now if we've already solved this sub-array before, don't recurse any more
		if (p1turn) {
			if (p1solutionBank.containsKey(key)) 
				return p1solutionBank.get(key);
		}
		if (!p1turn) {
			if (p2solutionBank.containsKey(key)) 
				return p2solutionBank.get(key);
		}
		//here's the meat: take off either the left or the right coin, and the recurse. The recurse
		//then keeps pulling off coins, eventually getting to the base case. Then it will start to
		//compare options, and the code below the recursive calls picks the best option.
		if (p1turn) {
			int [] chooseLeft;
			int [] chooseRight;
			chooseLeft = recurMaxVictory(C,start+1,end,p1score + C[start], p2score, false, p1solutionBank, p2solutionBank);
			chooseRight = recurMaxVictory(C,start,end-1,p1score + C[end], p2score, false, p1solutionBank, p2solutionBank);
			if (chooseLeft[0] > chooseRight[0]) {
				p1solutionBank.put(key,chooseLeft);
				return chooseLeft; }
			else {
				p1solutionBank.put(key,chooseRight);
				return chooseRight;
			}
		}
		if (!p1turn) {
			int [] chooseLeft;
			int [] chooseRight;
			chooseLeft = recurMaxVictory(C,start+1,end,p1score, p2score + C[start], true, p1solutionBank, p2solutionBank);
			chooseRight = recurMaxVictory(C,start,end-1,p1score, p2score + C[end], true, p1solutionBank, p2solutionBank);
			if (chooseLeft[1] > chooseRight[1]) {
				p2solutionBank.put(key,chooseLeft);
				return chooseLeft; }
			else {
				p2solutionBank.put(key,chooseRight);
				return chooseRight;
			}
		}		

		return result;



	}
}	