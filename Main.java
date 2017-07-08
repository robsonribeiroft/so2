import java.util.Scanner;

public class Main {

    private static int qntFrames = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //ReadFile readFile = new ReadFile();
        new ReadFile();

        //System.out.print("Entre com a quantidade de frames: ");
        //qntFrames = scanner.nextInt();


        while (!Singleton.getInstance().fileComplete){}
        new FIFO(5, 8);

    }

}
