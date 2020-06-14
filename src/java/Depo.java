
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
@ManagedBean(name = "depo", eager = true)
@RequestScoped
public class Depo{
    int depoid;
    String depoadi;
    String depoaciklama;

    public String getDepoaciklama() {
        return depoaciklama;
    }

    public void setDepoaciklama(String depoaciklama) {
        this.depoaciklama = depoaciklama;
    }
    
    public int getDepoid() {
        return depoid;
    }

    public void setDepoid(int depoid) {
        this.depoid = depoid;
    }

    public String getDepoadi() {
        return depoadi;
    }

    public void setDepoadi(String depoadi) {
        this.depoadi = depoadi;
    }
    
    public void depoEkle(){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("insert into depo(depoadi,depoaciklama) values(?, ?)");
            ps.setString(1, depoadi);
            ps.setString(2, depoaciklama);
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
    
    public List<String> depoListe(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String depo;
        List<String> depoList;
        depoList=new ArrayList<>();
        try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("select * from depo order by depoid asc");
            rs = ps.executeQuery();
            while(rs.next()){
                depo = Integer.toString(rs.getInt("depoid"));
                depo += " "+ rs.getString("depoadi");
                depoList.add(depo);
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
        return depoList;
    }
    
    public List<Depo> getDepoListesi(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Depo> depoList;
        depoList=new ArrayList<>();
        try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("select * from depo order by depoid asc");
            rs = ps.executeQuery();
            while(rs.next()){
                Depo depo = new Depo();
                depo.setDepoid(rs.getInt(1));
                depo.setDepoadi(rs.getString(2));
                depo.setDepoaciklama(rs.getString(3));
                depoList.add(depo);
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
        return depoList;
    }
    
    public void depoSil(){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("delete from depo where depoid = ?");
            ps.setInt(1, depoid);
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
    
    public void depoAdGuncelle(){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("update depo set depoadi = ? where depoid = ?");
            ps.setString(1, depoadi);
            ps.setInt(2, depoid);
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
    
    public void depoAciklamaGuncelle(){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("update depo set depoaciklama = ? where depoid = ?");
            ps.setString(1, depoaciklama);
            ps.setInt(2, depoid);
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
    
    public void depoCalisanEkle(int userid){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("insert into kuldep(depoid,id,calisiyor) values(?, ?, ?)");
            ps.setInt(1, depoid);
            ps.setInt(2, userid);
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
    
    public void depoCalisanSil(int userid){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("delete from kuldep where depoid=? and id=?");
            ps.setInt(1, depoid);
            ps.setInt(2, userid);
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
    
    public void depoIdSeç(String gelen){
        setDepoid(Integer.parseInt(gelen));
    }
}
