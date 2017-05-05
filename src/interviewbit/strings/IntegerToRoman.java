/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.strings;

/**
 *
 * @author Dang
 */
public class IntegerToRoman {

    public String intToRoman(int a) {
        StringBuilder sb = new StringBuilder();

        int m = a / 1000;
        sb.append(getThousands(m));
        a -= m * 1000;

        int h = a / 100;
        sb.append(getHundreds(h));
        a -= h * 100;

        int t = a / 10;
        sb.append(getTens(t));
        a -= t * 10;
        
        sb.append(getSingle(a));
        
        return sb.toString();
    }

    public String getThousands(int m) {
        switch (m) {
            case (1):
                return "M";
            case (2):
                return "MM";
            case (3):
                return "MMM";
        }
        return "";
    }

    public String getHundreds(int h) {
        switch (h) {
            case (1):
                return "C";
            case (2):
                return "CC";
            case (3):
                return "CCC";
            case (4):
                return "CD";
            case (5):
                return "D";
            case (6):
                return "DC";
            case (7):
                return "DCC";
            case (8):
                return "DCCC";
            case (9):
                return "CM";
        }
        return "";
    }

    public String getTens(int t) {
        switch (t) {
            case (1):
                return "X";
            case (2):
                return "XX";
            case (3):
                return "XXX";
            case (4):
                return "XL";
            case (5):
                return "L";
            case (6):
                return "LX";
            case (7):
                return "LXX";
            case (8):
                return "LXXX";
            case (9):
                return "XC";
        }
        return "";
    }
    
    public String getSingle(int s) {
            switch (s) {
            case (1):
                return "I";
            case (2):
                return "II";
            case (3):
                return "III";
            case (4):
                return "IV";
            case (5):
                return "V";
            case (6):
                return "VI";
            case (7):
                return "VII";
            case (8):
                return "VIII";
            case (9):
                return "IX";
        }
        return "";    
    }
}
