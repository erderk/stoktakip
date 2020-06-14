
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ahmet
 */
@ManagedBean(name = "admin", eager = true)
@SessionScoped
public class Admin {
    String kadi,sifre,hata;

    public String getHata() {
        return hata;
    }

    public void setHata(String hata) {
        this.hata = hata;
    }

    public String getKadi() {
        return kadi;
    }

    public void setKadi(String kadi) {
        this.kadi = kadi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }
    
    public String adminKontrol(){
        String admin ="admin",dondur="";
        if(kadi.equals(admin) && sifre.equals(admin)){
            dondur = "admin?faces-redirect=true";
            setHata("");
        }else
            setHata("Hatalı Kullanıcı Adı veya Şifre");
        return dondur;
    }
    public String logout() {
        setKadi("");
        setSifre("");
        return "index?faces-redirect=true";
    }
}
