package Metier;

public class Etudiant {
    private int idetu;
    private String nom;
    private String prenom;
    private String etatcivil;
    private int annee;
    private int semestre;
    private int lecture;
    private int sport;
    private int cinema;

    public Etudiant(int id, String nom, String prenom, String ec, int a, int s, int lec, int sp, int cin) {
        this.idetu = id;
        this.nom = nom;
        this.prenom = prenom;
        this.etatcivil = ec;
        this.annee = a;
        this.semestre = s;
        this.lecture = lec;
        this.sport = sp;
        this.cinema = cin;
    }

    public int getIdetu() {
        return idetu;
    }

    public void setIdetu(int idetu) {
        this.idetu = idetu;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEtatcivil() {
        return etatcivil;
    }

    public void setEtatcivil(String etatcivil) {
        this.etatcivil = etatcivil;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public int getLecture() {
        return lecture;
    }

    public void setLecture(int lecture) {
        this.lecture = lecture;
    }

    public int getSport() {
        return sport;
    }

    public void setSport(int sport) {
        this.sport = sport;
    }

    public int getCinema() {
        return cinema;
    }

    public void setCinema(int cinema) {
        this.cinema = cinema;
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "idetu=" + idetu +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", etatcivil='" + etatcivil + '\'' +
                ", annee=" + annee +
                ", semestre=" + semestre +
                ", lecture=" + lecture +
                ", sport=" + sport +
                ", cinema=" + cinema +
                '}';
    }
}
