package org.learn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
