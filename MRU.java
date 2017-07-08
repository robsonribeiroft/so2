import java.util.ArrayList;
import java.util.List;

/**
 * Created by robso on 08/07/2017.
 */
public class MRU extends Thread{

    private int lowestFrame;
    private int biggerFrame;
    private int acertos = 0;
    private List<Integer> memory = new ArrayList<>();

    public MRU(int lowestFrame, int biggerFrame){
        this.lowestFrame = lowestFrame;
        this.biggerFrame = biggerFrame;
        new Thread(this).start();
    }

    @Override
    public void run() {
        super.run();
        for (int i=lowestFrame; i<=biggerFrame; i++){
            for (int j=0; j<Singleton.getInstance().data.values.size(); j++){

            }
        }
    }
}
