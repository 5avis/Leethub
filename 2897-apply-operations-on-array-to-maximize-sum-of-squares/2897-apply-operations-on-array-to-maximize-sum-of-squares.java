import java.util.List;

public class Solution {
    public int maxSum(List<Integer> nums, int k) {
        // Array to store the count of set bits at each of the 32 bit positions
        int[] bitCounts = new int[32];
        
        // Step 1: Count occurrences of 1s at each bit position
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                if (((num >> i) & 1) == 1) {
                    bitCounts[i]++;
                }
            }
        }
        
        long mod = 1_000_000_007;
        long totalSumOfSquares = 0;
        
        // Step 2: Greedily build the k largest numbers
        for (int i = 0; i < k; i++) {
            long currentNum = 0;
            for (int j = 0; j < 32; j++) {
                // If there are still bits available for position j, use one
                if (bitCounts[j] > 0) {
                    currentNum |= (1L << j);
                    bitCounts[j]--; // Consume the bit
                }
            }
            // Step 3: Accumulate the square of the constructed number
            long square = (currentNum * currentNum) % mod;
            totalSumOfSquares = (totalSumOfSquares + square) % mod;
        }
        
        return (int) totalSumOfSquares;
    }
}
