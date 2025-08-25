import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Funcionarios {

    private String nome;
    private List<RegistroDePonto> registros;

    private static List<Funcionarios> listaFuncionarios = new ArrayList<>();

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

    public void baterEntrada(){
        LocalDate hoje = LocalDate.now();
        LocalTime agora = LocalTime.now();
        RegistroDePonto registro = new RegistroDePonto(hoje, agora);
        registros.add(registro);
        System.out.println("Entrada registrada para " + nome + " às " + agora);
    }

    public void baterSaida() {
        LocalTime agora = LocalTime.now();
        for (int i = registros.size() - 1; i >= 0; i--) {
            RegistroDePonto r = registros.get(i);
            if (r.getHoraSaida() == null) {
                r.setHoraSaida(agora);
                System.out.println("Saída registrada para " + nome + " às " + agora);
                return;
            }
        }
        System.out.println("Nenhuma entrada encontrada para registrar saída.");
    }


    public static List<Funcionarios> getListaFuncionarios() {
        return listaFuncionarios;
    }

}
