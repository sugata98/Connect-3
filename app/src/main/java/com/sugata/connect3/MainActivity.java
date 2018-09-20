package com.sugata.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //"1"->yellow; "0"->red;"2"->draw;
    //EditText player;
    //LinearLayout playerLayout;
    TextView playerOne;
    TextView playerTwo;
    TextView currPlayer;
    Button reset;
    int count1=0;
    int count2=0;
    int currentPlayer = 1;
    boolean gameIsActive =true;
    String winner;
    int[] gameState ={2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropIn(View view){

        ImageView counter = (ImageView) view;
        int tappedCounter=Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter]==2 && gameIsActive) {
            gameState[tappedCounter] = currentPlayer;
            counter.setTranslationY(-1000f);
                if (currentPlayer == 1) {
                    counter.setImageResource(R.drawable.yellow);
                    currPlayer.setText("Red, it's your turn!");
                    currentPlayer = 0;
                } else {
                    counter.setImageResource(R.drawable.red);
                    currPlayer.setText("Yellow, it's your turn!");
                    currentPlayer = 1;
                }
            counter.animate().translationYBy(1000f).setDuration(300);
            for (int i = 0; i < 8; i++) {
                if (gameState[winningPositions[i][0]] == gameState[winningPositions[i][1]]
                        && gameState[winningPositions[i][1]] == gameState[winningPositions[i][2]]
                        && gameState[winningPositions[i][0]] != 2)
                {
                    gameIsActive=false;
                        winner = "Red";//for Red,0
                        count1++;

                        if (gameState[winningPositions[i][0]] == 1) {
                            winner = "Yellow";//for Yellow,1
                            count2++;
                            count1--;
                        }
                    TextView winnerMessage = (TextView) findViewById(R.id.winner);
                    winnerMessage.setText(winner + " has Won!");
                    LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                    reset.setVisibility(View.INVISIBLE);
                    currPlayer.setVisibility(View.INVISIBLE);
                    layout.setVisibility(View.VISIBLE);

                }
                else{
                    boolean gameIsOver = true;
                    for(int counterState : gameState){
                        if(counterState==2)gameIsOver=false;
                    }
                    if(gameIsOver){
                        TextView winnerMessage = (TextView) findViewById(R.id.winner);
                        winnerMessage.setText("It's a Draw!");
                        currentPlayer=2;
                        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                        reset.setVisibility(View.INVISIBLE);
                        currPlayer.setVisibility(View.INVISIBLE);
                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
        playerOne.setText(Integer.toString(count1));
        playerTwo.setText(Integer.toString(count2));

    }
    public void resetGame(View view){
        gameIsActive=true;
        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        GridLayout gridlayout = (GridLayout)findViewById(R.id.gridLayout);

        for(int i=0;i<gridlayout.getChildCount();i++){

            ((ImageView)gridlayout.getChildAt(i)).setImageResource(0);
        }
    }
    public void playAgain(View view){
        gameIsActive=true;
        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        reset.setVisibility(View.VISIBLE);
        currPlayer.setVisibility(View.VISIBLE);
        if(currentPlayer!=2) {
            if (winner.equals("Red")) {
                currentPlayer = 0;
                currPlayer.setText("Red, Start the Game!");
            } else {
                currentPlayer = 1;
                currPlayer.setText("Yellow, Start the Game!");
            }
        }
        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        GridLayout gridlayout = (GridLayout)findViewById(R.id.gridLayout);

        for(int i=0;i<gridlayout.getChildCount();i++){

            ((ImageView)gridlayout.getChildAt(i)).setImageResource(0);
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerOne = (TextView)findViewById(R.id.p1Score);
        playerTwo = (TextView)findViewById(R.id.p2Score);
        reset = (Button)findViewById(R.id.Reset);
        currPlayer = (TextView)findViewById(R.id.currentPlayer);
        currPlayer.setText("Yellow, Start the Game!");
    }
}
