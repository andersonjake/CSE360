
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
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
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
        stage.setTitle("Text File Analyzer"); //window title
 
        final FileChooser fileChooser = new FileChooser();
 
        final Button openButton = new Button("Choose a File");
        final Button helpButton = new Button("Help");
        
        Text headerText = new Text ("CSE 360 Text File Analyzer");
        headerText.setStyle("-fx-font: 24 arial");
        
        /*Text for statistic labels */
        Text fileStat = new Text ("File Statistics");
        Text numLines = new Text ("Number of Lines: ");
        Text numBlankLines = new Text ("Number of Blank Lines: ");
        Text numSpaces = new Text("Number of Spaces: ");
        Text numWords = new Text("Number of Words: ");
        Text avgCharLine = new Text("Average Characters Per Line: ");
        Text avgWordLength = new Text("Average Word Length: ");
        Text commonWord = new Text("Most Common Word: ");
        
        /*Place holders for statistics NOT IN USE CURRENTLY */
        Text fileStatVal = new Text (" ");
        Text numLinesVal = new Text ("0");
        Text numBlankLinesVal = new Text ("0");
        Text numSpacesVal = new Text("0");
        Text numWordsVal = new Text("0");
        Text avgCharLineVal = new Text("0");
        Text avgWordLengthVal = new Text("0");
        Text commonWordVal = new Text("0");
        
        /*Average file labels */
        Text avgfileStat = new Text ("Average File Statistics");
        Text avgnumLines = new Text ("Number of Lines: ");
        Text avgnumBlankLines = new Text ("Number of Blank Lines: ");
        Text avgnumSpaces = new Text("Number of Spaces: ");
        Text avgnumWords = new Text("Number of Words: ");
        Text avgavgCharLine = new Text("Average Characters Per Line: ");
        Text avgavgWordLength = new Text("Average Word Length: ");
        Text avgcommonWord = new Text("Most Common Word: ");
        
        /*Place holders for average statistics NOT IN USE CURRENTLY */
        Text avgfileStatVal = new Text (" ");
        Text avgnumLinesVal = new Text ("0");
        Text avgnumBlankLinesVal = new Text ("0");
        Text avgnumSpacesVal = new Text("0");
        Text avgnumWordsVal = new Text("0");
        Text avgavgCharLineVal = new Text("0");
        Text avgavgWordLengthVal = new Text("0");
        Text avgcommonWordVal = new Text("0");
        
        helpButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Stage stage = new Stage();
                
                stage.setTitle("Help Window"); //Window title
                
                //Title formatting
                Text headerText = new Text ("Program Information");
                headerText.setStyle("-fx-font: 24 arial");
                
                //Text to be put inside help window.  Broken into 4 different sections
                Text chooseHelp = new Text("\nTo Select a New File - \n\t"
                		+ "Click on 'Choose a File' and select a text file");
                Text exp1 = new Text("File Statistics Box - "
                		+ "\n\tStatistics on the most recently chosen file");
                Text exp2 = new Text("Average File Statistics Box - \n\t"
                		+ "Average statistics between all chosen files\n"
                		+ "\n*Below is an explanation for all statistics in each box*");
                Text exp3 = new Text("\nNumber of lines - \n\t"
                		+ "Amount of lines in the text file\n"
                		+ "Number of Blank Lines - \n\t"
                		+ "Amount of empty lines in the text file\n"
                		+ "Number of Spaces - \n\t"
                		+ "Amount of spaces in the text file\n"
                		+ "Number of Words - \n\t"
                		+ "Amount of words in the text file\n"
                		+ "Average Characters Per Line - \n\t"
                		+ "Averaage amount of characters per each line\n"
                		+ "Average Word Length - \n\t"
                		+ "Average amount of characters per each word\n"
                		+ "Most Common Word - \n\t"
                		+ "Word that is used the most often in the text file\n");
                
                VBox helpVBox = new VBox(); //main box
                helpVBox.setStyle(
    					"-fx-border-style: solid inside;" + 
    					"-fx-border-width: 1;" +
  						"-fx-border-color: gray;" +
  						"-fx-padding: 10;");
                
                VBox textVBox = new VBox();//Box for the text - set to white background
                textVBox.setStyle("-fx-background-color: #FFFFFF;" +
    					"-fx-border-style: solid inside;" + 
    					"-fx-border-width: 1;" +
  						"-fx-border-color: gray;" +
  						"-fx-padding: 5");
                
                //placing children into respective boxes
                textVBox.getChildren().addAll(headerText, chooseHelp, exp1, exp2, exp3);
                helpVBox.getChildren().addAll(textVBox);
                
                Scene scene = new Scene(helpVBox, 400, 445);
                
                stage.setScene(scene);
                
                stage.show();
            }
        });
 
        /*Button for selecting file */
        openButton.setOnAction(
            new EventHandler<ActionEvent>() {
                public void handle(final ActionEvent e) {
                    File file = fileChooser.showOpenDialog(stage);
                    if (file != null) {
                        parseFile(file);
                    }
                }
            });
        
        //MAIN BOX
        VBox mainVBox = new VBox(); //MAIN BOX
        mainVBox.setStyle("-fx-border-style: solid inside;" + 
                		  	"-fx-border-width: 0;" +
        				  	"-fx-border-color: black;");
        
        //Top 1/3 Box
        VBox topVBox = new VBox(15); //TITLE BOX
        topVBox.setStyle("-fx-border-style: solid inside;" + 
      		  				"-fx-border-width: 0;" +
				  			"-fx-border-color: black;" +
				  			"-fx-padding: 10 0 10 10;");
        
        //Middle 1/3 Box
        HBox midHBox = new HBox(); //INFO/STAT BOX
        midHBox.setStyle("-fx-border-style: solid inside;" + 
      		  				"-fx-border-width: 0;" +
				  			"-fx-border-color: black;");
        
        //Box for holding stat labels and stat variables
        HBox fileHBox = new HBox();
        fileHBox.setPrefWidth(250);
        fileHBox.setStyle("-fx-background-color: #FFFFFF;" +
        					"-fx-border-style: solid inside;" + 
        					"-fx-border-width: 1;" +
	  						"-fx-border-color: gray;");
        
        //Box for holding regular variables
        VBox fileValVBox = new VBox(15);
       
        //Box for holding average stat labels and average stat variables
        HBox avgFileHBox = new HBox();
        avgFileHBox.setPrefWidth(250);
        avgFileHBox.setStyle("-fx-background-color: #FFFFFF;" +
        						"-fx-border-style: solid inside;" + 
        						"-fx-border-width: 1;" +
								"-fx-border-color: gray;");
        
        //Box for holding average variables
        VBox avgFileValVBox = new VBox(15);
        
        //Bottom 1/3 Box
        HBox botVBox = new HBox(15); //BOX FOR THE BUTTONS
        botVBox.setStyle("-fx-border-style: solid inside;" + 
      		  				"-fx-border-width: 0;" +
				  			"-fx-border-color: black;");
        
        //Box for file statistic labels -> filehbox
        VBox statVBox = new VBox(15);
        statVBox.setStyle("-fx-border-style: solid inside;" + 
	  						"-fx-border-width: 0;" +
	  						"-fx-border-color: black;" +
	  						"-fx-padding: 5 5 0 10;");
        statVBox.setPrefWidth(200);
        
        //Box for average file stat labels
        VBox avgStatVBox = new VBox(15);
        avgStatVBox.setStyle("-fx-border-style: solid inside;" + 
								"-fx-border-width: 0;" +
								"-fx-border-color: black;" +
								"-fx-padding: 5 5 0 10;");
        avgStatVBox.setPrefWidth(200);
        
        //Last 1/3 to the right of midbox, for maybe placing the text file's contents
        VBox textFileVBox = new VBox(15);
        textFileVBox.setStyle("-fx-border-style: solid inside;" + 
								"-fx-border-width: 0;" +
								"-fx-border-color: black;");
        
        //uses to push over the box for the buttons to the right
        HBox space1 = new HBox(15);
        space1.setPrefWidth(300);
        
        HBox space2 = new HBox(15);
        space2.setPrefWidth(275);
        
        HBox butHBox = new HBox(15); //BUTTON BOX -> BOTHBOX
        butHBox.setStyle("-fx-border-style: solid inside;" + 
	  						"-fx-border-width: 0;" +
	  						"-fx-border-color: black;");
        
        //Header text going into top
        topVBox.getChildren().addAll(headerText);
        
        //Labels being put into statbox
        statVBox.getChildren().addAll(fileStat, numLines, numBlankLines, 
        		numSpaces, numWords, avgCharLine, avgWordLength, commonWord);
        
        //VAR PLACEHOLDERS NOT IN USE
        /*fileValVBox.getChildren().addAll(fileStatVal, numLinesVal, numBlankLinesVal, 
        		numSpacesVal, numWordsVal, avgCharLineVal, avgWordLengthVal, commonWordVal);*/
        
        //Average labels being put into avgstatbox
        avgStatVBox.getChildren().addAll(avgfileStat, avgnumLines, avgnumBlankLines, 
        		avgnumSpaces, avgnumWords, avgavgCharLine, avgavgWordLength, avgcommonWord);
        
        //VAR PLACEHOLDERS NOT IN USE
        /*avgFileValVBox.getChildren().addAll(avgfileStatVal, avgnumLinesVal, 
        		avgnumBlankLinesVal, avgnumSpacesVal, avgnumWordsVal, avgavgCharLineVal,
        		avgavgWordLengthVal, avgcommonWordVal); */
        
        
        //Middle box, left 1/3, file stats being put in
        fileHBox.getChildren().addAll(statVBox, fileValVBox);
        
        //Middle box, middle 1/3, average file stats being put in
        avgFileHBox.getChildren().addAll(avgStatVBox, avgFileValVBox);
        
        //Putting contents into middle box
        midHBox.getChildren().addAll(fileHBox, avgFileHBox, textFileVBox);
        
        //putting items into button box
        butHBox.getChildren().addAll(openButton, helpButton);
        
        //putting items into bottom box
        botVBox.getChildren().addAll(space1, space2, butHBox);
        
        //Putting top, mid, and bottom box into main
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