class Account {
	int Account_No;
	double Balance;
	String Title;
	String Password;
	String CNIC;
	String Contact;
	int RandomOTP;
	double WithDrawAmount;
	Transaction_DataStructure td = new Transaction_DataStructure();

public Account(int AN,String T,String P,String C,String CN) {
	this.Account_No = AN;
	this.Title=T;
	this.Password=P;
	this.CNIC=C;
	this.Contact=CN;
}

}