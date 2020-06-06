package com.myapp.smirthi.count3games;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //o:jerry,1:tom,2:empty;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winningpos={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int activeplayer=0;
    boolean gameActive=true;
    boolean fullboard=false;

    public void dropin(View view) {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter]==2 && gameActive) {
            gameState[tappedCounter] = activeplayer;
            counter.setTranslationY(-1500);

            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.jerry);
                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.tom);
                activeplayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
            for (int[] winningpos : winningpos) {
                int check = 0;
                for (int k = 0; k < gameState.length; k++) {
                    if (gameState[k] != 2) {
                        check++;
                    }
                }
                if (check == 9) {
                    fullboard = true;
                }

                if (gameState[winningpos[0]] == gameState[winningpos[1]] && gameState[winningpos[1]] == gameState[winningpos[2]] && gameState[winningpos[0]] != 2){

                    gameActive =false;
                    String winner ;

                    if (activeplayer == 1) {
                        winner = "jerry has won";
                    } else {
                        winner = "tom has won";
                    }
                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winner);
                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                } else if (fullboard) {
                    String message = "Match draw";
                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                    winnerTextView.setText(message);
                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);

                }

                }
        }
    }
        public void playAgain(View view){

            Button playAgainButton = (Button)findViewById(R.id.playAgainButton);
            TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
            playAgainButton.setVisibility(View.INVISIBLE);
            winnerTextView.setVisibility(View.INVISIBLE);
           GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

            for(int i=0; i<gridLayout.getChildCount(); i++) {
                ImageView counter = (ImageView) gridLayout.getChildAt(i);
                counter.setImageDrawable(null);
            }
            for (int i=0; i<gameState.length; i++){
                gameState[i] = 2;
            }
            activeplayer= 0;
            gameActive = true;

        }



        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
