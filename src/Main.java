import java.util.Scanner;
import java.util.Vector;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;

class Grafo{

	//private int inicio;

	private class Vertice{
		int nome;
		int id;
		int pai;//Id do vértice pai
		int altura;//Tamanho do percurso até a raiz
		
		Vertice(int nome, int id, int pai, int altura){
			this.nome = nome;
			this.id = id;
			this.pai = pai;
			this.altura = altura;
		}

		public int getId(){return id;}
		public int getNome(){return nome;}
		public int getPai(){return pai;}
		public int getAltura(){return altura;}
	}

	Vector<Vertice> vertices = new Vector<Vertice>();
	Queue<Vertice> fila = new LinkedList<>();
	//LinkedList<Vertice> vertices = new LinkedList<Vertice>();


	Grafo(int inicio){//O grafo inicialmente só tem um nó
		//this.inicio = inicio;
		Vertice vertice = new Vertice(inicio, 0, 0, 0);//Seria o nó Raiz
		this.vertices.add(vertice);
	}

	void adicionaVertice(Vertice v){
		vertices.add(v);
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


	private void verificaRepeticao(int nome){
		for(Vertice x: fila){
			if(x.getNome() == nome){
				fila.remove(x);
				return;
			}
		}
	}

	static private boolean qtdAlgarismosIguais(int num1, int num2){

		int j = 0, i = 0;
		int tamN1 = 0;
		int tamN2 = 0;

		while (num1 > 0) {
			j *= 10;
			j = (num1%10);
			num1 /= 10;
			tamN1++;
		}

		while (num2 > 0) {
			j *= 10;
			j = (num2%10);
			num2 /= 10;
			tamN2++;
		}

		if(tamN1 >= tamN2){
			return true;
		}else{
			return false;
		}

	}

	boolean verificaVerticeExistente(int nome){
		for(Vertice v: vertices){
			if(v.getNome() == nome){
				return false;
			}
		}
		return true;
	}

	int constroi(int vertice){//Percorre o grafo, enquanto cria os vetices

		
		int indice = 0;
		int nome;
		fila.add(vertices.get(0));
		
		while (true) {
			Vertice v = fila.poll();

			//System.out.print("Sou o vertice " + v.getNome() + " Filho: ");

			//Cria filho da esquerda
			nome = inverte(v.getNome());
				if(nome != v.getNome() && qtdAlgarismosIguais(nome, v.getNome())){
					if(verificaVerticeExistente(nome)){//Só vai criar se o vertice não existe
						Vertice esq = new Vertice(nome, ++indice, v.getId(), v.getAltura()+1);
						//verificaRepeticao(nome);//Remove da fila algum vertice que vai gerar a mesma arvore
						//System.out.print("Esq " + nome);
						adicionaVertice(esq);
						if(nome == vertice){
							return esq.getAltura();
						}
						fila.add(esq);
					}
				}
			
			//Cria filho da direita
			nome = v.getNome() + 1;
			Vertice dir = new Vertice(nome, ++indice, v.getId(), v.getAltura()+1);
			//verificaRepeticao(nome);//Remove da fila algum vertice que vai gerar a mesma arvore
			//System.out.println(" Dir " + nome);
			adicionaVertice(dir);
			if(nome == vertice){
				return dir.getAltura();
			}
			fila.add(dir);
		}
	}
}


public class Main{

	public static void main(String[] args) throws FileNotFoundException{

		Scanner scan = null;
		int testes;
		int inicio, fim;
		
		scan = new Scanner(System.in);
		testes = scan.nextInt();

		Grafo g;

		for(int i = 0; i < testes; i++){
			inicio = scan.nextInt();
			fim = scan.nextInt();
			g = new Grafo(inicio);
			//System.out.println();
			System.out.println(g.constroi(fim));
		}
		
	}
}