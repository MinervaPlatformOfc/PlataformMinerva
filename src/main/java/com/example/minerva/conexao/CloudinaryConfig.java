package com.example.minerva.conexao;
import io.github.cdimascio.dotenv.Dotenv;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
public class CloudinaryConfig {
    private static Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing()
            .load();

    private static final Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", dotenv.get("CLOUDINARY_CLOUD_NAME", System.getenv("CLOUDINARY_CLOUD_NAME")),
            "api_key", dotenv.get("CLOUDINARY_API_KEY", System.getenv("CLOUDINARY_API_KEY")),
            "api_secret", dotenv.get("CLOUDINARY_API_SECRET", System.getenv("CLOUDINARY_API_SECRET")),
            "secure", true
    ));


    public static Cloudinary getInstance() {
        return cloudinary;
    }
}
