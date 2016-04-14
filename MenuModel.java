import java.util.ArrayList;

class MenuModel {
    // Add your implementation for Menu Items here.
    // Determine what data you want to store for each item.
	
	private String name;
	private Double price;
	ArrayList<MenuModel> list = new ArrayList<MenuModel>(); 
	
	public MenuModel(String name, Double price)
	{
		this.name = name;
		this.price = price;
		
	}
   
	
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public String toString()
	{
		return getName() + ": " + getPrice();
	}
}