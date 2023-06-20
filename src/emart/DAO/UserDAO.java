
package emart.DAO;

import emart.dbutil.DBConnection;
import emart.pojo.UserPojo;
import emart.pojo.UserProfile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class UserDAO {
    public static boolean validateUser(UserPojo user)throws SQLException{
         Connection con=DBConnection.getConnection();
         PreparedStatement p=con.prepareStatement("select * from users where userid=? and password=? and usertype=?");
         p.setString(1,user.getUserid()); 
         p.setString(2,user.getPassword());
         p.setString(3,user.getUsertype());
         ResultSet rs=p.executeQuery();
         if(rs.next()){
             UserProfile.setUsername(rs.getString("username"));
             return true;
         }
         return false;         
    }
    public static boolean isUserPresent(String empid)throws SQLException{
         Connection con=DBConnection.getConnection();
         PreparedStatement p=con.prepareStatement("select 1 from users where empid=?");
         p.setString(1, empid);
         ResultSet rs=p.executeQuery();
         return rs.next();
    }
}
