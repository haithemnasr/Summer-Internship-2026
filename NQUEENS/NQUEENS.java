import java.util.*;
public class NQUEENS{
    boolean underAttack(point[] positions, point p) {
        for (point q : positions) {
            if (q == null)
                continue;
            if (q.col == p.col || q.row == p.row || q.row - q.col == p.row - p.col || q.row + q.col == p.row + p.col) {
                return true;
            }
        }
        return false;
    }
    void solve(int row, point[] positions){
        if (row == positions.length){
            for (point q : positions){
                System.out.print("("+q.row+","+q.col+") ");
            }
            System.out.println();
            return;
        }
        for (int col=0; col< positions.length; col++){
            point p= new point(row, col);
            if (! underAttack(positions, p)){
                positions[row]=p;
                solve(row+1, positions);
                positions[row]=null;
            }

        }
    }
    public static void main(String[] args) {
        System.out.println("chess board size : ");
        Scanner scanner= new Scanner(System.in);
        int n = scanner.nextInt();
        point[] positions = new point[n];
        NQUEENS solver = new NQUEENS();
        solver.solve(0, positions);
    }
}