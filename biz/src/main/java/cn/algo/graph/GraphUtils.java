package cn.algo.graph;

import java.util.*;

/**
 * Created by leslie on 2019/8/30.
 */
public class GraphUtils {
    // BFS 广度优先 非递归
    public static void BFS(Graph graph) {
        ArrayList<Graph.Vertex> vertexs = graph.vertexs;
        ArrayList<Graph.Edge> edges = graph.edges;
        Queue<Graph.Vertex> queue = new LinkedList<Graph.Vertex>();   //创建队列

        queue.add(vertexs.get(0));    //顶节点放入队列
        vertexs.get(0).visited = true;      //顶节点设为已阅
        System.out.print(vertexs.get(0));

        while (!queue.isEmpty()) {
            Graph.Vertex vertex = queue.remove();
            for (Graph.Edge edge : edges) {
                if (edge.start.equals(vertex) && edge.end.visited == false) {
                    queue.add(edge.end);
                    edge.end.visited = true;
                    System.out.print(edge.end);
                }
            }
        }
    }

    //DFS 深度优先 递归
    public static void DFS(Graph graph, Graph.Vertex vertex) {  //参数：图、点信息
        System.out.print(vertex);
        vertex.visited2 = true;

        for (Graph.Edge edge : graph.edges) {
            if (edge.start.equals(vertex) && edge.end.visited2 == false) {
                DFS(graph, edge.end);
            }
        }
    }

    /**
     * TODO: prim 最小生成树算法.
     *
     */

    /**
     * 最小生成树其实是最小权重生成树的简称, 无向图
     * kruskal(克鲁斯卡尔)算法创建最小生成树
     */
    public static List<Graph.Edge> kruskal(Graph graph) {
        // 已生成的MST，起点-终点信息
        Map<Graph.Vertex, Graph.Vertex> vertexEndInfoMST = new HashMap<>();

        List<Graph.Edge> result = new ArrayList<>();
        int sum = 0;
        Collections.sort(graph.edges, (e1, e2) -> (e1.weight - e2.weight));
        for (Graph.Edge edge : graph.edges) {
            // 找到起点和终点在临时连线数组中的最后连接点
            Graph.Vertex start = findEndInMST(vertexEndInfoMST, edge.start);
            Graph.Vertex end = findEndInMST(vertexEndInfoMST, edge.end);

            // 通过起点和终点找到的最后连接点是否为同一个点，是则产生回环
            if (!start.equals(end)) {
                // 没有产生回环则将临时数组中，起点为下标，终点为值
                if (start.name.compareTo(end.name) < 0) {
                    vertexEndInfoMST.put(start, end);
                } else {
                    vertexEndInfoMST.put(end, start);
                }
                System.out.println("访问到了节点：{" + edge.start + "," + edge.end + "}，权值：" + edge.weight);
                result.add(edge);
                sum += edge.weight;
            }
        }
        System.out.println("最小生成树的权值总和：" + sum);
        return result;
    }


    /**
     * 获取某个顶点在已生成的MST中的终点,  规则时顶点值较大的是终点.
     */
    private static Graph.Vertex findEndInMST(Map<Graph.Vertex, Graph.Vertex> parent, Graph.Vertex start) {
        while (parent.containsKey(start)) {
            start = parent.get(start);
        }
        return start;
    }


    /**
     * TODO:Dijkstra 算法:  两点之间最短路径
     *      单源最短路径
     */

}
