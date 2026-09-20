import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] beautifulArray(int n) {
        List<Integer> currentList = new ArrayList<>();
        currentList.add(1); // Base case: an array of size 1 is always beautiful

        // Keep growing the beautiful array until it contains all elements up to n
        while (currentList.size() < n) {
            List<Integer> nextList = new ArrayList<>();

            // Step 1: Add all transformed odd elements first
            for (int x : currentList) {
                if (2 * x - 1 <= n) {
                    nextList.add(2 * x - 1);
                }
            }

            // Step 2: Add all transformed even elements second
            for (int x : currentList) {
                if (2 * x <= n) {
                    nextList.add(2 * x);
                }
            }

            currentList = nextList;
        }

        // Convert the list back into a primitive int[] array
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = currentList.get(i);
        }

        return result;
    }
}
