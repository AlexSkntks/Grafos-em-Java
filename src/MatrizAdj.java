import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

public class MatrizAdj{
    private Integer[][] matriz;

	MatrizAdj(int n){
		matriz = new Integer[n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                matriz[i][j] = null;
            }
        }
	}

    public void addArestaMatriz(int i, int j, int w){
        matriz[i-1][j-1] = w;
    }

    public void imprimeMatrizSemcustoGrafo(int n) throws IOException{
        
        File arquivo = new File( "grafo_n_m.dot" );
        FileWriter fw = new FileWriter( arquivo );
        BufferedWriter bf = new BufferedWriter(fw);

        bf.write("graph G");
        bf.newLine();
        bf.write("{");
        bf.newLine();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(matriz[i][j] != null){
                    bf.write(" " + (i+1) + " -- " + (j+1)+";");
                    bf.newLine();
                }
            }
        }
        bf.write("}");
        bf.newLine();

        bf.close();
        fw.close();
    }

    public void imprimeMatrizSemcustoDigrafo(int n) throws IOException{

        File arquivo = new File( "digrafo_n_m.dot" );
        FileWriter fw = new FileWriter( arquivo );
        BufferedWriter bf = new BufferedWriter(fw);

        bf.write("digraph G");
        bf.newLine();
        bf.write("{");
        bf.newLine();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(matriz[i][j] != null){
                    bf.write(" " + (i+1) + " -> " + (j+1)+";");
                    bf.newLine();
                }
            }
        }
        bf.write("}");
        bf.newLine();
        bf.close();
        fw.close();
    }

    public void imprimeMatrizComcustoGrafo(int n) throws IOException{


        File arquivo = new File( "grafov_n_m.dot" );
        FileWriter fw = new FileWriter( arquivo );
        BufferedWriter bf = new BufferedWriter(fw);

        bf.write("graph G");
        bf.newLine();
        bf.write("{");
        bf.newLine();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(matriz[i][j] != null){
                    bf.write(" " + (i+1) + " -- " +
                     (j+1) + " [label = " + matriz[i][j] + "];");
                     bf.newLine();
                }
            }
        }
        bf.write("}");
        bf.newLine();
        bf.close();
        fw.close();
    }

    public void imprimeMatrizComcustoDigrafo(int n) throws IOException{
        
        File arquivo = new File( "digrafov_n_m.dot" );
        FileWriter fw = new FileWriter( arquivo );
        BufferedWriter bf = new BufferedWriter(fw);
        
        bf.write("digraph G");
        bf.newLine();
        bf.write("{");
        bf.newLine();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(matriz[i][j] != null){
                    bf.write(" " + (i+1) + " -> " +
                     (j+1) + " [label = " + matriz[i][j] + "];");
                     bf.newLine();
                }
            }
        }
        bf.write("}");
        bf.newLine();
        bf.close();
        fw.close();
    }
}