public class Input {
	private String date;
	private String product;
    private String company;

	public Input(String date, String product, String company) {
        this.date = date;
		this.product = product;
		this.company = company;
    }

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
}

