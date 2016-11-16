package io.abdulklarapl.neural.element;

import java.util.List;

/**
 * @author Patryk Szlagowski (abdulklarapl) <szlagowskipatryk@gmail.com>
 */
public class Neuron {

    private int id;
    private List<Synapse> synapses;

    public Neuron(int id) {
        this.id = id;
    }

    public List<Synapse> getSynapses() {
        return synapses;
    }

    public void setSynapses(List<Synapse> synapses) {
        this.synapses = synapses;
    }

    public int getId() {
        return id;
    }
}
