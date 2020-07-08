package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import pageObjects.logueoPageObject;

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

}
