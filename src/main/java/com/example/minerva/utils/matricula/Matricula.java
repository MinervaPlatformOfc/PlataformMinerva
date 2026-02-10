package com.example.minerva.utils.matricula;

import java.time.LocalDate;
import java.util.Random;

public class Matricula {
    public static String gerarMatricula(String role) {
        Random rnd = new Random();
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // Identificação de role
        String part1;
        if (role.equals("STUDENT")) part1 = "S";
        else part1 = "T";

        // Ano e dia do ano
        LocalDate hoje = LocalDate.now();
        String ano = String.valueOf(hoje.getYear()).substring(2); // últimos 2 dígitos do ano
        String diaDoAno = String.format("%03d", hoje.getDayOfYear()); // 3 dígitos do dia do ano
        String part2 = ano + diaDoAno;

        // 2 últimos caracteres aleatórios
        String letra = "" + letras.charAt(rnd.nextInt(letras.length()));
        String numero = "" + rnd.nextInt(10);
        String part3 = letra + numero;

        // Combina tudo
        return part1+part2+part3;
    }
}
