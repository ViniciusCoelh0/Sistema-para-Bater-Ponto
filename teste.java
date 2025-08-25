import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class teste {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Funcionarios> funcionarios = new ArrayList<>();

        //Criando registro
        System.out.println("Nome do funcionario: ");
        String nome = sc.nextLine();

        Funcionarios f = new Funcionarios(nome);

//        //Registro de ponto
        System.out.println("REGISTRANDO ENTRADA...");
        LocalTime horaEntrada = LocalTime.now();
        LocalDate hoje = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        RegistroDePonto registro = new RegistroDePonto(hoje, horaEntrada);

        //Registro de saida
        System.out.println("REGISTRANDO SAÍDA...");
        System.out.println("Precione enter para registrar a saida...");
        sc.nextLine();
        LocalTime horaSaida = LocalTime.now();
        registro.setHoraSaida(horaSaida);

        f.adicionarRegistro(registro);

        //Resultado
        System.out.println("\nRegistro do funcionario " + f.getNome() + ": ");
        for (RegistroDePonto r : f.getRegistros()) {
            System.out.println(
                    "Data: " + r.getData().format(formatter) +
                            "\nHora de entrada: " + r.getHoraEntrada()
                            + "\nHora de saída: " + r.getHoraSaida()
                            + "\nHoras trabalhadas: " + r.getHorasTrabalhadas()
            );
        }


        sc.close();

    }

}
