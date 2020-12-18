/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login2222;


import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class mysqlConnect { 
    Connection conn= null;
    
    public static Connection connectDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/login2222","root","");
            JOptionPane.showMessageDialog(null, "Connected");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null ; 
    }
    
    
}
