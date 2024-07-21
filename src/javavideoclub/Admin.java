/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javavideoclub;

/**
 *
 * @author pc
 */
public class Admin {

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenoms() {
        return prenoms;
    }

    public void setPrenoms(String prenoms) {
        this.prenoms = prenoms;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroDeTelephone() {
        return numeroDeTelephone;
    }

    public void setNumeroDeTelephone(String numeroDeTelephone) {
        this.numeroDeTelephone = numeroDeTelephone;
    }

    public Boolean getCompteExiste() {
        return compteExiste;
    }

    public void setCompteExiste(Boolean compteExiste) {
        this.compteExiste = compteExiste;
    }
    
    private String nom;
    private String prenoms;
    private String mdp;
    private String email;
    private String numeroDeTelephone;
    
    private Boolean compteExiste;
    
    public void seConnecter () {
        DbOperationsAdmin connexion = new DbOperationsAdmin ();
        
        connexion.setEmail(email);
        connexion.setMdp(mdp);
        connexion.setNumeroDeTelephone(numeroDeTelephone);
        
        connexion.connexion();
        compteExiste = connexion.getCompteExiste();
        
    }
}
