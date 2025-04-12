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

        Maze maze = new Maze(width, height); 
    }
}
class Maze {
    private final int width, height;
    private final Cell[][] grid;

    public Maze(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new Cell[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = new Cell(x, y);
            }
        }
    }
}
public void generate() {
    for (int y = 0; y < height; y++)
        for (int x = 0; x < width; x++)
            grid[y][x].isWall = true;

    generateRecursive(1, 1);
}

private void generateRecursive(int x, int y) {
    grid[y][x].isWall = false;
    int[] dirs = {0, 1, 2, 3};
    shuffleArray(dirs);

    for (int dir : dirs) {
        int dx = 0, dy = 0;
        switch (dir) {
            case 0 -> dy = -2;
            case 1 -> dy = 2;
            case 2 -> dx = -2;
            case 3 -> dx = 2;
        }

        int nx = x + dx;
        int ny = y + dy;

        if (inBounds(nx, ny) && grid[ny][nx].isWall) {
            grid[y + dy / 2][x + dx / 2].isWall = false;
            generateRecursive(nx, ny);
        }
    }
}
class Cell {
    int x, y;
    boolean isWall = true;
    boolean visited = false;
    boolean isPath = false;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
private Cell entrance, exit;

public void setEntranceAndExit() {
    entrance = grid[1][0];
    exit = grid[height - 2][width - 1];
    entrance.isWall = false;
    exit.isWall = false;
}
public void print() {
    for (Cell[] row : grid) {
        for (Cell cell : row) {
            if (cell == entrance) {
                System.out.print('E');
            } else if (cell == exit) {
                System.out.print('X');
            } else if (cell.isPath) {
                System.out.print('.');
            } else {
                System.out.print(cell.isWall ? '#' : ' ');
            }
        }
        System.out.println();
    }
}