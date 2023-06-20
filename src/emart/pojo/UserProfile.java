
package emart.pojo;


public class UserProfile {
    private static String username;
    private static String usertype;
    private static String userid;

    public static String getUsername() {
        return username;
    }

    public static String getUsertype() {
        return usertype;
    }

    public static String getUserid() {
        return userid;
    }

    public static void setUsername(String username) {
        UserProfile.username = username;
    }

    public static void setUsertype(String usertype) {
        UserProfile.usertype = usertype;
    }

    public static void setUserid(String userid) {
        UserProfile.userid = userid;
    }
    
}
