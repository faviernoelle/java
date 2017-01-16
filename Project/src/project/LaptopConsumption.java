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
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class LaptopConsumption {

    // Variable globale
    DataContainer dataContainer;
    PlotFactory plotFactory;
    int plotCounter = 0;
    int numberOfVariables;
    int numberOfSamples;
    String[] variables;
    // String zoneAanalyser;

    // Constructor
    public LaptopConsumption(DataContainer dataContainer) throws IOException, ParseException {
        // Asignation
        this.variables = dataContainer.getAvailableVariables();
        this.numberOfVariables = dataContainer.getNumberOfVariables();
        this.dataContainer = dataContainer;
        this.numberOfSamples = dataContainer.getNumberOfSamples();
        // this.zoneAanalyser = {"power_laptop1_zone1"};

        this.AnalyseConsumption();
    }

    public int[] AnalyseConsumption() {
        String zoneAanalyser = "power_laptop1_zone1";
        // 1. Récupérer depuis dataContainer la colonne de zone à analyser
        int index = -1;
        for (int i = 0; i < variables.length; i++) {
            if (variables[i].equals(zoneAanalyser)) {
                index = i;
                break;
            }
        }
        System.out.println(index);
        System.out.println(variables[index]);

        // 2. Calcul de la consommation
        double[] donnees = dataContainer.getData(zoneAanalyser);
        int[] presence = new int[numberOfSamples];
        System.out.println(donnees);
        for (int i = 0; i < numberOfSamples; i++) {
            if (donnees[i] > 15) {
                presence[i] = 1;
            } else {
                presence[i] = 0;

            }
        }

        // 3. Print oui y'a qqn ou non y'a personne
        return presence ;
    }

}
