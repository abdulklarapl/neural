package io.abdulklarapl.neural.element;

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

    public Network(String name, int inputSize) {
        this(name, inputSize, 1);
    }

    public Network(String name, int inputSize, int hiddenLayers) {
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
    }
}
