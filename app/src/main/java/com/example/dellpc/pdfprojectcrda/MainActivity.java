package com.example.dellpc.pdfprojectcrda;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dellpc.pdfprojectcrda.Controleur.ContrloeurLogin;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
Button buttonLogin ;
EditText textUser,textPass ;
ContrloeurLogin contrloeurLogin ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    init();
    setView();
    }
void  init()
{buttonLogin = findViewById(R.id.buttonLogin) ;
    textPass=findViewById(R.id.editPass) ;
    textUser=findViewById(R.id.editLogin);
}
void  setView()
{
    buttonLogin.setOnClickListener(this);
}

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        contrloeurLogin =  new ContrloeurLogin(textUser,textPass,MainActivity.this);
        contrloeurLogin.login();
      //    Toast.makeText(getApplicationContext(),textUser.getText().toString(),Toast.LENGTH_LONG).show();
    }
}
