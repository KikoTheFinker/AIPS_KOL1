//Да се напише алгоритам кој ќе пресметува (евалуира) математички израз
// составен од броеви и операциите за собирање (+) и множење (*).
//
//Забелешка: Операцијата множење има предност пред операцијата собирање.
//
///
//
//Write an algorithm that will calculate (evaluate) a mathematical expression that consists of numbers and operations for adding (*) and multiplying (*).
//
//Note: The operation of multiplying has precedence before the operation of adding.
//
//For example:
//
//Input
//2+2*2*2*2*2*2+2*2
//Result
//70
//Input
//3317*317+12345*2+1*1*1*1*1*1*1*1*1*1*1*1*1*1*1*1*1*1*1*1*1*1*1*1*1*1*1*1*1*1*1*1*1*1*1*1*1*1*1*1+1+1+1
//Result
//1076183
//Input
//12*1*145*2+8*1*1+2*3+2+4
//Result
//3500
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class ExpressionEvaluator {

    public static int evaluateExpression(String expression) {
        Stack<Character> stringStack = new Stack<Character>();
        Stack<Integer> numberStack = new Stack<Integer>();
        int result = 0;
        int i = 0;
        while (i < expression.length()){
            if (Character.isDigit(expression.charAt(i))){
                while (Character.isDigit(expression.charAt(i))){
                    result = result * 10 + Character.getNumericValue(expression.charAt(i));
                    i++;
                    if (i == expression.length()){
                        break;
                    }

                }
                numberStack.push(result);
                result = 0;
            }
            else{
                stringStack.push(expression.charAt(i));
                i++;
            }
            if (!stringStack.empty()){
                if (stringStack.peek() == '*') {
                    while (Character.isDigit(expression.charAt(i))){
                        result = result * 10 + Character.getNumericValue(expression.charAt(i));
                        i++;
                        if (i == expression.length()){
                            break;
                        }

                    }
                    numberStack.push(result);
                    result = (numberStack.pop() * numberStack.pop());
                    numberStack.push(result);
                    stringStack.pop();
                }
            }
            if (i == expression.length()){
                break;
            }
            result = 0;
        }
        while (numberStack.size() > 1 && !stringStack.isEmpty()){
            result = numberStack.pop() + numberStack.pop();
            numberStack.push(result);
            stringStack.pop();
        }
        return numberStack.pop();
    }
    public static void main(String[] args) throws IOException {
        BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
        System.out.println(evaluateExpression(input.readLine()));
    }

}