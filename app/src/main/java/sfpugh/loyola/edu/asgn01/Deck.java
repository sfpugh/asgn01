/**
 * A class to represent the current state of the deck
 * @author Sydney Pugh
 */

package sfpugh.loyola.edu.asgn01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {

    private ArrayList<Card> cards_in_deck;

    private String[] suits = {"clubs","diamonds","hearts","spades"};
    private String[] ranks = {"A","1","2","3","4","5","6","7","8","9","10","J","Q","K"};

    /**
     * Create a new deck of cards
     * @param num_of_decks - number of decks (Blackjack can be played with 1 or 2 decks)
     */
    public Deck(int num_of_decks) {
        cards_in_deck = new ArrayList<>();

        for (String suit : suits) {
            for (String rank : ranks) {
                cards_in_deck.add(new Card(suit, rank));    // by default, 1 deck is used

                if (num_of_decks == 2)              // if 2 decks is specified
                    cards_in_deck.add(new Card(suit, rank));
            }
        }

        shuffle();
    }

    /**
     * Draw a card from the deck
     * @return a card
     */
    public Card draw() {
        return cards_in_deck.remove(0); // remove top card from deck
    }


    /**
     * Shuffle the deck of cards
     */
    private void shuffle() {
        Random rand = new Random();

        for (int i = 0; i < cards_in_deck.size(); i++)
            Collections.swap(cards_in_deck, i, rand.nextInt(52));
    }
}