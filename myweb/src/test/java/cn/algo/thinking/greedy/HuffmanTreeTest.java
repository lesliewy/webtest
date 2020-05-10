package cn.algo.thinking.greedy;

import org.junit.Test;

/**
 * Created by leslie on 2019/12/2.
 */
public class HuffmanTreeTest {
    @Test
    public void testHuffmanTree() {
        HuffmanTree huffmanTree = new HuffmanTree();
        int[] w = {5, 29, 7, 8, 14, 23, 3, 11};
        String[] elem = {"A", "B", "C", "D", "E", "F", "G", "H"};
        huffmanTree.createHuffmanTree(w, elem).printHuffmanTree();
        huffmanTree.createHuffmanCode(w, elem).printHuffmanCode();
    }
}
