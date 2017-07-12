import java.util.ArrayList;
import java.util.List;

/**
 * Created by robso on 11/07/2017.
 */
public class NUR extends Thread {

    private int lowestFrame;
    private int biggerFrame;
    private int zerador;
    private int acertos = 0;
    private List<Integer> memory = new ArrayList<>();
    private List<Integer> R = new ArrayList<>();
    private List<Integer> M = new ArrayList<>();
    private List<Integer> classe = new ArrayList<>();
    private static final int FIRST = 0;

    public NUR(int lowestFrame, int biggerFrame, int zerador){
        this.lowestFrame = lowestFrame;
        this.biggerFrame = biggerFrame;
        this.zerador = zerador;
        new Thread(this).start();
    }

    public void clearBitR(){
        for (int i=0; i<R.size(); i++){
            R.set(i, 0);
            classe.set(i, getClass(0, M.get(i)));
        }
    }

    public int getClass(int R, int M){
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
            R = new ArrayList<>();
            M = new ArrayList<>();
            classe = new ArrayList<>();
            for (int j = 0; j < Singleton.getInstance().data.values.size(); j++) {
                if (j%zerador == 0 && j!=0){
                    clearBitR();
                }
                if (memory.contains(Singleton.getInstance().data.values.get(j))) {
                    for (int k = 0; k < memory.size(); k++) {
                        if (memory.get(k) == Singleton.getInstance().data.values.get(j)) {
                            R.set(k, 1);
                            if (Singleton.getInstance().data.method.get(j) == 'W'){
                                M.set(k, 1);
                            }
                            classe.set(k, getClass(R.get(k),M.get(k)));
                        }
                    }
                    acertos++;
                } else {
                    if (memory.size() < i) {
                        memory.add(Singleton.getInstance().data.values.get(j));
                        R.add(1);
                        M.add(Singleton.getInstance().data.method.get(j) == 'W' ? 1 : 0);
                        classe.add(getClass(1, Singleton.getInstance().data.method.get(j) == 'W' ? 1 : 0));
                    } else {
                        int lowerClass = 3;
                        int indexLowerClass = 0;
                        for (int k = 1; k < classe.size(); k++){
                            if (classe.get(k)<lowerClass){
                                lowerClass = classe.get(k);
                            }
                        }
                        for (int k = 0; k < classe.size(); k++){
                            if (lowerClass == classe.get(k)){
                                indexLowerClass = k;
                                break;
                            }
                        }

                        memory.remove(indexLowerClass);
                        R.remove(indexLowerClass);
                        M.remove(indexLowerClass);
                        classe.remove(indexLowerClass);
                        memory.add(Singleton.getInstance().data.values.get(j));
                        R.add(1);
                        M.add(Singleton.getInstance().data.method.get(j) == 'W' ? 1 : 0);
                        classe.add(getClass(1, Singleton.getInstance().data.method.get(j) == 'W' ? 1:0));
                    }
                }
            }
            //System.out.println("NUR - "+i+" -> " + acertos);
            Singleton.getInstance().resultNUR.add(acertos);
        }
    }
}
