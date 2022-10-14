package evaluacion.smoya.evaluacionandroid;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // Creo el arreglo para poner los generos
    String[] genero = {"Hombre", "Mujer", "Otro"};

    // Variables de instancia
    private EditText txtUsuario, txtPass;
    private CheckBox chkSuscripcion;
    private Button btnRegister;
    private ImageButton btnAyuda;
    private RadioButton rbUno, rbDos;
    NotificationManagerCompat notificationManagerCompat;
    Notification notificacion;
    private SensorManager sensorManager;
    private Sensor proximitySensor;
    private SensorEventListener proximitySensorListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        // Notificación Push
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("miCh", "Mi Channel", NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"miCh")
                .setSmallIcon(android.R.drawable.stat_notify_sync)
                .setContentTitle("Gracias por activar a las notificaciones")
                .setContentText("Ahora estarás al día con las novedades");

        notificacion = builder.build();

        notificationManagerCompat = NotificationManagerCompat.from(this);


        // Asignamos los valores ingresados a las variables.
        btnRegister = (Button) findViewById(R.id.btnRegistrarse);
        txtUsuario = (EditText) findViewById(R.id.txtUsuario);
        txtPass = (EditText) findViewById(R.id.txtPass);
        chkSuscripcion = (CheckBox) findViewById(R.id.chkSuscripcion);
        rbUno = (RadioButton) findViewById(R.id.rbOpcionUno);
        rbDos = (RadioButton) findViewById(R.id.rbOpcionDos);
        btnAyuda = (ImageButton) findViewById(R.id.btnHelp);

        // Obteniendo una instancia del Spinner y asignando a la variable
        Spinner spin = (Spinner) findViewById(R.id.spnGnero);
        spin.setOnItemSelectedListener(this);

        // Creando una instancia del ArrayAdapter obteniendo la lista de paises.
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, genero);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Setteando el ArrayAdapter al Spinner
        spin.setAdapter(aa);

        // Envío la notificación si se marca el checkbox
        chkSuscripcion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView,boolean a) {
               if(chkSuscripcion.isChecked()){
                   notificationManagerCompat.notify(1, notificacion);
               }
           }
        }
        );

        // Inicializamos el administrador del sensor
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Usamos el sensor de proximidad
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if(proximitySensor == null) {
            Log.e("TAG", "El sensor de proximidad no está disponible.");
            finish();
        }
        proximitySensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if(sensorEvent.values[0] >= proximitySensor.getMaximumRange()) {
                    String mensaje = "👁No te acerques tanto al telefono, te puede pasar algo";
                    Toast toast = Toast.makeText(RegisterActivity.this, mensaje, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.START, 90, 0);
                    toast.show();
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(proximitySensorListener, proximitySensor, 2 * 1000 * 1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(proximitySensorListener);
    }

    public void onButtonClicked(View view) {
        if(txtUsuario.getText().toString().equals("") | txtPass.getText().toString().equals("") | chkSuscripcion.isChecked() == false){
            String mensaje = "⚠Tienes campos vacios";
            Toast toast = Toast.makeText(this, mensaje, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.START, 90, 0);
            toast.show();
        }else{
            Intent intent = new Intent (this, LoginActivity.class);
            intent.putExtra("username", txtUsuario.getText().toString());
            intent.putExtra("pass", txtPass.getText().toString());
            startActivity(intent);
        }

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    // Método para revisar los radioButton
    public void onRadioButtonClicked(View view){

        // ¿Está marcado el radioButton?
        boolean marcado = ((RadioButton) view).isChecked();
        CompoundButton radioButtonUnoTexto = rbUno; // Estableciendo el CompoundButton para poder cambiar el texto
        CompoundButton radioButtonDosTexto = rbDos; // Estableciendo el CompoundButton para poder cambiar el texto

        // Revisando cual radioButton está marcado
        switch(view.getId()) {
            case R.id.rbOpcionUno:
                if (marcado == true)

                break;
            case R.id.rbOpcionDos:
                if (marcado == true)
                    abrirDialog();
                break;
        }
    }

    private void abrirDialog() {
        Dialog terminos = new Dialog();
        terminos.show(getSupportFragmentManager(), "terminos");
    }

    public void onHelpClicked(View view) {
        redactarMail(new String[]{"Ayuda@Enyerfame.cl", "Soporte@Enyerfame.cl"}, "Necesito ayuda con la app");
    }

    // Redactar mail
    public void redactarMail(String[] direcciones, String asunto) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // para que sólo las apps de mail puedan utilizarlo
        intent.putExtra(Intent.EXTRA_EMAIL, direcciones);
        intent.putExtra(Intent.EXTRA_SUBJECT, asunto);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}