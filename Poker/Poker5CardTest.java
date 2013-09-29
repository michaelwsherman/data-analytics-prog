import org.junit.*;
import static org.junit.Assert.*;

public class Poker5CardTest {

@Test
 // card and hand copy
	public void testcopies() {
	Card original = new Card(Suit.CLUB, Value.ACE);
	Card copy = original.copy();
	copy.setValue(Value.TWO);
	assertTrue(original.value != copy.value);
	assertTrue(original.value == Value.ACE);
	assertTrue(copy.value == Value.TWO);
	
	Hand5 origin = new Hand5( new Card(Suit.CLUB, Value.ACE),
            new Card(Suit.HEART, Value.ACE),
            new Card(Suit.DIAMOND, Value.ACE),
            new Card(Suit.SPADE, Value.EIGHT),
            new Card(Suit.CLUB, Value.FIVE));
	Hand5 copied = origin.copy();
	copied.cards[3].setValue(Value.THREE);
	assertTrue(origin.cards[3].value != copied.cards[3].value);
	
}

@Test
//comparestrightflush
	public void compareStraightFlush() {
	
		Hand5 h0  = new Hand5(new Card(Suit.CLUB,Value.ACE),
	            new Card(Suit.CLUB, Value.JACK),
	            new Card(Suit.CLUB, Value.KING),
	            new Card(Suit.CLUB, Value.TEN),
	            new Card(Suit.CLUB, Value.QUEEN));
	
		Hand5 h1 = new Hand5(new Card(Suit.CLUB,Value.ACE),
	            new Card(Suit.CLUB, Value.TWO),
	            new Card(Suit.CLUB, Value.THREE),
	            new Card(Suit.CLUB, Value.FIVE),
	            new Card(Suit.CLUB, Value.FOUR));
		
		assertTrue(new Hand5Comparator().compareStraightFlush(h0,h1) < 0);
		
}

@Test
//four of a kind
	public void compare4OfAKind() {
	
	Hand5 h0  = new Hand5(new Card(Suit.CLUB,Value.TWO),
            new Card(Suit.CLUB, Value.TWO),
            new Card(Suit.CLUB, Value.TWO),
            new Card(Suit.CLUB, Value.TWO),
            new Card(Suit.CLUB, Value.THREE));

	Hand5 h1 = new Hand5(new Card(Suit.CLUB,Value.TWO),
            new Card(Suit.CLUB, Value.TWO),
            new Card(Suit.CLUB, Value.TWO),
            new Card(Suit.CLUB, Value.TWO),
            new Card(Suit.CLUB, Value.FOUR));
	
	Hand5 h2 = new Hand5(new Card(Suit.CLUB,Value.ACE),
            new Card(Suit.CLUB, Value.ACE),
            new Card(Suit.CLUB, Value.ACE),
            new Card(Suit.CLUB, Value.ACE),
            new Card(Suit.CLUB, Value.FOUR));
	
	assertTrue(new Hand5Comparator().compare4OfAKind(h0,h1) > 0);
	assertTrue(new Hand5Comparator().compare4OfAKind(h1,h0) < 0);
	assertTrue(new Hand5Comparator().compare4OfAKind(h1,h1) == 0);
	assertTrue(new Hand5Comparator().compare4OfAKind(h1,h2) > 0);
	
}

@Test
//three of a kind
	public void compare3OfAKind() {
	
	Hand5 h0  = new Hand5(new Card(Suit.CLUB,Value.TWO),
          new Card(Suit.CLUB, Value.TWO),
          new Card(Suit.CLUB, Value.TWO),
          new Card(Suit.CLUB, Value.FOUR),
          new Card(Suit.CLUB, Value.THREE));

	Hand5 h1 = new Hand5(new Card(Suit.CLUB,Value.TWO),
          new Card(Suit.CLUB, Value.TWO),
          new Card(Suit.CLUB, Value.TWO),
          new Card(Suit.CLUB, Value.ACE),
          new Card(Suit.CLUB, Value.FOUR));
	
	Hand5 h2 = new Hand5(new Card(Suit.CLUB,Value.ACE),
          new Card(Suit.CLUB, Value.ACE),
          new Card(Suit.CLUB, Value.ACE),
          new Card(Suit.CLUB, Value.KING),
          new Card(Suit.CLUB, Value.FOUR));
	
	Hand5 h3 = new Hand5(new Card(Suit.CLUB,Value.ACE),
	          new Card(Suit.CLUB, Value.ACE),
	          new Card(Suit.CLUB, Value.KING),
	          new Card(Suit.CLUB, Value.KING),
	          new Card(Suit.CLUB, Value.FOUR));
	
	assertTrue(new Hand5Comparator().compare3OfAKind(h0,h1) > 0);
	assertTrue(new Hand5Comparator().compare3OfAKind(h1,h0) < 0);
	assertTrue(new Hand5Comparator().compare3OfAKind(h1,h1) == 0);
	assertTrue(new Hand5Comparator().compare3OfAKind(h1,h2) > 0);
	assertTrue(new Hand5Comparator().compare3OfAKind(h3,h0) > 0);
	
}

@Test
//full house
	public void compareFullHouse() {
	
	Hand5 h0  = new Hand5(new Card(Suit.CLUB,Value.TWO),
        new Card(Suit.CLUB, Value.TWO),
        new Card(Suit.CLUB, Value.TWO),
        new Card(Suit.CLUB, Value.THREE),
        new Card(Suit.CLUB, Value.THREE));

	Hand5 h1 = new Hand5(new Card(Suit.CLUB,Value.TWO),
        new Card(Suit.CLUB, Value.TWO),
        new Card(Suit.CLUB, Value.TWO),
        new Card(Suit.CLUB, Value.FOUR),
        new Card(Suit.CLUB, Value.FOUR));
	
	Hand5 h2 = new Hand5(new Card(Suit.CLUB,Value.TWO),
        new Card(Suit.CLUB, Value.ACE),
        new Card(Suit.CLUB, Value.ACE),
        new Card(Suit.CLUB, Value.TWO),
        new Card(Suit.CLUB, Value.TWO));
	
	Hand5 h3 = new Hand5(new Card(Suit.CLUB,Value.ACE),
	          new Card(Suit.CLUB, Value.ACE),
	          new Card(Suit.CLUB, Value.KING),
	          new Card(Suit.CLUB, Value.KING),
	          new Card(Suit.CLUB, Value.KING));
	
	assertTrue(new Hand5Comparator().compareFullHouse(h0,h1) > 0);
	assertTrue(new Hand5Comparator().compareFullHouse(h1,h0) < 0);
	assertTrue(new Hand5Comparator().compareFullHouse(h1,h1) == 0);
	assertTrue(new Hand5Comparator().compareFullHouse(h1,h2) > 0);
	assertTrue(new Hand5Comparator().compareFullHouse(h3,h2) < 0);
	
}

@Test
//find a pair
public void testFindAPair() {
	Hand5 h3 = new Hand5(new Card(Suit.CLUB,Value.ACE),
	          new Card(Suit.CLUB, Value.KING),
	          new Card(Suit.CLUB, Value.TWO),
	          new Card(Suit.CLUB, Value.KING),
	          new Card(Suit.CLUB, Value.TWO));
	int index = new Hand5Comparator().findAPair(h3, 4);
	System.out.println(index + " " + h3.cards[index].value + " " + h3.cards[index-1].value);
	int index2 = new Hand5Comparator().findAPair(h3, index-2);
	System.out.println(index2 + " " + h3.cards[index2].value + " " + h3.cards[index2-1].value);
	int index3 = new Hand5Comparator().findAPair(h3, index2-2);
	System.out.println(index3 + "");
}

@Test
//one pair
	public void compareOnePair() {
	
	Hand5 h0  = new Hand5(new Card(Suit.CLUB,Value.TWO),
      new Card(Suit.CLUB, Value.TWO),
      new Card(Suit.CLUB, Value.FOUR),
      new Card(Suit.CLUB, Value.FIVE),
      new Card(Suit.CLUB, Value.THREE));

	Hand5 h1 = new Hand5(new Card(Suit.CLUB,Value.TWO),
      new Card(Suit.CLUB, Value.TWO),
      new Card(Suit.CLUB, Value.FOUR),
      new Card(Suit.CLUB, Value.FIVE),
      new Card(Suit.CLUB, Value.SIX));
	
	Hand5 h2 = new Hand5(new Card(Suit.CLUB,Value.TWO),
      new Card(Suit.CLUB, Value.ACE),
      new Card(Suit.CLUB, Value.ACE),
      new Card(Suit.CLUB, Value.KING),
      new Card(Suit.CLUB, Value.QUEEN));
	
	Hand5 h3 = new Hand5(new Card(Suit.CLUB,Value.ACE),
	          new Card(Suit.CLUB, Value.ACE),
	          new Card(Suit.CLUB, Value.KING),
	          new Card(Suit.CLUB, Value.JACK),
	          new Card(Suit.CLUB, Value.QUEEN));
	Hand5 h4 = new Hand5(new Card(Suit.CLUB,Value.ACE),
	          new Card(Suit.CLUB, Value.TWO),
	          new Card(Suit.CLUB, Value.KING),
	          new Card(Suit.CLUB, Value.JACK),
	          new Card(Suit.CLUB, Value.QUEEN));
	
	assertTrue(new Hand5Comparator().compareOnePair(h0,h4) < 0);
	assertTrue(new Hand5Comparator().compareOnePair(h0,h1) > 0);
	assertTrue(new Hand5Comparator().compareOnePair(h1,h0) < 0);
	assertTrue(new Hand5Comparator().compareOnePair(h1,h1) == 0);
	assertTrue(new Hand5Comparator().compareOnePair(h1,h2) > 0);
	assertTrue(new Hand5Comparator().compareOnePair(h3,h2) < 0);
	assertTrue(new Hand5Comparator().compareOnePair(h3,h0) < 0);
	
}

@Test
//two pair
	public void compareTwoPair() {
	
	Hand5 h0  = new Hand5(new Card(Suit.CLUB,Value.TWO),
    new Card(Suit.CLUB, Value.TWO),
    new Card(Suit.CLUB, Value.THREE),
    new Card(Suit.CLUB, Value.FIVE),
    new Card(Suit.CLUB, Value.THREE));

	Hand5 h1 = new Hand5(new Card(Suit.CLUB,Value.TWO),
    new Card(Suit.CLUB, Value.TWO),
    new Card(Suit.CLUB, Value.FOUR),
    new Card(Suit.CLUB, Value.FOUR),
    new Card(Suit.CLUB, Value.FIVE));
	
	Hand5 h2 = new Hand5(new Card(Suit.CLUB,Value.TWO),
    new Card(Suit.CLUB, Value.FOUR),
    new Card(Suit.CLUB, Value.FOUR),
    new Card(Suit.CLUB, Value.THREE),
    new Card(Suit.CLUB, Value.THREE));
	
	Hand5 h3 = new Hand5(new Card(Suit.CLUB,Value.FOUR),
	          new Card(Suit.CLUB, Value.FOUR),
	          new Card(Suit.CLUB, Value.THREE),
	          new Card(Suit.CLUB, Value.THREE),
	          new Card(Suit.CLUB, Value.ACE));
	
	assertTrue(new Hand5Comparator().compareTwoPair(h0,h1) > 0);
	assertTrue(new Hand5Comparator().compareTwoPair(h1,h0) < 0);
	assertTrue(new Hand5Comparator().compareTwoPair(h1,h1) == 0);
	assertTrue(new Hand5Comparator().compareTwoPair(h1,h2) > 0);
	assertTrue(new Hand5Comparator().compareTwoPair(h3,h2) < 0);
	assertTrue(new Hand5Comparator().compareTwoPair(h3,h0) < 0);
}

