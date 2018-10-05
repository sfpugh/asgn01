package sfpugh.loyola.edu.asgn01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SimpleBlackJack extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_black_jack);

        playBlackJack();
    }

    /**
     *  Run a game of Blackjack
     */
    private void playBlackJack() {
        Button newGameButton = (Button) findViewById(R.id.NewGameButton);
        final TextView gameResultTextView = (TextView) findViewById(R.id.gameResultTextView);
        final TextView playerPointsTextView = (TextView) findViewById(R.id.playerPointsTextView);
        final TextView dealerPointsTextView = (TextView) findViewById(R.id.dealerPointsTextView);

        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Instantiate the game
                Deck deck = new Deck(1);
                Player player = new Player(deck);
                Player dealer = new Player(deck);

                // Display initial cards and hand values
                displayCard();  // player's first card
                displayCard();  // player's second card
                displayCard();  // dealer's first card
                displayCard();  // dealer's second card
                playerPointsTextView.setText(player.handValue());
                dealerPointsTextView.setText(dealer.handValue());


                // Check Blackjack scenarios
                if (player.checkBlackjack() == true && dealer.checkBlackjack() == true)
                    gameResultTextView.setText("It's a tie!");
                else if (player.checkBlackjack() == true && dealer.checkBlackjack() == false)
                    gameResultTextView.setText("Congrats! You won.");
                else if (player.checkBlackjack() == false && dealer.checkBlackjack() == true)
                    gameResultTextView.setText("Sorry. The dealer won.");
            }
        });
    }

    /**
     *
     * @param cardObject - app object to display card on
     * @param card       - card to display
     */
    private void displayCard() {

    }
}
