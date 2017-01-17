package io.abdulklarapl.neural.activator;

/**
 * @author Patryk Szlagowski (abdulklarapl) <szlagowskipatryk@gmail.com>
 */
public class ThresholdActivatorFunction implements ActivationFunction {

    private double threshold;

    public ThresholdActivatorFunction(double threshold) {
        this.threshold = threshold;
    }

    @Override
    public double activate(double weightSum) {
        if (weightSum > threshold) {
            return 1;
        }
        return 0;
    }

    @Override
    public double derivative(double weightSum) {
        return 0;
    }

    @Override
    public ActivationFunction copy() {
        return new ThresholdActivatorFunction(threshold);
    }
}
