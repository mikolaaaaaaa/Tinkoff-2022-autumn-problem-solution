import java.util.*;

public class SixthTask {

    static int result;

    public static void firstDfs(Map<Integer, List<Integer>> graph,
                                Map<Integer, Integer> pathLength,
                                Map<Integer, Integer> hasCycle,
                                int num, int length) {
        if (hasCycle.containsKey(num)) {
            length += hasCycle.get(num);
        }
        result = Math.max(result, length);
        if (!graph.containsKey(num)) {
            return;
        }
        if (pathLength.containsKey(num)) {
            pathLength.put(num, Math.max(pathLength.get(num), length));
        } else {
            pathLength.put(num, length);
        }
        result = Math.max(result, pathLength.get(num));
        List<Integer> nodes = graph.get(num);
        for (int i : nodes) {
            if (i == num) {
                continue;
            }
            firstDfs(graph, pathLength, hasCycle, i, length + 1);
        }
    }

    public static void main(String[] args) {

        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> pathLength = new HashMap<>();
        Map<Integer, Integer> hasCycle = new HashMap<>();
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        int from;
        int to;
        for (int i = 0; i < n; i++) {
            from = input.nextInt();
            to = input.nextInt();
            if (from == to) {
                int count = 0;
                if (hasCycle.containsKey(from)) {
                    count = hasCycle.get(from);
                }
                hasCycle.put(from, count + 1);
            }
            if (graph.containsKey(from)) {
                graph.get(from).add(to);
            } else {
                graph.put(from, new LinkedList<>());
                graph.get(from).add(to);
            }


        }

        for (Map.Entry<Integer, List<Integer>> i : graph.entrySet()) {
            if (!pathLength.containsKey(i.getKey())) {
                firstDfs(graph, pathLength, hasCycle, i.getKey(), 0);
            }
        }

        System.out.println(result);

    }
}
