package versao3;

import java.util.ArrayList;
import java.util.Random;

public class BuscaSequencialMedia {

	private static Random random = new Random();
	private static int contadorIndice = 0;
	private static final int QTD_EXPERIMENTOS = 10;
	private static final int VETOR_TAMANHO_MAXIMO = 1000;
	private static int qtdBuscasSequenciais = 0;
	private static int[] vetor;
	private static int x;
	private static int resultadoSomaIndices = 0;
	private static Double resultadoMedia;
	private static ArrayList<Double> listaResultados = new ArrayList<Double>();

	public static void main(String[] args) {
		iniciar();
		imprimirResultadoSimplificado();
	}

	private static void iniciar() {
		for (int i = 1; i <= QTD_EXPERIMENTOS; i++) {
			System.out.println("\t\t" + i + "º Experimento: \n");
			iniciarBuscaSequencial(1, i);
			iniciarBuscaSequencial(10, i);
			iniciarBuscaSequencial(100, i);
			iniciarBuscaSequencial(1000, i);
			iniciarBuscaSequencial(10000, i);
			System.out.println("---------------------------------------------------------\n");
		}
	}

	private static void iniciarBuscaSequencial(int qtdElementosMedia, int indiceInicial) {
		resultadoSomaIndices = 0;
		resultadoMedia = 0.0;
		if (indiceInicial == 1) {
			qtdBuscasSequenciais++;
		}

		if (qtdElementosMedia == 1) {
			x = aleatorizarNumero();
			vetor = new int[VETOR_TAMANHO_MAXIMO];
			buscarValorEquivalente();
			resultadoSomaIndices = contadorIndice;
			contadorIndice = 0;
		} else {
			int elementoAtual = 1;
			while (elementoAtual < qtdElementosMedia) {
				x = aleatorizarNumero();
				vetor = new int[VETOR_TAMANHO_MAXIMO];
				buscarValorEquivalente();
				resultadoSomaIndices += contadorIndice;
				contadorIndice = 0;
				elementoAtual++;
			}
		}

		resultadoMedia = calcularMedia(qtdElementosMedia);
		imprimirResultadoMedia(qtdElementosMedia);
		listaResultados.add(resultadoMedia);
	}

	private static int aleatorizarNumero() {
		int numeroAleatorio = random.nextInt(VETOR_TAMANHO_MAXIMO);
		return numeroAleatorio;
	}

	private static int buscarValorEquivalente() {
		for (int i = 0; i < vetor.length; i++) {
			contadorIndice++;
			vetor[i] = aleatorizarNumero();

			if (x == vetor[i]) {
				contadorIndice++;
				return i;
			}
		}
		contadorIndice++;
		return -1;
	}

	private static double calcularMedia(int qtdElementosMedia) {
		return (resultadoSomaIndices / qtdElementosMedia);
	}

	private static void imprimirResultadoMedia(int qtdElementosMedia) {
		System.out.println(
				" A média entre " + qtdElementosMedia + "\telemento(s) calculada foi: " + resultadoMedia.shortValue());
	}

	private static void imprimirResultadoSimplificado() {
		int m[][] = new int[QTD_EXPERIMENTOS][qtdBuscasSequenciais];
		int linha = 0;
		int coluna = 0;
		for (int i = 0; i < listaResultados.size(); i++) {
			if (coluna == qtdBuscasSequenciais) {
				coluna = 0;
				linha++;
			}

			m[linha][coluna] = listaResultados.get(i).intValue();

			coluna++;
		}

		System.out.println(" Para copiar e colar os resultados respectivamente na planilha: \n");
		for (int c = 0; c < qtdBuscasSequenciais; c++) {
			for (int l = 0; l < QTD_EXPERIMENTOS; l++) {
				if (l == QTD_EXPERIMENTOS - 1) {
					System.out.println(m[l][c]);
				} else {
					System.out.print(m[l][c] + "\t");
				}
			}
		}

	}
}
