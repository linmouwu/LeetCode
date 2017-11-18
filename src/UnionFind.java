/**
 * @author Mouwu Lin
 * @AndrewID mouwul
 */
public class UnionFind {

    private int[] roots;

    private int[] size;

    private int count;

    UnionFind(int N) {

        this.roots = new int[N];
        this.size = new int[N];
        this.count = N;

        for (int i = 0; i < roots.length; i++) {
            this.roots[i] = i;
            this.size[i] = 1;
        }

    }

    public void union(int p, int q) {

        int pRoot = findRoot(p);
        int qRoot = findRoot(q);

        if (pRoot == qRoot) {
            return;
        }

        if (size[p] > size[q]) {
            roots[q] = p;
            size[p] += size[q];
        } else {
            roots[p] = q;
            size[q] += size[p];
        }

        count--;

    }

    public int findRoot(int p) {

        while(roots[p] != p){
            roots[p] = roots[roots[p]];
            p = roots[p];
        }
        return p;

    }

    public boolean isConnected(int p, int q) {
        return findRoot(p) == findRoot(q);
    }

    public int count() {
        return count;
    }

}