  @Test
  // 3 Aces v. 3 Kings
  public void t1() {
    Hand5 h0 = new Hand5( new Card(Suit.CLUB, Value.ACE),
                        new Card(Suit.HEART, Value.ACE),
                        new Card(Suit.DIAMOND, Value.ACE),
                        new Card(Suit.SPADE, Value.EIGHT),
                        new Card(Suit.CLUB, Value.FIVE));
    Hand5 h1 = new Hand5( new Card(Suit.CLUB, Value.KING),
                        new Card(Suit.HEART, Value.KING),
                        new Card(Suit.DIAMOND, Value.KING),
                        new Card(Suit.CLUB, Value.EIGHT),
                        new Card(Suit.SPADE, Value.FIVE));
    int cmp = new Hand5Comparator().compare(h0,h1);
    assertTrue(cmp<0);
  }

  @Test
  // Ace High v. 3 of a Kind
  public void t2() {
    Hand5 h0 = new Hand5( new Card(Suit.CLUB, Value.ACE),
                        new Card(Suit.HEART, Value.TWO),
                        new Card(Suit.DIAMOND, Value.FIVE),
                        new Card(Suit.SPADE, Value.THREE),
                        new Card(Suit.DIAMOND, Value.SIX) );
    Hand5 h1 = new Hand5( new Card(Suit.CLUB, Value.KING),
                        new Card(Suit.HEART, Value.KING),
                        new Card(Suit.DIAMOND, Value.KING),
                        new Card(Suit.SPADE, Value.TWO),
                        new Card(Suit.DIAMOND, Value.FIVE) );
    int cmp = new Hand5Comparator().compare(h0,h1);
    assertTrue(cmp>0);
  }

