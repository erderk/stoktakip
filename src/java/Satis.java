
import database.Baglanti;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
@ManagedBean(name = "satisgecmis", eager = true)
@RequestScoped
public class Satis {
    int depoid,urunid,adet,secilen;
    String satilan,depoadi,urunadi;

    public String getDepoadi() {
        return depoadi;
    }

    public void setDepoadi(String depoadi) {
        this.depoadi = depoadi;
    }

    public String getUrunadi() {
        return urunadi;
    }

    public void setUrunadi(String urunadi) {
        this.urunadi = urunadi;
    }
    Date tarih;

    public int getSecilen() {
        return secilen;
    }

    public void setSecilen(int secilen) {
        this.secilen = secilen;
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

    public String getSatilan() {
        return satilan;
    }

    public void setSatilan(String satilan) {
        this.satilan = satilan;
    }

    public Date getTarih() {
        return tarih;
    }

    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }
    
    public void satisKontrol(String gelendepoid){
        depoid = Integer.parseInt(gelendepoid);
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("update adet set adet = adet-? where depoid =? and urunid=?");
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
    }
    
    public void satis(String gelendepoid){
        depoid = Integer.parseInt(gelendepoid);
        Connection conn = null;
        PreparedStatement ps = null;
        tarih = Date.valueOf(LocalDate.now());
        satisKontrol(gelendepoid);
        try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("insert into satisgecmis(depoid, urunid, adet, satilan, tarih) values(?,?,?,?,?)");
            ps.setInt(1, depoid);
            ps.setInt(2, urunid);
            ps.setInt(3, adet);
            ps.setString(4, satilan);
            ps.setDate(5, tarih);
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
    
    public List<Satis> getSatisListesi(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Satis>satisList=new ArrayList<>();
            try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("select a.depoid,depoadi,a.urunid,urunadi,adet,satilan,tarih from satisgecmis a full join urun b on a.urunid = b.urunid full join depo c on a.depoid = c.depoid where satilan is not null");
            rs = ps.executeQuery();
            while(rs.next()){
                Satis satis = new Satis();
                satis.setDepoid(rs.getInt("depoid"));
                satis.setDepoadi(rs.getString("depoadi"));
                satis.setUrunid(rs.getInt("urunid"));
                satis.setUrunadi(rs.getString("urunadi"));
                satis.setAdet(rs.getInt("adet"));
                satis.setSatilan(rs.getString("satilan"));
                satis.setTarih(rs.getDate("tarih"));
                satisList.add(satis);
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
        return satisList;
    }
    
    public List<Satis> satisList(String gelendepoid){
        setDepoid(Integer.parseInt(gelendepoid));
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Satis>satisList=new ArrayList<>();
            try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("select a.depoid,depoadi,a.urunid,urunadi,adet,satilan,tarih from satisgecmis a full join urun b on a.urunid = b.urunid full join depo c on a.depoid = c.depoid where satilan is not null and a.depoid=?");
            ps.setInt(1, depoid);
            rs = ps.executeQuery();
            while(rs.next()){
                Satis satis = new Satis();
                satis.setDepoid(rs.getInt("depoid"));
                satis.setDepoadi(rs.getString("depoadi"));
                satis.setUrunid(rs.getInt("urunid"));
                satis.setUrunadi(rs.getString("urunadi"));
                satis.setAdet(rs.getInt("adet"));
                satis.setSatilan(rs.getString("satilan"));
                satis.setTarih(rs.getDate("tarih"));
                satisList.add(satis);
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
        return satisList;
    }
}
