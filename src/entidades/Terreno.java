package entidades;

import entidades.enums.TipoZonaTerreno;

public class Terreno extends Financiamento {

    private TipoZonaTerreno tipoZona;

    public Terreno(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, TipoZonaTerreno tipoZona) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.tipoZona = tipoZona;
    }

    public TipoZonaTerreno getTipoZona() {
        return tipoZona;
    }

    public void setTipoZona(TipoZonaTerreno tipoZona) {
        this.tipoZona = tipoZona;
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
    
    @Override
    public String toString() {
        return "|   Tipo de imóvel: [TERRENO]                  |\n" +
               "|   Valor do terreno: (R$" + getValorImovel() + ")                 |\n" +
               "|   Financiamento do terreno: (R$" + getPrazoFinanciamento() + ")   |\n" +
               "|   Taxa de juros do terreno: (R$" + getTaxaJurosAnual() + ")       |\n" +
               "|   Tipo de zona: (" + getTipoZona() + ")                              |";
    }

    
    
}
