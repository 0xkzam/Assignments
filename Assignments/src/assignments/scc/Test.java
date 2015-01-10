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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Kasun Amarasena
 */
public class Test {

    public static void main(String[] arg) {

        Test.test1();
//        Test.test2();
//        Test.test3();
//        Test.test4();
//        Test.testCase(7);
    }

    public static void test1() {
        Vertex s = new Vertex("s");
        Vertex v = new Vertex("v");
        Vertex t = new Vertex("t");
        Vertex w = new Vertex("w");
        Vertex a = new Vertex("a");
        Vertex b = new Vertex("b");

        Edge e1 = new Edge(s, v);
        Edge e2 = new Edge(s, w);
        Edge e3 = new Edge(v, w);
        Edge e4 = new Edge(v, t);
        Edge e5 = new Edge(w, t);
        Edge e6 = new Edge(t, s);
        Edge e7 = new Edge(a, b);
        Edge e8 = new Edge(t, a);

        Graph g = new Graph();
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);
        g.addEdge(e5);
        g.addEdge(e6);
        g.addEdge(e7);
        g.addEdge(e8);

        g.dfsLoop();
        g.print();        
               
    }

    public static void test4() {
        Runtime r = Runtime.getRuntime();
        r.gc();
        long l = r.maxMemory();
        long d = 1024*1024;
        System.out.println("free Mem: "+(l/d));
        System.out.println("free Mem. 1: "+(l-r.freeMemory())/d);
        Graph g = new Graph();
        File f = new File("E:\\&_Algorithms Analysis & Design\\Assignements\\SCC.txt");
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(f));
            String text;
            while ((text = reader.readLine()) != null) {
                String[] txt = text.split("\\s");
                Integer i = Integer.parseInt(txt[0]);
                Integer j = Integer.parseInt(txt[1]);
                //g.addEdge(new Edge(new Vertex(i), new Vertex(j)));
                g.addEdge(new Vertex(i), new Vertex(j));
            }
            System.out.println("(after while loop)free Mem. 2: "+(l-r.freeMemory())/d);
        } catch (FileNotFoundException e) {
            System.out.println(" " + e);
        } catch (IOException ex) {
            System.out.println(" " + ex);
        } finally {
            reader = null;
            f = null;
        }
        System.out.println("(before dfs)free Mem. 3: "+(l-r.freeMemory())/d);
        g.dfsLoop();
        System.out.println("(after dfs)free Mem. 4: "+(l-r.freeMemory())/d);
        g.print();
        g = null;
        r.gc();
        System.out.println("final free Mem. 5: "+(l-r.freeMemory())/d);
        
    }

    public static void test2() {
        Vertex a1 = new Vertex(1);
        Vertex a2 = new Vertex(2);
        Vertex a3 = new Vertex(3);
        Vertex a4 = new Vertex(4);
        Vertex a5 = new Vertex(5);
        Vertex a6 = new Vertex(6);
        Vertex a7 = new Vertex(7);
        Vertex a8 = new Vertex(8);
        Vertex a9 = new Vertex(9);
        
//        Vertex x = new Vertex("x");

        Edge[] edges = {new Edge(a1, a7), new Edge(a4, a1), new Edge(a7, a4),
                        new Edge(a6, a3),
                        new Edge(a7, a9),new Edge(a3, a9),
                        //new Edge(a7, x),new Edge(a3, x),new Edge(x,a9),
                        new Edge(a9, a6), new Edge(a6, a8), new Edge(a8, a2),
                        new Edge(a2, a5), new Edge(a5, a8)};
        
        
        
        Graph g =new Graph();
        for (Edge edge : edges) {
            g.addEdge(edge);
        }
        g.dfsLoop();
        g.print();
    }

    public static void test3() {
        Vertex y = new Vertex("y");
        Vertex x = new Vertex("x");
        Edge[] edges = {new Edge(x, y)};
        Graph g =new Graph();
        for (Edge edge : edges) {
            g.addEdge(edge);
        }
        g.dfsLoop();
        g.print();
    }
    
    public static void testCase(int k){
        Graph g = new Graph();
        File f = new File("E:\\&_Algorithms Analysis & Design\\Assignements\\SCC_test_"+k+".txt");
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(f));
            String text;
            while ((text = reader.readLine()) != null) {
                String[] txt = text.split("\\s");
                Integer i = Integer.parseInt(txt[0]);
                Integer j = Integer.parseInt(txt[1]);
                //g.addEdge(new Edge(new Vertex(i), new Vertex(j)));
                g.addEdge(new Vertex(i), new Vertex(j));                
            }            
        } catch (FileNotFoundException e) {
            System.out.println(" " + e);
        } catch (IOException ex) {
            System.out.println(" " + ex);
        } finally {
            reader = null;
            f = null;
        }
        g.dfsLoop();
        g.print();
    }
    

}
