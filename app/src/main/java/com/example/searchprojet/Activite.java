package com.example.searchprojet;


import android.app.DatePickerDialog;

import java.io.Serializable;

public class Activite implements Serializable {
    public static int idActivite;
    private String desc;
    private String date;
    private String heure;

    private String sujet;
    private String lieu;
    private String img;
    private String pdf;
    private int idUser;
    private int idCategorie;

    public Activite(int idActivite, String desc, String date, String sujet,String heur, String lieu, String img, String pdf, int idUser, int idCategorie) {
        idActivite++;
        this.desc = desc;
        this.date = date;
        this.heure = heur;
        this.sujet = sujet;
        this.lieu = lieu;
        this.img = img;
        this.pdf = pdf;
        this.idUser = idUser;
        this.idCategorie = idCategorie;
    }

    public Activite(String date, String sujet, String lieu, String imageURL) {
        idActivite++;
        this.date = date;
        this.sujet = sujet;
        this.lieu = lieu;
    }
    public Activite(String date, String sujet, String lieu) {
        idActivite++;
        this.date = date;
        this.sujet = sujet;
        this.lieu = lieu;
    }

    public Activite(String date,String heur, String sujet, String lieu,String descr,String img) {
        idActivite++;
        this.date = date;
        this.heure = heur;
        this.sujet = sujet;
        this.lieu = lieu;
        this.img = img;
        this.desc = descr;
    }


    public int getIdActivite() {
        return idActivite;
    }

    public void setIdActivite(int idActivite) {
        this.idActivite = idActivite;
    }

    public String getDesc() {
        return desc;
    }

    public void setNomDesc(String Desc) {
        this.desc = Desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

  /*  public String getHeur() {
        return heur;
    }

    public void setHeur(String heur) {
        this.heur = heur;
    }
*/
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
                ", nomActivite='" + desc + '\'' +
                ", date='" + date + '\'' +
         //       ", heur='" + heur + '\'' +
                ", sujet='" + sujet + '\'' +
                ", lieu='" + lieu + '\'' +
                ", img='" + img + '\'' +
                ", pdf='" + pdf + '\'' +
                ", idUser=" + idUser +
                ", idCategorie=" + idCategorie +
                '}';
    }
}
