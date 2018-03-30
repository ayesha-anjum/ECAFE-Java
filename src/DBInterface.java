import java.sql.*;
import java.util.Vector;


public class DBInterface {
    private String dbname;
    private String username;
    private String password;
    private Connection conn;
    private Vector<FoodItem>fooditems;

    public DBInterface(String dbname,String username,String password)throws Exception{
        this.dbname=dbname;
        this.username=username;
        this.password=password;
        this.fooditems=new Vector<FoodItem>();
        MakeConnection();
    }

    private ResultSet GetResults(String query)throws Exception{
        Statement statement = this.conn.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        return resultSet;
    }

    public int DeleteItem(String attr,String value,String table)throws Exception{
        PreparedStatement preparedstatement =conn.prepareStatement("delete from "+table+" where "+attr+" = ?");
        preparedstatement.setString(1,value);
        return preparedstatement.executeUpdate();
    }

    public void MakeConnection() throws Exception{
        Class.forName("com.mysql.jdbc.Driver"); // Setup the connection with the DB
        this.conn = DriverManager.getConnection("jdbc:mysql://localhost/"+this.dbname, this.username,this.password);
    }
    //will return customer id using user and pass
    public int GetUserID(String user,String pass,String type)throws Exception{               //will return customer id or -1 if noone exists

        ResultSet rs=GetResults("select * from users;");
        while(rs.next()){
            if(rs.getString("username").equals(user) && rs.getString("password").equals(pass) && rs.getString("type").equals(type)){
                return Integer.valueOf(rs.getString("id"));
            }
        }
        return -1;
    }

    //will insert orders to the database
    public void InsertOrders(Vector<CartItem> cartitems, int pmethod,int dmethod)throws Exception{
        Statement st=conn.createStatement();
        String orderid,userid,pmethodid;
        orderid=Integer.toString(GetnewID("orderrepo"));
        for(CartItem f: cartitems){
            String str="Insert into orderdetails values("+orderid+","+f.itemid+","+f.countval+","+f.price+");";
            st.executeUpdate(str);
        }

        //IO.println("Insert into orderrepo values("+orderid+","+Integer.toString(GlobalVars.customerid)+","+Integer.toString(pmethod)+",CURDATE());");

        st.executeUpdate("Insert into orderrepo values("+orderid+","+Integer.toString(GlobalVars.customerid)+","+Integer.toString(pmethod)+",CURDATE());");
        st.executeUpdate("Insert into currentorder values("+orderid+","+Integer.toString(GlobalVars.customerid)+","+Integer.toString(dmethod)+",CURDATE());");


    }

    //will return the id which can be used for new record. e.g. if there are order numbers upto 13, the function will return us 13.
    public int GetnewID(String table)throws Exception{
        ResultSet rs=GetResults("select count(*) from "+table+";");
        while (rs.next()) {
            return rs.getInt(1);
        }
        throw new Exception("Error in assigning new ID.");
    }

    //will return all fooditems currently present in database
    public Vector<FoodItem>getFooditems()throws Exception{
        if(this.fooditems.size()!=0){
            return this.fooditems;
        }
        ResultSet rs=GetResults("select * from fooditems;");
        while(rs.next()){
            int id=Integer.valueOf(rs.getString("itemid"));
            String itemname=rs.getString("itemname");
            String desc=rs.getString("description");
            double price=Double.valueOf(rs.getString("price"));
            this.fooditems.add(new FoodItem(id,itemname,desc,price));
        }
        return this.fooditems;
    }

    //to get fooditem using id
    public FoodItem getFooditem(int id)throws Exception{
        if(this.fooditems.size()==0){
            getFooditems();
        }
        for(FoodItem f:this.fooditems){
            if(f.itemid==id){
                return f;
            }
        }
        return null;
    }

    //to get payment methods
    public Vector<String>getPmethods()throws Exception{
        Vector<String>vec=new Vector<String>();
        ResultSet rs=GetResults("select * from paymentmethods;");
        while(rs.next()){
            vec.add(rs.getString("methodid")+" "+rs.getString("methodname"));
        }
        return vec;
    }

    //to get delivery methods
    public Vector<String>getDmethods()throws Exception{
        Vector<String>vec=new Vector<String>();
        ResultSet rs=GetResults("select * from orderdelivery;");
        while(rs.next()){
            vec.add(rs.getString("id")+" "+rs.getString("name"));
        }
        return vec;
    }
}

