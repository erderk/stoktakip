
import database.Baglanti;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ahmet
 */
@ManagedBean(name = "kullanici", eager = true)
@SessionScoped
public class Kullanici{
    int id;
    String ad,soyad,eposta,sifre,hata;

    public String getHata() {
        return hata;
    }

    public void setHata(String hata) {
        this.hata = hata;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getEposta() {
        return eposta;
    }

    public void setEposta(String eposta) {
        this.eposta = eposta;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }
    
    public String kullaniciKayit(){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("insert into kullanici(ad,soyad,eposta,sifre) values(?, ?, ?, ?)");
            ps.setString(1, ad);
            ps.setString(2, soyad);
            ps.setString(3, eposta);
            ps.setString(4, sifre);
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }finally{
            try {
                conn.close();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(Kullanici.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "index?faces-redirect=true";
    }
    
    public String kullaniciGiris(){
        String a = "";
        int id = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("select * from kullanici where eposta=? and sifre=?");
            ps.setString(1, eposta);
            ps.setString(2, sifre);
            rs = ps.executeQuery();
            if(rs.next()){
                a = "giris?faces-redirect=true";
                setHata("");
            }else{
                a = "index?faces-redirect=true";
                setHata("Hatalı E-posta veya Şifre");
            }
        }catch(SQLException e){
            System.out.println(e);
        }finally{
            try{
            rs.close();
            ps.close();
            conn.close();
            }catch(SQLException ex){
                Logger.getLogger(Kullanici.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return a;
    }
    
    public String idGetir(){
        String id = "";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("select * from kullanici where eposta=? and sifre=?");
            ps.setString(1, eposta);
            ps.setString(2, sifre);
            rs = ps.executeQuery();
            if(rs.next()){
                id = Integer.toString(rs.getInt("id"));
            }
        }catch(SQLException e){
            System.out.println(e);
        }finally{
            try{
            rs.close();
            ps.close();
            conn.close();
            }catch(SQLException ex){
                Logger.getLogger(Kullanici.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }
    
    public String kullaniciGetir(){
        String kullanici = "";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("select * from kullanici where eposta=? and sifre=?");
            ps.setString(1, eposta);
            ps.setString(2, sifre);
            rs = ps.executeQuery();
            if(rs.next()){
                kullanici = Integer.toString(rs.getInt(1));
                kullanici += " "+ rs.getString(2);
                kullanici += " "+ rs.getString(3);
            }
        }catch(SQLException e){
            System.out.println(e);
        }finally{
            try{
            rs.close();
            ps.close();
            conn.close();
            }catch(SQLException ex){
                Logger.getLogger(Kullanici.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return kullanici;
    }
    
    public List<String> getUserList(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> kullaniciListesi = new ArrayList<>();
        String liste="";
        try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("select * from kullanici order by id asc");
            rs = ps.executeQuery();
            while(rs.next()){
                liste = Integer.toString(rs.getInt("id"));
                liste += " "+ rs.getString("ad");
                liste += " "+ rs.getString("soyad");
                kullaniciListesi.add(liste);
            }
        }catch(Exception e){
            System.out.println(e);
        }finally{
            try {
                conn.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Kullanici.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return kullaniciListesi;
    }
    
    public String logout() {
        setEposta("");
        setSifre("");
        return "index?faces-redirect=true";
    }
    
}
