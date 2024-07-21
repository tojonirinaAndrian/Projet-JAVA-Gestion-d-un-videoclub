package javavideoclub;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbOperationsFilms {
//    Alt + insert : Generate code like getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(Boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Boolean getFilmExisteDeja() {
        return filmExisteDeja;
    }

    public void setFilmExisteDeja(Boolean filmExisteDeja) {
        this.filmExisteDeja = filmExisteDeja;
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
    private String titre;
    private String annee;
    private String genre;
    private String type;
    private Boolean disponibilite;
    
    private ResultSet result;
    private PreparedStatement stm;
    private Connection connexionALaBdd = null;
    private Boolean filmExisteDeja = false;
    
    DbOperationsFilms () {
        try {
            Class.forName("org.sqlite.JDBC");
            connexionALaBdd = DriverManager.getConnection ("jdbc:sqlite:D:/Tous les projets/JAVA/Netbeans/javavideoclubProject/jars, db/videoClubDb.db");
            System.out.print ("Mety");
        } catch (ClassNotFoundException | SQLException e) {e.printStackTrace();
        }
    }
    public void verifierSiExistance (String titre) {
        String voir = "SELECT * FROM films WHERE titre = ?;";
        try {
            PreparedStatement verifier = connexionALaBdd.prepareStatement (voir);
            verifier.setString(1, titre);
            
            result = verifier.executeQuery();
        } catch (SQLException e) {e.printStackTrace();}
    }
    public void ajouterFilmAlaBdd (String titre, String annee, String genre, String type) {
        try {
            String voir = "SELECT * FROM films WHERE titre = ?;";
            PreparedStatement verifier = connexionALaBdd.prepareStatement (voir);
            verifier.setString(1, titre);
            
            result = verifier.executeQuery();
            if (result.next()) {
                System.out.println ("DOF : Ce film existe deja dans la BDD");
            }
            else {
                System.out.println ("Insertion Possible");
                String inserer = "INSERT INTO films (titre, annee, genre, type, disponibilite) VALUES (?, ?, ?, ?, true);";
                PreparedStatement insererMaintenant = connexionALaBdd.prepareStatement (inserer);
                insererMaintenant.setString(1, titre);
                insererMaintenant.setString(2, annee);
                insererMaintenant.setString(3, genre);
                insererMaintenant.setString(4, type);
                insererMaintenant.execute();
            }
        } catch (SQLException e) {e.printStackTrace();
        } finally {
            try {
                connexionALaBdd.close();
            } catch (SQLException e) {e.printStackTrace();}
        }
    }
    
    public void listerTousLesFilms () {
        try {
            String lister = "SELECT * FROM films;";
            PreparedStatement liste = connexionALaBdd.prepareStatement(lister);
            result = liste.executeQuery();
            stm = liste;
            
        } catch (SQLException e) {e.printStackTrace();
        }
    }
    public void listerLesRecherches (String titleField) {
        try {
            int id_;
            String lister;
            try {
                id_ = Integer.parseInt(titleField);
                lister = "SELECT * FROM films WHERE titre LIKE '%" + titleField + "%' OR id = " + titleField + ";";
            } catch (Exception e) {
                lister = "SELECT * FROM films WHERE titre LIKE '%" + titleField + "%';";
            }
            PreparedStatement liste = connexionALaBdd.prepareStatement(lister);
            result = liste.executeQuery();
            stm = liste;
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }
    public void afficherLesInfo (String titre){
        try {
            String affiche = "SELECT * FROM films WHERE titre = '" + titre + "';";
            PreparedStatement affichage = connexionALaBdd.prepareStatement (affiche);
            result = affichage.executeQuery();
            stm = affichage;
        } catch (SQLException e) {e.printStackTrace();} 
    }
    
    public void afficherLesInfoParId (int id) {
        try {
            String affiche = "SELECT * FROM films WHERe id = " + id + ";";
            PreparedStatement affichage = connexionALaBdd.prepareStatement (affiche);
            result = affichage.executeQuery();
            stm = affichage;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void rendreIndisponible (int id) {
        try {
            String lister = "UPDATE films SET disponibilite = false WHERE id = " + id + ";";
            PreparedStatement liste = connexionALaBdd.prepareStatement(lister);
            liste.executeUpdate();     
        } catch (SQLException e) { e.printStackTrace();
        } finally {
            try {
                connexionALaBdd.close();
            } catch (SQLException e){e.printStackTrace();}
            
        }
    }
    
    public void rendreDisponible (int id) {
        try {
            String lister = "UPDATE films SET disponibilite = true WHERE id = " + id + ";";
            PreparedStatement liste = connexionALaBdd.prepareStatement(lister);
            liste.executeUpdate();
        } catch (SQLException e) {e.printStackTrace();
        } finally {
            try {
                connexionALaBdd.close();
            } catch (SQLException e){e.printStackTrace();}
            
        }
    }
    
    public void supprimer (int id) {
        try {
            String suppression = "DELETe from films where id = " + id;
            PreparedStatement supprimer = connexionALaBdd.prepareStatement(suppression);
            supprimer.execute();
        } catch (SQLException e) {e.printStackTrace();}
    }
    
    public void modifier (int id, String titre, String genre, String type, String anneeDeSortie) {
        try {
            String modification = "UPDATE films Set titre = '" + titre + "', genre = '" + genre + "', type = '" + type + "', annee = '" +anneeDeSortie+ "' WHERE id = " + id;
            connexionALaBdd.prepareStatement(modification).executeUpdate();
        } catch (SQLException e) {e.printStackTrace();}
    }
}
