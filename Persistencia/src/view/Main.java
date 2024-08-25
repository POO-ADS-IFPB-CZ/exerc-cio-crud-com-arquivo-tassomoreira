package view;

import dao.PessoaDao;
import model.Pessoa;

import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        PessoaDao dao = new PessoaDao();

        int choice;
        do {
            System.out.println();
            System.out.println("------------------- PESSOAS -------------------");
            System.out.println("|\t0 - Sair");
            System.out.println("|\t1 - Salvar uma pessoa");
            System.out.println("|\t2- Listar todas as pessoas");
            System.out.println("|\t3 - Deletar uma pessoa");
            System.out.println("------------------------------------------------");
            System.out.print("Digite uma opção: ");
            choice = input.nextInt();
            switch (choice) {
                case 0: exit(0); break;
                case 1:
                    System.out.println();
                    System.out.println("-------------------- SALVAR --------------------");
                    System.out.print("|\tInsira o nome da pessoa: ");
                    input.nextLine();
                    String name = input.nextLine();
                    System.out.print("|\tInsira o e-mail da pessoa: ");
                    String email = input.nextLine();

                    Pessoa pessoa = new Pessoa(name, email);

                    if(dao.salvar(pessoa)) {
                        System.out.println("------------------------------------------------");
                        System.out.println("Pessoa salva com sucesso");
                    };
                    break;
                case 2:
                    if(dao.getPessoas().isEmpty()) {
                        System.out.println("A lista está vazia");
                    } else dao.listar(); break;
                case 3:
                    System.out.println();
                    System.out.println("-------------------- DELETAR --------------------");
                    System.out.print("|\tInsira o email da pessoa: ");
                    input.nextLine();
                    String personEmail = input.nextLine();


                    if(dao.deletar(personEmail)) {
                        System.out.println("--------------------------------------------------");
                        System.out.println("Pessoa deletada com sucesso");
                    } else {
                        System.out.println("--------------------------------------------------");
                        System.out.println("Insira um email válido");
                    }
                    break;
                default:
                    System.out.println();
                    System.out.println("Insira uma opção válida!");
            }
        } while(choice != '0');
    }
}
