
public class UserAccount_DataStructure {
	private Account[] data;
	public int size;
	public UserAccount_DataStructure() {
		this(1);
	}
	public UserAccount_DataStructure(int inicap) {
		if(inicap>0)
		{
			this.data=  new Account[inicap];
		}
		else if(inicap==0)
		{
			this.data= new Account[0];
		}
	}
	public void ensurecapacity()
	{
		if(data.length<=size)
		{
			int oldcap=data.length;
			int newcap=oldcap +1;
			
			Account[] temp= new Account[newcap];
			for (int i = 0; i < data.length; i++) {
				temp[i]=data[i];
			}
			data=temp;
		}
	}
	public boolean Insert(Account value)
	{
		ensurecapacity();
		data[size]=value;
		size++;
		return true;
	}
	public void display()
	{
		System.out.print("[");
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i].Title + " , ");
		}
		System.out.print("]");
	}
	
	public Account get(int index)
	{
		if(index >=size || index<0)
		{
			return null;
		}
		 
		return data[index];
	}
	
	public String[] TotalAccountNo() {
		String[] a = new String[size];
		for(int i=0;i<size;i++) {
		a[i] = String.valueOf(100+i);
		}
		return a;
	}
	
	public void AddBalance(int Account,double Balance) {
		data[Account-100].Balance = data[Account-100].Balance + Balance;
	}
	public void AddTransaction(int User,int T_AN,double Amount,String reason) {
		data[User-100].Balance = data[User-100].Balance - Amount;
		data[T_AN-100].Balance = data[T_AN-100].Balance + Amount;
		data[User-100].td.insert_transaction(Amount,T_AN,reason);
	}
}
 