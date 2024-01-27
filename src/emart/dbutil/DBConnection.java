package emart.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class DBConnection {
    private static Connection con=null;
    static{
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            con=DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-1UVKDN7U:1521/XE","grocery","grocery");
            JOptionPane.showMessageDialog(null,"Connection opened successfully","success",JOptionPane.INFORMATION_MESSAGE);
            
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error in opening connection","DB Error",JOptionPane.INFORMATION_MESSAGE);
            e.printStackTrace();
            System.exit(1);
            
        }
        catch(ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null, "Error is loading the driver","Driver Error",JOptionPane.INFORMATION_MESSAGE);
            ex.printStackTrace();
            System.exit(1);
        }
        
    }
    public static Connection getConnection(){
        return con;
    }
    public static void close(){
       try{ 
           con.close();
           JOptionPane.showMessageDialog(null,"Connection closed","success",JOptionPane.INFORMATION_MESSAGE );
       }
       catch(SQLException ex){
           JOptionPane.showMessageDialog(null,"Error in closing the connection","DB error!",JOptionPane.ERROR_MESSAGE);
          ex.printStackTrace();
       
       }
    }
}
