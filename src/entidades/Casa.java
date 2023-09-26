package entidades;

public class Casa extends Financiamento {

    private double areaConstruida;
    private double tamanhoTerreno;

    public Casa(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, double areaConstruida, double tamanhoTerreno) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.areaConstruida = areaConstruida;
        this.tamanhoTerreno = tamanhoTerreno;
    }

    public double getAreaConstruida() {
        return areaConstruida;
    }

    public void setAreaConstruida(double areaConstruida) {
        this.areaConstruida = areaConstruida;
    }

    public double getTamanhoTerreno() {
        return tamanhoTerreno;
    }

    public void setTamanhoTerreno(double tamanhoTerreno) {
        this.tamanhoTerreno = tamanhoTerreno;
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
    
    
   

    public void aplicarDesconto(double desconto) throws DescontoMaiorDoQueJurosException {
        double jurosMensais = calcularPagamentoMensal() - (getValorImovel() / getPrazoFinanciamento());

        if (desconto > jurosMensais) {
            throw new DescontoMaiorDoQueJurosException("O desconto não pode ser maior do que os juros da mensalidade.");
        }

        
        
        
    }
    
    @Override
    public String toString() {
        return "|   Tipo de imóvel: [CASA]              |\n" +
               "|   Valor da casa: (R$" + getValorImovel() + ")              |\n" +
               "|   Financiamento da casa: (R$" + getPrazoFinanciamento() + ")        |\n" +
               "|   Taxa de juros da casa: (R$" + getTaxaJurosAnual() + ")      |\n" +
               "|   Área da casa: (" + getAreaConstruida() + " m²)              |\n" +
               "|   Tamanho do terreno da casa: (" + getTamanhoTerreno() + " m²)|";
    }





}
