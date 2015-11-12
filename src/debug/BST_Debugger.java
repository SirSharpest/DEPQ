package debug;


import cs21120.depq.nah26DEPQ;

public class BST_Debugger {

	public static void main(String[] args) {
		
		
		nah26DEPQ myDEPQ = new nah26DEPQ();
		
		myDEPQ.add(20);
		myDEPQ.add(1);
		myDEPQ.add(3);
		myDEPQ.add(2);
		myDEPQ.add(1);
		myDEPQ.add(3);
		myDEPQ.add(100);
		myDEPQ.add(-1);
		myDEPQ.add(-5);
		myDEPQ.add(-20);
		myDEPQ.add(1);
		myDEPQ.add(3);
		myDEPQ.add(2);
		myDEPQ.add(1);
		myDEPQ.add(3);
		
		//System.out.println(myDEPQ.inspectLeast());

		//System.out.println(myDEPQ.inspectMost());


		System.out.println(myDEPQ.getLeast());

	//	System.out.println(myDEPQ.getMost());
	}

}
