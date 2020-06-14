
import database.Baglanti;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
@ManagedBean(name = "kuldep", eager = true)
@RequestScoped
public class Kuldep {
    int id, depoid;
    boolean calisiyor;

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

    public boolean isCalisiyor() {
        return calisiyor;
    }

    public void setCalisiyor(boolean calisiyor) {
        this.calisiyor = calisiyor;
    }
    
    public String depoIdGetir(String gelenid){
        String depoid = null;
        id = Integer.parseInt(gelenid);
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("select depoid from kuldep where id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                depoid = Integer.toString(rs.getInt("depoid"));
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
        return depoid;
    }
    
    public String depoGetir(String kullanici){
        String depo = "";
        char a = kullanici.charAt(0);
        id = Character.getNumericValue(a);
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("select * from depo a full join kuldep b on a.depoid = b.depoid where id = ? and calisiyor=true");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                depo = Integer.toString(rs.getInt("depoid"));
                depo += " " + rs.getString("depoadi");
                depo += " " + rs.getString("depoaciklama");
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
        return depo;
    }
}
