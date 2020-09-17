package com.lyx.everyDayTraining.redAndYellow;

/*
https://leetcode-cn.com/problems/UlBDOe/
LCP 19. 秋叶收藏集
小扣出去秋游，途中收集了一些红叶和黄叶，他利用这些叶子初步整理了一份秋叶收藏集 leaves，
字符串 leaves 仅包含小写字符 r 和 y， 其中字符 r 表示一片红叶，字符 y 表示一片黄叶。
出于美观整齐的考虑，小扣想要将收藏集中树叶的排列调整成「红、黄、红」三部分。
每部分树叶数量可以不相等，但均需大于等于 1。每次调整操作，小扣可以将一片红叶替换成黄叶或者将一片黄叶替换成红叶。请问小扣最少需要多少次调整操作才能将秋叶收藏集调整完毕。

示例 1：

输入：leaves = "rrryyyrryyyrr"

输出：2

解释：调整两次，将中间的两片红叶替换成黄叶，得到 "rrryyyyyyyyrr"

示例 2：

输入：leaves = "ryr"

输出：0

解释：已符合要求，不需要额外操作

提示：

3 <= leaves.length <= 10^5
leaves 中只包含字符 'r' 和字符 'y'
 */

/**
 * @author lvyunxiao
 * @date 2020/9/14
 * 用 sum[x] 表示 [0, x) 区间内红叶数量. 假设整理后红叶的区间为 [0, i) 和 [j, n), 那么黄叶区间为 [i, j).
 * 对于左右两个区间, 操作次数为区间长度减去红叶的数量, 对于中间的区间, 操作次数就是红叶的数量.
 * 需要操作的总次数为 (i - sum[i]) + (n - j - sum[n] + sum[j]) + (sum[j] - sum[i]),
 * 整理后得到 n - sum[n] + (i - 2 * sum[i]) - (j - 2 * sum[j]),
 * 约束条件为 0 < i < j < n. 为了让操作数最少, 我们希望 j 确定时 i - 2 * sum[i] 最小.
 * 用 min[x] 记录 [1, x] 区间内的 i - 2 * sum[i] 的最小值, 将 j 从 n - 1 遍历到 2, min[j - 1] 即为当前最小的 i - 2 * sum[i].
 * <p>
 * 作者：aerysn
 * 链接：https://leetcode-cn.com/problems/UlBDOe/solution/simple-java-by-aerysn/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class Solution {
    public int minimumOperations(String leaves) {
        int n = leaves.length();
        char[] array = leaves.toCharArray();
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + (array[i] == 'r' ? 1 : 0);
        }
        int[] min = new int[n + 1];
        int currentMin = Integer.MAX_VALUE;
        for (int i = 1; i < n - 1; i++) {
            currentMin = Math.min(currentMin, i - 2 * sum[i]);
            min[i] = currentMin;
        }
        int result = Integer.MAX_VALUE;
        for (int j = n - 1; j > 1; j--) {
            result = Math.min(result, n - sum[n] + min[j - 1] - j + 2 * sum[j]);
        }
        return result;
    }

    public static void main(String[] args) {

        String leaves = "rrryyyrryyyrr";
        Solution s = new Solution();
        int minimumOperations = s.minimumOperations(leaves);
        System.out.println(minimumOperations);

    }

}
