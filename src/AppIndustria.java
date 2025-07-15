import dao.RelatorioDAO;
import dao.SetorDAO;
import file.EscritaArquivo;
import menu.FuncionarioMenu;
import menu.ProducaoMenu;
import menu.ProdutoMenu;
import menu.SetorMenu;
import model.Funcionario;
import model.Producao;
import model.Relatorio;
import model.Setor;

import java.util.ArrayList;
import java.util.Scanner;

public class AppIndustria {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true){
            System.out.println("=====MENU PRINCIPAL=====");
            System.out.println("[1] Produção");
            System.out.println("[2] Funcionário");
            System.out.println("[3] Setor");
            System.out.println("[4] Produto");
            System.out.println("[5] Relatório");
            System.out.println("[0] Sair");
            System.out.println("Opção");
            int opcao = sc.nextInt();

            if(opcao == 0){
                System.out.println("Programa finalizado com sucesso.");
                break;
            }
            if (opcao == 1){
                ProducaoMenu.main(args);
            }
            if (opcao == 2){
                FuncionarioMenu.main(args);
            }
            if (opcao == 3){
                SetorMenu.main(args);
            }
            if (opcao == 4){
                ProdutoMenu.main(args);
            }
            if (opcao == 5){
                RelatorioDAO relatorioDAO = new RelatorioDAO();
                Relatorio relatorio = new Relatorio(relatorioDAO);
                System.out.println(relatorio.gerarRelatorioGeral());
                EscritaArquivo escritaArquivo = new EscritaArquivo();
                escritaArquivo.salvarCSV(relatorio.gerarRelatorioGeral());
            }
        }
    }
}
