
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ahmet
 */
@ManagedBean(name = "urun", eager = true)
@RequestScoped
public class Urun{
    int urunid;
    String urunadi, marka, model, aciklama;
    double alisfiyat, satisfiyat;
    
    public int getUrunid() {
        return urunid;
    }

    public void setUrunid(int urunid) {
        this.urunid = urunid;
    }

    public String getUrunadi() {
        return urunadi;
    }

    public void setUrunadi(String urunadi) {
        this.urunadi = urunadi;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public double getAlisfiyat() {
        return alisfiyat;
    }

    public void setAlisfiyat(double alisfiyat) {
        this.alisfiyat = alisfiyat;
    }

    public double getSatisfiyat() {
        return satisfiyat;
    }

    public void setSatisfiyat(double satisfiyat) {
        this.satisfiyat = satisfiyat;
    }

    public void urunEkle(){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("insert into urun(urunadi,alisfiyat,satisfiyat,marka,model,aciklama) values(?,?,?,?,?,?)");
            ps.setString(1, urunadi);
            ps.setDouble(2, alisfiyat);
            ps.setDouble(3, satisfiyat);
            ps.setString(4, marka);
            ps.setString(5, model);
            ps.setString(6, aciklama);
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
    
    public void adetEkle(int depoid,int urunid , int adet){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("insert into adet(depoid,urunid,adet) values(?,?,?)");
            ps.setInt(1, depoid);
            ps.setInt(2, urunid);
            ps.setInt(3, adet);
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
    
    public List<Urun> getUrunListesi(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Urun> urunList=new ArrayList<>();
        try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("select * from urun order by urunid asc");
            rs = ps.executeQuery();
            while(rs.next()){
                Urun urun = new Urun();
                urun.setUrunid(rs.getInt(1));
                urun.setUrunadi(rs.getString(2));
                urun.setAlisfiyat(rs.getDouble(3));
                urun.setSatisfiyat(rs.getDouble(4));
                urun.setMarka(rs.getString(5));
                urun.setModel(rs.getString(6));
                urun.setAciklama(rs.getString(7));
                urunList.add(urun);
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
        return urunList;
    }
    
    public List<String> getUrunListe(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> urunList=new ArrayList<>();
        String urun="";
        try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("select * from urun order by urunid asc");
            rs = ps.executeQuery();
            while(rs.next()){
                urun = Integer.toString((rs.getInt("urunid")));
                urun += " " +(rs.getString("urunadi"));
                urunList.add(urun);
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
        return urunList;
    }
    
    public void urunSil(){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("delete from urun where urunid = ?");
            ps.setInt(1, urunid);
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
    
    public void urunAdGuncelle(){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("update urun set urunadi = ? where urunid = ?");
            ps.setString(1, urunadi);
            ps.setInt(2, urunid);
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
    
    public void urunAlisGuncelle(){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("update urun set alisfiyat = ? where urunid = ?");
            ps.setDouble(1, alisfiyat);
            ps.setInt(2, urunid);
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
    public void urunSatisGuncelle(){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("update urun set satisfiyat = ? where urunid = ?");
            ps.setDouble(1, satisfiyat);
            ps.setInt(2, urunid);
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
    public void urunMarkaGuncelle(){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("update urun set marka = ? where urunid = ?");
            ps.setString(1, marka);
            ps.setInt(2, urunid);
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
    public void urunModelGuncelle(){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("update urun set model = ? where urunid = ?");
            ps.setString(1, model);
            ps.setInt(2, urunid);
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
    public void urunAciklamaGuncelle(){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("update urun set aciklama = ? where urunid = ?");
            ps.setString(1, aciklama);
            ps.setInt(2, urunid);
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
    
    public void kategoriEkle(String kategoriadi){
        Connection conn = null;
        PreparedStatement ps = null;
        char a = kategoriadi.charAt(0);
        try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("insert into urunkategori(urunid,kategoriid,dogrula) values(?,?,?)");
            ps.setInt(1, urunid);
            ps.setInt(2, Character.getNumericValue(a));
            ps.setBoolean(3, true);
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
