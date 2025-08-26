import java.util.ArrayList;
import java.util.List;

public class Funcionarios {

    private String nome;
    private List<RegistroDePonto> registros;

    private static List<Funcionarios> listaFuncionarios = new ArrayList<>();

    public Funcionarios() {
    }

    public Funcionarios(String nome) {
        this.nome = nome;
        this.registros = new ArrayList<>();
        listaFuncionarios.add(this);
    }

    public String getNome() {
        return nome;
    }

    public List<RegistroDePonto> getRegistros() {
        return registros;
    }

    public void adicionarRegistro(RegistroDePonto registro) {
        this.registros.add(registro);
    }

    public RegistroDePonto getUltimoRegistro() {
        if (registros.isEmpty()) return null;
        return registros.get(registros.size() - 1);
    }

    public static List<Funcionarios> getListaFuncionarios() {
        return listaFuncionarios;
    }

}
