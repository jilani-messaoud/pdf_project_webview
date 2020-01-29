package com.example.dellpc.pdfprojectcrda.Controleur;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RequesteLivre extends  StringRequest{


    private Map<String, String> params;

    public RequesteLivre(String LOGIN_REQUEST_URL, String typeOp, String valeurs, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("typeOperation", typeOp);
        params.put("valeurs", valeurs);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
