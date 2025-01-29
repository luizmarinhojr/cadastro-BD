package cadastrobd;

import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaFisicaDAO;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.PessoaJuridicaDAO;


public class CadastroBDTeste {

    public static void main(String[] args) {
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();

//        // a. Instanciar uma pessoa física e persistir no banco de dados
        PessoaFisica pessoaFisica = new PessoaFisica("23133322255", 0, "Marcelo",
                "Rua Fernandes, 8, Malaio", "São João", "PE",
                "88999999998", "emailtest@gmail.com");
        pessoaFisicaDAO.incluir(pessoaFisica);
        
//        // b. Alterar os dados da pessoa física no banco.
        PessoaFisica pessoaFisica2 = new PessoaFisica("23133322255", 47, "Fernanda",
                "Rua Fernandes, 8, Malaio", "São João", "PE",
                "88999999998", "emailtest@gmail.com");
        pessoaFisicaDAO.alterar(pessoaFisica2);

//        // c. Consultar todas as pessoas físicas do banco de dados e listar no console.
        pessoaFisicaDAO.getPessoas().forEach(pessoa -> System.out.println(pessoa.exibir()));

//        // d. Excluir a pessoa física criada anteriormente no banco.
        pessoaFisicaDAO.excluir(pessoaFisica);

//        // e. Instanciar uma pessoa jurídica e persistir no banco de dados.
        PessoaJuridica pessoaJuridica = new PessoaJuridica("44444444444444", 0, "Golden Value",
                "Avenida Lopes, 33, Apto 359", "São Paulo", "SP",
                "11977777765", "contato@goldenvalue.com.br");
        pessoaJuridicaDAO.incluir(pessoaJuridica);

//        // f. Alterar os dados da pessoa jurídica no banco.
        PessoaJuridica pessoaJuridica2 = new PessoaJuridica("44444444444444", 55, "Golden Value",
                "Avenida Lopes, 33, Apto 359", "São Paulo", "SP",
                "11666666666", "contato@goldenvalue.com.br");
        pessoaJuridicaDAO.alterar(pessoaJuridica2);

//        // g. Consultar todas as pessoas jurídicas do banco e listar no console.
        pessoaJuridicaDAO.getPessoas().forEach(pessoa -> System.out.println(pessoa.exibir()));

        // h. Excluir a pessoa jurídica criada anteriormente no banco.
        pessoaJuridicaDAO.excluir(pessoaJuridica2);

    }
}
