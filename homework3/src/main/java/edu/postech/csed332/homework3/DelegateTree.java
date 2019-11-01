package edu.postech.csed332.homework3;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

/**
 * An implementation of Tree that delegates to a given instance of Graph. This class
 * is a wrapper of a MutableGraph instance that enforces the Tree invariant.
 * NOTE: you should NOT add more member variables to this class.
 *
 * @param <N> type of vertices, which must be immutable and comparable
 */
public class DelegateTree<N extends Comparable<N>> implements MutableTree<N> {

    /**
     * A root vertex of this tree; {@code null} for an empty tree.
     */
    private @Nullable N root;

    /**
     * The underlying graph of this tree
     */
    private final @NotNull MutableGraph<N> delegate;

    /**
     * A map assigning a depth to each vertex in this tree
     */
    private final @NotNull Map<N, Integer> depthMap;

    /**
     * Creates an empty tree that delegates to a given graph.
     *
     * @param emptyGraph an empty graph
     * @throws IllegalArgumentException if {@code emptyGraph} is not empty
     */
    public DelegateTree(@NotNull MutableGraph<N> emptyGraph) {
        if (!emptyGraph.getVertices().isEmpty())
            throw new IllegalArgumentException();

        delegate = emptyGraph;
        depthMap = new HashMap<>();
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
        if (!depthMap.containsKey(vertex)) {
            throw new IllegalArgumentException();
        }
        return depthMap.get(vertex);
    }

    @Override
    public int getHeight() {
        if (root == null) {
            throw new IllegalStateException();
        }
        int maxDept = 0;
        for (Map.Entry<N, Integer> n : depthMap.entrySet()) {
            if (n.getValue() > maxDept) {
                maxDept = n.getValue();
            }
        }
        return maxDept;
    }

    @Override
    public boolean containsVertex(@NotNull N vertex) {
        return delegate.containsVertex(vertex);
    }

    @Override
    public boolean addVertex(@NotNull N vertex) {
        if (root == null) {
            root = vertex;
        }
        boolean res = delegate.addVertex(vertex);
        if (res) {
            depthMap.put(vertex,0);
        }
        return res;
    }

    @Override
    public boolean removeVertex(@NotNull N vertex) {
        boolean res = delegate.removeVertex(vertex);
        if (res) {
            depthMap.remove(vertex);
        }
        return res;
    }

    @Override
    public boolean containsEdge(@NotNull N source, @NotNull N target) {
        return delegate.containsEdge(source, target);
    }

    @Override
    public boolean addEdge(@NotNull N source, @NotNull N target) {
        boolean res = delegate.addEdge(source, target);
        if (res) {
            depthMap.put(target, depthMap.get(source) + 1);
        }
        return res;
    }

    @Override
    public boolean removeEdge(@NotNull N source, @NotNull N target) {
        return delegate.removeEdge(source, target);
    }

    @Override
    public @NotNull Set<N> getSources(N target) {
        return delegate.getSources(target);
    }

    @Override
    public @NotNull Set<N> getTargets(N source) {
        return delegate.getTargets(source);
    }

    @Override
    public @NotNull Set<N> getVertices() {
        return delegate.getVertices();
    }

    @Override
    public @NotNull Set<Edge<N>> getEdges() {
        return delegate.getEdges();
    }

    /**
     * Checks if all class invariants hold for this object
     *
     * @return true if the representation of this tree is valid
     */
    boolean checkInv() {
        Set<N> vertices = delegate.getVertices();
        for (Edge<N> e : delegate.getEdges()) {
                if (!vertices.contains(e.getSource()) || !vertices.contains(e.getTarget())) {
                    return false;
                }
        }
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
