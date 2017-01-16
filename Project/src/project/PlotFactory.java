/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.awt.BorderLayout;
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
import javax.swing.JFrame;


/**
 * <b>PlotFactory est la classe permettant de tracer les time series (visualisation).</b>
 * <p>
 * Un objet PlotFactory est une visualisation des données
 * </p>
 * 
 * un GUI avec les time series
 * 
 * @author faviern
 * @version 1.0
 */

public class PlotFactory {

    // Déclaration des Variables
    DataContainer dataContainer;
    Hashtable<String, TimeSeries> timeSeriesContainer;

    // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    //Constructor    
    // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

    /**
     *
     * @param csvDataReader
     *              fichier CSV
     */
    public PlotFactory(DataContainer csvDataReader) {
        dataContainer = csvDataReader;
    }
    
    // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    // Methods
    // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    /**
    * Initialisation le GUI avec les courbes à visualiser
    * 
     * @param variableNames
     *              liste des variables à tracer
     * @return ChartPanel
     *              GUI de visualisation
     * @throws java.io.IOException
     *              en cas d'erreur
     * @throws java.text.ParseException
     *              en cas d'erreur
    */
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
        JFrame frame = new JFrame("Visualisation des données du fichier CSV");
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




/*
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
 */