  @Test
  // Flush v. Two Pair
  public void t3() {
    Hand5 h0 = new Hand5( new Card(Suit.CLUB, Value.FOUR),
                        new Card(Suit.CLUB, Value.TWO),
                        new Card(Suit.CLUB, Value.THREE), 
                        new Card(Suit.CLUB, Value.SIX),
                        new Card(Suit.CLUB, Value.JACK) );
    Hand5 h1 = new Hand5( new Card(Suit.CLUB, Value.ACE),
                        new Card(Suit.HEART, Value.TWO),
                        new Card(Suit.DIAMOND, Value.FOUR), 
                        new Card(Suit.HEART, Value.TWO),
                        new Card(Suit.SPADE, Value.FOUR) );
    int cmp = new Hand5Comparator().compare(h0,h1);
    assertTrue(cmp<0);
  }
  
  @Test
  // Straight Flush v. Straight
  public void t4() {
    Hand5 h0 = new Hand5( new Card(Suit.CLUB, Value.ACE),
                        new Card(Suit.CLUB, Value.TWO),
                        new Card(Suit.CLUB, Value.THREE),
                        new Card(Suit.CLUB, Value.FOUR),
                        new Card(Suit.CLUB, Value.FIVE) );
    Hand5 h1 = new Hand5( new Card(Suit.DIAMOND, Value.ACE),
                        new Card(Suit.HEART, Value.TWO),
                        new Card(Suit.DIAMOND, Value.THREE), 
                        new Card(Suit.DIAMOND, Value.FOUR),
                        new Card(Suit.DIAMOND, Value.FIVE) );
    int cmp = new Hand5Comparator().compare(h0,h1);
    assertTrue(cmp<0);
  }

