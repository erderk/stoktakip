
import database.Baglanti;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ahmet
 */
@ManagedBean(name = "calisanlar", eager = true)
@RequestScoped
public class Calisanlar {
    int id,depoid,secilen;

    public int getSecilen() {
        return secilen;
    }

    public void setSecilen(int secilen) {
        this.secilen = secilen;
    }
    String ad,soyad,depoadi,depoaciklama;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDepoid() {
        return depoid;
    }

    public void setDepoid(int depoid) {
        this.depoid = depoid;
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

    public String getDepoadi() {
        return depoadi;
    }

    public void setDepoadi(String depoadi) {
        this.depoadi = depoadi;
    }

    public String getDepoaciklama() {
        return depoaciklama;
    }

    public void setDepoaciklama(String depoaciklama) {
        this.depoaciklama = depoaciklama;
    }
    
    public void sec(int sec){
        if(sec==1){
            setSecilen(1);
        }else if(sec==2){
            setSecilen(2);
        }else{
            setSecilen(0);
        }
    }
    
    public List<Calisanlar> getCalisanListesi(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Calisanlar>calisanList=new ArrayList<>();
        if(getSecilen()==1){
            try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("select b.id,ad,soyad,a.depoid,depoadi,depoaciklama from kuldep a full join kullanici b on b.id = a.id full join depo c on c.depoid = a.depoid where b.id=? and calisiyor=true");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                Calisanlar calisan = new Calisanlar();
                calisan.setId(rs.getInt("id"));
                calisan.setAd(rs.getString("ad"));
                calisan.setSoyad(rs.getString("soyad"));
                calisan.setDepoid(rs.getInt("depoid"));
                calisan.setDepoadi(rs.getString("depoadi"));
                calisan.setDepoaciklama(rs.getString("depoaciklama"));
                calisanList.add(calisan);
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
        }else if(getSecilen()==2){
            try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("select b.id,ad,soyad,a.depoid,depoadi,depoaciklama from kuldep a full join kullanici b on b.id = a.id full join depo c on c.depoid = a.depoid where a.depoid=? and a.calisiyor=true");
            ps.setInt(1, depoid);
            rs = ps.executeQuery();
            while(rs.next()){
                Calisanlar calisan = new Calisanlar();
                calisan.setId(rs.getInt("id"));
                calisan.setAd(rs.getString("ad"));
                calisan.setSoyad(rs.getString("soyad"));
                calisan.setDepoid(rs.getInt("depoid"));
                calisan.setDepoadi(rs.getString("depoadi"));
                calisan.setDepoaciklama(rs.getString("depoaciklama"));
                calisanList.add(calisan);
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
        }else{
        try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("select b.id,ad,soyad,a.depoid,depoadi,depoaciklama from kuldep a full join kullanici b on b.id = a.id full join depo c on c.depoid = a.depoid where calisiyor=true");
            rs = ps.executeQuery();
            while(rs.next()){
                Calisanlar calisan = new Calisanlar();
                calisan.setId(rs.getInt("id"));
                calisan.setAd(rs.getString("ad"));
                calisan.setSoyad(rs.getString("soyad"));
                calisan.setDepoid(rs.getInt("depoid"));
                calisan.setDepoadi(rs.getString("depoadi"));
                calisan.setDepoaciklama(rs.getString("depoaciklama"));
                calisanList.add(calisan);
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
        }
        return calisanList;
    }
    
    public void calisanSil(){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("delete from kuldep where id=?");
            ps.setInt(1, id);
            ps.execute();
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
    }
}
