package project;

import java.awt.BorderLayout;
import java.io.IOException;

/*
import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.data.time.Hour;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection; */
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PlotTimeChart extends JFrame implements ActionListener {
    //Equivalent de MainFrame dans le doc

    DataContainer dataContainer;
    PlotFactory plotFactory;
    JButton button;
    JCheckBox[] checkBox1;
    int plotCounter = 0;

    // Constructor 
    public PlotTimeChart(DataContainer dataContainer) throws IOException {

        this.buildGUI();
        dataContainer = new DataContainer("office.csv");
        
    }

    private void buildGUI() {

        JPanel westPanel = new JPanel();
        this.add(westPanel, BorderLayout.WEST);
        JPanel tablePanel = new JPanel();
        westPanel.add(tablePanel);

        // Il faudrait que j'utilise les données récupérées dans PlotTimeChart
        int numberOfLignes;
        numberOfLignes = dataContainer.getNumberOfVariables();

        
        tablePanel.setLayout(new GridLayout(numberOfLignes, 1));
        
        for(int i=0; i<numberOfLignes; i++){
//            Checkbox[i]=dataContainer.data
            
            tablePanel.add(new Checkbox[i]("Toffice",false));
        }
        
        
        tablePanel.add(new Checkbox("Toffice", false));
        tablePanel.add(new Checkbox("Tcorridor", false));
        tablePanel.add(new Checkbox("Theater", false));
        tablePanel.add(new Checkbox("Toffice_for_heater", false));
        tablePanel.add(new Checkbox("office_CO2_concentration", false));
        tablePanel.add(new Checkbox("corridor_CO2_concentration", false));
        tablePanel.add(new Checkbox("window_opening", false));
        tablePanel.add(new Checkbox("door_opening", false));
        tablePanel.add(new Checkbox("power_zone1", false));
        tablePanel.add(new Checkbox("power_zone2", false));
        tablePanel.add(new Checkbox("power_laptop1_zone1", false));
        tablePanel.add(new Checkbox("power_laptop1_zone2", false));
        tablePanel.add(new Checkbox("power_laptop2_zone2", false));
        tablePanel.add(new Checkbox("power_laptop3_zone2", false));
        tablePanel.add(new Checkbox("detected_motion", false));
        tablePanel.add(new Checkbox("Tout", false));
        tablePanel.add(new Checkbox("nebulosity", false));
        tablePanel.add(new Checkbox("solar_radiation", false));
        tablePanel.add(new Checkbox("occupancy_from_laptops", false));
        tablePanel.add(new Checkbox("occupancy_from_detector", false));
        tablePanel.add(new Checkbox("occupancy_from_concentration", false));
        tablePanel.add(new Checkbox("occupancy_from_detector", false));
        button = new JButton("plot");
        tablePanel.add(button);
        button.addActionListener(this);
        JPanel centerPanel = new JPanel();
        this.add(centerPanel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.pack();
        this.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("bouton");
        
    }

    public static void main(String[] arg) {
        try {
            //new PlotTimeChart();
        } catch (Exception e) {
            
        }
    }

}













































//}

/*

            // Recuperation des données 
        //Format de date du fichier : 01/04/2015  02:00:00
        DateFormat format = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
        DataContainer dataContainer = new DataContainer("office.csv");
        Date[] vecteurDates = dataContainer.getDates();
        double[] value1 = dataContainer.getData("Toffice");
        double[] value2 = dataContainer.getData("Theater");
        double[] value3 = dataContainer.getData("Tcorridor");
        double[] value4 = dataContainer.getData("Tout");

        int nbDonnees = dataContainer.getNumberOfSamples();

        TimeSeries timeSeries1 = new TimeSeries("Toffice");
        for (int i = 0; i < nbDonnees; i++) {
            timeSeries1.add(new Hour(vecteurDates[i]), value1[i]);
        }

        TimeSeries timeSeries2 = new TimeSeries("Theater");
        for (int i = 0; i < nbDonnees; i++) {
            timeSeries2.add(new Hour(vecteurDates[i]), value2[i]);
        }

        TimeSeries timeSeries3 = new TimeSeries("Tcorridor");
        for (int i = 0; i < nbDonnees; i++) {
            timeSeries3.add(new Hour(vecteurDates[i]), value3[i]);
        }

        TimeSeries timeSeries4 = new TimeSeries("Tout");
        for (int i = 0; i < nbDonnees; i++) {
            timeSeries4.add(new Hour(vecteurDates[i]), value4[i]);
        }

        TimeSeriesCollection timeSeriesCollection = new TimeSeriesCollection();
        timeSeriesCollection.addSeries(timeSeries1);
        timeSeriesCollection.addSeries(timeSeries2);
        timeSeriesCollection.addSeries(timeSeries3);
        timeSeriesCollection.addSeries(timeSeries4);

        JPanel chartPanel = new ChartPanel(ChartFactory.createTimeSeriesChart("title", "xlabel", "ylabel", timeSeriesCollection, true, true, false));
        JFrame frame = new JFrame("Test");
        frame.setLayout(new BorderLayout());
        JPanel westPanel = new JPanel();
        frame.add(westPanel, BorderLayout.WEST);
        JPanel tablePanel = new JPanel();
        westPanel.add(tablePanel);
        tablePanel.setLayout(new GridLayout(22, 1));

            
          String[] vecteurDateTransf = new String[nbDonnees] ;
          
          for(int i = 0 ; i<nbDonnees;i++){
              //vecteurDateTransf[i]=vecteurDates[i].toString();
              vecteurDateTransf[i]=vecteurDates[i].toString();
            }
        
          
          Date[] dates = new Date[3];
        try {
        for (int i = 0; i < nbDonnees; i++) {
                //Transformer les Date en String
                dates[i] = format.parse(vecteurDateTransf[i]);
            }
      
//        DateFormat format = new SimpleDateFormat("yyyy-MM-d HH:mm:ss");
//        String[] stringDates = {"2015-04-01 00:00:00", "2015-04-01 01:00:00", "2015-04-01 02:00:00"};
//        double[] value1 = {3, 1, 2};
//        double[] value2 = {-1, 2, 1};
   
  catch (ParseException e) {
            e.printStackTrace();
        
//        DataContainer g=new DataContainer("office.csv");
//        PlotFactory f = new PlotFactory(g);
//        String[] variableATracer = {"Tout","Toffice"};
//        f.getPlot(variableATracer);
//        
//        JFrame frame = new JFrame("Test");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add(PlotFactory(g));
//        frame.pack();
//        frame.setVisible(true);
        
        } */
