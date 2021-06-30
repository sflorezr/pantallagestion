package steps;

import com.google.common.collect.Lists;
import models.criteriosPorMenu;
import models.menuInforme;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import org.assertj.core.api.SoftAssertions;
import pageObjects.logueoPageObject;
import utilities.conection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class logueoSteps {
    logueoPageObject paginaLogeo;
    private conection con = new conection();
    private conection con2 = new conection();

    @Step
    public void abrirAdminfo(){
        paginaLogeo.open();
    }

    @Step
    public void irAEmpresa(String nomEmpresa){
        paginaLogeo.seleccionarEmpresa(nomEmpresa);
    }

    @Step
    public void loguear(String nombreUsuario,String clave){
        paginaLogeo.escribirUsuario(nombreUsuario);
        paginaLogeo.escribirClave(clave);
    }
    @Step
    public void clicBotonEntrar(){
        paginaLogeo.darClicBotonEntrar();
        paginaLogeo.darClicBotonGestion();
    }

    @StepGroup
    public void iraInformes(String grupo){

        paginaLogeo.buscarPerfil(grupo);
       // System.out.println(paginaLogeo.getTitle());

    }
    @Step
    public void iraInforme(String nomprograma){
        paginaLogeo.openAt(paginaLogeo.ruta+"?rtr=informes&ctr=InformesControlador&acc=&nom_programa="+nomprograma+"");
    }
    @Step
    public void consulta(String consulta) throws SQLException, IOException, InterruptedException {
        ResultSet rs= null;
        rs=con.ConsultaBase(consulta);
        List<menuInforme> lista=Lists.newArrayList();
        menuInforme menu=null;
        while (rs.next()) {
            menu = new menuInforme(rs.getString(3),rs.getString(4));
            lista.add(menu);
            System.out.println(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4));
        }
      //  paginaLogeo.validarMenus(lista);
        validarMenu(lista);
    }
    @StepGroup
    public void validarCriterios(String consulta,String nomPrograma) throws IOException, SQLException, InterruptedException {
        ResultSet rs=null;
        String consultaDatos="";
        String cadena="";
        String titulos="|";
        boolean bandera=true;
        int columnas=0;
        rs=con.consultaBaseConNomPrograma(consulta,nomPrograma);
        List<criteriosPorMenu> lista =Lists.newArrayList();
        criteriosPorMenu criterios=null;
        while (rs.next()){
            cadena="";
            titulos="";
            columnas=0;

            criterios= new criteriosPorMenu(rs.getString(1),rs.getString(2),rs.getString(3));

            consultaDatos="SELECT DISTINCT ON("+rs.getString(2)+") "+rs.getString(2)+"  FROM "+rs.getString(1)+" limit 10 ;";
           //seleccionarCriterio(rs.getString(3));
            paginaLogeo.seleccionarCriterio(rs.getString(3));
            consultaDatosCriterio(consultaDatos);
            System.out.println(rs.getString(1) + ' ' + rs.getString(2));
            // System.out.println(consultaDatos);
           // cadena=consultaDatos+"\n"+"\n";

        }
        //Serenity.recordReportData().withTitle("Evidencia").andContents("lista");
    }
    @Step
    public void seleccionarCriterio(String criterio) throws InterruptedException {
        paginaLogeo.seleccionarCriterio(criterio);
    }
    @Step void consultaDatosCriterio(String consultaDatos) throws IOException, SQLException {
        String cadena="";
        String titulos="|";
        boolean bandera=true;
        ResultSet rs2=null;
        int columnas=0;
        rs2=con2.ConsultaBase(consultaDatos);
        if (rs2 != null) {
            ResultSetMetaData rsmd = rs2.getMetaData();
            columnas = rsmd.getColumnCount();
            while (rs2.next()) {
                if (bandera) {
                    for (int i = 1; i <= columnas; i++) {
                        if (rs2.getMetaData().getColumnDisplaySize(i) < 80) {
                            cadena = cadena + " | " + String.format("%-" + rs2.getMetaData().getColumnDisplaySize(i) + "s", rs2.getMetaData().getColumnName(i));
                        } else {
                            cadena = cadena + " | " + String.format("%-" + 30 + "s", rs2.getMetaData().getColumnName(i));
                        }
                    }
                    cadena = cadena + "|" + "\n";

                }
                //System.out.println(cadena);
                for (int i = 1; i <= columnas; i++) {
                    if (rs2.getMetaData().getColumnDisplaySize(i) < 80) {
                        cadena = cadena + " | " + String.format("%-" + rs2.getMetaData().getColumnDisplaySize(i) + "s", rs2.getString(i));
                    } else {
                        cadena = cadena + " | " + String.format("%-" + 30 + "s", rs2.getString(i));
                    }
                }
                cadena = cadena + "|" + "\n";
                bandera = false;
            }
            System.out.println(cadena);
            Serenity.recordReportData().withTitle("Evidencia "+consultaDatos).andContents(cadena);
           // lista.add(criterios);
        }else{
            SoftAssertions softAssert = new SoftAssertions();
            softAssert.assertThat("Hello".equals("hello"));
            softAssert.assertAll();
        }
    }
    @Step
    public void validarMenu(List<menuInforme> menus) throws IOException, SQLException, InterruptedException {
        InputStream input = getClass().getClassLoader().getResourceAsStream("consultas.properties");
        Properties prop = new Properties();
        prop.load(input);
        for(int i=0;i<menus.size();i++) {
            paginaLogeo.openAt(paginaLogeo.ruta + "?rtr=informes&ctr=" + menus.get(i).getCtr() + "&acc=&nom_programa=" + menus.get(i).getAcc() + "");
            validarCriterios(prop.getProperty("consultaCriterios"),menus.get(i).getAcc());
            Serenity.takeScreenshot();
        }
    }
}
