package com.app.quantitymeasurement.Enums;

public enum ArithmeticOperation {

    ADD {
        @Override
        public double compute(double left, double right) {
            return left + right;
        }
    },
    SUBTRACT {
        @Override
        public double compute(double left, double right) {
            return left - right;
        }
    },
    DIVIDE {
        @Override
        public double compute(double left, double right) {
            if (right == 0) {
                throw new ArithmeticException("Divide by zero");
            }
            return left / right;
        }
    };

    public abstract double compute(double left, double right);
}
