import java.util.ArrayList;
import java.util.List;

/**
 * Created by robso on 08/07/2017.
 */
public class SecondChance extends Thread {

    private int lowestFrame;
    private int biggerFrame;
    private int acertos = 0;
    private List<Integer> memory = new ArrayList<>();
    private List<Integer> R = new ArrayList<>();
    private static final int FIRST = 0;
    private static final int ZERADOR = 30;

    public SecondChance(int lowestFrame, int biggerFrame){
        this.lowestFrame = lowestFrame;
        this.biggerFrame = biggerFrame;
        new Thread(this).start();
    }

    public void clearBitR(){
        for (int i=0; i<R.size(); i++){
            R.set(i, 0);
        }
    }

    @Override
    public void run() {
        super.run();
        for (int i=lowestFrame; i<=biggerFrame; i++){
            acertos = 0;
            memory = new ArrayList<>();
            for (int j=0; j<Singleton.getInstance().data.values.size(); j++){
                if (j%ZERADOR == 0){
                    clearBitR();
                }
                if (memory.contains(Singleton.getInstance().data.values.get(j))){
                    for (int k=0; k<memory.size(); k++)
                        if (memory.get(k) == Singleton.getInstance().data.values.get(j)) R.set(k, 1);
                    acertos++;
                }
                else {
                    if (memory.size() < i){
                        memory.add(Singleton.getInstance().data.values.get(j));
                        R.add(1);
                    } else {

                        while (R.get(FIRST) != 0){
                            R.remove(0);
                            int carry = memory.get(0);
                            memory.remove(0);
                            memory.add(carry);
                            R.add(0);
                        }

                        memory.remove(0);
                        R.remove(0);
                        memory.add(Singleton.getInstance().data.values.get(j));
                        R.add(1);
                    }
                }
            }
            System.out.println("SecondChance - "+i+" -> " + acertos);

        }
    }
}
