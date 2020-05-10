package cn.algo.graph;

import java.util.ArrayList;

/**
 * Created by leslie on 2019/8/30.
 */
public class Graph {
    ArrayList<Vertex> vertexs = new ArrayList<Vertex>();
    ArrayList<Edge> edges = new ArrayList<Edge>();

    public void addVertex(Vertex vertex) {
        vertexs.add(vertex);
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    /**
     * 顶点类
     */
    public class Vertex {
        String name;
        boolean visited = false;     //标记该点是否被查看-广度优先专用
        boolean visited2 = false;     //标记该点是否被查看-深度优先专用

        public Vertex(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "[" + name + "]";
        }

        public boolean equals(Vertex target) {
            return this.name.equals(target.name);
        }
    }

    /**
     * 边类，有向图.
     */
    public class Edge {
        Vertex start;
        Vertex end;
        int weight;

        public Edge(Vertex start, Vertex end) {
            this.start = start;
            this.end = end;
        }

        public Edge(Vertex start, Vertex end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "(" + start + "," + end + ")";
        }

    }
}
