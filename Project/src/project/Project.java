/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;

/**
 * <b>Project est la classe main du projet</b>
 * 
 * @author faviern
 * @version 1.0
 */


public class Project {
    
    /**
     * Main du program
     */
    public Project(){

    }  
      
    /**
    * Main du program
    * 
    * @param args
    *           une chaine de caractère pouvant contenir des arguments pour le main
    * @throws java.text.ParseException
    */
    public static void main(String[] args) throws ParseException {
        try {
            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            System.out.println("Problem: occupancy estimators");
            System.out.println("Author: Noelle FAVIER");
            System.out.println("Date: 2017-01-16");
            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            
            System.out.println("START of the program");
            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            
            // Permet de lire le fichier .csv et stocker dans un tableau data
            System.out.println("Start DataContainer");
            DataContainer data = new DataContainer("office.csv");
            System.out.println(data);
            System.out.println("End DataContainer");
                     
            
            
            // Jouer avec ton DataContainer data
            System.out.println("Affichage de variables");
            String[] variables_existante = data.getAvailableVariables();
            //System.out.println(variables_existante);
            System.out.println(Arrays.toString(variables_existante));
            
            
            
            System.out.println("Affichage du nombre de variables");
            int nb_variable = data.getNumberOfVariables();
            System.out.println(nb_variable);           
            
            
            
            // afficher les variables à afficher dans une interface
             System.out.println("Start PlotFactory");
             PlotFactory plot = new PlotFactory(data);
             String tableauChaine[] = {"Toffice", "Theater"};
             System.out.println(tableauChaine[1]);
             plot.getPlot(tableauChaine);
             System.out.println("End PlotFactory");
            
             
             
            // Affichage de la fenetre de selection 
            System.out.println("Start Plot Buttons");
            PlotTimeChart plotButtons = new PlotTimeChart(data) ;
            
            
            // Laptop consumption
            System.out.println("Start Laptop consumption");
            LaptopConsumption lap= new LaptopConsumption(data) ;
            
            
            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            System.out.println("END of the program");
            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}


// extends JFrame implements ActionListener 
    
//  protected JButton button;


//import javax.swing.JFrame;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.*;



// Methode pour dévelloper un interface d'écriture de mail 
        
        /*
        JFrame f = new JFrame(" Type a mail");
        f.setLayout(new BorderLayout(10, 10));
        JPanel northPanel = new JPanel();
        f.add(northPanel, BorderLayout.NORTH);
        northPanel.setLayout(new GridLayout(2,1));
        JPanel newNorthPanel = new JPanel();
        northPanel.add(newNorthPanel);
        newNorthPanel.setLayout(new BorderLayout());
        newNorthPanel.add(new Label ("Destinataire"), BorderLayout.WEST);
        newNorthPanel.add(new TextField(""), BorderLayout.CENTER);
        JPanel newNewNorthPanel = new JPanel();
        northPanel.add(newNewNorthPanel);
        newNewNorthPanel.setLayout(new BorderLayout());
        newNewNorthPanel.add(new Label("Copie"), BorderLayout.WEST);
        newNewNorthPanel.add(new TextField(""), BorderLayout.CENTER);
        JPanel southPanel = new JPanel();
        f.add(southPanel, BorderLayout.SOUTH);
        southPanel.setLayout(new FlowLayout());
        button = new JButton("Envoyer");
        southPanel.add(button);
        button.addActionListener(this);
        southPanel.add(new Button ("Annuler"));
        southPanel.add(new Button ("Brouillon"));
        JPanel eastPanel = new JPanel();
        f.add(eastPanel, BorderLayout.EAST);
        eastPanel.setLayout(new GridLayout(3,1));
        eastPanel.add(new Button ("Gras"));
        eastPanel.add(new Button ("Italique"));
        eastPanel.add(new Button ("Normal"));
        JPanel centerPanel = new JPanel();
        f.add(centerPanel, BorderLayout.CENTER);
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(new Label ("Texte du courrier"), BorderLayout.NORTH);
        centerPanel.add(new TextArea (""), BorderLayout.CENTER);
        f.pack();
        f.setVisible(true);
*/




/*
    public void actionPerformed(ActionEvent e){
        button.setText("Envoyé");
    }
    */