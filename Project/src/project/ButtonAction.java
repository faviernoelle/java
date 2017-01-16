/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

/**
 *
 * @author Noëlle
 */
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class ButtonAction extends AbstractAction {

    DataContainer dataContainer;
    int numberOfVariable;
    int numberOfSamples;
    JCheckBox[] checkBox1;

//    Constructor 
    public ButtonAction(DataContainer dataContainer, int numberOfVariable, int numberOfSamples, JCheckBox[] checkBox1) {
        this.numberOfVariable = dataContainer.getNumberOfVariables();
        this.numberOfSamples = dataContainer.getNumberOfSamples();
        this.checkBox1 = checkBox1;
        this.dataContainer = dataContainer;

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
//        TimeSeriesCollection timeSerieCollection = new TimeSeriesCollection();
//        Date[] date = new Date[numberOfSamples];
//        double[] aTracer;
//        String[] variableATracer ;
//        
//        try {
//            date = dataContainer.getDates();
//        } catch (ParseException ex) {
//            Logger.getLogger(ButtonAction.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        DateFormat format = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
//        for (int i = 0; i < numberOfSamples; i++) {
//            if (checkBox1[i].isSelected()) {
//                aTracer = dataContainer.getData(checkBox1[i].getText());
//                TimeSeries timeSerie = new TimeSeries(checkBox1[i].getText());
//                timeSerieCollection.addSeries(timeSerie);
//            }
//        }
//        PlotFactory plotF;
//        plotF = new PlotFactory(dataContainer);
//        for (int j = 0; j < numberOfSamples; j++) {
////            plotF.getPlot());
//            
//        }
    }

}
