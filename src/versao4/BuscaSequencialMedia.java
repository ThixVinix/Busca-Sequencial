package versao4;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BuscaSequencialMedia {

	private static Scanner sc = new Scanner(System.in);
	private static Random random = new Random();
	private static int contadorIndice = 0;
	private static final int QTD_EXPERIMENTOS = 10;
	private static final int VETOR_TAMANHO_MAXIMO = 1000;
	private static final int PRIMEIRA_PASSAGEM = 1;
	private static int qtdBuscasSequenciais = 0;
	private static int[] vetor;
	private static int x;
	private static int resultadoSomaIndices = 0;
	private static Double resultadoMedia;
	private static ArrayList<Double> listaResultados = new ArrayList<Double>();

	public static void main(String[] args) {
		menu();
	}

	public static void menu() {

		while (true) {
			sc = new Scanner(System.in);
			int escolha = sc.nextInt();
			switch (escolha) {
			case 0:
				iniciarComando0();
				imprimirResultadoSimplificado();
				resetarValores();
				break;
			case 1:
				iniciarComando1();
				resetarValores();
				sc.close();
				System.exit(0);
				break;
			case 2:
				sc.close();
				System.out.println("Finalizado.");
				System.exit(0);
				break;
			default:
				System.err.println("Comando Inválido. Digite um comando válido:");
				continue;
			}
		}
	}

	private static void iniciarComando0() {
		for (int i = PRIMEIRA_PASSAGEM; i <= QTD_EXPERIMENTOS; i++) {
			System.out.println("\t\t" + i + "º Experimento: \n");
			iniciarBuscaSequencialComando0(1, i);
			iniciarBuscaSequencialComando0(10, i);
			iniciarBuscaSequencialComando0(100, i);
			iniciarBuscaSequencialComando0(1000, i);
			iniciarBuscaSequencialComando0(10000, i);
			System.out.println("---------------------------------------------------------\n");
		}
	}

	private static void iniciarComando1() {
		iniciarBuscaSequencialComando1(1);
	}

	private static void iniciarBuscaSequencialComando0(int qtdElementosMedia, int indiceInicial) {
		resultadoSomaIndices = 0;
		resultadoMedia = 0.0;
		if (indiceInicial == PRIMEIRA_PASSAGEM) {
			qtdBuscasSequenciais++;
		}

		if (qtdElementosMedia == 1) {
			x = aleatorizarNumero();
			vetor = new int[VETOR_TAMANHO_MAXIMO];
			buscarValorEquivalenteComando0();
			resultadoSomaIndices = contadorIndice;
			contadorIndice = 0;
		} else {
			int elementoAtual = 1;
			while (elementoAtual < qtdElementosMedia) {
				x = aleatorizarNumero();
				vetor = new int[VETOR_TAMANHO_MAXIMO];
				buscarValorEquivalenteComando0();
				resultadoSomaIndices += contadorIndice;
				contadorIndice = 0;
				elementoAtual++;
			}
		}

		resultadoMedia = calcularMedia(qtdElementosMedia);
		imprimirResultadoMedia(qtdElementosMedia);
		listaResultados.add(resultadoMedia);
	}

	private static void iniciarBuscaSequencialComando1(int qtdElementosMedia) {
		resultadoSomaIndices = 0;
		resultadoMedia = 0.0;

		if (qtdElementosMedia == 1) {

			while (true) {
				try {
					sc = new Scanner(System.in);
					x = sc.nextInt();
				} catch (Exception e) {
					System.err.println("Causa do erro: " + e.getMessage() + ". Digite novamente:");
					continue;
				}

				break;
			}
			vetor = new int[VETOR_TAMANHO_MAXIMO];
			buscarValorEquivalenteComando1();
			resultadoSomaIndices = contadorIndice;
			contadorIndice = 0;
		} else {
			int elementoAtual = 1;
			while (elementoAtual < qtdElementosMedia) {
				while (true) {
					sc = new Scanner(System.in);
					try {
						x = sc.nextInt();
					} catch (Exception e) {
						System.err.println("Causa do erro: " + e.getMessage() + ". Digite novamente:");
						continue;
					}
					break;
				}
				vetor = new int[VETOR_TAMANHO_MAXIMO];
				buscarValorEquivalenteComando1();
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

	private static int buscarValorEquivalenteComando0() {
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

	private static int buscarValorEquivalenteComando1() {
		for (int i = 0; i < vetor.length; i++) {
			contadorIndice++;
			while (true) {
				try {
					sc = new Scanner(System.in);
					vetor[i] = sc.nextInt();
					if (vetor[i] > VETOR_TAMANHO_MAXIMO + 1) {
						vetor[i] = VETOR_TAMANHO_MAXIMO + 1;
					}
				} catch (Exception e) {
					System.err.println("Causa do erro: " + e.getMessage() + ". Digite novamente:");
					continue;
				}
				break;
			}

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

	private static void resetarValores() {
		qtdBuscasSequenciais = 0;
		resultadoSomaIndices = 0;
		resultadoMedia = 0.0;
		resultadoSomaIndices = 0;
		listaResultados.clear();
	}
}
