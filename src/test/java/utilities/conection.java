package utilities;


import org.apache.log4j.Logger;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class conection {
    private static final Logger LOGGER = Logger.getLogger(conection.class.getName());

    public ResultSet ConsultaBase(String consulta){
        InputStream input = getClass().getClassLoader().getResourceAsStream("src/test/java/resources/datos.properties");
        Properties prop = new Properties();
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
            st.close();
            base.close();
        }catch (ClassNotFoundException | SQLException e){
            LOGGER.error(e.getMessage());
        }

        return rs;
    }

}
