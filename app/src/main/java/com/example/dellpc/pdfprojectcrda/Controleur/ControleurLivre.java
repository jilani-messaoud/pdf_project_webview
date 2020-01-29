package com.example.dellpc.pdfprojectcrda.Controleur;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.dellpc.pdfprojectcrda.ListLivre;
import com.example.dellpc.pdfprojectcrda.Model.Livre;
import com.example.dellpc.pdfprojectcrda.PdfView;
import com.example.dellpc.pdfprojectcrda.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.R.layout.simple_spinner_item;

public class ControleurLivre
{
    Bundle bundle ;
    Livre livre ;
    Context context ;
    ArrayList<Livre> listLivre = new ArrayList<>() ;
    ArrayList<String > listCAt = new ArrayList<>();
    public ControleurLivre(Context context) {
        this.context = context;
    }
   public void setSpinerCategorie (final Spinner spinner )
    {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray array = jsonResponse.getJSONArray("tab_Livre");
                    listCAt.add("All") ;
                   for (int i = 0 ; i<array.length() ;i++ )
                   {
                       listCAt.add(array.getJSONObject(i).getString("categorie"));
                   }
                 ArrayAdapter   stringArrayAdapter = new ArrayAdapter<String>(context, simple_spinner_item
                            , listCAt);
                    stringArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(stringArrayAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();

                }

            }

        };

        RequesteLivre loginRequest = new RequesteLivre("http://192.168.1.6/LIb/listelivre.php",
                "all", "",
                responseListener);
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(loginRequest);
    }
    public void setListeLivre (final ListView listeLivre,String op,String valeur)
    {
        listLivre = new ArrayList<>();
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray array = jsonResponse.getJSONArray("tab_Livre");
                    for (int i = 0 ; i<array.length() ;i++ )
                    {
                        livre = new Livre() ;
                        livre.setAuteur(array.getJSONObject(i).getString("auteur"));
                        livre.setCategorie(array.getJSONObject(i).getString("categorie"));
                        livre.setDescription(array.getJSONObject(i).getString("description"));
                        livre.setId_livre(array.getJSONObject(i).getString("id_livre"));
                        livre.setLangue(array.getJSONObject(i).getString("langue"));
                        livre.setLienlivre(array.getJSONObject(i).getString("lienlivre"));
                        livre.setTitre(array.getJSONObject(i).getString("titre"));
                        listLivre.add(livre) ;
                    }
                    CustomorAdapteur customorAdapteur =new CustomorAdapteur() ;
                    listeLivre.setAdapter(customorAdapteur);

                } catch (JSONException e) {
                    e.printStackTrace();

                }

            }

        };

        RequesteLivre loginRequest = new RequesteLivre("http://192.168.1.52/LIb/listelivre.php",
                op, valeur,
                responseListener);
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(loginRequest);
    listeLivre.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            bundle = new Bundle() ;
            bundle.putString("lien",listLivre.get(position).getLienlivre());
            Intent intent = new Intent(context.getApplicationContext(),PdfView.class) ;
            intent.putExtras(bundle) ;
            context.startActivity(intent);

        }
    });
    }

    class CustomorAdapteur extends BaseAdapter
    {
        public CustomorAdapteur() { }

        @Override
        public int getCount() {

            return listLivre.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.itms_livre, null, true);


            TextView id = convertView.findViewById(R.id.id) ;
            TextView title = convertView.findViewById(R.id.title) ;
            TextView auteur = convertView.findViewById(R.id.auteur) ;
            TextView langue = convertView.findViewById(R.id.langue) ;
            TextView categorie = convertView.findViewById(R.id.categorie) ;
            TextView description = convertView.findViewById(R.id.description) ;
            id.setText("id:"+listLivre.get(position).getId_livre());
            title.setText("Titre:"+listLivre.get(position).getTitre());
            auteur.setText("Auteur:"+listLivre.get(position).getAuteur());
            langue.setText("Langue:"+listLivre.get(position).getLangue());
            categorie.setText("Categorie="+listLivre.get(position).getCategorie());
            description.setText("Description:"+listLivre.get(position).getDescription());
            return convertView ;
        }
    }
}
