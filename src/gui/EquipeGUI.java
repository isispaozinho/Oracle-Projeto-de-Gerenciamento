package gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import data.Repository;
import model.Usuario;
import model.Projeto;
import model.Equipe;

public class EquipeGUI extends JPanel {
    private Repository db = new Repository();

    public EquipeGUI() {
        setLayout(new GridLayout(6, 2));

        JTextField nomeField = new JTextField();
        JTextField descricaoField = new JTextField();

        // Listas de seleção de membros e projetos
        List<Usuario> usuarios = db.getAllUsuarios();
        List<Projeto> projetos = db.getAllProjetos();
        JList<Usuario> membrosList = new JList<>(usuarios.toArray(new Usuario[0]));
        membrosList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane membrosScroll = new JScrollPane(membrosList);

        JList<Projeto> projetosList = new JList<>(projetos.toArray(new Projeto[0]));
        projetosList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane projetosScroll = new JScrollPane(projetosList);

        add(new JLabel("Nome da equipe:"));
        add(nomeField);
        add(new JLabel("Descrição:"));
        add(descricaoField);
        add(new JLabel("Membros (Ctrl+Clique para múltiplos):"));
        add(membrosScroll);
        add(new JLabel("Projetos (Ctrl+Clique para múltiplos):"));
        add(projetosScroll);

        JButton cadastrarBtn = new JButton("Cadastrar Equipe");
        add(cadastrarBtn);

        cadastrarBtn.addActionListener(e -> {
            String nome = nomeField.getText();
            String descricao = descricaoField.getText();
            Equipe equipe = new Equipe(nome, descricao);

            for (Usuario u : membrosList.getSelectedValuesList()) {
                equipe.adicionarMembro(u);
            }
            for (Projeto p : projetosList.getSelectedValuesList()) {
                equipe.vincularProjeto(p);
            }
            db.addEquipe(equipe);
            JOptionPane.showMessageDialog(this, "Equipe cadastrada com sucesso!");
        });
    }
}
