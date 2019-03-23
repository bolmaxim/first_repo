package ru.stqa.pft.mantis.tests.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceTests {

    @Test
    public void  testDistance1(){
        Point p1 = new Point(2,4);
        Point p2 = new Point(6,8);
        Assert.assertEquals(p1.distance(p2), 5.656854249492381);
    }
    @Test
    public void  testDistance2(){
        Point p1 = new Point(5,6);
        Point p2 = new Point(7,10);
        Assert.assertEquals(p1.distance(p2), 2);
    }
}
