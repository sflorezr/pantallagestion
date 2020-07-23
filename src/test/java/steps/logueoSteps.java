package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;
import pageObjects.logueoPageObject;
import utilities.conection;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class logueoSteps {
    logueoPageObject paginaLogeo;
    private conection con = new conection();

    @Step
    public void abrirAdminfo(){
        paginaLogeo.open();
    }

    @Step
    public void loguear(String nombreUsuario,String clave){
        paginaLogeo.escribirUsuario(nombreUsuario);
        paginaLogeo.escribirClave(clave);
    }
    @Step
    public void clicBotonEntrar(){
        paginaLogeo.darClicBotonEntrar();
    }

    @StepGroup
    public void iraIfnormes(String grupo){
        paginaLogeo.darClicBotonGestion();
        paginaLogeo.buscarPerfil(grupo);
       // System.out.println(paginaLogeo.getTitle());

    }
    @Step
    public void iraInforme(String nomprograma){
        paginaLogeo.openAt(paginaLogeo.ruta+"?rtr=informes&ctr=InformesControlador&acc=&nom_programa="+nomprograma+"");
    }
    @Step
    public void consulta(String consulta) throws SQLException, IOException {
        ResultSet rs= null;
        rs=con.ConsultaBase(consulta);
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }

    }
}
