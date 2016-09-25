/**
 * Created by mowerlin on 21/09/2016.
 */
public class Math {
    static void arrangeCoins(long[] coins) {

        for (int i = 0; i < coins.length; i++) {

            // For level n, it has coins between (n*(n-1)/2, n*(n+1)/2).
            long root = (long) java.lang.Math.sqrt(2 * coins[i]);
            // Thus we can easily find out the number of the coins in that level from the root.
            if(root*(root+1) == coins[i]*2){
                System.out.println(root);
            }else{
                System.out.println(root-1);
            }
        }
    }

}
