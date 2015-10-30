
public class Card {
	private int faceValue;
	private Suit suit;
	public Card(int val, Suit s) {
		this.faceValue = val;
		this.suit = s;
	}
	public int getValue() {
		if(faceValue >= 11 && faceValue <= 13) {
			return 10;
		}
		return faceValue;
	}
	public Suit getSuit() {
		return suit;
	}
}
