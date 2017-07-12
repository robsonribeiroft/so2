
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DefaultCategoryDataset ds = new DefaultCategoryDataset();

        JWindow window = new JWindow();

        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(window);
        java.io.File file = chooser.getSelectedFile();
        new ReadFile(file);

        int qntMinimaFrames = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira a quantidade de frames inicial"));
        int qntMaximaFrames = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira a quantidade de frames final"));
        int zerador = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira o intervalo de frames"));


        while (!Singleton.getInstance().fileComplete){}

        new Otimo(qntMinimaFrames, qntMaximaFrames);
        new FIFO(qntMinimaFrames, qntMaximaFrames);
        new MRU(qntMinimaFrames, qntMaximaFrames);
        new SecondChance(qntMinimaFrames, qntMaximaFrames, zerador);
        new NUR(qntMinimaFrames, qntMaximaFrames, zerador);

        while (Singleton.getInstance().resultOtimo.size()-1!=qntMaximaFrames-qntMinimaFrames){}


        LineChart chart = new LineChart(60,
                Singleton.getInstance().resultFIFO,
                Singleton.getInstance().resultMRU,
                Singleton.getInstance().resultSecondChance,
                Singleton.getInstance().resultNUR,
                Singleton.getInstance().resultOtimo);
        chart.pack( );
        RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible( true );
        System.out.println("  Frames\tSC\t\tNRU\t\tBEST\tFIFO\tMRU");
        for (int i=0; i<Singleton.getInstance().resultOtimo.size(); i++){
            System.out.println("\t"+qntMinimaFrames+"\t\t"+Singleton.getInstance().resultSecondChance.get(i)+"\t"+Singleton.getInstance().resultNUR.get(i)+"\t"+Singleton.getInstance().resultOtimo.get(i)+"\t"+Singleton.getInstance().resultFIFO.get(i)+"\t"+Singleton.getInstance().resultMRU.get(i));
            qntMinimaFrames++;

        }

    }

}
