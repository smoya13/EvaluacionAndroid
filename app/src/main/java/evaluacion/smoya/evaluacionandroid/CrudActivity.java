package evaluacion.smoya.evaluacionandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CrudActivity extends AppCompatActivity {

    // Variables de elementos
    private EditText editTextUsuario, editTextPass;
    private Button btnAgregar, btnEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        // Envío el mensaje inicial para felicitar al usuario
        Toast toast = Toast.makeText(this, "🚀¡Felicidades por acceder al CRUD!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.START, 90, 0);
        toast.show();

        // Asigno las variables a los elementos
        editTextUsuario = findViewById(R.id.txtUsuarioCrud);
        editTextPass = findViewById(R.id.txtPassCrud);
        btnAgregar = findViewById(R.id.btnAgregar);
        btnEditar = findViewById(R.id.btnLista);
    }

    public void btnAgregarClick(View view) {
        insertar();
    }

    public void btnListaClick(View view) {
        Intent i = new Intent(getApplicationContext(), LeerActivity.class);
        startActivity(i);
    }

    public void insertar()
    {
        try
        {
            String usuario = editTextUsuario.getText().toString();
            String pass = editTextPass.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("bdUsuario", Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS usuario(uid INTEGER PRIMARY KEY AUTOINCREMENT,user VARCHAR,pass VARCHAR,genero VARCHAR)");

            String sql = "insert into usuario(user,pass,genero)values(?,?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,usuario);
            statement.bindString(2,pass);
            statement.execute();
            Toast.makeText(this,"Datos agregados satisfactoriamente en la base de datos.",Toast.LENGTH_LONG).show();

            editTextUsuario.setText("");
            editTextPass.setText("");
            editTextUsuario.requestFocus();
        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Error no se pudieron guardar los datos.",Toast.LENGTH_LONG).show();
        }
    }
}