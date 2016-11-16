package io.abdulklarapl.neural;

import io.abdulklarapl.neural.element.Network;
import org.junit.Test;
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
        assertTrue(network.getInput().getNeurons().get(5).getId() == 5);
        assertTrue(network.getHiddenLayers().get(0).getNeurons().get(5).getId() == 10);
    }
}
