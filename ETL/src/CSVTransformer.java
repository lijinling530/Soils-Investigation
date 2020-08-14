import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVTransformer {
	public static void main(String[] args) throws Exception {
		List<Input> inputList = CSVReader.readCSV();
		List<Report> reportList = transform(inputList);
		CSVWriter.writeCSV(reportList);
	}	

    public static List<Report> transform(List<Input> inputList) {
        List<Report> reportList = new ArrayList<>();
 
        Map<String, Map<String, Integer>> productyear = new HashMap<>();
        for(int i=0; i<inputList.size(); i++) {
        	String pair = inputList.get(i).getProduct() + "#" + inputList.get(i).getDate();
        	String companyKey = inputList.get(i).getCompany();
        	if(!productyear.containsKey(pair)) {
        		Map<String, Integer> companyMap = new HashMap<>();
        		companyMap.put(companyKey, 1);
        		productyear.put(pair, companyMap);   
        	}
        	else {
        		Map<String, Integer> companyMaptmp = new HashMap<>();
        		companyMaptmp = productyear.get(pair);
        		companyMaptmp.put(companyKey, companyMaptmp.getOrDefault(companyKey, 0) + 1);
        	}
        }
 
        //to generate reportList
        for(String key : productyear.keySet()) {
        	Report report = new Report();
        	Map<String, Integer> companyMaptmp = new HashMap<>();
        	companyMaptmp = productyear.get(key);
        	int numofcomplaint = 0;
        	int max = 0;
        	for(String companyKey : companyMaptmp.keySet()) {
        		numofcomplaint += companyMaptmp.get(companyKey);
         		max = Math.max(max, companyMaptmp.get(companyKey));
        	}
        	String[] keypair = key.split("#");
        	report.setProduct(keypair[0]);
        	report.setYear(keypair[1]);
        	report.setNumofcomplaint(numofcomplaint);
        	report.setNumofcompany(companyMaptmp.keySet().size());
        	report.setPercentage((int)Math.round(100*(double)max/numofcomplaint));
         	//System.out.println(report.getProduct() + ", " + report.getYear()+ ", " + report.getNumofcomplaint()+ ", " + report.getNumofcompany()+ ", " + report.getPercentage());
        	reportList.add(report);
        }
 
        //to sort product alphabetically and year ascending    
        reportList.sort(new Comparator<Report>() {
            @Override
            public int compare(Report r1, Report r2) {
                String r1product = r1.getProduct().startsWith("\"") ? r1.getProduct().substring(1) : r1.getProduct();
                String r2product = r2.getProduct().startsWith("\"") ? r2.getProduct().substring(1) : r2.getProduct();         	
             	
            	int productCmp = r1product.compareToIgnoreCase(r2product);
                if (productCmp != 0) {
                    return productCmp;
                }
                return r1.getYear().compareTo(r2.getYear());
            }
        });       
        
        return reportList;	
   }
}

