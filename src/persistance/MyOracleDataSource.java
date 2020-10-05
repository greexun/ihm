package persistance;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import oracle.jdbc.pool.OracleDataSource;

public class MyOracleDataSource extends OracleDataSource {

    private static MyOracleDataSource ods;

    public MyOracleDataSource() throws SQLException {
    }

    public static MyOracleDataSource getOracleDataSource() {
        if (ods == null) {
            FileInputStream fichier = null;
            Properties props = new Properties();
            try {
                fichier = new FileInputStream("src/persistance/connOracle.properties");
            } catch (FileNotFoundException ex1) {
                System.out.println("Fichier de proprietes Oracle non trouve");
            }
            try {
                props.load(fichier);
            } catch (IOException ex) {
                System.out.println("Erreur lors du chargement du fichier de proprietes Oracle");
            } finally {
                try {
                    fichier.close();
                } catch (IOException ex) {
                    System.out.print("Probleme d'entree/sortie" + ex.getMessage());
                }
            }
            try {
                ods = new MyOracleDataSource();
                // OK, on peut instancier (a vide)
            } catch (SQLException ex) { // ... OracleDataSource() peut lever cette exception
                System.out.println("*** Erreur de CONNEXION ORACLE ...");
            }
            // on construit la source de donnees
            ods.setDriverType( props.getProperty("pilote") );
            ods.setPortNumber( new Integer( props.getProperty("port") ) );
            ods.setDatabaseName( props.getProperty("sid") );
            ods.setServerName( props.getProperty("serveur") );
            ods.setUser( props.getProperty("user") );
            ods.setPassword( props.getProperty("pwd") );
        }
        // sinon, une instance de source de donnees existe deja, on la renvoie
        return ods;
    }
}