/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javavideoclub;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc
 */
public class DbOperationsAdmin {

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

    public ResultSet getResult() {
        return result;
    }

    public void setResult(ResultSet result) {
        this.result = result;
    }

    public PreparedStatement getStm() {
        return stm;
    }

    public void setStm(PreparedStatement stm) {
        this.stm = stm;
    }

    public Connection getConnexionALaBdd() {
        return connexionALaBdd;
    }

    public void setConnexionALaBdd(Connection connexionALaBdd) {
        this.connexionALaBdd = connexionALaBdd;
    }
    
    
    private String nom, prenoms, mdp, email, numeroDeTelephone;
    
    private Boolean compteExiste;
    private Connection connexionALaBdd;
    private ResultSet result;
    private PreparedStatement stm;
    
    DbOperationsAdmin (){
        try {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DbOperationsAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
//            String currentDir = System.getProperty("user.dir");
//            System.out.println("Current working directory: " + currentDir);
            connexionALaBdd = DriverManager.getConnection ("jdbc:sqlite:src/javavideoclub/videoClubDb.db");
//            System.out.println ("Mety");
        } catch (SQLException e) {
//            System.out.println ("Tsy mety");
        }
    }
    
    public void connexion () {
        try {
            System.out.println ("DOA : email : " + email);
            System.out.println ("DOA : numero : " + numeroDeTelephone);
            System.out.println ("DOA : mdp : " + mdp);
            String voir = "SELECT * FROM admin WHERE (email = '" + email + "' OR numero_de_telephone = '" + numeroDeTelephone +"') AND mot_de_passe = '" + mdp + "'";
            PreparedStatement verifier = connexionALaBdd.prepareStatement (voir);
            
            result = verifier.executeQuery();
            if (result.next()) {
                System.out.println ("DOA : Ce compte Existe bien");
                compteExiste = true;
                result.close();
                verifier.close();    
            }
            else {
                compteExiste = false;
            }
        } catch (SQLException e) {e.printStackTrace();}
    }
}
