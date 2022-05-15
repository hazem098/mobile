// activité pour afficher les données des l'utilisateur courant
package com.example.projet_mobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile extends AppCompatActivity {
private Toolbar toolbar;
private TextView prof_type , t_blood , nom , email , prof_adresse , tel;
private Button back;
private DatabaseReference ref ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        toolbar = findViewById(R.id.prof_toolbar);
       getSupportActionBar().setTitle("profile");
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       getSupportActionBar().setDisplayShowHomeEnabled(true);


ref = FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
prof_type=findViewById(R.id.prof_type);
t_blood=findViewById(R.id.prof_blood);
        nom=findViewById(R.id.prof_name);
        email=findViewById(R.id.prof_mail);
        prof_adresse=findViewById(R.id.prof_adresse);
        tel=findViewById(R.id.prof_tel);
ref.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        if (snapshot.exists()) {
            String name = snapshot.child("name").getValue().toString();
            nom.setText(name);

            String telephone = snapshot.child("tel").getValue().toString();
            tel.setText(telephone);

            String mail = snapshot.child("email").getValue().toString();
            email.setText(mail);

            String blood = snapshot.child("blood").getValue().toString();
            t_blood.setText(blood);
            String type = snapshot.child("type").getValue().toString();
            prof_type.setText(type);
            String adresse = snapshot.child("adr").getValue().toString();
            prof_adresse.setText(adresse);
        }
    }
    @Override
    public void onCancelled(@NonNull  DatabaseError error) {

    }
});


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){case android.R.id.home:
        finish();
        return true;
            default:return super.onOptionsItemSelected(item);}

    }
}