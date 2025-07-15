package menu;

import dao.ProducaoDAO;
import model.Funcionario;
import model.Producao;
import model.Produto;

import java.util.Scanner;
import java.util.ArrayList;

public class ProducaoMenu {
    private static ProducaoDAO dao = new ProducaoDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n--- MENU PRODUÇÃO ---");
            System.out.println("[1] Listar produções");
            System.out.println("[2] Buscar produção por ID");
            System.out.println("[3] Cadastrar produção");
            System.out.println("[4] Atualizar produção");
            System.out.println("[5] Remover produção");
            System.out.println("[0] Sair");
            System.out.println("Escolha uma opção: ");
            opcao = scanner.nextInt();

            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    listarProducoes();
                    break;
                case 2:
                    buscarPorId();
                    break;
                case 3:
                    cadastrarSetor();
                    break;
                case 4:
                    //atualizarSetor();
                    break;
                case 5:
                    //removerSetor();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void listarProducoes() {
        ArrayList<Producao> producaos = dao.listar();
        if (producaos != null && !producaos.isEmpty()) {
            for (Producao producao : producaos) {
                System.out.println(producao);
            }
        } else {
            System.out.println("Nenhuma produção encontrada.");
        }
    }

    private static void buscarPorId() {
        System.out.print("Digite o ID da produção: ");
        int id = scanner.nextInt();
        Producao producao = dao.buscarPorId(id);
        if (producao != null) {
            System.out.println(producao);
        } else {
            System.out.println("model.Producao não encontrado.");
        }
    }

    private static void cadastrarSetor() {
        System.out.print("id do produto: ");
        Integer idProduto = scanner.nextInt();

        System.out.print("id do funcionário: ");
        Integer idFuncionario = scanner.nextInt();

        System.out.print("Data de produção: ");
        String dataProducao = scanner.nextLine();

        System.out.print("Quantidade: ");
        Integer quantidade = scanner.nextInt();

        Producao producao = new Producao();
        if (dao.cadastrar(producao)) {
            System.out.println("model.Producao cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar produção.");
        }
    }

    private static void atualizarProducao() {
        System.out.print("ID da producao a atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer
        Producao producao = dao.buscarPorId(id);
        if (producao == null) {
            System.out.println("model.Producao não encontrado.");
            return;
        }
        System.out.print("id do produto: ");
        Integer idProduto = scanner.nextInt();

        System.out.print("id do funcionário: ");
        Integer idFuncionario = scanner.nextInt();

        System.out.print("Data de produção: ");
        String dataProducao = scanner.nextLine();

        System.out.print("Quantidade: ");
        Integer quantidade = scanner.nextInt();

        producao.setProduto(new Produto(idProduto, null, null));
        producao.setFuncionario(new Funcionario(idFuncionario, null, null, null));
        producao.setDataProducao(dataProducao);
        producao.setQuantidade(quantidade);
        if (dao.atualizar(producao)) {
            System.out.println("model.Producao atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar a produçãor.");
        }
    }

    private static void removerProducao() {
        System.out.print("ID da producao a remover: ");
        int id = scanner.nextInt();
        if (dao.remover(id)) {
            System.out.println("model.Producao removido com sucesso!");
        } else {
            System.out.println("Erro ao remover a produção.");
        }
    }
}
