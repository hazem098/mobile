package com.example.projet_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SelectRegistration extends AppCompatActivity {
private Button donnateur ;
private Button recepient;
    private TextView login ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_registration);
        donnateur=findViewById(R.id.donation);
         recepient=findViewById(R.id.recipient);
         login=findViewById(R.id.login);
        //action pour le bouton receptient
        // perment de registrer en tant que recepient

        recepient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(SelectRegistration.this,recepientActivity.class);
                startActivity(intent);
            }
        });
        //action pour le bouton donnateur
         // perment de registrer en tant que donnateur
        donnateur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(SelectRegistration.this,donnateurActivity.class);
                startActivity(intent);
            }
        });
        //action le text view login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectRegistration.this , LoginActivity.class);
                startActivity(intent);
            }
        });
        }
    }
