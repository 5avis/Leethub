public class Solution {
    public char findKthBit(int n, int k) {
        // Base case: S1 is always "0"
        if (n == 1) {
            return '0';
        }

        // Calculate the length of Sn-1, which is also the exact midpoint index for Sn
        int mid = 1 << (n - 1); // Equivalent to 2^(n-1)

        if (k == mid) {
            return '1';
        } else if (k < mid) {
            // It lies in the first half (same as Sn-1)
            return findKthBit(n - 1, k);
        } else {
            // It lies in the second half (reversed and inverted version of the first half)
            // Mirror position in the first half: mid - (k - mid) -> 2 * mid - k
            char mirroredBit = findKthBit(n - 1, 2 * mid - k);
            return mirroredBit == '0' ? '1' : '0';
        }
    }
}
