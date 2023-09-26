package main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidades.Apartamento;
import entidades.Casa;
import entidades.Financiamento;
import entidades.Terreno;
import entidades.enums.TipoZonaTerreno;
import usuarios.InterfaceUsuario;

public class Main {
    static Scanner sc = new Scanner(System.in);

    private static int contador = 1;
    private static double totalImoveis = 0;
    private static double totalFinanciamentos = 0;
    
    

    public static void main(String[] args) {

        InterfaceUsuario user = new InterfaceUsuario();
        List<Financiamento> list = new ArrayList<>();

         // Carrega os dados serializados

        System.out.println("FINANCIAMENTO PUC-PR 2023");
        while (true) {
            System.out.println("╔═════════════════════════════╗");
            System.out.println("║     Escolha o tipo de       ║");
            System.out.println("║      financiamento:         ║");
            System.out.println("║                             ║");
            System.out.println("║   [1] CASA                  ║");
            System.out.println("║   [2] APARTAMENTO           ║");
            System.out.println("║   [3] TERRENO               ║");
            System.out.println("║   [4] ENCERRAR PROGRAMA     ║");
            System.out.println("║   [5] LER DADOS             ║");
            System.out.println("╚═════════════════════════════╝");

            int escolha = user.pedirEscolha();

            if (escolha == 4) {
                System.out.println("Deseja mesmo encerrar o programa? (S para sim, N para não)");
                char sair = sc.next().charAt(0);

                if (sair == 'S' || sair == 's') {
                    System.out.println("\nENCERRANDO ...");
                    break;
                } else if (sair == 'N' || sair == 'n') {
                    continue;
                } else {
                    System.out.println("Digite um valor válido (S para sim, N para não)!");
                }
            }

            switch (escolha) {
                case 1:
                    double valorImovelCasa = user.pedirValorImovel();
                    int prazoFinanciamentoCasa = user.pedirPrazoFinanciamento();
                    double taxaJurosCasa = user.pedirTaxaJuros();
                    double areaConstruida = user.pedirAreaConstruida();
                    double tamanhoTerrenoCasa = user.pedirTamanhoTerreno();
                    Casa casa = new Casa(valorImovelCasa, prazoFinanciamentoCasa, taxaJurosCasa, areaConstruida, tamanhoTerrenoCasa);
                    list.add(casa);
                    break;
                case 2:
                    double valorImovelApartamento = user.pedirValorImovel();
                    int prazoFinanciamentoApartamento = user.pedirPrazoFinanciamento();
                    double taxaJurosApartamento = user.pedirTaxaJuros();
                    int numVagasGaragem = user.pedirNumVagasGaragem();
                    int numAndar = user.pedirNumAndar();
                    Apartamento apartamento = new Apartamento(valorImovelApartamento, prazoFinanciamentoApartamento, taxaJurosApartamento, numVagasGaragem, numAndar);
                    list.add(apartamento);
                    break;
                case 3:
                    double valorImovelTerreno = user.pedirValorImovel();
                    int prazoFinanciamentoTerreno = user.pedirPrazoFinanciamento();
                    double taxaJurosTerreno = user.pedirTaxaJuros();
                    TipoZonaTerreno tipoZona = user.pedirTipoZona();
                    Terreno terreno = new Terreno(valorImovelTerreno, prazoFinanciamentoTerreno, taxaJurosTerreno, tipoZona);
                    list.add(terreno);
                    break;
                case 5:
                    char escolhaMenuDados;

                    System.out.println("╔═════════════════════════════╗");
                    System.out.println("║        Menu de Leitura      ║");
                    System.out.println("║                             ║");
                    System.out.println("║   [T] Texto                 ║");
                    System.out.println("║   [S] Serializável          ║");
                    System.out.println("║   [V] Voltar                ║");
                    System.out.println("║   [Q] Sair do Programa      ║");
                    System.out.println("╚═════════════════════════════╝");

                    escolhaMenuDados = sc.next().toUpperCase().charAt(0);
                    sc.nextLine(); // Limpa o buffer do scanner

                    switch (escolhaMenuDados) {
                        case 'T':
                            System.out.println("Você escolheu ler Texto");
                            break;
                        case 'S':
                            System.out.println("Você escolheu ler Serializável");
                            carregarDadosSerializados();
                            break;
                        case 'V':
                            System.out.println("Voltando ao menu principal...");
                            break;
                        case 'Q':
                            System.out.println("Encerrando o programa...");
                            return;
                        default:
                            System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                            break;
                    }
                    break;
            }
        }

        user.fecharScanner();

        for (Financiamento financiamento : list) {
            totalImoveis += financiamento.getValorImovel();
            totalFinanciamentos += financiamento.calcularTotalPagamento();
        }

        imprimirFinanciamentos(list);

        System.out.println("==============================================");
        System.out.println("|| Total de todos os imóveis: R$ " + String.format("%.2f", totalImoveis) + " ||");
        System.out.println("|| Total de todos os financiamentos: R$ " + String.format("%.2f", totalFinanciamentos) + " ||");
        System.out.println("==============================================");

        if (totalImoveis != 0 || totalFinanciamentos != 0 || !list.isEmpty()) {
            criarArquivoBinarioSerializado();
            criarArquivoTexto(list);
        }
    }

