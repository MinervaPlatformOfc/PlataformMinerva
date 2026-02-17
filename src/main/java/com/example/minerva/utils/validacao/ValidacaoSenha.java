package com.example.minerva.utils.validacao;

//Importação de regex
import java.util.regex.*;

// Classe para validar formato da senha por regex
public class ValidacaoSenha {
        //Metodo booleano de validação da senha, retorna true se válida
        public static boolean validarSenha(String senha) {

            //Expressão regular (regex) --> Pelo menos 1 letra minuscula, 1 maiuscula, 1 caractere especial, e 8 caracteres no mínimo
            String expressao = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{8,}$";
            //Compila a expressão
            Pattern regex = Pattern.compile(expressao);
            //Comparador com senha
            Matcher validacao = regex.matcher(senha);

            //Retorna se a senha é valida
            return validacao.matches();
        }
}
