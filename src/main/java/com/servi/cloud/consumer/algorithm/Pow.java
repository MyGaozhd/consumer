package com.servi.cloud.consumer.algorithm;

import com.servi.cloud.consumer.util.log.ServiLogger;

import java.math.BigDecimal;

/**
 * 求解x的n次方的问题
 */
public class Pow {

    public static void main(String[] args) {
        BigDecimal x = new BigDecimal(2);
        ServiLogger.log((c(x, 0)).toString());
        ServiLogger.log((c(x, 1)).toString());
        ServiLogger.log((c(x, 2)).toString());
        ServiLogger.log((c(x, 3)).toString());
        ServiLogger.log((c(x, 4)).toString());
        ServiLogger.log((c(x, 5)).toString());
        ServiLogger.log((c(x, 6)).toString());
        ServiLogger.log((c(x, 7)).toString());
        ServiLogger.log((c(x, 8)).toString());
        ServiLogger.log((c(x, 9)).toString());
        ServiLogger.log((c(x, 10)).toString());
        ServiLogger.log((c(x, 11)).toString());
    }

    private static BigDecimal c(BigDecimal x, int n) {
        if (n == 0) {
            return new BigDecimal(1);
        }
        if (n == 1) {
            return x;
        }
        BigDecimal value = null;
        BigDecimal o = new BigDecimal(1);
        if (n % 2 == 0) {
            value = c(x, n / 2);
        } else {
            value = c(x, (n - 1) / 2);
            o = x;
        }
        return o.multiply(value).multiply(value);
    }
}
