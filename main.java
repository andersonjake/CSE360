
import java.awt.Desktop;
import java.awt.Label;
import java.awt.TextField;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
 
public final class main extends Application {
 
    private Desktop desktop = Desktop.getDesktop();
 
    @Override
    public void start(final Stage stage) {
        stage.setTitle("Text File Analyzer");
 
        final FileChooser fileChooser = new FileChooser();
 
        final Button openButton = new Button("Choose a File");
        final Button helpButton = new Button("Help");
        
        Text headerText = new Text ("CSE 360 Text File Analyzer");
        headerText.setStyle("-fx-font: 24 arial");
        
        Text fileStat = new Text ("File Statistics");
        Text numLines = new Text ("Number of Lines: ");
        Text numBlankLines = new Text ("Number of Blank Lines: ");
        Text numSpaces = new Text("Number of Spaces: ");
        Text numWords = new Text("Number of Words: ");
        Text avgCharLine = new Text("Average Characters Per Line: ");
        Text avgWordLength = new Text("Average Word Length: ");
        Text commonWord = new Text("Most Common Word: ");
        
        Text fileStatVal = new Text (" ");
        Text numLinesVal = new Text ("0");
        Text numBlankLinesVal = new Text ("0");
        Text numSpacesVal = new Text("0");
        Text numWordsVal = new Text("0");
        Text avgCharLineVal = new Text("0");
        Text avgWordLengthVal = new Text("0");
        Text commonWordVal = new Text("0");
        
        Text avgfileStat = new Text ("Average File Statistics");
        Text avgnumLines = new Text ("Number of Lines: ");
        Text avgnumBlankLines = new Text ("Number of Blank Lines: ");
        Text avgnumSpaces = new Text("Number of Spaces: ");
        Text avgnumWords = new Text("Number of Words: ");
        Text avgavgCharLine = new Text("Average Characters Per Line: ");
        Text avgavgWordLength = new Text("Average Word Length: ");
        Text avgcommonWord = new Text("Most Common Word: ");
        
        Text avgfileStatVal = new Text (" ");
        Text avgnumLinesVal = new Text ("0");
        Text avgnumBlankLinesVal = new Text ("0");
        Text avgnumSpacesVal = new Text("0");
        Text avgnumWordsVal = new Text("0");
        Text avgavgCharLineVal = new Text("0");
        Text avgavgWordLengthVal = new Text("0");
        Text avgcommonWordVal = new Text("0");
 
        openButton.setOnAction(
            new EventHandler<ActionEvent>() {
                public void handle(final ActionEvent e) {
                    File file = fileChooser.showOpenDialog(stage);
                    if (file != null) {
                        parseFile(file);
                    }
                }
            });
        
        VBox mainVBox = new VBox(); //MAIN BOX
        mainVBox.setStyle("-fx-border-style: solid inside;" + 
                		  	"-fx-border-width: 0;" +
        				  	"-fx-border-color: black;");
        
        VBox topVBox = new VBox(15); //TITLE BOX
        topVBox.setStyle("-fx-border-style: solid inside;" + 
      		  				"-fx-border-width: 0;" +
				  			"-fx-border-color: black;" +
				  			"-fx-padding: 10 0 10 10;");
        
        HBox midHBox = new HBox(); //INFO/STAT BOX
        midHBox.setStyle("-fx-border-style: solid inside;" + 
      		  				"-fx-border-width: 0;" +
				  			"-fx-border-color: black;");
        
        HBox fileHBox = new HBox();
        fileHBox.setPrefWidth(250);
        fileHBox.setStyle("-fx-background-color: #FFFFFF;" +
        					"-fx-border-style: solid inside;" + 
        					"-fx-border-width: 1;" +
	  						"-fx-border-color: gray;");
        
        VBox fileValVBox = new VBox(15);
       
        HBox avgFileHBox = new HBox();
        avgFileHBox.setPrefWidth(250);
        avgFileHBox.setStyle("-fx-background-color: #FFFFFF;" +
        						"-fx-border-style: solid inside;" + 
        						"-fx-border-width: 1;" +
								"-fx-border-color: gray;");
        
        VBox avgFileValVBox = new VBox(15);
        
        HBox botVBox = new HBox(15); //BOX FOR THE BUTTONS
        botVBox.setStyle("-fx-border-style: solid inside;" + 
      		  				"-fx-border-width: 0;" +
				  			"-fx-border-color: black;");
        
        VBox statVbox = new VBox(15); //BOX FOR STATS -> MIDVBOX
        statVbox.setStyle("-fx-border-style: solid inside;" + 
	  						"-fx-border-width: 0;" +
	  						"-fx-border-color: black;" +
	  						"-fx-padding: 5 5 0 10;");
        statVbox.setPrefWidth(200);
        
        VBox avgStatVbox = new VBox(15);
        avgStatVbox.setStyle("-fx-border-style: solid inside;" + 
								"-fx-border-width: 0;" +
								"-fx-border-color: black;" +
								"-fx-padding: 5 5 0 10;");
        avgStatVbox.setPrefWidth(200);
        
        VBox textFileVBox = new VBox(15);
        textFileVBox.setStyle("-fx-border-style: solid inside;" + 
								"-fx-border-width: 0;" +
								"-fx-border-color: black;");
        
        HBox space1 = new HBox(15);
        space1.setPrefWidth(300);
        
        HBox space2 = new HBox(15);
        space2.setPrefWidth(275);
        
        HBox butHBox = new HBox(15); //BUTTON BOX -> BOTHBOX
        butHBox.setStyle("-fx-border-style: solid inside;" + 
	  						"-fx-border-width: 0;" +
	  						"-fx-border-color: black;");
        
        topVBox.getChildren().addAll(headerText);
        
        
        statVbox.getChildren().addAll(fileStat, numLines, numBlankLines, 
        		numSpaces, numWords, avgCharLine, avgWordLength, commonWord);
        
        /*fileValVBox.getChildren().addAll(fileStatVal, numLinesVal, numBlankLinesVal, 
        		numSpacesVal, numWordsVal, avgCharLineVal, avgWordLengthVal, commonWordVal);*/
        
        avgStatVbox.getChildren().addAll(avgfileStat, avgnumLines, avgnumBlankLines, 
        		avgnumSpaces, avgnumWords, avgavgCharLine, avgavgWordLength, avgcommonWord);
        
        /*avgFileValVBox.getChildren().addAll(avgfileStatVal, avgnumLinesVal, 
        		avgnumBlankLinesVal, avgnumSpacesVal, avgnumWordsVal, avgavgCharLineVal,
        		avgavgWordLengthVal, avgcommonWordVal); */
        
        
        
        fileHBox.getChildren().addAll(statVbox, fileValVBox);
        
        avgFileHBox.getChildren().addAll(avgStatVbox, avgFileValVBox);
        
        midHBox.getChildren().addAll(fileHBox, avgFileHBox, textFileVBox);
        
        butHBox.getChildren().addAll(openButton, helpButton);
        botVBox.getChildren().addAll(space1, space2, butHBox);
        
        mainVBox.getChildren().addAll(topVBox, midHBox, botVBox);
        
        Scene scene = new Scene(mainVBox, 800, 325);
        
        stage.setScene(scene);
        stage.show();
        
        
        
    }
 
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    private void parseFile(File file){
    	BufferedReader reader = null;

        int charCount = 0;

        int wordCount = 0;

        int lineCount = 0;

        int emptyLine = 0;

        int avgChar = 0;

        int avgLineLength = 0;

        int spaceCount = 0;

        try
        {

            reader = new BufferedReader(new FileReader(file));

            String currentLine = reader.readLine();

            while (currentLine != null)
            {

                lineCount++;

                if (currentLine.isEmpty())
                    emptyLine++;

                String[] words = currentLine.split(" ");
                if (currentLine.equals(""))
                    spaceCount++;

                wordCount = wordCount + words.length;

                for (String word : words)
                {
                    charCount = charCount + word.length();
                }
                currentLine = reader.readLine();
            }



            wordCount = wordCount - emptyLine;

            lineCount = lineCount - emptyLine;

            avgChar = charCount / wordCount;

            avgLineLength = charCount / lineCount;


            System.out.println("Number Of Chars In File : "+charCount);

            System.out.println("Number Of Words In File : "+wordCount);

            System.out.println("Number Of Lines In File : "+lineCount);

            System.out.println("Number Of Empty Lines In File : "+emptyLine);

            System.out.println("Average Characters Per Word In File : "+avgChar);

            System.out.println("Average Characters Per Line In File : "+avgLineLength);

            System.out.println("Number Of Spaces In File : "+spaceCount);



        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                reader.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
 
    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(
                main.class.getName()).log(
                    Level.SEVERE, null, ex
                );
        }
    }
}