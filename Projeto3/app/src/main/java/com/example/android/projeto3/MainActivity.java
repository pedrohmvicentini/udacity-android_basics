package com.example.android.projeto3;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import static com.example.android.projeto3.R.id.ckbQuestionTwoChelsea;
import static com.example.android.projeto3.R.id.ckbQuestionTwoNapoli;
import static com.example.android.projeto3.R.id.ckbQuestionTwoReal;
import static com.example.android.projeto3.R.id.ckbQuestionTwoValencia;
import static com.example.android.projeto3.R.id.ckbQuestionFiveArsenal;
import static com.example.android.projeto3.R.id.ckbQuestionFiveBorussia;
import static com.example.android.projeto3.R.id.ckbQuestionFiveManCity;
import static com.example.android.projeto3.R.id.ckbQuestionFivePSG;
import static com.example.android.projeto3.R.id.ckbQuestionFiveRoma;
import static com.example.android.projeto3.R.id.rbQuestionOneA;
import static com.example.android.projeto3.R.id.rbQuestionOneB;
import static com.example.android.projeto3.R.id.rbQuestionOneC;
import static com.example.android.projeto3.R.id.rbQuestionOneD;
import static com.example.android.projeto3.R.id.rbQuestionThreeAjax;
import static com.example.android.projeto3.R.id.rbQuestionThreeJuventus;
import static com.example.android.projeto3.R.id.rbQuestionThreePalmeiras;
import static com.example.android.projeto3.R.id.rbQuestionSixRussia;
import static com.example.android.projeto3.R.id.rbQuestionSevenArgentina;
import static com.example.android.projeto3.R.id.txtQuestionFour;

public class MainActivity extends AppCompatActivity {

