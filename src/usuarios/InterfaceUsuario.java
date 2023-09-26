package usuarios;

import java.util.InputMismatchException;
import java.util.Scanner;

import entidades.enums.TipoZonaTerreno;

public class InterfaceUsuario {

    private Scanner scanner;

    public InterfaceUsuario() {
        this.scanner = new Scanner(System.in);
    }

    public double pedirValorImovel() {
        try {
            System.out.print("Digite o valor do imóvel: ");
            return scanner.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println("[ERRO] Valor de imóvel incorreto, por favor utilize apenas números.");
            scanner.next();
            return pedirValorImovel();
        }
    }

    public int pedirPrazoFinanciamento() {
        try {
            System.out.print("Digite o prazo do financiamento em anos: ");
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("[ERRO] Valor de prazo de financiamento incorreto, por favor utilize apenas números.");
            scanner.next();
            return pedirPrazoFinanciamento();
        }
    }

    public double pedirTaxaJuros() {
        try {
            System.out.print("Digite a taxa de juros anual: ");
            return scanner.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println("[ERRO] Valor de taxas de juros anual incorreto, por favor utilize apenas números.");
            scanner.nextDouble();
            return pedirTaxaJuros();
        }
    }

    public int pedirEscolha() {
        try {
            System.out.print("Escolha uma opção: ");
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Erro: Entrada inválida.\nDigite valores válidos : [1] [2] [3] [4]");
            scanner.next();
            return pedirEscolha();
        }
    }

    public double pedirAreaConstruida() {
        try {
            System.out.print("Digite a área construída: ");
            return scanner.nextDouble();
        } catch (InputMismatchException e) {

            System.out.println("[ERRO] Entrada inválida para a área. Por favor, digite um número.");
            scanner.next();
            return pedirAreaConstruida();
        }
    }

    public double pedirTamanhoTerreno() {
        try {
            System.out.print("Digite o tamanho do terreno: ");
            return scanner.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println("[ERRO] Entrada inválida para o tamanho do terreno. Por favor, digite um número.");
            scanner.next();
            return pedirTamanhoTerreno();
        }
    }

    public int pedirNumVagasGaragem() {
        try {
            System.out.print("Digite o número de vagas na garagem: ");
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("[ERRO] Entrada inválida para o número de vagas. Por favor, digite um número.");
            scanner.next();
            return pedirNumVagasGaragem();
        }
    }

    public int pedirNumAndar() {
        try {
            System.out.print("Digite o número do andar: ");
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("[ERRO] Entrada inválida para o número do andar. Por favor, digite um número.");
            scanner.next();
            return pedirNumAndar();
        }
    }

    public TipoZonaTerreno pedirTipoZona() {
        try {
            System.out.print("Digite o tipo de zona (residencial/comercial): ");
            String tipoZonaInput = scanner.next();
            
            if (tipoZonaInput.equalsIgnoreCase("comercial")) {
                return TipoZonaTerreno.COMERCIAL;
            } else if (tipoZonaInput.equalsIgnoreCase("residencial")) {
                return TipoZonaTerreno.RESIDENCIAL;
            } else {
                System.out.println("[ERRO] Tipo de zona inválido. Por favor, digite 'residencial' ou 'comercial'.");
                return pedirTipoZona();
            }
        } catch (InputMismatchException e) {
            System.out.println("[ERRO] Entrada inválida para o tipo de zona. Por favor, digite 'residencial' ou 'comercial'.");
            return pedirTipoZona();
        }
    }




    public void fecharScanner() {
        scanner.close();
    }
}
