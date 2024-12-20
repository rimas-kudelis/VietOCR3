/**
 * Copyright @ 2010 Quan Nguyen
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
package net.sourceforge.vietpad.utilities;

import java.awt.Color;
import java.io.*;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;

import net.sourceforge.vietocr.util.Utils;

import com.stibocatalog.hunspell.Hunspell;
import com.sun.jna.Platform;
import java.nio.charset.StandardCharsets;

public class SpellCheckHelper {

    private static final String JNA_LIBRARY_PATH = "jna.library.path";
    JTextComponent textComp;
    // define the highlighter
    Highlighter.HighlightPainter myPainter = new WavyLineHighlighter(Color.red);
    String localeId;
    File baseDir;
    static List<DocumentListener> lstList = new ArrayList<>();
    Hunspell.Dictionary spellDict;
    static List<String> userWordList = new ArrayList<>();
    static long mapLastModified = Long.MIN_VALUE;

    private final static Logger logger = Logger.getLogger(SpellCheckHelper.class.getName());

    /**
     * Constructor.
     *
     * @param textComp
     * @param localeId
     */
    public SpellCheckHelper(JTextComponent textComp, String localeId) {
        this.textComp = textComp;
        this.localeId = localeId;
        baseDir = Utils.getBaseDir(SpellCheckHelper.this);
    }

    public boolean initializeSpellCheck() {
        if (localeId == null) {
            return false;
        }

        try {
            if (Platform.isWindows()) {
                String hunspellDllLocation = baseDir.getPath() + "/lib/" + Platform.RESOURCE_PREFIX;
                System.setProperty(JNA_LIBRARY_PATH, hunspellDllLocation);
            }
            String dictPath = new File(baseDir, "dict/" + localeId).getPath();
            spellDict = Hunspell.getInstance().getDictionary(dictPath);
            if (!loadUserDictionary()) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * Enables spellcheck.
     */
    public void enableSpellCheck() throws Exception {
        if (!initializeSpellCheck()) {
            throw new Exception("Spellcheck initialization error!");
        }

        SpellcheckDocumentListener docListener = new SpellcheckDocumentListener();
        lstList.add(docListener);
        this.textComp.getDocument().addDocumentListener(docListener);
        spellCheck();
    }

    /**
     * Disables spellcheck.
     */
    public void disableSpellCheck() {
        if (lstList.size() > 0) {
            this.textComp.getDocument().removeDocumentListener(lstList.remove(0));
            this.textComp.getHighlighter().removeAllHighlights();
        }
    }

    /**
     * Spellchecks.
     */
    public void spellCheck() {
        Highlighter hi = textComp.getHighlighter();
        hi.removeAllHighlights();

        List<String> words = parseText(textComp.getText());
        List<String> misspelledWords = spellCheck(words);
        if (misspelledWords.isEmpty()) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (String word : misspelledWords) {
            sb.append(word).append("|");
        }
        sb.setLength(sb.length() - 1); //remove last |

        // build regex
        String patternStr = "\\b(" + sb.toString() + ")\\b";

        Pattern pattern = Pattern.compile(patternStr, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(textComp.getText());

        while (matcher.find()) {
            try {
                hi.addHighlight(matcher.start(), matcher.end(), myPainter);
            } catch (BadLocationException ex) {
//                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean isMispelled(String word) {
        if (spellDict.misspelled(word)) {
            if (!userWordList.contains(word.toLowerCase())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Spellchecks list of words.
     *
     * @param words
     * @return
     */
    List<String> spellCheck(List<String> words) {
        List<String> misspelled = new ArrayList<>();

        for (String word : words) {
            if (isMispelled(word)) {
                misspelled.add(word);
            }
        }

        return misspelled;
    }

    /**
     * Parses input text.
     *
     * @param text
     * @return
     */
    List<String> parseText(String text) {
        List<String> words = new ArrayList<>();
        BreakIterator boundary = BreakIterator.getWordInstance();
        boundary.setText(text);
        int start = boundary.first();
        for (int end = boundary.next(); end != BreakIterator.DONE; start = end, end = boundary.next()) {
            if (!Character.isLetter(text.charAt(start))) {
                continue;
            }
            words.add(text.substring(start, end));
        }

        return words;
    }

    /**
     * Suggests words.
     *
     * @param misspelled
     * @return
     */
    public List<String> suggest(String misspelled) {
        List<String> list = new ArrayList<>();
        list.add(misspelled);
        if (spellCheck(list).isEmpty()) {
            return null;
        } else {
            try {
                return spellDict.suggest(misspelled);
            } catch (Exception e) {
                logger.log(Level.WARNING, e.getMessage(), e);
                return null;
            }
        }
    }

    /**
     * Ignores word.
     *
     * @param word
     */
    public void ignoreWord(String word) {
        if (!userWordList.contains(word.toLowerCase())) {
            userWordList.add(word.toLowerCase());
        }
    }

    /**
     * Adds word to user dictionary.
     *
     * @param word
     */
    public void addWord(String word) {
        if (!userWordList.contains(word.toLowerCase())) {
            userWordList.add(word.toLowerCase());
            try {
                File userDict = new File(baseDir, "dict/user.dic");
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(userDict, true), StandardCharsets.UTF_8));
                out.write(word);
                out.newLine();
                out.close();
            } catch (IOException e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        }
    }

    /**
     * Loads user dictionary only once.
     */
    boolean loadUserDictionary() {
        try {
            File userDict = new File(baseDir, "dict/user.dic");
            long fileLastModified = userDict.lastModified();

            if (fileLastModified <= mapLastModified) {
                return true;// no need to reload dictionary
            }

            mapLastModified = fileLastModified;
            userWordList.clear();
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(userDict), StandardCharsets.UTF_8));
            String str;
            while ((str = in.readLine()) != null) {
                userWordList.add(str.toLowerCase());
            }
            in.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    class SpellcheckDocumentListener implements DocumentListener {

        @Override
        public void removeUpdate(DocumentEvent e) {
            spellCheck();
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            spellCheck();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
        }
    }
}
