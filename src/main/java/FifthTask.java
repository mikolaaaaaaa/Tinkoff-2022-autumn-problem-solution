import java.util.*;

public class FifthTask {

    static class Trie {

        boolean isLeaf;
        List<Trie> children;
        int words_count;
        int next_words;
        int position;

        public Trie() {
            isLeaf = false;
            children = new ArrayList<>(Collections.nCopies(26, null));
        }

        public void addWord(String word, int position) {
            Trie current = this;
            char[] chars = word.toCharArray();
            for (char i : chars) {
                if (current.children.get(i - 'a') == null) {
                    current.children.set(i - 'a', new Trie());
                }
                current.next_words++;
                current = current.children.get(i - 'a');
            }
            current.position = position;
            current.isLeaf = true;
            current.words_count++;
        }

        public int search(int k, String pref) {
            Trie current = this;
            char[] chars = pref.toCharArray();
            for (char i : chars) {
                current = current.children.get(i - 'a');
                if (current == null) {
                    return -1;
                }
            }
            if (k > current.next_words + current.words_count) {
                return -1;
            }
            if (k <= current.words_count) {
                return current.position;
            }
            k -= current.words_count;
            Deque<Trie> bdfs = new LinkedList<>();
            for(int i = 0; i < 26; i++) {
                if (current.children.get(i) != null) {
                    bdfs.add(current.children.get(i));
                }
            }
            Trie temp;
            while(!bdfs.isEmpty()) {
                temp = bdfs.poll();
                if (temp.isLeaf) {
                    k -= temp.words_count;
                    if (k <= 0) {
                        return temp.position;
                    }
                }
                if (temp.next_words + temp.words_count < k) {
                    k -= temp.next_words;
                } else {
                    for (int i = 25; i >= 0; i--) {
                        if (temp.children.get(i) != null) {
                            bdfs.addFirst(temp.children.get(i));
                        }
                    }
                }
            }

            return -1;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n,q;
        n = input.nextInt();
        q = input.nextInt();
        String s;
        Trie trie = new Trie();
        input.nextLine();
        for(int i = 0; i < n; i++) {
            s = input.nextLine();
            trie.addWord(s,i + 1);
        }
        int k;
        for(int i = 0; i < q; i++) {
            k = input.nextInt();
            s = input.nextLine().strip();
            System.out.println(trie.search(k,s));
        }
    }
}
