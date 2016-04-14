
public class DiscoverCC extends CreditCard{

	public DiscoverCC(String number, String type) {
		super(number, type);
		// TODO Auto-generated constructor stub
		
	}
	
	public boolean isValid()
	{
			String number =  super.getCardNumber();
		
		int length = number.length();
		if(length == 16)
		{
			if(number.substring(0, 4).equals("6011"))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
				
		else
		{
			return false;
		}
	}

}
