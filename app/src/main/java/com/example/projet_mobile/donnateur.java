package com.example.projet_mobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projet_mobile.userAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.projet_mobile.model.user;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class donnateur extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
private DrawerLayout drawerLayout;
private Toolbar toolbar;
private NavigationView navView;
private TextView nav_name ;
private TextView nav_telephone ;
private TextView nav_mail ;
private TextView nav_blood ;
private TextView nav_type;
private DatabaseReference user_ref;
private RecyclerView recycler ;
private ProgressBar progressBar;
private List<user> userList;
private userAdapter useradapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donnateur2);
drawerLayout=findViewById(R.id.drawer);
toolbar = findViewById(R.id.toolbar);

getSupportActionBar().setTitle("app navigation");
progressBar = findViewById(R.id.progress);
recycler=findViewById(R.id.recycle);
LinearLayoutManager l = new LinearLayoutManager(this);
l.setReverseLayout(true);
l.setStackFromEnd(true);
recycler.setLayoutManager(l);

navView=findViewById(R.id.navigation);
navView.setNavigationItemSelectedListener(this);
ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(donnateur.this,drawerLayout,toolbar,R.string.navigation_open,R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    nav_name=navView.getHeaderView(0).findViewById(R.id.nav_user_name);
        nav_telephone=navView.getHeaderView(0).findViewById(R.id.nav_user_telephone);
        nav_mail=navView.getHeaderView(0).findViewById(R.id.nav_user_mail);
        nav_type=navView.getHeaderView(0).findViewById(R.id.nav_user_type);
        nav_blood=navView.getHeaderView(0).findViewById(R.id.nav_user_blood);
        user_ref= FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
userList=new ArrayList<>();
useradapter= new userAdapter(userList,donnateur.this);
recycler.setAdapter(useradapter);

DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
// method pour recuperer tout les listes des utilisateurs et l'ajouter dans la "userlisr"
ref.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull  DataSnapshot snapshot) {
        DatabaseReference refer = FirebaseDatabase.getInstance().getReference().child("users");
        Query query  = refer.child("users").orderByChild("type");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                userList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    user us = snapshot.getValue(user.class);
                    userList.add(us);

                }
            progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
    }

    @Override
    public void onCancelled(@NonNull  DatabaseError error) {

    }
});

//recuperation des données de l'utilisateur courant dans la navigationView
        user_ref.addValueEventListener(new ValueEventListener() {
    @Override

    public void onDataChange(@NonNull  DataSnapshot snapshot) {
        if(snapshot.exists()){String name=snapshot.child("name").getValue().toString();
        nav_name.setText(name);

        String telephone=snapshot.child("tel").getValue().toString();
            nav_telephone.setText(telephone);

        String mail=snapshot.child("email").getValue().toString();
            nav_mail.setText(mail);

       String blood=snapshot.child("blood").getValue().toString();
            nav_blood.setText(blood);
            String type=snapshot.child("type").getValue().toString();
            nav_type.setText(type);
        }
    }

    @Override
    public void onCancelled(@NonNull  DatabaseError error) {

    }
});
    }





// action lors de la selection de l'item "profile"
    @Override
    public boolean onNavigationItemSelected(@NonNull  MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile:
                Intent intent = new Intent(donnateur.this , profile.class);
                startActivity(intent);

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }
}