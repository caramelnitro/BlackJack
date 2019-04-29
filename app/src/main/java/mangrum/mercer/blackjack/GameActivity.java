package mangrum.mercer.blackjack;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends Activity implements View.OnClickListener {


    private static int[] deck = {R.drawable.ace_clubs, R.drawable.ace_diamonds, R.drawable.ace_hearts, R.drawable.ace_spades,
            R.drawable.clubs_2, R.drawable.clubs_3, R.drawable.clubs_4, R.drawable.clubs_5,
            R.drawable.clubs_6, R.drawable.clubs_7, R.drawable.clubs_8, R.drawable.clubs_9,
            R.drawable.clubs_10, R.drawable.jack_clubs, R.drawable.king_clubs, R.drawable.queen_clubs,
            R.drawable.diamonds_2, R.drawable.diamonds_3, R.drawable.diamonds_4, R.drawable.diamonds_5,
            R.drawable.diamonds_6, R.drawable.diamonds_7, R.drawable.diamonds_8, R.drawable.diamonds_9,
            R.drawable.diamonds_10, R.drawable.jack_diamonds, R.drawable.king_diamonds, R.drawable.queen_diamonds,
            R.drawable.spades_2, R.drawable.spades_3, R.drawable.spades_4, R.drawable.spades_5,
            R.drawable.spades_6, R.drawable.spades_7, R.drawable.spades_8, R.drawable.spades_9,
            R.drawable.spades_10, R.drawable.jack_spades, R.drawable.king_spades, R.drawable.queen_spades,
            R.drawable.hearts_2, R.drawable.hearts_3, R.drawable.hearts_4, R.drawable.hearts_5,
            R.drawable.hearts_6, R.drawable.hearts_7, R.drawable.hearts_8, R.drawable.hearts_9,
            R.drawable.hearts_10, R.drawable.jack_hearts, R.drawable.king_hearts, R.drawable.queen_hearts
    };
    private static int[] dealerSlots = {R.id.dealer1, R.id.dealer2, R.id.dealer3, R.id.dealer4, R.id.dealer5, R.id.dealer6, R.id.dealer7};
    private static int[] playerSlots = {R.id.player1, R.id.player2, R.id.player3, R.id.player4, R.id.player5, R.id.player6, R.id.player7};
    Random r = new Random();
    int deckSize = 52;
    int rand1 = r.nextInt(52);
    int rand2 = r.nextInt(52);
    int bet = 0;
    int chips = 1000;
    int hit = 0;
    int dealHit = 0;
    int dealerScore = 0;
    boolean dealAces = false;
    boolean playAces = false;
    boolean playStood = false;
    private int dealerCards = 0;
    private int playerCards = 0;
    private int[] dealerDealt = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int[] playerDealt = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    @Override
    public void onCreate(Bundle savedInstanceState) {

        // Set no title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Make full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);
        ImageView dealer = findViewById(R.id.dealer1);
        ImageView player = findViewById(R.id.player1);
        TextView tv = findViewById(R.id.winText);
        tv.setText("");

        shuffle(deck);
        shuffle(deck);
        shuffle(deck);
        shuffle(deck);

        playerDealt[playerCards] = deck[deckSize - 1];
        player.setImageResource(deck[deckSize - 1]);
        deckSize--;
        playerCards++;
        dealerDealt[dealerCards] = deck[deckSize - 1];
        dealer.setImageResource(getResources().getIdentifier(String.valueOf(deck[deckSize - 1]), "drawable", getPackageName()));
        deckSize--;
        dealerCards++;
        playerDealt[playerCards] = deck[deckSize - 1];
        player = findViewById(R.id.player2);
        player.setImageResource(getResources().getIdentifier(String.valueOf(deck[deckSize - 1]), "drawable", getPackageName()));
        playerCards++;
        deckSize--;
        winCheck(playerDealt, dealerDealt);

        Button b = findViewById(R.id.hit_button);
        b.setOnClickListener(this);

        b = findViewById(R.id.stay_button);
        b.setOnClickListener(this);

        b = findViewById(R.id.deal_button);
        b.setOnClickListener(this);
        b.setEnabled(false);
        b.setVisibility(View.INVISIBLE);
    }

    public void winCheck(int[] pCards, int[] dCards) {
        int pTotal = 0;
        int dTotal = 0;
        dealerScore = 0;
        TextView tv = findViewById(R.id.winText);
        TextView pText = findViewById(R.id.playerText);
        TextView dText = findViewById(R.id.dealerText);
        Button b = findViewById(R.id.hit_button);
        for (int i = 0; i < pCards.length; i++) {
            if (R.drawable.spades_2 == pCards[i] || R.drawable.hearts_2 == pCards[i]
                    || R.drawable.clubs_2 == pCards[i] || R.drawable.diamonds_2 == pCards[i]) {
                pTotal += 2;
            } else if (R.drawable.spades_3 == pCards[i] || R.drawable.hearts_3 == pCards[i]
                    || R.drawable.clubs_3 == pCards[i] || R.drawable.diamonds_3 == pCards[i]) {
                pTotal += 3;
            } else if (R.drawable.spades_4 == pCards[i] || R.drawable.hearts_4 == pCards[i]
                    || R.drawable.clubs_4 == pCards[i] || R.drawable.diamonds_4 == pCards[i]) {
                pTotal += 4;
            } else if (R.drawable.spades_5 == pCards[i] || R.drawable.hearts_5 == pCards[i]
                    || R.drawable.clubs_5 == pCards[i] || R.drawable.diamonds_5 == pCards[i]) {
                pTotal += 5;
            } else if (R.drawable.spades_6 == pCards[i] || R.drawable.hearts_6 == pCards[i]
                    || R.drawable.clubs_6 == pCards[i] || R.drawable.diamonds_6 == pCards[i]) {
                pTotal += 6;
            } else if (R.drawable.spades_7 == pCards[i] || R.drawable.hearts_7 == pCards[i]
                    || R.drawable.clubs_7 == pCards[i] || R.drawable.diamonds_7 == pCards[i]) {
                pTotal += 7;
            } else if (R.drawable.spades_8 == pCards[i] || R.drawable.hearts_8 == pCards[i]
                    || R.drawable.clubs_8 == pCards[i] || R.drawable.diamonds_8 == pCards[i]) {
                pTotal += 8;
            } else if (R.drawable.spades_9 == pCards[i] || R.drawable.hearts_9 == pCards[i]
                    || R.drawable.clubs_9 == pCards[i] || R.drawable.diamonds_9 == pCards[i]) {
                pTotal += 9;
            } else if (R.drawable.jack_hearts == pCards[i] || R.drawable.jack_spades == pCards[i]
                    || R.drawable.jack_clubs == pCards[i] || R.drawable.jack_diamonds == pCards[i]
                    || R.drawable.queen_hearts == pCards[i] || R.drawable.queen_spades == pCards[i]
                    || R.drawable.queen_clubs == pCards[i] || R.drawable.queen_diamonds == pCards[i]
                    || R.drawable.king_hearts == pCards[i] || R.drawable.king_spades == pCards[i]
                    || R.drawable.king_clubs == pCards[i] || R.drawable.king_diamonds == pCards[i]
                    || R.drawable.clubs_10 == pCards[i] || R.drawable.diamonds_10 == pCards[i]
                    || R.drawable.hearts_10 == pCards[i] || R.drawable.spades_10 == pCards[i]) {
                pTotal += 10;
            } else if (R.drawable.ace_spades == pCards[i] || R.drawable.ace_hearts == pCards[i]
                    || R.drawable.ace_diamonds == pCards[i] || R.drawable.ace_clubs == pCards[i]) {
                pTotal += 1;
                playAces = true;
            }
        }
        if (pTotal + 10 <= 21 && playAces) {
            pTotal += 10;
        }
        pText.setText(String.valueOf(pTotal));
        if (pTotal == 21) {
            tv.setText(R.string.playerWins);
            b.setEnabled(false);
            b = findViewById(R.id.stay_button);
            b.setEnabled(false);
            showDeal();
        } else if (pTotal > 21) {
            tv.setText(R.string.dealerWins);
            b.setEnabled(false);
            b = findViewById(R.id.stay_button);
            b.setEnabled(false);
            showDeal();
        }

        for (int i = 0; i < dCards.length; i++) {
            if (R.drawable.spades_2 == dCards[i] || R.drawable.hearts_2 == dCards[i]
                    || R.drawable.clubs_2 == dCards[i] || R.drawable.diamonds_2 == dCards[i]) {
                dTotal += 2;
                dealerScore += 2;
            } else if (R.drawable.spades_3 == dCards[i] || R.drawable.hearts_3 == dCards[i]
                    || R.drawable.clubs_3 == dCards[i] || R.drawable.diamonds_3 == dCards[i]) {
                dTotal += 3;
                dealerScore += 3;
            } else if (R.drawable.spades_4 == dCards[i] || R.drawable.hearts_4 == dCards[i]
                    || R.drawable.clubs_4 == dCards[i] || R.drawable.diamonds_4 == dCards[i]) {
                dTotal += 4;
                dealerScore += 4;
            } else if (R.drawable.spades_5 == dCards[i] || R.drawable.hearts_5 == dCards[i]
                    || R.drawable.clubs_5 == dCards[i] || R.drawable.diamonds_5 == dCards[i]) {
                dTotal += 5;
                dealerScore += 5;
            } else if (R.drawable.spades_6 == dCards[i] || R.drawable.hearts_6 == dCards[i]
                    || R.drawable.clubs_6 == dCards[i] || R.drawable.diamonds_6 == dCards[i]) {
                dTotal += 6;
                dealerScore += 6;
            } else if (R.drawable.spades_7 == dCards[i] || R.drawable.hearts_7 == dCards[i]
                    || R.drawable.clubs_7 == dCards[i] || R.drawable.diamonds_7 == dCards[i]) {
                dTotal += 7;
                dealerScore += 7;
            } else if (R.drawable.spades_8 == dCards[i] || R.drawable.hearts_8 == dCards[i]
                    || R.drawable.clubs_8 == dCards[i] || R.drawable.diamonds_8 == dCards[i]) {
                dTotal += 8;
                dealerScore += 8;
            } else if (R.drawable.spades_9 == dCards[i] || R.drawable.hearts_9 == dCards[i]
                    || R.drawable.clubs_9 == dCards[i] || R.drawable.diamonds_9 == dCards[i]) {
                dTotal += 9;
                dealerScore += 9;
            } else if (R.drawable.jack_hearts == dCards[i] || R.drawable.jack_spades == dCards[i]
                    || R.drawable.jack_clubs == dCards[i] || R.drawable.jack_diamonds == dCards[i]
                    || R.drawable.queen_hearts == dCards[i] || R.drawable.queen_spades == dCards[i]
                    || R.drawable.queen_clubs == dCards[i] || R.drawable.queen_diamonds == dCards[i]
                    || R.drawable.king_hearts == dCards[i] || R.drawable.king_spades == dCards[i]
                    || R.drawable.king_clubs == dCards[i] || R.drawable.king_diamonds == dCards[i]
                    || R.drawable.clubs_10 == dCards[i] || R.drawable.diamonds_10 == dCards[i]
                    || R.drawable.hearts_10 == dCards[i] || R.drawable.spades_10 == dCards[i]) {
                dTotal += 10;
                dealerScore += 10;
            } else if (R.drawable.ace_spades == dCards[i] || R.drawable.ace_hearts == dCards[i]
                    || R.drawable.ace_diamonds == dCards[i] || R.drawable.ace_clubs == dCards[i]) {
                dTotal += 1;
                dealerScore += 1;
                dealAces = true;
            }
        }
        if (dTotal + 10 <= 21 && dealAces) {
            dTotal += 10;
            dealerScore += 10;
        }
        if (dTotal == 21) {
            /*placeholder code so there are less warnings*/
            dTotal = 21;
            dealerScore += 21;
        } else if (dTotal > 21) {
            /*placeholder code so there are less warnings*/
            dTotal = 22;
            dealerScore += 22;
        }
        dText.setText(String.valueOf(dTotal));
        if (dTotal == 21 || (dTotal > pTotal && dTotal <= 21 && playStood)) {
            tv.setText(R.string.dealerWins);
            b.setEnabled(false);
            b = findViewById(R.id.stay_button);
            b.setEnabled(false);
            showDeal();
        } else if (dTotal > 21) {
            tv.setText(R.string.playerWins);
            b.setEnabled(false);
            b = findViewById(R.id.stay_button);
            b.setEnabled(false);
            showDeal();
        } else if (dTotal == pTotal && playStood) {
            tv.setText(R.string.playerWins);
            b.setEnabled(false);
            b = findViewById(R.id.stay_button);
            b.setEnabled(false);
            showDeal();
        }
    }

    public void onClick(View v) {
        ImageView player;
        ImageView dealer;
        if (v.getId() == R.id.hit_button) {
            hit++;
            if (hit == 1) {
                player = findViewById(R.id.player3);
                player.setVisibility(View.VISIBLE);
                player.setImageResource(getResources().getIdentifier(String.valueOf(deck[deckSize - 1]), "drawable", getPackageName()));
                playerDealt[playerCards] = deck[deckSize - 1];
                deckSize--;
                playerCards++;
                winCheck(playerDealt, dealerDealt);
            } else if (hit == 2) {
                player = findViewById(R.id.player4);
                player.setVisibility(View.VISIBLE);
                player.setImageResource(getResources().getIdentifier(String.valueOf(deck[deckSize - 1]), "drawable", getPackageName()));
                playerDealt[playerCards] = deck[deckSize - 1];
                deckSize--;
                playerCards++;
                winCheck(playerDealt, dealerDealt);
            } else if (hit == 3) {
                player = findViewById(R.id.player5);
                player.setVisibility(View.VISIBLE);
                player.setImageResource(getResources().getIdentifier(String.valueOf(deck[deckSize - 1]), "drawable", getPackageName()));
                playerDealt[playerCards] = deck[deckSize - 1];
                deckSize--;
                playerCards++;
                winCheck(playerDealt, dealerDealt);
            } else if (hit == 4) {
                player = findViewById(R.id.player6);
                player.setVisibility(View.VISIBLE);
                player.setImageResource(getResources().getIdentifier(String.valueOf(deck[deckSize - 1]), "drawable", getPackageName()));
                playerDealt[playerCards] = deck[deckSize - 1];
                deckSize--;
                playerCards++;
                winCheck(playerDealt, dealerDealt);
            } else if (hit == 5) {
                player = findViewById(R.id.player7);
                player.setVisibility(View.VISIBLE);
                player.setImageResource(getResources().getIdentifier(String.valueOf(deck[deckSize - 1]), "drawable", getPackageName()));
                playerDealt[playerCards] = deck[deckSize - 1];
                deckSize--;
                playerCards++;
                winCheck(playerDealt, dealerDealt);
            }
        }
        if (v.getId() == R.id.stay_button) {
            playStood = true;
            Button b = findViewById(R.id.hit_button);
            b.setEnabled(false);
            b = findViewById(R.id.stay_button);
            b.setEnabled(false);
            dealer = findViewById(R.id.dealer2);
            dealer.setImageResource(getResources().getIdentifier(String.valueOf(deck[deckSize - 1]), "drawable", getPackageName()));
            dealerDealt[dealerCards] = deck[deckSize - 1];
            deckSize--;
            dealerCards++;
            while (dealerScore < 17) {
                dealHit++;
                if (dealHit == 1) {
                    dealer = findViewById(R.id.player3);
                    dealer.setVisibility(View.VISIBLE);
                    dealer.setImageResource(getResources().getIdentifier(String.valueOf(deck[deckSize - 1]), "drawable", getPackageName()));
                    dealerDealt[dealerCards] = deck[deckSize - 1];
                    deckSize--;
                    dealerCards++;
                    winCheck(playerDealt, dealerDealt);
                } else if (dealHit == 2) {
                    dealer = findViewById(R.id.dealer4);
                    dealer.setVisibility(View.VISIBLE);
                    dealer.setImageResource(getResources().getIdentifier(String.valueOf(deck[deckSize - 1]), "drawable", getPackageName()));
                    dealerDealt[dealerCards] = deck[deckSize - 1];
                    deckSize--;
                    dealerCards++;
                    winCheck(playerDealt, dealerDealt);
                } else if (dealHit == 3) {
                    dealer = findViewById(R.id.dealer5);
                    dealer.setVisibility(View.VISIBLE);
                    dealer.setImageResource(getResources().getIdentifier(String.valueOf(deck[deckSize - 1]), "drawable", getPackageName()));
                    dealerDealt[dealerCards] = deck[deckSize - 1];
                    deckSize--;
                    dealerCards++;
                    winCheck(playerDealt, dealerDealt);
                } else if (dealHit == 4) {
                    dealer = findViewById(R.id.dealer6);
                    dealer.setVisibility(View.VISIBLE);
                    dealer.setImageResource(getResources().getIdentifier(String.valueOf(deck[deckSize - 1]), "drawable", getPackageName()));
                    dealerDealt[dealerCards] = deck[deckSize - 1];
                    deckSize--;
                    dealerCards++;
                    winCheck(playerDealt, dealerDealt);
                } else if (dealHit == 5) {
                    dealer = findViewById(R.id.dealer7);
                    dealer.setVisibility(View.VISIBLE);
                    dealer.setImageResource(getResources().getIdentifier(String.valueOf(deck[deckSize - 1]), "drawable", getPackageName()));
                    dealerDealt[dealerCards] = deck[deckSize - 1];
                    deckSize--;
                    dealerCards++;
                    winCheck(playerDealt, dealerDealt);
                }
            }
        }
        if(v.getId() == R.id.deal_button){
            redeal();
        }
    }

    public void shuffle(int[] shuffled) {
        for (int j = 0; j < 1000000; j++) {
            int temp = shuffled[rand1];
            shuffled[rand1] = shuffled[rand2];
            shuffled[rand2] = temp;
            rand1 = r.nextInt(52);
            rand2 = r.nextInt(52);
        }
        deck = shuffled;
    }
    public void redeal(){
        ImageView iv;
        Button b;
        TextView tv;
        tv = findViewById(R.id.winText);
        tv.setVisibility(View.INVISIBLE);
        b = findViewById(R.id.stay_button);
        b.setEnabled(true);
        b = findViewById(R.id.hit_button);
        b.setEnabled(true);
        hideDeal();
        deckSize = 52;
        playerCards = 0;
        dealerCards = 0;
        dealHit = 0;
        hit = 0;
        dealAces = false;
        playAces = false;
        playStood = false;
        rand1 = r.nextInt(52);
        rand2 = r.nextInt(52);
        shuffle(deck);
        shuffle(deck);
        for(int i = 0; i < 7; i++){
            dealerDealt[i] = 0;
            playerDealt[i] = 0;
            if(i >= 2) {
                iv = findViewById(playerSlots[i]);
                iv.setVisibility(View.INVISIBLE);
                iv = findViewById(dealerSlots[i]);
                iv.setVisibility(View.INVISIBLE);
            }
        }
        iv = findViewById(playerSlots[0]);
        iv.setImageResource(getResources().getIdentifier(String.valueOf(deck[deckSize - 1]), "drawable", getPackageName()));
        playerDealt[playerCards] = deck[deckSize - 1];
        playerCards++;
        deckSize--;
        winCheck(playerDealt, dealerDealt);
        iv = findViewById(playerSlots[1]);
        iv.setImageResource(getResources().getIdentifier(String.valueOf(deck[deckSize - 1]), "drawable", getPackageName()));
        playerDealt[playerCards] = deck[deckSize - 1];
        playerCards++;
        deckSize--;
        winCheck(playerDealt, dealerDealt);
        iv = findViewById(dealerSlots[0]);
        iv.setImageResource(getResources().getIdentifier(String.valueOf(deck[deckSize - 1]), "drawable", getPackageName()));
        dealerDealt[dealerCards] = deck[deckSize - 1];
        dealerCards++;
        deckSize--;
        winCheck(playerDealt, dealerDealt);
        iv = findViewById(dealerSlots[1]);
        iv.setImageResource(getResources().getIdentifier(String.valueOf(R.drawable.dp_back)
                , "drawable", getPackageName()));
        winCheck(playerDealt, dealerDealt);
    }

    public void showDeal(){
        Button b = findViewById(R.id.deal_button);
        b.setVisibility(View.VISIBLE);
        b.setEnabled(true);
    }

    public void hideDeal(){
        Button b = findViewById(R.id.deal_button);
        b.setVisibility(View.INVISIBLE);
        b.setEnabled(false);
    }
}
