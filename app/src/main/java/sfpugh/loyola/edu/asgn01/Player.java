package sfpugh.loyola.edu.asgn01;

import java.util.ArrayList;

/**
 * Player class represents a player in the SimpleBlackjack game. The player can draw cards from the
 * deck into his/her own hand. Various operations to interpret the player's hand are implemented.
 * @author Sydney Pugh
 * @version 1.0
 * @since 2018-10-06
 */
public class Player {

    private ArrayList<Card> hand;

    /**
     * Create a new player with a starting hand of 2 cards
     * @param d - the deck
     */
    public Player(Deck d) {
        hand = new ArrayList<>();
        hand.add(d.draw());
        hand.add(d.draw());
    }

    /**
     * Hit. Draw a card from the deck
     * @param d - the deck
     * @return card drawn from the deck
     */
    public Card hit(Deck d) {
        if (hand.size() < 5)   // at most 5 cards in hand
            hand.add(d.draw());

        return hand.get(hand.size()-1);
    }

    /**
     * Calculates the value of the player's hand
     * @return value of hand
     */
    public int handValue() {
        if (hand.isEmpty())
            return 0;

        int value = 0;
        for (int i = 0; i < hand.size(); i++) {
            // add value of card to total
            switch (hand.get(i).getRank()) {
                    case "A":   value += (value + 11 > 21) ? 1 : 11; break;
                    case "2":   value += 2; break;
                    case "3":   value += 3; break;
                    case "4":   value += 4; break;
                    case "5":   value += 5; break;
                    case "6":   value += 6; break;
                    case "7":   value += 7; break;
                    case "8":   value += 8; break;
                    case "9":   value += 9; break;
                    case "10":
                    case "J":
                    case "Q":
                    case "K":   value += 10; break;
                    default:    break;
            }
        }
        return value;
    }

    /**
     * Gets the player's current hand
     * @return array representation of the player's hand
     */
    public Card[] getHand(){
        return hand.toArray(new Card[0]);
    }

    /**
     * Returns the number of cards in the player's hand
     * @return number of cards in current hand
     */
    public int numCardsInHand() { return hand.size(); }
}
