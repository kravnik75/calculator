import java.util.Scanner;

public class calc {

    static int convDigit(String str) {
        return switch (str) {
            case "1"    -> 1;
            case "2"    -> 2;
            case "3"    -> 3;
            case "4"    -> 4;
            case "5"    -> 5;
            case "6"    -> 6;
            case "7"    -> 7;
            case "8"    -> 8;
            case "9"    -> 9;
            case "10"   -> 10;
            default     -> 0;
        };
    }

    static int convRoman(String str) {
        return switch (str) {
            case "I"    -> 1;
            case "II"   -> 2;
            case "III"  -> 3;
            case "IV"   -> 4;
            case "V"    -> 5;
            case "VI"   -> 6;
            case "VII"  -> 7;
            case "VIII" -> 8;
            case "IX"   -> 9;
            case "X"    -> 10;
            default     -> 0;
        };
    }

    public static String units(char unit) {
        return switch (unit) {
            case '0' -> "";
            case '1' -> "I";
            case '2' -> "II";
            case '3' -> "III";
            case '4' -> "IV";
            case '5' -> "V";
            case '6' -> "VI";
            case '7' -> "VII";
            case '8' -> "VIII";
            case '9' -> "IX";
            default -> throw new IllegalStateException("Unexpected unit value: " + unit);
        };
    }

    public static String tens(char tens) {
        String s_tens = "";
        return switch (tens) {
            case '0' -> "";
            case '1' -> "X";
            case '2' -> "XX";
            case '3' -> "XXX";
            case '4' -> "XL";
            case '5' -> "L";
            case '6' -> "LX";
            case '7' -> "LXX";
            case '8' -> "LXXX";
            case '9' -> "XC";
            default -> throw new IllegalStateException("Unexpected ten value: " + tens);
        };
    }

    static void operationDigit(int a, int b, char str) {
        int result;
        switch (str) {
            case '+' -> result = a + b;
            case '-' -> result = a - b;
            case '*' -> result = a * b;
            case '/' -> result = a / b;
            default -> result = 0;
        }
        System.out.println(result);
    }

    static void operationRoman(int a, int b, char str) {
        int result;
        switch (str) {
            case '+' -> result = a + b;
            case '-' -> result = a - b;
            case '*' -> result = a * b;
            case '/' -> result = a / b;
            default  -> result = 0;
        }
        if (result <= 0) throw new IllegalStateException("Negative or zero roman value");
        System.out.println(convDigitRoman(result));
    }

    public static String convDigitRoman(int result) {
        char[] charDigits = Integer.toString(result).toCharArray(); //получение массива цифр из числа
        return switch (charDigits.length) {
            case 1 -> units(charDigits[0]);
            case 2 -> tens(charDigits[0]) + units(charDigits[1]);
            case 3 -> "C" + tens(charDigits[1]) + units(charDigits[2]);
            default -> throw new IllegalStateException("Unexpected value: " + charDigits.length);
        };

    }

    public static void main(String[] args) {
        final String REGEX="[+\\-*/]";
        int a, b;
        Scanner in = new Scanner(System.in);

        System.out.print("Input expression: ");
        String num = in.nextLine();
        in.close();
        String[] words = num.split(REGEX);             // выделяет числа из строки
        if (words.length != 2) throw new IllegalStateException("Unexpected expression"); //если не 2 числа то выход
        char sign = num.charAt(words[0].length());                       // выделяет знак операции

        if (convDigit(words[0]) != 0) {                                 // для арабских чисел
            a = convDigit(words[0]);
            if (convDigit(words[1]) != 0) {
                b = convDigit(words[1]);
                operationDigit(a, b, sign);
            } else throw new IllegalStateException("Unexpected second value");
        } else {                                                            // для римских чисел
            if (convRoman(words[0]) != 0) {
                a = convRoman(words[0]);
                if (convRoman(words[1]) != 0) {
                    b = convRoman(words[1]);
                    operationRoman(a, b, sign);
                } else throw new IllegalStateException("Unexpected second value");
            } else throw new IllegalStateException("Unexpected first value");

        }
    }
}





