import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile extends Thread {

    private static final String FILE_LOCATION = "C:\\Users\\robso\\AndroidStudioProjects\\SistemaOperacionaisII\\src\\REFERENCIAS_100k.txt";
    private File file;

    ReadFile(File file){
        this.file = file;
        new Thread(this).start();
    }

    @Override
    public void run() {
        super.run();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
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
        Singleton.getInstance().fileComplete = true;
    }
}
