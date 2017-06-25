import java.util.Scanner;

public class Main {

    private static int qntPaginas = 0;
    private static int qntFrames = 0;
    private static int rangeFrames = 0;
    private static int FIRST = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int qntAcertos = 0;
        // ReadFile readFile = new ReadFile();

        System.out.print("Entre com a quantidade de frames: ");
        qntFrames = scanner.nextInt();

        System.out.print("Entre com o range de frames: ");
        rangeFrames = scanner.nextInt();

        new CreateRandomFile(qntFrames, rangeFrames);

        System.out.print("Entre com a quantidade de páginas da memória(Integer): ");
        qntPaginas = scanner.nextInt();

        while (!Singleton.getInstance().fileComplete){}

        System.out.println("Iniciando o FIFO:\nQnt de páginas: "+ qntPaginas + "\nQnt de frames: " + qntFrames);

        for (int i=0; i<Singleton.getInstance().data.values.size(); i++){
            if (Singleton.getInstance().memory.contains(Singleton.getInstance().data.values.get(i))){
                qntAcertos++;
            }
            else if (qntPaginas == Singleton.getInstance().memory.size()){
                Singleton.getInstance().memory.remove(FIRST);
                Singleton.getInstance().memory.add(Singleton.getInstance().data.values.get(i));
            }
            else {
                Singleton.getInstance().memory.add(Singleton.getInstance().data.values.get(i));
            }
        }

        System.out.println("Acerto em FIFO: " + qntAcertos);
        //System.out.println(Singleton.getInstance().data.values);

    }

}
