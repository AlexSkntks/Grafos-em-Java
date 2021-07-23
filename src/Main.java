import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
public class Main{

	public static void main(String[] args){

	
		Scanner scanGrafo;
		Scanner scanDigrafo;

		try{
			scanGrafo = new Scanner(new File("grafo_n_m.txt"));
			criaGrafo(scanGrafo, true);

		}catch(FileNotFoundException e){
			try{
				scanDigrafo = new Scanner(new File("digrafo_n_m.txt"));
                criaGrafo(scanDigrafo, false);
			}catch(FileNotFoundException r){
				r.printStackTrace();
			}
		}
		
	}

    static private void criaGrafo(Scanner scan, boolean graf){
        

        System.out.println("Escolha 1 para representação em matriz ou 2 para representação em lista");
		Scanner leitura = new Scanner(System.in);
        int choice = 0;

        while(choice != 1 && choice != 2){
            choice = leitura.nextInt();
            if(choice != 1 && choice != 2){
                System.out.println("Numero incorreto!! favor digitar os valores contidos no programa!");
            }
        }

        boolean flagcusto = false;

        String linha;
		String[] valores;
		int n = 0;
		int m = 0;

        //Rotina que ignora os comentários e lê a primeira linha
		while (scan.hasNextLine()) {
			linha = scan.nextLine();
			if (!linha.contains("c ")){
				valores = linha.split(" ");
				n = Integer.parseInt(valores[0]);
				m = Integer.parseInt(valores[1]);
				break;
			}
		}

		Grafo grafo = new Grafo(n, m);

		//Rotina que lê as arestas, ignora qualquer linha de comentário
		int aux = m;
		while(scan.hasNextLine() && aux != 0){
			linha = scan.nextLine();
			if (!linha.contains("c ")){
				valores = linha.split(" ");
				int v1 = Integer.parseInt(valores[0]);
				int v2 = Integer.parseInt(valores[1]);
				int custo = Integer.parseInt(valores[2]);

                if(custo > 0){
                    flagcusto = true;
                }
				grafo.insereAdj(v1, v2, custo);
                
				aux--;
			}
		}

		try{
			if(choice == 1){
				grafo.imprimeArestasMatriz(graf, flagcusto);
			}
			else{
				grafo.imprimeArestasLista(graf, flagcusto);
			}
		}catch(IOException e){
			e.printStackTrace();
		}

    
    }
}