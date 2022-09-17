import java.io.IOException;
import java.util.*;

public class FourthTaskV2 {

    public static void updatePosition(List<Integer> position, int curPosition) {
        while(position.get(position.size() - 1) > curPosition) {
            position.remove(position.size() - 1);
        }
    }

    public static void insertInMemory(int value, String variable, int nowStack,
                                      Map<String,List<Integer>> memory,
                                      Map<String,List<Integer>> position) {
        if (memory.containsKey(variable)) {
            int nowStackSize = memory.get(variable).size();
            if (nowStackSize < nowStack) {
                int addStacksCount = nowStack - nowStackSize;
                List<Integer> addElements= new ArrayList<>(addStacksCount);
                addElements.set(addStacksCount - 1,value);
                memory.get(variable).addAll(addElements);
            } else {
                memory.get(variable).set(nowStackSize - 1,value);
            }
        } else {
            memory.put(variable, new ArrayList<>());
            memory.get(variable).add(value);
        }
        if (position.containsKey(variable)) {
            position.get(variable).add(nowStack);
        } else {
            position.put(variable,new ArrayList<>());
            position.get(variable).add(nowStack);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String line;
        Map<String, List<Integer>> memory = new HashMap<>();
        Map<String, List<Integer>> position = new HashMap<>();
        List<String> stackVariables = new ArrayList<>();
        int nowStack = 0;
        while(input.hasNext()) {
            line = input.nextLine();
            String[] expression = line.split(" ");
            if (expression[0].equals("{")) {
               nowStack++;
            } else if (expression[0].equals("}")) {
               nowStack--;
            } else {
                if ((expression[1].charAt(0) >= '0' && expression[1].charAt(0) <= '9') ||
                        (expression[1].charAt(0) == '+') || (expression[1].charAt(0) == '-')) {
                    int value = Integer.parseInt(expression[1]);
                    String variable = expression[0];
                    insertInMemory(value,variable,nowStack,memory,position);
//                    if (memory.containsKey(expression[0])) {
//                       int nowStackSize = memory.get(expression[0]).size();
//                      if (nowStackSize < nowStack) {
//                          int addStacksCount = nowStack - nowStackSize;
//                          List<Integer> addElements= new ArrayList<>(addStacksCount);
//                          addElements.set(addStacksCount - 1,Integer.parseInt(expression[1]));
//                          memory.get(expression[0]).addAll(addElements);
//                      } else {
//                          memory.get(expression[0]).set(nowStackSize - 1,Integer.parseInt(expression[1]));
//                      }
//                   } else {
//                       memory.put(expression[0], new ArrayList<>());
//                       memory.get(expression[0]).add(Integer.parseInt(expression[1]));
//                   }
//                   if (position.containsKey(expression[0])) {
//                       position.get(expression[0]).add(nowStack);
//                   } else {
//                       position.put(expression[0],new ArrayList<>());
//                       position.get(expression[0]).add(nowStack);
//                   }
                } else {
                    if (!position.containsKey(expression[0])) {
                        memory.put(expression[0],new ArrayList<>(nowStack));
                        memory.get(expression[0]).set(nowStack,0);
                        position.put(expression[0],new ArrayList<>());
                        position.get(expression[0]).add(nowStack);
                    }
                    if (!position.containsKey(expression[1])) {
                        memory.put(expression[1],new ArrayList<>(nowStack));
                        memory.get(expression[1]).set(nowStack,0);
                        position.put(expression[1],new ArrayList<>());
                        position.get(expression[1]).add(nowStack);
                    }
                    List<Integer> leftPositionList = position.get(expression[0]);
                    List<Integer> rightPositionList = position.get(expression[1]);
                    updatePosition(leftPositionList,nowStack);
                    updatePosition(rightPositionList,nowStack);

                 //   int rValue = memory.get(expression[1]).get(position.get());
                }
            }
        }
    }

}
