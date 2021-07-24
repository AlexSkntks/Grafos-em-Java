import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Scanner scan = new Scanner(System.in);

        int T = scan.nextInt();

        Boolean [] passed;

        while(T-- > 0){

            int Origin = scan.nextInt();
            int V = scan.nextInt();
            int A = scan.nextInt();

            MatrizAdj mat = new MatrizAdj(V);
            passed = new Boolean[V];

            for (int i = 0; i < V; i++){
                passed[i] = false;
            }
            passed[Origin] = true;

            while (A-- > 0){
                int a, b;
                a = scan.nextInt();
                b = scan.nextInt();

                mat.addArestaMatriz(a, b);
                mat.addArestaMatriz(b, a);
            }
            int valor = DFS(mat, Origin, passed) -1;
            //mat.imprimeMatriz(V);
            System.out.println(valor*2);
        }


    }

    private static int DFS (MatrizAdj mat, int origin, Boolean[] passed){
        int x = 0;
        for (int j = 0; j < mat.getSize(); j++){
            if (mat.checkmat(origin, j) && passed[j] == false){
                passed[j] = true;
                x = x + DFS(mat, j, passed);
            }
        }
        return x + 1;
    }
}



class MatrizAdj {
    private Boolean[][] matriz;
    private int size;

    MatrizAdj(int var1) {
        this.matriz = new Boolean[var1][var1];

        for(int var2 = 0; var2 < var1; ++var2) {
            for(int var3 = 0; var3 < var1; ++var3) {
                this.matriz[var2][var3] = false;
            }
        }
        this.size = var1;
    }

    public void addArestaMatriz(int i, int j) {
        this.matriz[i][j] = true;
    }

    public boolean checkmat(int i, int j){
        return this.matriz[i][j];
    }

    public int getSize(){
        return this.size;
    }

    public void imprimeMatriz(int var1){
        for (int i = 0; i < var1; i++){
            for (int j = 0; j < var1; j++){
                System.out.print(this.matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
}
