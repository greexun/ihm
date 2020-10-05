package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;
import javax.sql.*;
import java.sql.*;
import java.util.ArrayList;
import Metier.Etudiant;
import implementationDAO.EtudiantDAOimpl;
import interfaceDAO.IEtudiantDAO;
import persistance.MyOracleDataSource;


/**
 * Classe de creation de la fenetre
 */
public class Fenetre extends JFrame /*implements WindowListener*/  {
    private static IEtudiantDAO etudiantDAO;
    private static DataSource dataSourceDAO;
    private static Connection connexionBD;

    private ArrayList<Etudiant> lesEtudiants;

    // déclaration de tous les éléments de la fenetre
    private JComboBox etuComboBox;
    private DefaultComboBoxModel<String> dcm = new DefaultComboBoxModel<>();

    private JLabel etatcivilLabel;
    private JLabel etatcivilImageLabel;
    private JLabel nomLabel;
    private JLabel prenomLabel;
    private JLabel anneeLabel;
    private JLabel loisirsLabel;
    private JLabel commentaireLabel;
    private JCheckBox cinemaCheckBox;
    private JCheckBox sportCheckBox;
    private JCheckBox lectureCheckBox;
    private JComboBox etatcivilComboBox;
    private JTextArea commentaireTextArea;
    private JTextField nomTextField;
    private JTextField prenomTextField;
    private JButton ajouterButton;
    private JButton modifierButton;
    private JButton supprimerButton;
    private JRadioButton a1RadioButton;
    private JRadioButton a2RadioButton;
    private ButtonGroup anneeButtonGroup;

    private JLabel semestreLabel;
    private JRadioButton semestre1RadioButton;
    private JRadioButton semestre2RadioButton;
    private JRadioButton semestre3RadioButton;
    private JRadioButton semestre4RadioButton;
    private ButtonGroup semestreButtonGroup;

    private JPanel panel;
    private JPanel etatcivilPanel;
    private JPanel nomprenomPanel;
    private JPanel anneePanel;
    private JPanel loisirsPanel;
    private JPanel commentairePanel;
    private JPanel boutonsPanel;

    private JPanel lignesrapprocheesPanel;

    // On met la frame en attribut pour pouvoir l'utiliser dans une class interne
    private JFrame frame = this;

    // Booleen vrai par défault pour activer ou non l'efet des boutons
    private boolean buttonEffects = true;

    /**
     * initialise les composants de la fenetre
     */
    private void initComponents() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.etuComboBox = new JComboBox(dcm);
        loadComboBox();

