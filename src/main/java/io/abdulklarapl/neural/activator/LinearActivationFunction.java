package io.abdulklarapl.neural.activator;

/**
 * @author Patryk Szlagowski (abdulklarapl) <szlagowskipatryk@gmail.com>
 */
public class LinearActivationFunction implements ActivationFunction {

    @Override
    public double activate(double weightSum) {
        return weightSum;
    }

    @Override
    public double derivative(double weightSum) {
        return 1;
    }

    @Override
    public ActivationFunction copy() {
        return new LinearActivationFunction();
    }
}
