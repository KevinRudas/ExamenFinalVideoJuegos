package com.example.examenfinalvideojuegos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class CapturarPokemon extends AppCompatActivity {

    private static final int PERMISSION = 7;
    TextView editTextTextPersonName, editTextTextPersonName2, editTextTextPersonName3, editTextTextPersonName4;
    ImageView imageView2;
    String LINK = "https://upn.lumenes.tk/pokemons/N00032894/crear";
    Button button3;
    String ImagenBaser64;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capturar_pokemon);

        if (ContextCompat.checkSelfPermission(
                getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE
        ) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    CapturarPokemon.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    PERMISSION
            );
        }

        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2);
        editTextTextPersonName3 = findViewById(R.id.editTextTextPersonName3);
        editTextTextPersonName4 = findViewById(R.id.editTextTextPersonName4);
        button3 = findViewById(R.id.button3);
        imageView2 = findViewById(R.id.imageView2);


        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/");
                startActivityForResult(Intent.createChooser(intent, "Galeria"),
                        PERMISSION);
            }
        });

        CaturarPokemon();
    }

    private void CaturarPokemon() {

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ClasePokeCapturar capturar = new ClasePokeCapturar();

                capturar.setNombre(editTextTextPersonName.getText().toString());
                capturar.setTipo(editTextTextPersonName2.getText().toString());

                Double Latitud = Double.valueOf(editTextTextPersonName3.getText().toString());
                Double Longitud = Double.valueOf(editTextTextPersonName4.getText().toString());

                capturar.setLatitude(Latitud);
                capturar.setLongitude(Longitud);
                capturar.set_imagen(ImagenBaser64);

                String jsonString = new Gson().toJson(capturar);
                RequestQueue queue = Volley.newRequestQueue(CapturarPokemon.this);
                Log.e("FOrmato", jsonString);
                try {

                    JSONObject objJSon = new JSONObject(jsonString);

                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, LINK, objJSon, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("Hay un ","ERROR");
                        }
                    });
                    queue.add(request);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(CapturarPokemon.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == PERMISSION) {
                Uri uri = data.getData();

                Glide.with(CapturarPokemon.this)
                        .load(uri)
                        .into(imageView2);
                try {

                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    byte[] subida = stream.toByteArray();
                    ImagenBaser64 = Base64.encodeToString(subida, Base64.DEFAULT);
                    Log.e("base 64", ImagenBaser64);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                throw new IllegalStateException("Unexpected value: " + requestCode);
            }
        }
    }
}