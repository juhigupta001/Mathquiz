package com.example.hrsh.mathquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class cheetActivity extends AppCompatActivity {
    private final String TAG="cheetActivity";
    private static final String answeristrue="in.ac.jiit.juhi.mathquiz.answeristrue";//unique keyvalue
    private static final String cheated="cheated";
    private  static boolean ischeated=false;
    private TextView mcheatanswertextview;
    private Button mshowcheatbutton;
public static Intent newIntent(Context context,boolean b){
    Intent intent=new Intent(context,cheetActivity.class);
    intent.putExtra(answeristrue,b);
    return intent;
}
public static boolean wascheatshown(Intent i) {
    return i.getBooleanExtra(cheated,false);
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheet);
        Log.d(TAG,"inside oncreate");
       ischeated= getIntent().getBooleanExtra(answeristrue,false);//get the correct answer which is stored in answeristrue
        mcheatanswertextview=(TextView)findViewById(R.id.cheatanswertextView);
        mshowcheatbutton=(Button)findViewById(R.id.showcheatbutton);
        mshowcheatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ischeated){
                    mcheatanswertextview.setText(R.string.truebutton);
                }
                else{
                    mcheatanswertextview.setText(R.string.falsebutton);
                }
            }

        });
        setanswerresult(true);


        //Log.d(TAG,"received value: "+i);
       // if(i>=0)
        //{
         // ischeated=true;
        //}


    }
    private void setanswerresult(boolean b)
    {
        Intent i=new Intent();
        i.putExtra(cheated,b);//key value pair cheated=true in thiscase when we click showcheatbutton
        setResult(RESULT_OK,i);
    }

    protected void onPause() {
        super.onPause();
        Log.d(TAG, "inside onpause");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "inside ondestroy");
    }



};
