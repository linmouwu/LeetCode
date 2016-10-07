import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * Created by mowerlin on 25/09/2016.
 */
public class StringManipulation {

    // Convert decimal to hexadecimal
    public String toHex(int num) {
        if (num == 0)
            return "0";
        // Convert the negative num into positive first.
        boolean neg = false;
        int remained = 0;
        if (num < 0) {
            neg = true;
            // By adding 1. Two Components.
            num = Math.abs(num + 1);
        }
        StringBuilder sb = new StringBuilder();
        // The remain is the number, the dividing result is the next number to compute.
        while (num > 0) {
            remained = num % 16;
            sb.append(convert(remained));
            num /= 16;
        }
        // If neg, convert by the difference between the two characters.
        if (neg) {
            int max = sb.length();
            StringBuilder nsb = new StringBuilder();
            for (int i = 0; i < 8; i++) {
                if (i >= max) {
                    nsb.append("f");
                } else {
                    switch (sb.charAt(i)) {
                        case 'a':
                            nsb.append("5");
                            break;
                        case 'b':
                            nsb.append("4");
                            break;
                        case 'c':
                            nsb.append("3");
                            break;
                        case 'd':
                            nsb.append("2");
                            break;
                        case 'e':
                            nsb.append("1");
                            break;
                        case 'f':
                            nsb.append("0");
                            break;
                        default:
                            // Here should be 15 as 0+15 = 15;
                            nsb.append(convert(15 - (sb.charAt(i) - '0')));
                            break;
                    }
                }
            }
            return nsb.reverse().toString();
        } else {
            return sb.reverse().toString();
        }
    }

    public boolean validWordAbbreviation(String word, String abbr) {
        int sa = abbr.length();
        int sw = word.length();
        int i = 0;
        int num = 0;
        StringBuilder sb = new StringBuilder();
        int total = 0;
        while (i < sa) {
            if ('0' < abbr.charAt(i) && abbr.charAt(i) <= '9' || abbr.charAt(i) == '0' && num > 0) {
                num = num * 10 + (abbr.charAt(i) - '0');
                i++;
            } else {
                total += (num);
                if (total >= sw)
                    return false;
                if (word.charAt(total) == abbr.charAt(i)) {
                    num = 0;
                    total += 1;
                    i++;
                } else {
                    return false;
                }
            }
        }
        if (total + num == sw) {
            return true;
        } else {
            return false;
        }
    }

    private char convert(int num) {
        switch (num) {
            case 10:
                return 'a';
            case 11:
                return 'b';
            case 12:
                return 'c';
            case 13:
                return 'd';
            case 14:
                return 'e';
            case 15:
                return 'f';
            default:
                return (char) ('0' + num);
        }
    }

    public int longestPalindrome(String s) {
        int[] lower = new int[26];
        int[] upper = new int[26];
        int le = s.length();
        if (le <= 0)
            return 0;
        for (int i = 0; i < le; i++) {
            // Like all other palindrome, use a fixed length array to mark the appearance of the characters.
            if (Character.isLowerCase(s.charAt(i))) {
                lower[s.charAt(i) - 'a']++;
            } else {
                upper[s.charAt(i) - 'A']++;
            }
        }
        int result = 0;
        int odd = 0;
        for (int i = 0; i < 26; i++) {
            // If some characters have odd appearance, we can choose one to add into the middle.
            result += ((lower[i] / 2) * 2);
            result += ((upper[i] / 2) * 2);
            if (lower[i] % 2 == 1 || upper[i] % 2 == 1)
                odd = 1;
        }
        return result + odd;
    }


    public String reverseString(String s) {
        // Use StringBuilder to improve performance.
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    public int lengthLongestPath(String input) {
        // Separate the file path.
        String[] parts = input.split("\n");
        // Storing the current length of the file path.
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        for (String part : parts) {
            // '\t' is used to defined the level of the file path.
            int level = part.lastIndexOf("\t") + 1;
            int currentLen = part.substring(level).length();
            while (stack.size() != level) {
                stack.pop();
            }
            if(stack.isEmpty()){
                stack.push(currentLen);
            }else{
                currentLen += stack.peek() + 1;
                stack.push(currentLen);
            }
            if(part.contains(".")){
                max = Math.max(max, stack.peek());
            }
        }
        return max;
    }

    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        // The last word should always be in the list.
        wordList.add(endWord);
        // BFS to solve the problem.
        Queue<String> queue = new LinkedList<String>();
        queue.add(beginWord);
        // Level to mark down the current distance to the end word.
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // For all strings in the queue, get it and compare it to the end.
            for (int i = 0; i < size; i++) {
                String currentString = queue.poll();
                // If it's the end, return the level+1.
                if (currentString.equals(endWord)) {
                    return level + 1;
                }
                // Otherwise, change the chars in the string to get closer to the end.
                for (int j = 0; j < currentString.length(); j++) {
                    char[] characters = currentString.toCharArray();
                    // If the new string is not in the list, do not added it into the string.
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        characters[j] = ch;
                        String newString = new String(characters);
                        if (!characters.equals(currentString) && wordList.contains(newString)) {
                            // If the new string is in the list, update the list by removing the string, and put it into the queue.
                            queue.add(newString);
                            wordList.remove(newString);
                        }
                    }
                }
            }
            level++;
        }
        return 0;
    }
}
