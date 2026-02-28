package com.example.minerva.utils.otp;

import com.example.minerva.dao.StudentDAO;
import com.example.minerva.dao.TeacherDAO;
import io.github.cdimascio.dotenv.Dotenv;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Random;

public class Otp {

    private static final Duration TTL = Duration.ofMinutes(10); //Duração de 4 dias
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

    public String generateAndSave(String email) {
        String otp;
        try (Jedis jedis = pool.getResource()) {
            do{
                otp = String.valueOf(new SecureRandom().nextInt(900000) + 100000);
            } while (jedis.get("OTP:" + otp) != null);

            jedis.setex("OTP:" + otp, (int) TTL.getSeconds(), email); //Otp como chave tbm pra ver se existe no redis antes de enviar para o user
        }

        return otp;
    }

    public boolean validate(String email, String otp) {
        try (Jedis jedis = pool.getResource()) {
            String keyCode  = "OTP:" + otp;
            String saved = jedis.get(keyCode);

            if (saved == null || !saved.equals(email)) {
                return false;
            }

            jedis.del(keyCode);

            return true;
        }
    }
}
