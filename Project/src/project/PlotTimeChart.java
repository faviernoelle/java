package project;

import java.awt.BorderLayout;
import java.io.IOException;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


/**
 * <b>PlotTimeChart est la classe permettant de réaliser l'interface graphique 
 * pour les boutons et d'afficher si nécessaire les capteurs sélectionnés par 
 * l'utilisateur </b>
 * 
 * @see une interface graphique permettant de voir les données avec la date par capteur ou ensemble
 * @see une interface permettant de choisir les données à visualiser
 * 
 * @author faviern
 * @version 1.0
 */

public class PlotTimeChart extends JFrame implements ActionListener {
    //Equivalent de MainFrame dans le doc

    // Déclaration des variables
    DataContainer dataContainer;
    PlotFactory plotFactory;
    JButton button;
    int plotCounter = 0;
    int numberOfVariables;
    int numberOfSamples;
    JCheckBox[] checkBox1;

    
    // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    //Constructor    
    // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    // public ButtonAction(JCheckBox[] tabCheckbox, DataContainer data, JTextField fieldDateDebut, JTextField fieldDateFin) {
    
    /**
    * Initialisation de la classe et lancement automatique de l'interface de 
    * données et du choix de variables
    * 
    * @param dataContainer
    *               objet data container contenant les données au format 
    *               en colonne les variables (capteurs)
    *               en ligne les données datées
    * @throws java.io.IOException
    * @throws java.text.ParseException
    */
    public PlotTimeChart(DataContainer dataContainer) throws IOException, ParseException {

        // Assignation
        this.numberOfVariables  = dataContainer.getNumberOfVariables();
        this.checkBox1          = new JCheckBox[numberOfVariables];
        this.dataContainer      = dataContainer;
        this.numberOfSamples    = dataContainer.getNumberOfSamples();
        
        // Lance par defaut InitComponents avec dataContainer (construction des GUIs)
        this.InitComponents(dataContainer);

    }

    
    /**
    * Initialisation le GUI avec les courbes à visualiser
    * 
    * @param dataContainer
    *               objet data container contenant les données au format 
    *               en colonne les variables (capteurs)
    *               en ligne les données datées
    */
    private void InitComponents(DataContainer dataContainer) {

        // Crée un GUI pour les boutons
        System.out.println("- Crée un GUI pour les boutons");
        JPanel westPanel = new JPanel();
        this.add(westPanel, BorderLayout.WEST);
        JPanel tablePanel = new JPanel();
        westPanel.add(tablePanel);

        // Récupère les variables du DataContainer
        System.out.println("- Récupère les variables du DataContainer");
        String[] availableVariable = dataContainer.getAvailableVariables();
        System.out.println(availableVariable[0]);

        //Crée le tableau avec les cases a cocher
        tablePanel.setLayout(new GridLayout(numberOfVariables, 1));
        
        // Crée un bouton pour chacune des variables du dataContainer
        for (int i = 0; i < numberOfVariables; i++) {
            checkBox1[i] = new JCheckBox(availableVariable[i]);
            tablePanel.add(checkBox1[i]);
        }

        // Crée le GUI qui permet de tracer les variables sélectionnées
        button = new JButton("plot");
        tablePanel.add(button);
        button.addActionListener(this);
        JPanel eastPanel = new JPanel();
        // JPanel centerPanel = new JPanel();
        this.add(eastPanel, BorderLayout.EAST);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

    }

    
    /**
    * Est lancé lorsque l'utilisateur appuie sur le bouton 'Plot'
    * 
    * @param e
    *               evenement (appui sur le bouton Plot)
    */
    public void actionPerformed(ActionEvent e) {
        // But : va plot les variables sélectionnées
        
        // int numberOfSamples = dataContainer.getNumberOfSamples();
        // TimeSeriesCollection timeSerieCollection = new TimeSeriesCollection();       
        // Date[] date = new Date[numberOfSamples];
        // double[] aTracer;
        // String[] variableATracer;
        System.out.println("-- Le bouton 'Plot' est actionné");

        try {
            // Je récupère la date
            System.out.println("-- Je récupère la date");
            Date[] date = dataContainer.getDates();
        } catch (ParseException ex) {
            Logger.getLogger(ButtonAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // DateFormat format = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
        System.out.println("-- Je détermine le nombre de variables à tracer");
        int nbATrace = 0;
        for (int i = 0; i < numberOfVariables; i++) {
            if (checkBox1[i].isSelected()) {
                nbATrace = nbATrace+1;
            }
        }
        System.out.println(nbATrace);
        
        
        System.out.println("-- Je stockes dans un tableau");
        String[] tableauNames = new String[nbATrace];
        int counter = 0;
        // String[] variables_existante = dataContainer.getAvailableVariables();
        System.out.println("-- Voici les nouvelles variables tracées");
        for (int i = 0; i < numberOfVariables; i++) {
            if (checkBox1[i].isSelected()) {
                tableauNames[counter] = checkBox1[i].getText();
                System.out.println(tableauNames[counter]);
                counter = counter +1;
            }
        }
        
        // Appel à la fonction qui trace
        PlotFactory plot = new PlotFactory(dataContainer);
        try {
            plot.getPlot(tableauNames);
        } catch (IOException | ParseException ex) {
            Logger.getLogger(PlotTimeChart.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}






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