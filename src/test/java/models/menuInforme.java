package models;

public class menuInforme {
    String ctr;

    public menuInforme(String ctr, String acc) {
        this.ctr = ctr;
        this.acc = acc;
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }

    String acc;

    public String getCtr() {
        return ctr;
    }

    public void setCtr(String ctr) {
        this.ctr = ctr;
    }
}
