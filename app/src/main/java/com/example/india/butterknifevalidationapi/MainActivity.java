
package com.example.india.butterknifevalidationapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements Validator.ValidationListener {

    /**
     * ButterKnife Code
     **/
    @NotEmpty
    @BindView(R.id.name)
    EditText name;

    @Length(max = 10, min = 10, message = "10 number digit require")
    @BindView(R.id.phone)
    EditText phone;

    @Email
    @BindView(R.id.email)
    EditText email;

    @Password(message = "minimum 6 character require")
    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.login)
    Button login;
    /**
     * ButterKnife Code
     **/
    Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        validator = new Validator(this);
        validator.setValidationListener(this);


//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent a=new Intent(MainActivity.this,Main2Activity.class);
//                startActivity(a);
            }

            //onclick method using butterknife
  @OnClick(R.id.login)
    void login(){
      Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        validator.validate();

  }

    @Override
    public void onValidationSucceeded() {
        Intent a=new Intent(MainActivity.this,Main2Activity.class);
        startActivity(a);

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {


        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);


            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();

            }
        }
    }
}