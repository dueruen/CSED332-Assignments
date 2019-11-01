package edu.postech.csed332.homework3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Set;

/**
 * An abstract test class for MutableGraph with blackbox test methods
 *
 * @param <V> type of vertices
 * @param <G> type of Graph
 */
@Disabled
public abstract class AbstractMutableGraphTest<V extends Comparable<V>, G extends MutableGraph<V>> {

    G graph;
    V v1, v2, v3, v4, v5, v6, v7, v8;

    abstract boolean checkInv();    // call checkInv of graph

    @Test
    void testAddVertexGraph() {
        Assertions.assertTrue(graph.addVertex(v1));
        Assertions.assertTrue(graph.containsVertex(v1));
        Assertions.assertTrue(checkInv());
    }

    @Test
    void testAddDuplicateVerticesGraph() {
        Assertions.assertTrue(graph.addVertex(v6));
        Assertions.assertTrue(graph.addVertex(v7));
        Assertions.assertFalse(graph.addVertex(v6));
        Assertions.assertTrue(graph.containsVertex(v6));
        Assertions.assertTrue(graph.containsVertex(v7));
        Assertions.assertTrue(checkInv());
    }

    @Test
    void testFindReachableVerticesGraph() {
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addEdge(v1, v1);
        graph.addEdge(v1, v2);
        graph.addEdge(v3, v1);
        graph.addEdge(v3, v2);
        graph.addVertex(v4);

        Assertions.assertEquals(graph.findReachableVertices(v1), Set.of(v1, v2));
        Assertions.assertEquals(graph.findReachableVertices(v2), Set.of(v2));
        Assertions.assertEquals(graph.findReachableVertices(v3), Set.of(v1, v2, v3));
        Assertions.assertEquals(graph.findReachableVertices(v4), Set.of(v4));
        Assertions.assertEquals(graph.findReachableVertices(v5), Collections.emptySet());
        Assertions.assertTrue(checkInv());
    }

    @Test
    void testRemoveVertexGraph() {
        graph.addVertex(v1);

        Assertions.assertTrue(graph.removeVertex(v1));
        Assertions.assertFalse(graph.containsVertex(v1));
        Assertions.assertTrue(checkInv());
    }

    @Test
    void testRemoveDuplicateVerticesGraph() {
        graph.addVertex(v6);
        graph.addVertex(v7);

        Assertions.assertTrue(graph.removeVertex(v6));
        Assertions.assertFalse(graph.removeVertex(v6));
        Assertions.assertFalse(graph.containsVertex(v6));
        Assertions.assertTrue(graph.containsVertex(v7));
        Assertions.assertTrue(checkInv());
    }

    @Test
    void testAddEdgeGraph() {
        graph.addVertex(v1);
        Assertions.assertTrue(graph.addEdge(v1, v1));
        Assertions.assertTrue(graph.containsEdge(v1,v1));

        Assertions.assertTrue(checkInv());
    }

    @Test
    void testAddEdgeDuplicateGraph() {
        graph.addVertex(v1);
        graph.addEdge(v1, v1);
        Assertions.assertFalse(graph.addEdge(v1, v1));

        Assertions.assertTrue(checkInv());
    }

    @Test
    void testRemoveEdgeGraph() {
        graph.addVertex(v1);
        graph.addEdge(v1, v1);
        Assertions.assertTrue(graph.removeEdge(v1, v1));
        Assertions.assertFalse(graph.containsEdge(v1,v1));

        Assertions.assertTrue(checkInv());
    }

    @Test
    void testRemoveEdgeDuplicateGraph() {
        graph.addVertex(v1);
        graph.addEdge(v1, v1);
        graph.removeEdge(v1, v1);
        Assertions.assertFalse(graph.removeEdge(v1, v1));

        Assertions.assertTrue(checkInv());
    }

    @Test
    void testcontainsVertexGraph() {
        graph.addVertex(v1);
        Assertions.assertTrue(graph.containsVertex(v1));

        Assertions.assertTrue(checkInv());
    }

    @Test
    void testcontainsEdgeGraph() {
        graph.addVertex(v1);
        graph.addEdge(v1,v1);
        Assertions.assertTrue(graph.containsEdge(v1,v1));

        Assertions.assertTrue(checkInv());
    }

    @Test
    void testgetSourcesGraph() {
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addEdge(v1,v2);
        Assertions.assertEquals(graph.getSources(v2), Set.of(v1));

        Assertions.assertTrue(checkInv());
    }

    @Test
    void testgetTargetsGraph() {
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addEdge(v1,v2);
        Assertions.assertEquals(graph.getTargets(v1), Set.of(v2));

        Assertions.assertTrue(checkInv());
    }

    @Test
    void testgetVerticesGraph() {
        graph.addVertex(v1);
        graph.addVertex(v2);
        Assertions.assertEquals(graph.getVertices(), Set.of(v1, v2));

        Assertions.assertTrue(checkInv());
    }

    @Test
    void testgetEdgesGraph() {
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addEdge(v1,v2);
        Assertions.assertEquals(graph.getEdges(), Set.of(new Edge(v1, v2)));

        Assertions.assertTrue(checkInv());
    }

    // TODO: write black-box test cases for each method of MutableGraph with respect to
    //  the specification, including the methods of Graph that MutableGraph extends.

}
