///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package javavideoclub;
//
///**
// *
// * @author pc
// */
//public class Films {
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getAnnee() {
//        return annee;
//    }
//
//    public void setAnnee(String annee) {
//        this.annee = annee;
//    }
//
//    public String getGenre() {
//        return genre;
//    }
//
//    public void setGenre(String genre) {
//        this.genre = genre;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public Boolean getDisponibilite() {
//        return disponibilite;
//    }
//
//    public void setDisponibilite(Boolean disponibilite) {
//        this.disponibilite = disponibilite;
//    }
//    
//    public String getTitre() {
//        return titre;
//    }
//
//    public void setTitre(String titre) {
//        this.titre = titre;
//    }
//
//    public Boolean getFilmExisteDeja() {
//        return filmExisteDeja;
//    }
//
//    public void setFilmExisteDeja(Boolean filmExisteDeja) {
//        this.filmExisteDeja = filmExisteDeja;
//    }
//    
//    private int id;
//    private String titre;
//    private String annee;
//    private String genre;
//    private String type;
//    private Boolean disponibilite;
//    
//    private Boolean filmExisteDeja = false;
//    
//    public void initialisation (String titre,String annee,String genre,String type) {
//        this.titre = titre;
//        this.annee = annee;
//        this.genre = genre;
//        this.type = type;
//    }
//    public void ajouterFilm () {
//        DbOperationsFilms ajoutFilmBdd = new DbOperationsFilms();
//        
//        ajoutFilmBdd.setTitre(titre);
//        ajoutFilmBdd.setAnnee(annee);
//        ajoutFilmBdd.setGenre(genre);
//        ajoutFilmBdd.setType(type);
//        
//        ajoutFilmBdd.ajouterFilmAlaBdd();
//        if (ajoutFilmBdd.getFilmExisteDeja()) {
//            filmExisteDeja = true;
//        }
//    }
//    public void listerTousLesFilms () {
//        DbOperationsFilms listeBdd = new DbOperationsFilms();
//        
//        listeBdd.listerTousLesFilms();
//        try {
//            while (listeBdd.getResult().next()) {
//                id = listeBdd.getResult().getInt("id");
//                titre = listeBdd.getResult().getString ("titre");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
//
