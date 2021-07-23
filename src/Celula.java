//Células da lista de Adjacẽncia
public class Celula{
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