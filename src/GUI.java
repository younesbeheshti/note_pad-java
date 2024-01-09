import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {


    JFrame window;
    JTextArea textArea;
    JScrollPane scrollPane;
    boolean isWordWrapOn = false;
    JMenuBar menuBar;
    JMenu menuFile, menuEdit, menuFormat, menuColor;
    JMenuItem iNew, iOpen, iSave, iSaveAs, iExit;
    JMenuItem iUndo, iRedo;
    JMenuItem iWrap, iFontArial, iFontCSMS, iFontTNR, iFontSize8, iFontSize12, iFontSize20, iFontSize24, iFontSize28;
    JMenuItem iColor1, iColor2;
    JMenu menuFont, menuFontSize;
    Function_File file = new Function_File(this);
    Function_Format format = new Function_Format(this);
    Function_Color color = new Function_Color(this);
    Function_Edit edit = new Function_Edit(this);

    public static void main(String[] args) {
        new GUI();
    }

    public GUI() {
        createWindow();
        createTextArea();
        createMenuBar();
        createFileMenu();
        createEditMenu();
        createFormatMenu();
        createColorMenu();
        format.selectedFont = "Arial";
        format.createFont(16);
        format.wordWrap();
        color.changeColor("White");
        window.setVisible(true);
    }

    public void createWindow(){
        window = new JFrame("Note Pad");
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createTextArea() {
        textArea = new JTextArea();
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                edit.addToUndoStack(textArea.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                edit.addToUndoStack(textArea.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                edit.addToUndoStack(textArea.getText());
            }
        });

        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        window.add(scrollPane);
    }

    public void createMenuBar() {
        menuBar = new JMenuBar();

        menuFile = new JMenu("File");
        menuBar.add(menuFile);

        menuEdit = new JMenu("Edit");
        menuBar.add(menuEdit);

        menuFormat = new JMenu("Format");
        menuBar.add(menuFormat);

        menuColor = new JMenu("Color");
        menuBar.add(menuColor);

        window.setJMenuBar(menuBar);
    }

    public void createFileMenu() {

        iNew = new JMenuItem("New");
        iNew.addActionListener(this);
        iNew.setActionCommand("New");
        menuFile.add(iNew);

        iOpen = new JMenuItem("Open");
        iOpen.addActionListener(this);
        iOpen.setActionCommand("Open");
        menuFile.add(iOpen);

        iSave = new JMenuItem("Save");
        iSave.addActionListener(this);
        iSave.setActionCommand("Save");
        menuFile.add(iSave);

        iSaveAs = new JMenuItem("Save As...");
        iSaveAs.addActionListener(this);
        iSaveAs.setActionCommand("SaveAs");
        menuFile.add(iSaveAs);

        iExit = new JMenuItem("Exit");
        iExit.addActionListener(this);
        iExit.setActionCommand("Exit");
        menuFile.add(iExit);

    }

    public void createEditMenu() {

        iUndo =new JMenuItem("Undo");
        iUndo.addActionListener(this);
        iUndo.setActionCommand("Undo");
        menuEdit.add(iUndo);

        iRedo =new JMenuItem("Redo");
        iRedo.addActionListener(this);
        iRedo.setActionCommand("Redo");
        menuEdit.add(iRedo);

    }
    public void createFormatMenu() {

        iWrap = new JMenuItem("Word Wrap : Off");
        iWrap.addActionListener(this);
        iWrap.setActionCommand("Word Wrap");
        menuFormat.add(iWrap);

        menuFont = new JMenu("Font");
        menuFormat.add(menuFont);

        menuFontSize = new JMenu("Font Size");
        menuFormat.add(menuFontSize);

        iFontArial = new JMenuItem("Arial");
        iFontArial.addActionListener(this);
        iFontArial.setActionCommand("Arial");
        menuFont.add(iFontArial);

        iFontCSMS = new JMenuItem("Comic Sans MS");
        iFontCSMS.addActionListener(this);
        iFontCSMS.setActionCommand("Comic Sans MS");
        menuFont.add(iFontCSMS);

        iFontTNR = new JMenuItem("Times New Roman");
        iFontTNR.addActionListener(this);
        iFontTNR.setActionCommand("Times New Roman");
        menuFont.add(iFontTNR);

        iFontSize8 = new JMenuItem("8");
        iFontSize8.addActionListener(this);
        iFontSize8.setActionCommand("Size8");
        menuFontSize.add(iFontSize8);

        iFontSize12 = new JMenuItem("12");
        iFontSize12.addActionListener(this);
        iFontSize12.setActionCommand("Size12");
        menuFontSize.add(iFontSize12);

        iFontSize20 = new JMenuItem("20");
        iFontSize20.addActionListener(this);
        iFontSize20.setActionCommand("Size20");
        menuFontSize.add(iFontSize20);

        iFontSize24 = new JMenuItem("24");
        iFontSize24.addActionListener(this);
        iFontSize24.setActionCommand("Size24");
        menuFontSize.add(iFontSize24);

        iFontSize28 = new JMenuItem("28");
        iFontSize28.addActionListener(this);
        iFontSize28.setActionCommand("Size28");
        menuFontSize.add(iFontSize28);
    }

    public void createColorMenu() {

        iColor1 = new JMenuItem("White");
        iColor1.addActionListener(this);
        iColor1.setActionCommand("White");
        menuColor.add(iColor1);

        iColor2 = new JMenuItem("Black");
        iColor2.addActionListener(this);
        iColor2.setActionCommand("Black");
        menuColor.add(iColor2);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();

        switch (command) {
            case "New" : {
                file.newFile();
                break;
            }
            case "Open" : {
                file.open();
                break;
            }
            case "Save" : {
                file.save();
                break;
            }
            case "SaveAs" : {
                file.saveAs();
                break;
            }
            case "Exit" : {
                file.exit();
                break;
            }
            case "Undo" : {
                edit.undo();
                break;
            }
            case "Redo" : {
                edit.redo();
                break;
            }
            case "Word Wrap" : {
                format.wordWrap();
                break;
            }
            case "Arial", "Comic Sans MS", "Times New Roman" : {
                format.setFont(command);
                break;
            }
            case "Size8" : {
                format.createFont(8);
                break;
            }
            case "Size12" : {
                format.createFont(12);
                break;
            }
            case "Size20" : {
                format.createFont(20);
                break;
            }
            case "Size24" : {
                format.createFont(24);
                break;
            }
            case "Size28" : {
                format.createFont(28);
                break;
            }
            case "White", "Black" : {
                color.changeColor(command);
                break;
            }
        }
    }
}