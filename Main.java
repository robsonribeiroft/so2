import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String FILENAME = "";


    public static void main(String[] args) {
        List<Integer> values = new ArrayList<>();
        List<Character> method = new ArrayList<>();
        int currentChar;
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                for (int i=0; i<sCurrentLine.length(); i++){
                    String tmp = "";
                    if (sCurrentLine.charAt(i)>=48 && sCurrentLine.charAt(i)<=57){
                        while (sCurrentLine.charAt(i)>=48 && sCurrentLine.charAt(i)<=57){
                            tmp += String.valueOf(sCurrentLine.charAt(i));
                            i++;
                        }
                        method.add(sCurrentLine.charAt(i));
                    }

                    if (!tmp.isEmpty()){
                        values.add(Integer.valueOf(tmp));
                    }

                }
            }



            System.out.println(values);
            System.out.println(method);



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
