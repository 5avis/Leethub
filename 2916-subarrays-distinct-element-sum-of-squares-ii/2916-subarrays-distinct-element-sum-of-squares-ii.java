import java.util.HashMap;
import java.util.Map;

public class Solution {
    private static final int MOD = 1_000_000_007;

    // Segment Tree structure for Range Addition updates on Sum and Sum of Squares
    private static class SegmentTree {
        int n;
        long[] sum;       // Tracks \sum(x)
        long[] sumSq;     // Tracks \sum(x^2)
        long[] lazy;      // Tracks lazy increment propagation values

        public SegmentTree(int n) {
            this.n = n;
            this.sum = new long[4 * n];
            this.sumSq = new long[4 * n];
            this.lazy = new long[4 * n];
        }

        // Push lazy updates down to children
        private void push(int node, int start, int end) {
            if (lazy[node] == 0) return;

            long val = lazy[node];
            int mid = start + (end - start) / 2;
            int leftChild = 2 * node;
            int rightChild = 2 * node + 1;

            // Update left child
            updateNode(leftChild, start, mid, val);
            // Update right child
            updateNode(rightChild, mid + 1, end, val);

            // Clear active parent lazy status
            lazy[node] = 0;
        }

        // Helper to update a specific node's sum, sumSq, and lazy markers
        private void updateNode(int node, int start, int end, long val) {
            long count = end - start + 1;
            
            // New sumSq = sumSq + 2 * val * sum + val^2 * count
            long term1 = sumSq[node];
            long term2 = (2 * val % MOD) * sum[node] % MOD;
            long term3 = (val * val % MOD) * count % MOD;
            
            sumSq[node] = (term1 + term2 + term3) % MOD;
            sum[node] = (sum[node] + val * count) % MOD;
            lazy[node] = (lazy[node] + val) % MOD;
        }

        // Range Update: Increment everything in range [l, r] by 'val'
        public void update(int node, int start, int end, int l, int r, long val) {
            if (r < start || end < l) return; // Completely out of range

            if (l <= start && end <= r) { // Completely inside range
                updateNode(node, start, end, val);
                return;
            }

            push(node, start, end);
            int mid = start + (end - start) / 2;
            update(2 * node, start, mid, l, r, val);
            update(2 * node + 1, mid + 1, end, l, r, val);

            // Re-calculate parent state from children summaries
            sum[node] = (sum[2 * node] + sum[2 * node + 1]) % MOD;
            sumSq[node] = (sumSq[2 * node] + sumSq[2 * node + 1]) % MOD;
        }
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        SegmentTree tree = new SegmentTree(n);
        Map<Integer, Integer> lastSeen = new HashMap<>();
        long totalSum = 0;

        for (int r = 0; r < n; r++) {
            // Find the starting boundary where this element is truly unique
            int l = lastSeen.containsKey(nums[r]) ? lastSeen.get(nums[r]) + 1 : 0;
            
            // Increment distinct element counters for all valid subarrays ending at 'r'
            tree.update(1, 0, n - 1, l, r, 1);
            
            // Track the element's updated placement
            lastSeen.put(nums[r], r);
            
            // Root node (index 1) always evaluates total current subarray squared sums up to 'r'
            totalSum = (totalSum + tree.sumSq[1]) % MOD;
        }

        return (int) totalSum;
    }
}
