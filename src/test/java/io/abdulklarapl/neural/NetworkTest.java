package io.abdulklarapl.neural;

import io.abdulklarapl.neural.activator.LinearActivationFunction;
import io.abdulklarapl.neural.element.Network;
import io.abdulklarapl.neural.element.Neuron;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Patryk Szlagowski (abdulklarapl) <szlagowskipatryk@gmail.com>
 */
public class NetworkTest {

    @Test
    public void testCreatingNewNetwork() {
        Network network = new Network("test", 10);
        assertTrue(network.getInput() != null);
        assertTrue(network.getInput().getNeurons() != null);
        assertTrue(network.getInput().getNeurons().size() == 10);
        assertTrue(network.getHiddenLayers().size() == 1);

        network.getInput().setBias(new Neuron(new LinearActivationFunction(), 1));
        List<Neuron> inputNeurons = network.getInput().getNeurons();
        assertTrue(inputNeurons.get(inputNeurons.size()-1).getActivationFunction().getClass().equals(LinearActivationFunction.class));
    }
}
