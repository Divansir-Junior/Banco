package entidades;

public class Apartamento extends Financiamento {

    private int numVagasGaragem;
    private int numAndar;

    public Apartamento(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, int numVagasGaragem, int numAndar) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.numVagasGaragem = numVagasGaragem;
        this.numAndar = numAndar;
    }

    public int getNumVagasGaragem() {
        return numVagasGaragem;
    }

    public void setNumVagasGaragem(int numVagasGaragem) {
        this.numVagasGaragem = numVagasGaragem;
    }

    public int getNumAndar() {
        return numAndar;
    }

    public void setNumAndar(int numAndar) {
        this.numAndar = numAndar;
    }

    @Override
    public double calcularPagamentoMensal() {
        double taxaMensal = getTaxaJurosAnual() / 12 / 100;
        return (getValorImovel() / getPrazoFinanciamento()) * (1 + taxaMensal);
    }

    @Override
    public double calcularTotalPagamento() {
        return calcularPagamentoMensal() * getPrazoFinanciamento();
    }
    
    public String toString() {
        return "|   Tipo de imóvel: [APARTAMENTO]               |\n" +
               "|   Valor do apartamento: (R$" + getValorImovel() + ")             |\n" +
               "|   Financiamento do apartamento: (R$" + getPrazoFinanciamento() + ") |\n" +
               "|   Taxa de juros do apartamento: (R$" + getTaxaJurosAnual() + ")     |\n" +
               "|   Número de garagens: (" + getNumVagasGaragem() + ")                   |\n" +
               "|   Número do andar: (" + getNumAndar() + ")                      |";
    }



    
}
