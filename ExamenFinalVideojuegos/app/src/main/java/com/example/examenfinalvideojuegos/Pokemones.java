package com.example.examenfinalvideojuegos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class Pokemones extends AppCompatActivity {

    String url = "https://upn.lumenes.tk/pokemons/N00032894";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemones);

        RequestQueue queue = Volley.newRequestQueue(Pokemones.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response != null) {

                    Gson gson = new Gson();
                    List<ClasePoke> list = Arrays.asList(gson.fromJson(response, ClasePoke[].class));
                    Adapter(list);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }

    private void Adapter(List<ClasePoke> classes) {
        RecyclerView recyclerView = findViewById(R.id.pokemones);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        ListaPokemones adapterList = new ListaPokemones(Pokemones.this, classes);
        recyclerView.setAdapter(adapterList);
    }
}