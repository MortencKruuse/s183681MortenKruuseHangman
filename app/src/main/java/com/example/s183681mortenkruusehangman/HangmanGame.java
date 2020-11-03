package com.example.s183681mortenkruusehangman;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HangmanGame extends AppCompatActivity implements OnClickListener {
    Galgelogik galgelogik = Galgelogik.getInstance();
    TextView visibleWord;
    ImageView hangmanImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangmangame);
        visibleWord = findViewById(R.id.visibleWord);
        hangmanImage = findViewById(R.id.gamehangmanmainimage);
        initializeButtons();
        galgelogik.startNytSpil();
        visibleWord.setText(galgelogik.getSynligtOrd());
    }

    public void initializeButtons(){
        Button[] buttons = new Button[26];
        buttons[0] = findViewById(R.id.abutton);
        buttons[1] = findViewById(R.id.bbutton);
        buttons[2] = findViewById(R.id.cbutton);
        buttons[3] = findViewById(R.id.dbutton);
        buttons[4] = findViewById(R.id.ebutton);
        buttons[5] = findViewById(R.id.fbutton);
        buttons[6] = findViewById(R.id.gbutton);
        buttons[7] = findViewById(R.id.hbutton);
        buttons[8] = findViewById(R.id.ibutton);
        buttons[9] = findViewById(R.id.jbutton);
        buttons[10] = findViewById(R.id.kbutton);
        buttons[11] = findViewById(R.id.lbutton);
        buttons[12] = findViewById(R.id.mbutton);
        buttons[13] = findViewById(R.id.nbutton);
        buttons[14] = findViewById(R.id.obutton);
        buttons[15] = findViewById(R.id.pbutton);
        buttons[16] = findViewById(R.id.qbutton);
        buttons[17] = findViewById(R.id.rbutton);
        buttons[18] = findViewById(R.id.sbutton);
        buttons[19] = findViewById(R.id.tbutton);
        buttons[20] = findViewById(R.id.ubutton);
        buttons[21] = findViewById(R.id.vbutton);
        buttons[22] = findViewById(R.id.wbutton);
        buttons[23] = findViewById(R.id.xbutton);
        buttons[24] = findViewById(R.id.ybutton);
        buttons[25] = findViewById(R.id.zbutton);

        for (Button button: buttons) {
            button.setOnClickListener(this);
        }
    }


    @Override
    public void onClick(View view) {
        if (view instanceof Button){
            visibleWord.setText(galgelogik.getSynligtOrd());
            ((Button) view).setBackgroundColor(getResources().getColor(R.color.clicked));
            ((Button) view).setClickable(false);
            galgelogik.gætBogstav((String) ((Button) view).getText());
            if (galgelogik.erSidsteBogstavKorrekt()){
                visibleWord.setText(galgelogik.getSynligtOrd());
                if (galgelogik.erSpilletVundet()){
                    startActivity(new Intent(HangmanGame.this,Won.class));
                }
            }
            else{
                switch(galgelogik.getAntalForkerteBogstaver()) {
                    case 1:
                        hangmanImage.setImageResource(R.drawable.forkert1);
                        break;
                    case 2:
                        hangmanImage.setImageResource(R.drawable.forkert2);
                        break;
                    case 3:
                        hangmanImage.setImageResource(R.drawable.forkert3);
                        break;
                    case 4:
                        hangmanImage.setImageResource(R.drawable.forkert4);
                        break;

                    case 5:
                        hangmanImage.setImageResource(R.drawable.forkert5);
                        break;

                    case 6:
                        hangmanImage.setImageResource(R.drawable.forkert6);
                        break;
                    default:
                        startActivity(new Intent(HangmanGame.this,Lost.class));
                }
            }
        }
    }
}



