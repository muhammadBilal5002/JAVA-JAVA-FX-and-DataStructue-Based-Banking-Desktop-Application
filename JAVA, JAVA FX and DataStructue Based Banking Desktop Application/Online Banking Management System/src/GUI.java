
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GUI {
	int AccountNo = 102;
	UserAccount_DataStructure ud = new UserAccount_DataStructure();
	Account Searched;
	GUI(){
	ud.Insert(new Account(100,"Electricity Bill","12345","000","0345****"));
	ud.Insert(new Account(101,"Water Bill","12345","111","0311****"));
	ud.Insert(new Account(102,"Gas Bill","12345","222","0312****"));
	}
	public void Start() throws IOException {
	String im = "C:\\Users\\Muhammad\\Desktop\\IQRA BANKING.JPG";
	File file = new File(im);
    BufferedImage image = ImageIO.read(file);
    JLabel label = new JLabel(new ImageIcon(image));
	JFrame f = new JFrame();
	JButton start = new JButton("Start Banking");
	//start.setOpaque(false);
	//start.setContentAreaFilled(false);
//	start.setBackground(Color.PINK);
//	start.setForeground(Color.WHITE);
	label.setBounds(15, 10, 353, 197);
	start.setBounds(130,230,120, 40);
	f.getContentPane().setBackground(Color.pink);
	f.add(label);
	f.add(start);
	f.setTitle("IQRA BANKING");
	f.setLocation(400, 150);
	f.setSize(400, 350);
	f.setLayout(null);
	f.setVisible(true);
	start.addActionListener(e->{
	f.setState(f.ICONIFIED);
	Panel();
	});
	}
	
	public void Panel() {
	JFrame f = new JFrame();
	f.getContentPane().setBackground(Color.pink);
	JLabel l1 = new JLabel("ACCOUNT NO.");
	JLabel l2 = new JLabel("PASSWORD");
	JTextField tf1 = new JTextField();
	JTextField tf2 = new JTextField();
	JButton login = new JButton("LOG IN");
	JButton signup = new JButton("SIGN UP");
	tf1.setBounds(90, 25, 250, 25);
	l1.setBounds(5, 10,100,50);
	tf2.setBounds(90, 80, 250, 25);
	l2.setBounds(5, 67,100,50);
	login.setBounds(90, 130, 80, 30);
	signup.setBounds(258, 130, 80, 30);
	f.add(l1);
	f.add(l2);
	f.add(tf1);
	f.add(tf2);
	f.add(login);
	f.add(signup);
	f.setSize(400, 400);
	f.setLayout(null);
	f.setVisible(true);
	signup.addActionListener(e->{
		f.setVisible(false);
		Signup();
	});
	login.addActionListener(e->{
		if (!tf1.getText().isEmpty())
		{
			if (tf1.getText().equalsIgnoreCase("Admin") && tf2.getText().equals("12345")) {
			AdminPanel();
			f.setVisible(false);}
			else {
			try {
				Searched=ud.get(Integer.parseInt(tf1.getText())-100);
				if (Searched==null) {JOptionPane.showMessageDialog(null, "Given Account No. Not Found");}
				else{
					if(Searched.Password.equals(tf2.getText())) {
						f.setVisible(false);
						Login();
						}
					else if(!tf2.getText().isEmpty()){
						JOptionPane.showMessageDialog(null, "Wrong Password!");
					}
					else {
						JOptionPane.showMessageDialog(null, "Please Enter Password");
					}
					}
					
			}
			catch(Exception x){
				JOptionPane.showMessageDialog(null, "Wrong!");
			}
		}
	}
	});
	
	}
	public void AdminPanel() {
	JFrame f = new JFrame();
	f.getContentPane().setBackground(Color.pink);
	JLabel l1 = new JLabel("WELCOM ADMIN !");
	JButton ab = new JButton("Add Balance");
	JButton wa = new JButton("WithDraw Amount");
	JButton ph = new JButton("Paymet History");
	JButton lo = new JButton("Logout");
	ab.addActionListener(e->{
	ADDBALANCE();
	});
	lo.addActionListener(e->{
		f.setVisible(false);
		Panel();
	});
	l1.setBounds(145, -20,200,100);
	lo.setBounds(295, 45, 75, 20);
	ab.setBounds(70, 100, 250, 50);
	wa.setBounds(70, 210, 250, 50);
	ph.setBounds(70, 320, 250, 50);
	f.add(l1);
	f.add(ab);
	f.add(wa);
	f.add(ph);
	f.add(lo);
	f.setSize(400, 450);
	f.setLayout(null);
	f.setVisible(true);
	wa.addActionListener(e->{
	AdminWthDraw();
	});
	
	ph.addActionListener(e->{
	AdminPaymentHistory();
	});
	}
	public void AdminWthDraw() {	
		 JFrame f = new JFrame();
		f.getContentPane().setBackground(Color.pink);
		 JLabel l1 = new JLabel("ACCOUNT NO. ");
		 JButton but = new JButton("WithDraw");
		 JLabel l2 = new JLabel("OTP : ");
		 JTextField enterOTP = new JTextField();
		 String[] accounts = ud.TotalAccountNo();
		 JComboBox cb = new JComboBox(accounts);
		 cb.setSelectedIndex(-1);
		 but.addActionListener(e->{
		 if(cb.getSelectedItem()!=null) {
		 if(!enterOTP.getText().isEmpty() || ud.get(Integer.parseInt(cb.getSelectedItem().toString())-100).RandomOTP==0) {
		 if(ud.get(Integer.parseInt(cb.getSelectedItem().toString())-100).RandomOTP!=0) {
		 if(ud.get(Integer.parseInt(cb.getSelectedItem().toString())-100).RandomOTP==Integer.parseInt(enterOTP.getText()))
		 {
		 ud.get(Integer.parseInt(cb.getSelectedItem().toString())-100).Balance = ud.get(Integer.parseInt(cb.getSelectedItem().toString())-100).Balance - ud.get(Integer.parseInt(cb.getSelectedItem().toString())-100).WithDrawAmount;
		 ud.get(Integer.parseInt(cb.getSelectedItem().toString())-100).RandomOTP=0;
		 ud.get(Integer.parseInt(cb.getSelectedItem().toString())-100).WithDrawAmount=0.0;
		 JOptionPane.showMessageDialog(null,"Amount WithDraw Done");
		 f.setVisible(false);
		 return;
		 }
		 else {}JOptionPane.showMessageDialog(null,"Wrong OTP!");
		 }
		 else {JOptionPane.showMessageDialog(null,"OTP Request isn't generated");}
		 }
		 else {JOptionPane.showMessageDialog(null,"Please Enter OTP");}
		 }
		 else {JOptionPane.showMessageDialog(null,"Please Select Account No");}
		 });
		 cb.setBounds(90, 25, 220, 25);
		 l1.setBounds(5, 10,100,50);
		 enterOTP.setBounds(90, 80, 220, 25);
		 l2.setBounds(5, 65,100,50);
		 but.setBounds(90, 135, 100, 30);
		 f.add(l2);
		 f.add(enterOTP);
		 f.add(but);
		 f.add(l1);
		 f.add(cb);
		 f.setLayout(null);
		 f.setSize(400,400);    
		 f.setVisible(true);   
			
		}

	public void AdminPaymentHistory() {	
	 JFrame f = new JFrame();
	f.getContentPane().setBackground(Color.pink);
	 JLabel l1 = new JLabel("ACCOUNT NO. ");
	 JButton but = new JButton("Search");
	 String[] accounts = ud.TotalAccountNo();
	 JComboBox cb = new JComboBox(accounts);
	 cb.setSelectedIndex(-1);
	 but.addActionListener(e->{
	 if(cb.getSelectedItem()!=null) {
	 Searched=ud.get(Integer.parseInt(cb.getSelectedItem().toString())-100);	 
	 PaymentHistory();
	 } 
	 });
	 cb.setBounds(90, 25, 150, 25);
	 l1.setBounds(5, 10,100,50);
	 but.setBounds(275, 22, 100, 30);
	 f.add(but);
	 f.add(l1);
	 f.add(cb);
	 f.setLayout(null);
	 f.setSize(400,400);    
	 f.setVisible(true);   
		
	}
	
	public void ADDBALANCE() {
	JFrame f = new JFrame();
	f.getContentPane().setBackground(Color.pink);
	JLabel l1 = new JLabel("ACCOUNT NO. ");
	JLabel l2 = new JLabel("AMOUNT: ");
	JTextField tf1 = new JTextField();
	JTextField tf2 = new JTextField();
	JButton but1 = new JButton("ADD BALANCE");
	but1.addActionListener(e->{
	if(check(tf1.getText()) && check(tf2.getText())) { 
	if(AcExist(Integer.parseInt(tf1.getText()))) {
	ud.AddBalance(Integer.parseInt(tf1.getText()), Double.parseDouble(tf2.getText()));
	f.setVisible(false);
	}
	else {
	JOptionPane.showMessageDialog(null,"Account Doesn't Exist!");
	}
	}
	else {
	s();
	}
	});
	tf1.setBounds(90, 25, 250, 25);
	l1.setBounds(5, 10,100,50);
	tf2.setBounds(90, 80, 250, 25);
	l2.setBounds(5, 67,100,50);
	but1.setBounds(90, 135, 120, 30);
	f.add(but1);
	f.add(l1);
	f.add(l2);
	f.add(tf1);
	f.add(tf2);
	f.setSize(400, 300);
	f.setLayout(null);
	f.setVisible(true);
	}
	public void Signup() {
	JFrame f = new JFrame();
	f.getContentPane().setBackground(Color.pink);
	JLabel l1 = new JLabel("NAME: ");
	JLabel l2 = new JLabel("CNIC #: ");
	JLabel l3 = new JLabel("Contact No: ");
	JLabel l4 = new JLabel("PASSWORD : ");
	JTextField tf1 = new JTextField();
	JTextField tf2 = new JTextField();
	JTextField tf3 = new JTextField();
	JTextField tf4 = new JTextField();
	JButton ca = new JButton("CREATE ACCOUNT");
	ca.addActionListener(e->{
	if(!tf1.getText().isEmpty() && !tf2.getText().isEmpty() && !tf3.getText().isEmpty() && !tf4.getText().isEmpty() ) {
	AccountNo++;
	Account newac = new Account(AccountNo,tf1.getText(),tf4.getText(),tf2.getText(),tf3.getText());
	ud.Insert(newac);
	JOptionPane.showMessageDialog(null,"Save This Account Number To Login Again\n" + AccountNo);
	f.setVisible(false);
	Searched = newac;
	Login();
	}
	else {
	JOptionPane.showMessageDialog(null, "Please Fill All Fields!");
	}
	});
	tf1.setBounds(90, 25, 250, 25);
	l1.setBounds(5, 10,100,50);
	tf2.setBounds(90, 80, 250, 25);
	l2.setBounds(5, 67,100,50);
	tf3.setBounds(90, 135, 250, 25);
	l3.setBounds(5, 124,100,50);
	tf4.setBounds(90, 190, 250, 25);
	l4.setBounds(5, 181,100,50);
	ca.setBounds(90, 240, 150, 30);
	f.add(l1);
	f.add(l2);
	f.add(l3);
	f.add(l4);
	f.add(tf1);
	f.add(tf2);
	f.add(tf3);
	f.add(tf4);
	f.add(ca);
	f.setSize(400, 400);
	f.setLayout(null);
	f.setVisible(true);
	}
	
	public void Login() {
		JFrame f = new JFrame();
		f.getContentPane().setBackground(Color.pink);
		JLabel l1 = new JLabel("Name: "+Searched.Title);
		JLabel l2 = new JLabel("AC NO: "+Searched.Account_No);
		JLabel l3 = new JLabel("Current Balance : "+Searched.Balance);
		JButton bp = new JButton("Bill Payment");
		JButton mt = new JButton("Money Transfer");
		JButton wo = new JButton("WithDraw OTP");
		JButton ph = new JButton("Paymet History");
		JButton lo = new JButton("Logout");
		lo.addActionListener(e->{
			Searched = null;
			f.setVisible(false);
			Panel();
		});
		l1.setBounds(5, 6,150,50);
		l2.setBounds(5, 25,100,50);
		l3.setBounds(240, 6,150,50);
		lo.setBounds(295, 45, 75, 20);
		bp.setBounds(70, 100, 250, 50);
		mt.setBounds(70, 175, 250, 50);
		wo.setBounds(70, 250, 250, 50);
		ph.setBounds(70, 325, 250, 50);
		f.add(l1);
		f.add(l2);
		f.add(l3);
		f.add(bp);
		f.add(mt);
		f.add(wo);
		f.add(ph);
		f.add(lo);
		f.setSize(400, 450);
		f.setLayout(null);
		f.setVisible(true);
		bp.addActionListener(e->{
			BillPayment(f);
		});
		mt.addActionListener(e->{
			MoneyTransfer(f);
		});
		wo.addActionListener(e->{
			WithDrawOTP();
		});
		ph.addActionListener(e->{
			PaymentHistory();
		});
		}
	public void PaymentHistory() {
		JFrame f = new JFrame();
		f.getContentPane().setBackground(Color.pink);
		 String data[][]= Searched.td.gettransaction();   
		 String column[]={"SNo. ","ACCOUNT NUMBER","AMOUNT","REASON"};         
		 JTable jt=new JTable(data,column); 
		 jt.setBounds(30,40,500,500);          
		 JScrollPane sp=new JScrollPane(jt); 
		 f.add(sp);          
		 f.setSize(900,400);    
		 f.setVisible(true);    
}
	
	public void BillPayment(JFrame a) {
		JFrame f = new JFrame();
		f.getContentPane().setBackground(Color.pink);
		JButton eb = new JButton("Electricity Bill");
		JButton wb = new JButton("Water Bill");
		JButton gb = new JButton("Gas Bill");
		eb.setBounds(70, 100-50, 250, 50);
		wb.setBounds(70, 175-50, 250, 50);
		gb.setBounds(70, 250-50, 250, 50);
		f.add(eb);
		f.add(wb);
		f.add(gb);
		f.setSize(400, 350);
		f.setLayout(null);
		f.setVisible(true);
		eb.addActionListener(e->{
			f.setVisible(false);
			Bill(100,"Electricity Bill",a);
		});
		wb.addActionListener(e->{
			f.setVisible(false);
			Bill(101,"Water Bill",a);
		});
		gb.addActionListener(e->{
			f.setVisible(false);
			Bill(102,"Gas Bill",a);
		});
}
	public void Bill(int acno,String reasons,JFrame a) {
		JFrame f = new JFrame();
		f.getContentPane().setBackground(Color.pink);
		JLabel l1 = new JLabel("Bill Number: ");
		JLabel l2 = new JLabel("Amount: ");
		JTextField tf1 = new JTextField();
		JTextField tf2 = new JTextField();
		JButton but = new JButton("PAY");
		tf1.setBounds(90, 25, 250, 25);
		l1.setBounds(5, 10,100,50);
		tf2.setBounds(90, 80, 250, 25);
		l2.setBounds(5, 67,100,50);
		but.setBounds(90, 140,100,30);
		but.addActionListener(e->{
			if(check(tf2.getText()) && !tf2.getText().isEmpty()) {
			if(Balance(Searched.Account_No,Double.parseDouble(tf2.getText()))) {
			f.setVisible(false);
			a.setVisible(false);
			ud.AddTransaction(Searched.Account_No, acno,Double.parseDouble(tf2.getText()),reasons+" With Bill No. " +tf1.getText());
			Login();
			}
			else {
			JOptionPane.showMessageDialog(null, "Insufficient Balance");
			}
			}
			else {
			s();
			}
			
		});
		f.add(l1);
		f.add(l2);
		f.add(tf1);
		f.add(tf2);
		f.add(but);
		f.setSize(400, 300);
		f.setLayout(null);
		f.setVisible(true);
	}	
	
	public void MoneyTransfer(JFrame a) {
		JFrame f = new JFrame();
		f.getContentPane().setBackground(Color.pink);
		JLabel l1 = new JLabel("Account No: ");
		JLabel l2 = new JLabel("Amount: ");
		JLabel l3 = new JLabel("Reason: ");
		JLabel l4 = new JLabel("Other: ");
		JTextField tf1 = new JTextField();
		JTextField tf2 = new JTextField();
		JTextField tf3 = new JTextField();
		String[] reason = {"Fee", "Internet", "Online Shopping", "Business", "Other"};
		JComboBox cb = new JComboBox(reason);
		cb.setSelectedIndex(-1);
		JButton but = new JButton("Transfer");
		but.addActionListener(e->{
		if (!tf1.getText().isEmpty() && !tf2.getText().isEmpty() && check(tf1.getText()) && cb.getSelectedItem()!=null) {
		if(AcExist(Integer.parseInt(tf1.getText()))){
		if(Balance(Searched.Account_No,Integer.parseInt(tf2.getText()))) {
		if(cb.getSelectedItem().toString().equalsIgnoreCase("Other")) {
		if(!tf3.getText().isEmpty()) {
		ud.AddTransaction(Searched.Account_No, Integer.parseInt(tf1.getText()), Integer.parseInt(tf2.getText()), tf3.getText());
		a.setVisible(false);
		Login();
		}
		else {JOptionPane.showMessageDialog(null,"Please Enter Reason");}
		}
		else {
		ud.AddTransaction(Searched.Account_No, Integer.parseInt(tf1.getText()), Integer.parseInt(tf2.getText()), cb.getSelectedItem().toString());
		a.setVisible(false);
		Login();
		}
		}
		else {JOptionPane.showMessageDialog(null, "Insufficient Balance");}
		}
		else {JOptionPane.showMessageDialog(null,"Account Doesn't Exist!");}
		}
		else {s();}
		});
		tf1.setBounds(90, 25, 250, 25);
		l1.setBounds(5, 10,100,50);
		tf2.setBounds(90, 80, 250, 25);
		l2.setBounds(5, 67,100,50);
		l3.setBounds(5, 124, 100, 50);
		cb.setBounds(90, 135,250,25);
		but.setBounds(90, 190,100,30);
		cb.addActionListener(e->{
			if (cb.getSelectedItem().toString().equalsIgnoreCase("Other")) {
				f.add(tf3);
				f.add(l4);
				l4.setBounds(5, 124+57, 100, 50);
				tf3.setBounds(90, 190, 250, 25);
				but.setBounds(90, 190+55,100,30);
				f.setSize(400, 400);
			}
			else {
				but.setBounds(90, 190,100,30);
				f.remove(tf3);
				f.remove(l4);
				f.setSize(400, 300);
			}
			
		});
		f.add(l1);
		f.add(l2);
		f.add(l3);
		f.add(tf1);
		f.add(tf2);
		f.add(but);
		f.add(cb);
		f.setSize(400, 300);
		f.setLayout(null);
		f.setVisible(true);
	}
	public void WithDrawOTP() {
		JFrame f = new JFrame();
		f.getContentPane().setBackground(Color.pink);
		Random r = new Random();
		JLabel l1 = new JLabel("AMOUNT : ");
		JTextField tf1 = new JTextField();
		JLabel l2 = new JLabel("OTP : ");
		JLabel l3 = new JLabel("");
		JButton but1 = new JButton("Generate OTP");
		but1.addActionListener(e->{
		int randomPIN = (int)(Math.random()*90000)+10000;
		if(!tf1.getText().isEmpty()) {
			if(check(tf1.getText())) {
				if(Balance(Searched.Account_No,Double.parseDouble(tf1.getText()))) {
				Searched.WithDrawAmount = Double.parseDouble(tf1.getText());
				Searched.RandomOTP = randomPIN;
				System.out.println(ud.get(Searched.Account_No-100).RandomOTP +", "+ud.get(Searched.Account_No-100).WithDrawAmount );
				l3.setText(randomPIN+"");
				}
				else {JOptionPane.showMessageDialog(null, "Insufficient Balance");}
		}
		else {s();}
		}
		else {
			JOptionPane.showMessageDialog(null,"Please Enter Amount");
		}
		});
		l1.setBounds(5, 10,100,50);
		tf1.setBounds(90, 25, 250, 25);
		l2.setBounds(5, 120, 100, 50);
		l3.setBounds(90, 120, 100, 50);
		but1.setBounds(90, 80, 120, 30);
		f.add(but1);
		f.add(l3);
		f.add(l2);
		f.add(l1);
		f.add(tf1);
		f.setSize(400, 300);
		f.setLayout(null);
		f.setVisible(true);

	}
	
	public void s() {JOptionPane.showMessageDialog(null,"Something Went Wrong");}
	public boolean check(String a) {
		try {
			int b = Integer.parseInt(a);
		}
		catch(Exception x){
			return false;
		}
		return true;
	} 
	public boolean AcExist(int ac) {
		if(ud.get(ac-100)==null) {
		return false;	
		}
		return true;
	}
	public boolean Balance(int ac,double trans) {
		if(ud.get(ac-100).Balance>trans) {
		return true;	
		}
		return false;	
		}
}
