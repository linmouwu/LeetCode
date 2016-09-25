import java.util.*;
import java.lang.*;

/**
 * Created by mowerlin on 25/09/2016.
 */
public class BFSDFS {

    // User a helper to pass by the list and the level as arguments.
    public int depthSum(List<NestedInteger> nestedList) {
        return helper(nestedList, 1);
    }

    private int helper(List<NestedInteger> nestedList, int level) {
        int size = nestedList.size();
        // If the list is empty, return 0;
        if (size <= 0)
            return 0;
        int result = 0;
        // If the current element is a integer, get the results.
        // Otherwise recursively call the helper.
        for(NestedInteger i: nestedList){
            if(i.isInteger()){
                result += i.getInteger() * level;
            }else{
                result += helper(i.getList(), level+1) * level;
            }
        }
        return result;
    }

}
