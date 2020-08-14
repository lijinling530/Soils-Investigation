import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class CSVWriter {
	public static void writeCSV(List<Report> reportList) throws Exception {
        String csvFile = "./output/report.csv";
		
        FileWriter writer = new FileWriter(csvFile);
        
        for (Report report : reportList) {
            List<String> tmplist = new ArrayList<>();         
            tmplist.add(report.getProduct());
            tmplist.add(report.getYear());
            tmplist.add(String.valueOf(report.getNumofcomplaint()));
            tmplist.add(String.valueOf(report.getNumofcompany()));
            tmplist.add(String.valueOf(report.getPercentage()));

            writeLine(writer, tmplist);
        }
        writer.flush();
        writer.close();
    }

    private static final char DEFAULT_SEPARATOR = ',';

    public static void writeLine(Writer w, List<String> values) throws IOException {
        writeLine(w, values, DEFAULT_SEPARATOR, ' ');
    }

    public static void writeLine(Writer w, List<String> values, char separators) throws IOException {
        writeLine(w, values, separators, ' ');
    }

    public static void writeLine(Writer w, List<String> values, char separators, char quotes) throws IOException {

        boolean first = true;
        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuilder sb = new StringBuilder();
        for (String value : values) {
            if (!first) {
                sb.append(separators);
            }
            if (quotes == ' ') {
                sb.append(value);
            } else {
                sb.append(quotes).append(value).append(quotes);
            }

            first = false;
        }
        sb.append("\n");
        w.append(sb.toString());
    }		
}

