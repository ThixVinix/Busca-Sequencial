package versao1;

import java.util.Random;

public class BuscaSequencial {

	private static Random random = new Random();
	private static int count = 0;
	private static final int VETOR_TAMANHO_MAXIMO = 1000;
	private static int[] v;
	private static int x;

	public static void main(String[] args) {
		x = aleatorizarNumero();
		v = new int[VETOR_TAMANHO_MAXIMO];

		switch (buscarValorEquivalente()) {
		case -1:
			System.out.println(
					"Não foi encontrado nenhum valor no vetor igual à: " + x + ". O índice atual é igual à " + count);
			break;
		case 1:
			System.out.println("O valor de \"x\" coincidiu com o valor do vetor \"v\" na posição(índice) " + count
					+ ", e o valor encontrado foi: " + x);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + buscarValorEquivalente());
		}
	}

	private static int aleatorizarNumero() {
		int numeroAleatorio = random.nextInt(VETOR_TAMANHO_MAXIMO);
		return numeroAleatorio;
	}

	private static int buscarValorEquivalente() {
		for (int i = 0; i < v.length; i++) {
			count++;
			v[i] = aleatorizarNumero();
			if (x == v[i]) {
				count++;
				return 1;
			}
		}
		count++;
		return -1;
	}
}
