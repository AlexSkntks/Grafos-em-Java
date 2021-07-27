import java.io.IOException;
import java.util.Scanner;

class Teste{

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

	public static void main(String[] args) throws IOException{
		
		Scanner scan = new Scanner(System.in);
		System.out.println(qtdAlgarismosIguais(scan.nextInt(), scan.nextInt()));
		//System.out.println(inverte(scan.nextInt()));
	}
}