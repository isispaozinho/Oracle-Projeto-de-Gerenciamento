package gui;

import javax.swing.*;
import java.awt.*;
import data.Repository;
import model.Usuario;
import model.PerfilUsuario;

public class MainGUI extends JPanel {
    private Repository db = new Repository();

    public MainGUI() {
        setLayout(new GridLayout(7, 2));

        JTextField nomeField = new JTextField();
        JTextField cpfField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField loginField = new JTextField();
        JTextField senhaField = new JTextField();
        JComboBox<String> perfilBox = new JComboBox<>(new String[]{"ADMINISTRADOR", "GERENTE", "COLABORADOR"});

        add(new JLabel("Nome completo:"));
        add(nomeField);
        add(new JLabel("CPF:"));
        add(cpfField);
        add(new JLabel("E-mail:"));
        add(emailField);
        add(new JLabel("Login:"));
        add(loginField);
        add(new JLabel("Senha:"));
        add(senhaField);
        add(new JLabel("Perfil:"));
        add(perfilBox);

        JButton cadastrarBtn = new JButton("Cadastrar");
        add(cadastrarBtn);

        cadastrarBtn.addActionListener(e -> {
            String nome = nomeField.getText();
            String cpf = cpfField.getText();
            String email = emailField.getText();
            String login = loginField.getText();
            String senha = senhaField.getText();
            PerfilUsuario perfil = PerfilUsuario.valueOf((String) perfilBox.getSelectedItem());

            if (db.getUsuarioByLogin(login) != null) {
                JOptionPane.showMessageDialog(this, "Login já existe!");
                return;
            }

            Usuario novoUsuario = new Usuario(nome, cpf, email, perfil, login, senha);
            db.addUsuario(novoUsuario);
            JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
        });
    }
}
