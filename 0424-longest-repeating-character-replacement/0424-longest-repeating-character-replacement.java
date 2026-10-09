class Solution {
    public int characterReplacement(String s, int k) {
        int[] count = new int[26]; // Frequency map for uppercase letters
        int maxFreq = 0;           // Highest frequency of a single character in the current window
        int left = 0;              // Left pointer of the window
        int maxLength = 0;         // Stores the final answer
        
        for (int right = 0; right < s.length(); right++) {
            // Include the current character in the window
            int currCharIdx = s.charAt(right) - 'A';
            count[currCharIdx]++;
            
            // Update the maximum frequency seen in the window so far
            maxFreq = Math.max(maxFreq, count[currCharIdx]);
            
            // Current window length is (right - left + 1)
            // If the characters to replace exceed k, shrink the window from the left
            if ((right - left + 1) - maxFreq > k) {
                count[s.charAt(left) - 'A']--;
                left++; // Move left pointer forward
            }
            
            // Update the maximum window length achieved
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
}
