import java.util.Iterator;

/**
 * Created by mowerlin on 24/01/2017.
 */
public class DataStructure {

    class Node<T>{
        T data;
        Node(T data){
            this.data = data;
        }
    }

    class Shipping<T> implements Iterable<T>{

        Node<T> data;

        Shipping(){

        }

        @Override
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                @Override
                public boolean hasNext() {
                    return false;
                }

                @Override
                public T next() {
                    return null;
                }
            };
        }
    }

    public int binarySearch(int[] A, int key) {
        if (A.length == 0) {
            return -1;
        }
        int start = 0;
        int end = A.length - 1;
        int mid;
        while (true) {
            if (start > end) {
                return -1;
            }
            mid = start + (end - start) / 2;
            if (key == A[mid]) {
                return mid;
            } else if (key < A[mid]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
    }
}
