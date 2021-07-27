
import java.util.LinkedList;
import java.util.Queue;

public class Grafo{

	//private int inicio;

	private class Vertice{
		int nome;
		int id;
		int pai;//Id do "pai"
		//ListaAdj lista = new ListaAdj();
		
		Vertice(int nome, int id, int pai){
			this.nome = nome;
			this.id = id;
			this.pai = pai;
		}

		public int getId(){return id;}
		public int getNome(){return nome;}
		public int getPai(){return pai;}
		//void adicionaAdj(int nome, int id){lista.adicionaVertice(nome, id);}
	}

	LinkedList<Vertice> vertices = new LinkedList<Vertice>();


	Grafo(int inicio){//O grafo inicialmente só tem um nó
		//this.inicio = inicio;
		Vertice vertice = new Vertice(inicio, 0, -1);//Seria o nó Raiz
		this.vertices.add(vertice);
	}

	void adicionaVertice(Vertice v){
		vertices.add(v);
	}

	static private int inverte(int num){

		if(num > 0 && num < 10){
			return num;
		}
		int alg1 = num/100;
		num %= 100;
		int alg2 = num/10;
		int alg3 = num%10;

		String aux;
		if (alg3 == 0 && alg2 == 0){
			aux = new String(Integer.toString(alg1));
		}else {
			aux = new String(Integer.toString(alg3) + Integer.toString(alg2) + Integer.toString(alg1));
		}
		
		
		return Integer.parseInt(aux);
	}

	int constroi(int vertice){//Percorre o grafo, enquanto cria os vetices

		Queue<Vertice> fila = new LinkedList<>();
		int indice = 0;
		int nome;
		fila.add(vertices.get(0));
		
		while (true) {
			Vertice v = fila.poll();

			if(v.getNome() == vertice){
				return v.getId();
			}
			//System.out.print("Sou o vertice: " + v.getNome() + " Filho: ");

			//Cria filho da esquerda
			nome = inverte(v.getNome());

			if(!(nome > 0 && nome < 10)){//Verifica se na esquerda é um número de um algarismo
				//System.out.print("Esq: " + nome);
				Vertice esq = new Vertice(nome, ++indice, v.getId());
				//esq.adicionaAdj(nome, indice);
				adicionaVertice(esq);
				fila.add(esq);
			}

			//Cria filho da direita
			nome = v.getNome() + 1;
			//System.out.println(", Dir: " + nome);
			Vertice dir = new Vertice(nome, ++indice, v.getId());
			//dir.adicionaAdj(nome, indice);
			adicionaVertice(dir);
			fila.add(dir);


		}
	}

	int calculaCliques(int id){
		int count = 0;
		while (vertices.get(id).getPai() != -1) {
			id = vertices.get(id).getPai();
			count++;
		}
		return count;
	}


}