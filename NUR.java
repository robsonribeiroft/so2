import java.util.ArrayList;
import java.util.List;

/**
 * Created by robso on 11/07/2017.
 */
public class NUR extends Thread {

    private int lowestFrame;
    private int biggerFrame;
    private int acertos = 0;
    private List<Integer> memory = new ArrayList<>();
    private List<Integer> R = new ArrayList<>();
    private List<Integer> M = new ArrayList<>();
    private static final int FIRST = 0;
    private static final int ZERADOR = 30;

    public NUR(int lowestFrame, int biggerFrame){
        this.lowestFrame = lowestFrame;
        this.biggerFrame = biggerFrame;
        new Thread(this).start();
    }

    public void clearBitR(){
        for (int i=0; i<R.size(); i++){
            R.set(i, 0);
        }
    }

    public int biggerClass(int R, int M){
        if (R==1 && M==1)
            return 3;
        if (R==1 && M==0)
            return 2;
        if (R==0 && M==1)
            return 1;
        return 0;
    }

    @Override
    public void run() {
        super.run();
        for (int i=lowestFrame; i<=biggerFrame; i++) {
            acertos = 0;
            memory = new ArrayList<>();
            for (int j = 0; j < Singleton.getInstance().data.values.size(); j++) {
                if (memory.contains(Singleton.getInstance().data.values.get(j))) {
                    for (int k = 0; k < memory.size(); k++) {
                        if (memory.get(k) == Singleton.getInstance().data.values.get(j)) {
                            R.set(k, 1);
                            if (Singleton.getInstance().data.method.get(j) == 'W'){
                                M.set(k, 1);
                            }
                        }
                    }
                    acertos++;
                } else {
                    if (memory.size() < i) {
                        memory.add(Singleton.getInstance().data.values.get(j));
                        R.add(1);
                        M.add(Singleton.getInstance().data.method.get(j) == 'W' ? 1 : 0);
                    } else {
                        int indexLowerClass = 0;
                        for (int k = 0; k < memory.size(); k++) {
                            for (int l = 0; l < memory.size(); l++){
                                if (biggerClass(R.get(l), M.get(l))< biggerClass(R.get(k), M.get(k))){
                                    indexLowerClass = l;
                                    break;
                                }
                            }
                        }
                        memory.remove(indexLowerClass);
                        R.remove(indexLowerClass);
                        M.remove(indexLowerClass);
                        memory.add(Singleton.getInstance().data.values.get(j));
                        R.add(1);
                        M.add(Singleton.getInstance().data.method.get(j) == 'W' ? 1 : 0);
                    }
                }
            }
            System.out.println("NUR - "+i+" -> " + acertos);
        }
    }
}
