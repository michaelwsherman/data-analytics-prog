import java.util.Comparator;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.Random;

enum Suit {
	  HEART, CLUB, SPADE, DIAMOND
}

enum Value {
	  TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
}

class Card {
	  Suit suit;
	  Value value;
	  Card( Suit s, Value v) {
	    suit = s;
	    value = v;
	  }
	  
	  public void setValue (Value v ) {
		  value = v;
	  }
	  
	  public Card copy() {
		  Card copied = new Card(this.suit, this.value);
		  return copied;
	  }
}

class CardCompare implements Comparator<Card> {
		  private CardCompare() { };
		  static public final CardCompare compareObject = new CardCompare();
		  public int compare(Card s1, Card s2) {
		    return s1.value.ordinal() - s2.value.ordinal();
		  }
}	
	
class Hand5 {
  Card cards[] = new Card[5];
  

  public Hand5() {
  }
  
  public void print() { //put this in for testing, fun, and profit
	  System.out.println(cards[0].value.toString() + cards[0].suit.toString() + " " + 
			  cards[1].value.toString() + cards[1].suit.toString() + " " +
			  cards[2].value.toString() + cards[2].suit.toString() + " " +
			  cards[3].value.toString() + cards[3].suit.toString() + " " +
			  cards[4].value.toString() + cards[4].suit.toString() + " ");
  }
  
  public Hand5 copy() {
	  Hand5 copied = new Hand5(this.cards[0].copy(), this.cards[1].copy(), 
			  this.cards[2].copy(), this.cards[3].copy(), this.cards[4].copy());
	  return copied;
  }
  
  public Hand5( Card a, Card b, Card c, Card d, Card e) {
   // this a constructor for a 5 card hand
	  cards[0] = a;
	    cards[1] = b;
	    cards[2] = c;
	    cards[3] = d;
	    cards[4] = e;
	    Arrays.sort(cards, 
	        // descending sort
	        CardCompare.compareObject
	    );
	  
  }
  boolean is4OfAKind() {
	  	boolean first4; //this just made for readable code
	  	boolean last4;
	  	first4 = (cards[0].value == cards[1].value && cards[1].value == cards[2].value 
	  			&& cards[2].value == cards[3].value);
	  	last4 = (cards[1].value == cards[2].value && cards[2].value == cards[3].value 
	  			&& cards[3].value == cards[4].value);
	    return first4 || last4;
	  }

  boolean isFlush() {
	    return (cards[0].suit == cards[1].suit && cards[1].suit == cards[2].suit &&
	    		cards[2].suit == cards[3].suit && cards[3].suit == cards[4].suit);
	  }

  boolean isStraight() {
	    return (cards[0].value.ordinal() == cards[1].value.ordinal() - 1 
	          && cards[1].value.ordinal() == cards[2].value.ordinal() - 1
	          && cards[2].value.ordinal() == cards[3].value.ordinal() - 1
	          && cards[3].value.ordinal() == cards[4].value.ordinal() - 1) ||
	          (cards[0].value == Value.TWO && cards[1].value == Value.THREE &&
	          cards[2].value == Value.FOUR && cards[3].value == Value.FIVE && 
	          cards[4].value == Value.ACE);
	  }
	  
  boolean isStraightFlush() {
	    return isFlush() && isStraight();
	  }
  
  boolean isFullHouse() {
	    return (cards[0].value.ordinal() == cards[1].value.ordinal()
	          && cards[2].value.ordinal() == cards[3].value.ordinal()
	          && cards[3].value.ordinal() == cards[4].value.ordinal()
	          && cards[1].value.ordinal() != cards[2].value.ordinal()) ||
	          (cards[0].value.ordinal() == cards[1].value.ordinal()
	          && cards[1].value.ordinal() == cards[2].value.ordinal()
	          && cards[3].value.ordinal() == cards[4].value.ordinal()
	          && cards[2].value.ordinal() != cards[3].value.ordinal());
	  }

