/**
 * @author Gurpreet Singh
 */

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

 class McPatternsGUI extends JFrame implements ActionListener{
	McPatternsPresenter presenter;
	JMenuItem newOrder, quit;
	ArrayList<MenuModel> orderList = new ArrayList<MenuModel>();
	public JTextArea textArea = new JTextArea();
	public Double total = 0.0;
	public boolean check = false;
	public String cCNumber = null;
	public JPanel buttonPanel = new JPanel();
	public JLabel totalPanel = new JLabel();
	
	public McPatternsGUI(McPatternsPresenter presenter) {
		
		this.presenter = presenter;
		presenter.attachView(this);
		showGUI();

	}
	/**
	 * Loads the main GUI of the system
	 */
	private void showGUI() {
		presenter.loadMenuItems();

		JFrame theFrame = new JFrame("Place your order");
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theFrame.setLayout(new BorderLayout());
		//Frame menu
		JMenuBar menu = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		newOrder = new JMenuItem("New Order");
		newOrder.addActionListener(this);
		fileMenu.add(newOrder);
		quit = new JMenuItem("Quit");
		quit.addActionListener(this);
		fileMenu.add(quit);
		//fileMenu.addSeparator();
		menu.add(fileMenu);
		menu.setVisible(true);
		setJMenuBar(menu);
		
		
		JPanel title = new JPanel(new FlowLayout());
		title.add(new JLabel("Welcome to McDonalds"));

		JPanel orderPane = new JPanel();
		orderPane.setLayout(new BoxLayout(orderPane, BoxLayout.PAGE_AXIS));
		JLabel orderDetails = new JLabel("Your Order");
		orderPane.setBorder(BorderFactory.createRaisedBevelBorder());
		orderPane.add(orderDetails, BoxLayout.X_AXIS);
		orderPane.setBackground(new Color(0,191,255));
		JPanel orderButtons = new JPanel();
		orderButtons.setLayout(new GridLayout(2,1));
		
		totalPanel = new JLabel("Total $ : ");
		JLabel payStatus = new JLabel("Payment status: ");
		payStatus.setVisible(false);
		
		
		
		JButton creditCard = new JButton("Pay for Order");
		creditCard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String[] ccTypeList = {"Visa", "MasterCard", "American Exp", "Discover"};
			       JComboBox<String> typeList = new JComboBox<String>(ccTypeList);
			       typeList.setSelectedIndex(0);
			     // typeList.addActionListener(this);
				JOptionPane ccDetails = new JOptionPane();
				String input =(String) ccDetails.showInputDialog(null, "Select type...", "Select Card Type", ccDetails.QUESTION_MESSAGE, null, ccTypeList, ccTypeList[0]);
				
				String type = input;
				
				//System.out.println("Type " + type);
				input = ccDetails.showInputDialog("Enter card number..");
				
				//System.out.println("number " + input);

				
				String number = (String) input;
				cCNumber = number;
				
				if(type.equals("Visa"))
				{
					VisaCC c = new VisaCC(number, type);
					//System.out.println(c.isValid());
					if(c.isValid() == true)
					{
						payStatus.setText("Payment status: Transaction approved");
						payStatus.setVisible(true);
						check = true;
					}
					else if(c.isValid() == false)
					{
						payStatus.setText("Payment status: Transaction Failed");
						payStatus.setVisible(true);
						check = false;
					}
				}
				 	
				else if(type.equals("MasterCard"))
				{
					MasterCC c = new MasterCC(number, type);
					if(c.isValid() == true)
					{
						payStatus.setText("Payment status: Transaction approved");
						payStatus.setVisible(true);
						check = true;
					}
					else if(c.isValid() == false)
					{
						payStatus.setText("Payment status: Transaction Failed");
						payStatus.setVisible(true);
						check = false;
					}
				}
				else if(type.equals("American Exp"))
				{
					AmExCC c = new AmExCC(number, type);
					
					if(c.isValid() == true)
					{
						payStatus.setText("Payment status: Transaction approved");
						payStatus.setVisible(true);
						check = true;

					}
					else if(c.isValid() == false)
					{
						payStatus.setText("Payment status: Transaction Failed");
						payStatus.setVisible(true);
						check = false;
					}
				}
				else if(type.equals("Discover"))
				{
					DiscoverCC c = new DiscoverCC(number, type);
					if(c.isValid() == true)
					{
						payStatus.setText("Payment status: Transaction approved");
						payStatus.setVisible(true);
						check = true;

					}
					else if(c.isValid() == false)
					{
						payStatus.setText("Payment status: Transaction Failed");
						payStatus.setVisible(true);
						check = false;
					}
				}
				
				
				
				
			}
			
		});
		JButton confirm = new JButton("Place Order");
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(check == true)
				{
				orderDetails.setText("Order confrimed for " + cCNumber);
				JOptionPane.showMessageDialog(null, "Order Confirmed for : " + cCNumber);
				presenter.printReciept();
				
				}
				else if(check = false)
				{
						orderDetails.setText("Please enter a valid card number");
						JOptionPane.showMessageDialog(null, "Please enter Credit Card to place order");
						 
				
				}
				
			}

		});
		JButton cancel = new JButton("Cancel Order");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				orderDetails.setText("Order cancelled");
				textArea.setText(null);
				presenter.orderList.clear();
				payStatus.setText("Payment Status: ");
				payStatus.setVisible(false);
				totalPanel.setText("Total $: ");
				
				 
			}

		});
		
		JButton newOrder = new JButton("Clear");
		newOrder.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				orderDetails.setText("Your Order");
				textArea.setText(null);
				presenter.orderList.clear();
				payStatus.setText("Payment Status: ");
				payStatus.setVisible(false);
				totalPanel.setText("Total $: ");
				
				 
			}

		});
		
		  
        
        textArea.setLineWrap(true);
        textArea.setBackground(Color.gray);
        textArea.setFont(new Font("Lucida Console", Font.PLAIN, 14));
        textArea.setForeground(Color.BLACK);
        
      
        createButtonPanel(); //Creates the panel of menu item buttons in the main frame
        orderButtons.add(newOrder);
        orderButtons.add(confirm);
        orderButtons.add(cancel);
        orderButtons.add(creditCard);

		orderPane.add(textArea);
		orderPane.add(totalPanel);
		orderPane.add(payStatus);
		orderPane.add(orderButtons);
		orderPane.setSize(200, 500);
		
		
		theFrame.add(title,BorderLayout.NORTH);
		theFrame.add(buttonPanel, BorderLayout.CENTER);
		theFrame.add(orderPane, BorderLayout.EAST);
		theFrame.pack();
		theFrame.setSize(800, 500);
		theFrame.setLocationRelativeTo(null);
		theFrame.setVisible(true);
		
	}
	
	public void createButtonPanel()
	{
		 buttonPanel = new JPanel();
		GridLayout b = new GridLayout(10, 1);
		buttonPanel.setLayout(b);
		
		buttonPanel.setBackground(new Color(0,191,255));
		
		ArrayList<MenuModel> l = presenter.returnMenuItems();
		for(int i = 0; i < l.size(); i++)
		{
			MenuModel m = (MenuModel) l.get(i);
			JButton bj = new JButton(m.getName() + "|" + m.getPrice());
			bj.setBackground(new Color(255,140,0));
					
			 
			    bj.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					presenter.addOrder(m);
					
					presenter.addPrice(m.getPrice());
					orderList = presenter.getOrderList();
					total = presenter.getTotalPrice();
					
					if(orderList.size() == 0)
					{
						textArea.setText("1x " + m.getName() + ": " + m.getPrice());
						totalPanel.setText("Total $: " + total);
					}
					else if(orderList.size() > 0	)
					{
					
					for(int i = 0;i < orderList.size(); i++)
					{
						
						MenuModel item = orderList.get(i);
						String name = item.getName();
						double price = item.getPrice();
						textArea.append("1x " + name + ": "+ price);
						textArea.append("\n");
						presenter.removeOrder(m);
						totalPanel.setText("Total $: " + total);
					}
					}
					
					
					 
					
					
					
				}
			});
			buttonPanel.add(bj);	
			
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == newOrder) {
			 			 
			 		} else if (e.getSource() == quit) {
			 			Toolkit.getDefaultToolkit().beep();
			 			int decision = JOptionPane.showConfirmDialog(null,
			 					"Are you sure want to quit the window?", "Quit",
			 					JOptionPane.YES_NO_OPTION);
						if (decision == JOptionPane.YES_OPTION) {
							System.exit(0);
			 			} else {
			 				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 			}
			 		}
			 	}
		
	}
	

