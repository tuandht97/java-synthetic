package org.learn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 You may assume that each input would have exactly one solution, and you may not use the same element twice.
 You can return the answer in any order.

 Example 1:

 Input: nums = [2,7,11,15], target = 9
 Output: [0,1]
 Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].

 Example 2:

 Input: nums = [3,2,4], target = 6
 Output: [1,2]
*/

public class TwoSum {

    private static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];

        for(int i = 0; i < nums.length; i++){
            for(int j = i + 1; j < nums.length; j++) {
                if(nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    break;
                }
            }
        }

        return result;
    }

    private static int[] bestSolution(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i],i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i){
                return new int[]{i,map.get(complement)};
            }
        }
        //In case there is no solution we'll return null
        return null;
    }

    public static void main(String[] args) {
        int[] nums = {2,7,11,15};
        int target = 9;
        System.out.println(Arrays.toString(twoSum(nums, target)));

        int[] nums2 = {3,3};
        int target2 = 6;
        System.out.println(Arrays.toString(bestSolution(nums2, target2)));

        int[] nums3 = {3,2,4};
        int target3 = 6;
        System.out.println(Arrays.toString(twoSum(nums3, target3)));
    }
}
