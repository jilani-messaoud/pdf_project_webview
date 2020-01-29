package com.example.dellpc.pdfprojectcrda.Controleur;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.dellpc.pdfprojectcrda.ListLivre;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ContrloeurLogin
{
    boolean login ;
    EditText textUserName , textPassword ;
    Context context ;
    Intent intent ;
    ArrayList<String>list ;
    Bundle bundle = new Bundle() ;
    ArrayList<String> listeCircuit= new ArrayList<String>() ;
/*    DAOSQlite daosQlite = new DAOSQlite(LoginActivity.this);
    Gouvernorat gouvernorat = new Gouvernorat() ;
                gouvernorat.setId("11");
                gouvernorat.setLabel("1");
    DAOGouvernorat daoGouvernorat = new DAOGouvernorat(daosQlite);
                Toast.makeText(getApplicationContext(),String.valueOf(daoGouvernorat.ajoutGouvernorat(gouvernorat)),Toast.LENGTH_LONG).show();
*/
    public ContrloeurLogin(EditText textUserName, EditText textPassword, Context context)
    {
        this.textUserName = textUserName;
        this.textPassword = textPassword;
        this.context = context;
    }
    public void login()
    {
        if (verifierText())
       {

           Response.Listener<String> responseListener = new Response.Listener<String>() {
               @Override
               public void onResponse(String response) {
                   try {

                       JSONObject jsonResponse = new JSONObject(response);
                        Boolean login = jsonResponse.getBoolean("login") ;
                        if (login)
                        {
                            Intent intent = new Intent(context.getApplicationContext(),ListLivre.class);
                            context.startActivity(intent);
                        }
                        else
                            {
                                textUserName.setError("Vérifier le nom d'utilisateur");
                                textPassword.setError("Vérifiez votre mot de passe");

                            }
                   } catch (JSONException e) {
                       e.printStackTrace();

                   }

               }

           };

           RequesteLogin loginRequest = new RequesteLogin("http://192.168.1.52/LIb/login.php",
                   textPassword.getText().toString(), textUserName.getText().toString(),
                   responseListener);
           RequestQueue queue = Volley.newRequestQueue(context);
           queue.add(loginRequest);
       }
           // Intent intent = new Intent(context.getApplicationContext(),MainActivity.class);
            //context.startActivity(intent);



        }

    boolean verifierText()
    {
        if (textUserName.getText().toString().length()==0)
        {
            textUserName.setError("Vérifier le nom d'utilisateur");
        }

        if (textPassword.getText().toString().length()==0)
        {
            textPassword.setError("Vérifiez votre mot de passe");
        }
        return  textPassword.getText().toString().length()!=0 && textUserName.getText().toString().length()!=0 ;
    }

}
