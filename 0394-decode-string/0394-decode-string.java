import java.util.Stack;

class Solution {
    public String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();
        StringBuilder currentString = new StringBuilder();
        int k = 0;

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                // Formulate the multi-digit number k
                k = k * 10 + (ch - '0');
            } else if (ch == '[') {
                // Push the current multiplier and string to the stacks
                countStack.push(k);
                stringStack.push(currentString);
                
                // Reset for the new internal bracket context
                currentString = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                // Decode by popping the repetition multiplier
                int repeatTimes = countStack.pop();
                StringBuilder decodedString = stringStack.pop();
                
                // Append the current inner pattern k times to the parent string
                for (int i = 0; i < repeatTimes; i++) {
                    decodedString.append(currentString);
                }
                
                // Move our pointer forward with the updated decoded string
                currentString = decodedString;
            } else {
                // Normal character, just append to current fragment
                currentString.append(ch);
            }
        }

        return currentString.toString();
    }
}
