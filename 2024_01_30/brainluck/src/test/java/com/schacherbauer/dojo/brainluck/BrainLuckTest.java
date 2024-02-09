package com.schacherbauer.dojo.brainluck;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BrainLuckTest {
    @Test
    public void testEchoUntilByte255Encountered() {
        var brainLuck = new BrainLuck(",+[-.,+]");

        var result = brainLuck.process("Codewars" + ((char) 255));

        assertThat(result).isEqualTo("Codewars");
    }

    @Test
    public void testEchoUntilByte0Encountered() {
        var brainLuck = new BrainLuck(",[.[-],]");

        var result = brainLuck.process("Codewars" + ((char) 0));

        assertThat(result).isEqualTo("Codewars");
    }

    @Test
    public void testTwoNumbersMultiplier() {
        final char[] input = {8, 9};
        var brainLuck = new BrainLuck(",>,<[>[->+>+<<]>>[-<<+>>]<<<-]>>.");
        var expected = String.valueOf((char) (input[0] * input[1]));

        var result = brainLuck.process(String.valueOf(input[0]) + String.valueOf(input[1]));

        assertThat(result).isEqualTo(expected);
    }
}