package fhs.cs.stepcounter.dataexplorer;

import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JFrame;

import org.math.plot.Plot2DPanel;

import processing.core.PApplet;

public class TestDataExplorer extends PApplet {
	private final String COLUMN_TO_GRAPH = "x acc";
	private final String DATA_PATH = "alejandro/";
	
	private int currentIndex = 0;
	DataFileSet dataset = new DataFileSet();
	DataFile currentDataFile;

	public void setup() {
		size(600, 600);
		dataset.addDataFrom(DATA_PATH);
		System.out.println("Loaded " + dataset.size() + " files.");
		currentDataFile = dataset.get(0);
		CSVData csvdata = currentDataFile.getData();

		
		double[][] data = csvdata.getDataForColumns(new String[] { "time", COLUMN_TO_GRAPH });
		System.out.println(data.length + " " + data[0].length);
		
		graphData(data);
	}

	public void draw() {
		background(255);

		fill(0);
		textSize(14);
		text("Data file #" + currentIndex, 10, 15);
		text(currentDataFile.toString(), 10, 35);
	}

	public void keyReleased() {
		currentIndex++;
		if (currentIndex >= dataset.size())
			currentIndex = 0;

		System.out.println("index now : " + currentIndex);
		
		currentDataFile = dataset.get(currentIndex);
		System.out.println("Loading data file: " + currentDataFile.getMetaData("filename"));
		CSVData csvdata = currentDataFile.getData();

		double[][] data = csvdata.getDataForColumns(new String[] { "time", COLUMN_TO_GRAPH });

		graphData(data);
	}

	public static void graphData(double[][] data) {
		Plot2DPanel plot = new Plot2DPanel();

		// add a line plot to the PlotPanel
		plot.addLinePlot("y", data);
		
		// put the PlotPanel in a JFrame, as a JPanel
		JFrame frame = new JFrame("Results");
		frame.setBounds(600, 50, 800, 600);
		//frame.setSize(800, 600);
		frame.setContentPane(plot);
		frame.setVisible(true);
	}
}