    private static void imprimirFinanciamentos(List<Financiamento> list) {
        for (Financiamento financiamento : list) {
            System.out.println("----------------------------------------");
            System.out.println("|Financiamento #" + contador);
            System.out.println("|Valor do imóvel: R$ " + String.format("%.2f", financiamento.getValorImovel()));
            System.out.println("|Valor do financiamento: R$ " + String.format("%.2f", financiamento.calcularTotalPagamento()));
            System.out.println("----------------------------------------");

            if (financiamento instanceof Casa) {
                Casa casa = (Casa) financiamento;
                System.out.println(casa);
            } else if (financiamento instanceof Apartamento) {
                Apartamento apartamento = (Apartamento) financiamento;
                System.out.println(apartamento);
            } else if (financiamento instanceof Terreno) {
                Terreno terreno = (Terreno) financiamento;
                System.out.println(terreno);
            }

            System.out.println();

            contador++;
        }
    }

    private static void criarArquivoTexto(List<Financiamento> list) {
        if (list.isEmpty()) {
            System.out.println("Não há dados para salvar. O arquivo de texto não será criado.");
            return;
        }

        try (FileWriter fw = new FileWriter("financiamentos.txt")) {
            for (Financiamento financiamento : list) {
                fw.write("----------------------------------------\n");
                fw.write("|Financiamento #" + contador + "\n");
                fw.write("|Valor do imóvel: R$ " + String.format("%.2f", financiamento.getValorImovel()) + "\n");
                fw.write("|Valor do financiamento: R$ " + String.format("%.2f", financiamento.calcularTotalPagamento()) + "\n");
                fw.write("----------------------------------------\n");

                if (financiamento instanceof Casa) {
                    Casa casa = (Casa) financiamento;
                    fw.write(casa.toString() + "\n");
                } else if (financiamento instanceof Apartamento) {
                    Apartamento apartamento = (Apartamento) financiamento;
                    fw.write(apartamento.toString() + "\n");
                } else if (financiamento instanceof Terreno) {
                    Terreno terreno = (Terreno) financiamento;
                    fw.write(terreno.toString() + "\n");
                }

                fw.write("\n");
            }

            fw.write("==============================================\n");
            fw.write("|| Total de todos os imóveis: R$ " + String.format("%.2f", totalImoveis) + "       ||\n");
            fw.write("|| Total de todos os financiamentos: R$ " + String.format("%.2f", totalFinanciamentos) + "||\n");
            fw.write("==============================================\n");
            System.out.println("Arquivo de texto criado com sucesso.");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao criar o arquivo de texto.");
            e.printStackTrace();
        }
    }

    private static void criarArquivoBinarioSerializado() {
        if (totalImoveis == 0 && totalFinanciamentos == 0) {
            System.out.println("Não há dados para salvar. O arquivo binário serializado não será criado.");
            return;
        }

        try (FileOutputStream fos = new FileOutputStream("financiamentos.ser");
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(totalImoveis);
            oos.writeObject(totalFinanciamentos);

            System.out.println("Arquivo binário serializado criado com sucesso.");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao criar o arquivo binário serializado.");
            e.printStackTrace();
        }
    }

    private static void carregarDadosSerializados(List<Financiamento> list) {
        try (FileInputStream fis = new FileInputStream("financiamentos.ser");
                ObjectInputStream ois = new ObjectInputStream(fis)) {

            totalImoveis = (double) ois.readObject();
            totalFinanciamentos = (double) ois.readObject();

            System.out.println("Dados carregados com sucesso.");
            System.out.println("Total de Imóveis: " + totalImoveis);
            System.out.println("Total de Financiamentos: " + totalFinanciamentos);

            imprimirFinanciamentos(list);

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ocorreu um erro ao carregar os dados serializados.");
            e.printStackTrace();
        }
    }

}

