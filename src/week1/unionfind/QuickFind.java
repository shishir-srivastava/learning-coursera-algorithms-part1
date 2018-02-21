package week1.unionfind;

/**
 * QuickFind algorithm implementation for Dynamic Connectivity problem
 *
 * Author: Shishir Srivastava
 * Date: 20th Feb, 2018
 */

public class QuickFind {
    private int[] arr;
    private int count;

    /**
     * Initialize QuickFind class for working with `n` nodes
     * @param n Number of nodes in the problem
     */
    public QuickFind(int n) {
        count = n;
        arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
    }

    /**
     * Simple getter to facilitate testing
     * @return The internal array used to represent the connected components
     */
    public int[] getArr() {
        return arr;
    }

    /**
     * Connects 2 nodes
     * @param p
     * @param q
     */
    public void union(int p, int q) throws IndexOutOfBoundsException {
        if (!(isValidIndex(p) && isValidIndex(q))) throw new IndexOutOfBoundsException();
        if (arr[p] == arr[q]) return;
        int qId = arr[q];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == qId) arr[i] = arr[p];
        }
    }

    /**
     * Checks if 2 nodes are connected
     * @param p
     * @param q
     * @return `true` if nodes p and q are connected. `false` otherwise.
     */
    public boolean connected(int p, int q) throws IndexOutOfBoundsException {
        if (!(isValidIndex(p) && isValidIndex(q))) throw new IndexOutOfBoundsException();
        return (arr[p] == arr[q]);
    }

    private boolean isValidIndex(int a) {
        return (a >= 0 && a < count);
    }
}
