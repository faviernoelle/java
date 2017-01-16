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
import java.awt.BorderLayout;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.data.time.Hour;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class LaptopConsumption {

    // Variable globale
    DataContainer dataContainer;
    PlotFactory plotFactory;
    int plotCounter = 0;
    int numberOfVariables;
    int numberOfSamples;
    String[] variables;
    String[] zoneAanalyser;

    // Constructor
    public LaptopConsumption(DataContainer dataContainer) throws IOException, ParseException {
        // Asignation
        this.variables = dataContainer.getAvailableVariables();
        this.numberOfVariables = dataContainer.getNumberOfVariables();
        this.dataContainer = dataContainer;
        this.numberOfSamples = dataContainer.getNumberOfSamples();
        String tmp_zoneAanalyser[] = {"power_laptop1_zone1", "power_laptop1_zone2", "power_laptop2_zone2", "power_laptop3_zone2"};
        this.zoneAanalyser = tmp_zoneAanalyser;
        int[] presence = this.AnalyseConsumption();
        System.out.println(presence);
        this.getPresencePlot(presence);

    }

    public int[] AnalyseConsumption() {

        //0. Boucle sur les laptops
        // String zoneAanalyser = "power_laptop1_zone1";
        // 1. Récupérer depuis dataContainer la colonne de zone à analyser
        // 2. Calcul de la consommation
        int[] presence = new int[numberOfSamples];
        double[] donnees;
        for (int t=0 ; t<numberOfSamples ; t++){
            presence[t]=0;
        }
        for (int j = 0; j < 4; j++) {
            String zoneAanalyser_j = zoneAanalyser[j];
            // Copie des données du power_laptopX_zoneY dans la variable "donnée"
            donnees = dataContainer.getData(zoneAanalyser_j);
//            System.out.println(donnees);
            // Je convertis chaque donnee[i] en presence[i]: 0 ou 1 si > ou < 15
            for (int i = 0; i < numberOfSamples; i++) {
                if (donnees[i] > 15) {
                    presence[i] = presence[i] + 1;
                } else {
                    presence[i] = presence[i] + 0;

                }
            }
        }

        // 3. Print oui y'a qqn ou non y'a personne
        return presence;
    }

    // Methods
    public JPanel getPresencePlot(int[] presence) throws IOException, ParseException {

        DateFormat format = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
//        DataContainer dataContainer = new DataContainer("office.csv"); // Read CSV
        Date[] vecteurDates = dataContainer.getDates();
        // Initialization du tableau
//        int nbVariable = variableNames.length;
        // int nbLigne = vecteurDates.length;
       
        TimeSeriesCollection timeSeriesCollection = new TimeSeriesCollection();
//        for (int j = 0; j < 1 ; j++) {
            // String capteur = tableauChaine[j];
            // Get the data
            // double[] value1 = dataContainer.getData(variableNames[j]);
//            int nbDonnees = dataContainer.getNumberOfSamples();
            // Store data in vector
            TimeSeries timeSeries1 = new TimeSeries("presence");
            for (int i = 0; i <numberOfSamples ; i++) {
                timeSeries1.add(new Hour(vecteurDates[i]), presence[i]);
            }
            timeSeriesCollection.addSeries(timeSeries1);
//        }

        // Trace la time series
        JPanel chartPanel = new ChartPanel(ChartFactory.createTimeSeriesChart("title", "xlabel", "ylabel", timeSeriesCollection, true, true, false));
        JFrame frame = new JFrame("Test");
        frame.setLayout(new BorderLayout());

//        button.addActionListener(this);
//        JPanel centerPanel = new JPanel();
//        frame.add(centerPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setVisible(true);

        return new ChartPanel(ChartFactory.createTimeSeriesChart("title", "xlabel", "ylabel", timeSeriesCollection, true, true, false));

    }    
    
    
    
    
    
    
    
}
