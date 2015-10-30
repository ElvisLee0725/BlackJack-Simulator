import java.util.ArrayList;
import java.util.List;

public class Hand {
	protected List<Card> myCards = new ArrayList<Card>();
	private String name;
	// Add 2 cards at beginning:
	public void startAddCard(Card [] cards) {
		for(Card c : cards) {
			myCards.add(c);
		}
		System.out.println(this.name + " has " + getScore() + " at hand.");
	}
	// Add 1 card each time:
	public void addCard(Card c) {
		myCards.add(c);
		System.out.println(this.name + " has " + getScore() + " at hand.");
	}
	public void setName(String n) {
		this.name = n;
	}
	public String getName() {
		return name;
	}
	public int getScore() {
		List<Integer> scores = possibleScores();
		int minOver21 = Integer.MAX_VALUE;
		int maxUnder21 = Integer.MIN_VALUE;
		for(int s : scores) {
			if(s > 21 && s < minOver21) {
				minOver21 = s;
			}
			else if(s <= 21 && s > maxUnder21) {
				maxUnder21 = s;
			}
		}
		// If maxUnder is still the same, it means no possible score under 21, so return minOver21
		return maxUnder21 == Integer.MIN_VALUE ? minOver21 : maxUnder21;
	}
	private List<Integer> possibleScores() {
		List<Integer> scores = new ArrayList<Integer>();
		for(Card c : myCards) {
			updateScore(c, scores);
		}
		return scores;
	}
	private void updateScore(Card c, List<Integer> scores) {
		if(scores.isEmpty()) {
			for(int score : getScores(c)) {
				scores.add(score);
			}
		}
		else {
			// We need to find all the possible results
			// size need to be set first, otherwise it might run forever...
			final int size = scores.size();
			for(int i = 0; i < size; i++) {
				int prevScore = scores.get(i);
				int [] toAdd = getScores(c);
				scores.set(i, prevScore + toAdd[0]);
//				for(int j = 1; j < toAdd.length; j++) {
//					scores.add(prevScore + toAdd[j]);
//				}
				if(toAdd.length == 2) {
					scores.add(prevScore + toAdd[1]);
				}
			}
		}
	}
	// Since there could be more than one value (Ace card), we return a integer array
	private int [] getScores(Card c) {
		if(c.getValue() == 1) {
			return new int[]{1, 11};
		}
		else if(c.getValue() > 10) {
			return new int[]{10};
		}
		else {
			return new int[]{c.getValue()};
		}
	}
	public boolean isBlackJack() {
		if(myCards.size() != 2) {
			return false;
		}
		Card first = myCards.get(0);
		Card second = myCards.get(1);
		if((isAce(first) && isTen(second)) || (isAce(second) && isTen(first))) {
			return true;
		}
		return false;
	}
	
	public boolean is21() {
		return getScore() == 21;
	}
	
	public boolean isBusted() {
		return getScore() > 21;
	}
	
	public boolean isAce(Card c) {
		return c.getValue() == 1;
	}
	public boolean isTen(Card c) {
		return c.getValue() == 10;
	}
}
