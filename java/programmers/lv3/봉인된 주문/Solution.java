import java.util.*;

class Solution {
    public String solution(long n, String[] bans) {
        // String answer = "";
        
        System.out.println("원래 " + n + "번째: " + toString(n));
        Arrays.sort(
            bans, 
            (a, b) -> (int)(Math.signum(toLong(a) - toLong(b)))
        );
        
        for (String ban : bans) {
            if (toLong(ban) <= n) {
                n += 1;
            } else {
                break;
            }
        }
        
        
        return toString(n);
    }
    
    private long toLong(String string) {
        long result = 0;
        
        for (int i = 0; i < string.length(); i ++) {
            result = result * 26 + string.charAt(i) - 'a' + 1;
        }
        
        return result;
    }
    
    private String toString(long number) {
        String result = "";
        while (number > 0) {
            number -= 1; // 1부터 시작하기 위해서
            long mod = number % 26;
            number = number / 26;
            result = (char)(mod + 'a') + result;
        }
        
        return result;
    }
}