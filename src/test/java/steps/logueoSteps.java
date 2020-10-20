package steps;

import com.google.common.collect.Lists;
import models.menuInforme;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import pageObjects.logueoPageObject;
import utilities.conection;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
    public void iraInformes(String grupo){
        paginaLogeo.darClicBotonGestion();
        paginaLogeo.buscarPerfil(grupo);
       // System.out.println(paginaLogeo.getTitle());

    }
    @Step
    public void iraInforme(String nomprograma){
       // paginaLogeo.openAt(paginaLogeo.ruta+"?rtr=informes&ctr=InformesControlador&acc=&nom_programa="+nomprograma+"");
    }
    @Step
    public void consulta(String consulta) throws SQLException, IOException {
        ResultSet rs= null;
        rs=con.ConsultaBase(consulta);
        List<menuInforme> lista=Lists.newArrayList();
        menuInforme menu=null;
        while (rs.next()) {
            menu = new menuInforme(rs.getString(3),rs.getString(4));
            lista.add(menu);
            //System.out.println(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5));
        }
        paginaLogeo.validarMenus(lista);
        validarMenu(lista);
    }
    @Step
    public void validarMenu(List<menuInforme> menus){
        for(int i=0;i<menus.size();i++) {
            paginaLogeo.openAt(paginaLogeo.ruta + "?rtr=informes&ctr=" + menus.get(i).getCtr() + "&acc=&nom_programa=" + menus.get(i).getAcc() + "");
            Serenity.takeScreenshot();
        }
    }
}
