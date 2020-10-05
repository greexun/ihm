package implementationDAO;

import Metier.Etudiant;
import interfaceDAO.IEtudiantDAO;
import javax.sql.*;
import java.sql.*;
import java.util.*;

public class EtudiantDAOimpl implements IEtudiantDAO {
    private static DataSource ds;
    private static Connection connexionBD;

    public EtudiantDAOimpl(DataSource ds, Connection connexionBD) {
        this.ds = ds;
        this.connexionBD = connexionBD;
    }

    /**
     * Permet de récuper toutes les informations des étudiants
     * @return ArrayList contenant les informations de chaque étudiant
     */
    @Override
    public ArrayList<Etudiant> getEtudiants() {
        ArrayList<Etudiant> res = new ArrayList<>();
        try {
            Statement st = connexionBD.createStatement();
            ResultSet result = st.executeQuery("select * from etudiants");
            while (result.next()) {
                res.add(new Etudiant(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getInt(5), result.getInt(6), result.getInt(7), result.getInt(8), result.getInt(9)));
            }
            st.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ;
        } finally {
            return res;
        }
    }

    @Override
    public int setEtudiants(List<Etudiant> etudiants) {
        return 0;
    }

    /**
     * Permet d'insérer un étudiant dans la base de donnée
     * @param nom
     * @param prenom
     * @param etatcivil
     * @param annee
     * @param semestre
     * @param lecture
     * @param sport
     * @param cinema
     */
    @Override
    public void insertEtudiant(String nom, String prenom, String etatcivil, int annee, int semestre, int lecture, int sport, int cinema) {
        try {
            Statement st = connexionBD.createStatement();
            st.executeUpdate("INSERT INTO etudiants (nom, prenom, etatcivil, annee, semestre, lecture, sport, cinema) VALUES ('" + nom + "', '" + prenom + "', '" + etatcivil + "', " + annee + ", " + semestre + ", " + lecture + ", " + sport + ", " + cinema + ")");
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet de modifier un étudiant présent dans la base de donnée
     * @param oldNom
     * @param oldPrenom
     * @param newNom
     * @param newPrenom
     * @param etatcivil
     * @param annee
     * @param semestre
     * @param lecture
     * @param sport
     * @param cinema
     */
    @Override
    public void updateEtudiant(String oldNom, String oldPrenom, String newNom, String newPrenom, String etatcivil, int annee, int semestre, int lecture, int sport, int cinema) {
        try {
            Statement st = connexionBD.createStatement();
            st.executeUpdate("UPDATE etudiants SET nom='" + newNom + "', prenom='" + newPrenom + "', etatcivil='" + etatcivil + "', annee='" + annee + "', semestre='" + semestre + "', lecture='" + lecture + "', sport='" + sport + "', cinema='" + cinema + "'" + " WHERE nom='" + oldNom + "' AND prenom='" + oldPrenom + "'");
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet de suprimmer un étudiant de la base de donnée
     * @param nom
     * @param prenom
     */
    @Override
    public void supprEtudiant(String nom, String prenom) {
        try {
            Statement st = connexionBD.createStatement();
            st.executeUpdate("DELETE FROM etudiants WHERE nom='" + nom + "' AND prenom='" + prenom + "'");
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void setDataSource(DataSource ds) {

    }

    @Override
    public void setConnection(Connection connexionBD) {

    }
}
