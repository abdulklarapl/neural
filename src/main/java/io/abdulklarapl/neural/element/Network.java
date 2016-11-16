package io.abdulklarapl.neural.element;

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

    public Network(String name, int inputSize) {
        this(name, inputSize, 1);
    }

    public Network(String name, int inputSize, int hiddenLayers) {
        this.name = name;
        layers = IntStream.range(1, hiddenLayers+3).mapToObj(index -> {
            Layer layer = new Layer(index);
            layer.setNeurons(
                    IntStream.range(0, inputSize)
                            .mapToObj(neuron -> new Neuron(neuron * index))
                            .collect(Collectors.toList())
                    //@TODO set synapses with links
            );
            return layer;
        }).collect(Collectors.toList());
    }
}