package io.abdulklarapl.neural.train;

/**
 * @author Patryk Szlagowski <patryksz@lsnova.pl>
 */
public class TrainData {

    private double[] input;
    private double[] output;

    public TrainData(double[] input, double[] output) {
        this.input = input;
        this.output = output;
    }

    public double[] getInput() {
        return input;
    }

    public double[] getOutput() {
        return output;
    }
}
