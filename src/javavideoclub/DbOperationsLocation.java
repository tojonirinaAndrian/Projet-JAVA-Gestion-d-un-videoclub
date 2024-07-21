/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javavideoclub;

import java.sql.*;

/**
 *
 * @author pc
 */
public class DbOperationsLocation {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_l() {
        return id_l;
    }

    public void setId_l(int id_l) {
        this.id_l = id_l;
    }

    public int getId_f() {
        return id_f;
    }

    public void setId_f(int id_f) {
        this.id_f = id_f;
    }

    public String getDate_location() {
        return date_location;
    }

    public void setDate_location(String date_location) {
        this.date_location = date_location;
    }

    public String getDate_remise() {
        return date_remise;
    }

    public void setDate_remise(String date_remise) {
        this.date_remise = date_remise;
    }

    public String getDate_remise_prevue() {
        return date_remise_prevue;
    }

    public void setDate_remise_prevue(String date_remise_prevue) {
        this.date_remise_prevue = date_remise_prevue;
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
    private int id_l;
    private int id_f;
    private String date_location;
    private String date_remise;
    private String date_remise_prevue;
    
    
    private Connection connexionALaBdd = null;
     private ResultSet result;
    private PreparedStatement stm;
    DbOperationsLocation () {
        try {
            Class.forName("org.sqlite.JDBC");
            connexionALaBdd = DriverManager.getConnection ("jdbc:sqlite:D:/Tous les projets/JAVA/Netbeans/javavideoclubProject/jars, db/videoClubDb.db");
            System.out.println ("Mety");
            connexionALaBdd.createStatement().execute("PRAGMA busy_timeout = 10000");
//            connexionALaBdd.createStatement().execute("PRAGMA foreign_keys = ON");
        } catch (ClassNotFoundException | SQLException e) {e.printStackTrace();
        } 
    }
    
    public void voirLaLocationViaFilm(int id_f){
        String voir = "SELECT * FROM location WHERE id_f = " + id_f + " AND date_remise is null;";
        try {
            PreparedStatement prepa = connexionALaBdd.prepareStatement(voir);
            result = prepa.executeQuery();
            if (result.next()) {
                id_l = result.getInt ("id_m");
                PreparedStatement prepa2 = connexionALaBdd.prepareStatement("SELECT * FROM membres WHERE id = " + id_l + ";");
                result = prepa2.executeQuery();
                stm = prepa2;
            } else {System.out.println ("Film disponible pour la location.");}
        } catch (SQLException e){e.printStackTrace();} 
    }
    
    public void faireLocation (int id_f, int id_l, String date_location, String date_remise_prevue) {
       try {
            String location = "INSERT INTO location (id_f, id_m , date_location, date_remise_prevue) VALUES (?, ?, ?, ?)";
            PreparedStatement prepa = connexionALaBdd.prepareStatement(location);
            prepa.setInt(1, id_f);
            prepa.setInt(2, id_l);
            prepa.setString(3, date_location); prepa.setString(4,date_remise_prevue);
            prepa.executeUpdate();
            
        } catch (SQLException e){e.printStackTrace();} 
        
    }
    
    public void rendreFilmAfficher(int id_f) {
        try {
            String rendre = "SELECT * FROM location WHERE date_remise is null AND id_f = ?";
            PreparedStatement prepa = connexionALaBdd.prepareStatement(rendre);
            prepa.setInt(1, id_f);
            result = prepa.executeQuery();
            stm = prepa;
            
        } catch (SQLException e){e.printStackTrace();} 
    }
    
    public void rendreFilm (int id_f, String date_remise) {
        try {
            String rendre2 = "UPDATE location SET date_remise = ? WHERE date_remise is null AND id_f = ?";
            stm = connexionALaBdd.prepareStatement(rendre2);
            stm.setString(1, date_remise);
            stm.setInt (2, id_f);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println ("ETOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO.");
        } finally {
            try {
                connexionALaBdd.close();
            } catch (SQLException e){e.printStackTrace();}
            
        }
    }
    
    public void historique (int id_m) {
        try {
            String histo = "SELECT * FROM location WHERE id_m = " + id_m;
            stm = connexionALaBdd.prepareStatement(histo);
            result = stm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void historiqueParId (int id_m, int id_f) {
        try {
            String histo = "SELECT * FROM location WHERE id_m = " + id_m + " AND id_f = " + id_f;
            stm = connexionALaBdd.prepareStatement(histo);
            result = stm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void historiqueParDate (String date) {
        try {
            result = connexionALaBdd.prepareStatement("SELECT * FROM location WHERE date_remise LIKE '%" + date + "%' OR date_remise_prevue LIKE '%" + date + "%' OR date_location LIKE '%" + date + "%'").executeQuery();
        } catch (SQLException e) { 
            e.printStackTrace();
        }
    }
}
