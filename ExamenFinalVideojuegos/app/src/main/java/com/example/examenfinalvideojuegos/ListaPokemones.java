package com.example.examenfinalvideojuegos;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ListaPokemones extends RecyclerView.Adapter<ListaPokemones.ViewHolderPoke> {

    Context activity;
    List<ClasePoke> data;
    private View view;

    public ListaPokemones(Context activity, List<ClasePoke> data) {
        this.activity = activity;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolderPoke onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        view = inflater.inflate(R.layout.poke_list, parent, false);

        ViewHolderPoke viewHolderPoke = new ViewHolderPoke(view);

        return viewHolderPoke;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPoke holder, int position) {

        ImageView imagen = holder.itemView.findViewById(R.id.imagen);
        TextView nombre = holder.itemView.findViewById(R.id.nombre);
        TextView tipo = holder.itemView.findViewById(R.id.tipo);
        Button detalle = holder.itemView.findViewById(R.id.detalle);

        ClasePoke pokemones = data.get(position);

        String URLIMAGEN = "https://upn.lumenes.tk" + pokemones.getUrl_imagen();
        Picasso.get()
                .load(URLIMAGEN)
                .into(imagen);
        nombre.setText(pokemones.getNombre());
        tipo.setText(pokemones.getTipo());

        detalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity,DetallePokemon.class);
                intent.putExtra("pokemon",pokemones);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolderPoke extends RecyclerView.ViewHolder {
        public ViewHolderPoke(@NonNull View itemView) {
            super(itemView);
        }
    }
}
