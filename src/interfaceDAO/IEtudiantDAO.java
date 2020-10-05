package interfaceDAO;

import Metier.Etudiant;
import javax.sql.*;
import java.sql.*;
import java.util.*;

public interface IEtudiantDAO {
        public List<Etudiant> getEtudiants();
        public int setEtudiants(List<Etudiant> etudiants);
        public void insertEtudiant(String nom, String prenom, String etatcivil, int annee, int semestre, int lecture, int sport, int cinema);
        public void updateEtudiant(String oldNom, String oldPrenom, String newNom, String newPrenom, String etatcivil, int annee, int semestre, int lecture, int sport, int cinema);
        public void supprEtudiant(String nom, String prenom);
        public void setDataSource(DataSource ds);
        public void setConnection(Connection connexionBD);
}
