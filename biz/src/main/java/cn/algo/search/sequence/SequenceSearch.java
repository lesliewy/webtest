package cn.algo.search.sequence;

/**
 * Created by leslie on 2019/8/28.
 */
public class SequenceSearch {

    public static int search(int[] a, int key) {
        if (a == null) {
            return -1;
        }
        for (int i = 0, length = a.length; i < length; i++) {
            if (a[i] == key) {
                return i;
            }
        }
        return -1;
    }
}
