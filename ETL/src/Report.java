public class Report {
    private String product;
    private String year;
    private int numofcomplaint;
    private int numofcompany;
    private int percentage;
  
	public Report() {
	}

	public Report(String product, String year, int numofcomplaint, int numofcompany, int percentage) {
        this.product = product;
        this.year = year;
        this.numofcomplaint = numofcomplaint;
        this.numofcompany = numofcompany;
        this.percentage = percentage;
    }    

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getNumofcomplaint() {
		return numofcomplaint;
	}

	public void setNumofcomplaint(int numofcomplaint) {
		this.numofcomplaint = numofcomplaint;
	}

	public int getNumofcompany() {
		return numofcompany;
	}

	public void setNumofcompany(int numofcompany) {
		this.numofcompany = numofcompany;
	}

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
}
