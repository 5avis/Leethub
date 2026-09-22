import java.util.Arrays;

class Solution {
    // Segment tree node to store products and remainder frequency counts
    static class Node {
        int[] cnt;
        int prod;

        Node(int k) {
            cnt = new int[k];
            prod = 1;
        }
    }

    private int K;
    private Node[] tree;

    // Merges left and right child ranges
    private Node merge(Node left, Node right) {
        if (left == null) return right;
        if (right == null) return left;
        
        Node res = new Node(K);
        res.prod = (left.prod * right.prod) % K;
        
        // 1. Prefixes entirely within the left child
        for (int r = 0; r < K; r++) {
            res.cnt[r] = left.cnt[r];
        }
        // 2. Prefixes that span across the left child into the right child
        for (int r = 0; r < K; r++) {
            int targetRemainder = (left.prod * r) % K;
            res.cnt[targetRemainder] += right.cnt[r];
        }
        return res;
    }

    // Standard segment tree build process
    private void build(int[] nums, int node, int start, int end) {
        if (start == end) {
            int val = nums[start] % K;
            tree[node] = new Node(K);
            tree[node].prod = val;
            tree[node].cnt[val] = 1;
            return;
        }
        int mid = start + (end - start) / 2;
        build(nums, 2 * node, start, mid);
        build(nums, 2 * node + 1, mid + 1, end);
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    // Point update to update values dynamically across queries
    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            int rem = val % K;
            tree[node].prod = rem;
            Arrays.fill(tree[node].cnt, 0);
            tree[node].cnt[rem] = 1;
            return;
        }
        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, val);
        } else {
            update(2 * node + 1, mid + 1, end, idx, val);
        }
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    // Range query to get the aggregated counts from start_i to n-1
    private Node query(int node, int start, int end, int l, int r) {
        if (l <= start && end <= r) {
            return tree[node];
        }
        int mid = start + (end - start) / 2;
        Node leftResult = null;
        Node rightResult = null;
        if (l <= mid) {
            leftResult = query(2 * node, start, mid, l, r);
        }
        if (r > mid) {
            rightResult = query(2 * node + 1, mid + 1, end, l, r);
        }
        return merge(leftResult, rightResult);
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.K = k;
        int n = nums.length;
        tree = new Node[4 * n];
        
        build(nums, 1, 0, n - 1);
        
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];
            
            // Perform the persistent point update
            update(1, 0, n - 1, idx, val);
            
            // Fetch remaining valid prefixes from start to the end of the array
            Node qRes = query(1, 0, n - 1, start, n - 1);
            ans[i] = qRes.cnt[x];
        }
        
        return ans;
    }
}
