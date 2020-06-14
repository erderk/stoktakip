/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ahmet
 */
public class Baglanti {
    public static Connection getConnection(){
        Connection conn = null;
        try{
        Class.forName("org.postgresql.Driver");
        conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","1234");
        }catch(Exception e){
            System.out.println(e);
        }
        return conn;
    }
}
