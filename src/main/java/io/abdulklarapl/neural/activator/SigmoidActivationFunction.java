package io.abdulklarapl.neural.activator;

/**
 * @author Patryk Szlagowski (abdulklarapl) <szlagowskipatryk@gmail.com>
 */
public class SigmoidActivationFunction implements ActivationFunction {

    @Override
    public double activate(double weightSum) {
        return 1.0/(1+Math.exp(-1.0 * weightSum));
    }

    @Override
    public double derivative(double weightSum) {
        return weightSum * (1.0 - weightSum);
    }

    @Override
    public ActivationFunction copy() {
        return new SigmoidActivationFunction();
    }
}
