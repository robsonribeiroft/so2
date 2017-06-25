import java.util.ArrayList;
import java.util.List;

/**
 * Created by robso on 23/06/2017.
 */
public class Singleton {

    public Data data = new Data();
    public List<Integer> memory = new ArrayList<>();
    public boolean fileComplete = false;

    private static Singleton uniqueInstance;

    private Singleton() {
    }

    public static synchronized Singleton getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new Singleton();

        return uniqueInstance;
    }
}
