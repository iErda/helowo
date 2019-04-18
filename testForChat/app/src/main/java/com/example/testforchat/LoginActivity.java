package com.example.testforchat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.*;

public class LoginActivity extends AppCompatActivity {
private EditText name,pass,repass,email;
private CheckBox check;
private Button login;
private RadioGroup gender;
private Button bt_nologin;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);
        name=(EditText)findViewById(R.id.name);
        pass=(EditText)findViewById(R.id.pass);
        email=(EditText)findViewById(R.id.email);
        repass=(EditText)findViewById(R.id.repass);
        gender=(RadioGroup) findViewById(R.id.gender);
        check=(CheckBox)findViewById(R.id.check);
        login=(Button)findViewById(R.id.login);
        bt_nologin=(Button)findViewById(R.id. bt_nologin);

        bt_nologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ///跳转处
                /*
                Intent intent=new Intent(LoginActivity.this,SignInActivity.class);
                startActivity(intent);
                LoginActivity.this.finish();
                 */
            }
        });

        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    pass.setSelection(pass.getText().length());
                }
                else
                {
                    pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    pass.setSelection(pass.getText().length());
                }
            }
        });

        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(name.getText().length()==0)
                {
                    Toast.makeText(LoginActivity.this,"Please input the username",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(pass.getText().length()==0)
                {
                    Toast.makeText(LoginActivity.this,"Please input the password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(repass.getText().length()==0)
                {
                    Toast.makeText(LoginActivity.this,"Please confirm the password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (gender.getCheckedRadioButtonId() !=R.id.male&&gender.getCheckedRadioButtonId() !=R.id.female )
                {
                    Toast.makeText(LoginActivity.this,"please chose your gender",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!pass.getText().toString().equals(repass.getText().toString()))
                {
                    Toast.makeText(LoginActivity.this,"two inputs are different",Toast.LENGTH_SHORT).show();
                    pass.setText("");
                    repass.setText("");
                    return;
                }

                Intent intent= new Intent(LoginActivity.this,ChatActivity.class);
                startActivity(intent);
                LoginActivity.this.finish();
            }
        });
    }

}