	boolean is3OfAKind (){
	  	boolean first3; //this just made for readable code
	  	boolean middle3;
	  	boolean last3;
	  	first3 = (cards[0].value == cards[1].value && cards[1].value == cards[2].value);
	  	middle3 = (cards[1].value == cards[2].value && cards[2].value == cards[3].value);
	  	last3 = (cards[2].value == cards[3].value && cards[3].value == cards[4].value);
	    return (first3 || middle3 || last3) && !(this.is4OfAKind()) && !(this.isFullHouse());
	}
	boolean is2Pair (){
	  	boolean pairPairNo; //this just made for readable code
	  	boolean pairNoPair;
	  	boolean noPairPair;
	  	pairPairNo = (cards[0].value == cards[1].value && cards[2].value == cards[3].value);
	  	pairNoPair = (cards[0].value == cards[1].value && cards[3].value == cards[4].value);
	  	noPairPair = (cards[1].value == cards[2].value && cards[3].value == cards[4].value);
	    return (pairPairNo || pairNoPair || noPairPair) 
	    		&& !(this.is4OfAKind()) && !(this.isFullHouse());
	}
	boolean isPair (){
	    return ( (cards[0].value == cards[1].value || cards[1].value == cards[2].value || 
	    		cards[2].value == cards[3].value || cards[3].value == cards[4].value) &&
	    		!(this.is4OfAKind()) && !(this.isFullHouse()) && 
	    		!(this.is3OfAKind()) && !(this.is2Pair()) );
	}
	
}

class Hand5Comparator implements Comparator<Hand5> {
	
	  public int compareOne(Card c0, Card c1) { //negative is c0, positive is c1
	      int v0 = c0.value.ordinal();
	      int v1 = c1.value.ordinal();
	      if ( v0 != v1 ) {
	        return v1 - v0;
	      }
	    return 0;
	  }
	  
	  public int compareHandsIgnoreOne(Hand5 h0, Hand5 h1, Card c1) {
		   Hand5 hand0 = h0.copy();
		   Hand5 hand1 = h1.copy();

		  for ( int i = 0; i < 5; i++){ 
			  if ( compareOne(c1,hand0.cards[i]) == 0 ) 
			  {hand0.cards[i].setValue(Value.TWO);}
			  if (compareOne(c1,hand1.cards[i]) == 0 )
			  {hand1.cards[i].setValue(Value.TWO);}
		  }
		  Arrays.sort(hand0.cards, CardCompare.compareObject);
		  Arrays.sort(hand1.cards, CardCompare.compareObject);
		  int cmp;
		  cmp = compareHandsHighCard(hand0, hand1);
		  return cmp;
		}
		  
	  public int compareHandsHighCard(Hand5 h0, Hand5 h1) {
		  int diff;
		  for ( int i = 4; i >=0; i-- ) {
			  diff = compareOne(h0.cards[i], h1.cards[i]);
			  if (diff != 0) {return diff;}
		  }
		  return 0;
		}
		   

	  
	public int compareStraightFlush(Hand5 h0, Hand5 h1) {
		boolean b0 = h0.isStraightFlush();
		boolean b1 = h1.isStraightFlush();
		if (b0 && !b1) { return -1; }
		if (!b0 && b1) { return 1;}
		if (!b0 && !b1) { return 0; }
		if (b0 && b1)
		{ 
			if (h0.cards[0].value.ordinal() == 0 && h0.cards[4].value.ordinal() == 12 ) {
				h0.cards[4].setValue(Value.TWO);
			}
			if (h1.cards[0].value.ordinal() == 0 && h1.cards[4].value.ordinal() == 12 ) {
				h1.cards[4].setValue(Value.TWO);
			}
			int cmp = compareHandsHighCard(h0,h1);
			return cmp;	
		}
		return 0;	
	}
	
