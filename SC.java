import java.util.ArrayList;
import java.util.List;

/**
 * Created by robso on 08/07/2017.
 */
public class SC extends Thread {

    private int lowestFrame;
    private int biggerFrame;
    private int acertos = 0;
    private List<Integer> memory = new ArrayList<>();
    private List<Integer> R = new ArrayList<>();
    private static final int FIRST = 0;
    private static final int ZERADOR = 30;

    public SC(int lowestFrame, int biggerFrame){
        this.lowestFrame = lowestFrame;
        this.biggerFrame = biggerFrame;
        new Thread(this).start();
    }

    @Override
    public void run() {
        super.run();
        for (int i=lowestFrame; i<=biggerFrame; i++){
            memory = new ArrayList<>();
            for (int j=0; j<Singleton.getInstance().data.values.size(); j++){
                if (j%ZERADOR == 0 && j!=0){
                    for (int k=0; k<R.size(); k++){
                        R.set(k, 0);
                    }
                }
                if ()
            }
            System.out.println("SC - "+i+" -> " + acertos);
            acertos = 0;
        }
    }
}
