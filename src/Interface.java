public class Interface {
    public static void main(String []args){
        try{
            GlobalVars.initialize();
            LoginMenu();

        }
        catch(Exception e){
            LoginMenu();
        }

    }
    //add staff functions for getting input and printing menu and output


    //add admin functions for getting input and printing menu and output
    static void LoginMenu(){
        System.out.printf("\n%20s", "LOGIN");
        System.out.println();
        System.out.printf("\n%s %3s\n", "1.","User");
        System.out.printf("%s %3s\n", "2.","Registration");
    }

}
