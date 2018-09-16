package com.selma.constructions.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.selma.constructions.R;

public class RegisterActivity extends AppCompatActivity{

    TextInputLayout emailTextInputLayout;
    TextInputLayout passwordTextInputLayout;
    EditText emailEditText;
    EditText passwordEditText;
    EditText repeatPasswordEditText;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle(R.string.register);

        emailTextInputLayout = findViewById(R.id.til_email);
        passwordTextInputLayout = findViewById(R.id.til_password);
        emailEditText = findViewById(R.id.et_email);
        passwordEditText = findViewById(R.id.et_password);
        repeatPasswordEditText = findViewById(R.id.et_repeat_password);
        registerButton = findViewById(R.id.btn_register);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRegisterClicked();
            }
        });
    }


    private void onRegisterClicked() {
        if (passwordEditText.getText().toString().equals(repeatPasswordEditText.getText().toString())) {
            passwordTextInputLayout.setErrorEnabled(false);
            if (isEmailValid()) {
                registerUser(emailEditText.getText().toString(), passwordEditText.getText().toString());
            } else {
                emailTextInputLayout.setError(getString(R.string.email_not_valid));
            }
        } else {
            passwordTextInputLayout.setError(getString(R.string.password_dont_match));
        }
    }

    private void registerUser(String userName, String password) {

    }


    private boolean isEmailValid() {
        return true;
    }

}
