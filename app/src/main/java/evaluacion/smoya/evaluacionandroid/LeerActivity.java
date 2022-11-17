package evaluacion.smoya.evaluacionandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class LeerActivity extends AppCompatActivity {
    private ListView lst1;
    private ArrayList<String> arreglo = new ArrayList<String>();
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leer);

        try{
            SQLiteDatabase db = openOrCreateDatabase("bdUsuario", Context.MODE_PRIVATE,null);
            lst1 = findViewById(R.id.lst1);
            final Cursor c = db.rawQuery("select * from usuario",null);
            int id = c.getColumnIndex("uid");
            int user = c.getColumnIndex("user");
            int pass = c.getColumnIndex("pass");
            int genero = c.getColumnIndex("genero");
            arreglo.clear();

            arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,arreglo);

            lst1.setAdapter(arrayAdapter);

            final  ArrayList<Usuario> lista = new ArrayList<Usuario>();


            if(c.moveToFirst())
            {
                do{
                    Usuario usuario = new Usuario();
                    usuario.uid = Integer.parseInt(c.getString(id));
                    usuario.user = c.getString(user);
                    usuario.pass = c.getString(pass);
                    usuario.genero = c.getString(genero);
                    lista.add(usuario);

                    arreglo.add(c.getString(id) + " \t " + c.getString(user) + " \t "  + c.getString(pass) + " \t "  + c.getString(genero) );

                } while(c.moveToNext());
                arrayAdapter.notifyDataSetChanged();
                lst1.invalidateViews();
            }

            lst1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, android.view.View view, int position, long l) {
                    Usuario usuario = lista.get(position);
                    Intent i = new Intent(getApplicationContext(), EditarActivity.class);
                    i.putExtra("id",usuario.uid);
                    i.putExtra("user",usuario.user);
                    i.putExtra("pass",usuario.pass);
                    i.putExtra("genero",usuario.genero);
                    startActivity(i);
                }
            });
        }
        catch (Exception e){
            Toast.makeText(this, "Ha ocurrido un error, intentalo nuevamente.", Toast.LENGTH_SHORT).show();
        }
    }
}