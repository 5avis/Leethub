class Solution {
    public int reverseDegree(String s) {
        int ans = 0;
        
        // Loop through the string from left to right
        for (int i = 0; i < s.length(); i++) {
            // Find the character's position in the reversed alphabet ('a' -> 26, 'z' -> 1)
            int reversePos = 26 - (s.charAt(i) - 'a');
            
            // Multiply by the 1-based index (i + 1) and add to the running total
            ans += reversePos * (i + 1);
        }
        
        return ans;
    }
}
