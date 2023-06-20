
package emart.pojo;

public class UserPojo {
    private String userid;
    private String empid;
    private String password;
    private String usertype;
    private String username;
    @Override
    public String toString() {
        return "UserPojo{" + "userid=" + userid + ", empid=" + empid + ", password=" + password + ", usertype=" + usertype + ", username=" + username + '}';
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    

    public String getUserid() {
        return userid;
    }

    public String getEmpid() {
        return empid;
    }

    public String getPassword() {
        return password;
    }

    public String getUsertype() {
        return usertype;
    }

    public String getUsername() {
        return username;
    }
    
}
