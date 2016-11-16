package io.abdulklarapl.neural.element;

import java.util.List;

/**
 * @author Patryk Szlagowski (abdulklarapl) <szlagowskipatryk@gmail.com>
 */
public class Layer {

    private int id;
    private List<Neuron> neurons;

    public Layer(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Neuron> getNeurons() {
        return neurons;
    }

    public void setNeurons(List<Neuron> neurons) {
        this.neurons = neurons;
    }
}
