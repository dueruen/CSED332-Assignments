package edu.postech.csed332.homework3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

/**
 * An abstract test class for MutableTree with blackbox test methods
 *
 * @param <V> type of vertices
 * @param <T> type of Tree
 */
@Disabled
public abstract class AbstractMutableTreeTest<V extends Comparable<V>, T extends MutableTree<V>> {

    T tree;
    V v1, v2, v3, v4, v5, v6, v7, v8;

    abstract boolean checkInv();    // call checkInv of tree

    @Test
    void testGetDepthRoot() {
        tree.addVertex(v1);
        Assertions.assertEquals(tree.getDepth(v1), 0);
    }

    @Test
    void testGetDepthTwo() {
        tree.addVertex(v1);
        tree.addVertex(v2);
        tree.addEdge(v1, v2);
        Assertions.assertEquals(tree.getDepth(v2), 1);
    }

    @Test
    void testGetDepthNoRoot() {
        Assertions.assertThrows(IllegalStateException.class, () -> tree.getDepth(v1));
    }

    @Test
    void testGetDepthNoVertex() {
        Assertions.assertTrue(tree.addVertex(v1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> tree.getDepth(v2));
    }

    @Test
    void testAddVertex() {
        Assertions.assertTrue(tree.addVertex(v3));
        Assertions.assertTrue(tree.containsVertex(v3));
        Assertions.assertTrue(checkInv());
    }

    @Test
    void testAddDuplicateVertices() {
        Assertions.assertTrue(tree.addVertex(v6));
        Assertions.assertTrue(tree.addVertex(v7));
        Assertions.assertFalse(tree.addVertex(v6));
        Assertions.assertTrue(tree.containsVertex(v6));
        Assertions.assertTrue(tree.containsVertex(v7));
        Assertions.assertTrue(checkInv());
    }

    @Test
    void testRemoveVertex() {
        tree.addVertex(v1);

        Assertions.assertTrue(tree.removeVertex(v1));
        Assertions.assertFalse(tree.containsVertex(v1));
        Assertions.assertTrue(checkInv());
    }

    @Test
    void testRemoveDuplicateVertices() {
        tree.addVertex(v6);
        tree.addVertex(v7);

        Assertions.assertTrue(tree.removeVertex(v6));
        Assertions.assertFalse(tree.removeVertex(v6));
        Assertions.assertFalse(tree.containsVertex(v6));
        Assertions.assertTrue(tree.containsVertex(v7));
        Assertions.assertTrue(checkInv());
    }

    @Test
    void testAddEdge() {
        tree.addVertex(v1);
        tree.addVertex(v4);
        Assertions.assertTrue(tree.addEdge(v1, v4));
        Assertions.assertTrue(tree.containsEdge(v1,v4));

        Assertions.assertTrue(checkInv());
    }

    @Test
    void testAddEdgeDuplicate() {
        tree.addVertex(v1);
        tree.addVertex(v4);
        tree.addEdge(v1, v4);
        Assertions.assertFalse(tree.addEdge(v1, v4));

        Assertions.assertTrue(checkInv());
    }

    @Test
    void testRemoveEdge() {
        tree.addVertex(v1);
        tree.addEdge(v1, v1);
        Assertions.assertTrue(tree.removeEdge(v1, v1));
        Assertions.assertFalse(tree.containsEdge(v1,v1));

        Assertions.assertTrue(checkInv());
    }

    @Test
    void testRemoveEdgeDuplicate() {
        tree.addVertex(v1);
        tree.addEdge(v1, v1);
        tree.removeEdge(v1, v1);
        Assertions.assertFalse(tree.removeEdge(v1, v1));

        Assertions.assertTrue(checkInv());
    }

    @Test
    void testGetRoot() {
        tree.addVertex(v1);
        Assertions.assertEquals(tree.getRoot(), Optional.of(v1));

        Assertions.assertTrue(checkInv());
    }

    @Test
    void testGetRootNull() {
        Assertions.assertEquals(tree.getRoot(), Optional.empty());

        Assertions.assertTrue(checkInv());
    }

    @Test
    void testgetDepth() {
        tree.addVertex(v1);
        tree.addVertex(v2);
        tree.addEdge(v1, v2);
        Assertions.assertEquals(tree.getDepth(v2), 1);
        Assertions.assertDoesNotThrow(() -> {
            tree.getDepth(v2);
        });

        Assertions.assertTrue(checkInv());
    }

    @Test
    void testgetHeight() {
        tree.addVertex(v1);
        tree.addVertex(v2);
        tree.addEdge(v1, v2);
        Assertions.assertEquals(tree.getHeight(), 1);

        Assertions.assertDoesNotThrow(() -> {
            tree.getHeight();
        });
        Assertions.assertTrue(checkInv());
    }

    @Test
    void testgetChildren() {
        tree.addVertex(v1);
        tree.addVertex(v2);
        tree.addEdge(v1, v2);
        Assertions.assertEquals(tree.getChildren(v1), Set.of(v2));

        Assertions.assertTrue(checkInv());
    }

    @Test
    void testgetParent() {
        tree.addVertex(v1);
        tree.addVertex(v2);
        tree.addEdge(v1, v2);
        Assertions.assertEquals(tree.getParent(v2), Optional.of(v1));

        Assertions.assertTrue(checkInv());
    }

    // TODO: write black-box test casess for each method of MutableTree with respect to
    //  the specification, including the methods of Tree that MutableTree extends.
}
