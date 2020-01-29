package com.example.dellpc.pdfprojectcrda.Model;

public class Livre
{
    String id_livre, titre, auteur, langue, lienlivre,categorie,description;

    public String getId_livre() {
        return id_livre;
    }

    public void setId_livre(String id_livre) {
        this.id_livre = id_livre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getLienlivre() {
        return lienlivre;
    }

    public void setLienlivre(String lienlivre) {
        this.lienlivre = lienlivre;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
