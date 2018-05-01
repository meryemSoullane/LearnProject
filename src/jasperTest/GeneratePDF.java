package jasperTest;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import com.mysql.jdbc.Driver;

public class GeneratePDF {

    public static void main(String[] args) {

        // - Param�tres de connexion � la base de donn�es
        String url = "jdbc:mysql://localhost/jasper_database";
        String login = "root";
        String password = "root";
        Connection connection = null;

        try {
            // - Connexion � la base
            Driver monDriver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(monDriver);
            connection = DriverManager.getConnection(url, login, password);

            // - Chargement et compilation du rapport
//            JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\MSOULLANE\\report2.jrxml");
            String reportSrcFile = "C:\\Users\\MSOULLANE\\report2.jrxml";
            JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);

            // - Param�tres � envoyer au rapport
            Map parameters = new HashMap();
            parameters.put("Titre", "Titre");

            // - Execution du rapport
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);

            // - Cr�ation du rapport au format PDF
            JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\MSOULLANE\\classic.pdf");
        } catch (JRException e) {

            e.printStackTrace();
        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            try {
                 connection.close();
                } catch (SQLException e) {

                        e.printStackTrace();
                }
        }

    }
}
