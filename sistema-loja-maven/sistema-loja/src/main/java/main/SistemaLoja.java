package main;

import dao.ProdutoDAO;
import dao.ClienteDAO;
import dao.HibernateUtil;
import modelo.*;
import java.util.List;
import java.util.Scanner;

/**
 * Classe principal do Sistema de Loja
 * Implementa o menu de terminal para interação com o usuário
 */
public class SistemaLoja {
    private ProdutoDAO produtoDAO;
    private ClienteDAO clienteDAO;
    private Scanner scanner;
    
    /**
     * Construtor - Inicializa o banco de dados e o scanner
     */
    public SistemaLoja() {
        this.produtoDAO = new ProdutoDAO();
        this.clienteDAO = new ClienteDAO();
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Método principal - Ponto de entrada do programa
     */
    public static void main(String[] args) {
        SistemaLoja sistema = new SistemaLoja();
        sistema.exibirMenu();
    }
    
    /**
     * Exibe o menu principal e processa as opções do usuário
     */
    public void exibirMenu() {
        int opcao = 0;
        
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║     SISTEMA DE LOJA - POO EM JAVA             ║");
        System.out.println("║     Demonstração dos 4 Pilares da POO         ║");
        System.out.println("╚════════════════════════════════════════════════╝");
        
        do {
            System.out.println("\n┌─────────────── MENU PRINCIPAL ───────────────┐");
            System.out.println("│ 1 - Cadastrar Produto Físico                 │");
            System.out.println("│ 2 - Cadastrar Produto Digital                │");
            System.out.println("│ 3 - Consultar Produtos                       │");
            System.out.println("│ 4 - Cadastrar Cliente                        │");
            System.out.println("│ 5 - Consultar Clientes                       │");
            System.out.println("│ 6 - Demonstrar Polimorfismo                  │");
            System.out.println("│ 0 - Sair                                     │");
            System.out.println("└──────────────────────────────────────────────┘");
            System.out.print("Escolha uma opção: ");
            
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                
                switch (opcao) {
                    case 1:
                        cadastrarProdutoFisico();
                        break;
                    case 2:
                        cadastrarProdutoDigital();
                        break;
                    case 3:
                        consultarProdutos();
                        break;
                    case 4:
                        cadastrarCliente();
                        break;
                    case 5:
                        consultarClientes();
                        break;
                    case 6:
                        demonstrarPolimorfismo();
                        break;
                    case 0:
                        System.out.println("\n✓ Encerrando sistema...");
                        HibernateUtil.shutdown();
                        break;
                    default:
                        System.out.println("\n✗ Opção inválida! Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n✗ Por favor, digite um número válido!");
                opcao = -1;
            }
            
        } while (opcao != 0);
        
        scanner.close();
    }
    
    /**
     * Cadastra um produto físico no sistema
     * Demonstra HERANÇA - ProdutoFisico herda de Produto
     */
    private void cadastrarProdutoFisico() {
        System.out.println("\n═══ CADASTRAR PRODUTO FÍSICO ═══");
        
        try {
            System.out.print("Nome do produto: ");
            String nome = scanner.nextLine();
            
            System.out.print("Preço (R$): ");
            double preco = Double.parseDouble(scanner.nextLine());
            
            System.out.print("Quantidade em estoque: ");
            int estoque = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Peso (kg): ");
            double peso = Double.parseDouble(scanner.nextLine());
            
            // HERANÇA e POLIMORFISMO em ação
            Produto produto = new ProdutoFisico(nome, preco, estoque, peso);
            produtoDAO.salvar(produto);
            
        } catch (NumberFormatException e) {
            System.out.println("✗ Erro: Valor numérico inválido!");
        }
    }
    
    /**
     * Cadastra um produto digital no sistema
     * Demonstra HERANÇA - ProdutoDigital herda de Produto
     */
    private void cadastrarProdutoDigital() {
        System.out.println("\n═══ CADASTRAR PRODUTO DIGITAL ═══");
        
        try {
            System.out.print("Nome do produto: ");
            String nome = scanner.nextLine();
            
            System.out.print("Preço (R$): ");
            double preco = Double.parseDouble(scanner.nextLine());
            
            System.out.print("Quantidade em estoque: ");
            int estoque = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Tamanho (MB): ");
            double tamanhoMB = Double.parseDouble(scanner.nextLine());
            
            System.out.print("Link de download: ");
            String linkDownload = scanner.nextLine();
            
            // HERANÇA e POLIMORFISMO em ação
            Produto produto = new ProdutoDigital(nome, preco, estoque, tamanhoMB, linkDownload);
            produtoDAO.salvar(produto);
            
        } catch (NumberFormatException e) {
            System.out.println("✗ Erro: Valor numérico inválido!");
        }
    }
    
    /**
     * Consulta e exibe todos os produtos cadastrados
     * Demonstra POLIMORFISMO - lista contém diferentes tipos de produtos
     */
    private void consultarProdutos() {
        System.out.println("\n═══ PRODUTOS CADASTRADOS ═══");
        
        List<Produto> produtos = produtoDAO.listarTodos();
        
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            System.out.println("\nTotal de produtos: " + produtos.size());
            System.out.println("─────────────────────────────────────────────────────────────────────────────");
            
            // POLIMORFISMO - cada produto exibe seus detalhes de forma específica
            for (Produto produto : produtos) {
                System.out.println(produto.exibirDetalhes());
            }
            
            System.out.println("─────────────────────────────────────────────────────────────────────────────");
        }
    }
    
