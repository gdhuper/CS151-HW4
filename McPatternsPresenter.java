import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class McPatternsPresenter { //java -jar McPatterns.jar Myfile.txt
	// Menu comes from MYFile.txt in the current directory. 
     //This is the class that will handle the model <-> UI communication.
	static  MenuModel model;
    McPatternsGUI view;
    Double total = 0.0;
    ArrayList<MenuModel> list;
    ArrayList<MenuModel> tempList = new ArrayList<MenuModel>();
    ArrayList<MenuModel> orderList = new ArrayList<MenuModel>();
  

     void loadMenuItems()
    {
	   
    	
    	File f = new File("C:\\Users\\gopi\\Documents\\workspace\\CS151 HW4\\src\\Menu.txt");
    	
			Scanner in;
			try {
				in = new Scanner(f);
				//in.useDelimiter(" | ");
			 list = new ArrayList<MenuModel>();
		    	while(in.hasNextLine())
		    	{
		    		
		    		String line = in.nextLine();
		    		int index = line.indexOf("|");
		    		String name = line.substring(0, index);
		    		String price = line.substring(index + 1);
		    		Double p = Double.parseDouble(price);
		    		
		    		model = new MenuModel(name, p);
		    		
		    		list.add(model);
		    		
		    		
		    		
		    	}
		    	
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			}
		
		
    	
    	
    	
         
    } 
   

    void attachView(McPatternsGUI view) {
        this.view = view;
    }
    /**
     * Method to return list of menu items
     * @return an array list that contains the list of menu items. 
     */
     ArrayList<MenuModel> returnMenuItems()
    {
		return list;
    	
    }
    
     void addOrder(MenuModel m)
    {
    	orderList.add(m);
    	tempList.add(m);
    	
    }
    
    ArrayList<MenuModel> getOrderList()
    {
    	return orderList;
    }
    void removeOrder(MenuModel m)
    {
    	orderList.remove(m);
    	
    }
    
    void addPrice(Double p)
    {
    	total += p;
    	
    	
    }
    
    public Double getTotalPrice()
    {
   	 
    	
    	double t = Math.floor(total * 100) / 100;

    	return t;
    }
    public ArrayList<MenuModel> getTempList()
    {
    	
    	return tempList;
    }
    
    public void printReciept()
    {
    	System.out.println("Sending Order to Kitchen................");
    	ArrayList<MenuModel> l = getTempList();
    	System.out.println("========================================");
    	System.out.println("\n");
    	for(int i = 0; i < l.size(); i++)
    	{
    		MenuModel m = l.get(i);
    	System.out.println(m.getName()+":              " +m.getPrice());
    	}
    	System.out.println("======================================== ");
    	System.out.println("Order Total($): " + getTotalPrice() );
    }
    
    
    
   //make a method to keep track of how many items are there for each type 
    //suggestion - array, hashmap, 

}