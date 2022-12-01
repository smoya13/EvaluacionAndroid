package evaluacion.smoya.evaluacionandroid;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.content.Context;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    // Creo la lista
    ArrayList<ServicioVo> listaServicios;
    RecyclerView recyclerServicios;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

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
        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("first", "Ada");
        user.put("last", "Lovelace");
        user.put("born", 1815);

// Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    private void llenarPersonajes() {
        listaServicios.add(new ServicioVo("👪Servicio Followers Uno","💸$-", R.drawable.followers, 2));
        listaServicios.add(new ServicioVo("👪Servicio Followers Dos","💸$-", R.drawable.followers, 2));
        listaServicios.add(new ServicioVo("💕Servicio Likes Uno","💸$-", R.drawable.likes, 4));
        listaServicios.add(new ServicioVo("💕Servicio Likes Dos","💸$-", R.drawable.likes, 5));
        listaServicios.add(new ServicioVo("👀Servicio Visitas Uno","💸$-", R.drawable.views, 2));
        listaServicios.add(new ServicioVo("👀Servicio Visitas Dos","💸$-", R.drawable.views, 1));


    }
}