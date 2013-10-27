public class LogAnalysis {

	// TODO(DAP): add helper methods, fields here



	public String highestAffinityPair(String logFileString) {

		String [][] userHistory = makeUserHistory(logFileString);
		String [] affinityList = makeAffinityList(userHistory);
		
		//all this code actually figures out which pair has the highest affinity
		int maxAffinity = 0;
		String highestPair = null;
		for (int i = 0; i < 25000000; i++) 
		{
			if (affinityList[i] == null) {break;} //break if we hit the end of usable data
			
			String splitEntry [] = affinityList[i].split(","); //otherwise do all this
			int testedTotal = Integer.parseInt(splitEntry[2]);
			if (testedTotal > maxAffinity) {
				highestPair = splitEntry[0]+ "," + splitEntry[1];
				maxAffinity = testedTotal;
			}

			
		}
		return highestPair;
	}
	
	private String [] makeAffinityList(String[][] userHistory) {
		String [] affinityList = new String [25000000]; ///5000*5000
		for (int i = 0; i < 1000; i++) //go through userHistory
		{
			if (userHistory[i][0] == null) {break;} //stop when we hit the end of usable data
			int j = 1;
			for (j = 1; j < 5001; j++) //as long as each user's website list can be
			{
				if (userHistory[i][j] == null) {break;} //stop when we hit the end of a user's sites

				for (int k = 1; k < j; k++) //go from 1 up to j, adding the pairs of sites to affinityList
				{
					String firstString;
					String secondString;
					//old debug code:System.out.println(userHistory[i][0] + " " + userHistory[i][j] + " " + userHistory[i][k]);
					if (userHistory[i][j].compareTo(userHistory[i][k]) > 0) { //alphabetizing
						firstString =  userHistory[i][k];
						secondString = userHistory[i][j];
					} else 
					{ 
						firstString = userHistory[i][j];
						secondString = userHistory[i][k];
					}
					for (int l = 0; l < affinityList.length; l++) {
						if (affinityList[l] == null)
						{
							affinityList[l] = firstString + "," + secondString + "," + "1";
							break;
						}
						String splitEntry [] = affinityList[l].split(",");
						//old debug code:System.out.println(splitEntry[0] + firstString + " " + splitEntry[1] + secondString);
						if (splitEntry[0].equals(firstString) && splitEntry[1].equals(secondString))
						{
							int newTotal = Integer.parseInt(splitEntry[2]);
							newTotal++;
							affinityList[l] = firstString + "," + secondString + "," + Integer.toString(newTotal);
							//System.out.println(affinityList[l]);
							break;
						}
						
			
					}
					//System.out.println(firstString + "," + secondString + "," + "1");

				}
					
				
					
				
			}
			
		}
		return affinityList;
	}

	private String [][] makeUserHistory(String logFileString) {

		String [][] userHistory = new String [1000][5001]; //userHistory[x][0] is a list of usernames. userHistory[x][y] is the websites for a user
		String [] lines = logFileString.split("\n");

		for (int i = 0; i < lines.length; i++) {

			String logEntry [] = lines[i].split(",");
			String userName = logEntry[2];
			String webSite = logEntry[1];

			int userIndex = 0; //will be used to add a website to a user

			//lets add our user and webSite, if not a duplicate
			for (int j = 0; j < 1000; j++) {

				//if we hit the end of our list of users, we'll see a blank string. add the user
				if (userHistory[j][0] == null) {
					userHistory[j][0] = userName;
					userIndex = j;
					break;
				}
				
				if ( (userHistory[j][0]).equals(userName) ) { //if we find the user, yay
					userIndex = j;
					break;
				}
			}
			for (int k = 1; k < 5001; k++) {

				//if we hit the end of our list of websites for this user, add the website
				if (userHistory[userIndex][k] == null) {
					userHistory[userIndex][k] = webSite;
					
					break; //if we find the website, it's a duplicate. don't add it.
				}
				if ( (userHistory[userIndex][k]).equals(webSite) ) {
					//old test code: System.out.println("duplicate found i = " + i + "  j= " + userIndex + " k = " + k);
					break;
				}
			}

		}
		return userHistory;
	}

}