package gui;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import data.Repository;
import model.Projeto;
import model.Usuario;
import model.StatusProjeto;
import model.PerfilUsuario;

public class ProjetoGUI extends JPanel {
    private Repository db = new Repository();

    public ProjetoGUI() {
        setLayout(new GridLayout(8, 2));

        JTextField nomeField = new JTextField();
        JTextField descricaoField = new JTextField();
        JTextField dataInicioField = new JTextField(LocalDate.now().toString());
        JTextField dataTerminoField = new JTextField();
        JComboBox<String> statusBox = new JComboBox<>(new String[]{"PLANEJADO", "EM_ANDAMENTO", "CONCLUIDO", "CANCELADO"});

        // Gerentes disponíveis
        List<Usuario> usuarios = db.getAllUsuarios();
        DefaultComboBoxModel<Usuario> gerenteModel = new DefaultComboBoxModel<>();
        for (Usuario u : usuarios) {
            if (u.getPerfil() == PerfilUsuario.GERENTE || u.getPerfil() == PerfilUsuario.ADMINISTRADOR) {
                gerenteModel.addElement(u);
            }
        }
        JComboBox<Usuario> gerenteBox = new JComboBox<>(gerenteModel);

        add(new JLabel("Nome do projeto:"));
        add(nomeField);
        add(new JLabel("Descrição:"));
        add(descricaoField);
        add(new JLabel("Data de início (AAAA-MM-DD):"));
        add(dataInicioField);
        add(new JLabel("Data de término prevista (AAAA-MM-DD):"));
        add(dataTerminoField);
        add(new JLabel("Status:"));
        add(statusBox);
        add(new JLabel("Gerente responsável:"));
        add(gerenteBox);

        JButton cadastrarBtn = new JButton("Cadastrar Projeto");
        add(cadastrarBtn);

        cadastrarBtn.addActionListener(e -> {
            try {
                String nome = nomeField.getText();
                String descricao = descricaoField.getText();
                LocalDate dataInicio = LocalDate.parse(dataInicioField.getText());
                LocalDate dataTermino = LocalDate.parse(dataTerminoField.getText());
                StatusProjeto status = StatusProjeto.valueOf((String) statusBox.getSelectedItem());
                Usuario gerente = (Usuario) gerenteBox.getSelectedItem();

                if (gerente == null) {
                    JOptionPane.showMessageDialog(this, "Selecione um gerente válido.");
                    return;
                }

                Projeto projeto = new Projeto(nome, descricao, dataInicio, dataTermino, gerente);
                projeto.setStatus(status);
                db.addProjeto(projeto);
                JOptionPane.showMessageDialog(this, "Projeto cadastrado com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar projeto: " + ex.getMessage());
            }
        });
    }
}
