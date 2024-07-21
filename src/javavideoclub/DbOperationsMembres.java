/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javavideoclub;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

/**
 *
 * @author pc
 */
public class DbOperationsMembres {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getDate_de_naissance() {
        return date_de_naissance;
    }

    public void setDate_de_naissance(String date_de_naissance) {
        this.date_de_naissance = date_de_naissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumero_de_telephone() {
        return numero_de_telephone;
    }

    public void setNumero_de_telephone(String numero_de_telephone) {
        this.numero_de_telephone = numero_de_telephone;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Boolean getLocation() {
        return location;
    }

    public void setLocation(Boolean location) {
        this.location = location;
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
    
    
    private int id;
    private String nom, prenoms, date_de_naissance, adresse, email, numero_de_telephone, genre;
    private Boolean location;
    
    private Connection connexionALaBdd = null;
    private ResultSet result;
    private PreparedStatement stm;
    
    DbOperationsMembres () {
        try {
            Class.forName("org.sqlite.JDBC");
            connexionALaBdd = DriverManager.getConnection ("jdbc:sqlite:D:/Tous les projets/JAVA/Netbeans/javavideoclubProject/jars, db/videoClubDb.db");
            System.out.println ("Mety");
        } catch (ClassNotFoundException | SQLException e) {e.printStackTrace();
        }
    }
    
    public void listerTousLesMembres(){
        try {
            String liste = "SELECT * FROM membres";
            PreparedStatement prepa = connexionALaBdd.prepareStatement(liste);
            result = prepa.executeQuery();
            stm = prepa;
            
        } catch (SQLException e) { e.printStackTrace();
        } 
    }
    
    public void listerLesRecherchesParId(int id_l) {
        try {
            String liste = "SELECT * FROM membres WHERE id = " + id_l + ";";
            PreparedStatement prepa = connexionALaBdd.prepareStatement(liste);
            result = prepa.executeQuery ();
            stm = prepa;
            
        } catch (SQLException e){e.printStackTrace();} 
    }
    public void listerLesRecherchesParNom (String nomP) {
        try {
            String liste = "SELECT * FROM membres WHERE nom LIKE '%" + nomP + "%' OR prenoms LIKE '%" + nomP + "%';";
            PreparedStatement prepa = connexionALaBdd.prepareStatement(liste);
            result = prepa.executeQuery ();
            stm = prepa;
            
        } catch (SQLException e){e.printStackTrace();}
    }
    
    public void faireLocation(int id) {
        try {
            String location2 = "UPDATE membres SET location = true WHERE id = " + id + ";";
            PreparedStatement prepa = connexionALaBdd.prepareStatement(location2);
            prepa.executeUpdate ();
            stm = prepa;
            
        } catch (SQLException e){e.printStackTrace();} finally {
            try {
                connexionALaBdd.close();
            } catch (SQLException e){e.printStackTrace();}
            
        }
    }
    public void defaireLocation(int id) {
        try {
            String location2 = "UPDATE membres SET location = false WHERE id = " + id + ";";
            PreparedStatement prepa = connexionALaBdd.prepareStatement(location2);
            prepa.executeUpdate();
            stm = prepa;
        } catch (SQLException e){e.printStackTrace();} finally {
            try {
                connexionALaBdd.close();
            } catch (SQLException e){e.printStackTrace();}
            
        }
    }
    public void ajouterMembre (String nom, String prenoms, String date_de_naissance, String adresse, String email, String numero_de_telephone, String genre) {
        try {
            String prepa = "INSERT INTO membres (nom, prenoms, date_de_naissance, adresse, email, numero_de_telephone, genre, location) VALUES";
            String prepa2 = "('" + nom + "','" + prenoms + "','" + date_de_naissance + "','" + adresse + "','" + email + "','" + numero_de_telephone + "','" + genre + "', false )";
            PreparedStatement prepaSt = connexionALaBdd.prepareStatement (prepa + prepa2);
            prepaSt.executeUpdate();
        } catch (SQLException e){e.printStackTrace();}
    }
    public void supprimerMembre (int id) {
        try {
            connexionALaBdd.prepareStatement("DELETE FROM membres WHERE id = " + id).execute();    
        } catch (SQLException e) {e.printStackTrace();} finally {
            try {
                connexionALaBdd.close();
            } catch (SQLException e) {e.printStackTrace();}
        }
    }
    public void modifierMembre (String nom, String prenoms, String date_de_naissance, String adresse, String email, String numero_de_telephone, String genre, int id) {
        try {
            String prepa = "UPDATE membres SET nom = '";
            String prepa2 = nom + "', prenoms = '" + prenoms + "', date_de_naissance = '" + date_de_naissance + "', adresse = '";
            String prepa3 = adresse + "', email = '" + email + "', numero_de_telephone = '" + numero_de_telephone + "', genre = '" + genre + "' WHERE id = " + id;
            PreparedStatement prepaSt = connexionALaBdd.prepareStatement(prepa + prepa2 + prepa3);
            prepaSt.executeUpdate();
        } catch (SQLException e) {e.printStackTrace();}
        finally {
            try {
                connexionALaBdd.close();
            } catch (SQLException e) {e.printStackTrace();}
        }
    }
}
