import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        // Step 1: Map the knowledge base for O(1) lookups
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }
        
        StringBuilder result = new StringBuilder();
        int n = s.length();
        
        // Step 2: Parse the string
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                StringBuilder key = new StringBuilder();
                i++; // Move past the '('
                
                // Read the key inside the brackets
                while (i < n && s.charAt(i) != ')') {
                    key.append(s.charAt(i));
                    i++;
                }
                
                // Step 3: Replace the key with its value or '?'
                String keyStr = key.toString();
                result.append(map.getOrDefault(keyStr, "?"));
            } else {
                result.append(c);
            }
        }
        
        return result.toString();
    }
}
