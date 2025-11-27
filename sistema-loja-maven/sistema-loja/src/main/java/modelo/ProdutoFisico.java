package modelo;

import javax.persistence.Entity;

/**
 * Classe ProdutoFisico - Demonstra HERANÇA e POLIMORFISMO
 * Herda de Produto e implementa seus métodos abstratos de forma específica
 */
@Entity
public class ProdutoFisico extends Produto {
    // Atributos específicos de produto físico
    private double peso;
    private double taxaFrete;
    
    // Construtor padrão exigido pelo Hibernate
    public ProdutoFisico() {}
    
    /**
     * Construtor que chama o construtor da superclasse
     */
    public ProdutoFisico(String nome, double preco, int estoque, double peso) {
        super(nome, preco, estoque); // Chama o construtor da classe pai
        this.peso = peso;
        this.taxaFrete = calcularTaxaFrete(peso);
    }
    
    /**
     * Calcula a taxa de frete baseada no peso
     */
    private double calcularTaxaFrete(double peso) {
        if (peso <= 1.0) {
            return 10.0;
        } else if (peso <= 5.0) {
            return 20.0;
        } else {
            return 30.0 + (peso - 5.0) * 5.0;
        }
    }
    
    // Getters e Setters
    public double getPeso() {
        return peso;
    }
    
    public void setPeso(double peso) {
        this.peso = peso;
        this.taxaFrete = calcularTaxaFrete(peso);
    }
    
    public double getTaxaFrete() {
        return taxaFrete;
    }
    
    /**
     * POLIMORFISMO: Implementação específica do método abstrato
     * Produto físico tem custo de frete adicional
     */
    @Override
    public double calcularValorFinal() {
        return getPreco() + taxaFrete;
    }
    
    /**
     * POLIMORFISMO: Implementação do método abstrato getTipo
     */
    @Override
    public String getTipo() {
        return "FISICO";
    }
    
    /**
     * POLIMORFISMO: Sobrescreve o método da classe pai para adicionar informações específicas
     */
    @Override
    public String exibirDetalhes() {
        return super.exibirDetalhes() + 
               String.format(" | Tipo: %s | Peso: %.2f kg | Frete: R$ %.2f | Valor Final: R$ %.2f",
                           getTipo(), peso, taxaFrete, calcularValorFinal());
    }
}
