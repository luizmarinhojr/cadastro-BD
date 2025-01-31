package cadastrobd;

import cadastrobd.model.*;

import java.util.Scanner;


public class CadastroBD {

    static final Scanner LEITOR = new Scanner(System.in);

    static final PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
    static final PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();

    public static void main(String[] args) {

        int escolhaMenu = -1;

        String opcoes = """
                        
                     MENU PRINCIPAL
        =======================================
        1 - Incluir Pessoa
        2 - Alterar Pessoa
        3 - Excluir pessoa
        4 - Buscar pelo Id
        5 - Exibir Todos
        0 - Finalizar Programa
        =======================================""";

        do {
            boolean escolhaValida = false;
            System.out.println(opcoes);
            do {
                try {
                    System.out.print("Digite o número da opção -> ");
                    escolhaMenu = Integer.parseInt(LEITOR.nextLine());
                    escolhaValida = true;
                } catch(NumberFormatException ex) {
                    System.out.println("\nEntrada inválida, tente novamente\n");
                }
            } while (!escolhaValida);

            switch (escolhaMenu) {
                case 1 -> incluirPessoa();

                case 2 -> alterarPessoa();

                case 3 -> excluirPessoa();

                case 4 -> buscarPeloId();

                case 5 -> exibirTodos();

                case 0 -> System.out.println("\nFinalizando o programa...");

                default -> System.out.println("\nOpção inválida! Tente novamente.");

            }

        } while (escolhaMenu != 0);
    }

    private static void incluirPessoa() {
        switch(selecionarTipoPessoa().toUpperCase()) {
            case "F" -> incluirPessoaFisica();

            case "J" -> incluirPessoaJuridica();
        }
        digiteParaVoltarAoMenu();
    }

    private static void alterarPessoa() {
        switch(selecionarTipoPessoa().toUpperCase()) {
            case "F" -> alterarPessoaFisica();

            case "J" -> alterarPessoaJuridica();
        }
        digiteParaVoltarAoMenu();
    }

    private static void excluirPessoa() {
        switch(selecionarTipoPessoa().toUpperCase()) {
            case "F" -> excluirPessoaFisica();

            case "J" -> excluirPessoaJuridica();
        }
        digiteParaVoltarAoMenu();
    }

    private static void buscarPeloId() {
        switch(selecionarTipoPessoa().toUpperCase()) {
            case "F" -> buscarPessoaFisicaPeloId();

            case "J" -> buscarPessoaJuridicaPeloId();
        }
        digiteParaVoltarAoMenu();
    }

    private static void exibirTodos() {
        switch(selecionarTipoPessoa().toUpperCase()) {
            case "F" -> {
                System.out.println("\n**** Todas as pessoas físicas ****\n");
                pessoaFisicaDAO.getPessoas().forEach(pessoa -> System.out.println(pessoa.exibir()));
            }

            case "J" -> {
                System.out.println("\n**** Todas as pessoas jurídicas ****\n");
                pessoaJuridicaDAO.getPessoas().forEach(pessoa -> System.out.println(pessoa.exibir()));
            }
        }
        digiteParaVoltarAoMenu();
    }

    // INÍCIO FUNÇÕES AUXILIARES

    private static void digiteParaVoltarAoMenu() {
        int escolhaMenu = -1;
        do {
            try {
                System.out.print("\nDigite 1 para voltar ao Menu Principal -> ");
                escolhaMenu = Integer.parseInt(LEITOR.nextLine());
            } catch(NumberFormatException ex) {
                System.out.println("\nEntrada inválida, tente novamente");
            }
        } while(escolhaMenu != 1);
    }



    private static String selecionarTipoPessoa() {
        String escolhaMenu = "";
        do {
            System.out.println("\nF - Pessoa física   |   J - Pessoa Jurídica");
            System.out.print("Digite a letra da opção desejada -> ");
            escolhaMenu = LEITOR.nextLine();

        } while(!(escolhaMenu.equalsIgnoreCase("F") || escolhaMenu.equalsIgnoreCase("J")));

        return escolhaMenu;
    }

    private static Pessoa digitarPessoa() {
        int id = 0;
        System.out.print("\nDigite o nome -> ");
        String nome = LEITOR.nextLine();
        System.out.print("\nDigite o logradouro -> ");
        String logradouro = LEITOR.nextLine();
        System.out.print("\nDigite a cidade -> ");
        String cidade = LEITOR.nextLine();
        System.out.print("\nDigite o estado -> ");
        String estado = LEITOR.nextLine();
        System.out.print("\nDigite o telefone -> ");
        String telefone = LEITOR.nextLine();
        System.out.print("\nDigite o email -> ");
        String email = LEITOR.nextLine();

        return new Pessoa(id, nome, logradouro, cidade, estado, telefone, email);
    }

    private static Integer digitarIdPessoa(String tipoPessoa) {
        boolean escolhaValida = false;
        int idPessoa = -1;
        do {
            try {
                System.out.print("\nDigite o ID da pessoa " + tipoPessoa + " -> ");
                idPessoa = Integer.parseInt(LEITOR.nextLine());
                escolhaValida = true;
            } catch(NumberFormatException ex) {
                System.out.println("Entrada inválida, tente novamente");
            }
        } while(!escolhaValida);

        return idPessoa;
    }

    private static void incluirPessoaFisica() {
        Pessoa pessoa = digitarPessoa();
        System.out.print("\nCPF (somente números) -> ");
        String cpf = LEITOR.nextLine();
        pessoaFisicaDAO.incluir(new PessoaFisica(pessoa, cpf));
    }

