import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        // arr[i][0] stores the digit sum, arr[i][1] stores the original number
        int[][] arr = new int[n][2];
        
        for (int i = 0; i < n; i++) {
            arr[i][0] = getDigitSum(nums[i]);
            arr[i][1] = nums[i];
        }
        
        // Sort based on digit sum. If equal, sort by the number itself.
        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });
        
        // Map each value to its final sorted index
        Map<Integer, Integer> targetMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            targetMap.put(arr[i][1], i);
        }
        
        // Cycle detection
        boolean[] visited = new boolean[n];
        int totalCycles = 0;
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                totalCycles++;
                int current = i;
                // Traverse the permutation cycle
                while (!visited[current]) {
                    visited[current] = true;
                    // Move to the index where the current number belongs
                    current = targetMap.get(nums[current]);
                }
            }
        }
        
        // Minimum swaps formula: N - total cycles
        return n - totalCycles;
    }
    
    // Helper function to calculate the sum of digits
    private int getDigitSum(int x) {
        int sum = 0;
        while (x > 0) {
            sum += x % 10;
            x /= 10;
        }
        return sum;
    }
}