    private static double score = 0;
    private RadioButton rbQuestionOneA;
    private RadioButton rbQuestionOneB;
    private RadioButton rbQuestionOneC;
    private RadioButton rbQuestionOneD;
    private CheckBox ckbQuestionTwoReal;
    private CheckBox ckbQuestionTwoValencia;
    private CheckBox ckbQuestionTwoNapoli;
    private CheckBox ckbQuestionTwoChelsea;
    private RadioButton rbQuestionThreePalmeiras;
    private RadioButton rbQuestionThreeJuventus;
    private RadioButton rbQuestionThreeAjax;
    private EditText txtQuestionFour;
    private CheckBox ckbQuestionFiveBorussia;
    private CheckBox ckbQuestionFiveManCity;
    private CheckBox ckbQuestionFiveRoma;
    private CheckBox ckbQuestionFivePSG;
    private CheckBox ckbQuestionFiveArsenal;
    private RadioButton rbQuestionSixRussia;
    private RadioButton rbQuestionSixChina;
    private RadioButton rbQuestionSixBrasil;
    private RadioButton rbQuestionSixItalia;
    private RadioButton rbQuestionSevenArgentina;
    private RadioButton rbQuestionSevenFrança;
    private RadioButton rbQuestionSevenItalia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
    }

    private void bindViews() {
        rbQuestionOneA = (RadioButton) findViewById(R.id.rbQuestionOneA);
        rbQuestionOneB = (RadioButton) findViewById(R.id.rbQuestionOneB);
        rbQuestionOneC = (RadioButton) findViewById(R.id.rbQuestionOneC);
        rbQuestionOneD = (RadioButton) findViewById(R.id.rbQuestionOneD);
        ckbQuestionTwoReal = (CheckBox) findViewById(R.id.ckbQuestionTwoReal);
        ckbQuestionTwoValencia = (CheckBox) findViewById(R.id.ckbQuestionTwoValencia);
        ckbQuestionTwoNapoli = (CheckBox) findViewById(R.id.ckbQuestionTwoNapoli);
        ckbQuestionTwoChelsea = (CheckBox) findViewById(R.id.ckbQuestionTwoChelsea);
        rbQuestionThreePalmeiras = (RadioButton) findViewById(R.id.rbQuestionThreePalmeiras);
        rbQuestionThreeJuventus = (RadioButton) findViewById(R.id.rbQuestionThreeJuventus);
        rbQuestionThreeAjax = (RadioButton) findViewById(R.id.rbQuestionThreeAjax);
        txtQuestionFour = (EditText) findViewById(R.id.txtQuestionFour);
        ckbQuestionFiveBorussia = (CheckBox) findViewById(R.id.ckbQuestionFiveBorussia);
        ckbQuestionFiveManCity = (CheckBox) findViewById(R.id.ckbQuestionFiveManCity);
        ckbQuestionFiveRoma = (CheckBox) findViewById(R.id.ckbQuestionFiveRoma);
        ckbQuestionFivePSG = (CheckBox) findViewById(R.id.ckbQuestionFivePSG);
        ckbQuestionFiveArsenal = (CheckBox) findViewById(R.id.ckbQuestionFiveArsenal);
        rbQuestionSixRussia = (RadioButton) findViewById(R.id.rbQuestionSixRussia);
        rbQuestionSixChina = (RadioButton) findViewById(R.id.rbQuestionSixChina);
        rbQuestionSixBrasil = (RadioButton) findViewById(R.id.rbQuestionSixBrasil);
        rbQuestionSixItalia = (RadioButton) findViewById(R.id.rbQuestionSixItalia);
        rbQuestionSevenArgentina = (RadioButton) findViewById(R.id.rbQuestionSevenArgentina);
        rbQuestionSevenFrança = (RadioButton) findViewById(R.id.rbQuestionSevenFrança);
        rbQuestionSevenItalia = (RadioButton) findViewById(R.id.rbQuestionSevenItalia);
    }

    private void verifyQuestionOne() {
        if (rbQuestionOneC.isChecked()) {
            score++;
        }
    }

    private void verifyQuestionTwo() {
        if (!ckbQuestionTwoChelsea.isChecked() && !ckbQuestionTwoNapoli.isChecked()) {
            if (ckbQuestionTwoReal.isChecked()) {
                score += 0.5;
            }
            if (ckbQuestionTwoValencia.isChecked()) {
                score += 0.5;
            }
        }
    }

    private void verifyQuestionThree() {
        if (rbQuestionThreePalmeiras.isChecked()) {
            score++;
        }
    }

    private void verifyQuestionFour() {
        if (txtQuestionFour.getText().equals("FIFA")) {
            score++;
        }
    }

    private void verifyQuestionFive() {
        if (!ckbQuestionFiveBorussia.isChecked()) {
            if (ckbQuestionFiveManCity.isChecked()) {
                score += 0.25;
            }
            if (ckbQuestionFiveRoma.isChecked()) {
                score += 0.25;
            }
            if (ckbQuestionFivePSG.isChecked()) {
                score += 0.25;
            }
            if (ckbQuestionFiveArsenal.isChecked()) {
                score += 0.25;
            }
        }
    }

    private void verifyQuestionSix() {
        if (rbQuestionSixRussia.isChecked()) {
            score++;
        }
    }

    private void verifyQuestionSeven() {
        if (rbQuestionSevenArgentina.isChecked()) {
            score++;
        }
    }

    private void Clear() {
        rbQuestionOneA.setChecked(false);
        rbQuestionOneB.setChecked(false);
        rbQuestionOneC.setChecked(false);
        rbQuestionOneD.setChecked(false);
        ckbQuestionTwoReal.setChecked(false);
        ckbQuestionTwoValencia.setChecked(false);
        ckbQuestionTwoNapoli.setChecked(false);
        ckbQuestionTwoChelsea.setChecked(false);
        rbQuestionThreePalmeiras.setChecked(false);
        rbQuestionThreeJuventus.setChecked(false);
        rbQuestionThreeAjax.setChecked(false);
        txtQuestionFour.setText("");
        ckbQuestionFiveBorussia.setChecked(false);
        ckbQuestionFiveManCity.setChecked(false);
        ckbQuestionFiveRoma.setChecked(false);
        ckbQuestionFivePSG.setChecked(false);
        ckbQuestionFiveArsenal.setChecked(false);
        rbQuestionSixRussia.setChecked(false);
        rbQuestionSixChina.setChecked(false);
        rbQuestionSixBrasil.setChecked(false);
        rbQuestionSixItalia.setChecked(false);
        rbQuestionSevenArgentina.setChecked(false);
        rbQuestionSevenFrança.setChecked(false);
        rbQuestionSevenItalia.setChecked(false);

        score = 0;
    }

    public void submitQuiz(View view) {
        verifyQuestionOne();
        verifyQuestionTwo();
        verifyQuestionThree();
        verifyQuestionFour();
        verifyQuestionFive();
        verifyQuestionSix();
        verifyQuestionSeven();

        DisplayMsg();

        Clear();
    }

    private void DisplayMsg() {
        Context context = getApplicationContext();
        CharSequence text = "Sua pontuação foi: " + score + "!";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }
}
