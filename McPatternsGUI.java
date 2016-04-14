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
	public McPatternsGUI(McPatternsPresenter presenter) {
		
		this.presenter = presenter;
		presenter.attachView(this);
		showGUI();

	}
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
		JLabel orderDetails = new JLabel("Your order");
		orderPane.setBorder(BorderFactory.createRaisedBevelBorder());
		orderPane.add(orderDetails);
		orderPane.setBackground(new Color(0,191,255));
		JTextField ccEntry = new JTextField("Enter Credit Card #");
		ccEntry.setPreferredSize(new Dimension(20, 10));
		JTextField ccNumber = new JTextField("Enter Credit Card number...");
		JTextField ccType = new JTextField("Credit Card Type(Visa, MasterCard..");

		JLabel totalPanel = new JLabel("Total $ : ");
		JLabel payStatus = new JLabel("Payment status: ");
		payStatus.setVisible(false);
		
		JButton creditCard = new JButton("Enter Credit Card info");
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
						payStatus.setVisible(true)	;
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
						payStatus.setVisible(true)	;
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
						payStatus.setVisible(true)	;
					}
				}
				
				
				
				
			}
			
		});
		JButton confirm = new JButton("Place Order");
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: Add the function to handle confirmed order
				//Think about where you will store order and who should manipulate.
				//Handle the Payment validation before confirming order. Who should validate?
				if(check == true)
				{
				orderDetails.setText("Order confrimed for " + cCNumber);
				}
			}

		});
		JButton cancel = new JButton("Cancel Order");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: Add the function to h	andle cancel order
				//Think about where you will store order and who should manipulate.
				orderDetails.setText("Order cancelled");
			}

		});
		
		  
       // JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setBackground(Color.WHITE);
        textArea.setFont(new Font("Lucida Console", Font.PLAIN, 13));
        textArea.setForeground(Color.GREEN);
        //textArea.setBounds(10, 57, 414, 383);
        
      

      //orderPane.add(typeList);
       //orderPane.add(ccEntry);
		
		orderPane.add(textArea);
		//orderPane.add(ccNumber);
		//orderPane.add(ccType);
		orderPane.add(totalPanel);
		orderPane.add(payStatus);
		orderPane.add(creditCard);
		orderPane.add(confirm);
		orderPane.add(cancel);

		JPanel buttonPanel = new JPanel();
		//BoxLayout b = new BoxLayout(buttonPanel, BoxLayout.Y_AXIS);
		//buttonPanel.setLayout(b);
		buttonPanel.setSize(600, 400);
		buttonPanel.add(Box.createVerticalGlue()); // To orient the menu buttons vertically
		buttonPanel.setBackground(new Color(0,191,255));
		// TODO: Ask the presenter for the buttons to create. Iterate over the buttons and create them
		ArrayList<MenuModel> l = presenter.returnMenuItems();
		for(int i = 0; i < l.size(); i++)
		{
			MenuModel m = (MenuModel) l.get(i);
			JButton bj = new JButton(m.getName() + "|" + m.getPrice());
			
			 //bj.putClientProperty("price", m.getPrice());
			 //bj.addActionListener(this);
			    bj.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					presenter.addOrder(m);
					presenter.addPrice(m.getPrice());
					orderList = presenter.getOrderList();
					total = presenter.getTotalPrice();
					
					if(orderList.size() == 0)
					{
						textArea.setText(m.getName() + ": " + m.getPrice());
						totalPanel.setText("Total $: " + total);
					}
					else if(orderList.size() > 0	)
					{
					
					for(int i = 0;i < orderList.size(); i++)
					{
						
						MenuModel item = orderList.get(i);
						String name = item.getName();
						double price = item.getPrice();
						textArea.append(name + ": "+ price);
						textArea.append("\n");
						presenter.removeOrder(m);
						totalPanel.setText("Total $: " + total);
					}
					}
					
					
					 
					
					
					
				}
			});
			buttonPanel.add(bj);	
			
		}
		
	
		
		theFrame.add(title,BorderLayout.NORTH);
		theFrame.add(buttonPanel, BorderLayout.CENTER);
		theFrame.add(orderPane, BorderLayout.EAST);
		
		theFrame.pack();
		ccEntry.setPreferredSize(new Dimension(20, 10));

		theFrame.setSize(700, 500);
		//buttonPanel.setSize(700, 500);
		//theFrame.setPreferredSize(new Dimension(1000, 200));
		theFrame.setLocationRelativeTo(null);
		theFrame.setVisible(true);
		
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
	