    private static void incluirPessoaJuridica() {
        Pessoa pessoa = digitarPessoa();
        System.out.print("\nCNPJ (Somente números) -> ");
        String cnpj = LEITOR.nextLine();
        pessoaJuridicaDAO.incluir(new PessoaJuridica(pessoa, cnpj));
    }

    private static void alterarPessoaFisica() {
        String escolhaMenu = "";
        PessoaFisica pessoaFisica = buscarPessoaFisicaPeloId();

        if (pessoaFisica != null) {
            do {
                System.out.println("Deseja alterar essa pessoa física? (S) Sim | (N) Não ");
                System.out.print("\nDigite a letra da opção desejada -> ");
                escolhaMenu = LEITOR.nextLine();
            } while(!(escolhaMenu.equalsIgnoreCase("S") || escolhaMenu.equalsIgnoreCase("N")));

            switch (escolhaMenu.toUpperCase()) {
                case "S" -> {
                    System.out.println("\nInsira os novos dados...");
                    Pessoa pessoa = digitarPessoa();
                    System.out.print("\nCPF (somente números) -> ");
                    String cpf = LEITOR.nextLine();
                    pessoa.setId(pessoaFisica.getId());
                    PessoaFisica pessoaFisicaAtualizada = new PessoaFisica(pessoa, cpf);
                    pessoaFisicaDAO.alterar(pessoaFisicaAtualizada);
                }

                case "N" -> System.out.println("\nPessoa física não alterada. Voltando para o menu...");
            }
        }

    }

    private static void alterarPessoaJuridica() {
        String escolhaMenu = "";
        PessoaJuridica pessoaJuridica = buscarPessoaJuridicaPeloId();

        if (pessoaJuridica != null) {
            do {
                System.out.println("Deseja alterar essa pessoa jurídica? (S) Sim | (N) Não ");
                System.out.print("Digite a letra da opção desejada -> ");
                escolhaMenu = LEITOR.nextLine();
            } while(!(escolhaMenu.equalsIgnoreCase("S") || escolhaMenu.equalsIgnoreCase("N")));

            switch (escolhaMenu.toUpperCase()) {
                case "S" -> {
                    System.out.println("\nInsira os novos dados...");
                    Pessoa pessoa = digitarPessoa();
                    System.out.print("\nCNPJ (somente números) -> ");
                    String cnpj = LEITOR.nextLine();
                    pessoa.setId(pessoaJuridica.getId());
                    PessoaJuridica pessoaJuridicaAtualizada = new PessoaJuridica(pessoa, cnpj);
                    pessoaJuridicaDAO.alterar(pessoaJuridicaAtualizada);
                }

                case "N" -> System.out.println("\nPessoa física não alterada. Voltando para o menu...");
            }
        }
    }

    private static void excluirPessoaFisica() {
        String escolhaMenu = "";
        PessoaFisica pessoaFisica = buscarPessoaFisicaPeloId();
        if (pessoaFisica != null) {
            do {
                System.out.println("Deseja excluir " + pessoaFisica.getNome() + " do registro? (S) Sim | (N) Não ");
                System.out.print("Digite a letra da opção desejada -> ");
                escolhaMenu = LEITOR.nextLine();
            } while(!(escolhaMenu.equalsIgnoreCase("S") || escolhaMenu.equalsIgnoreCase("N")));

            switch (escolhaMenu.toUpperCase()) {
                case "S" -> pessoaFisicaDAO.excluir(pessoaFisica);

                case "N" -> System.out.println("\nPessoa física não excluída. Voltando para o menu...");
            }
        } else {
            System.out.println("\nNão existe pessoa física cadastrada com esse ID");
        }
    }

    private static void excluirPessoaJuridica() {
        String escolhaMenu = "";
        PessoaJuridica pessoaJuridica = buscarPessoaJuridicaPeloId();
        if (pessoaJuridica != null) {
            do {
                System.out.println("Deseja excluir " + pessoaJuridica.getNome() + " do registro? (S) Sim | (N) Não ");
                System.out.print("Digite a letra da opção desejada -> ");
                escolhaMenu = LEITOR.nextLine();
            } while(!(escolhaMenu.equalsIgnoreCase("S") || escolhaMenu.equalsIgnoreCase("N")));

            switch (escolhaMenu.toUpperCase()) {
                case "S" -> pessoaJuridicaDAO.excluir(pessoaJuridica);

                case "N" -> System.out.println("\nPessoa jurídica não excluída. Voltando para o menu...");
            }
        } else {
            System.out.println("\nNão existe pessoa jurídica cadastrada com esse ID");
        }
    }

    private static PessoaFisica buscarPessoaFisicaPeloId() {
        Integer idPessoa = digitarIdPessoa("física");
        PessoaFisica pessoaFisica = pessoaFisicaDAO.getPessoa(idPessoa);
        if (pessoaFisica != null) {
            System.out.println("\n**** Pessoa física ****\n" + pessoaFisica.exibir());
            return pessoaFisica;
        }
        System.out.println("\nNão existe pessoa física cadastrada com esse ID");
        return null;
    }

    private static PessoaJuridica buscarPessoaJuridicaPeloId() {
        Integer idPessoa = digitarIdPessoa("jurídica");
        PessoaJuridica pessoaJuridica = pessoaJuridicaDAO.getPessoa(idPessoa);
        if (pessoaJuridica != null) {
            System.out.println("\n**** Pessoa jurídica ****\n" + pessoaJuridica.exibir());
            return pessoaJuridica;
        }
        System.out.println("\nNão existe pessoa física cadastrada com esse ID");
        return null;
    }
}
