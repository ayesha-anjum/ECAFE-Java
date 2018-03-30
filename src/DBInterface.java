import java.sql.*;

public class DBInterface {
    String dbname;
    String username;
    String password;
    Connection conn;

    public DBInterface(String dbname,String username,String password)throws Exception{
        this.dbname=dbname;
        this.username=username;
        this.password=password;
        MakeConnection();
    }

    public void MakeConnection() throws Exception{
        Class.forName("com.mysql.jdbc.Driver"); // Setup the connection with the DB
        this.conn = DriverManager.getConnection("jdbc:mysql://localhost/"+this.dbname, this.username,this.password);

    }

    public ResultSet GetResults(String query)throws Exception{
        Statement statement = this.conn.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        return resultSet;
    }

    public int DeleteItem(String attr,String value,String table)throws Exception{
        PreparedStatement preparedstatement =conn.prepareStatement("delete from "+table+" where "+attr+" = ?");
        preparedstatement.setString(1,value);
        return preparedstatement.executeUpdate();

    }
}
