import java.util.Objects;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите задачу в следующем порядке:\nСтрока (в кавычках), оператор, строка (в кавычках) или цифра от 1 до 10:");
        String string = scanner.nextLine();
        String result = analys(string);
        System.out.println("Результат: " + '"' + result + '"');
    }

    private static String analys(String string) {
        StringBuilder first = new StringBuilder();
        StringBuilder second = new StringBuilder();
        Character operator = null;
        StringBuilder numbers = new StringBuilder();
        String result = new String();

        boolean checkQuotes = true;
        for (int i = 0; i < string.length(); i++) {
            char oneChar = string.charAt(i);
            try {
                if (oneChar == '"') {
                    checkQuotes = !checkQuotes;
                } else if (!checkQuotes && operator == null) {
                    first.append(oneChar);
                } else if (Character.isDigit(oneChar) && operator == null) {
                    throw new RuntimeException();
                } else if (operator != null && checkQuotes) {
                    numbers.append(oneChar);
                } else if (!checkQuotes && operator != null) {
                    second.append(oneChar);
                } else if (oneChar == '+' || oneChar == '-' || oneChar == '*' || oneChar == '/' && checkQuotes) {
                    operator = oneChar;
                }
            } catch (Exception e) {
                result = "Сначала строка, потом цифра";
                return result;
            }
        }

        return getIntFromString(numbers, first, second, result, operator);
    }

    private static String getIntFromString(StringBuilder numbers, StringBuilder first, StringBuilder second, String result, Character operator) {
        String finalNumbers = new String(numbers.toString());
        int sum = 0;
        if (numbers.isEmpty()) {
            return checkСharacterСount(first, second, result, operator, sum);
        } else {
            try {
                sum = Integer.parseInt(finalNumbers);
                if (sum <= 10 && sum > 0) {
                    return checkСharacterСount(first, second, result, operator, sum);
                } else if (sum > 10 || sum < 1 ) {
                    throw new RuntimeException();
                }
            } catch (Exception e) {
                result = "Параметр введен некорректно.";
                return result;
            }
            return null;
        }
    }

    private static String checkСharacterСount(StringBuilder first, StringBuilder second, String result, Character operator, int sum) {
        try {
            if (first.length() > 10 || second.length() > 10) {
                throw new RuntimeException();
            } else if (first.length() <= 10 && second.length() <= 10) {
                return distribution(first, second, result, operator, sum);
            }
        } catch (Exception e) {
            result = "Одна из строк содержит более десяти символов.";
            return result;

        }
        return null;
    }

    private static String distribution(StringBuilder first, StringBuilder second, String result, Character operator, int sum) {
        try {
            if (operator == null) {
                throw new RuntimeException();
            } else if (operator == '+') {
                return getPlus(first, second, result, sum);
            } else if (operator == '-') {
                return getMinus(first, second, result, sum);
            } else if (operator == '*') {
                return getMultiplication(first, second, result, sum);
            } else if (operator == '/') {
                return getDivision(first, second, result, sum);
            }
        } catch (Exception e) {
            result = "Вы не ввели оператор.";
            return result;
        }
        return null;
    }

    private static String getPlus(StringBuilder first, StringBuilder second, String result, int sum) {
        String fin;
        try {
            if (sum != 0) {
                throw new RuntimeException();
            }
            fin = first.toString() + second.toString();
            return getResult(fin);


        } catch (Exception e) {
            result = "При сложении задача должна состоять из двух строк.";
            return result;
        }
    }

    private static String getMinus(StringBuilder first, StringBuilder second, String result, int sum) {
        String phrase1 = new String(first);
        String phrase2 = new String(second);
        String[] words = phrase1.split(" ");
        StringBuilder fin = new StringBuilder();
        try {
            if (sum != 0) {
                throw new RuntimeException();
            }
            for (String word : words) {
                if (!word.equals(phrase2)) {
                    fin.append(word).append(' ');
                }
            }
            if (fin.length() > 0) {
                fin.setLength(fin.length() - 1);
            }
            return fin.toString();
        } catch (Exception e) {
            result = "При вычитании задача должна состоять из двух строк.";
            return result;
        }
    }


    private static String getMultiplication(StringBuilder first, StringBuilder second, String result, int sum) {
        StringBuilder resultMultiplication = new StringBuilder();
        String fin;
        try {
            if (second.length() != 0) {
                throw new RuntimeException();
            }
            for (int i = 0; i < sum; i++) {
                resultMultiplication.append(first);
            }
            fin = resultMultiplication.toString();
            return getResult(fin);
        } catch (Exception e) {
            result = "При умножении задача должна состоять из строки и цифры.";
            return result;
        }
    }

    private static String getDivision(StringBuilder first, StringBuilder second, String result, int sum) {
        int firstLength = 0;
        StringBuilder resultGetDivision = new StringBuilder();
        try {
            if (second.length() != 0) {
                throw new RuntimeException();
            }
            for (int i = 0; i < first.length(); i++) {
                firstLength = firstLength + 1;
            }
            firstLength = firstLength / sum;
            for (int i = 0; i < firstLength; i++) {
                char s = first.charAt(i);
                resultGetDivision.append(s);
            }
            return resultGetDivision.toString();
        } catch (Exception e) {
            result = "При делении задача должна состоять из строки и цифры.";
            return result;
        }

    }


    private static String getResult(String fin) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < fin.length(); i++) {
            char oneChar = fin.charAt(i);
            if (i < 40) {
                result.append(oneChar);
            } else if (i >= 40) {
                result.append("...");
                return result.toString();
            }
        }
        return result.toString();
    }
}
