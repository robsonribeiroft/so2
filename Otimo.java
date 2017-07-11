import java.util.ArrayList;
import java.util.List;

/**
 * Created by robso on 09/07/2017.
 */
public class Otimo extends Thread {

    private int lowestFrame;
    private int biggerFrame;
    private int acertos = 0;
    private List<Integer> memory;

    public Otimo(int lowestFrame, int biggerFrame){
        this.lowestFrame = lowestFrame;
        this.biggerFrame = biggerFrame;
        new Thread(this).start();
    }

    @Override
    public void run() {
        super.run();
        for (int i=lowestFrame; i<=biggerFrame; i++){
            acertos = 0;
            memory = new ArrayList<>();
            for (int j=0; i<Singleton.getInstance().data.values.size(); i++){
                if (memory.contains(Singleton.getInstance().data.values.get(j)))
                    acertos++;
                else {
                    if (memory.size() < i){
                        memory.add(Singleton.getInstance().data.values.get(j));
                    }
                    else {
                        int goFirst = i+1;
                        int index = 0;
                        for (int l=0; l<memory.size(); l++){
                            for (int k=j; k<Singleton.getInstance().data.values.size(); k++){
                                if (memory.get(l) == Singleton.getInstance().data.values.get(k)){
                                    if (k<goFirst){
                                        goFirst = k;
                                        index = l;
                                    }
                                }
                            }
                        }
                        memory.remove(index);
                        memory.add(Singleton.getInstance().data.values.get(j));
                    }
                }
            }
            System.out.println("Otimo - "+i+" -> " + acertos);
        }
    }
}
