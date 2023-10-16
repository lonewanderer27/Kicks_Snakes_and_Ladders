package com.kicks.snakesladders;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class KicksSnLMainActivity extends AppCompatActivity {
    ImageView dice;
    Button play, reset;
    int diceVal;
    int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kicks_snl_activity_main);

        dice = findViewById(R.id.dice);
        play = findViewById(R.id.playBtn);
        reset = findViewById(R.id.resetBtn);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Play", "Clicked");
                RollDice(v);
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Reset", "Clicked");
                Reset(v);
            }
        });
    }

    void Reset(View reset) {
        diceVal = 0;
        progress = 0;
        UpdateDice();
        UpdateBoxes();
    }

    void RollDice(View rollDice) {
        diceVal = (int) (Math.random() * 6) + 1;
        Log.i("Dice", "Rolled: " + diceVal);
        UpdateDice();

        progress += diceVal;

        if (progress > 60) {
            int excess = progress - 60;
            progress = 60 - excess;
        }

        if (progress == 60) {
            FinishAudio();
        } else {
            TransitionAudio();
        }

        UpdateBoxes();
    }

    void UpdateBoxes() {
        for (int i = 1; i < 61; i++) {
            int id = getResources().getIdentifier("box" + i, "id", getPackageName());
            TextView box = findViewById(id);

            if (i == progress) {
                box.setBackgroundResource(R.drawable.selected_border_color);
            } else {
                box.setBackgroundResource(R.drawable.border_color);
            }
        }
    }

    void UpdateDice() {
        switch (diceVal) {
            case 1:
                dice.setBackgroundResource(R.drawable.dice_k1);
                break;
            case 2:
                dice.setBackgroundResource(R.drawable.dice_k2);
                break;
            case 3:
                dice.setBackgroundResource(R.drawable.dice_k3);
                break;
            case 4:
                dice.setBackgroundResource(R.drawable.dice_k4);
                break;
            case 5:
                dice.setBackgroundResource(R.drawable.dice_k5);
                break;
            case 6:
                dice.setBackgroundResource(R.drawable.dice_k6);
                break;
        }
    }

    void TransitionAudio() {
        MediaPlayer.create(this, R.raw.transition).start();
    }

    void FinishAudio() {
        MediaPlayer.create(this, R.raw.finish).start();
    }
}