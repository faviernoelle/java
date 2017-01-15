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
import java.text.ParseException;
import javax.swing.*;

public class PlotTimeChart extends JFrame implements ActionListener {
    //Equivalent de MainFrame dans le doc

    DataContainer dataContainer;
    PlotFactory plotFactory;
    JButton button;
    JCheckBox[] checkBox1;
    int plotCounter = 0;

    // Constructor 
    public PlotTimeChart(DataContainer dataContainer) throws IOException, ParseException {

       this.InitComponents();
        dataContainer = new DataContainer("office.csv");

        PlotFactory plotF;
        plotF = new PlotFactory(dataContainer);
            String[] availableVariable ;
            availableVariable=dataContainer.getAvailableVariables();
            plotF.getPlot(availableVariable);
            
            
        
        
    }

    private void InitComponents() {

        JPanel westPanel = new JPanel();
        this.add(westPanel, BorderLayout.WEST);
        JPanel tablePanel = new JPanel();
        westPanel.add(tablePanel);

        // Il faudrait que j'utilise les données récupérées dans PlotTimeChart--> FAIT
        int numberOfLignes;
        numberOfLignes = dataContainer.getNumberOfVariables();
        String[] availableVariable;
        availableVariable=dataContainer.getAvailableVariables();
        
        
        tablePanel.setLayout(new GridLayout(numberOfLignes, 1));
        
        for(int i=0; i<numberOfLignes; i++){
//            Checkbox[i]=dataContainer.data
            checkBox1[i]=new JCheckBox(availableVariable[i]);
        }
        
        
        
        button = new JButton("plot");
        tablePanel.add(button);
        button.addActionListener(this);
        JPanel centerPanel = new JPanel();
        this.add(centerPanel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.pack();
        this.setVisible(true);

    }

//    public void actionPerformed(ActionEvent e) {
//        System.out.println("bouton");
//        
//        
//    }

//    public static void main(String[] arg) {
//        try {
//            //new PlotTimeChart();
//        } catch (Exception e) {
//            
//        }
//    }

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
