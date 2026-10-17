import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), 1, n, k);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> current, int start, int n, int k) {
        // Base Case: If the combination has reached the size k, add it to results
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Optimization: Ensure there are enough remaining elements left to pick to reach k
        // If (n - i + 1) is less than the numbers we still need, we can safely break early.
        for (int i = start; i <= n - (k - current.size()) + 1; i++) {
            // 1. Choose the current number
            current.add(i);
            
            // 2. Explore further combinations with elements strictly greater than i
            backtrack(result, current, i + 1, n, k);
            
            // 3. Backtrack: remove the last element to try the next number
            current.remove(current.size() - 1);
        }
    }
}
