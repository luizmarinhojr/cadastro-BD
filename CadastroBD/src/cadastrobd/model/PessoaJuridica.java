package cadastrobd.model;


public class PessoaJuridica extends Pessoa {

    private String cnpj;

    public PessoaJuridica(String cnpj, int id, String nome, String logradouro, 
            String cidade, String estado, String telefone, String email) {
        super(id, nome, logradouro, cidade, estado, telefone, email);
        this.cnpj = cnpj;
    }

    public PessoaJuridica(Pessoa pessoa, String cnpj) {
        super(pessoa.getId(), pessoa.getNome(), pessoa.getLogradouro(), pessoa.getCidade(),
                pessoa.getEstado(), pessoa.getTelefone(), pessoa.getEmail());
        this.cnpj = cnpj;
    }

    @Override
    public String exibir() {
        return String.format(
               """
               ID: %d
               Nome: %s
               Logradouro: %s
               Cidade: %s
               Estado: %s
               Telefone: %s
               Email: %s
               CNPJ: %s
               """, 
               getId(), getNome(), getLogradouro(), getCidade(), getEstado(), 
               getTelefone(), getEmail(), getCnpj()
        );
    }

    public String getCnpj() {
        return cnpj;
    }
}
