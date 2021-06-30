package utilities;


import net.serenitybdd.core.Serenity;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class conection {
    private static final Logger LOGGER = Logger.getLogger(conection.class.getName());

    public ResultSet ConsultaBase(String consulta) throws IOException {
        InputStream input = getClass().getClassLoader().getResourceAsStream("datos.properties");
        Properties prop = new Properties();
        prop.load(input);
        ResultSet rs=null;
        String ip=prop.getProperty("ip");
        String nombre=prop.getProperty("base");
        String usuario=prop.getProperty("usuario");
        String puerto=prop.getProperty("puerto");
        String pass=prop.getProperty("pass");
        Connection base=null;
        Statement st=null;

        try{
            Class.forName("org.postgresql.Driver");
            base = DriverManager.getConnection("jdbc:postgresql://"+ip+":"+puerto+"/"+nombre,usuario,pass);
            st = base.createStatement();
            rs = st.executeQuery(consulta);
          //  st.close();
          //  base.close();
        }catch (ClassNotFoundException | SQLException e){
            LOGGER.error(e.getMessage());
            Serenity.recordReportData().withTitle("Consulta fallida "+consulta).andContents(e.getMessage());
        }

        return rs;
    }

    public ResultSet consultaBaseConNomPrograma(String consulta,String nomPrograma) throws IOException {
        InputStream input = getClass().getClassLoader().getResourceAsStream("datos.properties");
        Properties prop = new Properties();
        prop.load(input);
        ResultSet rs=null;
        String ip=prop.getProperty("ip");
        String nombre=prop.getProperty("base");
        String usuario=prop.getProperty("usuario");
        String puerto=prop.getProperty("puerto");
        String pass=prop.getProperty("pass");
        Connection base=null;
        Statement st=null;
        PreparedStatement preparedStatement=null;

        try{
            Class.forName("org.postgresql.Driver");
            base = DriverManager.getConnection("jdbc:postgresql://"+ip+":"+puerto+"/"+nombre,usuario,pass);
            //st = base.createStatement();
            preparedStatement = base.prepareStatement(consulta);
            preparedStatement.setString(1,nomPrograma.replaceAll("&nom_programa=",""));
            rs = preparedStatement.executeQuery();
            //  st.close();
            //  base.close();
        }catch (ClassNotFoundException | SQLException e){
            LOGGER.error(e.getMessage());
        }

        return rs;
    }

}
