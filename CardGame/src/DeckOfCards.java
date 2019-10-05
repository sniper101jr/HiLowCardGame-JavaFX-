import java.util.Collections;
import java.util.Stack;

public class DeckOfCards extends Card {
	
	/*
	 * Name: Tochukwu Michael Chizea
	 * Student Number: 2981920
	 * 
	 * 
	 * *\
	 */
	//Student Number: 2981920

	protected int indexSet;
	//using stack and collection is was able to use some predefined methods to hellp facilatate this class
	Stack<Card> cardArray = new Stack<Card>(); 
	
	public void deckOfCards() {
		
		//for this method i used a nested loop to get the 4 suit valus and 14 rank values and then pass this value to the class Card constructor 
		// TODO Auto-generated constructor stub
		for (int suit = 0; suit <= 3; suit++) {
			for (int rank = 1; rank <= 13; rank++) {
				cardArray.add(new Card(rank,suit));
			}
		}	
	
	}//deckofCards
	
	protected void shuffle(){
		//these are one of the staatic methods, in this case it was shuffle method 
		Collections.shuffle(cardArray);
		indexSet = 0;
	}

	
	public Card dealTopCard() {
		//also another predefined static method but its for pop which removes the top value of the stack and then returns that value
		return cardArray.pop();
		
	} 
	public boolean isEmpty() {
		//in this method predefined isempty method is ued to see if the stack is empty
		if (cardArray.isEmpty()) {
			 
			return true;
		}
	
		return false;
		
		
	}
	

	

}