	public int compare4OfAKind(Hand5 h0, Hand5 h1) {
		boolean b0 = h0.is4OfAKind();
		boolean b1 = h1.is4OfAKind();
		if (b0 && !b1) { return -1; }
		if (!b0 && b1) { return 1;}
		if (!b0 && !b1) { return 0; }
		if (b0 && b1)
		{ 
			int cmp = compareOne(h0.cards[2], h1.cards[2]);
			if (cmp != 0) {
				return cmp;
			}
			Card common = h0.cards[2].copy();
			cmp = compareHandsIgnoreOne(h0, h1, common);
			return cmp;
			}
		return 0;
	}
	
	public int compare3OfAKind(Hand5 h0, Hand5 h1) {
		boolean b0 = h0.is3OfAKind();
		boolean b1 = h1.is3OfAKind();
		if (b0 && !b1) { return -1; }
		if (!b0 && b1) { return 1;}
		if (!b0 && !b1) { return 0; }
		if (b0 && b1)
		{ 
			int cmp = compareOne(h0.cards[2], h1.cards[2]);
			if (cmp != 0) {
				return cmp;
			}
			Card common = h0.cards[2].copy();
			cmp = compareHandsIgnoreOne(h0, h1, common);
			return cmp;
			}
		return 0;
	}
	
	public int compareFullHouse(Hand5 h0, Hand5 h1) {
		boolean b0 = h0.isFullHouse();
		boolean b1 = h1.isFullHouse();
		if (b0 && !b1) { return -1; }
		if (!b0 && b1) { return 1;}
		if (!b0 && !b1) { return 0; }
		if (b0 && b1)
		{ 
			int cmp = compareOne(h0.cards[2], h1.cards[2]);
			if (cmp != 0) {
				return cmp;
			}
			Card common = h0.cards[2].copy();
			cmp = compareHandsIgnoreOne(h0, h1, common);
			return cmp;
			}
		return 0;
	}
	
	
	
	
	public int compareStraight(Hand5 h0, Hand5 h1) {
		boolean b0 = h0.isStraight();
		boolean b1 = h1.isStraight();
		if (b0 && !b1) { return -1; }
		if (!b0 && b1) { return 1;}
		if (!b0 && !b1) { return 0; }
		if (b0 && b1)
		{ 
			if (h0.cards[0].value.ordinal() == 0 && h0.cards[4].value.ordinal() == 12 ) {
				h0.cards[4].setValue(Value.TWO);
			}
			if (h1.cards[0].value.ordinal() == 0 && h1.cards[4].value.ordinal() == 12 ) {
				h1.cards[4].setValue(Value.TWO);
			}
			int cmp = compareHandsHighCard(h0,h1);
			return cmp;	
		}
		return 0;	
	}

	public int compareFlush(Hand5 h0, Hand5 h1) {
		boolean b0 = h0.isFlush();
		boolean b1 = h1.isFlush();
		if (b0 && !b1) { return -1; }
		if (!b0 && b1) { return 1;}
		if (!b0 && !b1) { return 0; }
		if (b0 && b1)
		{ 
			int cmp = compareHandsHighCard(h0,h1);
			return cmp;	
		}
		return 0;	
	}
	
	public int findAPair(Hand5 h0, int start) {
		for (int i; start > 0; start--) {
			if (h0.cards[start].value == h0.cards[start-1].value)
				{return start;}
		}
		return 0;
	}
	
	public int compareOnePair(Hand5 h0, Hand5 h1) {
		boolean b0 = h0.isPair();
		boolean b1 = h1.isPair();
		System.out.println("test1");
		int result;
		if (b0 && !b1) { return -1; }
		if (!b0 && b1) { return 1;}
		if (!b0 && !b1) { return 0; }
		if (b0 && b1) {
			System.out.println("test");
		int pair0 = findAPair(h0, 4);
		int pair1 = findAPair(h1, 4);
		result = compareOne(h0.cards[pair0], h1.cards[pair1]);
		if (result != 0) {
			return result;
		} else {		
		int cmp = compareHandsHighCard(h0, h1);
		return cmp; }
		}
		return 0;
	}


