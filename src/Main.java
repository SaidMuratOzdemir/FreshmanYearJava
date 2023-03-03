public class Main {

    /*
    For a string 5 and an integer k, a selection of substrings is valid if the following conditions are met:
0 The length of every substring is greater than or equal to k
0 Each substring is a palindrome.
0 No two substrings overlap.
Determine the maximum number of valid substrings that can be formed from 5.
Notes:
0 A substring is a group ofadjacent characters in a string.
0 A palindrome is a string that reads the same backward as forward.
Example
5: "aababaabce"
k = 3
a ababa abce
a aba baab ce
Any valid substring must be kor more characters long. Either i or 2 non-overlapping palindromes can be formed. Return 2, the
maximum number that can be formed.
Function Description
Complete the function getMaXSL/bstrings in the editor below.
getMaxSubstrings has the following parameters:
strings: the given string
int k: the minimum length of a valid substring
Returns
int: the maximum number of valid substrings that can be formed
     */
    public static int getMaxSubstrings(String s, int k) {
        int n = s.length();
        int maxCount = 0;
        boolean[][] dp = new boolean[n][n];

        // Initialize the dp matrix with base cases
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;  // A single character is always a palindrome
            if (i < n - 1 && s.charAt(i) == s.charAt(i+1)) {
                dp[i][i+1] = true;  // Two consecutive equal characters form a palindrome
                maxCount = 1;  // Update the maxCount
            }
        }

        // Compute the rest of the dp matrix using dynamic programming
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i <= n-len; i++) {
                int j = i+len-1;
                if (s.charAt(i) == s.charAt(j) && dp[i+1][j-1]) {
                    dp[i][j] = true;  // The substring s[i..j] is a palindrome
                    if (j-i+1 >= k) {
                        maxCount = Math.max(maxCount, 1 + getMaxSubstrings(s.substring(i+1, j), k));
                    }
                }
            }
        }

        return maxCount;
    }

    public static void main(String[] args) {
        System.out.println(getMaxSubstrings("aaaaabb", 2));
    }


}
