/*
 * Copyright (C) 2014 Kasun Amarasena
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package assignments.scc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author Kasun Amarasena
 */
public class Graph {

    private HashMap<Vertex, ArrayList<Vertex>> graph;
    private HashMap<Vertex, ArrayList<Vertex>> graphReverse;
    private Set<Vertex> explored;
    private int label;
    private int sccElementCount;

    private ArrayList<Integer> scc;
    private Vertex[] array;    
    private boolean secondPass;

    public Graph() {
        this.explored = new HashSet<>();
        this.graph = new HashMap<>();
        this.graphReverse = new HashMap<>();
        this.scc = new ArrayList<>();
    }

    /**
     * Insert the Edge to the HashMap(List representation)<br/>
     * Insert the reversed Edge to the 2nd HashMap(List representation)<br/>
     * key - source vertex of the Edge <br/>
     * value - ArrayList<Vertex> (destination vertices)<br/>
     * Calls private void addEdge(HashMap<Vertex, ArrayList> graph, Vertex
     * source, Vertex dest) method
     *
     * @param e Edge object
     */
    public void addEdge(Edge e) {
        Vertex source = e.getSource();
        Vertex dest = e.getDestination();
        addEdge(this.graph, source, dest);
        addEdge(this.graphReverse, dest, source);

    }

    /**
     * Insert an edge to the HashMap(List representation) directly<br/>
     * without using the Edge data structure key - source vertex of the Edge
     * <br/>
     * value - ArrayList<Vertex> (destination vertices)<br/>
     *
     * @param source Source Vertex
     * @param destination Destination Vertex
     */
    public void addEdge(Vertex source, Vertex destination) {
        addEdge(this.graph, source, destination);
        addEdge(this.graphReverse, destination, source);
    }

    /**
     * called by public void addEdge(Edge e) method
     *
     * @param graph HashMap<Vertex,ArrayList>
     * @param source Vertex
     * @param dest Vertex
     */
    private void addEdge(HashMap<Vertex, ArrayList<Vertex>> graph, Vertex source, Vertex dest) {
        ArrayList<Vertex> successors = graph.get(source);
        if (successors == null) {
            successors = new ArrayList<>();
            successors.add(dest);
        } else if (!successors.contains(dest)) {
            successors.add(dest);
        }
        graph.put(source, successors);
        if (!graph.containsKey(dest)) {
            graph.put(dest, null);
        }
    }

    /**
     * Recursive depth-first search <br/>
     * Generate stackOverFlows with a graph with 5.1M edges To increase stack
     * size -Xss100m
     *
     * @param graph HashMap<Vertex,ArrayList> (list representation)
     * @param start starting Vertex
     */
    public void dfsR(HashMap<Vertex, ArrayList<Vertex>> graph, Vertex start) {
        this.explored.add(start);

        /* Second pass - count elements of SCC */
        if (this.secondPass) {
            ++this.sccElementCount;
        }

        ArrayList<Vertex> list = graph.get(start);
        if (list != null) {
            for (Vertex v : list) {
                if (!this.explored.contains(v)) {
                    this.dfsR(graph, v);
                }
            }
        }

        /* First pass-Execution order */
        if (!this.secondPass) {
            start.setLabel(++this.label);
//            System.out.println("dfs- "+start+":"+start.getLabel());
        }
    }

    /**
     * Iterative depth-first search - First pass<br/>
     * Unlike recursive version this works well with the graph with 5.1M edges
     *
     * @param graph HashMap<Vertex,ArrayList> (list representation)
     * @param start starting Vertex
     */
    public void dfs(HashMap<Vertex, ArrayList<Vertex>> graph, Vertex start) {
        Stack<Vertex> stack = new Stack<>();
        stack.push(start);
        this.explored.add(start);

        /* que = similar to a Queue data struture to store the execution order */
        ArrayList<Vertex> que = new ArrayList<>();
        /* leaves = a set to store the termination nodes */
        Set<Vertex> leaves = new HashSet<>();

        while (!stack.isEmpty()) {
            Vertex curV = stack.pop();
            que.add(0, curV);

            /* list = successors of the parent vertex curV*/
            ArrayList<Vertex> list = graph.get(curV);
            if (list != null) {
                boolean isEnd = true;
                for (Vertex v : list) {
                    if (this.explored.add(v)) {
                        stack.push(v);
                        isEnd = false;
                    }
                }
                /* Selecting the leaves(termination nodes) */
                if (isEnd && leaves.add(curV)) {
                    curV.setLabel(++this.label);
                }
            } else {
                //System.out.println("null: "+curV);
                if (leaves.add(curV)) {
                    curV.setLabel(++this.label);
                }
            }
        }
        /* Set the labels of vertices except leaves(termination nodes) */
        for (Vertex vertex : que) {
            if (!leaves.contains(vertex)) {
                vertex.setLabel(++this.label);
            }
        }
    }

