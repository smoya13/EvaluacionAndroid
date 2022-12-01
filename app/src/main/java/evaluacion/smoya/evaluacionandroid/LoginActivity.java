package evaluacion.smoya.evaluacionandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import kotlinx.coroutines.Delay;

public class LoginActivity extends AppCompatActivity {
    // Variables de instancia
    Button btnLogin;
    TextView txtLogin;
    EditText txtUsuario;
    EditText txtPass;
    ProgressBar progressBar;
    Handler handler = new Handler();

    String user, pass;
    String userID;

    int progreso = 0;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.SplashTheme);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        // Asigno los valores a botones
        txtLogin = findViewById(R.id.textView);
        btnLogin = findViewById(R.id.btnLogin);
        txtUsuario = findViewById(R.id.txtUsuario);
        txtPass = findViewById(R.id.txtPass);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        Toast msjAdvertencia = Toast.makeText(LoginActivity.this, "⚠Recuerda que esta aplicación es meramente un trabajo de la universidad.", Toast.LENGTH_LONG);
        msjAdvertencia.show();


    }

    public void btnLoginClick(View view) {
        String user = txtUsuario.getText().toString();
        String pass = txtPass.getText().toString();

        if(user.isEmpty() && pass.isEmpty()){
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }else{
            loginUser();
        }
    }

    public void btnRegisterClick(View view) {
        Intent intent = new Intent (this, RegisterActivity.class);
        startActivity(intent);
    }


    public void btnTextoClick(View view) {
        Intent intent = new Intent(this, CrudActivity.class);
        startActivity(intent);
    }

    public void loginUser(){
       String mail = txtUsuario.getText().toString();
       String pass = txtPass.getText().toString();

       if(TextUtils.isEmpty(mail)){
           txtUsuario.setError("Ingrese un correo");
           txtUsuario.requestFocus();
       }else if(TextUtils.isEmpty(pass)){
           txtPass.setError("Ingrese una contraseña");
           txtPass.requestFocus();
       }else{
           mAuth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful()){
                       Toast.makeText(LoginActivity.this,"Bienvenid@",Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(LoginActivity.this, MainActivity.class));
                   }else{
                       Log.w("TAG", "Error:",task.getException());
                   }
               }
           });
       }
    }
}
