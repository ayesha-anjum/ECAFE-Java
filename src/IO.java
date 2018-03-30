import java.util.Scanner;

public class IO {
    static Scanner s=new Scanner(System.in);
    public static int getint(String message){
        try{
            print(message+": ");
            s=new Scanner(System.in);
            return s.nextInt();
        }
        catch(Exception e){
            println(e.toString());
            throw e;
        }

    }
    public static double getdouble(String message){
        try{
            print(message+": ");
            s=new Scanner(System.in);
            return s.nextDouble();

        }
        catch(Exception e){
            println(e.toString());
            throw e;
        }
    }
    public static String getstring(String message){
        try{
            print(message+": ");
            s=new Scanner(System.in);
            return s.nextLine();
        }
        catch(Exception e){
            println(e.toString());
            throw e;
        }

    }

    public static <T> void println(T val){
        System.out.println(val);
    }

    public static <T> void print(T val){
        System.out.print(val);
    }

}

