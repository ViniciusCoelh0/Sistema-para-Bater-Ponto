import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class SistemaDePonto {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Funcionarios> funcionarios = new ArrayList<>();

        while (true) {
            System.out.println("\n====== PONTO DE CONTROLE ======");
            System.out.println("1 - Registrar entrada");
            System.out.println("2 - Registrar saída");
            System.out.println("3 - Listar funcionários");
            System.out.println("4 - Sair");
            System.out.print("Escolha uma opção: ");
            int option = -1;

            try {
                option = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("⚠️ Opção inválida! Digite apenas números.");
                sc.nextLine(); // limpa o buffer para não travar
            }


            switch (option) {
                case 1 -> adicionarHoraDeEntrada(sc, funcionarios);
                case 2 -> adicionarHoraDeSaida(sc, funcionarios);
                case 3 -> listaDeFuncionario(funcionarios);
                case 4 -> {
                    System.out.println("Expediente encerrado");
                    System.exit(4);
                    return;
                }
            }
        }
    }

    //Case 1
    private static void adicionarHoraDeEntrada(Scanner sc, List<Funcionarios> funcionarios) {
        sc.nextLine();
        System.out.println("Registrando entrada...");
        System.out.println("Qual o seu nome: ");
        String nome = sc.nextLine();
        Funcionarios f = new Funcionarios(nome);

        //Adicionando a data
        LocalDate data = LocalDate.now();
        LocalTime horaEntrada = LocalTime.now();

        //Formatador
        DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter DTMH = DateTimeFormatter.ofPattern("hh:mm:ss");

        RegistroDePonto registro = new RegistroDePonto(data, horaEntrada);


        //Registrar no sistema
        f.adicionarRegistro(registro);
        funcionarios.add(f);
        System.out.println("\nRegistro do(a) funcionário(a) " + f.getNome() + ": ");
        for (RegistroDePonto r : f.getRegistros()) {
            System.out.println(
                    "Data: " + r.getData().format(DTF) + "\nHora de entrada: " + r.getHoraEntrada().format(DTMH)
            );
        }
    }

    //Case 2
    private static void adicionarHoraDeSaida(Scanner sc, List<Funcionarios> funcionarios) {
        if (funcionarios.isEmpty()) {
            System.out.println("Ainda não foi adicionado nenhum funcionário ao registro de ponto.\n");
            return;
        }

        //Formatador
        DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter DTMH = DateTimeFormatter.ofPattern("hh:mm:ss");

        sc.nextLine();
        System.out.println("Qual funcionário ira encerrar o expediente:");
        String nome = sc.nextLine();

        //Procurar funcionario
        Funcionarios f = null;
        for (Funcionarios func : funcionarios) {
            if (func.getNome().equalsIgnoreCase(nome)) {
                f = func;
                break;
            }
        }

        if (f == null) {
            System.out.println("Funcionário não encontrado");
            return;
        }

        //Pegar o ultimo registro do funcionario
        if (f.getRegistros().isEmpty()) {
            System.out.println("Esse funcionário não possui um registro");
            return;
        }

        RegistroDePonto ultimoRegistro = f.getUltimoRegistro();
        ultimoRegistro.setHoraSaida(LocalTime.now());


        System.out.println("\nRegistro atualizado do(a) funcionario(a) " + f.getNome() + ": ");
        for (RegistroDePonto r : f.getRegistros()) {
            System.out.println(
                    "Data: " + r.getData().format(DTF) +
                            "\nHora de entrada: " + r.getHoraEntrada().format(DTMH)
                            + "\nHora de saída: " + r.getHoraSaida().format(DTMH)
                            + "\nHoras trabalhadas: " + r.getHorasTrabalhadas()
            );
        }
    }


    //Case 3
    private static void listaDeFuncionario(List<Funcionarios> funcionarios) {
        if (funcionarios.isEmpty()) {
            System.out.println("Ainda não foi adicionado nenhum funcionário ao registro de ponto.\n");
        }

        //Formatador
        DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter DTMH = DateTimeFormatter.ofPattern("hh:mm:ss");

        for (int i = 0; i < funcionarios.size(); i++) {
            Funcionarios f = funcionarios.get(i);
            System.out.println("\n" + (i + 1) + " - " + funcionarios.get(i).getNome());

            if (f.getRegistros().isEmpty()) {
                System.out.println("Nenhum registro de ponto ainda.");
            } else {
                RegistroDePonto ultimoRegistroDePonto = f.getUltimoRegistro();

                System.out.println("   Data: " + ultimoRegistroDePonto.getData().format(DTF));
                System.out.println("   Hora de entrada: " + ultimoRegistroDePonto.getHoraEntrada().format(DTMH));

                if (ultimoRegistroDePonto.getHoraSaida() != null) {
                    System.out.println("   Hora de saída: " + ultimoRegistroDePonto.getHoraSaida().format(DTMH));
                    System.out.println("   Horas trabalhadas: " + ultimoRegistroDePonto.getHorasTrabalhadas());
                } else {
                    System.out.println("   Expediente ainda em aberto (sem saída).");
                }
            }
        }
    }

}

