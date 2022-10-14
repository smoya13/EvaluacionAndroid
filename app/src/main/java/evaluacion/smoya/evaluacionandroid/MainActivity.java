package evaluacion.smoya.evaluacionandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // Creo la lista
    ArrayList<ServicioVo> listaServicios;
    RecyclerView recyclerServicios;

    // Comienzo a
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaServicios = new ArrayList<>();
        recyclerServicios = (RecyclerView) findViewById(R.id.rvLista);
        recyclerServicios.setLayoutManager(new LinearLayoutManager(this));

        // Enviando al siguiente activity para el tutorial - REVISA SI LA APLICACIÓN ESTÁ ABIERTA POR PRIMERA VEZ
        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstRun", true);

        if (isFirstRun) {
            // Muestra el Activity de inicio.
            startActivity(new Intent(MainActivity.this, TutorialActivity.class));
        }
        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("isFirstRun", false).commit();

        // Comienzo a llenar el RecyclerView
        llenarPersonajes(); // Metodo para alimentar nuestra lista
        AdaptadorServicios adaptador = new AdaptadorServicios(listaServicios);
        recyclerServicios.setAdapter(adaptador);



    }

    private void llenarPersonajes() {
        listaServicios.add(new ServicioVo("👪500 Seguidores","💸$2.990", R.drawable.followers, 2));
        listaServicios.add(new ServicioVo("👪1000 Seguidores","💸$4.990", R.drawable.followers, 2));
        listaServicios.add(new ServicioVo("💕500 Likes","💸$1.990", R.drawable.likes, 4));
        listaServicios.add(new ServicioVo("💕1000 Likes","💸$2.990", R.drawable.likes, 5));
        listaServicios.add(new ServicioVo("👀1000 Visitas","💸$990", R.drawable.views, 2));
        listaServicios.add(new ServicioVo("👀2000 Visitas","💸$1.990", R.drawable.views, 1));


    }


    }
