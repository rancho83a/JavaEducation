package telephony;

import java.util.List;

public class Smartphone implements Callable, Browsable {
    private List<String> numbers;
    private List<String> urls;

    public Smartphone(List<String> numbers, List<String> urls) {
        this.urls = urls;
        this.numbers = numbers;
    }

    @Override
    public String call() {
        StringBuilder sb = new StringBuilder();

        for (String number : numbers) {
            if (isOnlyDigit(number)) {
                sb.append("Calling... ").append(number);
            } else {
                sb.append("Invalid number!");
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }

    private boolean isOnlyDigit(String number) {
        for (int i = 0; i < number.length(); i++) {
            if (!Character.isDigit(number.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String browse() {
        StringBuilder sb = new StringBuilder();

        for (String url : urls) {
            if (isNotDigit(url)) {
                sb.append("Browsing: ").append(url).append("!");
            } else {
                sb.append("Invalid URL!");
            }
            sb.append(System.lineSeparator());

        }
        return sb.toString().trim();
    }

    private boolean isNotDigit(String url) {
        for (int i = 0; i < url.length(); i++) {
            if (Character.isDigit(url.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
