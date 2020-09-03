package com.rizkykhapidsyah;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FormUtama extends JFrame {

    JPanel PanelUtama;
    JButton TombolBuka;

    public FormUtama() {
        super("Preview Image File Chooser");
        Inisialisasi_Komponen();
    }

    private void Inisialisasi_Komponen() {
        PanelUtama = new JPanel();
        TombolBuka = new JButton("Buka Gambar");
        TombolBuka.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent ae) {
                bukaJFileChooser();
            }
        });

        PanelUtama.add(TombolBuka);

        getContentPane().add(PanelUtama, BorderLayout.CENTER);
        setPreferredSize(new Dimension(300, 100));
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void bukaJFileChooser() {
        JFileChooser JFC = new JFileChooser();

        JFC.setAccessory(new ImagePreviewComponent(JFC));
        JFC.showOpenDialog(this);
    }
}
