import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.*;


/**
 * Created by mowerlin on 21/01/2017.
 */

public class JUniteTest {
    String string = "myString";
    Solution solution = new Solution();

    SubsetProblems subsetProblems = new SubsetProblems();

    DPAndGreedy dpAndGreedy = new DPAndGreedy();

    Amazon amazon = new Amazon();
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
    public void testfindSubstring() {
//        System.out.println(solution.findSubstring("barfoothefoobarman", new String[]{"foo","bar"}));
        System.out.println(solution.findSubstring("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "good"}));
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

    @Test
    public void testnumSquares() {
        System.out.println(dpAndGreedy.numSquares(4));
    }

    @Test
    public void testminimumTotal() {
        List<Integer> one = new ArrayList<>();
        List<Integer> two = new ArrayList<>();
        List<Integer> three = new ArrayList<>();
        List<Integer> four = new ArrayList<>();

        one.add(2);
        two.add(3);
        two.add(4);
        three.add(6);
        three.add(5);
        three.add(7);
        four.add(4);
        four.add(1);
        four.add(8);
        four.add(3);


        List<List<Integer>> lists = new ArrayList<>();
        lists.add(one);
        lists.add(two);
        lists.add(three);
        lists.add(four);

        System.out.println(dpAndGreedy.minimumTotal(lists));
    }

    @Test
    public void testcoinChange() {
        System.out.println(dpAndGreedy.coinChange(new int[]{2}, 3));
    }

    @Test
    public void testnumDecodings() {
        System.out.println(dpAndGreedy.numDecodings("0000"));
        System.out.println(dpAndGreedy.numDecodings("70"));
        System.out.println(dpAndGreedy.numDecodings("110"));
    }

    @Test
    public void testmaxProfit2() {
        System.out.println(dpAndGreedy.maxProfit2(new int[]{1, 2, 3, 0}));
    }

    @Test
    public void testintegerBreak() {
        System.out.println(dpAndGreedy.integerBreak(10));
    }

    @Test
    public void testnthUglyNumber() {
        System.out.println(dpAndGreedy.nthUglyNumber(1));
        System.out.println(dpAndGreedy.nthUglyNumber(2));
        System.out.println(dpAndGreedy.nthUglyNumber(3));
        System.out.println(dpAndGreedy.nthUglyNumber(4));
        System.out.println(dpAndGreedy.nthUglyNumber(7));
        System.out.println(dpAndGreedy.nthUglyNumber(10));
    }

    @Test
    public void testnumberOfArithmeticSlices() {
        System.out.println(dpAndGreedy.numberOfArithmeticSlices(new int[]{1, 2, 3}));
        System.out.println(dpAndGreedy.numberOfArithmeticSlices(new int[]{1, 2, 3, 4}));
        System.out.println(dpAndGreedy.numberOfArithmeticSlices(new int[]{1, 2, 3, 4, 6, 7, 8, 9}));
    }

    @Test
    public void testmaximalSquare() {
        System.out.println(dpAndGreedy.maximalSquare(new char[][]{{'1'}}));
        System.out.println(dpAndGreedy.maximalSquare(new char[][]{{'0'}}));
        System.out.println(dpAndGreedy.maximalSquare(new char[][]{"11".toCharArray(), "11".toCharArray()}));
        System.out.println(dpAndGreedy.maximalSquare(new char[][]{"10100".toCharArray(), "10111".toCharArray()
                , "11111".toCharArray(), "10010".toCharArray()}));
    }


    @Test
    public void testlengthOfLIS() {
        System.out.println(dpAndGreedy.lengthOfLIS(new int[]{1}));
        System.out.println(dpAndGreedy.lengthOfLIS(new int[]{1, 2, 3}));
        System.out.println(dpAndGreedy.lengthOfLIS(new int[]{}));
        System.out.println(dpAndGreedy.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    }

    @Test
    public void testproductExceptSelf() {
        System.out.println(Arrays.toString(amazon.productExceptSelf(new int[]{1, 2, 3, 4})));
    }

    @Test
    public void testfindComplement() {
        System.out.println(amazon.findComplement(-1));
    }
}
