/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

/**
 * <b>Dichotomie est la classe permettant de réaliser la dichotomie.</b>
 * <p>
 * In order to determine the best proportional coefficient, a simple dichotomy
 * can be done, considering the laptop consumption based estimations as the
 * actual occupancies. Scale the coefficient to minimize the error between
 * laptop consumption and motion detection based occupancy estimators. Algorithm
 * is given in 15.2.3.
 *
 * Finding the minimum of a 1-variable convex function can be done by dichotomy.
 * </p>
 *
 *
 * @author faviern
 * @version 1.0
 */

public class Dichotomie {
    // Definition des variables
    int numberOfIterations = 0;
    int xmin;
    double[] values;
    double precision;

    
    /**
    * Finding the minimum of a 1-variable convex function can be done by dichotomy.
    * 
    * @param values
    *           valeur de la fonction
    * @param m
    *           borne inférieure de l'intervalle [m,M]
    * @param M
    *           borne supérieure de l'intervalle [m,M]
     * @param maxIteration
     *          nombre d'ittérations maximales de la méthode
    */
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
    
    
    /**
    * Retourne le minimum de la valeur trouvée
    *
    * @return xmin
    *           minimum trouvée
    */   
    public double getMinValue() {
        return (xmin);
    }

    /**
    * Retourne la précision de la valeur trouvée
    *
    * @return precision
    *           precision obtenue
    */   
    public double getPrecision() {
        return (precision);
    }

    
    /**
    * Retourne le nombre d'ittérations
    *
    * @return numberOfIterations
    *           nombre d'ittérations
    */
    public int getNumberOfIterations() {
        return (numberOfIterations);
    }
}
