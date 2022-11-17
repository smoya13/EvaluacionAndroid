package evaluacion.smoya.evaluacionandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditarActivity extends AppCompatActivity {

    // Variables
    private EditText editTextUsuario,editTextPass,editTextGenero,editTextId;
    private Button btnEditar,btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        editTextUsuario = findViewById(R.id.txtEditUsuario);
        editTextPass = findViewById(R.id.txtEditPass);
        editTextGenero = findViewById(R.id.txtEditGenero);
        editTextId = findViewById(R.id.txtEditId);
    }

    public void btnEditarClick(View view) {
    }

    public void btnListaClick(View view) {
    }

    public void eliminar()
    {
        try
        {
            int id = Integer.parseInt(editTextId.getText().toString());
            SQLiteDatabase db = openOrCreateDatabase("bdUsuario", Context.MODE_PRIVATE,null);


            String sql = "delete from usuario where id = ?";
            SQLiteStatement statement = db.compileStatement(sql);

            statement.bindDouble(0,id);
            statement.execute();
            Toast.makeText(this,"Datos eliminados de la base de datos.",Toast.LENGTH_LONG).show();

            editTextId.setText("");
            editTextGenero.setText("");
            editTextPass.setText("");
            editTextUsuario.requestFocus();

        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Error no se pudieron guardar los datos.",Toast.LENGTH_LONG).show();
        }
    }
    public void editar()
    {
        try
        {
            String nombre = editTextUsuario.getText().toString();
            String pass = editTextPass.getText().toString();
            String genero = editTextGenero.getText().toString();
            int id = Integer.parseInt(editTextId.getText().toString());

            SQLiteDatabase db = openOrCreateDatabase("bdUsuario",Context.MODE_PRIVATE,null);

            String sql = "update usuario set nombre = ?,apellido=?,edad=? where id= ?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,nombre);
            statement.bindString(2,pass);
            statement.bindString(3,genero);
            statement.bindDouble(4,id);
            statement.execute();
            Toast.makeText(this,"Datos actualizados satisfactoriamente en la base de datos.",Toast.LENGTH_LONG).show();

            editTextId.setText("");
            editTextGenero.setText("");
            editTextPass.setText("");
            editTextUsuario.requestFocus();
        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Error no se pudieron guardar los datos.",Toast.LENGTH_LONG).show();
        }
    }
}