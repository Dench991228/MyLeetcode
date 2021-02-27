public class Question36 {
    boolean validateRow(char[][] board, int r){
        boolean[] visited = new boolean[9];
        for(int i=0;i<9;i++){
            if(board[r][i]=='.')continue;
            if(visited[board[r][i]='0']){
                return false;
            }
            visited[board[r][i]-'0'] = true;
        }
        return true;
    }
    boolean validateCol(char[][] board, int c){
        boolean[] visited = new boolean[9];
        for(int i=0;i<9;i++){
            if(board[i][c]=='.')continue;
            if(visited[board[i][c]-'0']){
                return false;
            }
            visited[board[i][c]-'0'] = true;
        }
        return true;
    }
    boolean validateBlock(char[][] board, int r, int c){
        boolean[] visited = new boolean[9];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board[r+i][c+j]=='.')continue;
                if(visited[board[r+i][c+j]-'0']){
                    return false;
                }
                visited[board[r+i][c+j]-'0'] = true;
            }
        }
        return true;
    }
    public boolean isValidSudoku(char[][] board) {
        for(int i=0;i<9;i++){
            boolean flag = validateRow(board, i)&validateCol(board, i);
            if(!flag)return false;
        }
        for(int i=0;i<9;i+=3){
            for(int j=0;j<9;j+=3){
                boolean flag = validateBlock(board, i,j);
                if(!flag)return false;
            }
        }
        return true;
    }
}
