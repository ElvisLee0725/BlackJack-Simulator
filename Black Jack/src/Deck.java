import java.util.ArrayList;
import java.util.List;


public class Deck {
	private final List<Card> cards = new ArrayList<Card>();
	private int dealtIndex = 0;
	
	public Deck() {
		for(Suit s : Suit.values()) {
			for(int i = 1; i <= 13; i++) {
				cards.add(new Card(i, s));
			}
		}
	}
	public void shuffle() {
		for(int i = 0; i < cards.size(); i++) {
			System.out.print(cards.get(i).getSuit() + " " + cards.get(i).getValue() + ", ");
		}
		System.out.println();
		int end = cards.size();
		while(end > 0) {
			int index = (int) (Math.random() * end);
			Card temp = cards.get(index);
			cards.set(index, cards.get(end - 1));
			cards.set(end - 1, temp);
			end--;
		}
		for(int i = 0; i < cards.size(); i++) {
			System.out.print(cards.get(i).getSuit() + " " + cards.get(i).getValue() + ", ");
		}
		System.out.println();
	}
	public Card dealCards() {
		if(remainingCards() < 1) {
			return null;
		}
		return cards.get(dealtIndex++);
	}
	public int remainingCards() {
		return cards.size() - dealtIndex;
	}
	// Deal 2 cards for each player at first round
	public Card [] initialDeal() {
		if(remainingCards() < 2) {
			return null;
		}
		Card [] deal = new Card[2];
		for(int i = 0; i < 2; i++) {
			deal[i] = dealCards();
		}
		return deal;
	}
}
