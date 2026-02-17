package com.example.minerva.utils.matricula;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Random;

public class Matricula {
    private static final Duration TTL = Duration.ofDays(4); //Duração de 4 dias
    private static final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing()
            .load();
    private static final JedisPool pool;
    static {
        JedisPoolConfig config = new JedisPoolConfig();
        pool = new JedisPool(
                config,
                dotenv.get("REDIS_HOST", System.getenv("REDIS_HOST")),
                Integer.parseInt(dotenv.get("REDIS_PORT", System.getenv("REDIS_PORT"))),
                4000,
                dotenv.get("REDIS_USER", System.getenv("REDIS_USER")),
                dotenv.get("REDIS_PASSWORD", System.getenv("REDIS_PASSWORD"))
        );
    }

    public String generateAndSave(String role, String email) {
        Random rnd = new Random();
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // Identificação de role
        String part1 = role.equals("STUDENT") ? "S" : "T";

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
        String registration = part1+part2+part3;

        try (Jedis jedis = pool.getResource()) {

            jedis.setex(
                    "REGISTRATION:" + email,
                    (int) TTL.getSeconds(),
                    registration
            );
        }

        return registration;
    }

    public boolean validate(String email, String registration) {
        try (Jedis jedis = pool.getResource()) {

            String key = "REGISTRATION:" + email;

            String saved = jedis.get(key);

            if (saved == null || !saved.equals(registration)) {
                return false;
            }

            jedis.del(key);

            return true;
        }
    }
}
