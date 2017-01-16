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
public class Dichotomie {
    int numberOfIterations = 0;
    int xmin;
    double[] values;
    double precision;

    public Dichotomie(double[] values, int m, int M, int maxIteration) {
        this.values = values;
        int l = m;
        int u = M;
        int c = 0;
        for (int i = 0; i < maxIteration; i++) {
            c = Math.round((l + u) / 2);
            if ((values[l] < values[u]) && (values[c] < values[u])) {
                u = c;
            } else if ((values[c] < values[l]) && (values[u] < values[l])) {
                l = c;
            } else {
                break;
            }
            numberOfIterations++;
        }
        xmin = c;
        precision = (u - l) / 2;
    }

    public double getMinValue() {  //Valeur du minimum trouvée
        return (xmin);
    }

    public double getPrecision() {
        return (precision);
    }

    public int getNumberOfIterations() {
        return (numberOfIterations);
    }
}
