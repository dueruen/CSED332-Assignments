package edu.postech.csed332.homework3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class DoubleParentPointerMutableTreeTest extends AbstractMutableTreeTest<Double, ParentPointerTree<Double>> {

    @BeforeEach
    void setUp() {
        tree = new ParentPointerTree<>();
        v1 = 1.9;
        v2 = 2.8;
        v3 = 3.7;
        v4 = 4.6;
        v5 = 5.5;
        v6 = 6.4;
        v7 = 7.3;
        v8 = 8.2;
    }

    @Override
    boolean checkInv() {
        return tree.checkInv();
    }

    @Test
    void testgetEdges() {
        tree.addVertex(v1);
        tree.addVertex(v2);
        tree.addEdge(v1, v2);
        tree.addEdge(v1, v1);
        Assertions.assertEquals(tree.getEdges(), Set.of(new Edge(v1, v2), new Edge(v1, v1)));
    }

    @Test
    void testgetVertices() {
        tree.addVertex(v1);
        tree.addVertex(v2);
        Assertions.assertEquals(tree.getVertices(), Set.of(v1,v2));
    }

    @Test
    void testtoString() {
        tree.addVertex(v1);
        tree.addVertex(v2);
        tree.addEdge(v1, v1);
        Assertions.assertEquals(tree.toString(), "[root: 1.9, vertex: {1.9, 2.8}, edge: {(1.9,1.9)}]");
    }

    // TODO: write more white-box test cases to achieve more code coverage, if needed.
    // You do not need to add more test methods, if you tests already meet the desired coverage.
}
