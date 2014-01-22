import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.PlotState;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.xy.XIntervalSeries;
import org.jfree.data.xy.XIntervalSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.experimental.chart.swt.demo.SWTBarChartDemo1;
import org.jfree.experimental.chart.swt.editor.SWTChartEditor;


public class Program {
	public static void main(String[] args) {
		ScratchArdroneController controller = new ScratchArdroneController();
		ScratchArdroneWindow window = new ScratchArdroneWindow();
		final XIntervalSeriesCollection dataset = new XIntervalSeriesCollection();
	    XIntervalSeries[] serArray = new XIntervalSeries[]{
	    		new XIntervalSeries("X"),
	    		new XIntervalSeries("Y"),
	    		new XIntervalSeries("Z")
	    };
	    double[][] posArray = new double[3][2];
	    for(XIntervalSeries ser : serArray){
	    	dataset.addSeries(ser);
	    }
	    
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {				
				ValueAxis domainAxis = new NumberAxis();
				ValueAxis rangeAxis = new NumberAxis();
				XYItemRenderer renderer = new XYLineAndShapeRenderer();
				XYPlot plot = new XYPlot(dataset, domainAxis, rangeAxis, renderer);
				ChartPanel demo = new ChartPanel(new JFreeChart(plot));
				JFrame frame = new JFrame();
				frame.setSize(1000, 500);
				frame.add(demo);
				frame.setVisible(true);
				frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			}
		});
		
		Random rnd = new Random();
		
		while(true){
			for(int i = 0; i < serArray.length; i++){				
				serArray[i].add(posArray[i][0], posArray[i][0], posArray[i][0], posArray[i][1]);
				posArray[i][0] = posArray[i][0] + 0.1;
				posArray[i][1] = posArray[i][1] + (0.5 - rnd.nextDouble());
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
