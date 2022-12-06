package Lab02_MyStack;
import java.util.Stack;

public class StackProbsRunner {
    public static void main(String[] args) {
        StackProbs probs = new StackProbs();
        System.out.println(probs.doubleUp(makeStack(new int[]{1, 3, 5, 0, -1})));
        System.out.println(probs.posAndNeg(makeStack(new int[]{2, 9, -4, 3, -1, 0, -6})));
        System.out.println(probs.shiftByN(makeStack(new int[]{7, 23, -7, 0, 22, -8, 4, 5}), 3));
        System.out.println(probs.reverseVowels("hello how are you "));
        System.out.println(probs.bracketBalance("(([()])))"));
        System.out.println(probs.bracketBalance("([()[]()])()"));
    }

    private static Stack<Integer> makeStack(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        for (int num : nums)
            stack.push(num);
        return stack;
    } //example call: makeStack(new int[] {1, 2, 3, 4})
}
