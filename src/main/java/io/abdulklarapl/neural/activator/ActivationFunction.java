package io.abdulklarapl.neural.activator;

import java.io.Serializable;

/**
 * @author Patryk Szlagowski (abdulklarapl) <szlagowskipatryk@gmail.com>
 */
public interface ActivationFunction extends Serializable {

    double activate(double weightSum);
    double derivative(double weightSum);
    ActivationFunction copy();
}
