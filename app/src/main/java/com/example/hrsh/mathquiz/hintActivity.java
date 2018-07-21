package com.example.hrsh.mathquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.content.Context;

public class hintActivity extends AppCompatActivity {
    private TextView mhintanswertextview;
    private Button mshowhintbutton;


    private static final String answeristrue="in.ac.jiit.juhi.mathquiz.answeristrue";//unique keyvalue

    public static Intent newIntent(Context context,String k){
        Intent intent=new Intent(context,hintActivity.class);
        intent.putExtra(answeristrue, k);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);
        mhintanswertextview=(TextView)findViewById(R.id.hinttextview);
        mshowhintbutton=(Button)findViewById(R.id.showhintbutton);
        final WebView webview=(WebView) findViewById(R.id.webview);
        mshowhintbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    webview.setWebViewClient(new WebViewClient());
                    webview.loadUrl("http://www.wikipedia.com");
            }
        });


    }
}
