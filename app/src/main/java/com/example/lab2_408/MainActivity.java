package com.example.lab2_408;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int playerScore = 0;
    private Weapon playerWeapon = null;
    private Weapon computerWeapon = null;
    private int computerScore = 0;

    public enum Weapon {

        ROCK("Rock"),
        PAPER("Paper"),
        SCISSORS("Scissors");
        private String message;

        private Weapon(String msg) { message = msg; }
        private static final Weapon[] allValues = values();
        private static final int weaponEnumLength = allValues.length;
        private static final Random random = new Random();

        public static Weapon getRandomWeapon() {
            return allValues[random.nextInt(weaponEnumLength)];
        }
        @Override
        public String toString() { return message; }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }
    public void rockSelected(View v){
        playerWeapon = Weapon.ROCK;
        conflictResolution(playerWeapon);

    }
    public void scissorsSelected(View v){
        playerWeapon = Weapon.SCISSORS;
        conflictResolution(playerWeapon);

    }
    public void paperSelected(View v){
        playerWeapon = Weapon.PAPER;
        conflictResolution(playerWeapon);

    }
    public void conflictResolution(Weapon weapon){
        TextView playerText = (TextView) findViewById(R.id.playerWeaponText);
        playerText.setText("Player chose: "+ playerWeapon.toString() + ".");
        computerWeapon = Weapon.getRandomWeapon();
        TextView computerText = (TextView) findViewById(R.id.computerWeaponText);
        computerText.setText("Computer chose: "+ computerWeapon.toString() + ".");
        TextView scoreText = (TextView) findViewById(R.id.scoresText);
        TextView whoWonText = (TextView) findViewById(R.id.whoWonText);

        if (playerWeapon == Weapon.ROCK){
            if (computerWeapon == Weapon.SCISSORS){
                whoWonText.setText("Player Win, Rock Beats Scissors");
                playerScore++;
            }
            if (computerWeapon == Weapon.PAPER){
                whoWonText.setText("Computer Win, Paper Covers Rock");
                computerScore++;
            }

        }
        if (playerWeapon == Weapon.PAPER){
            if (computerWeapon == Weapon.ROCK){
                whoWonText.setText("Player Win, Paper Covers Rock");
                playerScore++;
            }
            if (computerWeapon == Weapon.SCISSORS){
                whoWonText.setText("Computer Win, Scissors Cuts Paper");
                computerScore++;
            }
        }
        if (playerWeapon == Weapon.SCISSORS) {
            if (computerWeapon == Weapon.PAPER){
                whoWonText.setText("Player Win, Scissors Cuts Paper");
                playerScore++;
            }
            if (computerWeapon == Weapon.ROCK){
                whoWonText.setText("Computer Win, Rock Beats Scissors");
                computerScore++;
            }
        }
        if (playerWeapon == computerWeapon){
            whoWonText.setText("It Is a Tie!");
        }
        scoreText.setText("Player: " + Integer.toString(playerScore) + ", Computer: " + Integer.toString(computerScore));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
