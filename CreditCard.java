import java.awt.Window.Type;

	public class CreditCard {
	
	private String cardNumber;
	private String cardType;
	
	
	public CreditCard(String number, String type)
	{
		
		this.cardNumber = number;
		this.cardType = type;
		
	
	}
	

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}



	
	
}
