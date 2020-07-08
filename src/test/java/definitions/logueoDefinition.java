package definitions;

import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import net.thucydides.core.annotations.Steps;
import steps.logueoSteps;

public class logueoDefinition {
    @Steps
    logueoSteps pasos;

    @Dado("^Estoy en pagina de logueo adminfo$")
    public void estoy_en_pagina_de_logueo_adminfo() throws Exception {
        pasos.abrirAdminfo();
    }


    @Cuando("^digito usuario \"([^\"]*)\" y clave \"([^\"]*)\"$")
    public void digito_usuario_y_clave(String nombreUsuario, String clave) throws Exception {
        pasos.loguear(nombreUsuario,clave);
    }

    @Cuando("^presiono el boton de logueo$")
    public void presiono_el_boton_de_logueo() throws Exception {
        pasos.clicBotonEntrar();
    }

    @Entonces("^valido inicio de sesion$")
    public void valido_inicio_de_sesion() throws Exception {
        // aqui debo crear el metodo para validar
    }

}
