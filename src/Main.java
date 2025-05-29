
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
            System.out.println("[3] Listar usuários");
            System.out.println("[4] Cadastrar novo livro");
            System.out.println("[5] Listar livros");
            System.out.println("[6] Buscar livro por ID");
            System.out.println("[7] Editar livro");
            System.out.println("[8] Alugar livro");
            System.out.println("[9] Dvolver livro");
            System.out.println("[10] Salvar livros cadastrados");
            System.out.println("[11] Salvar usuarios cadastrados");
            System.out.println("[12] Exibir livros salvos");
            System.out.println("[13] Exibir usuarios salvos");
            System.out.println("[14] Sair");
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
                    usuario.setCpf(scanner.nextLine());

                    System.out.print("Digite a senha do usuário: ");
                    usuario.setSenha(scanner.nextLine());

                    usuarios.add(usuario);
                    System.out.println("Usuário cadastrado com sucesso!");
                    break;

                case 2:
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
                    break;

                case 3:
                    System.out.println("Lista de usuários cadastrados:");
                    for (Usuario u : usuarios) {
                        System.out.println("Nome: " + u.getNome() + " - CPF: " + u.getCpf());
                    }
                    break;

                case 4:
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
                    break;

                case 5:
                    System.out.println("Lista dos livros cadastrados:");
                    for (Livro l : livros) {
                        System.out.println(l.getId() + " - " + l.getTitulo() + " - " + l.getAutor() + " - " + l.getAnoDePublicacao() + " - " + l.getIsbn() + " - " + l.getDisponibilidade());
                    }
                    break;

                case 6:
                    System.out.print("Digite a ID do livro que você deseja buscar: ");
                    String id = scanner.nextLine();
                    boolean encontrado = false;
                    for (Livro l : livros) {
                        if (l.getId().equals(id)) {
                            System.out.println(l.getTitulo() + " - " + l.getAutor() + " - " + l.getAnoDePublicacao() + " - Disponível: " + l.getDisponibilidade());
                            encontrado = true;
                        }
                    }
                    if (!encontrado) System.out.println("Livro não está cadastrado");
                    break;

                case 7:
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
                    }
                    break;

                case 8:
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
                                    }
                                }
                                if (!alugadoId) System.out.println("Livro não encontrado.");
                                break;
                            case 2:
                                System.out.print("Digite o título do livro: ");
                                String titulo = scanner.nextLine();
                                boolean alugadoTitulo = false;
                                for (Livro l : livros) {
                                    if (l.getTitulo().equals(titulo)) {
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
                                break;
                        }
                    } while (escolha != 3);
                    break;
                case 9:
                    System.out.print("Digite o ID do livro para devolver: ");
                    String idDevolver = scanner.nextLine();
                    encontrado = false;
                    for (Livro l : livros) {
                        if (l.getId().equals(idDevolver)) {
                            if (!l.getDisponibilidade()) {
                                l.setDisponibilidade(true);
                                System.out.println("Livro devolvido com sucesso!");
                            } else {
                                System.out.println("Livro já está disponível.");
                            }
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) System.out.println("Livro não encontrado.");
                    break;
                case 10:
                    if (livros.isEmpty()) {
                        System.out.println("Não existem livros no sistema.");
                        break;
                    }
                    try {
                        FileWriter escritorL = new FileWriter("livros.txt", true);
                        for (Livro l : livros) {
                            escritorL.write(l.toString()+ "\n");
                        }
                        escritorL.close();
                    } catch (IOException e) {
                        System.out.println("Erro ao salvar livro.");
                        e.printStackTrace();
                        break;
                    }

                    break;
                case 11:
                    if (usuarios.isEmpty()){
                        System.out.println("Não existem usuarios no sistema.");
                        break;
                    }
                    try{
                        FileWriter escritorU =new FileWriter("usuarios.txt",true);
                        for (Usuario u : usuarios) {
                            escritorU.write(u.toString()+ "\n");
                        }
                        escritorU.close();
                    } catch (IOException e) {
                        System.out.println("Erro ao salvar usuarios.");
                        e.printStackTrace();
                    }
                    break;
                case 12:
                    try{
                        livros.clear();
                        BufferedReader leitorL = new BufferedReader(new FileReader("livros.txt"));
                        String linha;

                        while((linha = leitorL.readLine()) != null){
                            String[] partes = linha.split(";");

                                Livro l = new Livro();
                                l.setId(partes[0]);
                                l.setTitulo(partes[1]);
                                l.setAutor(partes[2]);
                                l.setAnoDePublicacao(partes[3]);
                                l.setIsbn(partes[4]);
                                l.setDisponibilidade(partes[5].equals("s"));
                                livros.add(l);

                        }
                        leitorL.close();
                        System.out.println("Exibindo livros salvos. ");
                        for (Livro l : livros) {
                            System.out.println(l.getId()+ " - " + l.getTitulo() + " - " + l.getAutor() + " - " + l.getAnoDePublicacao() + " - " + l.getIsbn() + " - " + l.getDisponibilidade());
                        }
                    }  catch (IOException e) {
                        System.out.println("Erro ao exibir livros salvos");
                        e.printStackTrace();
                    }
                    break;
                    case 13:
                        try{
                            usuarios.clear();
                            BufferedReader leitorU = new BufferedReader(new FileReader("usuarios.txt"));
                            String linha;
                                while((linha = leitorU.readLine()) != null){
                                    String[] partes = linha.split(";");
                                    Usuario u =new Usuario();
                                    u.setNome(partes[0]);
                                    u.setCpf(partes[1]);
                                    u.setSenha(partes[2]);
                                    usuarios.add(u);
                            }
                            leitorU.close();
                                System.out.println("Exibindo usuarios salvos. ");
                                for (Usuario u : usuarios) {
                                    System.out.println(u.getNome() + " - " + u.getCpf());
                                }
                        }  catch (IOException e) {
                            System.out.println("Erro ao exibir usuarios salvos");
                            e.printStackTrace();
                        }
                default:
                    System.out.println("Escolha uma opção válida");
            }
        } while (opcao != 14);
        System.out.println("Encerrando sistema. Até mais!");
    }
}