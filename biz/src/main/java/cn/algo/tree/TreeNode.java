package cn.algo.tree;

/**
 * Created by leslie on 2019/12/2.
 */
public class TreeNode<T> {
    public T data;
    //左孩子
    public TreeNode leftNode;
    //右孩子
    public TreeNode rightNode;

    public TreeNode(T val) {
        this.data = val;
    }

    public T getData() {
        return data;
    }

}
