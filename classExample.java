package com.Labprojects;

import java.util.Scanner;

public class classExample {

	public static void main(String[] args) {
	//created objects for both classes
		attributes sk = new attributes();
		employee emp = new employee();
     
        System.out.println("\t\t\t\t Welcome Employee Management");
        System.out.println(" choose 1 : for display all detail \n choose 2 : to increase salary \n choose 3 : to Downsize the employees ");
     
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        
        //using switch case for our main selection page 
        switch(x) {
        	
        	
        	case 1 :  {
        		//asigning a short word so that it could helpin method call display
        	     	 String n =  sk.name= "sandeep";
        	         String a= sk.address = "delhi";
        	         int i= sk.id=101;
        	         int s=  sk.salary=15000;
        	         String p= sk.phone="9717748672";
        		
        		emp.display(n,a,i,s,p);
        	}       
        	break;
        	case 2:  {
        		
        		System.out.println("enter the balance to hike");
        		int inc =sc.nextInt();
        		
        		 
        		int s=  sk.salary=15000;
        		
        		emp.salaryHike(inc,s);
        	}
        		break;
        		
        	case 3 :{
        	
                System.out.println("enter the salary to check weather \n you are fired or not ");
        		int s =sc.nextInt();
        		emp.DownSize(s);
        		
        	} 
        	
        	
        	
        	
        	
        	
        }

        sc.close();	//scannre close
   	     
	}

}






class employee{
	
	
	//both logic methods are in employye class
	
	void display(String n,String a,int i,int s,String p) {
		
    System.out.println(" Name of the employe : "+n);
    System.out.println(" Addresss of the employe : "+a);
    System.out.println(" ID of the employe : "+i);
    System.out.println(" salary  of the employe : "+s);
    System.out.println(" phone Number  of the employe : "+p);
	}
	
	void salaryHike(int inc,int s) {
		
		   System.out.println(" salary before  "+s);
		   s=s+inc;
		   System.out.println(" salary after hike "+s);
		   
		   
	}
	
	void DownSize(int s){
		if (s>15000) {
		
			System.out.println("we have to fire you : \n our company is facing financial crises \n and we are not liable to pay you anymore\n thanks regards \n ABC company ");
	}
		else {
			System.out.println("Welcome, employee \n its a nice day");
		}
		}
	
	
	
	
}




class attributes {
	String name,address,phone;
	int id,salary;
	
	
}