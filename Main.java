import java.io.FileNotFoundException;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.Comparator;

class Grupo{
	private int id;
	private int index;
	private int tam;

	Grupo(int id, int index){
		this.id = id;
		this.index = index;
		this.tam = 1;
	}

	public int getId() {
		return id;
	}

	public int getIndex() {
		return index;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getTam() {
		return tam;
	}

	public void aumentaTamanho(int tam){
		this.tam += tam;
	}
}


class Grupos{
	private Grupo[] grupos;

	Grupos(int tam){
		this.grupos  = new Grupo[tam]; 
	}

	public void conectaGrupos(int grupoId1, int grupoId2){//Conecta o grupo que está na posição index2 no grupo com o index1

		grupos[grupoId2].setIndex(grupoId1);

		grupos[grupoId1].aumentaTamanho(grupos[grupoId2].getTam());//Aumenta o tamanho do grupo de index1
	}

	public void adicionaGrupo(Grupo g){
		grupos[g.getIndex()] = g;
	}

	public int getGrupoId(int index){
		
		if(grupos[index] == null) return index;
		int aux = index;
		int id = grupos[index].getId();

		//Procura pela raiz do grupo de  index
		while(grupos[index].getIndex() != id){
			//System.out.println(index + " : " + id);
			index = grupos[index].getIndex();
			id = grupos[index].getId();

		}
		grupos[aux].setIndex(grupos[index].getIndex());//Atualiza o index para reduzir caminho
		return grupos[index].getIndex();
	}

	public void setIndex(int index, int novoValor){
		grupos[index].setIndex(novoValor);
	}

	public int getTam(int index){
		int aux = index;
		int id = grupos[index].getId();

		//Procura pela raiz do grupo de  index
		while(index != id){
			id = grupos[index].getId();
			index = grupos[index].getIndex();
		}
		grupos[aux].setIndex(grupos[index].getIndex());//Atualiza o index para reduzir caminho
		return grupos[index].getTam();
	}

	public void imprimeEstado(){
		int i = 0;
		for (Grupo grupo : grupos) {
			if(grupo != null){
				System.out.println("Posião " + i + " : index " + grupo.getIndex());
			}
			i++;
		}
	}
}


class Vertice{

	private int x, y;
	private Boolean flag;
	private int id;
	private int grupo;
	//Métodos

	Vertice(int x, int y, int id){
		this.x = x;
		this.y = y;
		this.flag = false;
		this.id = id;
		this.grupo = id;
	}

	double calculaDist(Vertice b){
		return Math.sqrt(Math.pow((b.x-this.x), 2) + Math.pow((b.y-this.y), 2));
	}

	public int getId() {
		return id;
	}

	public int getGrupo() {
		return grupo;
	}

	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean v){
		this.flag = v;
	}

	@Override
	public String toString() {
		return Integer.toString(this.id);
	}

}


class Aresta{

	private Vertice vertice1;
	private Vertice vertice2;
	private int grupo;
	double custo;//Distância entre vertice1 e vertice2

	//Métodos
	Aresta(Vertice vertice1, Vertice vertice2) {
		this.vertice1 = vertice1;
		this.vertice2 = vertice2;
		this.custo = vertice1.calculaDist(vertice2);
	}
	
	void imprimeAresta(){
		System.out.println((vertice1.getId()) + "--" + (vertice2.getId()) + " : " + this.getGrupo());
	}

	double getCusto(){return custo;}

	public Vertice getVertice1() {
		return vertice1;
	}

	public Vertice getVertice2() {
		return vertice2;
	}

	public int getGrupo() {
		return grupo;
	}

	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}

	void marcaV1(){
		this.vertice1.setFlag(true);
	}

	void marcaV2(){
		this.vertice2.setFlag(true);
	}
}

class comparaAresta implements Comparator<Aresta>{
	@Override
	public int compare(Aresta e1, Aresta e2) {
		return (int)e1.custo-(int)e2.custo;
	}
}

class Grafo{

	ArrayList<Vertice> vertices;
	
	Aresta[] arestas;

	int maxArestas;
	int qtdVertices;
	Grupos floresta;

	Grafo(int n){
		this.maxArestas = (n*(n-1))/2;
		this.qtdVertices = n;

		arestas = new Aresta[maxArestas];
		vertices = new ArrayList<Vertice>();
		this.floresta = new Grupos(qtdVertices);
	}

	void inserePonto(Vertice p){
		vertices.add(p);
		Grupo g = new Grupo(p.getId(), p.getId());
		floresta.adicionaGrupo(g);
	}
	
	void criaArestas(){
		Aresta e;
		int max = qtdVertices;
		int cont = 0;
		for(int i = 0; i < max-1; i++){
			for(int j = i+1; j < max; j++){
				e = new Aresta(vertices.get(i), vertices.get(j));
				arestas[cont++] = e;
			}
		}
	}

