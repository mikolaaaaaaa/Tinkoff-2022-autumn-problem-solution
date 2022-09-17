//import java.util.*;
//
//public class SixthTaskV2 {
//
//    static class Pair implements Comparable<Pair> {
//        int first;
//        int second;
//
//        public Pair() {
//            first = 0;
//            second = 0;
//        }
//
//        public Pair(int first, int second) {
//            this.first = first;
//            this.second = second;
//        }
//
//        @Override
//        public int compareTo(Pair o) {
//            if (Integer.compare(this.first,o.first) == 0) {
//                return Integer.compare(this.second,o.second);
//            } else {
//                return Integer.compare(this.first,o.first);
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        int n;
//        Scanner input = new Scanner(System.in);
//        n = input.nextInt();
//        List<Pair> elevators = new ArrayList<>();
//        Pair temp = new Pair();
//        Map<Integer,Integer> helper = new TreeMap<>();
//        for(int i = 0; i < n; i++) {
//            temp.first = input.nextInt();
//            temp.second = input.nextInt();
//            if (temp.first == temp.second) {
//                int count = 0;
//                if (helper.containsKey(temp.first)) {
//                    count = helper.get(temp.first);
//                }
//                helper.put(temp.first,count + 1);
//            } else {
//                elevators.add(temp);
//            }
//        }
//        Collections.sort(elevators);
//        for(Pair i : elevators) {
//            if (helper.get(i.first).equals(helper.) {
//                int count = 0;
//                if (helper.containsKey(i.second)) {
//                    count = helper.get(i.second);
//                }
//                helper.put(i.second,count + 1);
//            } else {
//                int firstCount = 0;
//                int secondCount = 0;
//                if (helper.containsKey(i.first)) {
//                    firstCount = helper.get(i.first);
//                }
//                if (helper.containsKey(i.second)) {
//                    secondCount = helper.get(i.second);
//                }
//                helper.put(i.second,Math.max(secondCount,firstCount + 1));
//            }
//        }
//        int result= 0;
//        for(Map.Entry<Integer,Integer> it : helper.entrySet()) {
//            result = Math.max(result,it.getValue());
//        }
//        System.out.println(result);
//    }
//}
