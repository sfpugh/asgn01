package sfpugh.loyola.edu.asgn01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

/**
 * MainActivity class controls the SimpleBlackjack game.
 * @author Sydney Pugh
 * @version 1.0
 * @since 2018-10-06
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_black_jack);

        // Initializing all buttons, text views, and image views
        final Button newGameButton = findViewById(R.id.newGameButton);
        final Button hitButton = findViewById(R.id.hitButton);
        final Button stopButton = findViewById(R.id.stopButton);
        final TextView gameResultTextView = findViewById(R.id.gameResultTextView);
        final TextView playerPointsTextView = findViewById(R.id.playerPointsTextView);
        final TextView dealerPointsTextView = findViewById(R.id.dealerPointsTextView);
        final ImageView dealerCard1ImageView = findViewById(R.id.dealerCard1ImageView);
        final ImageView dealerCard2ImageView = findViewById(R.id.dealerCard2ImageView);
        final ImageView dealerCard3ImageView = findViewById(R.id.dealerCard3ImageView);
        final ImageView dealerCard4ImageView = findViewById(R.id.dealerCard4ImageView);
        final ImageView dealerCard5ImageView = findViewById(R.id.dealerCard5ImageView);
        final ImageView playerCard1ImageView = findViewById(R.id.playerCard1ImageView);
        final ImageView playerCard2ImageView = findViewById(R.id.playerCard2ImageView);
        final ImageView playerCard3ImageView = findViewById(R.id.playerCard3ImageView);
        final ImageView playerCard4ImageView = findViewById(R.id.playerCard4ImageView);
        final ImageView playerCard5ImageView = findViewById(R.id.playerCard5ImageView);

        // Disable hit and stop buttons until game start
        hitButton.setEnabled(false);
        stopButton.setEnabled(false);

        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Deck deck = new Deck(1);
                final Player dealer = new Player(deck);
                final Player player = new Player(deck);
                Card[] tempHand;

                gameResultTextView.setText(""); // Clear prior game result

                // Show dealer's initial card-pair
                tempHand = dealer.getHand();
                dealerPointsTextView.setText(dealer.handValue()+"");
                dealerCard1ImageView.setImageResource( getCardDrawable(tempHand[0].getSuit(),tempHand[0].getRank()) );
                dealerCard2ImageView.setImageResource( getCardDrawable(tempHand[1].getSuit(),tempHand[1].getRank()) );
                dealerCard3ImageView.setImageResource(android.R.color.transparent);
                dealerCard4ImageView.setImageResource(android.R.color.transparent);
                dealerCard5ImageView.setImageResource(android.R.color.transparent);

                // Show player's initial card-pair
                tempHand = player.getHand();
                playerPointsTextView.setText(player.handValue()+"");
                playerCard1ImageView.setImageResource( getCardDrawable(tempHand[0].getSuit(),tempHand[0].getRank()) );
                playerCard2ImageView.setImageResource( getCardDrawable(tempHand[1].getSuit(),tempHand[1].getRank()) );
                playerCard3ImageView.setImageResource(android.R.color.transparent);
                playerCard4ImageView.setImageResource(android.R.color.transparent);
                playerCard5ImageView.setImageResource(android.R.color.transparent);


                // Check for Blackjack
                final int playerPts = player.handValue();
                int dealerPts = dealer.handValue();
                if (playerPts == 21 || dealerPts == 21) {
                    if (playerPts == 21 && dealerPts == 21)
                        gameResultTextView.setText(R.string.tie);
                    else if (playerPts == 21)
                        gameResultTextView.setText(R.string.player_win);
                    else
                        gameResultTextView.setText(R.string.dealer_win);

                    hitButton.setEnabled(false);
                    stopButton.setEnabled(false);
                    return;
                }

                // Enable player to take his/her turn
                hitButton.setEnabled(true);
                stopButton.setEnabled(true);

                /* The player has chosen to play another turn */
                hitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (player.numCardsInHand() < 5) {  // Can draw 3 additional cards max
                            Card c = player.hit(deck);

                            playerPointsTextView.setText(player.handValue()+"");

                            switch (player.numCardsInHand()) {
                                case 3: playerCard3ImageView.setImageResource(getCardDrawable(c.getSuit(), c.getRank())); break;
                                case 4: playerCard4ImageView.setImageResource(getCardDrawable(c.getSuit(), c.getRank())); break;
                                case 5: playerCard5ImageView.setImageResource(getCardDrawable(c.getSuit(), c.getRank()));
                            }

                            if (player.handValue() > 21) {   // if the player busts, dealer wins
                                gameResultTextView.setText(R.string.dealer_win);
                                hitButton.setEnabled(false);
                                stopButton.setEnabled(false);
                            }
                        }
                    }
                });

                /* The stop button indicates that it is the dealer's turn*/
                stopButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Card c;
                        Random rand = new Random();

                        // Disable hit and stop buttons
                        hitButton.setEnabled(false);
                        stopButton.setEnabled(false);

                        // Dealer's turn
                        if (dealer.handValue() < player.handValue()) {
                            while (dealer.numCardsInHand() < 5) {    // Can draw 3 additional cards max
                                if (dealer.handValue() > 21 || !rand.nextBoolean())  // true->hit, false->stop
                                    break;
                                else {                    // otherwise hit
                                    c = dealer.hit(deck);

                                    dealerPointsTextView.setText(dealer.handValue()+"");

                                    switch (dealer.numCardsInHand()) {
                                        case 3: dealerCard3ImageView.setImageResource( getCardDrawable(c.getSuit(),c.getRank()) ); break;
                                        case 4: dealerCard4ImageView.setImageResource( getCardDrawable(c.getSuit(),c.getRank()) ); break;
                                        case 5: dealerCard5ImageView.setImageResource( getCardDrawable(c.getSuit(),c.getRank()) );
                                    }
                                }
                            }
                        }

                        // Show game result
                        int playerPts = player.handValue();
                        int dealerPts = dealer.handValue();
                        if (dealerPts > 21 || playerPts > dealerPts)
                            gameResultTextView.setText(R.string.player_win);
                        else if (playerPts < dealerPts)
                            gameResultTextView.setText(R.string.dealer_win);
                        else
                            gameResultTextView.setText(R.string.tie);

                        hitButton.setEnabled(false);
                        stopButton.setEnabled(false);
                    }
                });
            }
        });
    }

    /**
     * Get the integer index of the drawable for a particular card
     * @param suit - suit of card
     * @param rank - rank of card
     * @return a card's drawable index
     */
    private int getCardDrawable(String suit, String rank) {
        if (suit.equals("clubs")) {
            switch (rank) {
                case "A":   return(R.drawable.ace_of_clubs);
                case "2":   return(R.drawable.two_of_clubs);
                case "3":   return(R.drawable.three_of_clubs);
                case "4":   return(R.drawable.four_of_clubs);
                case "5":   return(R.drawable.five_of_clubs);
                case "6":   return(R.drawable.six_of_clubs);
                case "7":   return(R.drawable.seven_of_clubs);
                case "8":   return(R.drawable.eight_of_clubs);
                case "9":   return(R.drawable.nine_of_clubs);
                case "10":  return(R.drawable.ten_of_clubs);
                case "J":   return(R.drawable.jack_of_clubs);
                case "Q":   return(R.drawable.queen_of_clubs);
                case "K":   return(R.drawable.king_of_clubs);
                default:    return(R.drawable.back);
            }
        } else if (suit.equals("diamonds")) {
            switch (rank) {
                case "A":   return(R.drawable.ace_of_diamonds);
                case "2":   return(R.drawable.two_of_diamonds);
                case "3":   return(R.drawable.three_of_diamonds);
                case "4":   return(R.drawable.four_of_diamonds);
                case "5":   return(R.drawable.five_of_diamonds);
                case "6":   return(R.drawable.six_of_diamonds);
                case "7":   return(R.drawable.seven_of_diamonds);
                case "8":   return(R.drawable.eight_of_diamonds);
                case "9":   return(R.drawable.nine_of_diamonds);
                case "10":  return(R.drawable.ten_of_diamonds);
                case "J":   return(R.drawable.jack_of_diamonds);
                case "Q":   return(R.drawable.queen_of_diamonds);
                case "K":   return(R.drawable.king_of_diamonds);
                default:    return(R.drawable.back);
            }
        } else if (suit.equals("hearts")) {
            switch (rank) {
                case "A":   return(R.drawable.ace_of_hearts);
                case "2":   return(R.drawable.two_of_hearts);
                case "3":   return(R.drawable.three_of_hearts);
                case "4":   return(R.drawable.four_of_hearts);
                case "5":   return(R.drawable.five_of_hearts);
                case "6":   return(R.drawable.six_of_hearts);
                case "7":   return(R.drawable.seven_of_hearts);
                case "8":   return(R.drawable.eight_of_hearts);
                case "9":   return(R.drawable.nine_of_hearts);
                case "10":  return(R.drawable.ten_of_hearts);
                case "J":   return(R.drawable.jack_of_hearts);
                case "Q":   return(R.drawable.queen_of_hearts);
                case "K":   return(R.drawable.king_of_hearts);
                default:    return(R.drawable.back);
            }
        } else if (suit.equals("spades")) {
            switch (rank) {
                case "A":   return(R.drawable.ace_of_spades);
                case "2":   return(R.drawable.two_of_spades);
                case "3":   return(R.drawable.three_of_spades);
                case "4":   return(R.drawable.four_of_spades);
                case "5":   return(R.drawable.five_of_spades);
                case "6":   return(R.drawable.six_of_spades);
                case "7":   return(R.drawable.seven_of_spades);
                case "8":   return(R.drawable.eight_of_spades);
                case "9":   return(R.drawable.nine_of_spades);
                case "10":  return(R.drawable.ten_of_spades);
                case "J":   return(R.drawable.jack_of_spades);
                case "Q":   return(R.drawable.queen_of_spades);
                case "K":   return(R.drawable.king_of_spades);
                default:    return(R.drawable.back);
            }
        }
        return(R.drawable.back);
    }
}

