import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ReadFile readFile = new ReadFile();

        System.out.print("Entre com a quantidade de páginas da memória(Integer): ");
        int number = scanner.nextInt();

        System.out.print("Numero de páginas: " + number);

    }

}
