public class GlobalVars {
    public static Interface uinterface;
    public static Customer customer;
    public static Admin admin;
    public static Staff staff;
    public static DBInterface DB;


    public static void initialize()throws Exception{
        uinterface=new Interface();
        customer=new Customer();
        admin=new Admin();
        staff=new Staff();
        DB=new DBInterface("ecafe","root","2851996");

    }
}