	public int compareTwoPair(Hand5 h0, Hand5 h1) {
		boolean b0 = h0.is2Pair();
		boolean b1 = h1.is2Pair();
		if (b0 && !b1) { return -1; }
		if (!b0 && b1) { return 1;}
		if (!b0 && !b1) { return 0; }
		if (b0 && b1) {
			
		int pair0 = findAPair(h0, 4);
		int pair1 = findAPair(h1, 4);
		int result = compareOne(h0.cards[pair0], h1.cards[pair1]);
		if (result != 0) {return result;}
		
		int pair0second = findAPair(h0, pair0-1);
		int pair1second = findAPair(h1, pair1-1);
		result = compareOne(h0.cards[pair0second], h1.cards[pair1second]);
		if (result != 0) {return result;}
		
		return compareHandsHighCard(h0, h1);
		}
		return 0;
	}
		
		

  public int compare(Hand5 h0, Hand5 h1) {
    int cmp=0;
 
    // use short circuit evaluation
    if ( (0 != ( cmp = compareStraightFlush(h0,h1) ) )
          || (0 != ( cmp = compare4OfAKind(h0,h1) ) )
          || (0 != ( cmp = compareFullHouse(h0,h1) ) )
          || (0 != ( cmp = compareFlush(h0,h1) ) )
          || (0 != ( cmp = compareStraight(h0,h1) ) )
          || (0 != ( cmp = compare3OfAKind(h0,h1) ) )
          || (0 != ( cmp = compareTwoPair(h0,h1) ) )
          || (0 != ( cmp = compareOnePair(h0,h1) ) )
          || (0 != ( cmp = compareHandsHighCard (h0,h1) ) ) ) {
    }
    return cmp;

  }

  // creates a hand from the 7 cards provided, skipping cards i and j
  public static Hand5 pick5( Card[] sevenCards, int i, int j ) {
    int [] resultArray = {10,10,10,10,10};
    int index = 0;
    for (int k = 0; k < 7; k++) {
    	if ( (k != i) && (k != j) ){
    		resultArray[index] = k;
    		index++;
    	}
    }
    Hand5 result = new Hand5( sevenCards[(resultArray[0])], sevenCards[(resultArray[1])], 
    		sevenCards[(resultArray[2])], sevenCards[(resultArray[3])],
    		sevenCards[(resultArray[4])] );
    return result;
  }
 
  // returns 21 hands of 5 cards made up from the seven cards
  // provided
  public static Hand5[] compute7Choose5( Card[] sevenCards ) {
    Hand5[] result = new Hand5[21]; // 7 choose 2 is 21
    int k = 0;
    for ( int i = 0 ; i < 7; i++ ) {
      for ( int j = i+1; j < 7; j++ ) {
        Hand5 aHand = pick5( sevenCards, i, j );
        result[k++] = aHand;
      }
    }
    return result;
  }

  public static Hand5 best5OutOf7( Card[] sevenCards ) {
    if ( sevenCards.length != 7 ) {
      System.out.println("Error: need 7 cards input to best5OutOf7");
      return null;
    }

    Hand5[] all5Hands = compute7Choose5( sevenCards );
    Arrays.sort(all5Hands, new Hand5Comparator() );

    return all5Hands[0];
  }

  public final static int NUMTRIALS = 10000;
  // returns the winner using a random deal for the remaining cards
  public static int simulate( Card[] community, Card[][] players ) {
    int result = -1;
    return result;
  }

  // TODO: for students with more sophisticated programming skills
  // assume there are 0 -- 5 community cards. Each player has 2 cards
  // in the hand. return the probability of each player winning, using
  // the random simulation approach outlined in the lab document
  public static double[] probabilities( Card[] community, Card[]... players) {
    double[] result = new double[players.length];
    // TODO:writeme
    for ( int i = 0 ; i < NUMTRIALS; i++ ) {
      int winner = simulate( community, players );
      // TODO: biased to earlier players, does not take into account ties
      result[winner] += 1.0;
    }
    for ( int i = 0 ; i < result.length; i++ ) {
      result[i] /= ((double) NUMTRIALS);
    }
    return result;
  }
}
