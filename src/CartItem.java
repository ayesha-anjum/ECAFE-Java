public class CartItem {
    public int itemid;
    public String itemname;
    public int countval;
    public double price;

    public CartItem(int itemid,String itemname,int countval, double price){
        this.itemid=itemid;
        this.itemname=itemname;
        this.countval=countval;
        this.price=price;
    }
}
