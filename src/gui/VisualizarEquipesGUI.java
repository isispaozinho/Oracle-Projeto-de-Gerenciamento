package gui;

import javax.swing.*;
import java.awt.*;
import data.Repository;
import model.Equipe;

public class VisualizarEquipesGUI extends JPanel {
    private Repository db = new Repository();
    private DefaultListModel<String> equipesModel = new DefaultListModel<>();
    private JList<String> equipesList = new JList<>(equipesModel);
    private JTextArea detalhesArea = new JTextArea();

    public VisualizarEquipesGUI() {
        setLayout(new BorderLayout());
        JLabel titulo = new JLabel("Equipes cadastradas:");
        add(titulo, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(equipesList);
        add(scroll, BorderLayout.WEST);

        detalhesArea.setEditable(false);
        add(new JScrollPane(detalhesArea), BorderLayout.CENTER);

        atualizarLista();

        equipesList.addListSelectionListener(e -> {
            int idx = equipesList.getSelectedIndex();
            if (idx >= 0) {
                mostrarDetalhes(idx);
            }
        });
    }

    private void atualizarLista() {
        equipesModel.clear();
        for (Equipe eq : db.getAllEquipes()) {
            equipesModel.addElement(eq.getNome());
        }
    }

    private void mostrarDetalhes(int idx) {
        Equipe eq = db.getAllEquipes().get(idx);
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(eq.getNome()).append("\n");
        sb.append("Descrição: ").append(eq.getDescricao()).append("\n");
        sb.append("Membros:\n");
        for (model.Usuario u : eq.getMembros()) {
            sb.append("- ").append(u.getNomeCompleto()).append(" (Perfil: ").append(u.getPerfil().getValue()).append(")\n");
        }
        sb.append("Projetos:\n");
        for (model.Projeto p : eq.getProjetos()) {
            sb.append("- ").append(p.getNome()).append(" (Status: ").append(p.getStatus().getValue()).append(")\n");
        }
        detalhesArea.setText(sb.toString());
    }
}
