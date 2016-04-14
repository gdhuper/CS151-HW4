
public class AmExCC extends CreditCard{

	public AmExCC(String number, String type) {
		super(number, type);
		
	}
	
	
	public boolean isValid()
	{
String number = super.getCardNumber();
		
		int length = number.length();
		if(length == 15)
		{
			if(number.charAt(0) == '3')
			{
				if(number.charAt(1) ==  '4' | number.charAt(1) ==  '7' )
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
		else
		{
			return false;
		}
	}
	
	

}
