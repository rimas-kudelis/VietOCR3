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

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.vietpad.components.SimpleFilter;
import net.sourceforge.vietocr.util.FormLocalizer;
import darrylbu.plaf.StayOpenCheckBoxMenuItemUI;
import net.sourceforge.vietocr.components.OuputFormatCheckBoxActionListener;

public class OptionsDialog extends javax.swing.JDialog {
    
    private int actionSelected = -1;
    private String watchFolder;
    private String outputFolder;
    private String dangAmbigsPath;
    private String curLangCode;
    private boolean watchEnabled;
    protected ResourceBundle bundle;

    /**
     * Creates new form OptionsDialog
     */
    public OptionsDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        bundle = ResourceBundle.getBundle("net/sourceforge/vietocr/OptionsDialog");
        
        this.setLocationRelativeTo(parent);
        getRootPane().setDefaultButton(jButtonOK);

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

        jPopupMenu = new javax.swing.JPopupMenu();
        jPanelCommand = new javax.swing.JPanel();
        jButtonOK = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelDangAmbigsPath = new javax.swing.JPanel();
        jLabelDangAmbigs = new javax.swing.JLabel();
        jButtonDangAmbigs = new javax.swing.JButton();
        jTextFieldDangAmbigs = new javax.swing.JTextField();
        jCheckBoxDangAmbigs = new javax.swing.JCheckBox();
        jPanelWatchFolder = new javax.swing.JPanel();
        jLabelWatch = new javax.swing.JLabel();
        jTextFieldWatch = new javax.swing.JTextField();
        jLabelOutput = new javax.swing.JLabel();
        jTextFieldOutput = new javax.swing.JTextField();
        jPanelWatch = new javax.swing.JPanel();
        jButtonOutputFormat = new javax.swing.JButton();
        jCheckBoxWatch = new javax.swing.JCheckBox();
        jButtonWatch = new javax.swing.JButton();
        jButtonOutput = new javax.swing.JButton();
        jPanelBulkBatchOptions = new javax.swing.JPanel();
        jCheckBoxPostProcessing = new javax.swing.JCheckBox();
        jCheckBoxCorrectLetterCases = new javax.swing.JCheckBox();
        jCheckBoxDeskew = new javax.swing.JCheckBox();
        jCheckBoxRemoveLines = new javax.swing.JCheckBox();
        jCheckBoxRemoveLineBreaks = new javax.swing.JCheckBox();
        jPanelHyphens = new javax.swing.JPanel();
        jCheckBoxReplaceHyphens = new javax.swing.JCheckBox();
        jCheckBoxRemoveHyphens = new javax.swing.JCheckBox();

        for (ITesseract.RenderedFormat value : ITesseract.RenderedFormat.values()) {
            JCheckBoxMenuItem item = new JCheckBoxMenuItem(value.name());
            item.setUI(new StayOpenCheckBoxMenuItemUI());
            item.addActionListener(new OuputFormatCheckBoxActionListener());
            jPopupMenu.add(item);
        }

