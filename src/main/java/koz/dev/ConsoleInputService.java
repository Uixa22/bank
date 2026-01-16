package koz.dev;

import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class ConsoleInputService  {
    private Scanner scanner = new Scanner(System.in);
    public String readline(){
        return scanner.nextLine();
    }
    public int readInt(){
        int value=scanner.nextInt();
        scanner.nextLine();
        return value;
    }
}
