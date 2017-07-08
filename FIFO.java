import java.util.ArrayList;
import java.util.List;

public class FIFO extends  Thread {

    private int lowestFrame;
    private int biggerFrame;
    private int acertos = 0;
    private List<Integer> memory = new ArrayList<>();
    private static final int FIRST = 0;

    FIFO(int lowestFrame, int biggerFrame){
        this.lowestFrame = lowestFrame;
        this.biggerFrame = biggerFrame;
        new Thread(this).start();
    }

    @Override
    public void run() {
        super.run();
        //Algoritmo FIFO
        for (int i=lowestFrame; i<=biggerFrame; i++){
            memory = new ArrayList<>();
            for (int j=0; j<Singleton.getInstance().data.values.size(); j++){
                if (memory.contains(Singleton.getInstance().data.values.get(j))){
                    acertos++;
                }
                else if (i == memory.size()){
                    memory.remove(FIRST);
                    memory.add(Singleton.getInstance().data.values.get(j));
                }
                else {
                    memory.add(Singleton.getInstance().data.values.get(j));
                }
            }
            System.out.println("Acerto em FIFO - "+i+" -> " + acertos);
            acertos = 0;
        }
    }

}
