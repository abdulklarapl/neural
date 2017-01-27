package io.abdulklarapl.neural.element;

import io.abdulklarapl.neural.activator.ActivationFunction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Patryk Szlagowski (abdulklarapl) <szlagowskipatryk@gmail.com>
 */
public class Neuron implements Serializable {

    private List<Synapse> synapses;
    private ActivationFunction activationFunction;
    private double output;
    private double derivative;
    private double sum;
    private double error;

    {
        synapses = new ArrayList<>();
    }

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

    public void activate() {
        sum = 0;
        synapses.forEach(synapse -> {
            sum += synapse.getWeight() * synapse.getSource().getOutput();
        });

        output = activationFunction.activate(sum);
        derivative = activationFunction.derivative(output);
    }

    public double getDerivative() {
        return derivative;
    }

    public void setError(double error) {
        this.error = error;
    }

    public double getError() {
        return error;
    }
}
