package univ_lecture.knu22_AI;
import java.util.*;
import java.util.Scanner;

/*
input
6 8
10111001
11101111
01100101
11110010
10101111
01111111
*/

public class Labyrinth {
    /**
     * maze[][]에서 좌표 형식의 (x,y) 한 칸을 나타내는 Node 클래스
     * */
    public static class Node {
        int x; // 칸의 x 좌표
        int y; // 칸의 y 좌표
 
        Node prev; // 이동한 경로를 연결리스트로 나타내기 위해 이전 Node를 참조하기 위한 변수
 
        Node(int x, int y, Node prev ) {
            this.x = x; this.y = y;
            this.prev = prev;
        }
        @Override
        public String toString() {
            return String.format("(%d, %d)", x, y);
        }
    }
 
    /**
     * bfs 실행 후 이동경로를 저장하는 리스트.
     * 좌표 형태의 노드(y,x)를 저장한다.
     * @param maze 탐색할 미로
     * @return
     */
    public static List<Node> bfs(int[][] maze) {
        // 미로의 가로세로 길이.
        int row = maze.length;
        int col = maze[0].length;
 
        // 방문한 노드는 true로 표시하기 위한 부울 매트릭스
        boolean[][] visited = new boolean[row][col];
 
        // 출발 지점부터 도착 지점까지 경로를 연결리스트에 저장.
        List<Node> paths = new LinkedList<>();
 
        // bfs를 구현하기 위한 큐.
        Queue<Node> queue = new LinkedList<>();
 
        // 출발지 (0,0).
        visited[0][0] = true;
        queue.offer(new Node(0,0, null));
 
        /* 현재 방문중인 노드의 인접노드들 중에 벽(Wall)이 아닌 길(Path)가 어디에 있는지 확인하기 위해
        현재 위치인 (x,y)에서 서 > 남 > 동 > 북 순서로 탐색하기 위한 거리 배열 변수. */
        int dist_x[] = { 1, 0, -1, 0};
        int dist_y[] = { 0, 1, 0, -1};
 
        // 큐가 빈다는 것은 탐색을 끝냈다는 것을 의미한다.
        while(!queue.isEmpty()) {
            // 현재 큐에 있는 것을 꺼낸다.
            Node node = queue.poll();
            // node가 출구까지 왔는지 체크한다. dest = (col-1, row-1)
            if(node.x == col-1 && node.y == row-1) {
                // 출구에 도착했으면 출구까지 오기 전에 
                // 이전 칸은 무엇이고, 그 이전 칸은 무엇인지
                // 거꾸로 prev 노드를 올라가면서 list에 추가하고 
                // 탐색을 마무리 한다. = Backtracking 
                Node n = node;
                while(n != null) {
                    paths.add(0, n);
                    n = n.prev;
                } break;
            }
 
            // 아직 출구에 도착하지 않았다면 현재 노드에서 인접한 노드들을
            // 서 > 남 > 동 > 북 순으로 검사해본다.
            for(int i = 0 ; i < dist_x.length ; i++) {
                int vx = node.x + dist_x[i];
                int vy = node.y + dist_y[i];
                // 인접한 (vx, vy) 노드가 이전에 방문하였거나 벽이면 방문했다고 저장한다.
                if(isPath(vx , vy, row, col, maze, visited)) {
                    visited[vy][vx] = true;
                    queue.offer(new Node(vx, vy , node));
                }
            }
        } return paths;
    }
 
    /**
     * (y,x) 노드를 방문을 했는지 안 했는지 확인.
     * row, cold은 2차원 배열(미로)의 가로세로 길이.
     * @return  maze 영역을 벗어나지 않았거나, 벽이 아니거나, 혹은 이미 방문한 노드가 아니면 true
     */
    public static boolean isPath(int x, int y, int row, int col, int[][] maze, boolean[][] visited) {
        // 미로를 벗어나면 안 됨.
        if(x < 0 || y < 0 || x > col -1 || y > row - 1) {
            return false;
        }
        // 이미 방문한 노드와 벽은 갈 수 없음.
        if(visited[y][x] || maze[y][x] == 0) {
            return false;
        } return true;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // 미로의 가로세로 크기 입력
        int h = scan.nextInt(); // 세로
        int w = scan.nextInt(); // 가로

        // 미로 선언
        int[][] maze = new int[h][w];

        for(int i = 0; i < h; i++) {
            String temp = scan.next();
            for(int j = 0; j < w; j++) {
                // maze의 해당 좌표가 길인지 벽인지 값을 저장. char형을 int로 삽입할 때 값을 처리
                maze[i][j] = temp.charAt(j) - '0';
            }
        }

        scan.close();

        // // maze test
        // for(int i = 0; i < h; i++) {
        //     for(int j = 0; j < w; j++) {
        //         System.out.print(maze[i][j] + " ");
        //     } System.out.println();
        // }
        
        List<Node> paths = bfs(maze);       // maze에 대한 bfs 실행
        System.out.println(paths.size());   // 이동거리
        // System.out.println(paths);          // 입구부터 출구까지 이동한 위치 정보
 
        // output
        // 이동거리 : 13
        // 이동경로 : [(0, 0), (0, 1), (1, 1), (2, 1), (2, 2), (2, 3), (2, 4), (2, 5), (3, 5), (4, 5), (5, 5), (6, 5), (7, 5)]
    }
}