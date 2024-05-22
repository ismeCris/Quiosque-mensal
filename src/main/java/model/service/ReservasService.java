package model.service;

import java.time.LocalDate;

public class ReservasService {

//istanciar repositorio

    //-----> calculo aluguel
    public double calcularAluguel(int diasAlugados, double valorDiario) {
        return diasAlugados * valorDiario;
    }


}
