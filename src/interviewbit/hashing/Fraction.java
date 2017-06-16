/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.hashing;

import java.util.HashMap;

/**
 *
 * @author Dang
 */
public class Fraction {

    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        // "+" or "-"
        res.append(((numerator > 0) ^ (denominator > 0)) ? "-" : "");
        // must cast before running Math.abs to prevent bit overflow
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);

        // integral part
        res.append(num / den);
        num %= den;
        if (num == 0) {
            // solution contains no decimal
            return res.toString();
        }

        // fractional part
        res.append(".");
        HashMap<Long, Integer> map = new HashMap<>();
        while (num != 0) {
            map.put(num, res.length());

            num *= 10;
            res.append(num / den);
            num %= den;

            // int is never null, Integer can be
            Integer remainderIndex = map.get(num);
            if (remainderIndex != null) {
                res.insert(remainderIndex, "(");
                res.append(")");
                break;
            }
        }
        return res.toString();
    }
}