        jPopupMenu.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                jButtonOutputFormat.setText(jButtonOutputFormat.getText().replace('▾', '▴'));
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                jButtonOutputFormat.setText(jButtonOutputFormat.getText().replace('▴', '▾'));
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {

            }
        }) ;

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("net/sourceforge/vietocr/OptionsDialog"); // NOI18N
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

        jPanelCommand.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 0, 6, 4));
        jPanelCommand.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButtonOK.setText(bundle.getString("jButtonOK.Text")); // NOI18N
        jButtonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOKActionPerformed(evt);
            }
        });
        jPanelCommand.add(jButtonOK);

        jButtonCancel.setText(bundle.getString("jButtonCancel.Text")); // NOI18N
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });
        jPanelCommand.add(jButtonCancel);

        getContentPane().add(jPanelCommand, java.awt.BorderLayout.SOUTH);

        jPanelDangAmbigsPath.setLayout(new java.awt.GridBagLayout());

        jLabelDangAmbigs.setLabelFor(jTextFieldDangAmbigs);
        jLabelDangAmbigs.setText(bundle.getString("jLabelDangAmbigs.Text")); // NOI18N
        jPanelDangAmbigsPath.add(jLabelDangAmbigs, new java.awt.GridBagConstraints());

        jButtonDangAmbigs.setText("...");
        jButtonDangAmbigs.setToolTipText(bundle.getString("jButtonDangAmbigs.ToolTipText")); // NOI18N
        jButtonDangAmbigs.setPreferredSize(new java.awt.Dimension(30, 23));
        jButtonDangAmbigs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDangAmbigsActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanelDangAmbigsPath.add(jButtonDangAmbigs, gridBagConstraints);

        jTextFieldDangAmbigs.setEditable(false);
        jTextFieldDangAmbigs.setPreferredSize(new java.awt.Dimension(200, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        jPanelDangAmbigsPath.add(jTextFieldDangAmbigs, gridBagConstraints);

        jCheckBoxDangAmbigs.setText(bundle.getString("jCheckBoxDangAmbigs.Text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 4, 0);
        jPanelDangAmbigsPath.add(jCheckBoxDangAmbigs, gridBagConstraints);

        jTabbedPane1.addTab("DangAmbigs.txt", jPanelDangAmbigsPath);

        jPanelWatchFolder.setLayout(new java.awt.GridBagLayout());

        jLabelWatch.setLabelFor(jTextFieldWatch);
        jLabelWatch.setText(bundle.getString("jLabelWatch.Text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanelWatchFolder.add(jLabelWatch, gridBagConstraints);

        jTextFieldWatch.setEditable(false);
        jTextFieldWatch.setPreferredSize(new java.awt.Dimension(200, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        jPanelWatchFolder.add(jTextFieldWatch, gridBagConstraints);

        jLabelOutput.setLabelFor(jTextFieldOutput);
        jLabelOutput.setText(bundle.getString("jLabelOutput.Text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanelWatchFolder.add(jLabelOutput, gridBagConstraints);

        jTextFieldOutput.setEditable(false);
        jTextFieldOutput.setPreferredSize(new java.awt.Dimension(200, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        jPanelWatchFolder.add(jTextFieldOutput, gridBagConstraints);

        jPanelWatch.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jButtonOutputFormat.setText(bundle.getString("jButtonOutputFormat.Text")); // NOI18N
        jButtonOutputFormat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOutputFormatActionPerformed(evt);
            }
        });
        jPanelWatch.add(jButtonOutputFormat);

        jCheckBoxWatch.setText(bundle.getString("jCheckBoxWatch.Text")); // NOI18N
        jCheckBoxWatch.setMargin(new java.awt.Insets(2, 20, 2, 2));
        jPanelWatch.add(jCheckBoxWatch);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanelWatchFolder.add(jPanelWatch, gridBagConstraints);

        jButtonWatch.setText("...");
        jButtonWatch.setToolTipText(bundle.getString("jButtonWatch.ToolTipText")); // NOI18N
        jButtonWatch.setPreferredSize(new java.awt.Dimension(30, 23));
        jButtonWatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonWatchActionPerformed(evt);
            }
        });
        jPanelWatchFolder.add(jButtonWatch, new java.awt.GridBagConstraints());

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
        jPanelWatchFolder.add(jButtonOutput, gridBagConstraints);

        jTabbedPane1.addTab(bundle.getString("Batch"), jPanelWatchFolder); // NOI18N

        jPanelBulkBatchOptions.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, -20, 1, 1));
        jPanelBulkBatchOptions.setLayout(new java.awt.GridBagLayout());

        jCheckBoxPostProcessing.setText(bundle.getString("jCheckBoxPostProcessing.Text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 15);
        jPanelBulkBatchOptions.add(jCheckBoxPostProcessing, gridBagConstraints);

        jCheckBoxCorrectLetterCases.setText(bundle.getString("jCheckBoxCorrectLetterCases.Text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 15);
        jPanelBulkBatchOptions.add(jCheckBoxCorrectLetterCases, gridBagConstraints);

        jCheckBoxDeskew.setText(bundle.getString("jCheckBoxDeskew.Text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanelBulkBatchOptions.add(jCheckBoxDeskew, gridBagConstraints);

        jCheckBoxRemoveLines.setText(bundle.getString("jCheckBoxRemoveLines.Text")); // NOI18N
        jCheckBoxRemoveLines.setActionCommand("RemoveLines");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanelBulkBatchOptions.add(jCheckBoxRemoveLines, gridBagConstraints);

        jCheckBoxRemoveLineBreaks.setText(bundle.getString("jCheckBoxRemoveLineBreaks.Text")); // NOI18N
        jCheckBoxRemoveLineBreaks.setActionCommand("RemoveLineBreaks");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanelBulkBatchOptions.add(jCheckBoxRemoveLineBreaks, gridBagConstraints);

        jTabbedPane1.addTab("Bulk/Batch Options", jPanelBulkBatchOptions);

        jPanelHyphens.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1));
        jPanelHyphens.setLayout(new javax.swing.BoxLayout(jPanelHyphens, javax.swing.BoxLayout.Y_AXIS));

        jCheckBoxReplaceHyphens.setText(bundle.getString("jCheckBoxReplaceHyphens.Text")); // NOI18N
        jCheckBoxReplaceHyphens.setToolTipText(bundle.getString("jCheckBoxReplaceHyphens.ToolTipText")); // NOI18N
        jCheckBoxReplaceHyphens.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCheckBoxReplaceHyphens.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanelHyphens.add(jCheckBoxReplaceHyphens);
        jCheckBoxReplaceHyphens.getAccessibleContext().setAccessibleName("ReplaceHyphens");

        jCheckBoxRemoveHyphens.setText(bundle.getString("jCheckBoxRemoveHyphens.Text")); // NOI18N
        jCheckBoxRemoveHyphens.setToolTipText(bundle.getString("jCheckBoxRemoveHyphens.ToolTipText")); // NOI18N
        jPanelHyphens.add(jCheckBoxRemoveHyphens);

        jTabbedPane1.addTab(bundle.getString("Hyphens"), jPanelHyphens); // NOI18N

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);
        jTabbedPane1.getAccessibleContext().setAccessibleName("Watch");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonWatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonWatchActionPerformed
        JFileChooser filechooser = new JFileChooser();
        filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        filechooser.setAcceptAllFileFilterUsed(false);
        filechooser.setApproveButtonText(bundle.getString("Set"));
        filechooser.setCurrentDirectory(new File(watchFolder));
        filechooser.setDialogTitle(bundle.getString("Set_Watch_Folder"));
        if (filechooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            watchFolder = filechooser.getSelectedFile().getPath();
            this.jTextFieldWatch.setText(watchFolder);
        }
    }//GEN-LAST:event_jButtonWatchActionPerformed

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

    private void jButtonDangAmbigsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDangAmbigsActionPerformed
        JFileChooser pathchooser = new JFileChooser(dangAmbigsPath);
        FileFilter txtFilter = new SimpleFilter("txt", "Text");
        pathchooser.addChoosableFileFilter(txtFilter);
        pathchooser.setAcceptAllFileFilterUsed(false);
        pathchooser.setApproveButtonText(bundle.getString("Set"));
        pathchooser.setDialogTitle(bundle.getString("Path_to") + " " + curLangCode + ".DangAmbigs.txt");
        int returnVal = pathchooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String path = pathchooser.getCurrentDirectory().getPath();
            if (!dangAmbigsPath.equals(path)) {
                dangAmbigsPath = path;
                this.jTextFieldDangAmbigs.setText(dangAmbigsPath);
            }
        }
    }//GEN-LAST:event_jButtonDangAmbigsActionPerformed

    private void jButtonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOKActionPerformed
        if (this.jCheckBoxWatch.isSelected() && getSelectedOutputFormats().length() == 0) {
            JOptionPane.showMessageDialog(null, bundle.getString("Please_select_output_format"), this.getTitle(), JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        actionSelected = JOptionPane.OK_OPTION;
        this.setVisible(false);
    }//GEN-LAST:event_jButtonOKActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        actionSelected = JOptionPane.CANCEL_OPTION;
        this.setVisible(false);
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        this.jTextFieldWatch.setText(watchFolder);
        this.jTextFieldOutput.setText(outputFolder);
        this.jCheckBoxWatch.setSelected(watchEnabled);
        this.jTextFieldDangAmbigs.setText(dangAmbigsPath);
    }//GEN-LAST:event_formWindowActivated

    private void formWindowDeactivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowDeactivated
        watchFolder = this.jTextFieldWatch.getText();
        outputFolder = this.jTextFieldOutput.getText();
        watchEnabled = this.jCheckBoxWatch.isSelected();
        dangAmbigsPath = this.jTextFieldDangAmbigs.getText();
    }//GEN-LAST:event_formWindowDeactivated

    private void jButtonOutputFormatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOutputFormatActionPerformed
        jPopupMenu.show(jButtonOutputFormat, 0, jButtonOutputFormat.getHeight());
    }//GEN-LAST:event_jButtonOutputFormatActionPerformed
    
    public int showDialog() {
        setVisible(true);
        return actionSelected;
    }
    
    public void setSelectedTab(int index) {
        this.jTabbedPane1.setSelectedIndex(index);
    }
    
    void changeUILanguage(final Locale locale) {
        Locale.setDefault(locale);
        bundle = ResourceBundle.getBundle("net/sourceforge/vietocr/OptionsDialog");
        
        SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                FormLocalizer localizer = new FormLocalizer(OptionsDialog.this, OptionsDialog.class);
                localizer.ApplyCulture(bundle);
                jTabbedPane1.setTitleAt(0, bundle.getString("Batch"));
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                OptionsDialog dialog = new OptionsDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonDangAmbigs;
    private javax.swing.JButton jButtonOK;
    private javax.swing.JButton jButtonOutput;
    private javax.swing.JButton jButtonOutputFormat;
    private javax.swing.JButton jButtonWatch;
    private javax.swing.JCheckBox jCheckBoxCorrectLetterCases;
    private javax.swing.JCheckBox jCheckBoxDangAmbigs;
    private javax.swing.JCheckBox jCheckBoxDeskew;
    private javax.swing.JCheckBox jCheckBoxPostProcessing;
    private javax.swing.JCheckBox jCheckBoxRemoveHyphens;
    private javax.swing.JCheckBox jCheckBoxRemoveLineBreaks;
    private javax.swing.JCheckBox jCheckBoxRemoveLines;
    private javax.swing.JCheckBox jCheckBoxReplaceHyphens;
    private javax.swing.JCheckBox jCheckBoxWatch;
    private javax.swing.JLabel jLabelDangAmbigs;
    private javax.swing.JLabel jLabelOutput;
    private javax.swing.JLabel jLabelWatch;
    private javax.swing.JPanel jPanelBulkBatchOptions;
    private javax.swing.JPanel jPanelCommand;
    private javax.swing.JPanel jPanelDangAmbigsPath;
    private javax.swing.JPanel jPanelHyphens;
    private javax.swing.JPanel jPanelWatch;
    private javax.swing.JPanel jPanelWatchFolder;
    private javax.swing.JPopupMenu jPopupMenu;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextFieldDangAmbigs;
    private javax.swing.JTextField jTextFieldOutput;
    private javax.swing.JTextField jTextFieldWatch;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the watchFolder
     */
    public String getWatchFolder() {
        return watchFolder;
    }

    /**
     * @param watchFolder the watchFolder to set
     */
    public void setWatchFolder(String watchFolder) {
        this.watchFolder = watchFolder;
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
     * @return the watchEnabled
     */
    public boolean isWatchEnabled() {
        watchEnabled = jCheckBoxWatch.isSelected();
        return watchEnabled;
    }

    /**
     * @param watchEnabled the watchEnabled to set
     */
    public void setWatchEnabled(boolean watchEnabled) {
        this.watchEnabled = watchEnabled;
    }

    /**
     * @param curLangCode the curLangCode to set
     */
    public void setCurLangCode(String curLangCode) {
        this.curLangCode = curLangCode;
    }

    /**
     * @return the selectedFormats
     */
    public String getSelectedOutputFormats() {
        List<String> list = new ArrayList<String>();
        for (Component comp : jPopupMenu.getComponents()) {
            JCheckBoxMenuItem item = (JCheckBoxMenuItem) comp;
            if (item.isSelected()) {
                list.add(item.getText());
            }
        }
//        return list.toString().replaceAll("[\\[ \\]]", "");
        return list.stream().collect(Collectors.joining(","));
    }

    /**
     * @param selectedFormats the selectedFormats to set
     */
    public void setSelectedOutputFormats(String selectedFormats) {
        List<String> list = Arrays.asList(selectedFormats.split(","));
        for (Component comp : jPopupMenu.getComponents()) {
            JCheckBoxMenuItem item = (JCheckBoxMenuItem) comp;
            if (list.contains(item.getText())) {
                item.setSelected(true);
            }
        }
    }

    /**
     * @return the processingOptions
     */
    public ProcessingOptions getProcessingOptions() {
        ProcessingOptions processingOptions = new ProcessingOptions();
        processingOptions.setDeskew(this.jCheckBoxDeskew.isSelected());
        processingOptions.setPostProcessing(this.jCheckBoxPostProcessing.isSelected());
        processingOptions.setRemoveLines(this.jCheckBoxRemoveLines.isSelected());
        processingOptions.setRemoveLineBreaks(this.jCheckBoxRemoveLineBreaks.isSelected());
        processingOptions.setCorrectLetterCases(this.jCheckBoxCorrectLetterCases.isSelected());
        processingOptions.setRemoveHyphens(this.jCheckBoxRemoveHyphens.isSelected());
        processingOptions.setReplaceHyphens(this.jCheckBoxReplaceHyphens.isSelected());
        processingOptions.setDangAmbigsEnabled(this.jCheckBoxDangAmbigs.isSelected());
        processingOptions.setDangAmbigsPath(dangAmbigsPath);
        
        return processingOptions;
    }

    /**
     * @param processingOptions the processingOptions to set
     */
    public void setProcessingOptions(ProcessingOptions processingOptions) {
        this.jCheckBoxDeskew.setSelected(processingOptions.isDeskew());
        this.jCheckBoxPostProcessing.setSelected(processingOptions.isPostProcessing());
        this.jCheckBoxRemoveLines.setSelected(processingOptions.isRemoveLines());
        this.jCheckBoxRemoveLineBreaks.setSelected(processingOptions.isRemoveLineBreaks());
        this.jCheckBoxCorrectLetterCases.setSelected(processingOptions.isCorrectLetterCases());
        this.jCheckBoxRemoveHyphens.setSelected(processingOptions.isRemoveHyphens());
        this.jCheckBoxReplaceHyphens.setSelected(processingOptions.isReplaceHyphens());
        this.jCheckBoxDangAmbigs.setSelected(processingOptions.isDangAmbigsEnabled());
        this.dangAmbigsPath = processingOptions.getDangAmbigsPath();
    }
}
