/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

/**
 * Given expression with operands and operators (OR , AND , XOR), 
 * in how many ways can you evaluate the expression to true, 
 * by grouping in different ways? Operands are only true and false.
 * @author Duy Dang
 */
public class EvaluateExpressionToTrue {

    public int cnttrue(String a) {
        int mod = 1003;
        int n = (a.length() + 1) / 2;
        int[][] t = new int[n][n]; // ways to make true (memoziation)
        int[][] f = new int[n][n]; // ways to make false (memoziation)
        for (int i = 0; i < n; i++) { // initialize single cell before operands
            if (a.charAt(2 * i) == 'T') {
                t[i][i] = 1;
            } else {
                f[i][i] = 1;
            }
        }

        for (int length = 2; length <= a.length(); length = length + 2) { // solving sublengths
            for (int ia = 0; ia < a.length() - length; ia = ia + 2) { // i pointer for 'a'
                int ja = ia + length; // j pointer for 'a'
                int i = ia / 2; // i pointer for memoziation
                int j = ja / 2; // j pointer for memoziation
                for (int ka = ia + 1; ka < ja; ka = ka + 2) { // k pointer for 'a'
                    int k = ka / 2; // k pointer for memoization parsing length
                    char op = a.charAt(ka); // operands
                    switch (op) {
                        case '&':
                            t[i][j] += t[i][k] * t[k+1][j]; // T&T = T
                            f[i][j] += t[i][k] * f[k+1][j]; // T&F = F
                            f[i][j] += f[i][k] * t[k+1][j]; // F&T = F
                            f[i][j] += f[i][k] * f[k+1][j]; // F&F = F
                            break;
                        case '|':
                            t[i][j] += t[i][k] * t[k+1][j]; // T|T = T
                            t[i][j] += t[i][k] * f[k+1][j]; // T|F = T
                            t[i][j] += f[i][k] * t[k+1][j]; // F|T = T
                            f[i][j] += f[i][k] * f[k+1][j]; // F|F = F
                            break;
                        case '^':
                            f[i][j] += t[i][k] * t[k+1][j]; // T^T = F
                            t[i][j] += t[i][k] * f[k+1][j]; // T^F = T
                            t[i][j] += f[i][k] * t[k+1][j]; // F^T = T
                            f[i][j] += f[i][k] * f[k+1][j]; // F^F = F
                            break;
                    }
                    t[i][j] %= mod;
                    f[i][j] %= mod;
                }
            }
        }
        
        return t[0][n - 1];
    }
}
