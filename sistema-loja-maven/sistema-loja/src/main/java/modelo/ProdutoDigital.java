package modelo;

import javax.persistence.Entity;

/**
 * Classe ProdutoDigital - Demonstra HERANÇA e POLIMORFISMO
 * Herda de Produto e implementa seus métodos abstratos de forma diferente de ProdutoFisico
 */
@Entity
public class ProdutoDigital extends Produto {
    // Atributos específicos de produto digital
    private double tamanhoMB;
    private String linkDownload;
    
    // Construtor padrão exigido pelo Hibernate
    public ProdutoDigital() {}
    
    /**
     * Construtor que chama o construtor da superclasse
     */
    public ProdutoDigital(String nome, double preco, int estoque, double tamanhoMB, String linkDownload) {
        super(nome, preco, estoque); // Chama o construtor da classe pai
        this.tamanhoMB = tamanhoMB;
        this.linkDownload = linkDownload;
    }
    
    // Getters e Setters
    public double getTamanhoMB() {
        return tamanhoMB;
    }
    
    public void setTamanhoMB(double tamanhoMB) {
        this.tamanhoMB = tamanhoMB;
    }
    
    public String getLinkDownload() {
        return linkDownload;
    }
    
    public void setLinkDownload(String linkDownload) {
        this.linkDownload = linkDownload;
    }
    
    /**
     * POLIMORFISMO: Implementação específica do método abstrato
     * Produto digital não tem custo adicional (sem frete)
     */
    @Override
    public double calcularValorFinal() {
        return getPreco(); // Valor final é apenas o preço base
    }
    
    /**
     * POLIMORFISMO: Implementação do método abstrato getTipo
     */
    @Override
    public String getTipo() {
        return "DIGITAL";
    }
    
    /**
     * POLIMORFISMO: Sobrescreve o método da classe pai para adicionar informações específicas
     */
    @Override
    public String exibirDetalhes() {
        return super.exibirDetalhes() + 
               String.format(" | Tipo: %s | Tamanho: %.2f MB | Link: %s | Valor Final: R$ %.2f",
                           getTipo(), tamanhoMB, linkDownload, calcularValorFinal());
    }
}
