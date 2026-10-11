import java.util.Arrays;

public class Solution {
    public long sumScores(String s) {
        int n = s.length();
        int[] z = new int[n];
        
        // [l, r] represents the window of the rightmost segment match found so far
        int l = 0;
        int r = 0;
        
        for (int i = 1; i < n; i++) {
            // If i is within the current window, initialize z[i] using previously computed values
            if (i <= r) {
                z[i] = Math.min(r - i + 1, z[i - l]);
            }
            
            // Linearly extend the match outside the window
            while (i + z[i] < n && s.charAt(z[i]) == s.charAt(i + z[i])) {
                z[i]++;
            }
            
            // If the match extends past the current window 'r', update the window boundaries
            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }
        
        // Sum all values inside the Z-array
        long totalSum = 0;
        for (int val : z) {
            totalSum += val;
        }
        
        // Add 'n' for the full string itself (sn) and return
        return totalSum + n;
    }
}