    /**
     * Cadastra um cliente no sistema
     * Demonstra ENCAPSULAMENTO - Cliente protege seus dados
     */
    private void cadastrarCliente() {
        System.out.println("\n═══ CADASTRAR CLIENTE ═══");
        
        System.out.print("Nome completo: ");
        String nome = scanner.nextLine();
        
        System.out.print("CPF (apenas números): ");
        String cpf = scanner.nextLine();
        
        System.out.print("E-mail: ");
        String email = scanner.nextLine();
        
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        
        // ENCAPSULAMENTO - dados são passados através do construtor
        Cliente cliente = new Cliente(nome, cpf, email, telefone);
        clienteDAO.salvar(cliente);
    }
    
    /**
     * Consulta e exibe todos os clientes cadastrados
     */
    private void consultarClientes() {
        System.out.println("\n═══ CLIENTES CADASTRADOS ═══");
        
        List<Cliente> clientes = clienteDAO.listarTodos();
        
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            System.out.println("\nTotal de clientes: " + clientes.size());
            System.out.println("─────────────────────────────────────────────────────────────────────────────");
            
            // ENCAPSULAMENTO - dados são acessados através de métodos públicos
            for (Cliente cliente : clientes) {
                System.out.println(cliente.toString());
            }
            
            System.out.println("─────────────────────────────────────────────────────────────────────────────");
        }
    }
    
    /**
     * Demonstra o conceito de POLIMORFISMO de forma clara
     * Mostra como objetos de diferentes classes podem ser tratados de forma uniforme
     */
    private void demonstrarPolimorfismo() {
        System.out.println("\n═══════════════════════════════════════════════════════════════════");
        System.out.println("        DEMONSTRAÇÃO DE POLIMORFISMO");
        System.out.println("═══════════════════════════════════════════════════════════════════");
        
        // POLIMORFISMO - variável do tipo Produto pode referenciar objetos de tipos diferentes
        Produto produto1 = new ProdutoFisico("Notebook Dell", 3500.00, 10, 2.5);
        Produto produto2 = new ProdutoDigital("Curso de Java", 199.90, 999, 1500.0, "http://download.com/java");
        
        System.out.println("\n1. ABSTRAÇÃO:");
        System.out.println("   - Produto é uma classe ABSTRATA que não pode ser instanciada");
        System.out.println("   - Define o 'contrato' que todas as subclasses devem seguir");
        
        System.out.println("\n2. HERANÇA:");
        System.out.println("   - ProdutoFisico e ProdutoDigital HERDAM de Produto");
        System.out.println("   - Reutilizam código comum (nome, preço, estoque)");
        
        System.out.println("\n3. POLIMORFISMO:");
        System.out.println("   - Mesmo método, comportamentos diferentes!");
        System.out.println("\n   Produto 1 (Físico):");
        System.out.println("   " + produto1.exibirDetalhes());
        System.out.println("   Cálculo: Preço base + Frete = R$ " + 
                         String.format("%.2f", produto1.calcularValorFinal()));
        
        System.out.println("\n   Produto 2 (Digital):");
        System.out.println("   " + produto2.exibirDetalhes());
        System.out.println("   Cálculo: Apenas preço base = R$ " + 
                         String.format("%.2f", produto2.calcularValorFinal()));
        
        System.out.println("\n4. ENCAPSULAMENTO:");
        System.out.println("   - Todos os atributos são PRIVADOS");
        System.out.println("   - Acesso controlado através de getters/setters");
        System.out.println("   - Exemplo: produto1.getPreco() = R$ " + 
                         String.format("%.2f", produto1.getPreco()));
        
        System.out.println("\n═══════════════════════════════════════════════════════════════════");
        System.out.println("Os 4 PILARES DA POO foram demonstrados com sucesso!");
        System.out.println("═══════════════════════════════════════════════════════════════════");
    }
}
