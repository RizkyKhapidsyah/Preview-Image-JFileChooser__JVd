package com.rizkykhapidsyah;

/* Created by Rizky Khapidsyah */

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

public class ImagePreviewComponent extends JComponent implements PropertyChangeListener {

    ImageIcon imageKecil = null;
    File berkas = null;
    Double kotakLebar = 100.0;
    Double kotakTinggi = 100.0;

    public ImagePreviewComponent(JFileChooser fc) {
        setPreferredSize(new Dimension(kotakLebar.intValue(), kotakTinggi.intValue()));
        fc.addPropertyChangeListener(this);
    }

    public double dapatkanSkalaTerbaik(ImageIcon iconGambar) {
        double skalaTerbaik;

        if ((kotakLebar >= iconGambar.getIconWidth()) && (kotakTinggi >= iconGambar.getIconHeight())) {
            skalaTerbaik = 1;
        } else {
            double skalaLebar = kotakLebar / iconGambar.getIconWidth();
            double skalaTinggi = kotakTinggi / iconGambar.getIconHeight();

            if (skalaLebar > skalaTinggi) {
                skalaTerbaik = skalaTinggi;
            } else {
                skalaTerbaik = skalaLebar;
            }
        }

        return skalaTerbaik;
    }

    @Override
    public void propertyChange(PropertyChangeEvent pCe) {
        String p = pCe.getPropertyName();

        if (JFileChooser.DIRECTORY_CHANGED_PROPERTY.equals(p)) {
            berkas = null;
        } else if (JFileChooser.SELECTED_FILE_CHANGED_PROPERTY.equals(p)) {
            berkas = (File) pCe.getNewValue();
        }

        if (berkas == null) {
            imageKecil = null;
        } else {
            ImageIcon iconGambar = new ImageIcon(berkas.getPath());
            double skalaTerbaik2 = dapatkanSkalaTerbaik(iconGambar);
            imageKecil = new ImageIcon(
                    iconGambar.getImage().getScaledInstance(
                            (int) ((double) iconGambar.getIconWidth() * skalaTerbaik2),
                            (int) ((double) iconGambar.getIconHeight() * skalaTerbaik2),
                            Image.SCALE_DEFAULT));
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (imageKecil != null) {
            int x = getWidth() / 2 - imageKecil.getIconWidth() / 2;
            int y = getHeight() / 2 - imageKecil.getIconHeight() / 2;
            imageKecil.paintIcon(this, g, x, y);
        }
    }
}
