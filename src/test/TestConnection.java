package test;

import javax.sql.*;
import java.sql.*;
import java.util.ArrayList;
import Metier.Etudiant;
import implementationDAO.EtudiantDAOimpl;
import oracle.jdbc.pool.OracleDataSource;
import interfaceDAO.IEtudiantDAO;
import persistance.MyOracleDataSource;

public class TestConnection {
    private static IEtudiantDAO etudiantDAO;
    private static DataSource dataSourceDAO;
    private static Connection connexionBD;

    public static void main(String[] args) {
        try {
            dataSourceDAO = MyOracleDataSource.getOracleDataSource();
            connexionBD = dataSourceDAO.getConnection();
            etudiantDAO = new EtudiantDAOimpl(dataSourceDAO, connexionBD);
            ArrayList<Etudiant> li = new ArrayList();
            li = (ArrayList<Etudiant>)etudiantDAO.getEtudiants();
            for (Etudiant e : li) {
                System.out.println(e);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}

