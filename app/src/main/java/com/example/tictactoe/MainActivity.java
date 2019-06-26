package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity" ;
    private TextView playerOne, playerTwo;
    private Button reset;
    private Button[][] pos = new Button[3][3];
    private int roundcount;
    private Boolean playerOneTurn = true;
    int playerOnePoints,playerTowPoints;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerOne = (TextView) findViewById(R.id.playerone);
        playerTwo = (TextView) findViewById(R.id.playertwo);
        reset = (Button) findViewById(R.id.reset);


        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++){
                String buttonId = "button_" + i+j;
//                Log.d("MainActivity/", "onCreate: "+buttonId);
                int resid = getResources().getIdentifier(buttonId,"id",getPackageName());
//                Log.d("MainAcitvity", "onCreate: "+resid);
                pos[i][j] = findViewById(resid);
                pos[i][j].setOnClickListener(this);
            }


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        if(onCheckwins()) {
            if (playerOneTurn) {
                Toast.makeText(this, "Player two wins", Toast.LENGTH_SHORT).show();
                playerOne.setText(++playerOnePoints);
            } else {
                Toast.makeText(this, "player one wins", Toast.LENGTH_SHORT).show();
                playerTwo.setText(++playerTowPoints);
            }
        }else if(roundcount==9)
            Toast.makeText(this, "Both sucks", Toast.LENGTH_SHORT).show();
   }

    @Override
    public void onClick(View view) {
        if(!((Button)view).getText().toString().equals("")){
            return;
        }

        Log.d(TAG, "onCreate: ------------------"+pos[0][0].getText().toString());


        if(playerOneTurn) {
            ((Button) view).setText("X");
            playerOneTurn = false;
        }else {
            ((Button) view).setText("O");
            playerOneTurn = true;
        }
        roundcount++;
    }

    private Boolean onCheckwins(){

        String[][] fields = new String[3][3];
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                fields[i][j] = pos[i][j].getText().toString();

        Log.d("MainActiviyt", "onCheckwins: "+fields[0][0]);

        for(int i=0;i<3;i++){
            if(fields[i][0].equals(fields[i][1])
                    && fields[i][0].equals(fields[i][2])
                    && !fields[i][0].equals("")){
                return true;}
        }

        for(int i=0;i<3;i++){
            if(fields[0][i].equals(fields[1][i])
                    && fields[0][i].equals(fields[2][i])
                    && !fields[i][0].equals("")){
                return true;}
        }

        if(fields[0][0].equals(fields[1][1])
                    && fields[0][0].equals(fields[2][2])
                    && !fields[0][0].equals("")
            ) {
            return true;}

        if(fields[0][2].equals(fields[1][1])
                && fields[0][2].equals(fields[2][0])
                && !fields[0][2].equals("")
        ) {
            return true;}

        return false;
    }
}
