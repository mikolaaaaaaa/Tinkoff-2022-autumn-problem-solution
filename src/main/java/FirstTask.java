import java.util.Scanner;

public class FirstTask {

    public static int solution(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {

        int minX;
        int minY;
        int maxX;
        int maxY;

        minX = Math.min(x1,x2);
        minX = Math.min(minX,x3);
        minX = Math.min(minX,x4);

        minY = Math.min(y1,y2);
        minY = Math.min(minY,y3);
        minY = Math.min(minY,y4);

        maxX = Math.max(x1,x2);
        maxX = Math.max(maxX,x3);
        maxX = Math.max(maxX,x4);

        maxY = Math.max(y1,y2);
        maxY = Math.max(maxY,y3);
        maxY = Math.max(maxY,y4);

        return Math.max(Math.abs(maxX - minX), Math.abs(maxY-minY)) * Math.max(Math.abs(maxX - minX), Math.abs(maxY-minY));
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int x1 = input.nextInt();
        int y1 = input.nextInt();
        int x2 = input.nextInt();
        int y2 = input.nextInt();
        int x3 = input.nextInt();
        int y3 = input.nextInt();
        int x4 = input.nextInt();
        int y4 = input.nextInt();

        System.out.println(solution(x1,y1,x2,y2,x3,y3,x4,y4));
    }
}
