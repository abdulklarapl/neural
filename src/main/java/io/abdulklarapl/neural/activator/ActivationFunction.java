package io.abdulklarapl.neural.activator;

/**
 * @author Patryk Szlagowski <patryksz@lnsova.pl>
 */
public interface ActivationFunction {

    double activate(double weightSum);
    double derivative(double weightSum);
    ActivationFunction copy();
}
