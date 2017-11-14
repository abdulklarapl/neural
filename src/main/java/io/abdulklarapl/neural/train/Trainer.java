package io.abdulklarapl.neural.train;

import io.abdulklarapl.neural.element.Layer;
import io.abdulklarapl.neural.element.Network;
import io.abdulklarapl.neural.element.Neuron;
import io.abdulklarapl.neural.element.Synapse;
import org.apache.log4j.Logger;

/**
 * @author Patryk Szlagowski <patryksz@lnsova.pl>
 */
public class Trainer {

    private final static Logger logger = Logger.getLogger(Trainer.class);

    private Network network;

    public Trainer(Network network) {
        this.network = network;
    }

    /**
     * train network using backpropagation algorithm
     * in this step, we execute next loop step until avg error will be greater than threshold
     *
     * @param data
     * @param epochs
     * @return error
     */
    public double train(TrainData data, int epochs) throws Exception {
        double[] input = data.getInput();
        double[] output = data.getOutput();
        double error = 0;

        for (int i = 0;i<epochs;i++) {
            error = backpropagation(input, output);
        }

        return error;
    }

    /**
     * use network to calculate output for given input and compare with expected output. If it's not equal, modify weights
     *
     * @param input
     * @param expectedOutput
     * @return
     */
    public double backpropagation(double[] input, double[] expectedOutput) throws Exception {
        double error = 0;
        double[] output = null;
        double neuronError = 0;

        network.input(input);
        output = network.process();
        for (Layer layer : network.getLayers()) {
            if (layer.isInput()) {
                continue;
            }

            int neuronIndex = 0;
            for (Neuron neuron : layer.getNeurons()) {
                if (layer.isOutput()) {
                    neuronError = neuron.getDerivative() * (output[neuronIndex]);
//                    neuronError = expectedOutput[neuronIndex]-output[neuronIndex];
                    neuronIndex++;
                } else {
                    neuronError = neuron.getDerivative();
                }
                neuron.setError(neuronError);

                double delta = 0;
                for (Synapse synapse : neuron.getSynapses()) {
                    delta = neuron.getError() * synapse.getSource().getOutput();
                    synapse.setWeight(synapse.getWeight() - delta);
                }
            }

            output = network.process();
            error += error(output, expectedOutput);
        }
        return error/expectedOutput.length/(network.getHiddenLayers().size()+1);
    }

    /**
     * calculate error
     *
     * @param actual
     * @param expected
     * @return
     */
    private double error(double[] actual, double[] expected) {
        if (actual.length != expected.length) {
            throw new IllegalArgumentException("The lengths of the actual and expected value arrays must be equal");
        }

        double sum = 0;
        for (int i = 0; i < expected.length; i++) {
            sum += Math.abs(expected[i] - actual[i]);
        }
        return sum;
    }
}
