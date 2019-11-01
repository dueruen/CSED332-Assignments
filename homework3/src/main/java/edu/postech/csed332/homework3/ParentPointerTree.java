package edu.postech.csed332.homework3;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

/**
 * An implementation of Tree, where each vertex has a reference to its parent node but
 * no references to its children.
 *
 * @param <N> type of vertices, which must be immutable and comparable
 */
public class ParentPointerTree<N extends Comparable<N>> implements MutableTree<N> {

    private static class Node<V> {
        final @Nullable V parent;
        final int depth;

        Node(@Nullable V parent, int depth) {
            this.parent = parent;
            this.depth = depth;
        }
    }

    /**
     * A root vertex of this tree; {@code null} for an empty tree.
     */
    private @Nullable N root;

    /**
     * A map assigning to each vertex a pair of a parent reference and a depth. The parent
     * of the root is {@code null}. For example, a tree with four vertices {v1, v2, v3, v4}
     * and three edges {(v1,v2), (v1,v3), (v2,v4)}, where v1 is the root, is represented by
     * the map {v1 |-> (null,0), v2 |-> (v1,1), v3 |-> (v1,1), v4 |-> (v2,2)}.
     */
    private final @NotNull SortedMap<N, Node<N>> nodeMap;

    /**
     * Creates an empty parent pointer tree.
     */
    public ParentPointerTree() {
        this.root = null;
        this.nodeMap = new TreeMap<>();
    }

    @Override
    public @NotNull Optional<N> getRoot() {
        if (root == null) {
            return Optional.empty();
        }
        return Optional.of(root);
    }

    @Override
    public int getDepth(@NotNull N vertex) {
        if (root == null) {
            throw new IllegalStateException();
        }
        if (!nodeMap.containsKey(vertex)) {
            throw new IllegalArgumentException();
        }
        return nodeMap.get(vertex).depth;
    }

    @Override
    public int getHeight() {
        if (root == null) {
            throw new IllegalStateException();
        }
        int maxDept = 0;
        for (Map.Entry<N, Node<N>> n : nodeMap.entrySet()) {
            if (n.getValue().depth > maxDept) {
                maxDept = n.getValue().depth;
            }
        }
        return maxDept;
    }

    @Override
    public boolean containsVertex(@NotNull N vertex) {
        return nodeMap.containsKey(vertex);
    }

    @Override
    public boolean addVertex(@NotNull N vertex) {
        if (root == null) {
            root = vertex;
        }
        if (containsVertex(vertex)) {
            return false;
        }
        nodeMap.put(vertex, new Node<>(null, 0));
        return true;
    }

    @Override
    public boolean removeVertex(@NotNull N vertex) {
        if (!containsVertex(vertex)) {
            return false;
        }
        nodeMap.remove(vertex);
        return true;
    }

    @Override
    public boolean containsEdge(@NotNull N source, @NotNull N target) {
        return nodeMap.get(target).parent == source;
    }

    @Override
    public boolean addEdge(@NotNull N source, @NotNull N target) {
        if (nodeMap.get(target).parent == source) {
            return false;
        }
        nodeMap.put(target, new Node<>(source, nodeMap.get(source).depth + 1));
        return true;
    }

    @Override
    public boolean removeEdge(@NotNull N source, @NotNull N target) {
        if (!containsVertex(target)) {
            return false;
        }
        if (nodeMap.get(target).parent != source) {
            return false;
        }
        nodeMap.put(target, new Node<>(null, 0));
        return true;
    }

    @Override
    public @NotNull Set<N> getSources(N target) {
        return new HashSet<>(Arrays.asList(nodeMap.get(target).parent));
    }

    @Override
    public @NotNull Set<N> getTargets(N source) {
        Set<N> targets = new HashSet<>();
        for (Map.Entry<N, Node<N>> n : nodeMap.entrySet()) {
            if (n.getValue().parent == source) {
                targets.add(n.getKey());
            }
        }
        return targets;
    }

    @Override
    public @NotNull Set<N> getVertices() {
        return nodeMap.keySet();
    }

    @Override
    public @NotNull Set<Edge<N>> getEdges() {
        Set<Edge<N>> edges = new HashSet<>();
        for (Map.Entry<N, Node<N>> n : nodeMap.entrySet()) {
            if (n.getValue().parent != null) {
                edges.add(new Edge<>(n.getValue().parent, n.getKey()));
            }
        }
        return edges;
    }

    /**
     * Checks if all class invariants hold for this object
     *
     * @return true if the representation of this tree is valid
     */
    boolean checkInv() {
        return true;
    }

    /**
     * Provides a human-readable string representation for the abstract value of the tree
     *
     * @return a string representation
     */
    @Override
    public String toString() {
        return String.format("[root: %s, vertex: {%s}, edge: {%s}]",
                root,
                getVertices().stream().map(N::toString).collect(Collectors.joining(", ")),
                getEdges().stream().map(Edge::toString).collect(Collectors.joining(", ")));
    }
}
