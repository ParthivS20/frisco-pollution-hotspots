package Lab02_MyStack;

import java.util.Stack;
import java.util.regex.Pattern;

class StackProbs {
    Stack<Integer> doubleUp(Stack<Integer> nums) {
        Integer x = nums.pop();
        if(!nums.isEmpty()) {
            doubleUp(nums);
        }
        nums.push(x);
        nums.push(x);
        return nums;
    }

    Stack<Integer> posAndNeg(Stack<Integer> nums) {
        Stack<Integer> pos = new Stack<>();
        Stack<Integer> neg = new Stack<>();
        int size = nums.size();
        for(int i = 0; i < size; i++) {
            int x = nums.pop();
            if(x < 0) {
                neg.push(x);
            }
            else {
                pos.push(x);
            }
        }

        while(pos.size() > 0) {
            neg.push(pos.pop());
        }

        return neg;
    }

    Stack<Integer> shiftByN(Stack<Integer> nums, int n) {
        Stack<Integer> firstN = new Stack<>();
        Stack<Integer> temp = new Stack<>();
        int size = nums.size();
        for(int i = 0; i < size - n; i++) {
            temp.push(nums.pop());
        }
        for(int i = 0; i < n; i++) {
            firstN.push(nums.pop());
        }

        int size2 = temp.size();
        for(int i = 0; i < size2; i++) {
            nums.push(temp.pop());
        }
        for(int i = 0; i < n; i++) {
            nums.push(firstN.pop());
        }

        return nums;
    }

    String reverseVowels(String str) {
        Pattern vowel = Pattern.compile("[aeiou]");
        Stack<String> vowels = new Stack<>();

        for(int i = 0; i < str.length(); i++) {
            String x = str.substring(i, i + 1);
            if(vowel.matcher(x).find()) {
                vowels.push(x);
            }
        }

        for(int i = 0; i < str.length(); i++) {
            String x = str.substring(i, i + 1);
            if(vowel.matcher(x).find()) {
                str = str.substring(0, i) + vowels.pop() + str.substring(i + 1);
            }
        }

        return str;
    }

    boolean bracketBalance(String s) {
        String openingBrackets = "({[<";
        String closingBrackets = ")}]>";

        Stack<Character> brackets = new Stack<>();
        char[] sequence = s.toCharArray();
        for(char b : sequence) {
            if(openingBrackets.contains(b + "")) {
                brackets.push(b);
            }
            else if(brackets.isEmpty() || !brackets.pop().equals(openingBrackets.charAt(closingBrackets.indexOf(b)))) {
                return false;
            }
        }

        return brackets.isEmpty();
    }
}
