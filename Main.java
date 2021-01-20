package calculator;

import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final Map<String, String> map = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

//        Various inputs and function calls for debugging purposes
//        map.put("n", "-32");
//        System.out.println(infixToPostfix("33 + 20 + 11 + 49 - 32 - 9 + 1 - 80 + 4"));
//        System.out.println(postfix(infixToPostfix("33 + 20 + 11 + 49 - 32 - 9 + 1 - 80 + 4")));
//        System.out.println(tokenize("33 + 20 + 11 + 49 + n - 9 + 1 - 80 + 4"));
//        System.out.println(tokenize("a * 4 / b - (3 - 1)"));
//        System.out.println(infixToPostfix("a * 4 / b - (3 - 1)"));
//        System.out.println(infixToPostfix("33 + 20 + 11 + 49 + n - 9 + 1 - 80 + 4"));
//        System.out.println(postfix(infixToPostfix("33 + 20 + 11 + 49 + n - 9 + 1 - 80 + 4")));
//        System.out.println(infixToPostfix("2 * (3 + 4) + 1"));
//        System.out.println(postfix(infixToPostfix("2 * (3 + 4) + 1")));

        while (true) {
            // for map debugging
            //map.forEach((key, value) -> System.out.println(key + ": " + value));

            String s = scanner.nextLine().trim();

            //  variable assignment for when expression contains an = sign
            if (s.contains("=")) {
                String[] temp = s.split("=");
                if (temp.length > 2) {
                    System.out.println("Invalid assignment.");
                }
                String s1 = temp[0].trim();
                String s2 = temp[1].trim();
                //check 1st value for incorrect values
                if (s1.replaceAll("[a-zA-Z]+", "").length() > 0) {
                    System.out.println("Invalid identifier.");
                    continue;
                } else if (s2.matches("[a-zA-Z]+") && map.containsKey(s2)) {
                    map.put(s1, map.get(s2));
                } else if (s2.replaceAll("-*\\d", "").length() > 0) { // check s2 for invalid input
                    System.out.println("Invalid assignment.");
                } else {
                    map.put(s1, s2);
                }
                continue;
            }


            // check for operators at the end of digits
            if (s.matches("[\\d]+[+\\-*/]+")) {
                System.out.println("Invalid expression");
                continue;
            }

            // check if command is valid -- all commands begin with "/"
            if (s.startsWith("/") && !s.equals("/help") && !s.equals("/exit")) {
                System.out.println("Unknown command");
                continue;
            }

            // remove leading addition signs
            if (s.matches("\\+\\d+")) {
                s = s.replaceAll("\\+", "");
                System.out.println(s);
                continue;
            }

            // verify both left and right parentheses are present if one is
            if (s.contains("(") && !s.contains(")") || s.contains(")") && !s.contains("(")) {
                System.out.println("Invalid expression");
                continue;
            }

            // if single number is input, print that number
            if (s.matches("[\\d]+")) {
                System.out.println(s);
                continue;
            }

            // validates whether the map contains the variable
            if (s.matches("[^/]*]\\D+")) {
                if (map.containsKey(s)) {
                    System.out.println(map.get(s));
                } else {
                    System.out.println("Unknown Variable");
                }
                continue;
            }



             /*
             Split string by spaces to check for extra operators (addition and subtraction only)
             This ensures that negative numbers are not affected
             While the program currently supports input with no spaces or with extra operators,
             it does not support both in the same input
             */

            String[] split = s.split(" ");

            // remove extra addition and subtraction signs
            if (split.length > 2) {
                for (int i = 1; i < split.length; i += 2) {
                    if (split[i].contains("+")) {
                        split[i] = split[i].replaceAll("[+]+", "+");
                    }

                    if (split[i].contains("-")) {
                        if (split[i].length() % 2 == 0) {
                            split[i] = split[i].replaceAll("[-]+", "+");
                        } else {
                            split[i] = split[i].replaceAll("[-]+", "-");
                        }
                    }

                    if (split[i].matches("[*/]{2,}")) {
                        System.out.println("Invalid expression.");
                        break;
                    }
                }
                StringBuilder sb = new StringBuilder(split[0]);
                for (int i = 1; i < split.length; i++) {
                    sb.append(" ").append(split[i]);
                }
                s = sb.toString();
            }

            // keep program going if empty input is entered
            if (s.equals("")) {
                continue;
            }

            // ensures expression is not a command (length of 1) or single number/variable
            if (split.length >= 2) {
                // add numbers if no operator given
                // extra checks to differentiate minus operator from negative number
                Pattern p = Pattern.compile("[+*/]");
                Pattern p1 = Pattern.compile(" - ");
                Matcher m = p.matcher(s);
                Matcher m1 = p1.matcher(s);
                if (!m.find() && !m1.find()) {
                    int total = 0;
                    for (String digits : s.split(" ")) {
                        total += Integer.parseInt(digits);
                    }
                    System.out.println(total);
                    continue;
                }
                String[] tokens = s.split(" ");

                // check if last item of expression is an operator (Still in infix notation at this point)
                if (tokens[tokens.length - 1].matches("[+\\-/*(]")) {
                    System.out.println("Invalid expression.");
                }

                // if input numbers are too large for int data types, switch to BigIntegers
                boolean isTooBigForIntegers = false;
                for (String str : tokens) {
                    if (str.length() > 9) {
                        isTooBigForIntegers = true;
                        break;
                    }
                }

                if (isTooBigForIntegers) {
                    System.out.println(evaluateBigIntegerPostfix(infixToPostfix(s)));
                } else {
                    System.out.println(evaluatePostfixExpression(infixToPostfix(s)));
                }


//            parse commands
            } else if (split.length == 1) {
                if (split[0].equals("/exit")) {
                    System.out.println("Bye!");
                    break;
                } else if (split[0].equals("/help")) {
                    System.out.println("The program calculates the sum of numbers");
                } else {
                    //todo possibly redundant step below
                    if (map.containsKey(s)) {
                        System.out.println(map.get(s));
                    } else {
                        System.out.println("Unknown Variable.");
                    }
                }
            }
        }

    }

    static BigInteger evaluateBigIntegerPostfix(String expression) {
        Deque<BigInteger> stack = new ArrayDeque<>();

        String[] postfix = expression.split(" ");

        if (postfix.length <= 2) {
            System.out.println("Invalid expression");
            return null;
        }
        for (int i = 0; i < postfix.length; i++) {
            String e = postfix[i];

            if (e.matches("-?[\\d]+")) {
                stack.push(new BigInteger(e));
            }

            if (e.matches("[a-zA-Z]+")) {
                stack.push(new BigInteger(map.get(e)));
            }

            if (e.matches("[+\\-*/]")) {
                BigInteger num1 = stack.pop();
                BigInteger num2 = stack.pop();
                BigInteger result;
                switch (e) {
                    case "+":
                        result = num1.add(num2);
                        stack.push(result);
                        break;
                    case "-":
                        result = num2.subtract(num1);
                        stack.push(result);
                        break;
                    case "*":
                        result = num1.multiply(num2);
                        stack.push(result);
                        break;
                    case "/":
                        result = num2.divide(num1);
                        stack.push(result);
                }
            }

        }
        BigInteger numnum = stack.pop();
        return numnum;


    }


    static int evaluatePostfixExpression(String expression) {
        Deque<Integer> stack = new ArrayDeque<>();
        //expression = tokenize(expression);
        String[] postfix = expression.split(" ");

        if (postfix.length <= 2) {
            System.out.println("Invalid expression");
            return 0;
        }

        for (int i = 0; i < postfix.length; i++) {
            String e = postfix[i];

            if (e.matches("-?[\\d]+")) {
                stack.push(Integer.parseInt(e));
            }

            if (e.matches("[a-zA-Z]+")) {
                stack.push(Integer.parseInt(map.get(e)));
            }

            if (e.matches("[+\\-*/]")) {
                int num1 = stack.pop();
                int num2 = stack.pop();
                int result;
                switch (e) {
                    case "+":
                        result = num1 + num2;
                        stack.push(result);
                        break;
                    case "-":
                        result = num2 - num1;
                        stack.push(result);
                        break;
                    case "*":
                        result = num1 * num2;
                        stack.push(result);
                        break;
                    case "/":
                        result = num2 / num1;
                        stack.push(result);
                }
            }

        }
        int numnum = stack.pop();
        return numnum;
    }


    static String infixToPostfix(String input) {

        Stack<String> stack = new Stack<>();
        List<String> elements = new ArrayList<>();
        input = tokenize(input);
        String[] split = input.split(" ");

        for (String s : split) {

            if (precedence(s) > 0) {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(s)) {
                    elements.add(stack.pop());
                }
                stack.push(s);
            } else if (s.equals(")")) {
                String popped = stack.pop();
                while (!popped.equals("(")) {
                    elements.add(popped);
                    popped = stack.pop();
                }
            } else if (s.equals("(")) {
                stack.push(s);
            } else {
                elements.add(s);
            }
        }


        while (!stack.isEmpty()) {
            elements.add(stack.pop());
        }

        StringBuilder sb = new StringBuilder();
        sb.append(elements.get(0));
        for (int i = 1; i < elements.size(); i++) {
            sb.append(" ").append(elements.get(i));
        }
        String endResult = sb.toString();
        return endResult;
    }

    //todo clean up main() by moving some/all of input validation to this function
    static void validateInputs() {


    }


    static String tokenize(String input) {
//todo this will not work for brackets for some reason
        Scanner s = new Scanner(input);
        Pattern p = Pattern.compile("[+\\-*/()]");
        Pattern variable = Pattern.compile("[a-zA-Z]+");
        Pattern parentheses = Pattern.compile("(.+)");
        //Pattern skippableCharacter = Pattern.compile(".");
        List<String> list = new ArrayList<>();
        while (s.hasNextInt() || s.hasNext(p) || s.hasNext(variable) || s.hasNext(parentheses)) {
            if (s.hasNext(parentheses)) {
                list.add(s.next(parentheses));
            } else if (s.hasNextInt()) {
                list.add(String.valueOf(s.nextInt()));
            } else if (s.hasNext(p)) {
                list.add(s.next(p));
            } else if (s.hasNext(variable)) {
                list.add(s.next(variable));
            }

        }

        StringBuilder sb = new StringBuilder(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            sb.append(" ").append(list.get(i));
        }
        String fixedString = sb.toString();
        fixedString = fixedString.replaceAll("\\(", "( ").replaceAll("\\)", " )");
        return fixedString;
    }


    static int precedence(String c) {
        switch (c) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
                return 3;
        }
        return -1;
    }
}
