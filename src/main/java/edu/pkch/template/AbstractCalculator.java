package edu.pkch.template;

public abstract class AbstractCalculator implements Calculator {
    private final CalculateValidator calculateValidator;

    public AbstractCalculator(CalculateValidator calculateValidator) {
        this.calculateValidator = calculateValidator;
    }

    @Override
    public int calculate(int x, int y) {
        calculateValidator.validate(x, y);
        return operate(x, y);
    }

    public abstract boolean isSupport(CalculateType type);
    protected abstract int operate(int x, int y);
}
