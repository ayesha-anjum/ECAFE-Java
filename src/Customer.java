

import java.sql.ResultSet;
import java.util.Vector;

//Hassan's Part
public class Customer {
    Vector<CartItem>cart;

    public Customer(){
        cart=new Vector<CartItem>();
    }

    public void Execute()throws Exception{

        GetOrder();
        GenerateBill();
        int pmethod=GetPmethod();
        int dmethod=GetDmethod();
        PlaceOrder(pmethod,dmethod);
        IO.println("Successfully Placed Your Order!");
    }

    private void GetOrder()throws Exception{

        IO.println(String.format("\n%30s", "Place Order"));
        ShowAllItems();

        IO.println("\nEnter -1 to terminate");

        int id=IO.getint("Enter the item ID");
        int icount=0;
        while(id!=-1){
            icount=IO.getint("Enter the Number of items");
            FoodItem tempf=GlobalVars.DBinterface.getFooditem(id);
            if(tempf!=null){
                cart.add(new CartItem(id,tempf.itemname,icount,tempf.price));
                IO.println("Added to Cart");
            }
            else{
                IO.println("No such ID in the database");
            }
            IO.println("\nEnter -1 to terminate");
            id=IO.getint("Enter the item ID");
        }


    }

    private int GetPmethod()throws Exception{
        IO.println(String.format("\n%30s", "Payment Method"));
        Vector<String>pmethods=GlobalVars.DBinterface.getPmethods();

        for(String p:pmethods){
            IO.println(p);
        }
        int choice=IO.getint("\nEnter your payment choice");
        return choice;
    }

    private int GetDmethod() throws Exception{
        IO.println(String.format("\n%30s", "Delivery Method"));
        Vector<String>dmethods=GlobalVars.DBinterface.getDmethods();

        for(String d:dmethods){
            IO.println(d);
        }
        int choice=IO.getint("\nEnter your delivery choice");
        return choice;
    }

    private void PlaceOrder(int pmethod,int dmethod)throws Exception{                                                  //it will place the order from cart
        GlobalVars.DBinterface.InsertOrders(cart,pmethod,dmethod);
    }

    private void GenerateBill(){
        IO.println(String.format("\n%30s", "Order Bill"));
        double sum=0;

        IO.println(String.format("%5s %13s %16s %20s","ItemID","Name","Count","TotalPrice"));

        for(CartItem c:cart){
            IO.println(String.format("%4s %18s %11s %18.2f",c.itemid,c.itemname,c.countval,Double.valueOf(c.price)*Double.valueOf(c.countval)));
            sum+=(Double.valueOf(c.price)*Double.valueOf(c.countval));
        }
        IO.println(String.format("\nTotal Amount to be Paid: $%.2f",sum));
    }

    private void ShowAllItems(){
        try{
            Vector<FoodItem>fooditems= GlobalVars.DBinterface.getFooditems();
            PrintHeading();
            for(FoodItem f:fooditems){
                int id=Integer.valueOf(f.itemid);
                String itemname=f.itemname;
                String desc=f.description;
                double price=Double.valueOf(f.price);
                IO.println(String.format("%4s %20s %11s %20s",id,itemname,price,desc));
            }
        }
        catch(Exception e){
            IO.print(e.toString());
        }

    }

    void PrintHeading(){
        IO.println(String.format("%5s %15s %14s %20s","ItemID","Name","Price","Description"));
    }




}
