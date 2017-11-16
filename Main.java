import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main
{
    public static void main(String[] args)
    {
        BufferedReader reader = null;

        int charCount = 0;

        int wordCount = 0;

        int lineCount = 0;

        int emptyLine = 0;

        int avgChar = 0;

        try
        {

            reader = new BufferedReader(new FileReader("C:\\test.txt"));

            String currentLine = reader.readLine();

            while (currentLine != null)
            {

                lineCount++;

                if (currentLine.isEmpty())
                    emptyLine++;

                String[] words = currentLine.split(" ");

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


            System.out.println("Number Of Chars In File : "+charCount);

            System.out.println("Number Of Words In File : "+wordCount);

            System.out.println("Number Of Lines In File : "+lineCount);

            System.out.println("Average Number of Characters per word In File : "+avgChar);

            System.out.println("Number Of Empty Lines In File : "+emptyLine);

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
}