    /**
     * Iterative depth-first search - Second pass<br/>
     * Unlike recursive version this works well with the graph with 5.1M edges
     *
     * @param graph HashMap<Vertex,ArrayList> (list representation)
     * @param start starting Vertex
     * @param secondPass any boolean value
     */
    public void dfs(HashMap<Vertex, ArrayList<Vertex>> graph, Vertex start, boolean secondPass) {
        Stack<Vertex> stack = new Stack<>();
        stack.push(start);
        this.explored.add(start);

        while (!stack.isEmpty()) {
            Vertex curV = stack.pop();
            this.sccElementCount++;
            ArrayList<Vertex> list = graph.get(curV);
            if (list != null) {
                for (Vertex v : list) {
                    if (this.explored.add(v)) {
                        stack.push(v);
                    }
                }
            }
        }
    }

    /**
     * First pass of the Kosaraju's 2-pass algorithm Run DFS on every vertex of
     * the Graph that isn't explored. F
     *
     * @param graph HashMap<Vertex,ArrayList> (list representation)
     */
    private void dfsLoop(HashMap<Vertex, ArrayList<Vertex>> graph) {
        this.secondPass = false;
        this.explored.clear();        
        this.label = 0;
//        System.out.println("");
        for (Vertex vertex : graph.keySet()) {
//            System.out.println("dfsLoop- "+vertex+":"+vertex.getLabel());
            if (!this.explored.contains(vertex)) {
                this.dfsR(graph, vertex); /* Recursive dfs */
//                this.dfs(graph, vertex);    /* Iterative dfs-1st pass */                
            }   
//            System.out.println("dfsLoop- "+vertex+":"+vertex.getLabel());
        }
    }

    /**
     * Second pass of the Kosaraju's 2-pass algorithm Run DFS on every vertex of
     * the Graph that isn't explored.<br/>
     *
     * @param graph HashMap<Vertex,ArrayList> (list representation)
     * @param array Vertex[] - Sorted array of vertices
     */
    private void dfsLoop(HashMap<Vertex, ArrayList<Vertex>> graph, Vertex[] array) {
        this.secondPass = true;
        this.explored.clear();

        for (Vertex vertex : array) {
            if (!this.explored.contains(vertex)) {
                this.sccElementCount = 0;
                this.dfsR(graph, vertex);     /* Recursive dfs */
//                this.dfs(graph, vertex, true);   /* Iterative dfs-2nd pass*/

                this.scc.add(this.sccElementCount);
            }
        }
        this.explored = null;
    }

    /**
     * Run dfsLoop(HashMap<Vertex,ArrayList> graph) method <br/>
     * 1. run dfs on graphReverse (1st pass)<br/>
     * 2. run dfs on graph (2nd pass)<br/>
     */
    public void dfsLoop() {
        System.out.print("1. dfs on graphReverse-1st pass.....");
        this.dfsLoop(this.graphReverse);
        System.out.print("..completed \n");        
        
        /* Instead of using the keySet of the graph the set of explored vertices is used. 
         * Using the keyset appear to be buggy. 
         * Problem: After execution is completed and label is set by the DFS
         *          some keys in the graph(HashMap) indicate that still the label 
         *          has not been set. 
         * Current solution is using the explored set.
        */
        this.array = this.explored.toArray(new Vertex[0]);
        System.out.println("2. Vertices added to array");       

        try {
            Arrays.sort(this.array, new VertexOrderComparator());
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            return;
        } 
        System.out.print("3. Array sorted, dfs on graph-2nd pass.....");
        this.dfsLoop(this.graph, this.array);
        System.out.print("..completed \n");
    }

    
    public void print() {
        Collections.sort(this.scc);
        System.out.println("yy: "+this.graph.keySet());
        for (Vertex v : this.graph.keySet()) {
            System.out.println(v + "-" + v.getLabel() + ", ");
        }
    }

}
