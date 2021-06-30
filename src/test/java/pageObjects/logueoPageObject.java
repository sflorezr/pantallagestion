package pageObjects;

import models.menuInforme;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
@DefaultUrl("https://implementacion.adminfo.net/vsmart/index.php")
public class logueoPageObject extends PageObject {

    public String ruta="https://implementacion.adminfo.net/vsmart/index.php";

    @FindBy(xpath = "//input[@name='usuario' and @id='usuario']")
    WebElementFacade usuario;

    @FindBy(xpath = "//*[@id='s2id_col']")
    WebElementFacade btnCriterios;
    @FindBy(xpath = "//*[@id='select2-drop']/div/input")
    WebElementFacade selectCriterios;

    @FindBy(id = "clave")
    WebElementFacade clave;

    @FindBy(xpath = "//*[@id='login-content']/div[5]/button")
    WebElementFacade botonEntrar;

    @FindBy(xpath = "//a[@id='irMantenimiento']/../a[2]/span")
    WebElementFacade botonGestion;

    @FindBy(xpath = "//a[@title='Listar Valores Campo']/span")
    WebElementFacade botonListarValores;

    public void seleccionarCriterio(String criterio) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(getDriver(),30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='s2id_col']")));
        btnCriterios.click();
        selectCriterios.type(criterio).sendKeys(Keys.TAB);
        if(!criterio.toLowerCase().contains("fech")){
            botonListarValores.click();
            Thread.sleep(3000);
        }
        //selectCriterios.selectByVisibleText(criterio);
    }
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
    public void seleccionarEmpresa(String consecutivoBase){
        WebElement empresas = getDriver().findElement(By.xpath("//input[@value='"+consecutivoBase+"']"));
        empresas.submit();
      //  getDriver().get("https://implementacion.adminfo.net/vsmart/index.php?rtr=mantenimiento&ctr=cambioempresactr&acc=cambiar&consecutivo=15888");
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
