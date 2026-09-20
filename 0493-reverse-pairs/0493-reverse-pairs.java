class Solution {
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        return mergeSortAndCount(nums, 0, nums.length - 1);
    }

    private int mergeSortAndCount(int[] nums, int start, int end) {
        // Base case: a single element cannot form a pair
        if (start >= end) {
            return 0;
        }

        int mid = start + (end - start) / 2;
        int count = 0;

        // Count reverse pairs in the left half and right half separately
        count += mergeSortAndCount(nums, start, mid);
        count += mergeSortAndCount(nums, mid + 1, end);

        // Count cross-boundary reverse pairs where 'i' is in left and 'j' is in right
        int j = mid + 1;
        for (int i = start; i <= mid; i++) {
            // Using (long) prevents overflow when multiplying elements near Integer.MAX_VALUE by 2
            while (j <= end && (long) nums[i] > 2 * (long) nums[j]) {
                j++;
            }
            // All elements in the right half from mid + 1 up to j-1 are valid pairs for nums[i]
            count += (j - (mid + 1));
        }

        // Merge the two sorted halves together
        merge(nums, start, mid, end);

        return count;
    }

    private void merge(int[] nums, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];
        int i = start;
        int j = mid + 1;
        int k = 0;

        // Standard merge process
        while (i <= mid && j <= end) {
            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }

        // Copy remaining elements from the left half
        while (i <= mid) {
            temp[k++] = nums[i++];
        }

        // Copy remaining elements from the right half
        while (j <= end) {
            temp[k++] = nums[j++];
        }

        // Copy the sorted elements back into the original array
        System.arraycopy(temp, 0, nums, start, temp.length);
    }
}
