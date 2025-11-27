package modelo;

import javax.persistence.*;

/**
 * Classe Cliente - Demonstra o conceito de ENCAPSULAMENTO
 * Todos os atributos são privados e acessados apenas através de métodos públicos
 */
@Entity
@Table(name = "clientes")
public class Cliente {
    // ENCAPSULAMENTO: Atributos privados protegem os dados da classe
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    @Column(unique = true)
    private String cpf;
    private String email;
    private String telefone;
    
    // Construtor padrão exigido pelo Hibernate
    public Cliente() {}
    
    /**
     * Construtor da classe Cliente
     */
    public Cliente(String nome, String cpf, String email, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }
    
    // ENCAPSULAMENTO: Métodos públicos (getters e setters) controlam o acesso aos atributos
    
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
        // Validação pode ser adicionada aqui
        if (nome != null && !nome.trim().isEmpty()) {
            this.nome = nome;
        }
    }
    
    public String getCpf() {
        return cpf;
    }
    
    public void setCpf(String cpf) {
        // Validação de CPF pode ser adicionada aqui
        this.cpf = cpf;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        // Validação de email pode ser adicionada aqui
        if (email != null && email.contains("@")) {
            this.email = email;
        }
    }
    
    public String getTelefone() {
        return telefone;
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    /**
     * Método toString para exibir informações do cliente
     */
    @Override
    public String toString() {
        return String.format("ID: %d | Nome: %s | CPF: %s | Email: %s | Telefone: %s",
                           id, nome, cpf, email, telefone);
    }
}