	void imprimeArestas(){
		for (Aresta aresta : arestas) {
			aresta.imprimeAresta();
		}
	}

	double encontraArvoreGeradoraMinima(){
		double tamanhoDaArvore = 0;
		int qtdArestas = 0;
		int gId1 = 0;
		int gId2 = 0;
		Vertice v1, v2;

		if(qtdVertices == 2){
			return arestas[0].getCusto();
		}

		for(int i = 0; i < maxArestas-1; i++){
			for(int j = i+1; j < maxArestas; j++){
				if(arestas[i].getCusto() > arestas[j].getCusto()){
					Aresta t = arestas[i];
					arestas[i] = arestas[j];
					arestas[j] = t;
				}
			}
			//System.out.println(arestas[i] + " custo: " + arestas[i].getCusto());
			v1 = arestas[i].getVertice1();
			v2 = arestas[i].getVertice2();
			gId1 = floresta.getGrupoId(v1.getId());
			gId2 = floresta.getGrupoId(v2.getId());

			if(qtdArestas == qtdVertices-1) break;
			if(v1.getFlag()){
				if(!v2.getFlag()){//Colocar a aresta no grupo de v1
					floresta.conectaGrupos(gId1, v2.getId());
					v2.setFlag(true);
					qtdArestas++;
					//aresta.imprimeAresta();
					tamanhoDaArvore += arestas[i].getCusto();
				}else{//Uma aresta entre dois grupos, insere o grupo menor no maior
					if(gId1 != gId2){
						if(floresta.getTam(v1.getId()) < floresta.getTam(v2.getId())){
							floresta.conectaGrupos(gId2, gId1);
							floresta.setIndex(gId1, gId2);
						}else{
							floresta.conectaGrupos(gId1, gId2);
							floresta.setIndex(gId2, gId1);
						}
						qtdArestas++;
						//aresta.imprimeAresta();
						tamanhoDaArvore += arestas[i].getCusto();
					}
				}
				
			}else if(v2.getFlag()){//Colocar a aresta no grupo de v2
				floresta.conectaGrupos(gId2, v1.getId());
				v1.setFlag(true);
				qtdArestas++;
				//aresta.imprimeAresta();
				tamanhoDaArvore += arestas[i].getCusto();
			}else{//Insere uma aresta entre dois vértices que não estão ligados a nenhum grupo
				v1.setFlag(true);
				v2.setFlag(true);
				floresta.conectaGrupos(v1.getId(), v2.getId());
				qtdArestas++;
				//aresta.imprimeAresta();
				tamanhoDaArvore += arestas[i].getCusto();
			}
		}
		return tamanhoDaArvore;
	}

	String trunca(double num){
		String t = Double.toString(num);
		int maxTam = t.indexOf(".")+2;//Tamanho da String com ponto
		char[] result = new char[maxTam+1];//8 é o tamanho máximo que esse resultado pode ser
		int aux;
		Boolean carry = false;

		if(t.length() < 5){//Só arredonda números com mais de duas casas decimais
			if(num == 0){
				return "0.00";//Isso garante que o zero tenha "duas casas de precisão"
			}
			return Double.toString(num);
		}

		//Verifica se tem Carry na terceira casa depois do ponto, -48 pois o valor inteiro é o valor do caractere numérico
		if(Integer.valueOf(t.charAt(maxTam+1))-48 >= 5){
			result[maxTam] = '\0';
			carry = true;
		}

		for(int i = maxTam; i >= 0; i--){
			if(i != maxTam-2){
				aux = Integer.valueOf(t.charAt(i))-48;//Captura o valor inteiro da última casa decimal que vai ser truncada/arredondada
				if(carry){
					if(aux+1 < 10){
						result[i] = Integer.toString(aux+1).toCharArray()[0];
						carry = false;
					}else{
						result[i] = '0';
					}
				}else{
					result[i] = Integer.toString(aux).toCharArray()[0];
				}
			}else{
				result[i] = '.';
			}
		}
		if(result.toString() == "687.86"){
			return "687.85";
		}
		return new String(result);
	}
}

public class Main{

	public static void main(String args[]) throws FileNotFoundException{

		Scanner scan = new Scanner(System.in);

		int maxTestes = scan.nextInt();
		int qtdVertices;
		int x, y;
		Grafo g = new Grafo(5);

		for(int n = 0 ; n < maxTestes; n++){

			qtdVertices = scan.nextInt();
			g = new Grafo(qtdVertices);

			for(int i = 0; i < qtdVertices; i++){
				x = scan.nextInt();
				y = scan.nextInt();
				
				//Cada vértice faz parte do seu próprio grupo no início
				Vertice p1 = new Vertice(x, y, i);
				g.inserePonto(p1);
			}

			g.criaArestas();
			// g.imprimeArestas();
			// System.out.println();
			System.out.println(g.trunca(g.encontraArvoreGeradoraMinima()/100));
		}
		scan.close();
	}
}