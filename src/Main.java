import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)  throws IOException {
        System.out.println("Введите выражение по шаблону a + b, a - b, a * b или a / b.");
        System.out.println("a и b арабские или римские числа от 1 до 10:");
        Scanner sc = new Scanner(System.in);
        String expression=sc.nextLine();
        sc.close();
        System.out.println(expression + " = " + calc(expression));
    }
    public static String calc(String expression) throws IOException {
        int result=0, num1, num2;
        boolean isRoman = false;
        String resultString = "";
                String[] splitedString = expression.split(" "); //раскладываем на элементы (число, мат.оператор, число)
        if ((splitedString.length == 3) && isMathOperator(splitedString[1])) { //Проверяем что параметров 3 и в середине мат.оператор
                if (isValidArabic(splitedString[0]) && isValidArabic(splitedString[2])) {//Проверяем что первый и третий параметры - арабские числа
                    num1 = Integer.parseInt(splitedString[0]);
                    num2 = Integer.parseInt(splitedString[2]);
                    char math = splitedString[1].charAt(0);
                    result=calculate(num1,num2,math);
                } else if (isValidRoman(splitedString[0]) && isValidRoman(splitedString[2])) { //Проверяем что первый и третий параметры - римские числа
                    isRoman = true;
                    num1 = romanToArabic(splitedString[0]);
                    num2 = romanToArabic(splitedString[2]);
                    char math = splitedString[1].charAt(0);
                    result = calculate(num1, num2, math);
                } else if ((isValidArabic(splitedString[0]) && isValidRoman(splitedString[2])) || (isValidArabic(splitedString[2]) && isValidRoman(splitedString[0]))) {
                    System.out.println("Неправильно введены данные: смешаны арабские и римские числа"); //Exсeption!
                    throw new IOException();
                }
                else {
                    System.out.println("Неправильно введены данные: a и b не арабские/римские числа"); //Exсeption!
                    throw new IOException();
                }
        }
        else {
            System.out.println("Неправильно введены данные: нет двух чисел и мат.оператора записаных через пробел"); //Exсeption!
            throw new IOException();
        }
        if (isRoman) {
            resultString = arabicToRoman(result);
        }
        else {
            resultString = Integer.toString(result);
        }
        return resultString;
    }
    static boolean isValidArabic(String str) {
        int size = str.length();
        for (int i = 0; i < size; i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return (0 < size) && (Integer.parseInt(str)>0) && (Integer.parseInt(str)<11);
    }
    static boolean isValidRoman(String str) {
        int size = str.length();
        return (0 < size) && str.matches("^(X{0,1})(V?I{0,3}|I[VX])$");
    }
    static boolean isMathOperator(String str) throws IOException {
        if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")) {
            return true;
        }
        System.out.println("Введена неправильная мат. операция");
        throw new IOException();
    }
    static int calculate(int num1, int num2, char math) {
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
    return -1;
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
        if (number > 100 || number < 1) {
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
            } else if (number >= 9) {
                result +="IX";
                number -= 9;
            } else if (number >= 5) {
                result +="V";
                number -= 5;
            } else if (number >= 4) {
                result +="IV";
                number -= 4;
            } else if (number >= 1) {
                result +="I";
                number -= 1;
            }
        }
        return result;
    }

}