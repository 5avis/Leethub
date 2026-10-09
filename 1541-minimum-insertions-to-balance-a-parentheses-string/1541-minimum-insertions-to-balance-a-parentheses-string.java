class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int neededRight = 0; // Tracks the count of ')' needed
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                // Each '(' requires two consecutive ')'
                neededRight += 2;
                
                // If we currently need an odd number of ')', it means the 
                // previous '(' only got ONE ')'. We must fix it immediately
                // by inserting one ')' and decreasing our required count.
                if (neededRight % 2 == 1) {
                    insertions++; 
                    neededRight--;
                }
            } else { // c == ')'
                neededRight--;
                
                // If neededRight drops below 0, it means we found a ')' (or '))') 
                // without a matching '('. We must insert an opening '('
                if (neededRight < 0) {
                    insertions++;    // Insert '('
                    neededRight += 2; // This new '(' requires two ')' (one is satisfied by current index)
                }
            }
        }
        
        // Any remaining neededRight values at the end represent missing ')'
        return insertions + neededRight;
    }
}
