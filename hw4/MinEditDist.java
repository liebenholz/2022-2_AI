package univ_lecture.knu22_AI;
import java.util.Scanner;

public class MinEditDist {

    public int Dist[][] = new int[1000][1000];
 
    public int getMini(int a, int b) {
        int min = a;
        if(min > b) min = b;
        return min;
    }

    public int getMin(int a, int b, int c) {
        int min = a;
        if(min > b) min = b;
        if(min > c) min = c;
        return min;
    }
 
    public void getDistance(String a, String b) {
        // (1) 삽입 : 비용 5
        for(int i = 0; i <= a.length(); i++) {
            Dist[i][0] = i*5;
        }
        for(int j = 0; j <= b.length(); j++) {
            Dist[0][j] = j*5;
        }

        // (2) 삭제 : 비용 5
        


        for(int i = 1; i <= a.length(); i++) {
            for(int j = 1; j <= b.length(); j++) {
                // (3) 동일할 경우 : 비용 0
                if(a.charAt(i-1) == b.charAt(j-1)) {
                    Dist[i][j] = Dist[i - 1][j - 1];
                } 
                // (a) 대소문자만 다른 경우 : 비용 1
                else if(Character.toLowerCase(a.charAt(i-1)) == Character.toLowerCase(b.charAt(j-1))) {
                    Dist[i][j] = getMin(Dist[i - 1][j], Dist[i - 1][j - 1], Dist[i][j - 1]) + 1;
                }
                // (b) 둘 다 숫자인 경우 : 비용 abs(na-nb)
                else if(a.charAt(i-1) >= '0' && a.charAt(i-1) <= '9' && b.charAt(j-1) >= '0' && b.charAt(j-1) <= '9') {
                    int na = a.charAt(i-1); int nb = b.charAt(j-1);
                    Dist[i][j] = getMin(Dist[i - 1][j], Dist[i - 1][j - 1], Dist[i][j - 1]) + Math.abs(nb-na);
                }
                // (c) 둘 다 알파벳인 경우 : 비용 5
                else if(a.charAt(i-1) >= 'a' && a.charAt(i-1) <= 'z' && b.charAt(j-1) >= 'A' && b.charAt(j-1) <= 'Z') {
                    Dist[i][j] = getMin(Dist[i - 1][j], Dist[i - 1][j - 1], Dist[i][j - 1]) + 5;
                }
                else if(a.charAt(i-1) >= 'a' && a.charAt(i-1) <= 'z' && b.charAt(j-1) >= 'a' && b.charAt(j-1) <= 'z') {
                    Dist[i][j] = getMin(Dist[i - 1][j], Dist[i - 1][j - 1], Dist[i][j - 1]) + 5;
                }
                else if(a.charAt(i-1) >= 'A' && a.charAt(i-1) <= 'Z' && b.charAt(j-1) >= 'A' && b.charAt(j-1) <= 'Z') {
                    Dist[i][j] = getMin(Dist[i - 1][j], Dist[i - 1][j - 1], Dist[i][j - 1]) + 5;
                }
                else if(a.charAt(i-1) >= 'a' && a.charAt(i-1) <= 'z' && b.charAt(j-1) >= 'a' && b.charAt(j-1) <= 'z') {
                    Dist[i][j] = getMin(Dist[i - 1][j], Dist[i - 1][j - 1], Dist[i][j - 1]) + 5;
                }
                // (d) 하나는 알파벳, 다른 하나는 숫자인 경우 : 비용 7
                else { 
                    Dist[i][j] = getMin(Dist[i - 1][j], Dist[i - 1][j - 1], Dist[i][j - 1]) + 7;
                }

                // 끝에 도달했을 때 (2) 삭제 : 비용 5
                if(i==a.length()) {
                    Dist[i][j] = getMini(Dist[i][j], Dist[i][j - 1] + 5);
                }
                if(j==b.length()){
                    Dist[i][j] = getMini(Dist[i][j], Dist[i - 1][j] + 5) ;
                }
            }
        }
        // 편집거리 출력
        System.out.println(Dist[a.length()][b.length()]);
        
        // for(int i=0; i<=a.length(); i++) {
        //     for(int j=0; j<=b.length(); j++) {
        //         System.out.printf(Dist[i][j]+ "\t");
        //     } System.out.println();
        // }
    }
 
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // 두 개의 문자열 입력
        String a = scan.next();
        String b = scan.next();

        // 최소 편집 거리 계산
        MinEditDist med = new MinEditDist();
        med.getDistance(a, b);
        scan.close();
    }
}
