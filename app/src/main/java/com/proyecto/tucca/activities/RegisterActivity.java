package com.proyecto.tucca.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.proyecto.tucca.R;
import com.proyecto.tucca.fragments.DatePickerFragment;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.proyecto.tucca.fragments.MainFragment.dataOut;

public class RegisterActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private Button button;
    private EditText editTextUser;
    private EditText editTextPassword;
    private EditText editTextRepeat;
    private EditText editTextEmail;
    private EditText editTextPhone;
    private ImageButton datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        datePicker = findViewById(R.id.button_date_picker);
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "Date Picker");
            }
        });
        button = findViewById(R.id.btn_register);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (compruebaCampos() && compruebaTfno(String.valueOf(editTextPhone.getText())) &&
                        compruebaEmail(String.valueOf(editTextEmail.getText()))) {

                    try {
                        dataOut.writeUTF("encriptar");
                        dataOut.flush();
                        dataOut.writeUTF(String.valueOf(editTextPassword.getText()));
                        dataOut.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //encriptar = new Encriptar();
                    /*try {
                        String contraseña = new String(txtContraseña.getPassword());
                        contraseñaCifrada = encriptar.encriptar(contraseña, CLAVE);
                        java.sql.Date date = new java.sql.Date(dateChooserNacimiento.getDate().getTime());
                        //System.out.println(contraseña);
                        cdi.insertar(txtUsuario.getText(), contraseñaCifrada, txtCorreo.getText(), date, Integer.parseInt(txtTfno.getText()));
                    } catch (Exception ex) {
                        Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
                    }*/
                }else{
                    //JOptionPane.showMessageDialog(this, "Campos vacíos");
                }
            }
        });
    }

    public boolean compruebaCampos() {
        boolean llenos = false;
        editTextUser = findViewById(R.id.edit_text_register_user);
        editTextPassword = findViewById(R.id.edit_text_register_password);
        editTextRepeat = findViewById(R.id.edit_text_register_repeat_password);
        editTextEmail = findViewById(R.id.edit_text_register_email);
        editTextPhone = findViewById(R.id.edit_text_register_phone);
        if (editTextUser != null && editTextPassword != null && editTextRepeat != null
                && editTextEmail != null && editTextPhone != null && datePicker != null) {
            if(compruebaRepetirContraseña()){
                llenos = true;
            }
        }
        return llenos;
    }

    private boolean compruebaRepetirContraseña(){
        boolean correcta = false;
        String contraseña = String.valueOf(editTextPassword.getText());
        String contraseñaRepetida = String.valueOf(editTextRepeat.getText());

        if(contraseña.compareTo(contraseñaRepetida) == 0){
            correcta = true;
        }

        return correcta;
    }

    public boolean compruebaEmail(String email) {
        boolean valido = false;
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        String emailAValidar = email;
        Matcher mather = pattern.matcher(emailAValidar);
        if (mather.find() == true) {
            System.out.println("El email ingresado es válido.");
            valido = true;
        } else {
            System.out.println("El email ingresado es inválido.");
        }

        return valido;
    }

    public boolean compruebaTfno(String tfno) {
        boolean valido = false;
        try {
            if (tfno.length() > 9) {
                return valido;
            } else {
                Integer.parseInt(tfno);
                valido = true;
            }
        } catch (Exception e) {
            valido = false;
        }
        return valido;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        TextView textView = findViewById(R.id.text_view_register_date);
        textView.setText(currentDateString);
    }
}
