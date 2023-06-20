
package emart.DAO;

import emart.dbutil.DBConnection;
import emart.pojo.ProductsPojo;
import emart.pojo.UserProfile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OrderDAO {
    public static String getNextOrderId()throws SQLException{
        Connection con=DBConnection.getConnection();
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select max(order_id) from orders");
        rs.next();
        String oid=rs.getString(1);
        if(oid==null){
        return "O-101";}
        int id=Integer.parseInt(oid.substring(2));
        int ono=id+1;
        return "O-"+ono;
     }
    public static boolean addOrder(ArrayList<ProductsPojo> al,String oid)throws SQLException{
        Connection con=DBConnection.getConnection();
        int count=0;
        PreparedStatement ps=con.prepareStatement("insert into orders values(?,?,?,?)");
        for(ProductsPojo p:al){
            ps.setString(1,oid);
            ps.setString(2,p.getProductId());
            ps.setInt(3,p.getQuantity());
            ps.setString(4,UserProfile.getUserid());
            count=count=ps.executeUpdate();
        }
        return count==al.size();
        
    }
}
