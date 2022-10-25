import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)  throws IOException {
        System.out.println("Введите выражение по шаблону a + b, a - b, a * b или a / b.");
        System.out.println("a и b арабские или римские числа от 1 до 10:");
        Scanner sc = new Scanner(System.in);
        String input=sc.nextLine();
        sc.close();
        System.out.println(input + " = " + calc(input));
    }
    public static String calc(String input) throws IOException {
        int num1, num2;
                String[] splittedString = input.split(" "); //раскладываем на элементы (число, мат.оператор, число)
        if ((splittedString.length == 3) && isMathOperator(splittedString[1])) { //Проверяем что параметров 3 и в середине мат.оператор
                if (isValidArabic(splittedString[0]) && isValidArabic(splittedString[2])) {//Проверяем что первый и третий параметры - арабские числа
                    num1 = Integer.parseInt(splittedString[0]);
                    num2 = Integer.parseInt(splittedString[2]);
                    char math = splittedString[1].charAt(0);
                    return Integer.toString(calculate(num1, num2, math));
                } else if (isValidRoman(splittedString[0]) && isValidRoman(splittedString[2])) { //Проверяем что первый и третий параметры - римские числа
                    num1 = romanToArabic(splittedString[0]);
                    num2 = romanToArabic(splittedString[2]);
                    char math = splittedString[1].charAt(0);
                    return arabicToRoman(calculate(num1, num2, math));
                } else if ((isValidArabic(splittedString[0]) && isValidRoman(splittedString[2])) || (isValidArabic(splittedString[2]) && isValidRoman(splittedString[0]))) {
                    System.out.println("Неправильно введены данные: смешаны арабские и римские числа");
                    throw new IOException();
                }
                else {
                    System.out.println("Неправильно введены данные: a и b не арабские/римские числа в диапазоне 1-10");
                    throw new IOException();
                }
        }
        else {
            System.out.println("Неправильно введены данные: нет двух чисел и мат.оператора записаных через пробел");
            throw new IOException();
        }
    }
    static boolean isValidArabic(String str) { //проверка на число 1-10
        int size = str.length();
        return (0 < size) && str.matches("[1-9]|10");
    }
    static boolean isValidRoman(String str) { //проверка на римское число I-X
        int size = str.length();
        return (0 < size) && str.matches("X|V?I{0,3}|I[VX]");
    }
    static boolean isMathOperator(String str) throws IOException { //проверка правильного мат. оператора
        if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")) {
            return true;
        }
        System.out.println("Введена неправильная мат. операция");
        throw new IOException();
    }
    static int calculate(int num1, int num2, char math) throws IOException {
        if (math=='+') {
            return num1+num2;
        }
        if (math=='-') {
            return num1-num2;
        }
        if (math=='*') {
            return num1*num2;
        }
        if (math=='/') {
            return num1/num2;
        }
        throw new IOException();
    }
    static int romanToArabic(String str) {
        int size = str.length();
        int romanNum = 0;
        for (int i = 0; i < size; i++) {
            if (str.charAt(i)=='X') {
                romanNum +=10;
            }
            else if (str.charAt(i)=='V') {
                romanNum +=5;
            }
            else if (str.charAt(i)=='I') {
                if (i==(size-1)) {
                    romanNum += 1;
                } else if (str.charAt(i+1)=='V' || str.charAt(i+1)=='X') {
                    romanNum -= 1;
                }
                else {
                    romanNum += 1;
                }
            }
        }
        return romanNum;
    }
    static String arabicToRoman(int number) throws IOException {
        if (number < 1) {
            System.out.println("Результат не является римским числом");
            throw new IOException();
        }
        String result = "";
        while (number > 0) {
            if (number >= 100) {
                result +="C";
                number -= 100;
            } else if (number >= 90) {
                result +="XC";
                number -= 90;
            } else if (number >= 50) {
                result +="L";
                number -= 50;
            } else if (number >= 40) {
                result +="XL";
                number -= 40;
            } else if (number >= 10) {
                result +="X";
                number -= 10;
            } else if (number == 9) {
                result +="IX";
                number -= 9;
            } else if (number >= 5) {
                result +="V";
                number -= 5;
            } else if (number == 4) {
                result +="IV";
                number -= 4;
            } else {
                result +="I";
                number -= 1;
            }
        }
        return result;
    }

}