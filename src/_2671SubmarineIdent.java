import java.util.Scanner;

public class _2671SubmarineIdent {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.next();

        if(input.matches("(100+1+|01)+"))
            System.out.println("SUBMARINE");
        else
            System.out.println("NOISE");
    }
}
