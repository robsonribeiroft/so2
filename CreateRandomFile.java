import java.util.Random;

/**
 * Created by robso on 24/06/2017.
 */
public class CreateRandomFile extends Thread {

    public CreateRandomFile(int qntRandom, int range){
        new Thread(){
            @Override
            public void run() {
                super.run();
                Random random = new Random();
                for (int i=0; i<qntRandom; i++){
                    Singleton.getInstance().data.values.add(random.nextInt(range));
                    Singleton.getInstance().data.method.add(random.nextInt(2)==1 ? 'W' : 'R');
                }
                Singleton.getInstance().fileComplete = true;
            }
        }.start();
    }


}
