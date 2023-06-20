
package emart.DAO;

import emart.dbutil.DBConnection;
import emart.pojo.ProductsPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public static String getNextProductId()throws SQLException{
        Connection con=DBConnection.getConnection();
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select max(p_id) from products");
        rs.next();
        String pid=rs.getString(1);
        if(pid==null){
        return "P101";}
        int id=Integer.parseInt(pid.substring(1));
        int poid=id+1;
        return "P"+poid;
     }
    public static boolean addProducts(ProductsPojo pro)throws SQLException{
         Connection con=DBConnection.getConnection();
        PreparedStatement p=con.prepareStatement("insert into products values(?,?,?,?,?,?,?,'Y')");
        p.setString(1,pro.getProductId());
        p.setString(2,pro.getProductName());
        p.setString(3,pro.getProductCompany());
        p.setDouble(4,pro.getProductPrice());
        p.setDouble(5,pro.getOurPrice());
        p.setInt(6,pro.getTax());
        p.setInt(7,pro.getQuantity());
        return p.executeUpdate()==1;
    }
    public static List<ProductsPojo> getProductDetails()throws SQLException{
       Connection con=DBConnection.getConnection();
       Statement stmt=con.createStatement();
       ResultSet rs=stmt.executeQuery("select * from products where status='Y'");
       List<ProductsPojo> list=new ArrayList<>();
       while(rs.next()){
           ProductsPojo pro=new ProductsPojo();
           pro.setProductId(rs.getString(1));
           pro.setProductName(rs.getString(2));
           pro.setProductCompany(rs.getString(3));
           pro.setProductPrice(rs.getDouble(4));
           pro.setOurPrice(rs.getDouble(5));
           pro.setTax(rs.getInt(6));
           pro.setQuantity(rs.getInt(7));
           list.add(pro);
       }
       return list;
    } 
    public static boolean deleteProduct(String id)throws SQLException{
       Connection con=DBConnection.getConnection();
       PreparedStatement p=con.prepareStatement("update products set status='N' where p_id=?");
       p.setString(1,id);
       return p.executeUpdate()==1;
    }
    public static boolean updateProduct(ProductsPojo pro)throws SQLException{
        Connection con=DBConnection.getConnection();
       PreparedStatement p=con.prepareStatement("update products set p_name=?,p_companyname=?,p_price=?,our_price=?,p_tax=?,quantity=? where p_id=?");
        p.setString(1,pro.getProductName());
        p.setString(2,pro.getProductCompany());
        p.setDouble(3,pro.getProductPrice());
        p.setDouble(4,pro.getOurPrice());
        p.setInt(5,pro.getTax());
        p.setInt(6,pro.getQuantity());
        p.setString(7,pro.getProductId());
        return p.executeUpdate()==1;
    }
     public static List<ProductsPojo> getAllProductDetails()throws SQLException{
        Connection con=DBConnection.getConnection();
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select p_id,p_name,p_companyname,p_price,our_price,p_tax,quantity from products where status='Y' order by p_id");
        ArrayList<ProductsPojo> list=new ArrayList<>();
        while(rs.next()){
          ProductsPojo pro=new ProductsPojo();
           pro.setProductId(rs.getString(1));
           pro.setProductName(rs.getString(2));
           pro.setProductCompany(rs.getString(3));
           pro.setProductPrice(rs.getDouble(4));
           pro.setOurPrice(rs.getDouble(5));
           pro.setTax(rs.getInt(6));
           pro.setQuantity(rs.getInt(7));
           list.add(pro);
        }
        return list;
    }
     public static ProductsPojo getProductDetails(String id)throws SQLException{
         Connection con=DBConnection.getConnection();
         PreparedStatement p=con.prepareStatement("select * from products where p_id=? and status='Y'");
         p.setString(1,id);
         ResultSet rs=p.executeQuery();
         ProductsPojo pro=new ProductsPojo();
         if(rs.next()){
         pro.setProductId(id);
         pro.setProductName(rs.getString(2));
         pro.setProductCompany(rs.getString(3));
         pro.setProductPrice(Double.parseDouble(rs.getString(4)));
         pro.setOurPrice(Double.parseDouble(rs.getString(5)));
         pro.setTax(Integer.parseInt(rs.getString(6)));
         pro.setQuantity(Integer.parseInt(rs.getString(7)));
         }
         return pro;
       } 
     public static boolean updateQuantity(ArrayList<ProductsPojo> al)throws SQLException{
         Connection con=DBConnection.getConnection();
         int x=0;
         PreparedStatement p=con.prepareStatement("update products set quantity=quantity-? where p_id=?");
         for(ProductsPojo pro:al){
         p.setInt(1,pro.getQuantity());
         p.setString(2,pro.getProductId());
         int rows=p.executeUpdate();
         if(rows!=0)
             x++;
         }
         return x==al.size();
     }
     
}
