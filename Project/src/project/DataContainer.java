package project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

/**
 * <b>DataContainer est la classe représentant un fichier de données.</b>
 * Un objet DataContainer est caractérisé par les informations suivantes :
 * <ul>
 * <li>Une date.</li>
 * <li>Un nom de variables.</li>
 * </ul>
 * 
 * @author faviern
 * @version 1.0
 */


public class DataContainer {

    ArrayList<String> timeStrings;
    ArrayList<String> orderedVariableNames;
    Hashtable<String, ArrayList<Double>> data;
    int numberOfSamples = 0;

    // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    //Constructor    
    // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    
    /**
    * Returns an object DataContainer that reads a csv formatted file with coma 
    * separator
    * The csvFileName argument is a string of the filename.
    * <p>
    * This method always returns immediately, whether or not the 
    * image exists. When this applet attempts to draw the image on
    * the screen, the data will be loaded. The graphics primitives 
    * that draw the image will incrementally paint on the screen. 
    *
    * @param  csvFileName  filename of the csvfile to read
    * @throws java.io.IOException in case of errors
    */
    public DataContainer(String csvFileName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFileName));
        orderedVariableNames = new ArrayList<String>();
        timeStrings = new ArrayList<String>();
        data = new Hashtable<String, ArrayList<Double>>();
        String line;
        line = bufferedReader.readLine();
        String[] tokens = line.split(",");
        int numberOfVariables = 0;
        for (int i = 1; i < tokens.length; i++) {
            orderedVariableNames.add(tokens[i]);
            data.put(tokens[i], new ArrayList<Double>());
            numberOfVariables++;
        }
        while ((line = bufferedReader.readLine()) != null) {
            String[] values = line.split(",");
            for (int i = 0; i < numberOfVariables + 1; i++) {
                if (i == 0) {
                    timeStrings.add(values[i]);
                } else {
                    data.get(orderedVariableNames.get(i - 1)).add(Double.parseDouble(values[i]));
                }
            }
        }
        bufferedReader.close();
        numberOfSamples = timeStrings.size();   

    }

    // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    // Methods
    // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    
    /**
    * Permet d'avoir le nombre de données.
    * 
    * @return    
    *           Le nombre d'éléments/données (lignes du fichier .csv) 
    */
    public int getNumberOfSamples() {
        return numberOfSamples;
    }

    
    /**
    * Permet d'avoir le nombre de variables mesurées (capteurs)
    * 
    * @return    
    *           Le nombre de variables (colonnes du fichier .csv) 
    */
    public int getNumberOfVariables() {
        return data.size();
    }
    
    
    /**
    * Permet d'avoir la date
    * 
    * @return    
    *           La date
    */
    public String[] getTimeStrings() {
        return timeStrings.toArray(new String[numberOfSamples]);
    }
    
    
    /**
    * Permet d'avoir les dates disponibles en format yyyy-MM-d HH:mm:ss
    * 
    * @return    
    *           Les dates disponibles en format yyyy-MM-d HH:mm:ss
     * @throws java.text.ParseException
     *          in case of error
    */
    public Date[] getDates() throws ParseException {
        Date[] dates = new Date[numberOfSamples];
        DateFormat format = new SimpleDateFormat("yyyy-MM-d HH:mm:ss");
        for (int i = 0; i < numberOfSamples; i++) {
            dates[i] = format.parse(timeStrings.get(i));
        }
        return dates;
    }
    
    
    /**
    * Permet d'avoir la liste des variables disponibles
    * 
    * @return    
    *           La date en format yyyy-MM-d HH:mm:ss
    */
    public String[] getAvailableVariables() {
        return orderedVariableNames.toArray(new String[getNumberOfVariables()]);
    }

    
    /**
    * Permet d'avoir les données pour la variables choisie (attention 1 seule)
    * @param columnName
    *           le nom de la variable choisie
    * @return    
    *           array contenant les données
    */    
    public double[] getData(String columnName) {
        List<Double> column = data.get(columnName);
        double[] values = new double[column.size()];
        for (int i = 0; i < column.size(); i++) {
            values[i] = column.get(i);
        }
        return values;
    }
    
    
    /**
    * Permet d'ajouter des données aux DataContainer
    * @param variableName
    *           la variable à laquelle on doit ajouter des données
    * @param values
    *           les valeurs à ajouter
    */  
    public void addData(String variableName, double[] values) {
        if (values.length != getNumberOfSamples()) {
            throw new RuntimeException(variableName + " has " + values.length + " samples instead of " + getNumberOfSamples());
        }
        if (data.containsKey(variableName)) {
            throw new RuntimeException(variableName + " already exists");
        }
        orderedVariableNames.add(variableName);
        ArrayList<Double> newValues = new ArrayList<Double>();
        for (double value : values) {
            newValues.add(value);
        }
        data.put(variableName, newValues);
    }

    
    /**
    * Permet d'ajouter des données aux DataContainer
    * @param variableName
    *           la variable à laquelle on doit ajouter des données
    * @param values
    *           les valeurs à ajouter
    */    
    public void addData(String variableName, Double[] values) {
        double[] primitiveValues = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            primitiveValues[i] = values[i];
        }
        addData(variableName, primitiveValues);
    }

    
    /**
    * Permet de faire un affichage des variables et des données
    * @return string
    *           chaine de caractère à l'affichage
    */  
    public String toString() {
        String string = getNumberOfVariables() + " variables: ";
        String firstRow = "[";
        String lastRow = "[";
        for (String variableName : getAvailableVariables()) {
            string += variableName + ", ";
            double[] values = getData(variableName);
            firstRow += values[0] + ", ";
            lastRow += values[numberOfSamples - 1] + ", ";
        }
        string += "\nnumber of data: " + numberOfSamples + "\n";
        string += getTimeStrings()[0] + ": " + firstRow + "]\n...\n" + getTimeStrings()[numberOfSamples - 1] + ": " + lastRow + "]\n";
        return string;
    }

    

}
