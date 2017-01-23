/**
 * Created by mowerlin on 21/01/2017.
 */

import java.lang.reflect.Array;
import java.util.*;

public class SubsetProblems {
    public List<List<Integer>> subsets(int[] nums) {
        // O(n * 2^n);
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        for (int i = 0; i < nums.length; i++) {
            List<Integer> current = new ArrayList<>();
            current.add(nums[i]);
            result.add(new ArrayList<>(current));
            helper(result, i + 1, current, nums);
        }
        return result;
    }

    private void helper(List<List<Integer>> result, int cI, List<Integer> current, int[] nums) {
        if (cI >= nums.length) {
            return;
        }
        for (int i = cI; i < nums.length; i++) {
            current.add(nums[i]);
            result.add(new ArrayList<>(current));
            helper(result, i + 1, current, nums);
            current.remove(current.size() - 1);
        }
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // Slightly less than O(n * 2^n);
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            List<Integer> current = new ArrayList<>();
            current.add(nums[i]);
            result.add(new ArrayList<>(current));
            helperWithDup(result, nums, i + 1, current);
        }
        return result;
    }

    private void helperWithDup(List<List<Integer>> result, int[] nums, int cI, List<Integer> current) {
        if (cI >= nums.length) {
            return;
        }
        for (int i = cI; i < nums.length; i++) {
            if (i > cI && nums[i] == nums[i - 1]) {
                continue;
            }
            current.add(nums[i]);
            result.add(new ArrayList<>(current));
            helperWithDup(result, nums, i + 1, current);
            current.remove(current.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // O(n*2^n)
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        helperCom(candidates, target, 0, new ArrayList<>(), result, 0);
        return result;
    }

    private void helperCom(int[] candidates, int target, int curSum, List<Integer> current, List<List<Integer>> result, int cI) {
        if (cI >= candidates.length || curSum > target) {
            return;
        }
        int tmpCurSum;
        for (int i = cI; i < candidates.length; i++) {
            tmpCurSum = curSum + candidates[i];
            if (tmpCurSum == target) {
                current.add(candidates[i]);
                result.add(new ArrayList<>(current));
                current.remove(current.size() - 1);
                return;
            } else if (tmpCurSum < target) {
                current.add(candidates[i]);
                helperCom(candidates, target, tmpCurSum, current, result, i);
                current.remove(current.size() - 1);
            } else {
                return;
            }
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        // O(C(n k)) = O(2^n)
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            List<Integer> current = new ArrayList<>();
            current.add(i);
            helperCombine(result, current, i + 1, n, k);
        }
        return result;
    }

    private void helperCombine(List<List<Integer>> result, List<Integer> current, int cI, int n, int k) {
        if (current.size() >= k) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (cI > n) {
            return;
        }
        for (int i = cI; i <= n; i++) {
            current.add(i);
            helperCombine(result, current, i + 1, n, k);
            current.remove(current.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // O(C(n k)) = O(2^n)
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        helperCom2(result, new ArrayList<>(), 0, 0, target, candidates);
        return result;
    }

    private void helperCom2(List<List<Integer>> result, List<Integer> current, int cI, int sum, int target, int[] candidates) {
        if (cI >= candidates.length) {
            return;
        }
        int tmp;
        for (int i = cI; i < candidates.length; i++) {
            if (i > cI && candidates[i] == candidates[i - 1]) {
                continue;
            }
            tmp = sum + candidates[i];
            current.add(candidates[i]);
            if (tmp == target) {
                result.add(new ArrayList<>(current));
            } else if (tmp < target) {
                helperCom2(result, current, i + 1, tmp, target, candidates);
            }
            current.remove(current.size() - 1);

            if (tmp >= target) {
                return;
            }

        }
    }

    public List<List<Integer>> permute(int[] nums) {
        // O(n!)
        boolean[] used = new boolean[nums.length];
        List<List<Integer>> result = new ArrayList<>();
        helperPer(result, used, nums, new ArrayList<>(), 0);
        return result;
    }

    private void helperPer(List<List<Integer>> result, boolean[] used, int[] nums, List<Integer> current, int count) {
        if (count == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            current.add(nums[i]);
            used[i] = true;
            helperPer(result, used, nums, current, count + 1);
            current.remove(current.size() - 1);
            used[i] = false;
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        helperPerUni(result, new ArrayList<>(), nums, new boolean[nums.length], 0);
        return result;
    }

    private void helperPerUni(List<List<Integer>> result, List<Integer> current, int[] nums, boolean[] used, int count) {
        if (count == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            if (i > 0 && !used[i - 1] && nums[i] == nums[i - 1]) {
                continue;
            }
            current.add(nums[i]);
            used[i] = true;
            helperPerUni(result, current, nums, used, count + 1);
            used[i] = false;
            current.remove(current.size() - 1);
        }
    }
}
