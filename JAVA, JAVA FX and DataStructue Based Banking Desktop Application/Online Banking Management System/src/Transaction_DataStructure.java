
public class Transaction_DataStructure {
	private Node head, tail;
	
	public void insert_transaction(double trans,int Acno,String reason) {
		Node node = new Node(trans,Acno,reason);
		
		if(head == null) {
			head = tail = node;
			return;
		}
		
		tail.next = node;
		tail = node;
	}
	
	public String[][] gettransaction(){
		String[][] arr = new String[20][4];
		if (head==null) {return arr;}
		int index=0;
		Node n = head;
		while(n!=null) {
		arr[index][0]=(index+1)+"";
		arr[index][1]=n.Account_No+"";
		arr[index][2]=n.Transaction+"";
		arr[index][3]=n.reason;
		index++;
		n=n.next;
		}
		return arr;
	}
	public void display() {
	}
}


class Node {
	int Account_No;
	double Transaction;
	String reason;
	Node next;
	
	Node(double data1,int data2,String reason){
		this.Transaction = data1;
		this.Account_No = data2;
		this.reason=reason;
		this.next = null;
	}
}
