package cn.algo.thinking.backtrack;

/**
 * <pre>
 *     正则表达式
 *     假设正则表达式中只包含“*”和“?”这两种通配符，并且对这两个通配符的语义稍微做些改变，
 *     其中，“*”匹配任意多个（大于等于 0 个）任意字符，“?”匹配零个或者一个任意字符
 *     判断正则表达式是否匹配文本
 * </pre>
 * Created by leslie on 2019/12/14.
 */
public class Pattern {
    private boolean matched = false;
    private char[] pattern; // 正则表达式
    private int plen; // 正则表达式长度

    public Pattern(char[] pattern, int plen) {
        this.pattern = pattern;
        this.plen = plen;
    }

    public boolean match(char[] text, int tlen) { // 文本串及长度
        matched = false;
        rmatch(0, 0, text, tlen);
        return matched;
    }

    /**
     * <pre>
     * 依次考察正则表达式中的每个字符，当是非通配符时，我们就直接跟文本的字符进行匹配，
     * 如果相同，则继续往下处理；
     * 如果不同，则回溯。
     * 如果遇到特殊字符的时候，我们就有多种处理方式了，也就是所谓的岔路口，比如“*”有多种匹配方案，可以匹配任意个文本串中的字符，
     * 我们就先随意的选择一种匹配方案，然后继续考察剩下的字符。如果中途发现无法继续匹配下去了，我们就回到这个岔路口，重新选择一种匹配方案，
     * 然后再继续匹配剩下的字符。
     * </pre>
     *
     * @param ti
     * @param pj
     * @param text
     * @param tlen
     */
    private void rmatch(int ti, int pj, char[] text, int tlen) {
        if (matched) {
            return; // 如果已经匹配了，就不要继续递归了
        }
        if (pj == plen) { // 正则表达式到结尾了
            if (ti == tlen) {
                matched = true; // 文本串也到结尾了
            }
            return;
        }
        if (pattern[pj] == '*') { // *匹配任意个字符
            for (int k = 0; k <= tlen - ti; ++k) {
                // 这里递归调用，如果失败了最终会回到这个岔路口.
                rmatch(ti + k, pj + 1, text, tlen);
            }
        } else if (pattern[pj] == '?') { // ?匹配0个或者1个字符
            // ? 的两种情况，最终失败后会回到此处.
            rmatch(ti, pj + 1, text, tlen);
            rmatch(ti + 1, pj + 1, text, tlen);
        } else if (ti < tlen && pattern[pj] == text[ti]) { // 纯字符匹配才行
            // 纯字符匹配就一种情况.
            rmatch(ti + 1, pj + 1, text, tlen);
        }
    }
}
