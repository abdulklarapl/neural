package io.abdulklarapl.neural;

import io.abdulklarapl.neural.element.Network;

/**
 * @author Patryk Szlagowski (abdulklarapl) <szlagowskipatryk@gmail.com>
 */
public class DigitNeuralNetwork {

    public static void main(String[] args) throws Exception {
        double[] input = {0};
        Network network = new Network("digit", 25*25, 10, 425); // digit network - input = rows*cols, output size = 10 digits
        network.input(input);
        double[] output = network.process();
        int found = 0;
        double max = 0;

        for (int i = 0; i < output.length; i++) {
            if (output[i] > max) {
                found = i;
            }
        }

        return;
    }
}
