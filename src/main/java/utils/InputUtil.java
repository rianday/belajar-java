package utils;

import java.util.Scanner;

public class InputUtil {

    private static Scanner scanner = new Scanner(System.in);

    public static String input(String ket) {
        System.out.print(ket + " : ");
        String input = scanner.nextLine();
        
        return input;
    }
}
