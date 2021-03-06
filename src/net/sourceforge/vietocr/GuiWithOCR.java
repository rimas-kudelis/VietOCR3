/**
 * Copyright @ 2008 Quan Nguyen
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

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.IIOImage;
import javax.swing.*;

import net.sourceforge.vietocr.components.JImageLabel;

public class GuiWithOCR extends GuiWithImageOps {

    private OcrWorker ocrWorker;
    protected String selectedPSM = "3"; // 3 - Fully automatic page segmentation, but no OSD (default)
    protected String selectedOEM = "3"; // Default, based on what is available
    protected boolean tessLibEnabled;

    private final static Logger logger = Logger.getLogger(GuiWithOCR.class.getName());

    @Override
    void jMenuItemOCRActionPerformed(java.awt.event.ActionEvent evt) {
        if (jImageLabel.getIcon() == null) {
            JOptionPane.showMessageDialog(this, bundle.getString("Please_load_an_image."), APP_NAME, JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        Rectangle rect = ((JImageLabel) jImageLabel).getRect();

        if (rect != null) {
            try {
                ImageIcon ii = (ImageIcon) this.jImageLabel.getIcon();
                int offsetX = 0;
                int offsetY = 0;
                if (ii.getIconWidth() < this.jScrollPaneImage.getWidth()) {
                    offsetX = (this.jScrollPaneImage.getViewport().getWidth() - ii.getIconWidth()) / 2;
                }
                if (ii.getIconHeight() < this.jScrollPaneImage.getHeight()) {
                    offsetY = (this.jScrollPaneImage.getViewport().getHeight() - ii.getIconHeight()) / 2;
                }
//                BufferedImage bi = ((BufferedImage) ii.getImage()).getSubimage((int) ((rect.x - offsetX) * scaleX), (int) ((rect.y - offsetY) * scaleY), (int) (rect.width * scaleX), (int) (rect.height * scaleY));

//                // create a new rectangle with scale factors and offets factored in
                rect = new Rectangle((int) ((rect.x - offsetX) * scaleX), (int) ((rect.y - offsetY) * scaleY), (int) (rect.width * scaleX), (int) (rect.height * scaleY));

                //move this part to the image entity
//                ArrayList<IIOImage> tempList = new ArrayList<IIOImage>();
//                tempList.add(new IIOImage(bi, null, null));
                performOCR(iioImageList, inputfilename, imageIndex, rect);
            } catch (RasterFormatException rfe) {
                logger.log(Level.SEVERE, rfe.getMessage(), rfe);
                JOptionPane.showMessageDialog(this, rfe.getMessage(), APP_NAME, JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        } else {
            performOCR(iioImageList, inputfilename, imageIndex, null);
        }
    }

    @Override
    void jMenuItemOCRAllActionPerformed(java.awt.event.ActionEvent evt) {
        if (this.jImageLabel.getIcon() == null) {
            JOptionPane.showMessageDialog(this, bundle.getString("Please_load_an_image."), APP_NAME, JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        this.jButtonOCR.setVisible(false);
        this.jButtonCancelOCR.setVisible(true);
        this.jButtonCancelOCR.setEnabled(true);
        performOCR(iioImageList, inputfilename, -1, null);
    }

    /**
     * Perform OCR on images represented by IIOImage.
     *
     * @param iioImageList list of IIOImage
     * @param inputfilename input filename
     * @param index Index of page to be OCRed: -1 for all pages
     * @param rect region of interest
     */
    void performOCR(final List<IIOImage> iioImageList, String inputfilename, final int index, Rectangle rect) {
        if (curLangCode.trim().length() == 0) {
            JOptionPane.showMessageDialog(this, bundle.getString("Please_select_a_language."), APP_NAME, JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        jLabelStatus.setText(bundle.getString("OCR_running..."));
        jProgressBar1.setIndeterminate(true);
        jProgressBar1.setString(bundle.getString("OCR_running..."));
        jProgressBar1.setVisible(true);
        getGlassPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        getGlassPane().setVisible(true);
        this.jButtonOCR.setEnabled(false);
        this.jMenuItemOCR.setEnabled(false);
        this.jMenuItemOCRAll.setEnabled(false);

        OCRImageEntity entity = new OCRImageEntity(iioImageList, inputfilename, index, rect, this.jCheckBoxMenuItemDoubleSidedPage.isSelected(), curLangCode);
        entity.setScreenshotMode(this.jCheckBoxMenuItemScreenshotMode.isSelected());

        // instantiate SwingWorker for OCR
        ocrWorker = new OcrWorker(entity);
        ocrWorker.execute();
    }

    @Override
    void jButtonCancelOCRActionPerformed(java.awt.event.ActionEvent evt) {
        if (ocrWorker != null && !ocrWorker.isDone()) {
            // Cancel current OCR op to begin a new one. You want only one OCR op at a time.
            ocrWorker.cancel(true);
            ocrWorker = null;
        }

        this.jButtonCancelOCR.setEnabled(false);
    }

    /**
     * A worker class for managing OCR process.
     */
    class OcrWorker extends SwingWorker<Void, String> {

        OCRImageEntity entity;
        List<File> workingFiles;
        List<IIOImage> imageList; // Option for Tess4J

        OcrWorker(OCRImageEntity entity) {
            this.entity = entity;
        }

        @Override
        protected Void doInBackground() throws Exception {
            String lang = entity.getLanguage();
            OCR<IIOImage> ocrEngine = new OCRImages(); // for Tess4J
            ocrEngine.setDatapath(datapath);
            ocrEngine.setPageSegMode(selectedPSM);
            ocrEngine.setLanguage(lang);
            imageList = entity.getSelectedOimages();

            for (int i = 0; i < imageList.size(); i++) {
                if (!isCancelled()) {
                    String result = ocrEngine.recognizeText(imageList.subList(i, i + 1), entity.getInputfilename(), entity.getRect());
                    publish(result); // interim result
                }
            }

            return null;
        }

        @Override
        protected void process(List<String> results) {
            for (String str : results) {
                jTextArea1.append(str);
                jTextArea1.setCaretPosition(jTextArea1.getDocument().getLength());
            }
        }

        @Override
        protected void done() {
            jProgressBar1.setIndeterminate(false);

            try {
                get(); // dummy method
                jLabelStatus.setText(bundle.getString("OCR_completed."));
                jProgressBar1.setString(bundle.getString("OCR_completed."));
            } catch (InterruptedException ignore) {
                logger.log(Level.WARNING, ignore.getMessage(), ignore);
            } catch (java.util.concurrent.ExecutionException e) {
                String why;
                Throwable cause = e.getCause();
                if (cause != null) {
                    if (cause instanceof IOException) {
                        why = bundle.getString("Cannot_find_Tesseract._Please_set_its_path.");
                    } else if (cause instanceof FileNotFoundException) {
                        why = bundle.getString("An_exception_occurred_in_Tesseract_engine_while_recognizing_this_image.");
                    } else if (cause instanceof OutOfMemoryError) {
                        why = cause.getMessage();
                    } else if (cause instanceof ClassCastException) {
                        why = cause.getMessage();
                        why += "\nConsider converting the image to binary or grayscale before OCR again.";
                    } else {
                        why = cause.getMessage();
                    }
                } else {
                    why = e.getMessage();
                }

                logger.log(Level.SEVERE, why, e);
                jLabelStatus.setText(null);
                jProgressBar1.setString(null);
                JOptionPane.showMessageDialog(null, why, "OCR Operation", JOptionPane.ERROR_MESSAGE);
            } catch (java.util.concurrent.CancellationException e) {
                logger.log(Level.WARNING, e.getMessage(), e);
                jLabelStatus.setText("OCR " + bundle.getString("canceled"));
                jProgressBar1.setString("OCR " + bundle.getString("canceled"));
            } finally {
                getGlassPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                getGlassPane().setVisible(false);
                jButtonOCR.setVisible(true);
                jButtonOCR.setEnabled(true);
                jMenuItemOCR.setEnabled(true);
                jMenuItemOCRAll.setEnabled(true);
                jButtonCancelOCR.setVisible(false);

                // clean up temporary image files
                if (workingFiles != null) {
                    for (File tempImageFile : workingFiles) {
                        tempImageFile.delete();
                    }
                }
            }
        }
    }
}
