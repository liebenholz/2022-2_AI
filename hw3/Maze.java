package univ_lecture.knu22_AI;
import java.awt.Point;

public class Maze {

    enum Direction {
        up, right, down, left
    }

    static Direction[] dirs = Direction.values();

    int[][] maze = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 1, 0, 0 }, { 1, 0, 1, 1, 0, 1, 0, 1, 0, 1 },
            { 1, 1, 1, 1, 0, 1, 0, 0, 0, 1 }, { 1, 1, 1, 1, 0, 1, 1, 1, 1, 1 },
            { 1, 0, 0, 0, 0, 1, 1, 1, 1, 1 }, { 1, 0, 1, 1, 0, 1, 1, 1, 1, 1 },
            { 1, 0, 1, 0, 0, 0, 0, 1, 1, 1 }, { 0, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };
    Point start = new Point(0, 8);
    Point finish = new Point(9, 1);

    Point go(Direction dir, Point from) {
        Point result = new Point(from);
        switch (dir) {
        case up:
            if ((from.y == 0) || (maze[from.y - 1][from.x] != 0))
                return null;
            result.translate(0, -1);
            break;
        case right:
            if ((from.x == maze[0].length) || (maze[from.y][from.x + 1] != 0))
                return null;
            result.translate(1, 0);
            break;
        case down:
            if ((from.y == maze.length) || (maze[from.y + 1][from.x] != 0))
                return null;
            result.translate(0, 1);
            break;
        case left:
            if ((from.x == 0) || (maze[from.y][from.x - 1] != 0))
                return null;
            result.translate(-1, 0);
            break;
        }
        return result;
    }

    String tryToGo(Direction dir, Point from) {
        String result;
        Point newPosition = go(dir, from);
        if (newPosition == null)
            return null;
        else if (newPosition.equals(start))
            return null;
        else if (newPosition.equals(finish))
            return "finish!";
        else {
            for (Direction newDir : dirs) {
                switch (newDir) {
                case up:
                    if (dir == Direction.down)
                        continue;
                    break;
                case down:
                    if (dir == Direction.up)
                        continue;
                    break;
                case left:
                    if (dir == Direction.right)
                        continue;
                    break;
                case right:
                    if (dir == Direction.left)
                        continue;
                    break;
                }
                result = tryToGo(newDir, newPosition);
                if (result == null)
                    continue;
                else
                    return newDir + ", " + result;
            }
            return null;
        }
    }

    public static void main(String[] args) {
        Maze maze = new Maze();
        System.out.println("right" + maze.tryToGo(Direction.right, maze.start));

    }
}
