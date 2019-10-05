

public class Card {
	 //rank and sute of the card
	
	/*
	 * Name: Tochukwu Michael Chizea
	 * Student Number: 2981920
	 * 
	 * 
	 * *\
	 */

	private int rank = 0;
	private int suit =0;
	
	public Card() {
		// TODO Auto-generated constructor stub
	}
	
	public Card(int r, int s) {
		//constructor for card which takes in two arguments rank and suit
		// TODO Auto-generated constructor stub
		this.rank=r;
		this.suit =s;
	}
	
	
	public boolean rankIsGreaterThan(Card c) {
		//this method compares two card objects and if one is greater then the other then it return the value as true
		boolean rankIsGreater = false;
		
		if(rank > c.getRank()) {
			rankIsGreater = true;
		}//if
		return rankIsGreater;
		
	}//rankIsGreater method

	
	public boolean rankIsLessThan(Card c) {
		//this method compares two card objects and if one is less than the other then it return the value as true
		boolean rankIsLess = false;
		
		if(rank < c.getRank()) {
			rankIsLess = true;
		}//if
		return rankIsLess;
		
	}//rank is less than method
	
	public boolean rankIsEqualsTo(Card c) {
		//this method compares two card objects and if one is equal to the other then it return the value as true
		boolean rankIsEqualsTo = false;
		
		if(rank == c.getRank()) {
			rankIsEqualsTo = true;
		}//if
		return rankIsEqualsTo;
		
	}//rankisEqualsto()
	
	
	public int getRank() {
		return rank;
	}//get rank
	
	public int getSuit() {
		return suit;
	}
	
	
	    // Returns a String representation of the card's value.
	    // returns a regular card, one of the strings "Ace", "2" or one of the case below  
	public String toString() {
		
		String cardSuit = "";
		String cardRank = "";
		String cardString = "";
		
		
		int cs = getSuit();
		int cr = getRank();
		
		switch(cr) {
		case 1:
			cardRank = "ace";
			break;
		case 2: 
			cardRank ="2";
			break;
		case 3: 
			cardRank ="3";
			break;
		case 4:
			cardRank ="4";
			break;
		case 5:
			cardRank ="5";
			break;
		case 6:
			cardRank ="6";
			break;
		case 7:
			cardRank ="7";
			break;
		case 8:
			cardRank ="8";
			break;
		case 9:
			cardRank ="9";
			break;
		case 10:
			cardRank ="10";
			break;
		case 11:
			cardRank ="jack";
			break;
		case 12:
			cardRank ="queen";
			break;
		case 13:
			cardRank ="king";
			break;
		default:
			cardRank = "n/a";
			
		}//switch rank
		
		//got a string representation of the rank
		//now get a string representation of the siut
		
		switch(cs) {
		case 0:
			cardSuit = "hearts";
			break;
		case 1:
			cardSuit = "diamonds";
			break;
		case 2:
			cardSuit = "clubs";
			break;  
		case 3:
			cardSuit = "spades";
			break;
		default:
			cardSuit = "n/a";
			
		}
		cardString = "file:cards/"+cardRank+"_of_"+cardSuit+".png";
		
		return cardString;
		
	}//toString()
	

}





























