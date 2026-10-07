class Solution {
    public int magicalString(int n) {
        if (n <= 0) return 0;
        if (n <= 3) return 1; // The string "122" has exactly one '1'
        
        // Use an array to simulate the sequence generation up to size n
        int[] arr = new int[n + 1];
        arr[0] = 1;
        arr[1] = 2;
        arr[2] = 2;
        
        int head = 2;   // Reads the frequency of the next group
        int tail = 3;   // Writes the next characters
        int num = 1;    // The character to append next (alternates between 1 and 2)
        int count1 = 1; // Pre-count the first '1' at index 0
        
        while (tail < n) {
            int repetitions = arr[head];
            
            for (int i = 0; i < repetitions && tail < n; i++) {
                arr[tail] = num;
                if (num == 1) {
                    count1++;
                }
                tail++;
            }
            
            // Alternate the number between 1 and 2 (3 - 1 = 2, 3 - 2 = 1)
            num = 3 - num;
            head++;
        }
        
        return count1;
    }
}
