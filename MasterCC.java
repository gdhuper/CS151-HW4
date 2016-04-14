
public class MasterCC extends CreditCard {

	public MasterCC(String number, String type) {
		super(number, type);
		// TODO Auto-generated constructor stub
	}
	
	
	public boolean isValid()
	{
		String number = super.getCardNumber();
		
		int length = number.length();
		if(length == 16)
		{
			if(number.charAt(0) == 5)
			{
				if(number.charAt(1) ==  '1' || number.charAt(1) ==  '2' || number.charAt(1) ==  '3'|| number.charAt(1) == '4' || number.charAt(1) ==  '5' )
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
