package com.example.hrsh.mathquiz;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ProviderInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import java.security.PrivateKey;

public class mathquizActivity extends AppCompatActivity {
    private static final String TAG = "mathquiz";// imp to use tag
    private Button mtruebutton;
    private Button mfalsebutton;
    private Button mnextbutton;
    private Button mcheatbutton;
    private Button mhintbutton;

    private static final String KEYINDEX = "index";
    private static final int REQUESTCODEHINT = 0;
    private static final int REQUESTCODECHEAT = 1;//reqest code from cheat activity
    private boolean mreceivedhint;
    private boolean mcheated;
    MediaPlayer pmusic;
    private TextView mquestiontextview;
    private questionbank[] mquestionbank = new questionbank[]{

            new questionbank(R.string.question1, true),
            new questionbank(R.string.question2, true),
            new questionbank(R.string.question3, false),
            new questionbank(R.string.question4, false)};

    private int currentindex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//whenever u override first call super class
        Log.d(TAG, "inside oncreate");
        setContentView(R.layout.activity_mathquiz);
        pmusic = MediaPlayer.create(this, R.raw.music);
        pmusic.start();
        Button play = (Button) findViewById(R.id.button);
        Button pause = (Button) findViewById(R.id.button2);
        Switch switch_looping = (Switch) findViewById(R.id.switch_looping);
        switch_looping.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                pmusic.setLooping(isChecked);

            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                pmusic.start();
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (pmusic.isPlaying()) {
                    pmusic.pause();
                }


            }
        });


        mquestiontextview = (TextView) findViewById(R.id.textview);
        if (savedInstanceState != null) {

            currentindex = savedInstanceState.getInt(KEYINDEX);//FOR SCREEN ROTATION
        }
        updatequestion();
        mtruebutton = (Button) findViewById(R.id.truebutton);
        mtruebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                checkanswer(true);
            }

        });
        mfalsebutton = (Button) findViewById(R.id.falsebutton);
        mfalsebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                checkanswer(false);
            }
        });
        mnextbutton = (Button) findViewById(R.id.nextbutton);
        mnextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updatequestion();
                mcheated = false;
            }
        });
        mcheatbutton = (Button) findViewById(R.id.cheatbutton);
        mcheatbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Log.d(TAG, "inside cheatbutton");
                // Intent i=new Intent(mathquizActivity.this,cheetActivity.class);
                boolean b = mquestionbank[currentindex].isTruequestion();
                Intent i = cheetActivity.newIntent(mathquizActivity.this, b);
                //startActivity(i);//to add new activit
                startActivityForResult(i, REQUESTCODECHEAT);
            }
        });
        mhintbutton = (Button) findViewById(R.id.hintbutton);//for hint button
        mhintbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String text = "see if it can be factorised";
                Intent l = hintActivity.newIntent(mathquizActivity.this, text);
                startActivityForResult(l, REQUESTCODEHINT);
            }
        });

    }
    private void updatequestion() {
        currentindex = (currentindex + 1) % mquestionbank.length;//concept of circular queue
        int question = mquestionbank[currentindex].getquestion();
        mquestiontextview.setText(question);

    }

    private void checkanswer(boolean userpressed)//to check the answer clicked with alreay present answer
    {
        boolean answeristrue = mquestionbank[currentindex].isTruequestion();//returns truequestion part of class think as of a structure
        int messageresid = 0;
        if (mcheated) {
            messageresid = R.string.cheattoast;
        } else {
            if (userpressed == answeristrue) {

                messageresid = R.string.correcttoast;

            } else {

                messageresid = R.string.incorrecttoast;
            }
        }
        Toast.makeText(mathquizActivity.this, messageresid, Toast.LENGTH_SHORT).show();
    }



    @Override
    protected void onActivityResult(int requestcode, int resultcode, Intent data)//for new activity
    {
        if (resultcode != Activity.RESULT_OK) {
            return;
        }
        if (requestcode == REQUESTCODECHEAT) {
            if (data == null) {
                return;
            }
            mcheated = cheetActivity.wascheatshown(data);//method is in cheetactivity
        }

    }


    @Override//for screen rotation
    public void onSaveInstanceState(Bundle savedInstanceState)//bundle saves the value as key value
    {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "inside onsaveinstance");//for debugging as in full lec
        savedInstanceState.putInt(KEYINDEX, currentindex);//creating key value
    }


    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG,"inside onstart");


    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "inside onpause");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "inside stop");
    }
        @Override
        protected void onResume() {
            super.onResume();
            Log.d(TAG, "inside onresume");
            Log.d(TAG,"did user cheat" +mcheated);
        }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "inside ondestroy");
    }
    public void OnDestroy()
    {
        pmusic.pause();
    }
    public void OnPause()
    {
        pmusic.pause();
    }

};

