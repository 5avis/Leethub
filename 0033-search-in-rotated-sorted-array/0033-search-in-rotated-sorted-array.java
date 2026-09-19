class Solution {
    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                return mid; // Target found
            }

            // Step 1: Check if the left half is normally sorted
            if (nums[low] <= nums[mid]) {
                // Step 2: Check if target lies within the left half boundaries
                if (target >= nums[low] && target < nums[mid]) {
                    high = mid - 1; // Search left
                } else {
                    low = mid + 1;  // Search right
                }
            } 
            // Step 1 Alternative: The right half must be normally sorted
            else {
                // Step 2 Alternative: Check if target lies within the right half boundaries
                if (target > nums[mid] && target <= nums[high]) {
                    low = mid + 1;  // Search right
                } else {
                    high = mid - 1; // Search left
                }
            }
        }

        return -1; // Target does not exist in the array
    }
}
