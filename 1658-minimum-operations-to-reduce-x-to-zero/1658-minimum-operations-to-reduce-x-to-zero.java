class Solution {
    public int minOperations(int[] nums, int x) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        
        // The sum we need to find inside the middle subarray
        int target = totalSum - x;
        
        // If target is 0, we need to remove all elements to reach x
        if (target == 0) {
            return nums.length;
        }
        // If target is negative, it's impossible because all elements are positive
        if (target < 0) {
            return -1;
        }
        
        int left = 0;
        int currentSum = 0;
        int maxLen = -1;
        
        // Sliding window to find the longest subarray that sums to target
        for (int right = 0; right < nums.length; right++) {
            currentSum += nums[right];
            
            // Shrink the window from the left if the sum exceeds our target
            while (currentSum > target && left <= right) {
                currentSum -= nums[left];
                left++;
            }
            
            // Check if we hit the exact target sum
            if (currentSum == target) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }
        
        // If maxLen was never updated, no valid subarray was found
        return maxLen == -1 ? -1 : nums.length - maxLen;
    }
}
