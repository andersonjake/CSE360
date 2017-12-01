import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.*;


public class Main {

    public static void main(String[] args) {
        new Layout();
    }


}

class Layout {

    // ------ DECLARE ALL OF THE THINGS! ------

    JFrame outter_container_frame;


    JPanel content_panel = new JPanel();
    JPanel button_panel = new JPanel();

    JPanel title_panel = new JPanel();
    JTextArea left_text_area = new JTextArea();
    JTextArea right_text_area = new JTextArea();
    JTextArea middle_text_area = new JTextArea();

    JScrollPane left_scroll = new JScrollPane(left_text_area);
    JScrollPane right_scroll = new JScrollPane(right_text_area);
    JScrollPane middle_scroll = new JScrollPane(middle_text_area);

    String left_content, right_content, middle_content, file_content = "No Document Selected.",
            current_most_common_word = "n/a", historic_most_common_word = "n/a";

    int current_number_of_lines = 0, current_number_of_blank_lines = 0,
            current_number_of_spaces = 0, current_number_of_words = 0,
            current_average_characters_per_line = 0, current_average_word_length = 0,
            current_repeated_word_occurences = 0;

    int historic_number_of_lines = 0, historic_number_of_blank_lines = 0,
            historic_number_of_spaces = 0, historic_number_of_words = 0,
            historic_average_characters_per_line = 0, historic_average_word_length = 0,
            historic_repeated_word_occurences = 0;



    Border empty_border = BorderFactory.createEmptyBorder();
    Border black_line_border = BorderFactory.createLineBorder(Color.black);
    Border left = BorderFactory.createTitledBorder(empty_border, "Current File Statistics:");
    Border right = BorderFactory.createTitledBorder(empty_border, "File Contents:");
    Border middle = BorderFactory.createTitledBorder(empty_border, "Average File Statistics:");

    Font font = new Font("Calibri", Font.PLAIN, 14);


