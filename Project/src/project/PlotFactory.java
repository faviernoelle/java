/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

/**
 *
 * @author faviern
 */

/*
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
 */
import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.GridLayout;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.data.time.Hour;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import java.util.Hashtable;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

public class PlotFactory {

    // Variables
    DataContainer dataContainer;
    Hashtable<String, TimeSeries> timeSeriesContainer;

    // Constructor
    public PlotFactory(DataContainer csvDataReader) {
        dataContainer = csvDataReader;
    }

    // Methods
    public JPanel getPlot(String[] variableNames) throws IOException, ParseException {

        DateFormat format = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
        DataContainer dataContainer = new DataContainer("office.csv"); // Read CSV
        Date[] vecteurDates = dataContainer.getDates();
        // Initialization du tableau
        int nbVariable = variableNames.length;
        // int nbLigne = vecteurDates.length;
       
        TimeSeriesCollection timeSeriesCollection = new TimeSeriesCollection();
        for (int j = 0; j < nbVariable; j++) {
            // String capteur = tableauChaine[j];
            // Get the data
            double[] value1 = dataContainer.getData(variableNames[j]);
            int nbDonnees = dataContainer.getNumberOfSamples();
            // Store data in vector
            TimeSeries timeSeries1 = new TimeSeries(variableNames[j]);
            for (int i = 0; i < nbDonnees; i++) {
                timeSeries1.add(new Hour(vecteurDates[i]), value1[i]);
            }
            timeSeriesCollection.addSeries(timeSeries1);
        }

        // Trace la time series
        JPanel chartPanel = new ChartPanel(ChartFactory.createTimeSeriesChart("title", "xlabel", "ylabel", timeSeriesCollection, true, true, false));
        JFrame frame = new JFrame("Test");
        frame.setLayout(new BorderLayout());

//        button.addActionListener(this);
        JPanel centerPanel = new JPanel();
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setVisible(true);

        return new ChartPanel(ChartFactory.createTimeSeriesChart("title", "xlabel", "ylabel", timeSeriesCollection, true, true, false));

    }
}

