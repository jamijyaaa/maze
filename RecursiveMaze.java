import java.util.Scanner;

public class RecursiveMaze {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите ширину лабиринта (нечетное число): ");
        int width = scanner.nextInt();
        System.out.print("Введите высоту лабиринта (нечетное число): ");
        int height = scanner.nextInt();

        if (width % 2 == 0) width++;
        if (height % 2 == 0) height++;

        Maze maze = new Maze(width, height); // Пока ещё пустой
    }
}