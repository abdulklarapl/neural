package io.abdulklarapl.neural.element;

import io.abdulklarapl.neural.activator.ActivationFunction;

import java.util.List;

/**
 * @author Patryk Szlagowski (abdulklarapl) <szlagowskipatryk@gmail.com>
 */
public class Neuron {

    private List<Synapse> synapses;
    private ActivationFunction activationFunction;
    private double output;

    public Neuron(ActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
    }

    public Neuron(ActivationFunction activationFunction, double output) {
        this.activationFunction = activationFunction;
        this.output = output;
    }

    public List<Synapse> getSynapses() {
        return synapses;
    }

    public void setSynapses(List<Synapse> synapses) {
        this.synapses = synapses;
    }

    public void addSynapse(Synapse synapse) {
        synapses.add(synapse);
    }

    public double getOutput() {
        return output;
    }

    public void setOutput(double output) {
        this.output = output;
    }

    public ActivationFunction getActivationFunction() {
        return activationFunction;
    }
}
