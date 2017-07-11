import java.util.ArrayList;
import java.util.List;

public class MRU extends Thread{

    private int lowestFrame;
    private int biggerFrame;
    private static final int FIRST = 0;

    public MRU(int lowestFrame, int biggerFrame){
        this.lowestFrame = lowestFrame;
        this.biggerFrame = biggerFrame;
        new Thread(this).start();
    }

    @Override
    public void run() {
        super.run();
        //Algoritmo MRU
        for (int i=lowestFrame; i<=biggerFrame; i++){
            int acertos = 0;
            List<Integer> memory = new ArrayList<>();
            for (int j=0; j<Singleton.getInstance().data.values.size(); j++){
                if (memory.contains(Singleton.getInstance().data.values.get(j))){
                    memory.remove(Singleton.getInstance().data.values.get(j));
                    memory.add(Singleton.getInstance().data.values.get(j));
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
            System.out.println("MRU - "+i+" -> " + acertos);
        }
    }
}
