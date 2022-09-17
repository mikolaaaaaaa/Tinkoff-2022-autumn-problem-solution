import java.util.*;

public class FourthTask {

    public static void updateCurrentStackOfVariables(List<String> variables, Map<String, List<Integer>> currentStack) {
        for (String variable : variables) {
            List<Integer> stack = currentStack.get(variable);
            if (!stack.isEmpty()) {
                stack.remove(stack.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        List<Map<String, Integer>> memory = new ArrayList<>();
        memory.add(new HashMap<>());
        Map<String, List<Integer>> currentStack = new HashMap<>();
        Map<Integer, List<String>> stackVariables = new HashMap<>();
        stackVariables.put(0,new ArrayList<>());
        Scanner input = new Scanner(System.in);
        //input.nextLine();
        String line;
        int nowStack = 0;
        while (input.hasNext()) {
            line = input.nextLine();
            String[] parseLine = line.split("=");
            if (!parseLine[0].equals("{") && !parseLine[0].equals("}")) {
                if ((parseLine[1].charAt(0) >= '0' && parseLine[1].charAt(0) <= '9') ||
                        parseLine[1].charAt(0) == '+' || parseLine[1].charAt(0) == '-') {
                    memory.get(nowStack).put(parseLine[0], Integer.parseInt(parseLine[1]));
                    if (currentStack.containsKey(parseLine[0])) {
                        currentStack.get(parseLine[0]).add(nowStack);
                    } else {
                        List<Integer> stack = new ArrayList<>();
                        stack.add(nowStack);
                        currentStack.put(parseLine[0], stack);
                    }
                    stackVariables.get(nowStack).add(parseLine[0]);
                } else {
                    if (!currentStack.containsKey(parseLine[0])) {
                        List<Integer> stack = new ArrayList<>();
                        stack.add(nowStack);
                        currentStack.put(parseLine[0], stack);
                        memory.get(nowStack).put(parseLine[0], 0);
                        stackVariables.get(nowStack).add(parseLine[0]);
                    }
                    if (!currentStack.containsKey(parseLine[1])) {
                        List<Integer> stack = new ArrayList<>();
                        stack.add(nowStack);
                        currentStack.put(parseLine[1], stack);
                        memory.get(nowStack).put(parseLine[1], 0);
                        stackVariables.get(nowStack).add(parseLine[1]);
                    }
                    List<Integer> firstCurStackList = currentStack.get(parseLine[0]);
                    Integer firstCurStack = firstCurStackList.get(firstCurStackList.size() - 1);
                    List<Integer> secondCurStackList = currentStack.get(parseLine[1]);
                    Integer secondCurStack = secondCurStackList.get(secondCurStackList.size() - 1);
                    Integer value = memory.get(secondCurStack).get(parseLine[1]);
                    memory.get(firstCurStack).put(parseLine[0], value);
                    System.out.println(value);
                }
            } else {
                if (parseLine[0].equals("{")) {
                    nowStack++;
                    memory.add(new HashMap<>());
                    stackVariables.put(nowStack, new ArrayList<>());
                } else if (nowStack > 0) {
                    memory.get(nowStack).clear();
                     updateCurrentStackOfVariables(stackVariables.get(nowStack),currentStack);
                    stackVariables.get(nowStack).clear();
                    nowStack--;
                }
            }
        }
    }
}
