package io.abdulklarapl.neural.element;

import java.io.Serializable;

/**
 * @author Patryk Szlagowski (abdulklarapl) <szlagowskipatryk@gmail.com>
 */
public class Synapse implements Serializable {

    private double weight;
    private Neuron source;

    public Synapse(Neuron source) {
        this.source = source;
        this.weight = (Math.random() * 1) - 0.5; // random, small weight
    }

    public Synapse(double weight, Neuron source) {
        this.weight = weight;
        this.source = source;
    }

    public Neuron getSource() {
        return source;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
