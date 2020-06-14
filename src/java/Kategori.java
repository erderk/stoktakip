
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
@ManagedBean(name = "kategori", eager = true)
@RequestScoped
public class Kategori {
    int kategoriid;
    String kategoriadi;

    public int getKategoriid() {
        return kategoriid;
    }

    public void setKategoriid(int kategoriid) {
        this.kategoriid = kategoriid;
    }

    public String getKategoriadi() {
        return kategoriadi;
    }

    public void setKategoriadi(String kategoriadi) {
        this.kategoriadi = kategoriadi;
    }
    
    public List<String> getKategoriListesi(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> kategoriListesi = new ArrayList<>();
        String kategori;
        try{
            conn = Baglanti.getConnection();
            ps = conn.prepareStatement("select * from kategori order by kategoriid asc");
            rs = ps.executeQuery();
            while(rs.next()){
                kategori = Integer.toString(rs.getInt("kategoriid"));
                kategori += " "+ rs.getString("kategoriadi");
                kategoriListesi.add(kategori);
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
        return kategoriListesi;
    }
}
