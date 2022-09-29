package evaluacion.smoya.evaluacionandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class TutorialActivity extends AppCompatActivity {
    Button btnOk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        btnOk = findViewById(R.id.btnEntendido);


    }

    public void onButtonClicked(View view) {
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
    }
}