  @Test
  // Two Pair v. Pair
  public void t5() {
    Hand5 h0 = new Hand5( new Card(Suit.CLUB, Value.FIVE),
                        new Card(Suit.DIAMOND, Value.FOUR),
                        new Card(Suit.SPADE, Value.FOUR),
                        new Card(Suit.SPADE, Value.FIVE),
                        new Card(Suit.SPADE, Value.THREE) );
    Hand5 h1 = new Hand5( new Card(Suit.CLUB, Value.ACE),
                        new Card(Suit.HEART, Value.ACE),
                        new Card(Suit.SPADE, Value.KING),
                        new Card(Suit.HEART, Value.TWO),
                        new Card(Suit.DIAMOND, Value.FOUR) );
    int cmp = new Hand5Comparator().compare(h0,h1);
    assertTrue(cmp<0);
  }

  @Test
  // Full House v. 4 of a Kind
  public void t6() {
    Hand5 h0 = new Hand5( new Card(Suit.DIAMOND, Value.FIVE),
                        new Card(Suit.CLUB, Value.TWO),
                        new Card(Suit.SPADE, Value.TWO),
                        new Card(Suit.DIAMOND, Value.TWO),
                        new Card(Suit.CLUB, Value.FIVE) );
    Hand5 h1 = new Hand5( new Card(Suit.CLUB, Value.ACE),
                        new Card(Suit.HEART, Value.THREE),
                        new Card(Suit.SPADE, Value.THREE),
                        new Card(Suit.CLUB, Value.THREE),
                        new Card(Suit.DIAMOND, Value.THREE) );
    int cmp = new Hand5Comparator().compare(h0,h1);
    assertTrue(cmp>0);
  }

