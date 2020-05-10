package cn.algo.thinking.greedy;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * <pre>
 *     霍夫曼编码是一种十分有效的编码方法，广泛用于数据压缩中，其压缩率通常在 20%～90% 之间.
 *     霍夫曼编码不仅会考察文本中有多少个不同字符，还会考察每个字符出现的频率，根据频率的不同，选择不同长度的编码.
 *     根据贪心的思想，我们可以把出现频率比较多的字符，用稍微短一些的编码；出现频率比较少的字符，用稍微长一些的编码.
 *
 *     我们把每个字符看作一个节点，并且辅带着把频率放到优先级队列中。我们从队列中取出频率最小的两个节点 A、B，然后新建一个节点 C，把频率设置为两个节点的频率之和，并把这个新节点 C 作为节点 A、B 的父节点。最后再把 C 节点放入到优先级队列中。重复这个过程，直到队列中没有数据
 *
 * </pre>
 * Created by leslie on 2019/12/2.
 */

public class HuffmanTree {
    //树的所有数据
    private int[] elem;
    //树的所有权重
    private int[] weight;
    //存储霍夫曼树  这里是使用数组来存储.
    private Node[] huffmanTree;
    //HuffmanCode
    private Map<String, String> huffmanCode;


    private class Node implements Comparable<Node> {
        //当前索引
        private Integer index;
        //权值
        private Integer weight;
        //数据
        private String elem;
        //父节点
        private Integer parent;
        //左孩子
        private Integer lChildren;
        //右孩子
        private Integer rChildren;

        public Node(Integer weight, String elem, Integer parent, Integer lChildren, Integer rChildren, Integer index) {
            this.weight = weight;
            this.elem = elem;
            this.parent = parent;
            this.lChildren = lChildren;
            this.rChildren = rChildren;
            this.index = index;
        }

        @Override
        public int compareTo(Node o) {
            return weight - o.weight;
        }
    }

    HuffmanTree createHuffmanTree(int[] weight, String[] elem) {
        int size = elem.length * 2 - 1;
        huffmanTree = new Node[size];
        int p = 0;
        // 优先级队列用于构建哈夫曼树.
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(elem.length);
        while (p < elem.length) {
            Node node = new Node(weight[p], elem[p], null, null, null, p);
            huffmanTree[p] = node;
            priorityQueue.add(node);
            p++;
        }

        while (p < huffmanTree.length) {
            Node minNode = priorityQueue.poll();
            Node secondMinNode = priorityQueue.poll();
            Node node = new Node(minNode.weight + secondMinNode.weight, null, null,
                    Math.min(secondMinNode.index, minNode.index), Math.max(secondMinNode.index, minNode.index), p);
            priorityQueue.add(node);
            huffmanTree[p] = node;
            minNode.parent = p;
            secondMinNode.parent = p;
            p++;
        }


        return this;
    }

    void printHuffmanTree() {
        if (huffmanTree == null) {
            throw new UnsupportedOperationException("霍夫曼树没有初始化");
        }
        System.out.println("weight\t" + "parent\t" + "lChildren\t" + "rChildren");
        for (int i = 0; i < huffmanTree.length; i++) {
            System.out.println(huffmanTree[i].weight + "\t" + huffmanTree[i].parent + "\t" + huffmanTree[i].lChildren + "\t" + huffmanTree[i].rChildren);
        }
    }

    HuffmanTree createHuffmanCode(int[] weight, String[] elem) {
        createHuffmanTree(weight, elem);
        huffmanCode = new HashMap<>(elem.length);
        createHuffmanCode(huffmanTree.length - 1, "");
        return this;
    }

    /**
     * 构造霍夫曼编码
     * 从上往下递归遍历
     *
     * @param current 当前节点索引
     */
    private void createHuffmanCode(Integer current, String code) {
        Node currentNode = huffmanTree[current];
        if (currentNode.rChildren == null && currentNode.lChildren == null) {
            huffmanCode.put(currentNode.elem, code);
        } else {
            createHuffmanCode(currentNode.lChildren, code + "0");
            createHuffmanCode(currentNode.rChildren, code + "1");
        }
    }

    void printHuffmanCode() {
        if (huffmanCode == null) {
            throw new UnsupportedOperationException("霍夫曼树没有初始化");
        }
        for (String key : huffmanCode.keySet()) {
            System.out.println("key : " + key + ",value:" + huffmanCode.get(key));
        }
    }
}
