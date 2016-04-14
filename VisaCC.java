
public class VisaCC extends CreditCard{

	
	public VisaCC(String number, String type) 
	{
		super(number, type);
	}
	
	
	public boolean isValid()
	{
		String number = super.getCardNumber();
		
		int length = number.length();
		if(length == 16 ||  length == 13)
		{
			
			if(number.charAt(0) == '4')
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
