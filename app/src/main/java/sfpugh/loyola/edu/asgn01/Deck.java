package sfpugh.loyola.edu.asgn01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Deck class represents the current state of the deck. The deck is initially unshuffled, but can be
 * shuffled using the shuffle function. Cards can only be drawn from the top of the deck.
 * @author Sydney Pugh
 * @version 1.0
 * @since 2018-10-06
 */
public class Deck {

    private ArrayList<Card> cardsInDeck;

    private String[] suits = {"clubs","diamonds","hearts","spades"};
    private String[] ranks = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};

    /**
     * Create a new deck of cards
     * @param numDecks - number of decks (Blackjack can be played with 1 or 2 decks)
     */
    public Deck(int numDecks) {
        cardsInDeck = new ArrayList<>();

        for (String suit : suits) {
            for (String rank : ranks) {
                cardsInDeck.add(new Card(suit, rank));    // by default, 1 deck is used

                if (numDecks == 2) cardsInDeck.add(new Card(suit, rank));
            }
        }

        shuffle();
    }

    /**
     * Draw a card from the deck
     * @return a card
     */
    public Card draw() {
        return cardsInDeck.remove(0); // remove top card from deck
    }


    /**
     * Shuffle the deck of cards
     */
    private void shuffle() {
        Random rand = new Random();

        for (int i = 0; i < cardsInDeck.size(); i++)
            Collections.swap(cardsInDeck, i, rand.nextInt(52));
    }
}