        this.etatcivilLabel = new JLabel("Etat Civil :");
        ImageIcon ecImage = new ImageIcon(this.getClass().getResource("/images/homme.png"));
        ecImage = new ImageIcon(ecImage.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        this.etatcivilImageLabel = new JLabel(ecImage);
        this.nomLabel = new JLabel("Nom :");
        this.prenomLabel = new JLabel("Prenom :");
        this.anneeLabel = new JLabel("Année :");
        this.loisirsLabel = new JLabel("Loisirs :");
        this.commentaireLabel = new JLabel("Commentaire :");

        this.cinemaCheckBox = new JCheckBox("Cinéma");
        this.sportCheckBox = new JCheckBox("Sport");
        this.lectureCheckBox = new JCheckBox("Lecture");

        String[] ec = new String[]{"M.", "Mme."};
        this.etatcivilComboBox = new JComboBox(ec);

        this.commentaireTextArea = new JTextArea("Saisir votre commentaire", 4, 10);

        this.nomTextField = new JTextField(10);
        this.prenomTextField = new JTextField(10);

        this.ajouterButton = new JButton("Ajouter");
        this.modifierButton = new JButton("Modifier");
        this.supprimerButton = new JButton("Supprimer");

        this.a1RadioButton = new JRadioButton("1A");
        this.a2RadioButton = new JRadioButton("2A");

        this.anneeButtonGroup = new ButtonGroup();
        this.anneeButtonGroup.add(this.a1RadioButton);
        this.anneeButtonGroup.add(this.a2RadioButton);

        // on crée les différents panneaux, et leur layout respectif
        panel = (JPanel) this.getContentPane();
        panel.setLayout(new GridLayout(4, 1));

        etatcivilPanel = new JPanel();
        etatcivilPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        nomprenomPanel = new JPanel();
        nomprenomPanel.setLayout((new FlowLayout(FlowLayout.LEFT)));

        anneePanel = new JPanel();
        anneePanel.setLayout((new FlowLayout(FlowLayout.LEFT)));

        loisirsPanel = new JPanel();
        loisirsPanel.setLayout((new FlowLayout(FlowLayout.LEFT)));

        commentairePanel = new JPanel();
        commentairePanel.setLayout((new FlowLayout(FlowLayout.LEFT)));

        boutonsPanel = new JPanel();
        boutonsPanel.setLayout((new FlowLayout(FlowLayout.LEFT)));

        // on ajoute à chaque panel ses composants
        etatcivilPanel.add(etuComboBox);
        etatcivilPanel.add(etatcivilImageLabel);
        etatcivilPanel.add(etatcivilLabel);

        etatcivilPanel.add(etatcivilComboBox);

        nomprenomPanel.add(nomLabel);
        nomprenomPanel.add(nomTextField);
        nomprenomPanel.add(prenomLabel);
        nomprenomPanel.add(prenomTextField);

        anneePanel.add(anneeLabel);
        anneePanel.add(a1RadioButton);
        anneePanel.add(a2RadioButton);

        loisirsPanel.add(loisirsLabel);
        loisirsPanel.add(cinemaCheckBox);
        loisirsPanel.add(sportCheckBox);
        loisirsPanel.add(lectureCheckBox);

        commentairePanel.add(commentaireLabel);
        commentairePanel.add(commentaireTextArea);

        boutonsPanel.add(ajouterButton);
        boutonsPanel.add(modifierButton);
        boutonsPanel.add(supprimerButton);

        // on ajoute chaque panneau au panneau principal
        panel.add(etatcivilPanel);
        //panel.add(nomprenomPanel);
        //panel.add(anneePanel);
        //panel.add(loisirsPanel);

        //TP2, on initialise les nouveaux radio button pour le choix du semestre
        semestreLabel = new JLabel("---> Semestre :");
        semestre1RadioButton = new JRadioButton("s1");
        semestre2RadioButton = new JRadioButton("s2");
        semestre3RadioButton = new JRadioButton("s3");
        semestre4RadioButton = new JRadioButton("s4");

        semestreButtonGroup = new ButtonGroup();

        semestreButtonGroup.add(semestre1RadioButton);
        semestreButtonGroup.add(semestre2RadioButton);
        semestreButtonGroup.add(semestre3RadioButton);
        semestreButtonGroup.add(semestre4RadioButton);
        semestre1RadioButton.setVisible(false);
        semestre2RadioButton.setVisible(false);
        semestre3RadioButton.setVisible(false);
        semestre4RadioButton.setVisible(false);

        anneePanel.add(semestreLabel);
        anneePanel.add(semestre1RadioButton);
        anneePanel.add(semestre2RadioButton);
        anneePanel.add(semestre3RadioButton);
        anneePanel.add(semestre4RadioButton);

        // on crée le nouveau panel pour
        lignesrapprocheesPanel = new JPanel();
        lignesrapprocheesPanel.setLayout(new GridLayout(3, 1));

        lignesrapprocheesPanel.add(nomprenomPanel);
        lignesrapprocheesPanel.add(anneePanel);
        lignesrapprocheesPanel.add(loisirsPanel);

        panel.add(lignesrapprocheesPanel);

        panel.add(commentairePanel);
        panel.add(boutonsPanel);

        // ItemListener définit en classe anonyme, gère les évenements liés au changement de selction dans le comboBox de l'état civil
        etatcivilComboBox.addItemListener(new ItemListener() {
            /**
             * change l'image lorsque l'état civil est modifié
             * @param e
             */
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String genre;
                    switch (e.getItem().toString()) {
                        case "M.":
                            genre = "homme";
                            break;
                        case "Mme.":
                            genre = "femme";
                            break;
                        default:
                            genre = "homme";
                    }
                    ImageIcon ecImage = new ImageIcon(this.getClass().getResource("/images/" + genre + ".png"));
                    ecImage = new ImageIcon(ecImage.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
                    etatcivilImageLabel.setIcon(ecImage);
                }
            }
        });

        etuComboBox.addItemListener(new etuComboBoxListener());

        a1RadioButton.addActionListener(new annee1ActionListener());
        a2RadioButton.addActionListener(new annee2ActionListener());


