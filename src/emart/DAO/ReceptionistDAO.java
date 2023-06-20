
package emart.DAO;

import emart.dbutil.DBConnection;
import emart.pojo.ReceptionistPojo;
import emart.pojo.UserPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ReceptionistDAO {
    public static Map<String,String> getNonRegisteredReceptionist()throws SQLException{
        Connection con=DBConnection.getConnection();
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select empid,empname from employees where job='Receptionist' and empid not in(select empid from users)");
        HashMap<String,String> hsh=new HashMap<>();
        while(rs.next()){
           String id=rs.getString(1);
           String name=rs.getString(2);
           hsh.put(id,name);
        }
        return hsh;
    }
    public static boolean addReceptionist(UserPojo user)throws SQLException{
        Connection con=DBConnection.getConnection();
        PreparedStatement p=con.prepareStatement("insert into users values(?,?,?,?,?)");
        p.setString(1,user.getUserid());
        p.setString(2,user.getEmpid());
        p.setString(3,user.getPassword());
        p.setString(4,user.getUsertype());
        p.setString(5,user.getUsername());
        int c=p.executeUpdate();
        return c==1;
    } 
    public static List<ReceptionistPojo> getAllReceptionistDetails()throws SQLException{
        Connection con=DBConnection.getConnection();
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select users.empid,empname,userid,job,salary from employees,users where users.empid=employees.empid and usertype='Receptionist'");
        ArrayList<ReceptionistPojo> list=new ArrayList<>();
        while(rs.next()){
          ReceptionistPojo e=new ReceptionistPojo();
           e.setEmpid(rs.getString(1));
           e.setEmpname(rs.getString(2));
           e.setUserid(rs.getString(3));
           e.setJob(rs.getString(4));
           e.setSalary(rs.getDouble(5));
           list.add(e);
        }
        return list;
    }
    public static Map<String,String> getAllReceptionistId()throws SQLException{
        Connection con=DBConnection.getConnection();
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select userid,username from users where usertype='Receptionist' order by userid ");
        Map<String,String> hsh=new HashMap<>();
        while(rs.next()){
            String id=rs.getString(1);
            String name=rs.getString(2);
            hsh.put(id,name);
        }
        return hsh;
    }
    public static boolean updatePassword(String id,String pwd)throws SQLException{
        Connection con=DBConnection.getConnection();
        PreparedStatement p=con.prepareStatement("update users set password=? where userid=?");
        p.setString(1, pwd);
        p.setString(2,id);
        return p.executeUpdate()==1;
    }
  public static List<String> getAllReceptionistUserId()throws SQLException{
        Connection con=DBConnection.getConnection();
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select userid from users where usertype='Receptionist' order by userid ");
        List<String> list=new ArrayList<>();
        while(rs.next()){
            String id=rs.getString(1);
            list.add(id);
        }
        return list;
    }
  public static boolean deleteReceptionist(String id)throws SQLException{
      Connection con=DBConnection.getConnection();
        PreparedStatement p=con.prepareStatement("delete from users where userid=?");
        p.setString(1, id);
       
        return p.executeUpdate()==1;
  }
}
