package cadastrobd.model;


public class Pessoa {
    private int id;
    private String nome;
    private String logradouro;
    private String cidade;
    private String estado;
    private String telefone;
    private String email;

    public Pessoa() {}

    public Pessoa(int id, String nome, String logradouro, String cidade, 
            String estado, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.estado = estado;
        this.telefone = telefone;
        this.email = email;
    }
    
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
               """, 
               getId(), getNome(), getLogradouro(), getCidade(), getEstado(), 
               getTelefone(), getEmail()
        );
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }
}
