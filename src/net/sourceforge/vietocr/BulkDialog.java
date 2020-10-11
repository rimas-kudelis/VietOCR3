/**
 * Copyright @ 2012 Quan Nguyen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package net.sourceforge.vietocr;

import java.awt.event.*;
import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.*;
import net.sourceforge.vietocr.util.FormLocalizer;

public class BulkDialog extends javax.swing.JDialog {

    private int actionSelected = -1;
    private String inputFolder;
    private String outputFolder;
    protected ResourceBundle bundle;

    /**
     * Creates new form BatchDialog
     */
    public BulkDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        bundle = ResourceBundle.getBundle("net/sourceforge/vietocr/BulkDialog");

        this.setLocationRelativeTo(parent);
        getRootPane().setDefaultButton(jButtonRun);

        //  Handle escape key to hide the dialog
        KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
        Action escapeAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        };
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
        getRootPane().getActionMap().put("ESCAPE", escapeAction);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanelImageFolder = new javax.swing.JPanel();
        jLabelInput = new javax.swing.JLabel();
        jTextFieldFolder = new javax.swing.JTextField();
        jLabelOutput = new javax.swing.JLabel();
        jTextFieldOutput = new javax.swing.JTextField();
        jLabelOutputFormat = new javax.swing.JLabel();
        jComboBoxOutputFormat = new javax.swing.JComboBox();
        jButtonInput = new javax.swing.JButton();
        jButtonOutput = new javax.swing.JButton();
        jPanelCommand = new javax.swing.JPanel();
        jButtonRun = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        jButtonCancel = new javax.swing.JButton();
        jButtonOptions = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("net/sourceforge/vietocr/BulkDialog"); // NOI18N
        setTitle(bundle.getString("this.Title")); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowDeactivated(java.awt.event.WindowEvent evt) {
                formWindowDeactivated(evt);
            }
        });

        jPanelImageFolder.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 10));
        jPanelImageFolder.setLayout(new java.awt.GridBagLayout());

        jLabelInput.setLabelFor(jTextFieldFolder);
        jLabelInput.setText(bundle.getString("jLabelInput.Text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanelImageFolder.add(jLabelInput, gridBagConstraints);

        jTextFieldFolder.setEditable(false);
        jTextFieldFolder.setPreferredSize(new java.awt.Dimension(200, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        jPanelImageFolder.add(jTextFieldFolder, gridBagConstraints);

        jLabelOutput.setLabelFor(jTextFieldOutput);
        jLabelOutput.setText(bundle.getString("jLabelOutput.Text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanelImageFolder.add(jLabelOutput, gridBagConstraints);

        jTextFieldOutput.setEditable(false);
        jTextFieldOutput.setPreferredSize(new java.awt.Dimension(200, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        jPanelImageFolder.add(jTextFieldOutput, gridBagConstraints);

        jLabelOutputFormat.setLabelFor(jComboBoxOutputFormat);
        jLabelOutputFormat.setText(bundle.getString("jComboBoxOutputFormat.Text")); // NOI18N
        jLabelOutputFormat.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanelImageFolder.add(jLabelOutputFormat, gridBagConstraints);
        jLabelOutputFormat.getAccessibleContext().setAccessibleName("jLabelOutputFormat");

        jComboBoxOutputFormat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "text", "hocr", "pdf" }));
        jComboBoxOutputFormat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jComboBoxOutputFormatMouseEntered(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanelImageFolder.add(jComboBoxOutputFormat, gridBagConstraints);

        jButtonInput.setText("...");
        jButtonInput.setToolTipText(bundle.getString("jButtonInput.ToolTipText")); // NOI18N
        jButtonInput.setPreferredSize(new java.awt.Dimension(30, 23));
        jButtonInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInputActionPerformed(evt);
            }
        });
        jPanelImageFolder.add(jButtonInput, new java.awt.GridBagConstraints());

        jButtonOutput.setText("...");
        jButtonOutput.setToolTipText(bundle.getString("jButtonOutput.ToolTipText")); // NOI18N
        jButtonOutput.setPreferredSize(new java.awt.Dimension(30, 23));
        jButtonOutput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOutputActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        jPanelImageFolder.add(jButtonOutput, gridBagConstraints);

        jPanelCommand.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 0, 5));

        jButtonRun.setText(bundle.getString("jButtonRun.Text")); // NOI18N
        jButtonRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRunActionPerformed(evt);
            }
        });
        jPanelCommand.add(jButtonRun);
        jPanelCommand.add(filler1);

        jButtonCancel.setText(bundle.getString("jButtonCancel.Text")); // NOI18N
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });
        jPanelCommand.add(jButtonCancel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 6, 0);
        jPanelImageFolder.add(jPanelCommand, gridBagConstraints);

        jButtonOptions.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/fatcow/icons/tools.png"))); // NOI18N
        jButtonOptions.setToolTipText("Options");
        jButtonOptions.setBorder(null);
        jButtonOptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOptionsActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        jPanelImageFolder.add(jButtonOptions, gridBagConstraints);

        getContentPane().add(jPanelImageFolder, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInputActionPerformed
        JFileChooser filechooser = new JFileChooser();
        filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        filechooser.setAcceptAllFileFilterUsed(false);
        filechooser.setApproveButtonText(bundle.getString("Set"));
        filechooser.setCurrentDirectory(new File(inputFolder));
        filechooser.setDialogTitle(bundle.getString("Set_Image_Folder"));
        if (filechooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            inputFolder = filechooser.getSelectedFile().getPath();
            this.jTextFieldFolder.setText(inputFolder);
        }
    }//GEN-LAST:event_jButtonInputActionPerformed

    private void jButtonOutputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOutputActionPerformed
        JFileChooser filechooser = new JFileChooser();
        filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        filechooser.setAcceptAllFileFilterUsed(false);
        filechooser.setApproveButtonText(bundle.getString("Set"));
        filechooser.setCurrentDirectory(new File(outputFolder));
        filechooser.setDialogTitle(bundle.getString("Set_Output_Folder"));
        if (filechooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            outputFolder = filechooser.getSelectedFile().getPath();
            this.jTextFieldOutput.setText(outputFolder);
        }
    }//GEN-LAST:event_jButtonOutputActionPerformed

    private void jButtonRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRunActionPerformed
        actionSelected = JOptionPane.OK_OPTION;
        this.setVisible(false);
    }//GEN-LAST:event_jButtonRunActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        actionSelected = JOptionPane.CANCEL_OPTION;
        this.setVisible(false);
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        this.jTextFieldFolder.setText(inputFolder);
        this.jTextFieldOutput.setText(outputFolder);
    }//GEN-LAST:event_formWindowActivated

    private void formWindowDeactivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowDeactivated
        inputFolder = this.jTextFieldFolder.getText();
        outputFolder = this.jTextFieldOutput.getText();
    }//GEN-LAST:event_formWindowDeactivated

    private void jComboBoxOutputFormatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxOutputFormatMouseEntered
        String val = this.jComboBoxOutputFormat.getSelectedItem().toString();
        if ("text".equals(val)) {
            val = "Text with no postprocessing";
        } else if ("pdf".equals(val)) {
            val = "PDF";
        } else if ("hocr".equals(val)) {
            val = "hOCR";
        } else {
            val = null;
        }
        this.jComboBoxOutputFormat.setToolTipText(val);
    }//GEN-LAST:event_jComboBoxOutputFormatMouseEntered

    private void jButtonOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOptionsActionPerformed
        ((GuiWithBulkOCR)this.getParent()).jMenuItemOptionsActionPerformed(evt);
    }//GEN-LAST:event_jButtonOptionsActionPerformed

    public int showDialog() {
        setVisible(true);
        return actionSelected;
    }

    void changeUILanguage(final Locale locale) {
        Locale.setDefault(locale);
        bundle = ResourceBundle.getBundle("net/sourceforge/vietocr/BulkDialog");

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FormLocalizer localizer = new FormLocalizer(BulkDialog.this, BulkDialog.class);
                localizer.ApplyCulture(bundle);
            }
        });
    }
    
      /**
     * @return the inputFolder
     */
    public String getInputFolder() {
        return inputFolder;
    }

    /**
     * @param inputFolder the inputFolder to set
     */
    public void setInputFolder(String inputFolder) {
        this.inputFolder = inputFolder;
    }

    /**
     * @return the outputFolder
     */
    public String getOutputFolder() {
        return outputFolder;
    }

    /**
     * @param outputFolder the outputFolder to set
     */
    public void setOutputFolder(String outputFolder) {
        this.outputFolder = outputFolder;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BulkDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BulkDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BulkDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BulkDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                BulkDialog dialog = new BulkDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonInput;
    private javax.swing.JButton jButtonOptions;
    private javax.swing.JButton jButtonOutput;
    private javax.swing.JButton jButtonRun;
    private javax.swing.JComboBox jComboBoxOutputFormat;
    private javax.swing.JLabel jLabelInput;
    private javax.swing.JLabel jLabelOutput;
    private javax.swing.JLabel jLabelOutputFormat;
    private javax.swing.JPanel jPanelCommand;
    private javax.swing.JPanel jPanelImageFolder;
    private javax.swing.JTextField jTextFieldFolder;
    private javax.swing.JTextField jTextFieldOutput;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the selectedFormat
     */
    public String getSelectedOutputFormat() {
        return this.jComboBoxOutputFormat.getSelectedItem().toString();
    }

    /**
     * @param selectedFormat the selectedFormat to set
     */
    public void setSelectedOutputFormat(String selectedFormat) {
        this.jComboBoxOutputFormat.setSelectedItem(selectedFormat);
    }
}
