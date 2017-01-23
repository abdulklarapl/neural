package io.abdulklarapl.neural;

import io.abdulklarapl.neural.activator.LinearActivationFunction;
import io.abdulklarapl.neural.element.Layer;
import io.abdulklarapl.neural.element.Network;
import io.abdulklarapl.neural.element.Neuron;

/**
 * @author Patryk Szlagowski (abdulklarapl) <szlagowskipatryk@gmail.com>
 */
public class DigitNeuralNetwork {

    public static void main(String[] args) {
        Network network = new Network("digit", 10);
        network.getLayers().forEach(layer -> {
            if (!layer.isOutput()) {
                layer.setBias(new Neuron(new LinearActivationFunction(), 1));
            }
        });

        return;
    }
}
