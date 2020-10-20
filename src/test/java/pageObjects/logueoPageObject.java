package pageObjects;

import models.menuInforme;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
@DefaultUrl("https://alianzas.adminfoweb.net/vsmart/index.php")
public class logueoPageObject extends PageObject {

    public String ruta="https://alianzas.adminfoweb.net/vsmart/index.php";

    @FindBy(xpath = "//input[@name='usuario' and @id='usuario']")
    WebElementFacade usuario;

    @FindBy(id = "clave")
    WebElementFacade clave;

    @FindBy(xpath = "//*[@id='login-content']/div[5]/button")
    WebElementFacade botonEntrar;

    @FindBy(xpath = "//a[@id='irMantenimiento']/../a[2]/span")
    WebElementFacade botonGestion;


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
    public void darClicBotonGestion(){
        botonGestion.click();
    }
    public void buscarPerfil(String grupo){
        WebElement perfil = getDriver().findElement(By.xpath("//tr[@tabindex="+grupo+"]/td[1]/a[1]"));
        perfil.click();
    }
    public void validarMenus(List<menuInforme> menus){
        List<WebElement> listaMenus = getDriver().findElements(By.xpath("//*[text()='Informes']/../../../../ul/li/a"));
        String cadena = "";
        String menusvalidados="";
        for (int i=0;i<menus.size();i++){
            cadena=cadena+ruta+"?rtr=informes&ctr="+menus.get(i).getCtr()+"&acc="+menus.get(i).getAcc()+ "\n";
            menusvalidados=menusvalidados+listaMenus.get(i).getAttribute("href")+ "\n";
            assertThat(ruta+"?rtr=informes&ctr="+menus.get(i).getCtr()+"&acc="+menus.get(i).getAcc(),containsString(listaMenus.get(i).getAttribute("href")));
        }
        Serenity.recordReportData().withTitle("Consulta").andContents(cadena);
        Serenity.recordReportData().withTitle("Menus en adminfo").andContents(menusvalidados);
    }
}
