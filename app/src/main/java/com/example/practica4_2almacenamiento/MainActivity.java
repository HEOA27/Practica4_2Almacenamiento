package com.example.practica4_2almacenamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
EditText nombre, numero,correo;

    private static final String FILE_NAME="contacto.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre=findViewById(R.id.etNombre);
        numero=findViewById(R.id.etTelefono);
        correo=findViewById(R.id.etCorreo);

    }
    public void Guardar(View v){
    String nom=nombre.getText().toString()+"\n";
    String num=numero.getText().toString()+"\n";
    String email=correo.getText().toString()+"\n";


        FileOutputStream fos =null;
        try{
            fos= openFileOutput(FILE_NAME, MODE_PRIVATE);

            fos.write(nom.getBytes());
            fos.write(num.getBytes());
            fos.write(email.getBytes());

            nombre.getText().clear();
            numero.getText().clear();
            numero.setText("");
            correo.getText().clear();
            Toast.makeText(this, "Guardar"+getFilesDir() + "/"+FILE_NAME,Toast.LENGTH_LONG).show();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if(fos !=null){
                try {
                    fos.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }

    }
    public void Recuperar(View v){
        FileInputStream fis= null;
        try {
            fis=openFileInput(FILE_NAME);
            InputStreamReader isr=new InputStreamReader(fis);
            BufferedReader br=new BufferedReader(isr);
            StringBuilder sb= new StringBuilder();

            String text1,text2,text3;
            if((text1 =br.readLine()) !=null){

              /*  sb.append(text1).append("\n");
                sb2.append(text2).append("\n");
                sb3.append(text3).append("\n");*/
                nombre.setText(text1); }
                if((text2 =br.readLine()) !=null){
                numero.setText(text2);}
            if((text3 =br.readLine()) !=null){
                correo.setText(text3);}







        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(fis !=null){
                try{
                    fis.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }

    }
    /*
    public void Guardar(){
        SharedPreferences preferences=getSharedPreferences("contacto", Context.MODE_PRIVATE);
        String nom= nombre.getText().toString();
        String num=numero.getText().toString();
        String ema=correo.getText().toString();
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("name",nom);
        editor.putString("number", num);
        editor.putString("sms", ema);
        /*nombre.setText(nom);
        numero.setText(num);
        correo.setText(ema);

    }
    public void Recuperar(){
        SharedPreferences preferences =getSharedPreferences("contacto", Context.MODE_PRIVATE);
        String name=preferences.getString("name","No existe informacion");
        String number=preferences.getString("number","No existe infomacion");
        String sms = preferences.getString("sms","No existe informatica");

        nombre.setText(name);
        numero.setText(number);
        correo.setText(sms);
    }


     */
    public void Preferencia (View v){
        Intent intent= new Intent(getApplicationContext(),Preferencias.class);
        startActivity(intent);
    }


}