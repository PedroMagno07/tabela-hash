import java.util.Random;

public class TabelaHash {

    static int TAM = 100;


    static boolean rehash(int ocupadas) {
        // passou de 90% nao deixa inserir mais
        if (ocupadas * 100 / TAM >= 90) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {

        int[] matriz = new int[TAM];
        boolean[] usada = new boolean[TAM];

        Random rnd = new Random(1090);

        int qtdOcup = 0;
        int colisoesTotal = 0;

        int[] elem = new int[TAM];
        int[] colElem = new int[TAM];
        int n = 0;

        int[] barrados = new int[TAM];
        int nb = 0;


        for (int i = 0; i < 100; i++) {
            int valor = rnd.nextInt(1000);

            if (!rehash(qtdOcup)) {
                barrados[nb] = valor;
                nb++;
                continue;
            }

            int p = valor % TAM;
            int bateu = 0;

            while (usada[p]) {
                bateu++;
                p = (p + 1) % TAM;
            }

            matriz[p] = valor;
            usada[p] = true;
            qtdOcup++;

            colisoesTotal = colisoesTotal + bateu;
            elem[n] = valor;
            colElem[n] = bateu;
            n++;
        }



        System.out.println("=== MATRIZ ===");
        for (int k = 0; k < TAM; k++) {
            if (usada[k]) {
                System.out.println("[" + k + "] = " + matriz[k]);
            } else {
                System.out.println("[" + k + "] = livre");
            }
        }

        System.out.println();
        System.out.println("Elementos inseridos: " + qtdOcup);
        System.out.print("Elementos barrados pelo rehash (" + nb + "): ");
        for (int k = 0; k < nb; k++) {
            System.out.print(barrados[k] + " ");
        }
        System.out.println();

        System.out.println();
        System.out.println("Total de colisoes: " + colisoesTotal);


        System.out.println();
        System.out.println("Os 3 elementos que mais colidiram:");
        boolean[] jaFoi = new boolean[TAM];
        for (int t = 1; t <= 3; t++) {
            int idxMaior = -1;
            for (int j = 0; j < n; j++) {
                if (!jaFoi[j]) {
                    if (idxMaior == -1 || colElem[j] > colElem[idxMaior]) {
                        idxMaior = j;
                    }
                }
            }
            if (idxMaior != -1) {
                jaFoi[idxMaior] = true;
                System.out.println(t + "o - elemento " + elem[idxMaior] + " com " + colElem[idxMaior] + " colisoes");
            }
        }

        System.out.println();
        System.out.print("Posicoes livres: ");
        int livres = 0;
        for (int x = 0; x < TAM; x++) {
            if (!usada[x]) {
                System.out.print(x + " ");
                livres++;
            }
        }
        System.out.println();
        System.out.println("Quantidade de posicoes livres: " + livres);
    }
}
