import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    public static List<Input> readCSV() throws Exception{    
    	//String csvFile = "./input/complaints.csv";
    	String csvFile = "./input/cc3.csv";
    	
    	FileInputStream inputStream = new FileInputStream(csvFile);
        Scanner scanner = new Scanner(inputStream, "UTF-8"); 

        List<String> line = parseLine(scanner.nextLine());
               
        List<Input> inputList = new ArrayList<>();
        while (scanner.hasNext()) {
        	String curline = scanner.nextLine();

        	while (parseLine(curline).size() < 18 && scanner.hasNext()) {
    			curline = curline + scanner.nextLine();
    		}

    		line = parseLine(curline);
            Input input = new Input(line.get(0).substring(0, 4),line.get(1).toLowerCase(),line.get(7).toLowerCase());
            inputList.add(input);
        }
 
        scanner.close();        
        return inputList;
    }

    private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_QUOTE = '"';    
     
    public static List<String> parseLine(String csvLine) {
        return parseLine(csvLine, DEFAULT_SEPARATOR, DEFAULT_QUOTE);
    }

    public static List<String> parseLine(String csvLine, char separators) {
        return parseLine(csvLine, separators, DEFAULT_QUOTE);
    }

    public static List<String> parseLine(String csvLine, char separators, char quotes) {
        List<String> result = new ArrayList<>();

        if (csvLine == null || csvLine.isEmpty()) {
            return result;
        }
        if (quotes == ' ') {
            quotes = DEFAULT_QUOTE;
        }
        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuffer sb = new StringBuffer();
        boolean inQuotes = false;
        
        char[] chars = csvLine.toCharArray();

        for (char ch : chars) {
            if (inQuotes) {
                if (ch == quotes) {
                    inQuotes = false;
                }
                sb.append(ch);
            } else {
            	if (ch == quotes) {
                    inQuotes = true;
                    sb.append('"');   
                } else if (ch == separators) {
                    result.add(sb.toString());
                    sb = new StringBuffer();
                } else {
                    sb.append(ch);
                }
            }
        }

        result.add(sb.toString());
        return result;
    }
	
}
