import java.util.ArrayList;

public class ListaAdj{// A lista podia ser um vetor de inteiros

	//Células da lista de Adjacẽncia
	class Celula{
		int nome;//Vertice de chegada
		int id;//Posicão na lista de vertices

		Celula(int nome, int id) {
			this.nome = nome;
			this.id = id;
		}
		//Métodos
		int getNome(){return nome;}
		int getId(){return id;}
	}
	
	ArrayList<Celula> lista;

	ListaAdj (){
        lista = new ArrayList<Celula>();
    }

	public void adicionaVertice(int nome, int id){
		Celula nova = new Celula(nome, id);
        lista.add(nova);
	}	

	public void imprimeSemCustoGrafo(int v){
		int aux = 0;
		for(Celula x: lista){
			System.out.println(" " + v + "--" + lista.get(aux++).getNome());
		}
	}

}