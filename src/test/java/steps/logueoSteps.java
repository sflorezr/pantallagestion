package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;
import pageObjects.logueoPageObject;
import utilities.conection;

public class logueoSteps {
    logueoPageObject paginaLogeo;

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
    public void consulta(String consulta){
        //conection.ConsultaBase consulta=null;
    }
}
