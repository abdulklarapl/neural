package io.abdulklarapl.neural.element;

import io.abdulklarapl.neural.activator.LinearActivationFunction;
import io.abdulklarapl.neural.activator.SigmoidActivationFunction;
import org.apache.log4j.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Patryk Szlagowski (abdulklarapl) <szlagowskipatryk@gmail.com>
 */
public class Network implements Serializable {

    private String name;
    private List<Layer> layers;

    public Layer getInput() {
        return layers.get(0);
    }

    public Layer getOutput() {
        return layers.get(layers.size() - 1);
    }

    public List<Layer> getHiddenLayers() {
        return layers.subList(1, layers.size() - 1);
    }

    public List<Layer> getLayers() {
        return layers;
    }

    /**
     * create network with given name, input size and one hidden layer
     *
     * @param name
     * @param inputSize
     * @param outputSize
     * @param hiddenLayerSize
     */
    public Network(String name, int inputSize, int outputSize, int hiddenLayerSize) {
        this(name, inputSize, outputSize, hiddenLayerSize, 1, true);
    }

    /**
     * create network with given name, input size and number of hidden layers
     *
     * @param name
     * @param inputSize
     * @param outputSize
     * @param hiddenLayerSize
     * @param hiddenLayers
     * @param bias
     */
    public Network(String name, int inputSize, int outputSize, int hiddenLayerSize, int hiddenLayers, boolean bias) {
        this.name = name;
        layers = new ArrayList<>();
        IntStream.range(1, hiddenLayers + 3).forEach(index -> {
            Layer layer = new Layer(index);
            try {
                layer.setPrevious(layers.get(index - 2));
            } catch (Exception e) {
            }

            int size = inputSize;
            if (index != 1) {
                size = hiddenLayerSize;
            }
            if (index == hiddenLayers + 2) {
                size = outputSize;
            }
            layer.setNeurons(
                    IntStream.range(0, size)
                            .mapToObj(neuron -> new Neuron(new SigmoidActivationFunction()))
                            .collect(Collectors.toList())
            );

            layers.add(layer);
        });

        IntStream.range(0, layers.size()).forEach(index -> {
            if (index == layers.size() - 1) {
                return;
            }
            layers.get(index).setNext(layers.get(index + 1));
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
        int targetSize = getInput().getNeurons().size();
        if (getInput().hasBias()) {
            targetSize -= 1;
        }
        if (input.length != targetSize) {
            throw new Exception("Wrong number of inputs. Expected elements: " + targetSize + " but given: " + input.length);
        }

        IntStream.range(0, input.length).forEach(in -> {
            getInput().getNeurons().get(in).setOutput(input[in]);
        });
    }

    /**
     * process whole network calculation and get output for given input
     *
     * @return
     */
    public double[] process() {
        double[] output = new double[getOutput().getNeurons().size()];
        layers.stream().forEach(layer -> {
            if (layer.isInput()) {
                return;
            }
            layer.process();
        });

        return getOutput().getNeurons().stream().mapToDouble(neuron -> neuron.getOutput()).toArray();
    }

    /**
     * ugly as fuck hack until I dont get the way how to persist weights
     * @return
     */
    public String persist() throws IOException {
        String fileName = "NeuralNetwork-".concat(name).concat(String.valueOf(new Date().getTime())).concat(".bin");
        ObjectOutputStream stream = null;

        stream = new ObjectOutputStream(new FileOutputStream(fileName));
        stream.writeObject(this);
        stream.flush();
        stream.close();

        return fileName;
    }
}
