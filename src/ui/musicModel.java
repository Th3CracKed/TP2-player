/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Asus
 */
public class musicModel {
    private String musique;
    private String auteur;
    private String duree;

    public musicModel(String music, String auteur, String duree) {
        this.musique = music;
        this.auteur = auteur;
        this.duree = duree;
    }

    public String getMusique() {
        return musique;
    }

    public void setMusique(String musique) {
        this.musique = musique;
    }
    
    public StringProperty musicProperty() { 
         if (musique == null) musique = "Musique Inconnu";
         return new SimpleStringProperty(this,musique); 
    }
    

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }
    
    public StringProperty auteurProperty() { 
        if (auteur == null) auteur = "Auteur Inconnu";
        return new SimpleStringProperty(this,auteur); 
    }
    

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }
    
    public StringProperty dureeProperty() { 
        if (duree == null) duree = "00:00";
        return new SimpleStringProperty(this,duree); 
    }
}
