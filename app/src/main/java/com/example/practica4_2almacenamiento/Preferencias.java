package com.example.practica4_2almacenamiento;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Preferencias extends AppCompatActivity {
EditText formato_letra,color_fondo;
Button guardar_fuente;

private static final String SHARED_PREF_NAME="MyPref";
private static final String KEY_FONT_STYLE="FontStyle";
SharedPreferences sharedPreferences;
SharedPreferences.Editor editor;
final String[] styleList=new String[]{"Normal","Bold","Italic","Bold Italic"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias);
sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
editor=sharedPreferences.edit();
formato_letra=findViewById(R.id.etLetra);
guardar_fuente=findViewById(R.id.btnGuardar);

    guardar_fuente.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            CambiarFuente();
        }
    });
    int intFontStyle=sharedPreferences.getInt(KEY_FONT_STYLE,0);
        if(intFontStyle ==0) {
            formato_letra.setTypeface(null, Typeface.NORMAL);

        }else if(intFontStyle ==1){
            formato_letra.setTypeface(formato_letra.getTypeface(), Typeface.BOLD);
        }else if (intFontStyle==2){
            formato_letra.setTypeface(formato_letra.getTypeface(),Typeface.ITALIC);
        }else if(intFontStyle ==3){
            formato_letra.setTypeface(formato_letra.getTypeface(),Typeface.BOLD_ITALIC);
        }
        guardar_fuente.setText(styleList[intFontStyle]);
        }


    private void CambiarFuente() {
        final String[] styleList = new String[]{"Normal", "Bold", "Italic", "Bold Italic"};
        AlertDialog.Builder builder = new AlertDialog.Builder(Preferencias.this);
        builder.setTitle("Preferencias");
        builder.setItems(styleList, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    formato_letra.setTypeface(null, Typeface.NORMAL);
                } else if (i == 1) {
                    formato_letra.setTypeface(formato_letra.getTypeface(), Typeface.BOLD);

                } else if (i == 2) {
                    formato_letra.setTypeface(formato_letra.getTypeface(), Typeface.ITALIC);

                } else if (i == 3) {
                    formato_letra.setTypeface(formato_letra.getTypeface(), Typeface.BOLD_ITALIC);

                }
                guardar_fuente.setText(styleList[i]);
                editor.putInt(KEY_FONT_STYLE,i);
            }
        });
        builder.show();
    }

}