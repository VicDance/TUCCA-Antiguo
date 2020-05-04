package com.proyecto.tucca;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

import static com.proyecto.tucca.LoginFragment.dataIn;

public class MeActivity extends AppCompatActivity {
    private TextView textViewUser;
    private TextView textViewBorn;
    private TextView textViewEmail;
    private TextView textViewPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        textViewUser = findViewById(R.id.text_view_user);
        textViewBorn= findViewById(R.id.text_view_born_date);
        textViewEmail = findViewById(R.id.text_view_email);
        textViewPhone = findViewById(R.id.text_view_phone);
        String datos = null;
        try {
            datos = dataIn.readUTF();
            String[] newDatos = datos.split(" ");
            String nombre = newDatos[0];
            String correo = newDatos[1];
            String fecha_nac = newDatos[2];
            String tfno = newDatos[3];
            textViewUser.setText(nombre);
            textViewBorn.setText(fecha_nac);
            textViewEmail.setText(correo);
            textViewPhone.setText(tfno);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
