package io.abdulklarapl.neural.train;

import io.abdulklarapl.neural.element.Layer;
import io.abdulklarapl.neural.element.Network;
import io.abdulklarapl.neural.element.Neuron;
import io.abdulklarapl.neural.element.Synapse;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Patryk Szlagowski <patryksz@lnsova.pl>
 */
public class Trainer {

    private final static Logger logger = Logger.getLogger(Trainer.class);

    private Network network;
    private double learningRate = 0.1;
    private double momentum = 0.9;
    private double characteristicTime = 0;
    private double currentEpoach;

    public Trainer(Network network) {
        this.network = network;
    }

    /**
     * train network using backpropagation algorithm
     * in this step, we execute next loop step until avg error will be greater than threshold
     *
     * @param data
     * @param errorThreshold
     */
    public void train(TrainData data, double errorThreshold) throws Exception {
        double[] input = data.getInput();
        double[] output = data.getOutput();
        int epoch = 1;
        double avgError = errorThreshold+1;
        double error = 0;
        double prevError = backpropagation(input, output);

        while (avgError > errorThreshold) {
            error = backpropagation(input, output);
            avgError = Math.abs(prevError-error);
            epoch++;
            currentEpoach = epoch;
            prevError = error;
            logger.info("output: "+output.hashCode()+", thr: "+errorThreshold+", er: "+error+"/"+avgError+", ep:"+epoch);
        }
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
        Map<Synapse, Double> synapseDeltaMap = new HashMap<>();

        network.input(input);
        output = network.process();
        for (Layer layer : network.getLayers()) {

            int neuronIndex = 0;
            for (Neuron neuron : layer.getNeurons()) {
                if (layer.isOutput()) {
                    neuronError = neuron.getDerivative() * (output[neuronIndex]);
                } else {
                    neuronError = neuron.getDerivative();

                    double sum = 0;
                    for (Neuron nextNeuron : layer.getNext().getNeurons()) {
                        for (Synapse synapse : nextNeuron.getSynapses()) {
                            if (synapse.getSource().equals(neuron)) {
                                sum += (synapse.getWeight() * nextNeuron.getError());
                                break;
                            }
                        }
                    }

                    neuronError += sum;
                }
                neuron.setError(neuronError);

                // now we can update the weights
                if (characteristicTime > 0) {
                    learningRate = learningRate / (1+(currentEpoach/characteristicTime));
                }
                double delta = 0;
                double prevDelta = 0;
                for (Synapse synapse : neuron.getSynapses()) {
                    delta = learningRate * neuron.getError() * synapse.getSource().getOutput();
                    if (synapseDeltaMap.containsKey(synapse)) {
                        prevDelta = synapseDeltaMap.get(synapse);
                        delta += momentum * prevDelta;
                    }
                    synapseDeltaMap.put(synapse, delta);
                    synapse.setWeight(synapse.getWeight() - delta);
                }

                output = network.process();
                error += error(output, expectedOutput);

                neuronIndex++;
            }
        }
        return error;
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
            sum += Math.pow(expected[i] - actual[i], 2);
        }
        return sum / 2;
    }
}
