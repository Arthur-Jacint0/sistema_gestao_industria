package menu;

import dao.FuncionarioDAO;
import dao.ProducaoDAO;
import model.Funcionario;
import model.Producao;
import model.Produto;
import model.Setor;

import java.util.ArrayList;
import java.util.Scanner;

public class FuncionarioMenu {
    private static FuncionarioDAO dao = new FuncionarioDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n--- MENU FUNCIONÁRIO ---");
            System.out.println("[1] Listar funcionários");
            System.out.println("[2] Buscar funcionário por ID");
            System.out.println("[3] Cadastrar funcionário");
            System.out.println("[4] Atualizar funcionário");
            System.out.println("[5] Remover funcionário");
            System.out.println("[0] Sair");
            System.out.println("Escolha uma opção: ");
            opcao = scanner.nextInt();

            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    listarFuncionarios();
                    break;
                case 2:
                    buscarPorId();
                    break;
                case 3:
                    cadastrarFuncionario();
                    break;
                case 4:
                    atualizarFuncionario();
                    break;
                case 5:
                    atualizarFuncionario();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void listarFuncionarios() {
        ArrayList<Funcionario> funcionarios = dao.listar();
        if (funcionarios != null && !funcionarios.isEmpty()) {
            for (Funcionario funcionario : funcionarios) {
                System.out.println(funcionario);
            }
        } else {
            System.out.println("Nenhum funcionário encontrado.");
        }
    }

    private static void buscarPorId() {
        System.out.print("Digite o ID do funcionário: ");
        int id = scanner.nextInt();
        Funcionario funcionario = dao.buscarPorId(id);
        if (funcionario != null) {
            System.out.println(funcionario);
        } else {
            System.out.println("model.Funcionario não encontrado.");
        }
    }

    private static void cadastrarFuncionario() {
        System.out.print("nome do funcionario: ");
        String nome = scanner.next();

        System.out.print("sobrenome do funcionario: ");
        String sobrenome = scanner.next();

        System.out.println("Id do setor: ");
        Integer idSetor = scanner.nextInt();

        Funcionario funcionario = new Funcionario(null, nome, sobrenome, null);
        if (dao.cadastrar(funcionario)) {
            System.out.println("model.Funcionario cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar produção.");
        }
    }

    private static void atualizarFuncionario() {
        System.out.print("ID do funcionário a atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer
        Funcionario funcionario = dao.buscarPorId(id);
        if (funcionario == null) {
            System.out.println("model.Funcionario não encontrado.");
            return;
        }
        System.out.print("id do Funcionario: ");
        Integer idFuncionario = scanner.nextInt();

        System.out.print("Nome do funcionário: ");
        String nome = scanner.next();

        System.out.print("Sobrenome do funcionário: ");
        String sobrenome = scanner.nextLine();

        System.out.print("Id do setor: ");
        Integer idSetor = scanner.nextInt();
        Setor setor = new Setor();
        setor.setIdSetor(idSetor);

        Funcionario funcionari = new Funcionario(idFuncionario, nome, sobrenome, setor);

        funcionari.setFuncionario(funcionari);
        if (dao.atualizar(funcionari)) {
            System.out.println("model.Funcionario atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar o funcionário.");
        }
    }

    private static void removerFuncionario() {
        System.out.print("ID do funcionario a remover: ");
        int id = scanner.nextInt();
        if (dao.remover(id)) {
            System.out.println("model.Funcionario removido com sucesso!");
        } else {
            System.out.println("Erro ao remover o funcionário.");
        }
    }
}