  @Test
  // Straight v. Full House
  public void t7() {
    Hand5 h0 = new Hand5( new Card(Suit.DIAMOND, Value.ACE),
                        new Card(Suit.CLUB, Value.TWO),
                        new Card(Suit.SPADE, Value.THREE),
                        new Card(Suit.CLUB, Value.FOUR),
                        new Card(Suit.HEART, Value.FIVE) );
    Hand5 h1 = new Hand5( new Card(Suit.SPADE, Value.FOUR),
                        new Card(Suit.HEART, Value.FOUR),
                        new Card(Suit.DIAMOND, Value.FOUR),
                        new Card(Suit.DIAMOND, Value.JACK),
                        new Card(Suit.CLUB, Value.JACK) );
    int cmp = new Hand5Comparator().compare(h0,h1);
    assertTrue(cmp>0);
  }

  @Test
  // 3 of a Kind vs. Two Pair
  public void t8() {
    Hand5 h0 = new Hand5( new Card(Suit.DIAMOND, Value.ACE),
                        new Card(Suit.CLUB, Value.ACE),
                        new Card(Suit.SPADE, Value.KING),
                        new Card(Suit.CLUB, Value.TEN),
                        new Card(Suit.CLUB, Value.KING) );
    Hand5 h1 = new Hand5( new Card(Suit.CLUB, Value.SEVEN),
                        new Card(Suit.HEART, Value.SEVEN),
                        new Card(Suit.SPADE, Value.TWO),
                        new Card(Suit.DIAMOND, Value.QUEEN),
                        new Card(Suit.DIAMOND, Value.SEVEN) );
    int cmp = new Hand5Comparator().compare(h0,h1);
    assertTrue(cmp>0);
  }

  @Test
  // Queen High Straight v. 6 High Straight
  public void t9() {
    Hand5 h0 = new Hand5( new Card(Suit.CLUB, Value.QUEEN),
                        new Card(Suit.SPADE, Value.JACK),
                        new Card(Suit.DIAMOND, Value.TEN),
                        new Card(Suit.CLUB, Value.NINE),
                        new Card(Suit.HEART, Value.EIGHT) );
    Hand5 h1 = new Hand5( new Card(Suit.CLUB, Value.SIX),
                        new Card(Suit.HEART, Value.FIVE),
                        new Card(Suit.HEART, Value.FOUR),
                        new Card(Suit.HEART, Value.THREE),
                        new Card(Suit.CLUB, Value.TWO) );
    int cmp = new Hand5Comparator().compare(h0,h1);
    assertTrue(cmp<0);
  }

  @Test
  // 10 High v. King High 
  public void t10() {
    Hand5 h0 = new Hand5( new Card(Suit.CLUB, Value.TEN),
                        new Card(Suit.SPADE, Value.EIGHT),
                        new Card(Suit.DIAMOND, Value.TWO),
                        new Card(Suit.CLUB, Value.NINE),
                        new Card(Suit.HEART, Value.FOUR) );
    Hand5 h1 = new Hand5( new Card(Suit.CLUB, Value.THREE),
                        new Card(Suit.HEART, Value.SEVEN),
                        new Card(Suit.HEART, Value.KING),
                        new Card(Suit.HEART, Value.JACK),
                        new Card(Suit.CLUB, Value.TWO) );
    int cmp = new Hand5Comparator().compare(h0,h1);
    assertTrue(cmp>0);
  }

