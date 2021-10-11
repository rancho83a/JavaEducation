package task03_Shop;

public class numericValidator {
    private numericValidator() {
    }

    public static void validate(double num) {
        if (num < 0) {
            throw new IllegalArgumentException(ConstantMessages.NEGATIVE_MONEY_EXCEPTION_MESSAGE);
        }
    }
}
