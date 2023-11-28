package org.learn;

/**
 * An image is represented by an m x n integer grid image where image[i][j] represents the pixel value of the image.
 * You are also given three integers sr, sc, and color. You should perform a flood fill on the image starting from the pixel image[sr][sc].
 * To perform a flood fill, consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color), and so on. Replace the color of all of the aforementioned pixels with color.
 * Return the modified image after performing the flood fill.
 * <p>
 * Example 1:
 * <p>
 * Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2
 * Output: [[2,2,2],[2,2,0],[2,0,1]]
 * Explanation: From the center of the image with position (sr, sc) = (1, 1) (i.e., the red pixel), all pixels connected by a path of the same color as the starting pixel (i.e., the blue pixels) are colored with the new color.
 * Note the bottom corner is not colored 2, because it is not 4-directionally connected to the starting pixel.
 * <p>
 * Example 2:
 * <p>
 * Input: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, color = 0
 * Output: [[0,0,0],[0,0,0]]
 * Explanation: The starting pixel is already colored 0, so no changes are made to the image.
 */
public class FloodFill {

    public static int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int oldColor = image[sr][sc];
        if (oldColor == color)
            return image;
        int x = image.length;
        int y = image[0].length;
        helper(image, sr, sc, oldColor, color, x, y);
        return image;
    }

    static void helper(int[][] image, int i, int j, int oldColor, int newColor, int x, int y) {
        if (i < 0 || j < 0 || i == x || j == y || image[i][j] != oldColor)
            return;

        //color that cell with new color
        image[i][j] = newColor;

        //Down
        helper(image, i + 1, j, oldColor, newColor, x, y);
        //up
        helper(image, i - 1, j, oldColor, newColor, x, y);
        //right
        helper(image, i, j + 1, oldColor, newColor, x, y);
        //left
        helper(image, i, j - 1, oldColor, newColor, x, y);
    }

    public static void main(String[] args) {
        int[][] image = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        int sr = 1;
        int sc = 1;
        int color = 2;

        int[][] newImage = floodFill(image, sr, sc, color);

        // In ra hình ảnh
        for (int i = 0; i < newImage.length; i++) {
            for (int j = 0; j < newImage[i].length; j++) {
                System.out.print(newImage[i][j] + " ");
            }
            System.out.println();
        }
    }
}
