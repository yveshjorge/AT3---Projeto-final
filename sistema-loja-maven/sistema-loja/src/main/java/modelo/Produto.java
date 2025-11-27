package modelo;

import javax.persistence.*;

/**
 * Classe abstrata Produto - Demonstra o conceito de ABSTRAÇÃO
 * Uma classe abstrata não pode ser instanciada diretamente e serve como base para outras classes
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Produto {
    // ENCAPSULAMENTO: Atributos privados
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private String nome;
    private double preco;
    private int estoque;
    
    /**
     * Construtor da classe Produto
     */
    public Produto(String nome, double preco, int estoque) {
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }
    
    // ENCAPSULAMENTO: Getters e Setters para controlar acesso aos atributos
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public double getPreco() {
        return preco;
    }
    
    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    public int getEstoque() {
        return estoque;
    }
    
    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }
    
    /**
     * Método abstrato - ABSTRAÇÃO
     * Cada tipo de produto calculará o valor final de forma diferente (POLIMORFISMO)
     */
    public abstract double calcularValorFinal();
    
    /**
     * Método abstrato para retornar o tipo do produto
     */
    public abstract String getTipo();
    
    // Construtor padrão exigido pelo Hibernate
    public Produto() {}
    
    /**
     * Método concreto que pode ser sobrescrito pelas classes filhas
     */
    public String exibirDetalhes() {
        return String.format("ID: %d | Nome: %s | Preço: R$ %.2f | Estoque: %d", 
                           id, nome, preco, estoque);
    }
}
