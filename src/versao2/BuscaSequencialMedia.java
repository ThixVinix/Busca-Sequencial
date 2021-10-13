package versao2;

import java.util.Random;

public class BuscaSequencialMedia {

	private static Random random = new Random();
	private static int contadorIndice = 0;
	private static final int VETOR_TAMANHO_MAXIMO = 1000;
	private static int[] vetor;
	private static int x;
	private static int resultadoSomaIndices = 0;

	public static void main(String[] args) {
		iniciar(1);
		iniciar(10);
		iniciar(100);
		iniciar(1000);
		iniciar(10000);
	}

	private static void iniciar(int qtdElementosMedia) {
		resultadoSomaIndices = 0;

		if (qtdElementosMedia == 1) {
			x = aleatorizarNumero();
			vetor = new int[VETOR_TAMANHO_MAXIMO];
			buscarValorEquivalente();
			resultadoSomaIndices = contadorIndice;
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

		Double resultadoMedia = calcularMedia(qtdElementosMedia);
		imprimirResultadoMedia(qtdElementosMedia, resultadoMedia);
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

	private static void imprimirResultadoMedia(int qtdElementosMedia, Double resultadoMedia) {
		System.out.println(
				" A média entre " + qtdElementosMedia + "\telemento(s) calculada foi: " + resultadoMedia.shortValue());
	}
}
