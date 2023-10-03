import java.util.Scanner;

public class Input {
    private Scanner console;

    Input() {
        console = new Scanner(System.in);
    }

    public int acceptIntegerInput(String input) {
        System.out.println(input);
        return Integer.parseInt(console.nextLine());
    }

    public String acceptStringInput(String input) {
        System.out.println(input);
        return console.nextLine();
    }
}