  @Test
  // Pair 10s v. Pair 8s
  public void t11() {
    Hand5 h0 = new Hand5( new Card(Suit.CLUB, Value.QUEEN),
                        new Card(Suit.SPADE, Value.JACK),
                        new Card(Suit.DIAMOND, Value.TEN),
                        new Card(Suit.CLUB, Value.NINE),
                        new Card(Suit.HEART, Value.TEN) );
    Hand5 h1 = new Hand5( new Card(Suit.CLUB, Value.EIGHT),
                        new Card(Suit.HEART, Value.FIVE),
                        new Card(Suit.HEART, Value.FOUR),
                        new Card(Suit.HEART, Value.EIGHT),
                        new Card(Suit.CLUB, Value.TWO) );
    int cmp = new Hand5Comparator().compare(h0,h1);
    assertTrue(cmp<0);
  }
  @Test
  // Two Pair Queens and 5s v. Two Pair Queens 8s
  public void t12() {
    Hand5 h0 = new Hand5( new Card(Suit.CLUB, Value.QUEEN),
                        new Card(Suit.SPADE, Value.JACK),
                        new Card(Suit.DIAMOND, Value.FIVE),
                        new Card(Suit.CLUB, Value.FIVE),
                        new Card(Suit.HEART, Value.QUEEN) );
    Hand5 h1 = new Hand5( new Card(Suit.CLUB, Value.EIGHT),
                        new Card(Suit.SPADE, Value.QUEEN),
                        new Card(Suit.DIAMOND, Value.QUEEN),
                        new Card(Suit.HEART, Value.EIGHT),
                        new Card(Suit.CLUB, Value.TWO) );
    int cmp = new Hand5Comparator().compare(h0,h1);
    assertTrue(cmp>0);
  }
  
  @Test 
  public void tpick5() {
	    Card[] h0 = new Card[] {
                  new Card(Suit.HEART, Value.TWO),
                  new Card(Suit.HEART, Value.THREE),
                  new Card(Suit.HEART, Value.FOUR),
                  new Card(Suit.HEART, Value.FIVE),
                  new Card(Suit.HEART, Value.SIX),
                  new Card(Suit.HEART, Value.SEVEN),
                  new Card(Suit.HEART, Value.EIGHT) 
                } ;
	    Hand5 result = new Hand5Comparator().pick5(h0, 0, 5);
	    
  }
  @Test
  public void t13() {
    Hand5 h0 = Hand5Comparator.best5OutOf7( 
                          new Card[] {
                            new Card(Suit.HEART, Value.TWO),
                            new Card(Suit.HEART, Value.THREE),
                            new Card(Suit.HEART, Value.FOUR),
                            new Card(Suit.HEART, Value.FIVE),
                            new Card(Suit.HEART, Value.SIX),
                            new Card(Suit.HEART, Value.SEVEN),
                            new Card(Suit.HEART, Value.EIGHT) 
                          } );

    Hand5 h1 = new Hand5( new Card(Suit.HEART, Value.FOUR), 
                          new Card(Suit.HEART, Value.FIVE),
                          new Card(Suit.HEART, Value.SIX),
                          new Card(Suit.HEART, Value.SEVEN),
                          new Card(Suit.HEART, Value.EIGHT) );

    int cmp = new Hand5Comparator().compare(h0,h1);
    assertEquals(0,cmp);
  }
  @Test
  public void t14() {
    Hand5 h0 = Hand5Comparator.best5OutOf7( 
                          new Card[] {
                            new Card(Suit.DIAMOND, Value.TWO),
                            new Card(Suit.DIAMOND, Value.THREE),
                            new Card(Suit.HEART, Value.ACE),
                            new Card(Suit.CLUB, Value.ACE),
                            new Card(Suit.DIAMOND, Value.SIX),
                            new Card(Suit.DIAMOND, Value.SEVEN),
                            new Card(Suit.DIAMOND, Value.ACE) 
                          } );

    Hand5 h1 = new Hand5( new Card(Suit.DIAMOND, Value.TWO), 
                          new Card(Suit.DIAMOND, Value.THREE),
                          new Card(Suit.DIAMOND, Value.SIX),
                          new Card(Suit.DIAMOND, Value.SEVEN),
                          new Card(Suit.DIAMOND, Value.ACE) );

    int cmp = new Hand5Comparator().compare(h0,h1);
    assertEquals(0,cmp);
  }
  
