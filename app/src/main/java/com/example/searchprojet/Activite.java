package com.example.searchprojet;


public class Activite {
    private int idActivite;
    private String nomActivite;
    private String date;
    private String heur;
    private String sujet;
    private String lieu;
    private String img;
    private String pdf;
    private int idUser;
    private int idCategorie;

    public Activite(int idActivite, String nomActivite, String date, String heur, String sujet, String lieu, String img, String pdf, int idUser, int idCategorie) {
        this.idActivite = idActivite;
        this.nomActivite = nomActivite;
        this.date = date;
        this.heur = heur;
        this.sujet = sujet;
        this.lieu = lieu;
        this.img = img;
        this.pdf = pdf;
        this.idUser = idUser;
        this.idCategorie = idCategorie;
    }

    public Activite(String date, String sujet, String lieu) {
        this.date = date;
        this.sujet = sujet;
        this.lieu = lieu;
    }

    public int getIdActivite() {
        return idActivite;
    }

    public void setIdActivite(int idActivite) {
        this.idActivite = idActivite;
    }

    public String getNomActivite() {
        return nomActivite;
    }

    public void setNomActivite(String nomActivite) {
        this.nomActivite = nomActivite;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeur() {
        return heur;
    }

    public void setHeur(String heur) {
        this.heur = heur;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    @Override
    public String toString() {
        return "Activite{" +
                "idActivite=" + idActivite +
                ", nomActivite='" + nomActivite + '\'' +
                ", date='" + date + '\'' +
                ", heur='" + heur + '\'' +
                ", sujet='" + sujet + '\'' +
                ", lieu='" + lieu + '\'' +
                ", img='" + img + '\'' +
                ", pdf='" + pdf + '\'' +
                ", idUser=" + idUser +
                ", idCategorie=" + idCategorie +
                '}';
    }
}
