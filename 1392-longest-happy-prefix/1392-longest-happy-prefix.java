class Solution {
    public String longestPrefix(String s) {
        int n = s.length();
        int[] lps = new int[n];
        
        // Build the KMP LPS table for string s
        for (int i = 1; i < n; i++) {
            int j = lps[i - 1];
            
            // Fall back in the prefix table if there's a mismatch
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = lps[j - 1];
            }
            
            // If the characters match, increment the length of the current matching prefix
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            lps[i] = j;
        }
        
        // The last value in the LPS array gives the length of the longest happy prefix
        int longestLen = lps[n - 1];
        
        // Return the corresponding substring prefix
        return s.substring(0, longestLen);
    }
}
