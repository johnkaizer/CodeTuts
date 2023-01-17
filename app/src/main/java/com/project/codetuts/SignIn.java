package com.project.codetuts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity {
    TextView forgotPassword;
    private EditText EditTextEmail,editTextPassword;
    private FirebaseAuth mAuth;
    boolean passwordVisible;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);
        EditTextEmail = findViewById(R.id.editText2);
        editTextPassword = findViewById(R.id.editText3);
        forgotPassword = findViewById(R.id.textView20);

        editTextPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if (event.getAction()==MotionEvent.ACTION_UP){
                    if (event.getRawX()>=editTextPassword.getRight()-editTextPassword.getCompoundDrawables()[Right].getBounds().width()){
                        int selection=editTextPassword.getSelectionEnd();
                        if (passwordVisible){
                            //show password
                            editTextPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.visibility_off_24,0);
                            //hide password
                            editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible=false;

                        }else {
                            //show password
                            editTextPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.visibility_24,0);
                            //show password
                            editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible=true;

                        }
                        editTextPassword.setSelection(selection);
                        return  true;

                    }
                }
                return false;
            }
        });
    }

    public void sign_Up(View view) {
        startActivity(new Intent(SignIn.this, SignUp.class));
        finish();
    }

    public void login(View view) {
        String email =EditTextEmail.getText().toString().trim();
        String password =editTextPassword.getText().toString().trim();

        if (email.isEmpty()){
            EditTextEmail.setError(" email is required!!");
            EditTextEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            EditTextEmail.setError("Please provide a valid email address!");
            EditTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()){
            editTextPassword.setError(" password is required!!");
            editTextPassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (mAuth.getCurrentUser().isEmailVerified()){
                        startActivity(new Intent(SignIn.this, MainActivity.class));
                        Toast.makeText(SignIn.this,"Logged in successfully",Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(SignIn.this,"Please verify your email",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(SignIn.this,"Failed to log in check your credentials and Internet connection",Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(SignIn.this);
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_forgot,null);
                EditText emailBox = dialogView.findViewById(R.id.emailBox);


                builder.setView(dialogView);
                AlertDialog dialog = builder.create();

                dialogView.findViewById(R.id.btnReset).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String userEmail = emailBox.getText().toString();
                        if (TextUtils.isEmpty(userEmail) && !Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
                            Toast.makeText(SignIn.this,"Enter your registered email address",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(SignIn.this,"Check your email",Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }else {
                                    Toast.makeText(SignIn.this,"Unable to send, Failed",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                dialogView.findViewById(R.id.cancelButton).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                if (dialog.getWindow() != null){
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }
                dialog.show();
            }
        });
    }

}