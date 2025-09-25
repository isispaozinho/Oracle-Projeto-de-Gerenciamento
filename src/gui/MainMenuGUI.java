package gui;

import javax.swing.*;
import java.awt.*;

public class MainMenuGUI extends JFrame {
    public MainMenuGUI() {
        setTitle("Sistema de Gestão - Menu Principal");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    JTabbedPane tabs = new JTabbedPane();

    // Abas de cadastro
    tabs.addTab("Cadastrar Usuário", new MainGUI());
    tabs.addTab("Cadastrar Projeto", new ProjetoGUI());
    tabs.addTab("Cadastrar Equipe", new EquipeGUI());

    // Abas de visualização
    tabs.addTab("Visualizar Equipes", new VisualizarEquipesGUI());

    add(tabs);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenuGUI().setVisible(true));
    }
}
