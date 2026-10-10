class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        long totalK = (long) k1 + k2;
        int maxDiff = 0;
        int n = nums1.length;
        int[] freq = new int[100001];
        long totalDiffSum = 0;

        for (int i = 0; i < n; i++) {
            int d = Math.abs(nums1[i] - nums2[i]);
            if (d > 0) {
                freq[d]++;
                totalDiffSum += d;
                maxDiff = Math.max(maxDiff, d);
            }
        }

        // If total operations can reduce all differences to 0
        if (totalDiffSum <= totalK) {
            return 0;
        }

        // Greedily reduce the largest differences down
        for (int d = maxDiff; d > 0 && totalK > 0; d--) {
            if (freq[d] > 0) {
                long reduceCount = Math.min(totalK, freq[d]);
                freq[d] -= reduceCount;
                freq[d - 1] += reduceCount;
                totalK -= reduceCount;
            }
        }

        // Calculate the sum of squared differences
        long minSumSquare = 0;
        for (int d = 1; d <= maxDiff; d++) {
            if (freq[d] > 0) {
                minSumSquare += (long) d * d * freq[d];
            }
        }

        return minSumSquare;
    }
}
