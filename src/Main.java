import java.util.Scanner;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

class Grafo{

	private int inicio;
	HashMap<Integer, Vertice> map = new HashMap<Integer, Vertice>();

	private class Vertice{
		int nome;
		int altura;//Tamanho do percurso até a raiz
		
		Vertice(int nome, int altura){
			this.nome = nome;
			this.altura = altura;
		}

		public int getNome(){return nome;}
		public int getAltura(){return altura;}
	}

	Grafo(int inicio){//O grafo inicialmente só tem um vértice
		this.inicio = inicio;
		Vertice vertice = new Vertice(inicio, 0);//Seria o nó Raiz
		this.map.put(inicio, vertice);
	}

	void adicionaVertice(Vertice v){
		map.put(v.getNome(), v);
	}

	static private int inverte(int num){
		int aux = num;

		if(aux > 0 && aux < 10){//Trata números de um algarismo
			return aux;
		}

		Integer[] numero = new Integer[5];

		int i = 0;//Tamanho do número
		//Rotina que armazena os caracteres de forma invertida no vetor
		for(int j = 0; aux > 0; i++){
			j *= 10;
			numero[i] = (aux%10);
			aux /= 10;
		}

		String number = new String();

		//Coloca o número em uma string
		for(int j = 0; j < i; j++){
			number += Integer.toString(numero[j]);	
		}

		//Converte a string em Int e retorna
		return Integer.parseInt(number);
	}

	boolean verificaVerticeExistente(int nome){
		if(map.get(nome) == null){
			return true;
		}
		return false;
	}

	//O grafo é construído de forma muito semelhante a uma árvore
	int constroi(int vertice){//Percorre o grafo, enquanto cria os vetices

		Queue<Vertice> fila = new LinkedList<>();
		int nome;//103
		fila.add(map.get(inicio));
		
		while (true) {
			Vertice v = fila.poll();
			System.out.print("Sou o vertice: " + v.getNome());
			//Cria "filho da esquerda"
			nome = inverte(v.getNome());
			if(nome != v.getNome()){//Verifica se a inversão é inútil
				if(verificaVerticeExistente(nome)){//Só vai criar se o vertice não existe
					Vertice esq = new Vertice(nome, v.getAltura()+1);
					System.out.print(" Esq: " + nome);
					adicionaVertice(esq);
					if(nome == vertice){
						return esq.getAltura();
					}
					fila.add(esq);
				}
			}
		
			//Cria "filho da direita"
			nome = v.getNome() + 1;
			if(verificaVerticeExistente(nome)){
				Vertice dir = new Vertice(nome, v.getAltura()+1);
				System.out.print(" Dir: " + nome);
				adicionaVertice(dir);
				if(nome == vertice){
					return dir.getAltura();
				}
				fila.add(dir);
			}
			System.out.println();
		}
	}
}


public class Main{

	public static void main(String[] args){

		Scanner scan = null;
		int testes;
		int inicio, fim;
		
		scan = new Scanner(System.in);
		testes = scan.nextInt();

		Grafo g;

		for(int i = 0; i < testes; i++){
			inicio = scan.nextInt();
			fim = scan.nextInt();//103
			g = new Grafo(inicio);
			System.out.println(g.constroi(fim));
		}
	}
}