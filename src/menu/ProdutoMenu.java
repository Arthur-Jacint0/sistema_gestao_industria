package menu;

import dao.ProducaoDAO;
import dao.ProdutoDAO;
import model.Produto;

import java.util.ArrayList;
import java.util.Scanner;

public class ProdutoMenu {
    private static ProdutoDAO dao = new ProdutoDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n--- MENU PRODUTO ---");
            System.out.println("[1] Listar produtos");
            System.out.println("[2] Buscar produtos por ID");
            System.out.println("[3] Cadastrar produto");
            System.out.println("[4] Atualizar produto");
            System.out.println("[5] Remover produto");
            System.out.println("[0] Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    listarProdutos();
                    break;
                case 2:
                    buscarPorId();
                    break;
                case 3:
                    cadastrarProduto();
                    break;
                case 4:
                    atualizarProduto();
                    break;
                case 5:
                    atualizarProduto();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void listarProdutos() {
        ArrayList<Produto> produtos = dao.listar();
        if (produtos != null && !produtos.isEmpty()) {
            for (Produto setor : produtos) {
                System.out.println(setor);
            }
        } else {
            System.out.println("Nenhum produto encontrado.");
        }
    }

    private static void buscarPorId() {
        System.out.print("Digite o ID do produto: ");
        int id = scanner.nextInt();
        Produto produto = dao.buscarPorId(id);
        if (produto != null) {
            System.out.println(produto);
        } else {
            System.out.println("model.Produto não encontrado.");
        }
    }

    private static void cadastrarProduto() {
        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        Produto produto = new Produto(null, nome, descricao);
        if (dao.cadastrar(produto)) {
            System.out.println("model.Produto cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar produto.");
        }
    }

    private static void atualizarProduto() {
        System.out.print("ID do produto a atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer
        Produto produto = dao.buscarPorId(id);
        if (produto == null) {
            System.out.println("model.Produto não encontrado.");
            return;
        }
        System.out.print("Novo nome do produto: ");
        String nome = scanner.nextLine();
        System.out.print("Nova descrição: ");
        String descricao = scanner.nextLine();
        produto.setNomeProduto(nome);
        produto.setDescricao(descricao);
        if (dao.atualizar(produto)) {
            System.out.println("model.Produto atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar produto.");
        }
    }

    private static void removerProduto() {
        System.out.print("ID do produto a remover: ");
        int id = scanner.nextInt();
        if (dao.remover(id)) {
            System.out.println("model.Produto removido com sucesso!");
        } else {
            System.out.println("Erro ao remover produto.");
        }
    }
}
