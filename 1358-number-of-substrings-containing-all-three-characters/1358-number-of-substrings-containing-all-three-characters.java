class Solution {
    public int numberOfSubstrings(String s) {
        // Track the most recent index seen for each character
        int lastA = -1;
        int lastB = -1;
        int lastC = -1;
        int count = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            
            // Update the last seen position
            if (c == 'a') lastA = right;
            else if (c == 'b') lastB = right;
            else if (c == 'c') lastC = right;
            
            // If we have seen all three characters at least once
            if (lastA != -1 && lastB != -1 && lastC != -1) {
                // The number of valid substrings ending at 'right' 
                // is equal to 1 + the minimum index of the three characters
                int minIndex = Math.min(lastA, Math.min(lastB, lastC));
                count += minIndex + 1;
            }
        }
        
        return count;
    }
}
