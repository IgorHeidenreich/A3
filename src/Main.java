import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Usuario> usuarios = new ArrayList<>();
        ArrayList<Livro> livros = new ArrayList<>();
        int opcao;
        int escolha;

        boolean logado = false;
        Usuario usuarioLogado = null;

        do {
            System.out.println("================ MENU PRINCIPAL ================");
            System.out.println("[1] Cadastrar novo usuário");
            System.out.println("[2] Login de usuário");
            System.out.println("[3] Cadastrar novo livro");
            System.out.println("[4] Buscar livro por ID");
            System.out.println("[5] Editar livro");
            System.out.println("[6] Alugar livro");
            System.out.println("[7] Devolver livro");
            System.out.println("[8] Exibir livros salvos");
            System.out.println("[9] Exibir usuarios salvos");
            System.out.println("[10] Sair");
            System.out.println("===============================================");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    Usuario usuario = new Usuario();

                    System.out.print("Digite o nome do usuário: ");
                    usuario.setNome(scanner.nextLine());

                    System.out.print("Digite o CPF do usuário: ");
                    String cpfDigitado = scanner.nextLine();
                    if (!Usuario.validar(cpfDigitado)) {
                        System.out.println("CPF inválido! Cadastro cancelado.");
                        break;
                    }
                    usuario.setCpf(cpfDigitado);

                    System.out.print("Digite a senha do usuário: ");
                    usuario.setSenha(scanner.nextLine());

                    usuarios.add(usuario);
                    System.out.println("Usuário cadastrado com sucesso!");

                    usuario.salvarUsuario(usuario);
                    break;


                case 2:
                    try{BufferedReader leitorSenha = new BufferedReader(new FileReader("usuarios.txt"));
                        String linhas4;
                        while ((linhas4 = leitorSenha.readLine()) != null) {
                            String[] separar4 = linhas4.split(";");
                            if (separar4.length < 3) {
                                System.out.println("Erro: Linha mal formatada -> " + linhas4);
                                continue;
                            }
                            String nome = separar4[0];
                            String cpf = separar4[1];
                            String senha = separar4[2];


                            Usuario usuario2 = new Usuario();
                            usuario2.setNome(nome);
                            usuario2.setCpf(cpf);
                            usuario2.setSenha(senha);


                            usuarios.add(usuario2);


                            System.out.print("Digite o CPF: ");
                            String loginCpf = scanner.nextLine();

                            System.out.print("Digite a senha: ");
                            String loginSenha = scanner.nextLine();

                            boolean encontrou = false;
                            for (Usuario u : usuarios) {
                                if (u.getCpf().equals(loginCpf) && u.getSenha().equals(loginSenha)) {
                                    logado = true;
                                    usuarioLogado = u;
                                    encontrou = true;
                                    System.out.println("Login realizado com sucesso! Bem-vindo, " + u.getNome());
                                    break;
                                }
                            }
                            if (!encontrou) {
                                System.out.println("CPF ou senha incorretos.");
                            }
                        }
            }
                    catch(IOException f){
                        f.printStackTrace();
                    }break;

                case 3:
                    if (!logado) {
                        System.out.println("Você precisa estar logado para cadastrar um livro.");
                        break;
                    }
                    Livro livro = new Livro();

                    System.out.print("Digite o id do livro: ");
                    livro.setId(scanner.nextLine());

                    System.out.print("Digite o titulo do livro: ");
                    livro.setTitulo(scanner.nextLine());

                    System.out.print("Digite o ano de publicação do livro: ");
                    livro.setAnoDePublicacao(scanner.nextLine());

                    System.out.print("Digite o isbn do livro: ");
                    livro.setIsbn(scanner.nextLine());

                    System.out.print("Digite o autor do livro: ");
                    livro.setAutor(scanner.nextLine());

                    livros.add(livro);
                    System.out.println("Livro cadastrado com sucesso!");



                    livro.salvarLivro(livro);
                    break;


                case 4:
                    livros.clear();
                    try { BufferedReader leitoriD = new BufferedReader(new FileReader("livros.txt"));
                    String linhas5;
                    while ((linhas5 = leitoriD.readLine()) != null) {
                        String [] separar5 = linhas5.split(";");
                        if (separar5.length < 6) {
                            System.out.println("Erro: Linha mal formatada -> " + linhas5);
                            continue;
                        }

                        String ID = separar5[0];
                        String titulo = separar5[1];
                        String autor = separar5[2];
                        String ano =separar5[3];
                        String isbn = separar5[4];
                        boolean disponibilidade = separar5[5].equals("s");

                        Livro livro5 = new Livro();
                        livro5.setId(ID);
                        livro5.setTitulo(titulo);
                        livro5.setAutor(autor);
                        livro5.setAnoDePublicacao(ano);
                        livro5.setIsbn(isbn);
                        livro5.setDisponibilidade(disponibilidade);

                        livros.add(livro5);

                    }

                } catch(IOException b){
                    b.printStackTrace();
                }
                    System.out.print("Digite a ID do livro que você deseja buscar: ");
                    String id = scanner.nextLine();
                    boolean encontrado = false;
                    for (Livro l : livros) {
                        if (l.getId().equals(id)) {
                            System.out.println(l.getTitulo() + " - " + l.getAutor() + " - " + l.getAnoDePublicacao() + " - Disponível: " + (l.getDisponibilidade()? "s" : "n"));
                            encontrado = true;
                        }
                    }
                    if (!encontrado) System.out.println("Livro não está cadastrado");
                    break;

                case 5:
                    livros.clear();
                    try { BufferedReader leitorAlterar = new BufferedReader(new FileReader("livros.txt"));
                        String linhas6;
                        while ((linhas6 = leitorAlterar.readLine()) != null) {
                            String [] separar6 = linhas6.split(";");
                            if (separar6.length < 6) {
                                System.out.println("Erro: Linha mal formatada -> " + linhas6);
                                continue;
                            }

                            String ID = separar6[0];
                            String titulo = separar6[1];
                            String autor = separar6[2];
                            String ano =separar6[3];
                            String isbn = separar6[4];
                            boolean disponibilidade = separar6[5].equals("s");

                            Livro livro6 = new Livro();
                            livro6.setId(ID);
                            livro6.setTitulo(titulo);
                            livro6.setAutor(autor);
                            livro6.setAnoDePublicacao(ano);
                            livro6.setIsbn(isbn);
                            livro6.setDisponibilidade(disponibilidade);

                            livros.add(livro6);

                        }

                    } catch(IOException b){
                        b.printStackTrace();
                    }
                    System.out.print("Digite o ID do livro que você deseja editar: ");
                    String id2 = scanner.nextLine();
                    boolean editado = false;
                    for (Livro l : livros) {
                        if (l.getId().equals(id2)) {
                            System.out.print("Digite o novo id do livro: ");
                            l.setId(scanner.nextLine());

                            System.out.print("Digite o novo titulo do livro: ");
                            l.setTitulo(scanner.nextLine());

                            System.out.print("Digite o novo ano do de publicação do livro: ");
                            l.setAnoDePublicacao(scanner.nextLine());

                            System.out.print("Digite o novo isbn do livro: ");
                            l.setIsbn(scanner.nextLine());

                            System.out.print("Digite o novo autor do livro: ");
                            l.setAutor(scanner.nextLine());

                            System.out.print("Digite a nova disponibilidade no livro: (s/n): ");
                            String resposta = scanner.nextLine().trim().toLowerCase();
                            boolean disponibilidade = resposta.equals("s");
                            l.setDisponibilidade(disponibilidade);

                            System.out.println("Cadastro do livro atualizado com sucesso!");
                            editado = true;


                            break;
                        }
                    }
                    if (!editado) {
                        System.out.println("Livro não está cadastrado");
                    } else {
                        try (BufferedWriter escritor = new BufferedWriter(new FileWriter("livros.txt", false))) {
                            for (Livro l : livros) {
                                escritor.write(l.toString());
                                escritor.newLine();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    break;

                case 6:
                    if (!logado) {
                        System.out.println("Você precisa estar logado para alugar um livro.");
                        break;
                    }
                    do {
                        System.out.println("Como deseja buscar o livro para alugar?");
                        System.out.println("[1] Buscar por ID");
                        System.out.println("[2] Buscar por Título");
                        System.out.println("[3] Voltar ao menu principal");
                        System.out.print("Escolha: ");
                        escolha = scanner.nextInt();
                        scanner.nextLine();

                        switch (escolha) {
                            case 1:
                                livros.clear();
                                try {
                                    BufferedReader leitorID = new BufferedReader(new FileReader("livros.txt"));
                                    String linhas;
                                    while ((linhas = leitorID.readLine()) != null) {
                                        String[] separar = linhas.split(";");
                                        if (separar.length < 6) {
                                            System.out.println("Erro: Linha mal formatada -> " + linhas);
                                            continue;
                                        }
                                        String ID = separar[0];
                                        String titulo = separar[1];
                                        String autor = separar[2];
                                        String ano = separar[3];
                                        String isbn = separar[4];
                                        boolean disponibilidade = separar[5].equalsIgnoreCase("s");

                                        Livro livro2 = new Livro();
                                        livro2.setId(ID);
                                        livro2.setTitulo(titulo);
                                        livro2.setAutor(autor);
                                        livro2.setAnoDePublicacao(ano);
                                        livro2.setIsbn(isbn);
                                        livro2.setDisponibilidade(disponibilidade);

                                        livros.add(livro2);
                                    }
                                    leitorID.close();
                                } catch (IOException a) {
                                    a.printStackTrace();
                                }

                                System.out.print("Digite a ID do livro: ");
                                String id3 = scanner.nextLine();
                                boolean alugadoId = false;

                                for (Livro l : livros) {
                                    if (l.getId().equals(id3)) {
                                        if (!l.getDisponibilidade()) {
                                            System.out.println("Livro já está alugado.");
                                        } else {
                                            l.setDisponibilidade(false);
                                            System.out.println("Livro alugado com sucesso!");
                                        }
                                        alugadoId = true;
                                        break;
                                    }
                                }

                                if (!alugadoId) System.out.println("Livro não encontrado.");


                                try {
                                    FileWriter escritorL = new FileWriter("livros.txt", false);
                                    for (Livro l : livros) {
                                        escritorL.write(l.toString() + "\n");
                                    }
                                    escritorL.close();
                                } catch (IOException e) {
                                    System.out.println("Erro ao salvar livro.");
                                    e.printStackTrace();
                                }
                                break;

                            case 2:
                                livros.clear();
                                try {
                                    BufferedReader leitorTitulo = new BufferedReader(new FileReader("livros.txt"));
                                    String linhas2;
                                    while ((linhas2 = leitorTitulo.readLine()) != null) {
                                        String[] separar2 = linhas2.split(";");
                                        if (separar2.length < 6) {
                                            System.out.println("Erro: Linha mal formatada -> " + linhas2);
                                            continue;
                                        }
                                        String ID = separar2[0];
                                        String titulo = separar2[1];
                                        String autor = separar2[2];
                                        String ano = separar2[3];
                                        String isbn = separar2[4];
                                        boolean disponibilidade = separar2[5].equalsIgnoreCase("s");

                                        Livro livro3 = new Livro();
                                        livro3.setId(ID);
                                        livro3.setTitulo(titulo);
                                        livro3.setAutor(autor);
                                        livro3.setAnoDePublicacao(ano);
                                        livro3.setIsbn(isbn);
                                        livro3.setDisponibilidade(disponibilidade);

                                        livros.add(livro3);
                                    }
                                    leitorTitulo.close();
                                } catch (IOException b) {
                                    b.printStackTrace();
                                }

                                System.out.print("Digite o título do livro: ");
                                String titulo3 = scanner.nextLine();
                                boolean alugadoTitulo = false;

                                for (Livro l : livros) {
                                    if (l.getTitulo().equals(titulo3)) {
                                        if (!l.getDisponibilidade()) {
                                            System.out.println("Livro já está alugado.");
                                        } else {
                                            l.setDisponibilidade(false);
                                            System.out.println("Livro alugado com sucesso!");
                                        }
                                        alugadoTitulo = true;
                                        break;
                                    }
                                }

                                if (!alugadoTitulo) System.out.println("Livro não encontrado.");


                                try {
                                    FileWriter escritorL = new FileWriter("livros.txt", false);
                                    for (Livro l : livros) {
                                        escritorL.write(l.toString() + "\n");
                                    }
                                    escritorL.close();
                                } catch (IOException e) {
                                    System.out.println("Erro ao salvar livro.");
                                    e.printStackTrace();
                                }
                                break;
                        }
                    } while (escolha != 3);
                    break;
                case 7:
                    livros.clear();
                    try { BufferedReader leitordevolver = new BufferedReader(new FileReader("livros.txt"));
                        String linhas3;
                        while ((linhas3 = leitordevolver.readLine()) != null) {
                            String [] separar3 = linhas3.split(";");

                            String ID = separar3[0];
                            String titulo = separar3[1];
                            String autor = separar3[2];
                            String ano =separar3[3];
                            String isbn = separar3[4];
                            boolean disponibilidade = separar3[5].equals("s");

                            Livro livro4 = new Livro();
                            livro4.setId(ID);
                            livro4.setTitulo(titulo);
                            livro4.setAutor(autor);
                            livro4.setAnoDePublicacao(ano);
                            livro4.setIsbn(isbn);
                            livro4.setDisponibilidade(disponibilidade);

                            livros.add(livro4);

                        }

                    } catch(IOException c){
                        c.printStackTrace();
                    }
                    System.out.print("Digite o ID do livro para devolver: ");
                    String idDevolver = scanner.nextLine();
                    boolean devolvido = false;
                    for (Livro l : livros) {
                        if (l.getId().equals(idDevolver)) {
                            if (!l.getDisponibilidade()) {
                                l.setDisponibilidade(true);
                                System.out.println("Livro devolvido com sucesso!");
                            } else {
                                System.out.println("Livro já está disponível.");
                            }
                            devolvido = true;
                            break;
                        }
                    }
                    if (!devolvido) {
                        System.out.println("Livro não encontrado.");
                    } else {

                        try {
                            FileWriter escritorL = new FileWriter("livros.txt", false);
                            for (Livro l : livros) {
                                escritorL.write(l.toString() + "\n");
                            }
                            escritorL.close();
                        } catch (IOException e) {
                            System.out.println("Erro ao salvar livro.");
                            e.printStackTrace();
                        }
                    }
                    break;
                case 8:
                    livros.clear();
                    try{
                        BufferedReader leitorL = new BufferedReader(new FileReader("livros.txt"));
                        String linha;

                        while((linha = leitorL.readLine()) != null){
                            String[] partes = linha.split(";");
                            if (partes.length >=6){
                                Livro l = new Livro();
                                l.setId(partes[0]);
                                l.setTitulo(partes[1]);
                                l.setAutor(partes[2]);
                                l.setAnoDePublicacao(partes[3]);
                                l.setIsbn(partes[4]);
                                l.setDisponibilidade(partes[5].equals("s"));
                                livros.add(l);
                            }
                        }
                        leitorL.close();
                        System.out.println("Exibindo livros salvos. ");
                        for (Livro l : livros) {
                            System.out.println(l.getId()+ " - " + l.getTitulo() + " - " + l.getAutor() + " - " + l.getAnoDePublicacao() + " - " + l.getIsbn() + " - " + (l.getDisponibilidade() ? "s" : "n"));
                        }
                    }  catch (IOException e) {
                        System.out.println("Erro ao exibir livros salvos");
                        e.printStackTrace();
                    }
                    break;

                case 9:
                    try {

                        BufferedReader leitorU = new BufferedReader(new FileReader("usuarios.txt"));
                        String linha;

                        while ((linha = leitorU.readLine()) != null) {
                            String[] partes = linha.split(";");
                            if ( partes.length >= 2){
                                Usuario u = new Usuario();
                                u.setNome(partes[0]);
                                u.setCpf(partes[1]);
                                u.setSenha(partes[2]);
                                usuarios.add(u);
                            }
                        }
                        leitorU.close();
                        System.out.println("Exibindo usuarios salvos. ");
                        for (Usuario u : usuarios) {
                            System.out.println(u.getNome() + " - " + u.getCpf());
                        }
                    } catch (IOException e) {
                        System.out.println("Erro ao exibir usuarios salvos");
                        e.printStackTrace();
                    }
                    break;
            }
        } while (opcao != 10);
        System.out.println("Encerrando sistema. Até mais!");
    }
}