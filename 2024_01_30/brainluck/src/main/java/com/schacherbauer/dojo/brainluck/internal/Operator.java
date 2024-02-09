package com.schacherbauer.dojo.brainluck.internal;

public enum Operator {
    INCREMENT_P('>') {
        @Override
        void execute(ProgramState state) {
            state.memory.incrementDp();
            state.instructionPointer++;
        }
    },
    DECREMENT_P('<') {
        @Override
        void execute(ProgramState state) {
            state.memory.decrementDp();
            state.instructionPointer++;
        }
    },
    INCREMENT('+') {
        @Override
        void execute(ProgramState state) {
            state.memory.increment();
            state.instructionPointer++;
        }
    },
    DECREMENT('-') {
        @Override
        void execute(ProgramState state) {
            state.memory.decrement();
            state.instructionPointer++;
        }
    },
    OUTPUT_P('.') {
        @Override
        void execute(ProgramState state) {
            var b = state.memory.getValue();
            state.output.append((char)b);
            state.instructionPointer++;
        }
    },
    STORE_P(',') {
        @Override
        void execute(ProgramState state) {
            state.memory.store(state.input.charAt(state.inputStreamPointer));
            state.inputStreamPointer++;
            state.instructionPointer++;
        }
    },
    JUMP_FORWARD('[') {
        @Override
        void execute(ProgramState state) {
            if (state.memory.getValue() == 0) {
                var openBrackets = 0;
                var code = state.code;
                for (int i = state.instructionPointer; i < code.length(); i++) {
                    if (code.charAt(i) == '[') {
                        openBrackets++;
                    }
                    if (code.charAt(i) == ']') {
                        openBrackets--;
                    }
                    if (openBrackets == 0) {
                        state.instructionPointer = i + 1;
                        break;
                    }
                }
            } else {
                state.instructionPointer++;
            }
        }
    },
    JUMP_BACKWARD(']') {
        @Override
        void execute(ProgramState state) {
            if (state.memory.getValue() != 0) {
                var openBrackets = 0;
                var code = state.code;
                for (int i = state.instructionPointer; i >= 0; i--) {
                    if (code.charAt(i) == '[') {
                        openBrackets--;
                    }
                    if (code.charAt(i) == ']') {
                        openBrackets++;
                    }
                    if (openBrackets == 0) {
                        state.instructionPointer = i + 1;
                        break;
                    }
                }
            } else {
                state.instructionPointer++;
            }
        }
    };

    private final char c;

    Operator(char c) {
        this.c = c;
    }

    boolean matches(ProgramState state) {
        return state.code.charAt(state.instructionPointer) == c;
    }

    abstract void execute(ProgramState state);

}
