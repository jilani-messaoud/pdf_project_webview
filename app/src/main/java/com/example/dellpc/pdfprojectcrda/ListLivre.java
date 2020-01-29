package com.example.dellpc.pdfprojectcrda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.dellpc.pdfprojectcrda.Controleur.ControleurLivre;
import com.example.dellpc.pdfprojectcrda.R;

public class ListLivre extends AppCompatActivity
{
Spinner spinner ;
ListView listView ;
ControleurLivre controleurLivre  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_livre);
        init();
        setView();
    }
   void init()
   {
        spinner = findViewById(R.id.spiner);
        listView = (ListView)findViewById(R.id.list) ;
    }
   void setView()
   {
   controleurLivre = new ControleurLivre(ListLivre.this) ;
   controleurLivre.setSpinerCategorie(spinner);
   controleurLivre.setListeLivre(listView,"all","");
   spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
       @Override
       public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
       {

            if (spinner.getSelectedItem().toString().equals("All"))
            controleurLivre.setListeLivre(listView,"all",spinner.getSelectedItem().toString());
       else
            controleurLivre.setListeLivre(listView,"cat",spinner.getSelectedItem().toString());

       }

       @Override
       public void onNothingSelected(AdapterView<?> parent) {

       }
   });
   }
}
