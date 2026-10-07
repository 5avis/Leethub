import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int remL = 0;
        int remR = 0;
        
        // Step 1: Calculate the exact number of misplaced left and right parentheses to remove globally
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                remL++;
            } else if (ch == ')') {
                if (remL > 0) {
                    remL--; // Found a valid matching pair
                } else {
                    remR++; // Unmatched right parenthesis
                }
            }
        }
        
        Set<String> validExpressions = new HashSet<>();
        // Step 2: Trigger DFS Backtracking with pruning
        dfs(s, 0, remL, remR, 0, 0, new StringBuilder(), validExpressions);
        
        return new ArrayList<>(validExpressions);
    }
    
    private void dfs(String s, int index, int remL, int remR, int leftCnt, int rightCnt, StringBuilder path, Set<String> result) {
        // Base Case: Processed the entire string
        if (index == s.length()) {
            if (remL == 0 && remR == 0) {
                result.add(path.toString());
            }
            return;
        }
        
        // Pruning 1: Remaining characters are fewer than required removals
        if ((s.length() - index) < (remL + remR)) {
            return;
        }
        
        // Pruning 2: Invalid prefix state (more closing brackets than opening brackets)
        if (leftCnt < rightCnt) {
            return;
        }
        
        char ch = s.charAt(index);
        int len = path.length();
        
        // Option A: Skip the current parenthesis (if we still have removals left to do)
        if (ch == '(' && remL > 0) {
            dfs(s, index + 1, remL - 1, remR, leftCnt, rightCnt, path, result);
        } else if (ch == ')' && remR > 0) {
            dfs(s, index + 1, remL, remR - 1, leftCnt, rightCnt, path, result);
        }
        
        // Option B: Keep the current character (valid for letters and parentheses)
        path.append(ch);
        int nextLeft = leftCnt + (ch == '(' ? 1 : 0);
        int nextRight = rightCnt + (ch == ')' ? 1 : 0);
        
        dfs(s, index + 1, remL, remR, nextLeft, nextRight, path, result);
        
        // Backtrack: Restore StringBuilder state for the next recursive branches
        path.setLength(len);
    }
}
