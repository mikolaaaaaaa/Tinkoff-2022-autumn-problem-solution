import java.util.Scanner;

public class SeventhTask {

    static final int MAXN = (int) (1e5 + 50);
    static final int DICTIONARY = 26;
    static Node[] tree = new Node[4 * MAXN];
    static String s;

    static {
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new Node();
        }
    }

    static class Node {
        int[] firstPosition;
        int[] lastPosition;
        int charBitmask;

        public Node() {
            firstPosition = new int[DICTIONARY];
            lastPosition = new int[DICTIONARY];
            for (int i = 0; i < DICTIONARY; i++) {
                firstPosition[i] = Integer.MAX_VALUE;
                lastPosition[i] = Integer.MIN_VALUE;
            }
        }
    }

    static void build(int v, int tl, int tr) {
        if (tl == tr) {
            tree[v].charBitmask = (1 << (s.charAt(tl) - 'a'));
            tree[v].firstPosition[s.charAt(tl) - 'a'] = tl;
            tree[v].lastPosition[s.charAt(tl) - 'a'] = tl;
        } else {
            int tm = (tl + tr) >> 1;
            build(v << 1, tl, tm);
            build((v << 1) | 1, tm + 1, tr);
            tree[v].charBitmask = tree[v << 1].charBitmask | tree[(v << 1) | 1].charBitmask;
            for (int i = 0; i < DICTIONARY; i++) {
                tree[v].firstPosition[i] = Math.min(tree[v << 1].firstPosition[i], tree[(v << 1) | 1].firstPosition[i]);
                tree[v].lastPosition[i] = Math.max(tree[v << 1].lastPosition[i], tree[(v << 1) | 1].lastPosition[i]);
            }
        }
    }

    static Node query(int v, int tl, int tr, int l, int r) {
        if (l > r) return new Node();
        if (l == tl && r == tr) {
            return tree[v];
        }
        int tm = (tl + tr) >> 1;
        Node left = query(v << 1, tl, tm, l, Math.min(r, tm));
        Node right = query((v << 1) | 1, tm + 1, tr, Math.max(l, tm + 1), r);
        Node res = new Node();
        res.charBitmask = left.charBitmask | right.charBitmask;
        for (int i = 0; i < DICTIONARY; i++) {
            res.firstPosition[i] = Math.min(left.firstPosition[i], right.firstPosition[i]);
            res.lastPosition[i] = Math.max(left.lastPosition[i], right.lastPosition[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        s = input.nextLine();
        build(1, 0, s.length() - 1);
        int q;
        q = input.nextInt();
        int l, r;
        for (int j = 0; j < q; j++) {
            l = input.nextInt();
            r = input.nextInt();
            l--;
            r--;
            Node res = query(1, 0, s.length() - 1, l, r);
            int ans = 0;
            int currPos = l;
            for (int i = 0; i < DICTIONARY; i++) {
                if ((res.charBitmask & (1 << i)) == 0) continue;
                if (currPos <= res.firstPosition[i]) {
                    ans += res.lastPosition[i] - currPos;
                } else {
                    ans += res.lastPosition[i] - l + r - currPos + 1;
                }
                currPos = res.lastPosition[i];
            }
            System.out.println(ans);
        }
    }
}
