package cn.algo.skiplist.p1;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Created by leslie on 2020/6/28.
 */
public class SkipList<K extends Comparable<K>, V> implements Iterable<K> {

    // 一个随机数生成器
    protected static final Random randomGenerator     = new Random();

    // 默认的概率
    protected static final double DEFAULT_PROBABILITY = 0.5;

    // 头节点
    private Node<K, V>            head;
    private double                probability;

    // SkipList中的元素数量（不计算多个层级中的冗余元素）
    private int                   size;

    public SkipList(){
        this(DEFAULT_PROBABILITY);
    }

    public SkipList(double probability){
        this.head = new Node<K, V>(null, null, 0);
        this.probability = probability;
        this.size = 0;
    }

    // 对key进行检查
    // 因为每条链表的头节点就是一个key为null的节点，所以不允许其他节点的key也为null
    protected void checkKeyValidity(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key must be not null!");
        }
    }

    // a是否小于等于b
    protected boolean lessThanOrEqual(K a, K b) {
        return a.compareTo(b) <= 0;
    }

    // 概率函数
    protected boolean isBuildLevel() {
        return randomGenerator.nextDouble() < probability;
    }

    // 将y水平插入到x的后面
    protected void horizontalInsert(Node<K, V> x, Node<K, V> y) {
        y.setPrevious(x);
        y.setNext(x.getNext());
        if (x.getNext() != null) {
            x.getNext().setPrevious(y);
        }
        x.setNext(y);
    }

    // x与y进行垂直连接
    protected void verticalLink(Node<K, V> x, Node<K, V> y) {
        x.setDown(y);
        y.setUp(x);
    }

    /**
     * 查找.
     * 
     * @param key
     * @return
     */
    protected Node<K, V> findNode(K key) {
        Node<K, V> node = head;
        Node<K, V> next = null;
        Node<K, V> down = null;
        K nodeKey = null;
        while (true) {
            // 不断遍历直到遇见大于目标元素的节点
            next = node.getNext();
            while (next != null && lessThanOrEqual(next.getKey(), key)) {
                node = next;
                next = node.getNext();
            }
            // 当前元素等于目标元素，中断循环
            nodeKey = node.getKey();
            if (nodeKey != null && nodeKey.compareTo(key) == 0) {
                break;
            }
            // 否则，跳跃到下一层级
            down = node.getDown();
            if (down != null) {
                node = down;
            } else {
                break;
            }
        }
        return node;
    }

    public V get(K key) {
        checkKeyValidity(key);
        Node<K, V> node = findNode(key);
        // 如果找到的节点并不等于目标元素，则目标元素不存在于SkipList中
        if (node.getKey().compareTo(key) == 0) {
            return node.getValue();
        } else {
            return null;
        }
    }

    /**
     * 插入
     * 
     * @param key
     * @param value
     */
    public void add(K key, V value) {
        checkKeyValidity(key);
        // 直接找到key，然后修改对应的value即可
        Node<K, V> node = findNode(key);
        if (node.getKey() != null && node.getKey().compareTo(key) == 0) {
            node.setValue(value);
            return;
        }

        // 将newNode水平插入到node之后
        Node<K, V> newNode = new Node<K, V>(key, value, node.getLevel());
        horizontalInsert(node, newNode);

        int currentLevel = node.getLevel();
        int headLevel = head.getLevel();
        while (isBuildLevel()) {
            // 如果当前层级已经到达或超越顶层
            // 那么需要构建一个新的顶层
            if (currentLevel >= headLevel) {
                Node<K, V> newHead = new Node<K, V>(null, null, headLevel + 1);
                verticalLink(newHead, head);
                head = newHead;
                headLevel = head.getLevel();
            }
            // 找到node对应的上一层节点
            while (node.getUp() == null) {
                node = node.getPrevious();
            }
            node = node.getUp();
            // 将newNode复制到上一层
            Node<K, V> tmp = new Node<K, V>(key, value, node.getLevel());
            horizontalInsert(node, tmp);
            verticalLink(tmp, newNode);
            newNode = tmp;
            currentLevel++;
        }
        size++;
    }

    /**
     * 删除.
     * 
     * @param key
     */
    public void remove(K key) {
        checkKeyValidity(key);
        Node<K, V> node = findNode(key);
        if (node == null || node.getKey().compareTo(key) != 0) {
            throw new NoSuchElementException("The key is not exist!");
        }
        // 移动到最底层
        while (node.getDown() != null) {
            node = node.getDown();
        }
        // 自底向上地进行删除
        Node<K, V> prev = null;
        Node<K, V> next = null;
        for (; node != null; node = node.getUp()) {
            prev = node.getPrevious();
            next = node.getNext();
            if (prev != null) {
                prev.setNext(next);
            }
            if (next != null) {
                next.setPrevious(prev);
            }
        }
        // 对顶层链表进行调整，去除无效的顶层链表
        while (head.getNext() == null && head.getDown() != null) {
            head = head.getDown();
            head.setUp(null);
        }
        size--;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<K, V> node = head;
        // 移动到最底层
        while (node.getDown() != null) {
            node = node.getDown();
        }
        while (node.getPrevious() != null) {
            node = node.getPrevious();
        }
        // 第一个节点是头部节点，没有任何意义，所以需要移动到后一个节点
        if (node.getNext() != null) {
            node = node.getNext();
        }
        // 遍历
        while (node != null) {
            sb.append(node.toString()).append("\n");
            node = node.getNext();
        }
        return sb.toString();
    }

    @Override
    public Iterator<K> iterator() {
        return new SkipListIterator<K, V>(head);
    }

    protected static class SkipListIterator<K extends Comparable<K>, V> implements Iterator<K> {

        private Node<K, V> node;

        public SkipListIterator(Node<K, V> node){
            while (node.getDown() != null) {
                node = node.getDown();
            }
            while (node.getPrevious() != null) {
                node = node.getPrevious();
            }
            if (node.getNext() != null) {
                node = node.getNext();
            }
            this.node = node;
        }

        @Override
        public boolean hasNext() {
            return this.node != null;
        }

        @Override
        public K next() {
            K result = node.getKey();
            node = node.getNext();
            return result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
