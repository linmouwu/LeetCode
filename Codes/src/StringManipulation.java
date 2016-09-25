/**
 * Created by mowerlin on 25/09/2016.
 */
public class StringManipulation {
    public String reverseString(String s) {
        // Use StringBuilder to improve performance.
        StringBuilder sb = new StringBuilder();
        for(int i = s.length()-1;i>=0;i--){
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}
