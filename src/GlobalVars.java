import java.sql.ResultSet;
import java.util.Vector;

public class GlobalVars {
    public static Interface uinterface;
    public static Customer customer;
    public static Admin admin;
    public static Staff staff;
    public static DBInterface DBinterface;
    public static int customerid;


    public static void initialize(String username,String password,String type)throws Exception{
        GlobalVars.uinterface=new Interface();
        GlobalVars.customer=new Customer();
        GlobalVars.admin=new Admin();
        GlobalVars.staff=new Staff();
        GlobalVars.DBinterface=new DBInterface("ecafe","root","2851996");
        GlobalVars.customerid=DBinterface.GetUserID(username,password,type);

    }
}
