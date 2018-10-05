package com.example.android.projeto2;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import static com.example.android.projeto2.R.id.btnSubstitutionTeamA;
import static com.example.android.projeto2.R.id.btnSubstitutionTeamB;
import static com.example.android.projeto2.R.id.btnTime;
import static com.example.android.projeto2.R.id.simpleChronometer;

public class MainActivity extends AppCompatActivity {

    int scoreTeamA = 0;
    int scoreTeamB = 0;
    String goalsTeamA = "";
    String goalsTeamB = "";
    int substitutionTeamA = 0;
    int substitutionTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void addOneForTeamA(View v) {
        scoreTeamA += 1;
        Chronometer pChronometer = (Chronometer) findViewById(simpleChronometer);
        goalsTeamA += pChronometer.getText().toString() + " - Goal \n";
        displayForTeamA(scoreTeamA);
    }

    public void substitutionForTeamA(View v) {
        substitutionTeamA += 1;
        if (substitutionTeamA == 3) {
            Button pSubstitution = (Button) findViewById(btnSubstitutionTeamA);
            pSubstitution.setEnabled(false);
        }
        Chronometer pChronometer = (Chronometer) findViewById(simpleChronometer);
        goalsTeamA += pChronometer.getText().toString() + " - Substitution \n";
        displayForTeamA(scoreTeamA);
    }

    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
        TextView goals = (TextView) findViewById(R.id.team_a_goals);
        goals.setText(goalsTeamA);
    }

    public void addOneForTeamB(View v) {
        scoreTeamB += 1;
        Chronometer pChronometer = (Chronometer) findViewById(simpleChronometer);
        goalsTeamB += pChronometer.getText().toString() + " - Goal \n";
        displayForTeamB(scoreTeamB);
    }

    public void substitutionForTeamB(View v) {
        substitutionTeamB += 1;
        if (substitutionTeamB == 3) {
            Button pSubstitution = (Button) findViewById(btnSubstitutionTeamB);
            pSubstitution.setEnabled(false);
        }
        Chronometer pChronometer = (Chronometer) findViewById(simpleChronometer);
        goalsTeamB += pChronometer.getText().toString() + " - Substitution \n";
        displayForTeamB(scoreTeamB);
    }


    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
        TextView goals = (TextView) findViewById(R.id.team_b_goals);
        goals.setText(goalsTeamB);
    }

    public void resetScore(View v) {
        scoreTeamA = 0;
        scoreTeamB = 0;
        goalsTeamA = "";
        goalsTeamB = "";
        substitutionTeamA = 0;
        substitutionTeamB = 0;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
        Chronometer pChronometer = (Chronometer) findViewById(simpleChronometer);
        Button pTime = (Button) findViewById(btnTime);
        pChronometer.stop();
        pChronometer.setBase(SystemClock.elapsedRealtime());
        pTime.setText("START TIME");
        Button pSubstitutionA = (Button) findViewById(btnSubstitutionTeamA);
        pSubstitutionA.setEnabled(true);
        Button pSubstitutionB = (Button) findViewById(btnSubstitutionTeamB);
        pSubstitutionB.setEnabled(true);
    }

    public void startTime(View v) {
        Chronometer pChronometer = (Chronometer) findViewById(simpleChronometer);
        Button pTime = (Button) findViewById(btnTime);
        if (pTime.getText() == "START TIME") {
            pChronometer.start();
            pTime.setText("STOP TIME");
        } else {
            pChronometer.stop();
            pTime.setText("START TIME");
        }
    }
}