import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class Main
{
    public static void main(String[] args)
    {
        HashMap<String, Integer> wordCountMap = new HashMap<String, Integer>();

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
            reader = new BufferedReader(new FileReader("/Users/DYLANSLATER/Desktop/test.txt"));

            String currentLine = reader.readLine();

            while (currentLine != null)
            {

                lineCount++;

                if (currentLine.isEmpty())
                    emptyLine++;

                String[] words = currentLine.toLowerCase().split(" ");
                //if (currentLine.equals(""))
                //    spaceCount++;

                wordCount = wordCount + words.length;

                for (String word : words)
                {
                    if (word == " ")
                    {
                        wordCount--;
                        spaceCount++;
                    }
                    spaceCount++;

                    if (wordCountMap.containsKey(word))
                    {
                        wordCountMap.put(word, wordCountMap.get(word)+1);
                    }

                    else
                    {
                        wordCountMap.put(word, +1);
                    }
                    charCount = charCount + word.length();
                }
                currentLine = reader.readLine();
            }

            String mostRepeatedWord = null;

            int count = 0;

            Set<Entry<String, Integer>> entrySet = wordCountMap.entrySet();

            for (Entry<String, Integer> entry : entrySet)
            {
                if(entry.getValue() > count)
                {
                    mostRepeatedWord = entry.getKey();

                    count = entry.getValue();
                }
            }


            wordCount = wordCount - emptyLine;

            lineCount = lineCount - emptyLine;

            avgChar = charCount / wordCount;

            avgLineLength = charCount / lineCount;

            spaceCount = spaceCount-1;

            System.out.println("Number Of Chars In File : "+charCount);

            System.out.println("Number Of Words In File : "+wordCount);

            System.out.println("Number Of Lines In File : "+lineCount);

            System.out.println("Number Of Empty Lines In File : "+emptyLine);

            System.out.println("Average Characters Per Word In File : "+avgChar);

            System.out.println("Average Characters Per Line In File : "+avgLineLength);

            System.out.println("Number Of Spaces In File : "+spaceCount);

            System.out.println("The most repeated word in input file is : "+mostRepeatedWord);

            System.out.println("Number Of Occurrences : "+count);


        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                reader.close();           //Closing the reader
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}