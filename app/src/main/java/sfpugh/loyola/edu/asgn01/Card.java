package sfpugh.loyola.edu.asgn01;

/**
 * Card represents a single playing card. A playing card holds two values: the suit and the rank of
 * the card.
 * @author Sydney Pugh
 * @version 1.0
 * @since 2018-10-06
 */
public class Card {

    private String suit;
    private String rank;

    /**
     * Create a card
     * @param s - suit of card
     * @param r - rank of card
     */
    public Card(String s, String r) {
        suit = s;
        rank = r;
    }

    /**
     * Returns the suit of the card
     * @return suit of card
     */
    public String getSuit() {
        return suit;
    }

    /**
     * Returns the rank of the card
     * @return rank of card
     */
    public String getRank() {
        return rank;
    }
}
