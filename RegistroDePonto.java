import java.time.*;
import java.time.format.DateTimeFormatter;

public class RegistroDePonto {

    private LocalDate data;
    private LocalTime horaEntrada;
    private LocalTime horaSaida;

    public RegistroDePonto(LocalDate data,  LocalTime horaEntrada) {
        this.data = data;
        this.horaEntrada = horaEntrada;
    }

    public RegistroDePonto(LocalDate data, LocalTime horaEntrada, LocalTime horaSaida) {
        this.data = data;
        this.horaEntrada = horaEntrada;
        this.horaSaida = horaSaida;
    }

    public LocalDate getData() {
        return data;
    }

//    public void setData(LocalDate data) {
//        this.data = data;
//    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalTime getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(LocalTime horaSaida) {
        this.horaSaida = horaSaida;
    }

    public String    getHorasTrabalhadas(){
        if(horaEntrada != null && horaSaida != null){
            Duration duracao =  Duration.between(horaEntrada, horaSaida);
            long horas = duracao.toHours();
            long minutos = duracao.toMinutes() % 60;
            long segundos = duracao.toSeconds() % 60;
            return horas + "h " + minutos + "m " + segundos + "s";
        }
        return "0h 0m 0s";
    }
}
