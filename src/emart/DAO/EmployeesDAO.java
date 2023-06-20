
package emart.DAO;

import emart.dbutil.DBConnection;
import emart.pojo.EmployeePojo;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmployeesDAO {
    public static String getNextEmpId()throws SQLException{
        Connection con=DBConnection.getConnection();
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select max(empid) from employees");
        rs.next();
        String empid=rs.getString(1);
        int id=Integer.parseInt(empid.substring(1));
        int eid=id+1;
        return "E"+eid;
     }
    public static boolean addEmployee(EmployeePojo emp)throws SQLException{
        Connection con=DBConnection.getConnection();
        PreparedStatement p=con.prepareStatement("insert into employees values(?,?,?,?)"); 
        p.setString(1,emp.getEmpid());
        p.setString(2,emp.getEmpname());
        p.setString(3,emp.getJob());
        p.setDouble(4,emp.getSalary());
        int c=p.executeUpdate();
        return c==1;
        }
    public static List<EmployeePojo> getAllEmployees()throws SQLException{
        Connection con=DBConnection.getConnection();
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("Select * from employees order by empid");
        ArrayList<EmployeePojo> empList=new ArrayList<>();
        while(rs.next()){
            EmployeePojo emp=new EmployeePojo();
            emp.setEmpid(rs.getString(1));
            emp.setEmpname(rs.getString(2));
            emp.setJob(rs.getString(3));
            emp.setSalary(rs.getDouble(4));
            empList.add(emp);
        }
        return empList;
    }
    public static List<String> getALLEmpId()throws SQLException{
        Connection con=DBConnection.getConnection();
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("Select empid from employees order by empid");
        ArrayList<String> list=new ArrayList<>();
        while(rs.next()){
           list.add(rs.getString(1));
        }
        return list;
    }
    public static EmployeePojo getEmployeeById(String id)throws SQLException{
        Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("select * from employees where empid=?");
        ps.setString(1,id);
        ResultSet rs=ps.executeQuery();
        EmployeePojo emp=new EmployeePojo();
        if(rs.next())
        { emp.setEmpid(id);
            emp.setEmpname(rs.getString(2));
            emp.setJob(rs.getString(3));
            emp.setSalary(rs.getDouble(4));}
        
        return emp;
    }
    public static boolean updateEmployee(EmployeePojo emp)throws SQLException{
        Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("update employees set empname=?,job=?,salary=? where empid=?");
        ps.setString(1,emp.getEmpname());
        ps.setString(2,emp.getJob());
        ps.setDouble(3,emp.getSalary());
        ps.setString(4,emp.getEmpid());
        int c=ps.executeUpdate();
        if(c==0)
            return false;
        else{
        boolean result=UserDAO.isUserPresent(emp.getEmpid());
        if(result==false)
            return true;
        ps=con.prepareStatement("update users set username=?,usertype=? where emid=?");
        ps.setString(1,emp.getEmpname());
        ps.setString(2,emp.getJob());
        ps.setString(3,emp.getEmpid());
        int y=ps.executeUpdate();
        return y==1;
        }
    }
    public static boolean deleteEmployee(String empid)throws SQLException{
        Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("delete from employees where empid=?");
        ps.setString(1,empid);
        int c=ps.executeUpdate();
        return c==1;
    }
}
