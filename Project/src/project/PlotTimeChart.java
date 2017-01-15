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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class PlotTimeChart extends JFrame implements ActionListener {
    //Equivalent de MainFrame dans le doc

    // Déclaration
    DataContainer dataContainer;
    PlotFactory plotFactory;
    JButton button;
    int plotCounter = 0;
    int numberOfVariables;
    int numberOfSamples;
    JCheckBox[] checkBox1;

    // public ButtonAction(JCheckBox[] tabCheckbox, DataContainer data, JTextField fieldDateDebut, JTextField fieldDateFin) {
    // Constructor 
    public PlotTimeChart(DataContainer dataContainer) throws IOException, ParseException {

        // Assignation
        this.numberOfVariables = dataContainer.getNumberOfVariables();
        this.checkBox1 = new JCheckBox[numberOfVariables];
        this.dataContainer = dataContainer;
        this.numberOfSamples = dataContainer.getNumberOfSamples();
        this.InitComponents(dataContainer);

    }

    private void InitComponents(DataContainer dataContainer) {

        // Crée un GUI pour les boutons
        System.out.println("- Crée un GUI pour les boutons");
        JPanel westPanel = new JPanel();
        this.add(westPanel, BorderLayout.WEST);
        JPanel tablePanel = new JPanel();
        westPanel.add(tablePanel);

        // Récupère les variables du DataContainer
        System.out.println("- Récupère les variables du DataContainer");
        // int numberOfLignes = dataContainer.getNumberOfVariables();
        // System.out.println(numberOfLignes);
        String[] availableVariable = dataContainer.getAvailableVariables();
        System.out.println(availableVariable[0]);

        //Crée le tableau avec les cases a cocher
        tablePanel.setLayout(new GridLayout(numberOfVariables, 1));
        // Crée un bouton pour chacune des variables du dataContainer
        // JCheckBox[] checkBox1 = new JCheckBox[numberOfVariables];

        for (int i = 0; i < numberOfVariables; i++) {
            checkBox1[i] = new JCheckBox(availableVariable[i]);
            tablePanel.add(checkBox1[i]);
        }

        //Crée le bouton qui permet de tracer les variables sélectionnées
        button = new JButton("plot");
        tablePanel.add(button);
        button.addActionListener(this);
        JPanel eastPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        this.add(eastPanel, BorderLayout.EAST);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        // But : va plot les variables sélectionnées

        TimeSeriesCollection timeSerieCollection = new TimeSeriesCollection();
        int numberOfSamples = dataContainer.getNumberOfSamples();
        Date[] date = new Date[numberOfSamples];
        double[] aTracer;
        String[] variableATracer;
        System.out.println("-- Le bouton est actionné");

        try {
            // Je récupère la date
            System.out.println("-- Je récupère la date");
            date = dataContainer.getDates();
        } catch (ParseException ex) {
            Logger.getLogger(ButtonAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        DateFormat format = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
        System.out.println("-- Je détermine le nombre de variables à tracer");
        int nbATrace = 0;
        for (int i = 0; i < numberOfVariables; i++) {
            if (checkBox1[i].isSelected()) {
                nbATrace = nbATrace+1;
            }
        }
        System.out.println(nbATrace);
        
        
        System.out.println("-- Je stock dans un tableau");
        String[] tableauNames = new String[nbATrace];
        int counter = 0;
        // String[] variables_existante = dataContainer.getAvailableVariables();
        for (int i = 0; i < numberOfVariables; i++) {
            if (checkBox1[i].isSelected()) {
                // tableauNames[i] = variables_existante(i);
                tableauNames[counter] = checkBox1[i].getText();
                System.out.println(tableauNames[counter]);
                counter = counter +1;
                // aTracer = dataContainer.getData(checkBox1[i].getText());
                // TimeSeries timeSerie = new TimeSeries(checkBox1[i].getText());
                // timeSerieCollection.addSeries(timeSerie);
            }
        }
        // appel à la fonction qui trace
        PlotFactory plot = new PlotFactory(dataContainer);
        try {
            plot.getPlot(tableauNames);
        } catch (IOException ex) {
            Logger.getLogger(PlotTimeChart.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(PlotTimeChart.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
// @Override
//        PlotFactory plotF;
//        plotF = new PlotFactory(dataContainer);
//        for (int j = 0; j < numberOfSamples; j++) {
////            plotF.getPlot());
//            
//        }
//
////    public void actionPerformed(ActionEvent e) {
////        System.out.println("bouton");
////        
////        
////    }
//
////    public static void main(String[] arg) {
////        try {
////            //new PlotTimeChart();
////        } catch (Exception e) {
////            
////        }
////    }
//
//}
//
//
//
//
//
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
