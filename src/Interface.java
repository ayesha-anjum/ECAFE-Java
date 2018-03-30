import javafx.util.Pair;

public class Interface {

    public static void main(String []args){
        try{

            String type=UsersLogin();
            do{
                Pair p=LoginMenu();
                String username=p.getKey().toString();
                String pass=p.getValue().toString();
                GlobalVars.initialize(username,pass,type);
                if(GlobalVars.customerid==-1){
                    IO.println("Password/username is wrong or No such user exists!");
                }
            }while(GlobalVars.customerid==-1);
            //pass username and pass to initialize function to get userid

            if(type.equals("customer")){
                GlobalVars.customer.Execute();
            }
            else if(type.equals("admin")){
                GlobalVars.admin.Execute();//to be implemented
            }
            else if(type.equals("staff")){
                GlobalVars.staff.Execute();//to be implemented
            }

        }
        catch(Exception e){
            IO.print(e.toString());
        }

    }

    private static Pair LoginMenu(){
        IO.println(String.format("\n%20s", "LOGIN"));
        IO.println("");
        return(new Pair(IO.getstring("Username"),IO.getstring("Password")));
    }

    private static String UsersLogin(){
        IO.println(String.format("\n%20s", "Users"));
        IO.println("\n1. Customer");
        IO.println("2. Admin");
        IO.println("3. Staff");

        boolean flag=false;
        do {
            switch (IO.getint("Enter your choice")) {
                case 1:
                    return "customer";
                case 2:
                    return "admin";
                case 3:
                    return "staff";
                default:
                    IO.println("Wrong choice.");
            }
        }while(true);

    }

}
