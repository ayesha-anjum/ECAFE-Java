import java.util.Scanner;

public class IO {
    static Scanner s=new Scanner(System.in);


    public static int getint(String message){
        print(message+": ");
        s=new Scanner(System.in);
        return s.nextInt();
    }
    public static float getfloat(String message){
        print(message+": ");
        s=new Scanner(System.in);
        return s.nextFloat();
    }
    public static String getstring(String message){
        print(message+": ");
        s=new Scanner(System.in);
        return s.nextLine();
    }

    public static <T> void println(T val){
        System.out.println(val);
    }

    public static <T> void print(T val){
        System.out.print(val);
    }

}

