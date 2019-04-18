package com.example.testforchat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class FirstPageActivity extends AppCompatActivity {
    private Button PhoneRegist;
    private Button VistorRegist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstpageactivity);
        PhoneRegist=(Button) findViewById(R.id.PhoneRegist);
        VistorRegist=(Button)findViewById(R.id.VictorRegist);

        PhoneRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(FirstPageActivity.this, LoginActivity.class);
                startActivity(intent);
                FirstPageActivity.this.finish();

            }
        });
        VistorRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstPageActivity.this, ChatActivity.class);
                startActivity(intent);
                FirstPageActivity.this.finish();
            }
        });

    }
}