    // Sets up the layout for the applet
    Layout() {

        outter_container_frame = new JFrame("Text File Analyzer");


        // ------ Title Panel ------

        String title = "<html><h1>CSE 360 Text File Analyzer: </h1>"
                + "<html><h3>Created by: Dylan Brabec, Branden Phillips, and Jacob Anderson </h3>";
        JLabel label = new JLabel(title);
        label.setBounds(0, 10, 500, 40);
        title_panel.add(label);
        title_panel.setBounds(50, 10, 500, 100);


        // ------ Content Panel ------

        // Left Side
        left_scroll.setBounds(25, 0, 300, 400);
        left_scroll.setBorder(left);

        left_text_area.setBackground(Color.white);
        left_text_area.setEditable(false);
        left_text_area.setFont(font);


        // Center
        middle_scroll.setBounds(350, 0, 300, 400);
        middle_scroll.setBorder(middle);

        middle_text_area.setBackground(Color.white);
        middle_text_area.setEditable(false);
        middle_text_area.setFont(font);

        // Right Side
        right_scroll.setBounds(675, 0, 300, 400);
        right_scroll.setBorder(right);

        right_text_area.setBackground(Color.white);
        right_text_area.setEditable(false);
        right_text_area.setFont(font);



        content_panel.setBounds(0, 110, 1000, 400);
        content_panel.setBorder(empty_border);
        content_panel.setLayout(null);
        content_panel.add(left_scroll);
        content_panel.add(middle_scroll);
        content_panel.add(right_scroll);


        // ------ Button Panel ------

        JButton choose_file_bt = new JButton("Choose File");
        choose_file_bt.setBounds(780, 40, 40, 30);
        choose_file_bt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Stuff
                if (e.getSource() == choose_file_bt) {
                    JFileChooser fileChooser = new JFileChooser();
                    int i = fileChooser.showOpenDialog(null);
                    File file = fileChooser.getSelectedFile();
                    if (file != null) {
                        ParseData(file);
                    }

                }
            }
        });
        JButton help_button = new JButton("Help");
        help_button.setBounds(840, 40, 40, 30);
        help_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = "\n<html><h1>Program Information:</h1>\n"
                        + "\n<html><b>To Select a New File:</b> Click on 'Choose a File' and select a text file"
                        + "\n<html><b>File Statistics Box:</b> Statistics on the most recently chosen file"
                        + "\n<html><b>Average File Statistics Box:</b> Average statistics between all chosen files"
                        + "\n\n<html><b>*Below is an explanation for all statistics in each box*</b>\n"
                        + "\n<html><b>Number of lines:</b> Amount of lines in the text file"
                        + "\n<html><b>Number of Blank Lines:</b> Amount of empty lines in the text file"
                        + "\n<html><b>Number of Spaces:</b> Amount of spaces in the text file"
                        + "\n<html><b>Number of Words:</b> Amount of words in the text file"
                        + "\n<html><b>Average Characters Per Line:</b> Averaage amount of characters per each line"
                        + "\n<html><b>Average Word Length:</b> Average amount of characters per each word"
                        + "\n<html><b>Most Common Word:</b> Word that is used the most often in the text file";

                JOptionPane.showMessageDialog(outter_container_frame, message);
            }
        });
        button_panel.add(choose_file_bt);
        button_panel.add(help_button);
        button_panel.setBounds(50, 520, 900, 80);


        // ------ GUI JFame ------

        outter_container_frame.add(title_panel);
        outter_container_frame.add(content_panel);
        outter_container_frame.add(button_panel);

        outter_container_frame.setLayout(null);
        outter_container_frame.setSize(1000, 600);
        outter_container_frame.setVisible(true);
        DetermineContent();
    }


    void DetermineContent() {
        // ------ Left Text Area ------
        left_content = "\n Number of Lines: " + current_number_of_lines
                + "\n\n Number of Blank Lines: " + current_number_of_blank_lines
                + "\n\n Number of Spaces: " + current_number_of_spaces
                + "\n\n Number of Words: " + current_number_of_words
                + "\n\n Average Characters Per Line: " + current_average_characters_per_line
                + "\n\n Average Word Length: " + current_average_word_length
                + "\n\n Most Common Word: " + current_most_common_word
                + "\n\n Number Of Occurrences: " + current_repeated_word_occurences;

        left_text_area.setText(left_content);


        // ------ Middle Text Area ------
        ReadFromHistory();

        middle_content = "\n Number of Lines: " + historic_number_of_lines
                + "\n\n Number of Blank Lines: " + historic_number_of_blank_lines
                + "\n\n Number of Spaces: " + historic_number_of_spaces
                + "\n\n Number of Words: " + historic_number_of_words
                + "\n\n Average Characters Per Line: " + historic_average_characters_per_line
                + "\n\n Average Word Length: " + historic_average_word_length
                + "\n\n Most Common Word: " + historic_most_common_word
                + "\n\n Number Of Occurrences: " + historic_repeated_word_occurences;

        middle_text_area.setText(middle_content);

        // ------ Right Text Area ------
        right_content = file_content;
        right_text_area.setText(right_content);
    }

    void ParseData(File file) {
        HashMap<String, Integer> wordCountMap = new HashMap<String, Integer>();

        BufferedReader reader = null;

        int charCount = 0;

        int wordCount = 0;

        int lineCount = 0;

        int emptyLine = 0;

        int avgChar = 0;

        int avgLineLength = 0;

        int spaceCount = 0;

        try {
            file_content = "";

            reader = new BufferedReader(new FileReader(file));

            String currentLine = reader.readLine();

            while (currentLine != null) {

                file_content += currentLine + "\n";

                lineCount++;

                if (currentLine.isEmpty())
                    emptyLine++;

                String[] words = currentLine.toLowerCase().split(" ");
                //if (currentLine.equals(""))
                //    spaceCount++;

                wordCount = wordCount + words.length;

                for (String word : words) {
                    if (word == " ") {
                        wordCount--;
                        spaceCount++;
                    }
                    spaceCount++;

                    if (wordCountMap.containsKey(word)) {
                        wordCountMap.put(word, wordCountMap.get(word) + 1);
                    } else {
                        wordCountMap.put(word, +1);
                    }
                    charCount = charCount + word.length();
                }
                currentLine = reader.readLine();
            }

            String mostRepeatedWord = null;

            int count = 0;

            Set<Entry<String, Integer>> entrySet = wordCountMap.entrySet();

            for (Entry<String, Integer> entry : entrySet) {
                if (entry.getValue() > count) {
                    mostRepeatedWord = entry.getKey();

                    count = entry.getValue();
                }
            }


            wordCount = wordCount - emptyLine;

            lineCount = lineCount - emptyLine;

            avgChar = charCount / wordCount;

            avgLineLength = charCount / lineCount;

           // spaceCount = spaceCount - 1;

            current_number_of_lines = lineCount;
            current_number_of_blank_lines = emptyLine;
            current_number_of_spaces = spaceCount;
            current_number_of_words = wordCount;
            current_average_characters_per_line = avgLineLength;
            current_average_word_length = avgChar;
            current_most_common_word = mostRepeatedWord;
            current_repeated_word_occurences = count;

            SaveDataToTextFile();
            DetermineContent();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void SaveDataToTextFile() {
        try {
            Writer output;
            output = new BufferedWriter(new FileWriter("./src/historicData.txt", true));
            String data_to_save = current_number_of_lines + ","     // 0
                    + current_number_of_blank_lines + ","           // 1
                    + current_number_of_spaces + ","                // 2
                    + current_number_of_words + ","                 // 3
                    + current_average_characters_per_line + ","     // 4
                    + current_average_word_length + ","             // 5
                    + current_most_common_word + ","                // 6
                    + current_repeated_word_occurences + "\n";      // 7
            output.append(data_to_save);
            output.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void ReadFromHistory() {

        historic_number_of_lines = 0;
        historic_number_of_blank_lines = 0;
        historic_number_of_spaces = 0;
        historic_number_of_words = 0;
        historic_average_characters_per_line = 0;
        historic_average_word_length = 0;
        historic_repeated_word_occurences = 0;

        BufferedReader reader = null;
        File file = new File("./src/historicData.txt");
        String historic_data;

        try {
            historic_data = "";

            reader = new BufferedReader(new FileReader(file));

            String currentLine = reader.readLine();

            if (currentLine == null) {
                return;
            }

            while (currentLine != null) {

                historic_data += currentLine + "\n";
                currentLine = reader.readLine();

            }

            ParseHistoricData(historic_data);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void ParseHistoricData(String data) {
        String[] split_by_line = data.split("\n");
        int number_of_lines = split_by_line.length, total_lines = 0,
                total_blank_lines = 0, total_spaces = 0, total_number_of_words = 0,
                total_average_characters_per_line = 0, total_average_word_length = 0;

        Map<String, Integer> map = new HashMap();

        for (int i = 0; i < number_of_lines; i++) {
            String[] split_by_value = split_by_line[i].split(",");
            int update = 0;

            if (map.get(split_by_value[6]) != null) {
                update = map.get(split_by_value[6]) + Integer.parseInt(split_by_value[7]);
            } else {
                update = Integer.parseInt(split_by_value[7]);
            }

            total_lines += Integer.parseInt(split_by_value[0]);
            total_blank_lines += Integer.parseInt(split_by_value[1]);
            total_spaces += Integer.parseInt(split_by_value[2]);
            total_number_of_words += Integer.parseInt(split_by_value[3]);
            total_average_characters_per_line += Integer.parseInt(split_by_value[4]);
            total_average_word_length += Integer.parseInt(split_by_value[5]);
            map.put(split_by_value[6], update);
        }

        String most_common_word = "";
        int maxSoFar = 0;

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            Integer num = entry.getValue();
            if (num > maxSoFar) {
                maxSoFar = num;
                most_common_word = key;
            } else if (num == maxSoFar) {
                most_common_word += " and " + key;
            }
        }

        historic_number_of_lines = total_lines / number_of_lines;
        historic_number_of_blank_lines = total_blank_lines / number_of_lines;
        historic_number_of_spaces = total_spaces / number_of_lines;
        historic_number_of_words = total_number_of_words / number_of_lines;
        historic_average_characters_per_line = total_average_characters_per_line / number_of_lines;
        historic_average_word_length = total_average_word_length / number_of_lines;
        historic_most_common_word = most_common_word;
        historic_repeated_word_occurences = maxSoFar;
    }
}