package fhs.cs.stepcounter.dataexplorer;

import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.math.plot.Plot2DPanel;

import processing.core.PApplet;

public class TestDataExplorer {
	private static final String DATA_PATH = "alejandro";

	public static void main(String[] args) {
		DataFileSet dataset = new DataFileSet();

		dataset.addDataFrom(DATA_PATH);
		System.out.println("Loaded " + dataset.size() + " files.");

		for (int i = 0; i < dataset.size(); i++) {
			System.out.println("Displaying File #" + i);
			System.out.println("\n\n");
			System.out.println();

			DataFile currentDataFile = dataset.get(i);
			System.out.println(currentDataFile);

			CSVData csvdata = currentDataFile.getData();

			// ------------ Fetch data to graph --------------------------------------
			double[][] data1 = csvdata.getDataForColumns(new String[] { "time", "x acc" });

			// --== uncomment these to display more axes at onces ==--
			// double[][] data2 = csvdata.getDataForColumns(new String[] { "time", "y
			// acc"});
			// double[][] data3 = csvdata.getDataForColumns(new String[] { "time", "z
			// acc"});

			Plot2DPanel plot = new Plot2DPanel();

			// ------------ Add data to plot to the PlotPanel --------------------------
			plot.addLinePlot("x", data1);

			// --== uncomment these to graph more lines for data fetched above ==--
			// plot.addLinePlot("y", data2);
			// plot.addLinePlot("z", data3);

			// put the PlotPanel in a JFrame, as a JPanel
			JFrame frame = new JFrame(currentDataFile.getMetaData("filename"));
			frame.setBounds(600, 50, 800, 600);
			// frame.setSize(800, 600);
			frame.setContentPane(plot);
			frame.setVisible(true);
			
		}
	}
}