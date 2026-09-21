class Solution {
    public long[] resultArray(int[] nums, int k) {
        // ans[x] will store the total number of subarrays whose product % k == x
        long[] ans = new long[k];
        
        // dp[r] stores the number of subarrays ending at the previous position with product % k == r
        long[] dp = new long[k];

        for (int num : nums) {
            long[] newDp = new long[k];
            int numMod = num % k;

            // Option 1: Start a completely new subarray containing just the current number
            newDp[numMod] += 1;

            // Option 2: Extend all existing subarrays that ended at the previous position
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    // Cast to long before multiplication to prevent integer overflow bounds
                    int nextMod = (int) (((long) r * numMod) % k);
                    newDp[nextMod] += dp[r];
                }
            }

            // Accumulate counts from the current position into the final answer array
            for (int r = 0; r < k; r++) {
                ans[r] += newDp[r];
            }

            // Move the state forward for the next element
            dp = newDp;
        }

        return ans;
    }
}
