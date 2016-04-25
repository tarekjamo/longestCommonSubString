import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * Created by tarekray on 25/04/16.
 */
public class LongestCommonStringTest {

    @org.junit.Test
    public void test()
    {
        longestSubstringTest("one", "two", "beleive in yourself");
        longestSubstringTest("acceptanceOne", "acceptanceTwo", "it was this same force that caused objects to fall to the ground the");
    }

    private void longestSubstringTest(String first, String second, String expected) {
        ClassLoader classLoader = getClass().getClassLoader();
        File one = new File(classLoader.getResource(first).getFile());
        File two = new File(classLoader.getResource(second).getFile());

        //  System.out.println("Longest substring");
        //  System.out.println(LongestSubstring.find(one, two));
        long startTime = System.currentTimeMillis() ;
        Assert.assertTrue(LongestSubstring.find(one, two).equals(expected));
        long endTime = System.currentTimeMillis() ;

        long startTimeDP = System.currentTimeMillis() ;
        Assert.assertTrue(LongestSubstring.findDynamicProgramming(one, two).equals(expected));
        long endTimeDP = System.currentTimeMillis() ;

        long time = endTime - startTime;
        System.out.println("Normal algorithm : " + time) ;
        long dpTime = endTimeDP - startTimeDP;
        System.out.println("Dynamic programming algorithm : " + dpTime) ;

        Assert.assertTrue(dpTime < time);
    }

    @Test
    public void test_clean()
    {
        Assert.assertTrue(LongestSubstring.clean("Don't.").equals("dont"));
    }
}

