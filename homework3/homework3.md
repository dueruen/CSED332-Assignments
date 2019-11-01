# Problem 1


Let $`\mathcal{N}`$ be the set of all elements of type $`\textsf{N}`$, and $`\mathsf{null} \notin \mathcal{N}`$ be an distinguished element to represent `null`. Write formal abstract specifications of the interfaces below with respect to following abstract values:

- A graph is a pair $`G = (V, E)`$, where $`V \subseteq \mathcal{N}`$ and $`E \subseteq V \times V`$.
- A tree is a triple $`T = (V, E, \hat{v})`$, where $`(V, E)`$ is a graph and $`\hat{v} \in \mathcal{N}`$ denotes the root.

Other data types, such as `boolean`, `int`, `Set<N>`, etc. have conventional abstract values, e.g., Boolean values, integers, and subsets of $`\mathcal{N}`$, etc.

## `Graph<N>`

Let $`G_{this} = (V_{this}, E_{this})`$ be an abstract value of the current graph object. 

##### Class invariant 

```math
\forall (v, w) \in E_{this}.\ v, w \in V_{this}
```
$\forall (v, w) \in E_{this}.\ v, w \in V_{this}$

##### containsVertex

```java 
boolean containsVertex(N vertex);
```

- requires: vertex is in $`\mathcal{N}`$ and not $`\mathsf{null}`$
- ensures:  
    + returns true if vertex is in $`V_{this}`$; and
    - returns false, otherwise.

##### containsEdge

```java
boolean containsEdge(N source, N target);
```

- requires: source and target is in $`\mathcal{N}`$ and not $`\mathsf{null}`$
- ensures:
    + returns true if source and target is connected by an edge; and
    - returns false, otherwise.

##### getSources

```java
Set<N> getSources(N target);
```

- requires: target is in $`\mathcal{N}`$
- ensures:  
    + returns the set of target vertices that are connected to a given source vertex, by a directed edge from the given source to each target in the graph; and
    - returns the empty set, if source is not in the graph

##### getTargets

```java
Set<N> getTargets(N source);
```

- requires: nothing
- ensures:  
    + returns the set of vertices in the graph, immutable


## `Tree<N>`

Let $`T_{this} = (V_{this}, E_{this}, \hat{v}_{this})`$ be an abstract value of the current graph object. 

##### Class invariant 

Let $`T_{this} = (V_{this}, E_{this}, \hat{v}_{this})`$ be an abstract value of the current tree object,
and $`T_{next} = (V_{next}, E_{next}, \hat{v}_{next})`$ be an abstract value of the tree object _modified by_ the method call. 

##### getDepth

```java
int getDepth(N vertex);
```

- requires: 
  + vertex is in $`\mathcal{N}`$ and not $`\mathsf{null}`$; and
  + getRoot.isPresent()
- ensures:  
  + returns 0 if vertex is getRoot.get(); and
  + returns getDepth(getParent(vertex)) + 1, otherwise.

##### getHeight

```java
int getHeight();
```

- requires: 
    + getRoot.isPresent()
- ensures:  
    + returns the maximum depth in this tree

##### getRoot

```java
Optional<N> getRoot();
```

- requires: nothing
- ensures:  
    + returns if not empty, the root of this tree;
    + returns otherwise, Optional.empty()

##### getParent

```java
Optional<N> getParent(N vertex);
```

- requires: 
    + vertex is in $`\mathcal{N}`$
    + getRoot.isPresent()
- ensures:  
    + returns if not root, the parent of vertex;
    + returns otherwise, Optional.empty()


## `MutableGraph<N>`

Let $`G_{this} = (V_{this}, E_{this})`$ be an abstract value of the current graph object,
and $`G_{next} = (V_{next}, E_{next})`$ be an abstract value of the graph object _modified by_ the method call. 

##### Class invariant 

```math
\forall (v, w) \in E_{this}.\ v, w \in V_{this}
```

##### addVertex

```java
boolean addVertex(N vertex);
```

- requires: vertex is in $`\mathcal{N}`$ and not $`\mathsf{null}`$
- ensures:  
    + $`V_{next} = V_{this} \cup \{\texttt{vertex}\}`$; 
    + $`E_{next} = E_{this}`$ (the edges are not modified)
    + If $`G_{this}`$ satisfies the class invariant, $`G_{next}`$ also satisfies the class invariant; and
    + returns true if and only if $`\texttt{vertex} \notin V_{this}`$.

##### removeVertex

```java
boolean removeVertex(N vertex);
```

- requires: vertex is in $`\mathcal{N}`$ and not $`\mathsf{null}`$
- ensures:  
    + returns true, if the graph has changed;
    + returns false, otherwise

##### addEdge

```java
boolean addEdge(N source, N target);
```

- requires: source and target is in $`\mathcal{N}`$ and not $`\mathsf{null}`$
- ensures:
    + returns true, if the graph has changed;
    + returns false, otherwise

##### removeEdge

```java
boolean removeEdge(N source, N target);
```

- requires: source and target is in $`\mathcal{N}`$ and not $`\mathsf{null}`$
- ensures:  
    + returns true, if the graph has changed;
    + returns false, otherwise


## `MutableTree<N>`

##### Class invariant 

Let $`T_{this} = (V_{this}, E_{this}, \hat{v}_{this})`$ be an abstract value of the current tree object,
and $`T_{next} = (V_{next}, E_{next}, \hat{v}_{next})`$ be an abstract value of the tree object _modified by_ the method call. 

##### addVertex

```java
boolean addVertex(N vertex);
```

- requires: vertex is in $`\mathcal{N}`$ and not $`\mathsf{null}`$
- ensures:  
    + returns true, if the graph has changed;
    + returns false, otherwise

##### removeVertex

```java
boolean removeVertex(N vertex);
```

- requires: vertex is in $`\mathcal{N}`$ and not $`\mathsf{null}`$
- ensures:  
    + returns true, if the graph has changed;
    + returns false, otherwise

##### addEdge

```java
boolean addEdge(N source, N target);
```

- requires: source and target is in $`\mathcal{N}`$ and not $`\mathsf{null}`$
- ensures:  
    + returns true, if the graph has changed;
    + returns false, otherwise

##### removeEdge

```java
boolean removeEdge(N source, N target);
```

- requires: source and target is in $`\mathcal{N}`$ and not $`\mathsf{null}`$
- ensures:  
    + returns true, if the graph has changed;
    + returns false, otherwise


# Problem 2

Identify whether the abstract interfaces satisfy the Liskov substitution principle.
For each question, explain your reasoning _using the abstract specifications that you have defined in Problem 1_. 


##### `Tree<N>` and `Graph<N>`

* Is `Tree<N>` a subtype of `Graph<N>`?  
Yes it is

##### `MutableGraph<N>` and `Graph<N>`

* Is `MutableGraph<N>` a subtype of `Graph<N>`  
Yes it is

##### `MutableTree<N>` and `Tree<N>`

* Is `MutableTree<N>` a subtype of `Tree<N>`  
Yes it is

##### `MutableTree<N>` and `MutableGraph<N>`

* Is `MutableTree<N>` a subtype of `MutableGraph<N>`  
Yes it is