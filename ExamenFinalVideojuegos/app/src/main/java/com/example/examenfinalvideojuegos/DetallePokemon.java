package com.example.examenfinalvideojuegos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetallePokemon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pokemon);

        ImageView Pokeimagen = findViewById(R.id.PokeImagen);
        TextView Pokenombre = findViewById(R.id.PokeNombre);
        TextView Poketipo = findViewById(R.id.PokeTypo);
        Button mapa = findViewById(R.id.PokeDetalle);

        ClasePoke poke = (ClasePoke) getIntent().getSerializableExtra("pokemon");

        String URLIMAGEN = "https://upn.lumenes.tk" + poke.getUrl_imagen();
        Picasso.get()
                .load(URLIMAGEN)
                .into(Pokeimagen);
        Pokenombre.setText(poke.getNombre());
        Poketipo.setText(poke.getTipo());

        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DetallePokemon.this, MapsActivity.class);
                intent.putExtra("Latitud", poke.getLatitude());
                intent.putExtra("Longitud", poke.getLongitude());
                startActivity(intent);
            }
        });
    }
}