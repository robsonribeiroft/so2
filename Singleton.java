import java.util.ArrayList;
import java.util.List;

/**
 * Created by robso on 23/06/2017.
 */
public class Singleton {

    public Data data = new Data();
    public boolean fileComplete = false;
    public List<Integer> resultFIFO = new ArrayList<>();
    public List<Integer> resultSecondChance = new ArrayList<>();
    public List<Integer> resultOtimo = new ArrayList<>();
    public List<Integer> resultMRU = new ArrayList<>();
    public List<Integer> resultNUR = new ArrayList<>();

    private static Singleton uniqueInstance;

    private Singleton() {
    }

    public static synchronized Singleton getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new Singleton();

        return uniqueInstance;
    }
}
