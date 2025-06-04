import java.io.FileWriter;
import java.io.IOException;

public class Usuario {
    private String nome;
    private String cpf;
    private String senha;



    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String toString() {
        return nome + ";" + cpf + ";" + senha;
    }

    public static boolean validar(String cpf) {
        cpf = cpf.replaceAll("[^\\d]", "");

        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) return false;

        try {
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
            }
            int dig1 = 11 - (soma % 11);
            if (dig1 >= 10) dig1 = 0;

            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
            }
            int dig2 = 11 - (soma % 11);
            if (dig2 >= 10) dig2 = 0;

            return dig1 == Character.getNumericValue(cpf.charAt(9)) &&
                    dig2 == Character.getNumericValue(cpf.charAt(10));
        } catch (Exception e) {
            return false;
        }
    }

    public void salvarUsuario(Usuario usuario) {

        try{
            FileWriter escritorU =new FileWriter("usuarios.txt",true);
            escritorU.write(usuario.toString()+ "\n");

            escritorU.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar usuarios.");
            e.printStackTrace();

        }
    }
}