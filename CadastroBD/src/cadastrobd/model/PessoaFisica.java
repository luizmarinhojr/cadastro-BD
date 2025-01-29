package cadastrobd.model;


public class PessoaFisica extends Pessoa {

    private String cpf;

    public PessoaFisica(String cpf, int id, String nome, String logradouro, 
            String cidade, String estado, String telefone, String email) {
        super(id, nome, logradouro, cidade, estado, telefone, email);
        this.cpf = cpf;
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
               CPF: %s
               """, 
               getId(), getNome(), getLogradouro(), getCidade(), getEstado(), 
               getTelefone(), getEmail(), getCpf()
        );
    }
    
    public String getCpf() {
        return cpf;
    }
}
