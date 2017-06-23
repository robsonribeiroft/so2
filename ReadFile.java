import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by robso on 23/06/2017.
 */

public class ReadFile extends Thread {

    private static final String FILENAME = "";
    //List<Integer> values = new ArrayList<>();
    //List<Character> method = new ArrayList<>();

    public ReadFile(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
                    //System.out.println("Leitura do arquivo iniciada!");
                    String sCurrentLine;
                    while ((sCurrentLine = br.readLine()) != null) {
                        for (int i=0; i<sCurrentLine.length(); i++){
                            String tmp = "";
                            if (sCurrentLine.charAt(i)>=48 && sCurrentLine.charAt(i)<=57){
                                while (sCurrentLine.charAt(i)>=48 && sCurrentLine.charAt(i)<=57){
                                    tmp += String.valueOf(sCurrentLine.charAt(i));
                                    i++;
                                }
                                Singleton.getInstance().data.method.add(sCurrentLine.charAt(i));
                            }

                            if (!tmp.isEmpty()){
                                Singleton.getInstance().data.values.add(Integer.valueOf(tmp));
                            }
                        }
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
                Singleton.getInstance().readFileComplet = true;
                //System.out.println(Singleton.getInstance().data.values);
                //System.out.println(Singleton.getInstance().data.method);
            }
        }.start();


    }

}
