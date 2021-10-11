public class Demo {
    public static double sqrt(double value) {

        if (value < 0)

            throw new IllegalArgumentException(
                    "Sqrt for negative numbers is undefined!");
        return Math.sqrt(value);
    }

    public static void main(String[] args) {

        try {

            sqrt(9);

        } catch (IllegalArgumentException ex) {

            System.err.println("Error: " + ex.getMessage());

            ex.printStackTrace();

        }

    }
}

