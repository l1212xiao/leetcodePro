package com.lyx.OwnLearning.interview;

import java.math.BigDecimal;

/**
 * @author lvyunxiao
 * @date 2020/7/28
 * <p>
 * 问题：已知 sqrt (2)约等于 1.414，要求不用数学库，求 sqrt (2)精确到小数点后 10 位。
 * 出题人：阿里巴巴出题专家：文景／阿里云 CDN 资深技术专家。
 * 考察点：基础算法的灵活应用能力（二分法学过数据结构的同学都知道，但不一定往这个方向考虑；如果学过数值计算的同学，应该还要能想到牛顿迭代法并解释清楚）退出条件设计
 * <p>
 * 参考答案：
 * 1. 已知 sqrt(2)约等于 1.414，那么就可以在(1.4, 1.5)区间做二分查找如：
 * a) high=>1.5
 * b) low=>1.4
 * c) mid => (high+low)/2=1.45
 * d) 1.45*1.45>2 ? high=>1.45 : low => 1.45 e) 循环到 c)
 */
public class SqrtDemo {
    public static double getSqrtOf2() {
        final double EPSINON = 0.0000000001;
        double low = 1.4D, high = 1.5D;
        double mid = (low + high) / 2;

        while (high - low > EPSINON) {
            if (mid * mid > 2) {
                high = mid;
            } else {
                low = mid;
            }
            mid = (high + low) / 2;
        }
        return mid;
    }

    public static void main(String[] args) {
        double sqrtOf2 = getSqrtOf2();

        // 保留位数
        BigDecimal bigDecimal = new BigDecimal(sqrtOf2);
        double doubleValue = bigDecimal.setScale(10, BigDecimal.ROUND_HALF_UP).doubleValue();

        System.out.println(doubleValue);
    }
}
