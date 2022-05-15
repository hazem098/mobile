//registration des donnateur
package com.example.projet_mobile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class donnateurActivity extends AppCompatActivity {
    private TextView login ;

    private TextInputEditText nom;
    private TextInputEditText adresse;
    private TextInputEditText mail;
    private TextInputEditText telephone;
    private TextInputEditText password;
    private Spinner group;
    private Button register ;

    private FirebaseAuth auth;
    private DatabaseReference ref;

    //method pour le textView login
    // cet method permet d'envoyer l'utilisateur a la page de login
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donnateur);
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(donnateurActivity.this , LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
// recuperation des données des TextInputEditText
    nom=findViewById(R.id.nom);
    adresse=findViewById(R.id.adresse);
    telephone=findViewById(R.id.telephone);
    mail=findViewById(R.id.mail);
    password=findViewById(R.id.password);
    register=findViewById(R.id.registerButton);
    group=findViewById(R.id.group);
    auth=FirebaseAuth.getInstance();




 register.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         // recuperation des données dans des variable
         final String email= mail.getText().toString().trim();
         final String pass= password.getText().toString().trim();
         final String name = nom.getText().toString().trim();
         final String tel = telephone.getText().toString().trim();
         final String adr = adresse.getText().toString().trim();
         final String blood = group.getSelectedItem().toString();
         // des test sur les variables
         if (TextUtils.isEmpty(email)){
             mail.setError("email is required");
         }
         if (TextUtils.isEmpty(pass)){
             password.setError("password is required");
         }
         if (TextUtils.isEmpty(name)){
             nom.setError("name is required");
         }
         if (TextUtils.isEmpty(tel)){
             telephone.setError("telephone is required");
         }
         if (TextUtils.isEmpty(adr)){
             adresse.setError("adresse is required");
         }
         if (group.equals("select your blood group")){
             Toast.makeText(donnateurActivity.this,"select a blood group",Toast.LENGTH_SHORT).show();
         }
         else {
auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull  Task<AuthResult> task) {
        if(task.isSuccessful()) {
            /*recuperation des l'id d'utilisateur courant*/ String currentUserId = auth.getCurrentUser().getUid();
            /*creer une referance de data base(creation du table users)*/  ref= FirebaseDatabase.getInstance().getReference().child("users").child(currentUserId);
            HashMap user = new HashMap();
user.put("currentUserId ",currentUserId );
            user.put("name",name);
            user.put("email",email);
            user.put("pass",pass);
            user.put("tel",tel);
            user.put("blood", blood);
            user.put("adr",adr);
            user.put("type","donateur");
            ref.updateChildren(user);

        }
    }
});
             Intent intent = new Intent(donnateurActivity.this,LoginActivity.class);
             startActivity(intent);

         }
     }

 });
    }


}