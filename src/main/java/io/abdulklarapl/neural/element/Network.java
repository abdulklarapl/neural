package io.abdulklarapl.neural.element;

import io.abdulklarapl.neural.activator.LinearActivationFunction;
import io.abdulklarapl.neural.activator.SigmoidActivationFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Patryk Szlagowski (abdulklarapl) <szlagowskipatryk@gmail.com>
 */
public class Network {

    private String name;
    private List<Layer> layers;

    public Layer getInput() {
        return layers.get(0);
    }

    public Layer getOutput() {
        return layers.get(layers.size());
    }

    public List<Layer> getHiddenLayers() {
        return layers.subList(1, layers.size() - 1);
    }

    public List<Layer> getLayers() {
        return layers;
    }

    /**
     * create network with given name, input size and one hidden layer
     * @param name
     * @param inputSize
     */
    public Network(String name, int inputSize) {
        this(name, inputSize, 1, true);
    }

    /**
     * create network with given name, input size and number of hidden layers
     * @param name
     * @param inputSize
     * @param hiddenLayers
     * @param bias
     */
    public Network(String name, int inputSize, int hiddenLayers, boolean bias) {
        this.name = name;
        layers = new ArrayList<>();
        IntStream.range(1, hiddenLayers+3).forEach(index -> {
            Layer layer = new Layer(index);
            try {
                layer.setPrevious(layers.get(index-2));
            } catch (Exception e) {}

            layer.setNeurons(
                    IntStream.range(0, inputSize)
                            .mapToObj(neuron -> new Neuron(new SigmoidActivationFunction()))
                            .collect(Collectors.toList())
            );

            layers.add(layer);
        });

        if (!bias) {
            return;
        }
        layers.forEach(layer -> {
            if (!layer.isOutput()) {
                layer.setBias(new Neuron(new LinearActivationFunction(), 1));
            }
        });
    }

    /**
     * fill input layer with data
     *
     * @param input
     * @throws Exception
     */
    public void input(double[] input) throws Exception {
        if (input.length != getInput().getNeurons().size()) {
            throw new Exception("Wrong number of inputs. Expected elements: "+getInput().getNeurons().size());
        }

        IntStream.range(0, input.length).forEach(in -> {
            getInput().getNeurons().get(in).setOutput(input[in]);
        });
    }

    /**
     * process whole network calculation and get output for given input
     * @return
     */
    public double[] process() {
        double[] output = new double[getOutput().getNeurons().size()];
        layers.stream().forEach(layer -> {
            layer.process();
        });

        return getOutput().getNeurons().stream().mapToDouble(neuron -> neuron.getOutput()).toArray();
    }
}
