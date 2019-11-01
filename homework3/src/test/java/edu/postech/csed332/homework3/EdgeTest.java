package edu.postech.csed332.homework3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EdgeTest {
    @Test
    void testgetSource() {
        Edge e = new Edge(1,2);
        Assertions.assertEquals(e.getSource(), 1);
    }

    @Test
    void testgetSourceNotEquals() {
        Edge e = new Edge(1,2);
        Assertions.assertNotEquals(e.getSource(), 2);
    }

    @Test
    void testgetTarget() {
        Edge e = new Edge(1,2);
        Assertions.assertEquals(e.getTarget(), 2);
    }

    @Test
    void testgetTargetNotEquals() {
        Edge e = new Edge(1,2);
        Assertions.assertNotEquals(e.getTarget(), 1);
    }

    @Test
    void testcompareTo() {
        Edge e = new Edge(1,2);
        Edge ee = new Edge(1,2);
        Assertions.assertEquals(e.compareTo(ee), 0);
    }

    @Test
    void testtoString() {
        Edge e = new Edge(1,2);
        Assertions.assertEquals(e.toString(), "(1,2)");
    }

    @Test
    void testequals() {
        Edge e = new Edge(1,2);
        Edge ee = new Edge(1,2);
        Assertions.assertEquals(e.equals(ee), true);
    }

    @Test
    void testequalsNot() {
        Edge e = new Edge(1,2);
        Edge ee = new Edge(1,3);
        Assertions.assertEquals(e.equals(ee), false);
    }

}
