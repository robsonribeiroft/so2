import java.awt.Color;
import java.awt.BasicStroke;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.ApplicationFrame;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import java.util.List;

public class LineChart extends ApplicationFrame {

    public LineChart(int startFrame, List<Integer> hintsFIFO, List<Integer> hintsMRU, List<Integer> hintsSecondChance, List<Integer> hintsNUR, List<Integer> hintsBest) {
        super("Gerenciamento de memória");

        JFreeChart xylineChart = createPanel(startFrame, hintsFIFO, hintsMRU, hintsSecondChance, hintsNUR, hintsBest);

        ChartPanel chartPanel = new ChartPanel( xylineChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 1200 , 700 ) );
        final XYPlot plot = xylineChart.getXYPlot( );

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
        renderer.setSeriesPaint( 0 , Color.RED );
        renderer.setSeriesPaint( 1 , Color.GREEN );
        renderer.setSeriesPaint( 2 , Color.YELLOW );
        renderer.setSeriesPaint( 3 , Color.BLUE );
        renderer.setSeriesPaint(4, Color.black);
        renderer.setSeriesStroke( 0 , new BasicStroke( 1.0f ) );
        renderer.setSeriesStroke( 1 , new BasicStroke( 1.0f ) );
        renderer.setSeriesStroke( 2 , new BasicStroke( 1.0f ) );
        renderer.setSeriesStroke( 3 , new BasicStroke( 1.0f ) );
        renderer.setSeriesStroke( 4 , new BasicStroke( 1.0f ) );
        plot.setRenderer( renderer );
        setContentPane( chartPanel );
    }

    private JFreeChart createPanel(int startFrame, List<Integer> hintsFIFO, List<Integer> hintsMRU, List<Integer> hintsSecondChance, List<Integer> hintsNUR, List<Integer> hintsBest){
        JFreeChart jfreechart = ChartFactory.createScatterPlot(
                "Frames x Acertos", "Frames", "Acertos", createDataset(startFrame, hintsFIFO, hintsMRU, hintsSecondChance, hintsNUR, hintsBest),
                PlotOrientation.VERTICAL, true, true, false);

        double tickUnit = 1000.0;

        if (hintsFIFO.get(0) < 1000){
            tickUnit = 100.0;
            if (hintsFIFO.get(0) < 100){
                tickUnit = 10.0;
            }
        }

        XYPlot xyPlot = (XYPlot) jfreechart.getPlot();

        NumberAxis domain = (NumberAxis) xyPlot.getDomainAxis();
        domain.setRange(startFrame - 1, hintsFIFO.size() + startFrame);
        domain.setTickUnit(new NumberTickUnit(1.0));
        domain.setVerticalTickLabels(true);

        NumberAxis range = (NumberAxis) xyPlot.getRangeAxis();
        range.setRange(hintsFIFO.get(0) - tickUnit, hintsBest.get(hintsMRU.size() - 1) + tickUnit);
        range.setTickUnit(new NumberTickUnit(tickUnit));

        return  jfreechart;
    }

    private XYDataset createDataset(int startFrame,
                                    List<Integer> hintsFIFO,
                                    List<Integer> hintsMRU,
                                    List<Integer> hintsSecondChance,
                                    List<Integer> hintsNUR,
                                    List<Integer> hintsBest) {

        final XYSeries fifo = new XYSeries( "FIFO" );
        double frames = (double) startFrame;
        for(Integer hint: hintsFIFO) {
            fifo.add(frames, (double) hint);
            frames++;
        }

        frames = (double) startFrame;
        final XYSeries mru = new XYSeries( "MRU" );
        for(Integer hint: hintsMRU) {
            mru.add(frames, (double) hint);
            frames++;
        }

        frames = (double) startFrame;
        final XYSeries secondChance = new XYSeries( "Second Chance" );
        for(Integer hint: hintsSecondChance) {
            secondChance.add(frames, (double) hint);
            frames++;
        }

        frames = (double) startFrame;
        final XYSeries nur = new XYSeries( "NUR" );
        for(Integer hint: hintsNUR) {
            nur.add(frames, (double) hint);
            frames++;
        }

        frames = (double) startFrame;
        final XYSeries best = new XYSeries( "Best" );
        for(Integer hint: hintsBest) {
            best.add(frames, (double) hint);
            frames++;
        }

        final XYSeriesCollection dataset = new XYSeriesCollection( );
        dataset.addSeries( fifo );
        dataset.addSeries( mru );
        dataset.addSeries( secondChance );
        dataset.addSeries( nur );
        dataset.addSeries(best);

        return dataset;
    }
}