package models;

public class criteriosPorMenu {
    String nomCampo;
    String tabla;
    String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public String getNomCampo() {
        return nomCampo;
    }

    public void setNomCampo(String nomCampo) {
        this.nomCampo = nomCampo;
    }
    public criteriosPorMenu(String nomCampo,String tabla,String label){
        this.nomCampo=nomCampo;
        this.tabla=tabla;
        this.label=label;
    }
}
