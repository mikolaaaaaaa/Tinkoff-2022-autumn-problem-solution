import java.util.Scanner;

public class ThirdTask {

    public static int solution(int[] nums) {
        int result = 0;
        int min = 0, max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                result += nums[i];
                if (nums[i] < nums[min]) {
                    min = i;
                }
            } else {
                result -= nums[i];
                if (nums[i] > nums[max]) {
                    max = i;
                }
            }
        }
        if (nums[max] > nums[min]) {
            return result + 2 * nums[max] - 2 * nums[min];
        }

        return result;

    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n ; i++) {
            nums[i] = input.nextInt();
        }
        System.out.println(solution(nums));
    }
}
