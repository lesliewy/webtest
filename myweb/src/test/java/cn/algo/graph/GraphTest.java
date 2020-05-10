package cn.algo.graph;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by leslie on 2019/8/30.
 */
public class GraphTest {
    @Test
    public void testBFS() {
        Graph graph = buildGraph1();
        GraphUtils.BFS(graph);

    }

    @Test
    public void testDFS() {
        Graph graph = buildGraph1();
        GraphUtils.DFS(graph, graph.vertexs.get(0));
    }

    @Test
    public void testKruskal() {
        Graph graph = buildGraph2();
        List<Graph.Edge> result = GraphUtils.kruskal(graph);
        Assert.assertEquals(8, result.stream().mapToInt(edge -> edge.weight).sum());
    }

    private Graph buildGraph1() {
        //构造有向图
        Graph graph = new Graph();
        // Vertex Edge 是Graph 的内部类，需要如下实例化.  如果Vertex, Edge是static类型，可以直接 new Graph.Vertex();
        Graph.Vertex v0 = graph.new Vertex("v0");
        Graph.Vertex v1 = graph.new Vertex("v1");
        Graph.Vertex v2 = graph.new Vertex("v2");
        Graph.Vertex v3 = graph.new Vertex("v3");
        Graph.Vertex v4 = graph.new Vertex("v4");
        Graph.Vertex v5 = graph.new Vertex("v5");
        Graph.Vertex v6 = graph.new Vertex("v6");
        graph.addVertex(v0);
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);
        graph.addVertex(v6);
        Graph.Edge e0 = graph.new Edge(v0, v1);
        Graph.Edge e1 = graph.new Edge(v0, v2);
        Graph.Edge e2 = graph.new Edge(v0, v3);
        Graph.Edge e3 = graph.new Edge(v1, v4);
        Graph.Edge e4 = graph.new Edge(v1, v5);
        Graph.Edge e5 = graph.new Edge(v2, v4);
        Graph.Edge e6 = graph.new Edge(v3, v5);
        Graph.Edge e7 = graph.new Edge(v4, v6);
        Graph.Edge e8 = graph.new Edge(v5, v6);
        graph.addEdge(e0);
        graph.addEdge(e1);
        graph.addEdge(e2);
        graph.addEdge(e3);
        graph.addEdge(e4);
        graph.addEdge(e5);
        graph.addEdge(e6);
        graph.addEdge(e7);
        graph.addEdge(e8);
        return graph;
    }

    private Graph buildGraph2() {
        Graph graph = new Graph();
        // Vertex Edge 是Graph 的内部类，需要如下实例化.  如果Vertex, Edge是static类型，可以直接 new Graph.Vertex();
        Graph.Vertex v1 = graph.new Vertex("v1");
        Graph.Vertex v2 = graph.new Vertex("v2");
        Graph.Vertex v3 = graph.new Vertex("v3");
        Graph.Vertex v4 = graph.new Vertex("v4");
        Graph.Vertex v5 = graph.new Vertex("v5");
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);
        Graph.Edge e1 = graph.new Edge(v1, v2, 5);
        Graph.Edge e2 = graph.new Edge(v1, v4, 4);
        Graph.Edge e3 = graph.new Edge(v1, v3, 3);
        Graph.Edge e4 = graph.new Edge(v3, v4, 3);
        Graph.Edge e5 = graph.new Edge(v2, v5, 1);
        Graph.Edge e6 = graph.new Edge(v4, v5, 6);
        Graph.Edge e7 = graph.new Edge(v2, v3, 2);
        Graph.Edge e8 = graph.new Edge(v2, v4, 2);
        graph.addEdge(e1);
        graph.addEdge(e2);
        graph.addEdge(e3);
        graph.addEdge(e4);
        graph.addEdge(e5);
        graph.addEdge(e6);
        graph.addEdge(e7);
        graph.addEdge(e8);
        return graph;
    }
}
