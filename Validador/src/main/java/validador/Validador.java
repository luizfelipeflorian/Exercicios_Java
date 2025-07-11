package validador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author 06850947150
 */
public class Validador {

    public boolean tamanho(String senha) {
        boolean tamanho = false;
        if (senha.length() >= 8) {
            tamanho = true;
        }
        return tamanho;
    }

    public boolean numero(String senha) {
        String regex = ".*[0-9].*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(senha);
        return matcher.find();
    }

    public boolean letra(String senha) {

        String regex = ".*[A-Z].*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(senha);
        return matcher.matches();
    }
}