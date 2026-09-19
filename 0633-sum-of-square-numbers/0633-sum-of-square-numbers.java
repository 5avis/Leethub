class Solution {
    public boolean judgeSquareSum(int c) {
        long a = 0;
        long b = (int) Math.sqrt(c);

        while (a <= b) {
            // Using long avoids integer overflow when squaring large numbers
            long currentSum = a * a + b * b;

            if (currentSum == c) {
                return true;
            } else if (currentSum < c) {
                a++;
            } else {
                b--;
            }
        }

        return false;
    }
}
