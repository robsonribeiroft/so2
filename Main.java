import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        new ReadFile();

        while (!Singleton.getInstance().fileComplete){}
        //new FIFO(5, 8);
        new MRU(5, 8);

    }

}
