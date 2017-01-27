package io.abdulklarapl.neural.element;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Patryk Szlagowski (abdulklarapl) <szlagowskipatryk@gmail.com>
 */
public class Layer implements Serializable {

    private int id;
    private List<Neuron> neurons;
    private Layer previous;
    private Layer next;
    private Neuron bias;

    {
        neurons = new ArrayList<>();
    }

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
        for (Neuron neuron : neurons) {
            addNeuron(neuron);
        }
    }

    public void addNeuron(Neuron neuron) {
        if (previous != null) {
            for (Neuron prevLayerNeuron : previous.getNeurons()) {
                neuron.addSynapse(new Synapse(prevLayerNeuron));
            }
        }
        neurons.add(neuron);
    }

    public void setBias(Neuron bias) {
        this.bias = bias;
        neurons.add(bias);
    }

    public Layer getPrevious() {
        return previous;
    }

    public void setPrevious(Layer previous) {
        this.previous = previous;
    }

    public Layer getNext() {
        return next;
    }

    public void setNext(Layer next) {
        this.next = next;
    }

    public boolean isInput() {
        return previous == null;
    }

    public boolean isHidden() {
        return previous != null && next != null;
    }

    public boolean isOutput() {
        return next == null;
    }

    public void process() {
        int start = 0;
        if (bias != null) {
            start = 1;
        }
        neurons.stream().skip(start).forEach(neuron -> {
            neuron.activate();
        });
    }

    public boolean hasBias() {
        return bias != null;
    }

    public String getStamp() {
        return neurons.stream().map(neuron -> String.valueOf(neuron.getOutput())).collect(Collectors.joining(""));
    }
}
