import java.util.HashSet;

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> window = new HashSet<>();
        
        for (int i = 0; i < nums.length; i++) {
            // If the window size exceeds k, remove the oldest element
            if (i > k) {
                window.remove(nums[i - k - 1]);
            }
            
            // If the element is already in the set, we found our duplicate within distance k
            if (!window.add(nums[i])) {
                return true;
            }
        }
        
        return false;
    }
}
