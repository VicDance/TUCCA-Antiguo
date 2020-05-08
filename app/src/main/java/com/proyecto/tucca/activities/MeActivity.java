package com.proyecto.tucca.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.proyecto.tucca.R;
import com.proyecto.tucca.fragments.LoginFragment;

import java.io.IOException;

import static com.proyecto.tucca.activities.MainActivity.dataIn;
import static com.proyecto.tucca.activities.MainActivity.dataOut;
import static com.proyecto.tucca.fragments.LoginFragment.login;

public class MeActivity extends AppCompatActivity {
    private TextView textViewUser;
    private TextView textViewBorn;
    private TextView textViewEmail;
    private TextView textViewPhone;
    private Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        btnExit = findViewById(R.id.btn_exit);
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

            btnExit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    login = false;
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LoginFragment()).commit();
                    /*try {
                        dataOut.writeUTF("exit");
                        dataOut.flush();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
