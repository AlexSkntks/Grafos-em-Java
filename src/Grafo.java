
/** Tem que representar por matriz e por listas de adjacências :/ */

/**
 * a) o arquivo de entrada: o nome do arquivo de entrada deve seguir o padrão:
 * 		grafo_n_m.txt (quando não for direcionado)
			Sem direção com peso
			Sem direção sem peso
 * 		digrafo_n_m.txt (quando for direcionado)
			Com direção, com peso
			Com direção, sem peso
 * b) os arquivos de saída: o nome do arquivo de saída deve seguir o padrão:
 * 		grafo_n_m.dot (quando não for direcionado e não valorado)
 *  	grafov_n_m.dot (quando não for direcionado e valorado)
 * 		digrafo_n_m.dot (quando for direcionado e não valorado)
 *		digrafov_n_m.dot (quando for direcionado e valorado)


*/
import java.util.Vector;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

public class Grafo{

	private int n;
	private int m;

	Vector<ListaAdj> vertices = new Vector<ListaAdj>();
	MatrizAdj matriz;  

	Grafo(int nVertices, int mArestas){
		this.n = nVertices;
		this.m = mArestas;

        for(int i = 0; i < n; i++){
			vertices.add(new ListaAdj());
		}
		matriz = new MatrizAdj(n);
	}


	void insereAdj(int nome, int chegada, int custo){

		//Colocando na forma de lista de Adjacencia
		vertices.get(nome-1).adicionaVertice(chegada, custo);

		//Colocando na forma de matriz
		matriz.addArestaMatriz(nome, chegada, custo);
	}


	void imprimeArestasMatriz(boolean graf, boolean cust) throws IOException{

        if(graf == true){
            if(cust == true){
				//grafov_n_m.dot
                matriz.imprimeMatrizComcustoGrafo(this.n);
            }
            else{
                //grafo_n_m.dot
                matriz.imprimeMatrizSemcustoGrafo(this.n);
            }
        }
        else{
            if(cust == true){
				//digrafov_n_m.dot
                matriz.imprimeMatrizComcustoDigrafo(this.n);
            }
            else{
                //digrafo_n_m.dot
                matriz.imprimeMatrizSemcustoDigrafo(this.n);
            }
        }
		
	}

	/*Uma função de impressão para cada tipo de grafo de acordo com a forma de representação*/
	/*São oito funções*/

	void imprimeArestasLista(boolean graf, boolean cust) throws IOException{
		int count = 1;

		File arquivo;

		FileWriter fw; 

		BufferedWriter bw;

		if(graf == true){

			if(cust == true){
                //grafov_n_m.dot
				arquivo = new File("grafov_n_m.dot");
				fw = new FileWriter(arquivo);
				bw = new BufferedWriter(fw);

				bw.write("graph G\n{");
				bw.newLine();

				for(ListaAdj x: vertices){
					x.imprimeComCustoGrafo(count++, bw);
				}
			}else{
                //grafo_n_m.dot
				arquivo = new File("grafo_n_m.dot");
				fw = new FileWriter(arquivo);
				bw = new BufferedWriter(fw);

				bw.write("graph G\n{");
				bw.newLine();

				for(ListaAdj x: vertices){
					x.imprimeSemCustoGrafo(count++, bw);
				}
			}
			bw.write("}");
			bw.newLine();
		}else{

			if(cust == true){
                //digrafov_n_m.dot
				arquivo = new File("digrafov_n_m.dot");
				fw = new FileWriter(arquivo);
				bw = new BufferedWriter(fw);

				bw.write("digraph G\n{");
				bw.newLine();

				for(ListaAdj x: vertices){
					x.imprimeComCustoDigrafo(count++, bw);
				}
			}else{

                //digrafo_n_m.dot
				arquivo = new File("digrafo_n_m.dot");
				fw = new FileWriter(arquivo);
				bw = new BufferedWriter(fw);

				bw.write("digraph G\n{");
				bw.newLine();

				for(ListaAdj x: vertices){
					x.imprimeSemCustoDigrafo(count++, bw);
				}
			}
			bw.write("}");
			bw.newLine();
		}
		bw.close();
		fw.close();
	}
}