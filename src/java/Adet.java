
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ahmet
 */
@ManagedBean(name = "adet", eager = true)
@RequestScoped
public class Adet {
    int depoid,urunid,adet,secilen;
    String urunadi, marka, model, aciklama;
    double alisfiyat, satisfiyat;

    public int getSecilen() {
        return secilen;
    }

    public void setSecilen(int secilen) {
        this.secilen = secilen;
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

    public int getDepoid() {
        return depoid;
    }

    public void setDepoid(int depoid) {
        this.depoid = depoid;
    }

    public int getUrunid() {
        return urunid;
    }

    public void setUrunid(int urunid) {
        this.urunid = urunid;
    }

    public int getAdet() {
        return adet;
    }

    public void setAdet(int adet) {
        this.adet = adet;
    }
    
    public boolean adetKontrol(String gelendepoid){
        boolean varmi = false;
        depoid = Integer.parseInt(gelendepoid);
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("select adet from adet where depoid =? and urunid=?");
            ps.setInt(1, depoid);
            ps.setInt(2, urunid);
            rs = ps.executeQuery();
            if(rs.next()){
                varmi = true;
            }else
                varmi = false;
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
        return varmi;
    }
    
    public void adetEkle(String gelendepoid){
        depoid = Integer.parseInt(gelendepoid);
        Connection conn = null;
        PreparedStatement ps = null;
        if(adetKontrol(gelendepoid)==true){
            try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("update adet set adet = adet+? where depoid=? and urunid=?");
            ps.setInt(1, adet);
            ps.setInt(2, depoid);
            ps.setInt(3, urunid);
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
        }else{
            try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("insert into adet(depoid, urunid, adet) values(?, ?, ?)");
            ps.setInt(1, depoid);
            ps.setInt(2, urunid);
            ps.setInt(3, adet);
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
        }
    }
    
    public List<Adet> getAdetListesi(String gelendepoid){
        depoid = Integer.parseInt(gelendepoid);
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Adet>adetList=new ArrayList<>();
        if(secilen==1){
            try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("select b.urunid,urunadi,alisfiyat,satisfiyat,marka,model,aciklama,adet from urun a full join adet b on a.urunid = b.urunid where depoid=? and urunadi=?");
            ps.setInt(1, depoid);
            ps.setString(2, urunadi);
            rs = ps.executeQuery();
            while(rs.next()){
                Adet adet = new Adet();
                adet.setUrunid(rs.getInt("urunid"));
                adet.setUrunadi(rs.getString("urunadi"));
                adet.setAlisfiyat(rs.getDouble("alisfiyat"));
                adet.setSatisfiyat(rs.getDouble("satisfiyat"));
                adet.setMarka(rs.getString("marka"));
                adet.setModel(rs.getString("model"));
                adet.setAciklama(rs.getString("aciklama"));
                adet.setAdet(rs.getInt("adet"));
                adetList.add(adet);
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
        else{    
        try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("select b.urunid,urunadi,alisfiyat,satisfiyat,marka,model,aciklama,adet from urun a full join adet b on a.urunid = b.urunid where depoid=?");
            ps.setInt(1, depoid);
            rs = ps.executeQuery();
            while(rs.next()){
                Adet adet = new Adet();
                adet.setUrunid(rs.getInt("urunid"));
                adet.setUrunadi(rs.getString("urunadi"));
                adet.setAlisfiyat(rs.getDouble("alisfiyat"));
                adet.setSatisfiyat(rs.getDouble("satisfiyat"));
                adet.setMarka(rs.getString("marka"));
                adet.setModel(rs.getString("model"));
                adet.setAciklama(rs.getString("aciklama"));
                adet.setAdet(rs.getInt("adet"));
                adetList.add(adet);
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
        return adetList;
    }
    
    public void urunSil(String gelendepoid){
        depoid = Integer.parseInt(gelendepoid);
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("delete from adet where depoid = ? and urunid=?");
            ps.setInt(1, depoid);
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
    
    public void adetGuncelle(String gelendepoid){
        depoid = Integer.parseInt(gelendepoid);
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("update adet set adet = ? where depoid = ? and urunid=?");
            ps.setInt(1, adet);
            ps.setInt(2, depoid);
            ps.setInt(3, urunid);
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
    
    public void aramaSec(int sec){
        if(sec==1){
            setSecilen(1);
        }
        else{
            setSecilen(0);
        }
    }
}
