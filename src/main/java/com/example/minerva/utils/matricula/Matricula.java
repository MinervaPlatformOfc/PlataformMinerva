package com.example.minerva.utils.matricula;

import com.example.minerva.dao.StudentDAO;
import com.example.minerva.dao.TeacherDAO;
import io.github.cdimascio.dotenv.Dotenv;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

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
        boolean isStudent = role.equals("STUDENT");
        String part1 = isStudent ? "S" : "T";

        // Ano e dia do ano
        LocalDate hoje = LocalDate.now();
        String ano = String.valueOf(hoje.getYear()).substring(2); // últimos 2 dígitos do ano
        String diaDoAno = String.format("%03d", hoje.getDayOfYear()); // 3 dígitos do dia do ano
        String part2 = ano + diaDoAno;

        StudentDAO studentDAO = new StudentDAO();
        TeacherDAO teacherDAO = new TeacherDAO();

        String registration;
        try (Jedis jedis = isStudent ? pool.getResource() : null) {
//            String pong = jedis.ping();
//            System.out.println("Conexão com Redis: " + pong);

        // 2 últimos caracteres aleatórios
            do{
            String letra = "" + letras.charAt(rnd.nextInt(letras.length()));
            String numero = "" + rnd.nextInt(10);
            String part3 = letra + numero;

            // Combina tudo
            registration = part1+part2+part3;
            } while (isStudent ? studentDAO.findByRegistration(registration)
                    || jedis.get("REGISTRATION_CODE:" + registration) != null
                    : teacherDAO.findByRegistration(registration)
            );

            if (isStudent) {
                jedis.setex(
                        "REGISTRATION:" + email,
                        (int) TTL.getSeconds(),
                        registration
                );
                jedis.setex("REGISTRATION_CODE:" + registration, (int) TTL.getSeconds(), email); //O contrário, salvando a matricula como chave tbm pra ver se existe no redis antes de enviar para o user
            }
//            String keyEmail = "REGISTRATION:" + email;
//            String keyCode = "REGISTRATION_CODE:" + registration;
//            String savedRegistration = jedis.get(keyEmail);
//            String savedEmail = jedis.get(keyCode);
//            System.out.println("Verificação - Registro salvo: " + savedRegistration);
//            System.out.println("Verificação - Email salvo: " + savedEmail);
//
//            if (savedRegistration != null && savedEmail != null) {
//                System.out.println("✅ MATRÍCULA SALVA COM SUCESSO NO REDIS!");
//                System.out.println("TTL " + keyEmail + ": " + jedis.ttl(keyEmail) + " segundos");
//                System.out.println("TTL " + keyCode + ": " + jedis.ttl(keyCode) + " segundos");
//            } else {
//                System.out.println("❌ FALHA AO SALVAR NO REDIS!");
//            }
        }

        return registration;
    }

    public boolean validate(String email, String registration) {
        try (Jedis jedis = pool.getResource()) {
//            String pong = jedis.ping();
//            System.out.println("Conexão Redis: " + pong);

            String keyEmail = "REGISTRATION:" + email;
            String keyCode  = "REGISTRATION_CODE:" + registration;

            String saved = jedis.get(keyEmail);
//            System.out.println("Valor encontrado no Redis: " + (saved != null ? saved : "NULL"));
            if (saved == null || !saved.equals(registration)) {
                return false;
            }

            jedis.del(keyEmail);
            jedis.del(keyCode);

            return true;
        }
    }
}
