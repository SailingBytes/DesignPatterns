package exercise18;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class Evaluate
{
    @Test
    public void singleRatOrigOrigTest()
    {
        GameOrig GameOrig = new GameOrig();
        RatOrig RatOrig = new RatOrig(GameOrig, "rat 1");
        assertEquals(1, RatOrig.attack);
    }

    @Test
    public void twoRatOrigTest()
    {
        GameOrig GameOrig = new GameOrig();
        RatOrig RatOrig = new RatOrig(GameOrig, "rat 1");
        System.out.println();
        System.out.println();
        System.out.println();
        RatOrig RatOrig2 = new RatOrig(GameOrig, "rat 2");
        assertEquals(2, RatOrig.attack);
        assertEquals(2, RatOrig2.attack);
    }

    @Test
    public void threeRatOrigsOneDies()
            throws IOException
    {
        GameOrig GameOrig = new GameOrig();

        RatOrig RatOrig = new RatOrig(GameOrig, "rat1");
        assertEquals(1, RatOrig.attack);
        System.out.println();
        System.out.println();
        System.out.println();

        RatOrig RatOrig2 = new RatOrig(GameOrig, "rat2");
        assertEquals(2, RatOrig.attack);
        assertEquals(2, RatOrig2.attack);

        System.out.println();
        System.out.println();
        System.out.println();
        try (RatOrig RatOrig3 = new RatOrig(GameOrig, "rat3"))
        {
            assertEquals(3, RatOrig.attack);
            assertEquals(3, RatOrig2.attack);
            assertEquals(3, RatOrig3.attack);
        }

        assertEquals(2, RatOrig.attack);
        assertEquals(2, RatOrig2.attack);
    }
}