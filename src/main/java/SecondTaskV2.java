import java.util.*;

public class SecondTaskV2 {

    static class Team {
        String first;
        String second;
        String third;

        public Team(String first, String second, String third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Team)) return false;
            Team team = (Team) o;
            return Objects.equals(first, team.first) && Objects.equals(second, team.second) && Objects.equals(third, team.third);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second, third);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.nextLine();
        String line;
        Map<Integer, Integer> helper = new HashMap<>();
        int result = 0;
        for (int i = 0; i < n; i++) {
            line = input.nextLine();
            String[] peoples = line.split(" ");
            Arrays.sort(peoples);
            Team team = new Team(peoples[0], peoples[1], peoples[2]);
            int hash = team.hashCode();
            if (helper.containsKey(hash)) {
                int count = helper.get(hash);
                helper.put(hash, count + 1);
            } else {
                helper.put(hash, 1);
            }
            result = Math.max(result, helper.get(hash));
        }
        System.out.println(result);
    }
}
