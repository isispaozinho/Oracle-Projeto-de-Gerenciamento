import data.Repository;
import model.Usuario;
import model.Projeto;
import model.PerfilUsuario;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Repository db = new Repository();
    private static Scanner scanner = new Scanner(System.in);

    private static void cadastrarUsuario() {
        System.out.println("\n--- Cadastro de Novo Usuário ---");
        System.out.print("Nome completo: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("E-mail: ");
        String email = scanner.nextLine();
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        System.out.println("Perfis disponíveis: [1] Administrador, [2] Gerente, [3] Colaborador");
        System.out.print("Escolha o perfil (1, 2 ou 3): ");
        String perfilEscolha = scanner.nextLine();

        PerfilUsuario perfil;
        if (perfilEscolha.equals("1")) {
            perfil = PerfilUsuario.ADMINISTRADOR;
        } else if (perfilEscolha.equals("2")) {
            perfil = PerfilUsuario.GERENTE;
        } else {
            perfil = PerfilUsuario.COLABORADOR;
        }

        if (db.getUsuarioByLogin(login) != null) {
            System.out.println("\n>>> Erro: Login já existe. Tente outro.");
            return;
        }

        Usuario novoUsuario = new Usuario(nome, cpf, email, perfil, login, senha);
        db.addUsuario(novoUsuario);
        System.out.println("\n>>> Usuário cadastrado com sucesso!");
    }

    private static void cadastrarProjeto() {
        System.out.println("\n--- Cadastro de Novo Projeto ---");
        System.out.print("Nome do projeto: ");
        String nome = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        System.out.print("Login do gerente responsável: ");
        String loginGerente = scanner.nextLine();
        Usuario gerente = db.getUsuarioByLogin(loginGerente);

        if (gerente == null) {
            System.out.printf("\n>>> Erro: Usuário com login '%s' não encontrado.\n", loginGerente);
            return;
        }
        if (gerente.getPerfil() != PerfilUsuario.GERENTE && gerente.getPerfil() != PerfilUsuario.ADMINISTRADOR) {
            System.out.printf("\n>>> Erro: O usuário '%s' não tem perfil de Gerente ou Administrador.\n", loginGerente);
            return;
        }

        LocalDate dataInicio = LocalDate.now();
        System.out.print("Ano de término previsto (ex: 2025): ");
        int anoTermino = Integer.parseInt(scanner.nextLine());
        System.out.print("Mês de término previsto (1-12): ");
        int mesTermino = Integer.parseInt(scanner.nextLine());
        System.out.print("Dia de término previsto (1-31): ");
        int diaTermino = Integer.parseInt(scanner.nextLine());
        LocalDate dataTermino = LocalDate.of(anoTermino, mesTermino, diaTermino);

        Projeto novoProjeto = new Projeto(nome, descricao, dataInicio, dataTermino, gerente);
        db.addProjeto(novoProjeto);
        System.out.println("\n>>> Projeto cadastrado com sucesso!");
    }

    private static void listarProjetos() {
        System.out.println("\n--- Lista de Projetos Cadastrados ---");
        List<Projeto> projetos = db.getAllProjetos();
        if (projetos.isEmpty()) {
            System.out.println("Nenhum projeto cadastrado.");
            return;
        }
        for (Projeto proj : projetos) {
            System.out.printf("ID: %d | Nome: %s | Status: %s | Gerente: %s\n",
                proj.getId(), proj.getNome(), proj.getStatus().getValue(), proj.getGerente().getNomeCompleto());
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Sistema de Gestão de Projetos =====");
            System.out.println("1. Cadastrar Novo Usuário");
            System.out.println("2. Cadastrar Novo Projeto");
            System.out.println("3. Listar todos os Projetos");
            System.out.println("0. Sair");
            System.out.print("Digite sua escolha: ");
            String escolha = scanner.nextLine();

            switch (escolha) {
                case "1":
                    cadastrarUsuario();
                    break;
                case "2":
                    cadastrarProjeto();
                    break;
                case "3":
                    listarProjetos();
                    break;
                case "0":
                    System.out.println("Saindo do sistema...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
