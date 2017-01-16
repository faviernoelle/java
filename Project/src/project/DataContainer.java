package project;

import java.awt.BorderLayout;
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
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.data.time.TimeSeriesCollection;

public class DataContainer {

    ArrayList<String> timeStrings;
    ArrayList<String> orderedVariableNames;
    Hashtable<String, ArrayList<Double>> data;
    int numberOfSamples = 0;

    //Constructor
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
        
        // PlotTimeChart toPlot = new PlotTimeChart() ;

    }

    // Methods
    public int getNumberOfSamples() {
        return numberOfSamples;
    }

    public int getNumberOfVariables() {
        return data.size();
    }

    public String[] getTimeStrings() {
        return timeStrings.toArray(new String[numberOfSamples]);
    }

    public Date[] getDates() throws ParseException {
        Date[] dates = new Date[numberOfSamples];
        DateFormat format = new SimpleDateFormat("yyyy-MM-d HH:mm:ss");
        for (int i = 0; i < numberOfSamples; i++) {
            dates[i] = format.parse(timeStrings.get(i));
        }
        return dates;
    }

    public String[] getAvailableVariables() {
        return orderedVariableNames.toArray(new String[getNumberOfVariables()]);
    }

    public double[] getData(String columnName) {
        List<Double> column = data.get(columnName);
        double[] values = new double[column.size()];
        for (int i = 0; i < column.size(); i++) {
            values[i] = column.get(i);
        }
        return values;
    }

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

    public void addData(String variableName, Double[] values) {
        double[] primitiveValues = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            primitiveValues[i] = values[i];
        }
        addData(variableName, primitiveValues);
    }

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

    public static void main(String[] args) throws ParseException {
        try {
            // Permet de lire le fichier .csv et stocker dans un tableau data
            System.out.println("Start DataContainer");
            DataContainer data = new DataContainer("office.csv");
            System.out.println(data);
            System.out.println("End DataContainer");
            
           
            // Jouer avec ton DataContainer data
            System.out.println("Affichage de variable");
            String[] variables_existante = data.getAvailableVariables();
            System.out.println(variables_existante);
            
            
            System.out.println("Affichage du nombre de variables");
            int nb_variable = data.getNumberOfVariables();
            System.out.println(nb_variable);
            
            // avoir les variables sélectionnées
            // variables_selectionnées
            
            
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
            
            System.out.println("Start Laptop consumption");
            LaptopConsumption lap= new LaptopConsumption(data) ;
//            int[] presence = lap.AnalyseConsumption();
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
