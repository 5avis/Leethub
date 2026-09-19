class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        // Step 1: Find the closest x-coordinate on the rectangle to the circle's center
        int closestX = Math.max(x1, Math.min(x2, xCenter));
        
        // Step 2: Find the closest y-coordinate on the rectangle to the circle's center
        int closestY = Math.max(y1, Math.min(y2, yCenter));
        
        // Step 3: Calculate the distance components between the center and the closest point
        int distanceX = xCenter - closestX;
        int distanceY = yCenter - closestY;
        
        // Step 4: Check if the squared distance is within the squared radius
        return (distanceX * distanceX) + (distanceY * distanceY) <= (radius * radius);
    }
}
