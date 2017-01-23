import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by mowerlin on 21/01/2017.
 */

public class JUniteTest {
    String string = "myString";
    Solution solution = new Solution();

    SubsetProblems subsetProblems = new SubsetProblems();

    DPAndGreedy dpAndGreedy = new DPAndGreedy();

//    @Test
//    public void myTest() {
//        Assert.assertEquals(solution.toString(), string);
//    }

    @Test
    @Ignore
    public void test84() {
        Assert.assertEquals(solution.largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}), 10);
    }

    @Test
    @Ignore
    public void test494() {
        Assert.assertEquals(solution.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3), 5);
    }

    @Test
    @Ignore
    public void test78() {
        System.out.println(solution.subsets(new int[]{1, 2, 3}));
    }

    @Test
    public void test39() {
        System.out.println(subsetProblems.combinationSum(new int[]{2, 3, 6, 7}, 7));
    }

    @Test
    public void test77() {
        System.out.println(subsetProblems.combine(4, 2));
        System.out.println(subsetProblems.combine(1, 1));
    }

    @Test
    public void test40() {
        System.out.println(subsetProblems.combinationSum2(new int[]{10, 1, 2, 7, 6, 6, 1, 5}, 8));
//        System.out.println(subsetProblems.combinationSum2(1, 1));
    }

    @Test
    public void test46() {
        System.out.println(subsetProblems.permute(new int[]{1, 2, 3}));
    }

    @Test
    public void test47() {
        System.out.println(subsetProblems.permuteUnique(new int[]{3, 3, 0, 3}));
    }


    @Test
    public void testwordBreak() {
        System.out.println(dpAndGreedy.wordBreak("leetcode", new ArrayList<>(Arrays.asList(new String[]{"leet", "code"}))));
    }

}
