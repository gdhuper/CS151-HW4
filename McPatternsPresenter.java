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
    	return total;
    }

}