        if (this.buttonEffects) {
            ajouterButton.addActionListener(new ajouterActionListener());
           // modifierButton.addMouseListener(new boutonMouseListener());
            supprimerButton.addActionListener(new supprimerActionListener());
            modifierButton.addActionListener(new modifierActionListener());
        }
    }

    /**
     * Constructeur simple
     */
    public Fenetre() {
        initComponents();
    }

    /**
     * Constructeur qui permet de définir le titre de la fenetre
     * @param titre titre de fenetre
     */
    public Fenetre(String titre) {
        initComponents();
        this.setTitle(titre);
    }

    /**
     * Constructeur qui permet de définir le titre de la fenetre et de désactiver l'effet sur les boutons
     * @param titre titre de la fenetre
     * @param buttoneffects (true) pour activer l'effet sur les boutons, (false) pour desactiver
     */
    public Fenetre(String titre, boolean buttoneffects) {

        try {
            this.setTitle(titre);
            this.buttonEffects = buttoneffects;
            dataSourceDAO = MyOracleDataSource.getOracleDataSource();
            connexionBD = dataSourceDAO.getConnection();
            etudiantDAO = new EtudiantDAOimpl(dataSourceDAO, connexionBD);

            initComponents();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Permet de "recharger" la comboBox après une supression, modification ..etc
     */
    private void loadComboBox() {
        dcm.removeAllElements();
        ArrayList<String> listePrenom = JComboBoxBD();
        listePrenom.add(0, "NOUVEAU");
        for (String el : listePrenom) {
            dcm.addElement(el);
        }
    }

    /**
     * Méthode permettant de récupérer le nom et le prenom des étudiants pour l'affichage dans la comboBox
     * @return ArrayList de string permettant l'affichage dans la comboBox des étudiants
     */
    public ArrayList<String> JComboBoxBD() {
        ArrayList<String> listePrenom = new ArrayList();
        lesEtudiants = (ArrayList<Etudiant>)etudiantDAO.getEtudiants();
        for (Etudiant e : lesEtudiants) {
            listePrenom.add(e.getPrenom() + " " + e.getNom());
        }
        return listePrenom;
    }

    /**
     * Classe interne pour les évenements sur la comboBox des étudiants
     */
    public class etuComboBoxListener implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (e.getItem() == "NOUVEAU") {
                    etatcivilComboBox.setSelectedIndex(0);
                    nomTextField.setText("");
                    prenomTextField.setText("");
                    anneeButtonGroup.clearSelection();
                    semestreButtonGroup.clearSelection();
                    semestre1RadioButton.setVisible(false);
                    semestre2RadioButton.setVisible(false);
                    semestre3RadioButton.setVisible(false);
                    semestre4RadioButton.setVisible(false);
                    cinemaCheckBox.setSelected(false);
                    sportCheckBox.setSelected(false);
                    lectureCheckBox.setSelected(false);
                    commentaireTextArea.setText("Saisir votre commentaire");
                }
                for (Etudiant etu : lesEtudiants) {
                    if (e.getItem().equals(etu.getPrenom() + " " + etu.getNom())) {
                        nomTextField.setText(etu.getNom());
                        prenomTextField.setText(etu.getPrenom());
                        commentaireTextArea.setText("Saisir votre commentaire");
                        if (etu.getEtatcivil() == "M.") {
                            etatcivilComboBox.setSelectedIndex(1);
                        } else {
                            etatcivilComboBox.setSelectedIndex(0);
                        }
                        if (etu.getAnnee() == 1) {
                            selectA1();
                        } else {
                            selectA2();
                        }
                        if (etu.getSemestre() == 1) {
                            semestre1RadioButton.setSelected(true);
                            semestre2RadioButton.setSelected(false);
                            semestre3RadioButton.setSelected(false);
                            semestre4RadioButton.setSelected(false);
                        } else if (etu.getSemestre() == 2) {
                            semestre1RadioButton.setSelected(false);
                            semestre2RadioButton.setSelected(true);
                            semestre3RadioButton.setSelected(false);
                            semestre4RadioButton.setSelected(false);
                        } else if (etu.getSemestre() == 3) {
                            semestre1RadioButton.setSelected(false);
                            semestre2RadioButton.setSelected(false);
                            semestre3RadioButton.setSelected(true);
                            semestre4RadioButton.setSelected(false);
                        } else if (etu.getSemestre() == 4) {
                            semestre1RadioButton.setSelected(false);
                            semestre2RadioButton.setSelected(false);
                            semestre3RadioButton.setSelected(false);
                            semestre4RadioButton.setSelected(true);
                        }
                        if (etu.getCinema() == 1) {
                            cinemaCheckBox.setSelected(true);
                        } else {
                            cinemaCheckBox.setSelected(false);
                        }
                        if (etu.getLecture() == 1) {
                            lectureCheckBox.setSelected(true);
                        } else {
                            lectureCheckBox.setSelected(false);
                        }
                        if (etu.getSport() == 1) {
                            sportCheckBox.setSelected(true);
                        } else {
                            sportCheckBox.setSelected(false);
                        }
                    }
                }
            }
        }
    }

    /**
     * Méthode de l'interface WindowListener
     * est exectuée lors de l'ouverture de la fenetre et rajoute "de lyon" au titre de la fenetre
     */
    public void windowOpened(WindowEvent e) {
        this.setTitle(this.getTitle() + " de Lyon");
    }

    /**
     * méthode appelée quand on selectionne la bouton radio A1
     */
    public void selectA1() {
        semestre1RadioButton.setVisible(true);
        semestre2RadioButton.setVisible(true);
        semestre3RadioButton.setVisible(false);
        semestre4RadioButton.setVisible(false);

        semestre1RadioButton.setSelected(false);
        semestre2RadioButton.setSelected(false);
        semestre3RadioButton.setSelected(false);
        semestre4RadioButton.setSelected(false);

        a1RadioButton.setSelected(true);
        a2RadioButton.setSelected(false);
    }

    /**
     * méthode appelée quand on selectionne le bouton radio A2
     */
    public void selectA2() {
        semestre1RadioButton.setVisible(false);
        semestre2RadioButton.setVisible(false);
        semestre3RadioButton.setVisible(true);
        semestre4RadioButton.setVisible(true);

        semestre1RadioButton.setSelected(false);
        semestre2RadioButton.setSelected(false);
        semestre3RadioButton.setSelected(false);
        semestre4RadioButton.setSelected(false);

        a1RadioButton.setSelected(false);
        a2RadioButton.setSelected(true);
    }

    /**
     * Classe interne pour les évenments sur la bouton radio A1
     */
    public class annee1ActionListener implements ActionListener {

        @Override
        /**
         * Selectionne l'année 2, affiche les bons semestres
         */
        public void actionPerformed(ActionEvent e) {
            selectA1();
        }
    }

    /**
     * Classe interne pour les évenements sur le bouton radio A2
     */
    public class annee2ActionListener implements ActionListener {

        @Override
        /**
         * Selectionne l'année 2, affiche les bons semestres
         */
        public void actionPerformed(ActionEvent e) {
            selectA2();
        }
    }

    /**
     * Classe interne pour les évenements sur le bouton ajouter
     */
    public class ajouterActionListener implements ActionListener {
        private JDialog dialog;

        /**
         * Permet de récupérer le bouton radio selectionné d'un button group
         * @param buttonGroup Groupe de boutons radio
         * @return le texte du bouton radio selectioné
         */
        String getSelectedButton(ButtonGroup buttonGroup)
        {
            for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
                AbstractButton button = buttons.nextElement();
                if (button.isSelected()) {
                    return button.getText();
                }
            }
            return null;
        }

        @Override
        /**
         * Permet d'ajouter un étudiant
         */
        public void actionPerformed(ActionEvent e) {
            String resEtatCivil = (String)etatcivilComboBox.getSelectedItem();
            String resPrenom = prenomTextField.getText();
            String resNom = nomTextField.getText();
            String resAnnee = this.getSelectedButton(anneeButtonGroup);
            String resSemestre = this.getSelectedButton(semestreButtonGroup);
            int resA = 0;
            int resSem = 0;
            int resCinema;
            int resSport;
            int resLecture;
            String resCommentaire = commentaireTextArea.getText();

            if (resPrenom.equals("") || resNom.equals("") || resAnnee == null || resSemestre == null || resCommentaire.equals("")) {
                dialog = new JDialog();
                JOptionPane.showMessageDialog(dialog,"ATTENTION ! Vous n'avez pas séléctionné tous les champs","ErrorDialog", JOptionPane.WARNING_MESSAGE);
            } else {
                if (a1RadioButton.isSelected()) {
                    resA = 1;
                } else {
                    resA = 2;
                }
                if (semestre1RadioButton.isSelected()) {
                    resSem = 1;
                } else if (semestre2RadioButton.isSelected()) {
                    resSem = 2;
                } else if (semestre3RadioButton.isSelected()) {
                    resSem = 3;
                } else if (semestre4RadioButton.isSelected()) {
                    resSem = 4;
                }
                if (cinemaCheckBox.isSelected())
                    resCinema = 1;
                else
                    resCinema = 0;
                if (lectureCheckBox.isSelected())
                    resLecture = 1;
                else
                    resLecture = 0;
                if (sportCheckBox.isSelected())
                    resSport = 1;
                else
                    resSport = 0;

                etudiantDAO.insertEtudiant(resNom, resPrenom, resEtatCivil, resA, resSem, resLecture, resSport, resCinema);
                JOptionPane.showMessageDialog(dialog,"Votre étudiant a bien été ajouté","MessageDialog", JOptionPane.INFORMATION_MESSAGE);
                loadComboBox();
            }
        }
    }

    /**
     * Classe interne pour les évenements sur le bouton supprimer
     */
    public class supprimerActionListener implements ActionListener {
        private JDialog dialog;
        @Override
        /**
         * Permet de supprimer un étudiant
         */
        public void actionPerformed(ActionEvent e) {
            String resNom = nomTextField.getText();
            String resPrenom = prenomTextField.getText();
            if (!etuComboBox.getSelectedItem().equals(resPrenom + " " + resNom) || !etuComboBox.getSelectedItem().equals("NOUVEAU"))
                JOptionPane.showMessageDialog(dialog,"Votre étudiant n'est pas dans la liste","ErrorDialog", JOptionPane.WARNING_MESSAGE);
            else {
                etudiantDAO.supprEtudiant(resNom, resPrenom);
                JOptionPane.showMessageDialog(dialog,"Votre étudiant a bien été supprimé","MessageDialog", JOptionPane.INFORMATION_MESSAGE);
                loadComboBox();
            }
        }
    }

    /**
     * Classe interne pour les évenements sur le bouton modfier
     */
    public class modifierActionListener implements ActionListener {
        private JDialog dialog;

        /**
         * Permet de récupérer le bouton radio selectionné d'un button group
         * @param buttonGroup Groupe de boutons radio
         * @return le texte du bouton radio selectioné
         */
        String getSelectedButton(ButtonGroup buttonGroup)
        {
            for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
                AbstractButton button = buttons.nextElement();
                if (button.isSelected()) {
                    return button.getText();
                }
            }
            return null;
        }

        @Override
        /**
         * Permet de de modifier un étudiant
         */
        public void actionPerformed(ActionEvent e) {
            String resEtatCivil = (String) etatcivilComboBox.getSelectedItem();
            String resNewPrenom = prenomTextField.getText();
            String resNewNom = nomTextField.getText();
            String resAnnee = this.getSelectedButton(anneeButtonGroup);
            String resSemestre = this.getSelectedButton(semestreButtonGroup);

            String resAll = (String)etuComboBox.getSelectedItem();

            int resA = 0;
            int resSem = 0;
            int resCinema;
            int resSport;
            int resLecture;
            String resCommentaire = commentaireTextArea.getText();

            if (resNewPrenom.equals("") || resNewNom.equals("") || resAnnee == null || resSemestre == null || resCommentaire.equals("")) {
                dialog = new JDialog();
                JOptionPane.showMessageDialog(dialog, "ATTENTION ! Vous avez rendu des champs vides", "ErrorDialog", JOptionPane.WARNING_MESSAGE);
            } else if (resAll == "NOUVEAU") {
                dialog = new JDialog();
                JOptionPane.showMessageDialog(dialog, "Vous ne pouvez pas modifer un étudiant qui n'est pas créé !", "ErrorDialog", JOptionPane.WARNING_MESSAGE);
                    } else {
                    int firstSpace = resAll.indexOf(" ");
                    String resOldNom = resAll.substring(firstSpace+1);
                    String resOldPrenom = resAll.substring(0, firstSpace);
                    if (a1RadioButton.isSelected()) {
                        resA = 1;
                    } else {
                        resA = 2;
                    }
                    if (semestre1RadioButton.isSelected()) {
                        resSem = 1;
                    } else if (semestre2RadioButton.isSelected()) {
                        resSem = 2;
                    } else if (semestre3RadioButton.isSelected()) {
                        resSem = 3;
                    } else if (semestre4RadioButton.isSelected()) {
                        resSem = 4;
                    }
                    if (cinemaCheckBox.isSelected())
                        resCinema = 1;
                    else
                        resCinema = 0;
                    if (lectureCheckBox.isSelected())
                        resLecture = 1;
                    else
                        resLecture = 0;
                    if (sportCheckBox.isSelected())
                        resSport = 1;
                    else
                        resSport = 0;

                    etudiantDAO.updateEtudiant(resOldNom, resOldPrenom, resNewNom, resNewPrenom, resEtatCivil, resA, resSem, resLecture, resSport, resCinema);
                    JOptionPane.showMessageDialog(dialog,"Votre étudiant a bien été modifié","MessageDialog", JOptionPane.INFORMATION_MESSAGE);
                    loadComboBox();
                }
        }
    }
}
