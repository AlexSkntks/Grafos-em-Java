import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.IOException;

public class ListaAdj{

	//Células da lista de Adjacẽncia
	class Celula{
		int vertice;//Vertice de chegada
		int custo;

		Celula(int vertice, int custo) {
			this.vertice = vertice;
			this.custo = custo;
		}
		//Métodos
		int getVertice(){return vertice;}
		int getCusto(){return custo;}
	}
	
	ArrayList<Celula> lista;


	ListaAdj (){
        lista = new ArrayList<Celula>();
    }

	public void adicionaVertice(int verticeChegada, int custo){
		Celula novaCelula = new Celula(verticeChegada, custo);
        lista.add(novaCelula);
	}	

	public void imprimeSemCustoGrafo(int v, BufferedWriter bw) throws IOException{
		int aux = 0;
		for(Celula x: lista){
			bw.write(" " + v + " -- " + lista.get(aux++).getVertice() + ";");
			bw.newLine();
			//System.out.println(" " + v + "--" + lista.get(aux++).getVertice());
		}
	}

	public void imprimeComCustoGrafo(int v, BufferedWriter bw)  throws IOException{
		int aux = 0;
		for(Celula x: lista){
			bw.write(" " + v + " -- " + lista.get(aux).getVertice() + " [label = " + lista.get(aux).getCusto() + "];");
			//System.out.println(" " + v + "--" + lista.get(aux).getVertice() + " [label = " + lista.get(aux).getCusto() + "]");
			bw.newLine();
			aux++;
		}
	}

	public void imprimeSemCustoDigrafo(int v, BufferedWriter bw) throws IOException{
		int aux = 0;
		for(Celula x: lista){
			bw.write(" " + v + " -> " + lista.get(aux++).getVertice() + ";");
			//System.out.println(" " + v + "->" + lista.get(aux++).getVertice());
			bw.newLine();
		}
	}


	public void imprimeComCustoDigrafo(int v, BufferedWriter bw) throws IOException{
		int aux = 0;
		for(Celula x: lista){
			bw.write(" " + v + " -> " + lista.get(aux).getVertice() + " [label = " + lista.get(aux).getCusto() + "];");
			//System.out.println(" " + v + "->" + lista.get(aux).getVertice() + " [label = " + lista.get(aux).getCusto() + "]");
			bw.newLine();
			aux++;
		}
	}

}