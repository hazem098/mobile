package com.example.projet_mobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.projet_mobile.model.user;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class userAdapter extends
        RecyclerView.Adapter<userAdapter.userHolder> {

    //Préparer la structure qui contiendra les éléments de la liste
    private List<user> listUsers;
private Context context ;

    userAdapter(List<user> listUsers, Context context) {
        this.listUsers = listUsers;
        this.context = context;
    }


// creation la classe holder

    class userHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        public TextView name , tel , type , mail , adresse,blood;
        final userAdapter mAdapter;
    public Button b ;

        public userHolder(View itemView, userAdapter adapter) {
            super(itemView);
            name = itemView.findViewById(R.id.user_name);
            blood = itemView.findViewById(R.id.user_blood);
            type = itemView.findViewById(R.id.user_type);
            tel = itemView.findViewById(R.id.user_telephone);
            adresse=itemView.findViewById(R.id.user_adr);
            mail=itemView.findViewById(R.id.user_mail);
           b=itemView.findViewById(R.id.emailNow);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {

        }
    }


    //Appelée au moment de la création du ViewHolder qui affichera
    //les éléments chargés à partir de l'Adapter
    @Override
    public userAdapter.userHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflater un view avec le layout déjà créé
        View view = LayoutInflater.from(context).inflate(
                R.layout.userdisplay, parent, false);
        return new userHolder(view, this);
    }


    //Elle est appelé à chaque fois qu'une vue doit être chargé.
    //On récupére alors la position du nouvel élément à afficher
    //et on le charge dans le TextView
    @Override
    public void onBindViewHolder(userAdapter.userHolder holder,
                                 int position) {
        // Récupérer l'élément qui doit etre affiché et chargé dans le ViewHolder
        final user mCurrent = listUsers.get(position);
        // Ajouter l'élément au ViewHolder
        holder.type.setText(mCurrent.getType());
        holder.name.setText(mCurrent.getName());
        holder.blood.setText(mCurrent.getBlood());
        holder.mail.setText(mCurrent.getEmail());
        holder.tel.setText(mCurrent.getTel());
        holder.adresse.setText(mCurrent.getAdr());
    }

    //Retourne le nombre d'éléments de notre liste
    @Override
    public int getItemCount() {
        return listUsers.size();

    }}
