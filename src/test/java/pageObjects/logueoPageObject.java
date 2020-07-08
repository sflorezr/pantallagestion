package pageObjects;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("https://solati.adminfoweb.net/vsmart/index.php")
public class logueoPageObject extends PageObject {

    @FindBy(xpath = "//input[@name='usuario' and @id='usuario']")
    WebElementFacade usuario;

    @FindBy(id = "clave")
    WebElementFacade clave;

    @FindBy(xpath = "//*[@id='login-content']/div[5]/button")
    WebElementFacade botonEntrar;

    public void escribirUsuario(String nombreUsuario){
        if(!nombreUsuario.equals("")){
            usuario.type(nombreUsuario);
        }
    }
    public void escribirClave(String claveUsuario){
        if(!claveUsuario.equals("")){
            clave.type(claveUsuario);
        }
    }

    public void darClicBotonEntrar(){
        botonEntrar.click();
    }
}
