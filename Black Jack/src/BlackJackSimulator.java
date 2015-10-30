
public class BlackJackSimulator {
	private Deck d;
	private Hand p1;
	private Hand dealer;
	public BlackJackSimulator() {
		d = new Deck();
		d.shuffle();		
		p1 = new Hand();
		p1.setName("Player");
		dealer = new Hand();
		dealer.setName("Dealer");
	}
	public void playGame() {
		
		Card [] playerCards = d.initialDeal();
		if(playerCards == null) {
			System.out.println("Oops...run out of cards");
			return ;
		}
		p1.startAddCard(playerCards);
		if(p1.isBlackJack()) {
			System.out.println("BlackJack, " + p1.getName() + " wins!");
			return ;
		}
		
		Card [] DealerCards = d.initialDeal();
		if(DealerCards == null) {
			System.out.println("Oops...run out of cards");
			return ;
		}
		dealer.startAddCard(DealerCards);
		if(dealer.isBlackJack()) {
			System.out.println("BlackJack, " + dealer.getName() + " wins!");
			return ;
		}
		while(p1.getScore() < 17) {
			Card c = d.dealCards();
			if(c == null) {
				System.out.println("Oops...run out of cards");
				return ;
			}
			p1.addCard(c);
			if(p1.isBusted()) {
				System.out.println(p1.getName() + " busted, the dealer wins");
				return ;
			}
		}
		while(dealer.getScore() < 17) {
			Card c = d.dealCards();
			if(c == null) {
				System.out.println("Oops...run out of cards");
				return ;
			}
			dealer.addCard(c);
			if(dealer.isBusted()) {
				System.out.println(dealer.getName() + " busted, the player wins");
				return ;
			}
		}
		if(p1.getScore() == dealer.getScore()) {
			System.out.println("This is a tie.");
		}
		else if(p1.getScore() > dealer.getScore()) {
			System.out.println(p1.getName() + " wins!");
		}
		else {
			System.out.println(dealer.getName() + " wins!");
		}
	}
	
}