  @Test
  public void t14a() {
    Hand5 h0 = Hand5Comparator.best5OutOf7( 
                          new Card[] {
                            new Card(Suit.DIAMOND, Value.TWO),
                            new Card(Suit.DIAMOND, Value.THREE),
                            new Card(Suit.HEART, Value.ACE),
                            new Card(Suit.CLUB, Value.ACE),
                            new Card(Suit.DIAMOND, Value.SIX),
                            new Card(Suit.DIAMOND, Value.SEVEN),
                            new Card(Suit.DIAMOND, Value.ACE) 
                          } );
    
    Hand5 h1 = Hand5Comparator.best5OutOf7( 
            new Card[] {
              new Card(Suit.DIAMOND, Value.TWO),
              new Card(Suit.DIAMOND, Value.THREE),
              new Card(Suit.DIAMOND, Value.FOUR),
              new Card(Suit.CLUB, Value.ACE),
              new Card(Suit.DIAMOND, Value.SIX),
              new Card(Suit.DIAMOND, Value.SEVEN),
              new Card(Suit.DIAMOND, Value.ACE) 
            } );

    int cmp = new Hand5Comparator().compare(h0,h1);
    assertTrue(cmp > 0);
  }
  


  @Test
  public void t15() {
    Card[] community = new Card[]{ new Card(Suit.CLUB, Value.ACE), new Card(Suit.HEART, Value.ACE)};
    Card[] p0 = new Card[]{ new Card(Suit.SPADE, Value.ACE), new Card(Suit.DIAMOND, Value.ACE)};
    Card[] p1 = new Card[]{ new Card(Suit.SPADE, Value.TWO), new Card(Suit.DIAMOND, Value.THREE)};
    Card[][] players = new Card[2][];
    players[0] = p0;
    players[1] = p1;
    double[] result = Hand5Comparator.probabilities( community, players);
    assertEquals( result[0], 1.0, 0.001);
  }
  
  @Test
  public void t16() {
    Card[] community = new Card[]{ new Card(Suit.CLUB, Value.ACE), new Card(Suit.HEART, Value.ACE)};
    Card[] p0 = new Card[]{ new Card(Suit.SPADE, Value.ACE), new Card(Suit.DIAMOND, Value.KING)};
    Card[] p1 = new Card[]{ new Card(Suit.DIAMOND, Value.ACE), new Card(Suit.CLUB, Value.KING)};
    Card[][] players = new Card[2][];
    players[0] = p0;
    players[1] = p1;
    double[] result = Hand5Comparator.probabilities( community, players);
    assertEquals( 0.5, result[0], 0.1);
  }

  @Test
  public void t17() {
    Card[] community = new Card[]{ new Card(Suit.CLUB, Value.TWO), new Card(Suit.HEART, Value.TWO)};
    Card[] p0 = new Card[]{ new Card(Suit.SPADE, Value.ACE), new Card(Suit.DIAMOND, Value.ACE)};
    Card[] p1 = new Card[]{ new Card(Suit.DIAMOND, Value.FIVE), new Card(Suit.CLUB, Value.FIVE)};
    Card[][] players = new Card[2][];
    players[0] = p0;
    players[1] = p1;
    double[] result = Hand5Comparator.probabilities( community, players);
    assertEquals( 1.0 - (2.0*3.0/46), result[0], 0.1);
  }
}
