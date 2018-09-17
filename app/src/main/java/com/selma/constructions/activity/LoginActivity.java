package com.selma.constructions.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.selma.constructions.GetDataAsObject;
import com.selma.constructions.R;
import com.selma.constructions.model.User;

import org.json.simple.JSONObject;

public class LoginActivity extends BaseActivityForObjects {

    public static final String CURRENT_USER = "current_user";
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button registerButton;
    private ProgressBar progressBar;
    private LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle(R.string.login);

        emailEditText = findViewById(R.id.et_email);
        passwordEditText = findViewById(R.id.et_password);
        loginButton = findViewById(R.id.btn_login);
        registerButton = findViewById(R.id.btn_register);
        progressBar = findViewById(R.id.activity_login_progress_bar);
        mainLayout = findViewById(R.id.activity_login_main_linear_layout);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateCredentials();
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }


    private void validateCredentials() {

        String email = emailEditText.getText().toString().toLowerCase().trim();
        String password = passwordEditText.getText().toString();

        if(isEmailValid(email, password)){

            progressBar.setVisibility(View.VISIBLE);
            mainLayout.setVisibility(View.GONE);

            String url = "https://www.google.com";  // TODO create the URL
            GetDataAsObject getData = new GetDataAsObject(this);
            getData.execute(url);

        }
    }

    private boolean isEmailValid(String email, String password) {

        boolean validation = true;

        if(!(Patterns.EMAIL_ADDRESS.matcher(new StringBuffer(email)).matches())){

            emailEditText.setError("Enter a valid email address");
            validation = false;

        }
        if(password.length() < 8) {

            passwordEditText.setError("Enter a valid password");
            validation = false;

        }

        return validation;
    }


    @Override
    public void getDataFromAsyncTask(JSONObject result) {

        if (result != null) {
            if(result.get("id") != null){

                User user = new User();
                user.setFirstName(result.get("Ime").toString());
                user.setLastName(result.get("Prezime").toString());
                user.setId((Long) result.get("Id"));
                user.setAddress(result.get("Adresa").toString());
                user.setPhone(result.get("KontaktTelefon").toString());

                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra(CURRENT_USER, user);
                startActivity(intent);

            } else
                Toast.makeText(this, "Wrong e-mail or password", Toast.LENGTH_SHORT).show();

        }else{

            progressBar.setVisibility(View.GONE);
            mainLayout.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Unexpected Error, Try again later", Toast.LENGTH_LONG).show();
        }

    }
}
