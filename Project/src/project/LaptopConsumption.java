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
        
        // Presence en fonction des occupants + plots       
        System.out.println("Presence en fonction des occupants + plots ");
        double[] presence = this.AnalyseConsumptionLaptops();
        System.out.println(presence);
        String title1 = "Occupancy estimator";
        this.getPresencePlot(presence, title1);
        
        // Presence en fonction de motion + plots
        System.out.println("Presence en fonction de motion + plots ");
        double[] presenceMotion = this.AnalyseDataMotion(presence);
        String title2 = "Occupancy estimator + Motion detection";
        this.getPresencePlot(presenceMotion, title2);
        
    }

    public double[] AnalyseConsumptionLaptops() {

        
        // 1. Récupérer depuis dataContainer la colonne de zone à analyser
        double[] presence = new double[numberOfSamples];
        double[] donnees;
        for (int t=0 ; t<numberOfSamples ; t++){
            presence[t]=0;
        }
        for (int j = 0; j < 4; j++) {
            String zoneAanalyser_j = zoneAanalyser[j];
            // Copie des données du power_laptopX_zoneY dans la variable "donnée"
            donnees = dataContainer.getData(zoneAanalyser_j);
            for (int i = 0; i < numberOfSamples; i++) {
                if (donnees[i] > 15) {
                    presence[i] = presence[i] + 1;
                } else {
                    presence[i] = presence[i] + 0;

                }
            }
        }

        return presence;
    }
    
    public double[] AnalyseDataMotion(double[] presence){

        double[] occupancyFromMotions = new double[numberOfSamples];
        
        // Occupancy from motion detections
        int scale = 1000;
        double[] ErrMoy = new double[scale + 1];
        double[] detectedMotions = dataContainer.getData("detected_motions");
        for (int alpha = 0; alpha < (scale + 1); alpha++) { //Création du vecteur de l'erreur en fonction de alpha
            int countAverage = 0;
            ErrMoy[alpha] = 0;
            double newAlpha = alpha;
            for (int t = 0; t < numberOfSamples; t++) {
                if (presence[t] != 0) {
                    ErrMoy[alpha] += Math.abs(((newAlpha / scale) * detectedMotions[t]) - presence[t]);
                    countAverage++;
                }
            }
            ErrMoy[alpha] = ErrMoy[alpha] / countAverage;
        }

        Dichotomie dicho = new Dichotomie(ErrMoy, 0, scale, 500);
        double alphaOptimal = dicho.getMinValue();       
        for (int i = 0; i < numberOfSamples; i++) {
            occupancyFromMotions[i] = alphaOptimal / scale * detectedMotions[i];
        }
        
        return occupancyFromMotions;
    }
    
    

    // Methods
    public JPanel getPresencePlot(double[] presence, String title) throws IOException, ParseException {

        DateFormat format = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
        Date[] vecteurDates = dataContainer.getDates();
       
        TimeSeriesCollection timeSeriesCollection = new TimeSeriesCollection();
            TimeSeries timeSeries1 = new TimeSeries("presence");
            for (int i = 0; i <numberOfSamples ; i++) {
                timeSeries1.add(new Hour(vecteurDates[i]), presence[i]);
            }
            timeSeriesCollection.addSeries(timeSeries1);

        // Trace la time series
        JPanel chartPanel = new ChartPanel(ChartFactory.createTimeSeriesChart("title", "xlabel", "ylabel", timeSeriesCollection, true, true, false));
        JFrame frame = new JFrame(title);
        frame.setLayout(new BorderLayout());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setVisible(true);

        return new ChartPanel(ChartFactory.createTimeSeriesChart("title", "xlabel", "ylabel", timeSeriesCollection, true, true, false));

    }    
    
    
    
    
    
    
    
}
