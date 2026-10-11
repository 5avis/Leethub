import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        // Map to store: [number_value, array_index]
        Map<Integer, Integer> numMap = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            
            // If the complement exists in the map, we found our unique solution pair
            if (numMap.containsKey(complement)) {
                return new int[] { numMap.get(complement), i };
            }
            
            // Otherwise, cache the current number value and index in the map
            numMap.put(nums[i], i);
        }
        
        // Return an empty array if no solution is found (though constraints guarantee one exists)
        return new int[] {};
    }
}
