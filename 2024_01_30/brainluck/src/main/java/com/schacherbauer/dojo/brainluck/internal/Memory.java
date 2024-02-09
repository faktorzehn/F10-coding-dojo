package com.schacherbauer.dojo.brainluck.internal;

import java.util.stream.IntStream;

class Memory {

    final int[] memory;
    int dataPointer;

    Memory(int size) {
        this.memory = IntStream.range(0, size).map(i -> 0).toArray();
        this.dataPointer = size / 2;
    }

    void increment() {
        var i = memory[dataPointer];
        memory[dataPointer] = i == 255 ? 0 : i + 1;
    }

    void decrement() {
        var i = memory[dataPointer];
        memory[dataPointer] = i == 0 ? 255 : i - 1;
    }

    void store(int value) {
        memory[dataPointer] = value;
    }

    void incrementDp() {
        dataPointer++;
    }

    void decrementDp() {
        dataPointer--;
    }

    int getValue() {
        return (memory[dataPointer]);
    }

    @Override
    public String toString() {
        var max = Math.max(0, dataPointer - 5);
        var min = Math.min(memory.length, dataPointer + 5);
        StringBuilder sb = new StringBuilder("[");
        for (int i = max; i < min; i++) {
            if (i == dataPointer) {
                sb.append("(").append(memory[i]).append(")");
            } else {
                sb.append(memory[i]);
            }
        }
        return sb.append("]").toString();
    }
}