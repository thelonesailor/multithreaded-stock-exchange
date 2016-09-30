import java.io.*;

class ordergen implements Runnable
{


		public void run()
		{
			 
			 while(stock.listoforders.size()==0)
			 {}
			     
		    while((test.orderqueued<stock.ls || stock.listoforders.size()>0) && (System.currentTimeMillis()-stock.starttime)/100<80)
			{
		    
		    stock.node o= new stock.node();
		    if(stock.listoforders.size()>0)
		    {o=stock.listoforders.remove(0);}
		    else
		    {continue;}
		    
		    if(o.good==0)
		    {test.badorderout(test.orderqueued);test.neq();continue;}
		    else
		    {test.exchdequeued++;}
		    
		    int t0=o.t0,texp=o.texp;/*qty=o.qty,price=o.price;
			String name=o.name,type=o.type,partial=o.partial;*/
       
	   if(t0<=(System.currentTimeMillis()-stock.starttime)/100 && (System.currentTimeMillis()-stock.starttime)/100<=t0+texp)
	   {stock.enq(o);test.neq();
	   long tx=(System.currentTimeMillis()-stock.starttime)/100;
	   test.orderout(tx,o);}
	   else if((System.currentTimeMillis()-stock.starttime)/100<t0) //wait
	   {
		    while((System.currentTimeMillis()-stock.starttime)/100<t0)
		   {//int c=123456*123456789;
			   }
		   /*try
		   {
		   Thread.sleep(t0-(System.currentTimeMillis()-stock.starttime)/100 -1);  // be careful !!! TODO 
		   }
		   catch(InterruptedException e)
		   {}*/

		   long tx=(System.currentTimeMillis()-stock.starttime)/100;
		   stock.enq(o);
		   test.neq();
		   
		   test.orderout(tx,o);
		   
	   }
	   else if(t0+texp<(System.currentTimeMillis()-stock.starttime)/100) //do nothing
	   {}
	   
	
	   try
	   {
	   Thread.sleep(1);  // be careful !!! TODO important don't remove*****
	   }
	   catch(InterruptedException e)
	   {}

	   
		}  
}
}


	class exch implements Runnable
	{

		public void run()
		{
			//maintain two lists of orders
		
	//		System.out.println("Exchange started1");
			while(stock.queue.size()<=0)
			{}
			 try
			   {
			   Thread.sleep(20);  // be careful !!! TODO  
			   }
			   catch(InterruptedException e)
			   {}
	//		System.out.println("Exchange started2 "+stock.count+"  "+stock.ls);
			while(stock.queue.size()>0 || test.exchdequeued<stock.ls || (System.currentTimeMillis()-stock.starttime)/100<80)
			{
				
     			   Exchange.mleo();
				
     			 /*  if(stock.queue.size()<=0)
     			   {
				   try
				   {
				   Thread.sleep(2);  // be careful !!! TODO 
				   }
				   catch(InterruptedException e)
				   {}
     			   }*/
				  
			}
			
		//	System.out.println("ans= "+stock.ans);
			try
			{
			FileOutputStream fs = new FileOutputStream ("Exchange.txt",true );
			PrintStream ex = new PrintStream (fs );
			
			ex.println("Total profit= "+stock.ans);
			ex.close();
			}
			catch(FileNotFoundException e)
			{}
		}
	}

		class cleanup implements Runnable{

		public void run()
		{
			   try
			   {
			   Thread.sleep(200);  // be careful !!! TODO 
			   }
			   catch(InterruptedException e)
			   {}
		
		
			stock.node temp=new stock.node ();
			
			
			
			while(stock.buylist.size()<=0 && stock.selllist.size()<=0)
				{}
	//		System.out.println("cleanup started");
			 while((stock.buylist.size()>0 || stock.selllist.size()>0) && (System.currentTimeMillis()-stock.starttime)/100<80)
			{
				
				for(int i=0;i<stock.buylist.size();++i)
				{
				
					if(i<stock.buylist.size() && (System.currentTimeMillis()-stock.starttime)/100 >stock.buylist.get(i).t0+stock.buylist.get(i).texp)
					{
					
							long tx=(System.currentTimeMillis()-stock.starttime)/100;
						//output
			//			System.out.println("Order removed from buylist");
						  // expired
						temp=stock.buylist.remove(i);
						test.cleanupout(tx,temp);
					    
						test.cp();
						if(i>0)
						--i;
						
						
			//			System.out.println("test.cleanupremoved= "+test.cleanupremoved);
					
					}
					else if(i<stock.buylist.size() && stock.buylist.get(i).qty==0)
					{
						
                        //output
						long tx=(System.currentTimeMillis()-stock.starttime)/100;
			//			System.out.println("Order removed from buylist");
						temp=stock.buylist.remove(i);  // expired
						test.cleanupout(tx,temp);
						
						test.cp();
						if(i>0)
						--i;
			//			System.out.println("test.cleanupremoved= "+test.cleanupremoved);
					}
					
				}
				
			
				
				for(int i=0;i<stock.selllist.size();++i)
				{
				
					if( i<stock.selllist.size() && (System.currentTimeMillis()-stock.starttime)/100 >stock.selllist.get(i).t0+stock.selllist.get(i).texp)
					{
						long tx=(System.currentTimeMillis()-stock.starttime)/100;
						temp=stock.selllist.remove(i);  // expired
						test.cleanupout(tx,temp);
						
						test.cp();
						if(i>0)
						--i;
				//		System.out.println("test.cleanupremoved= "+test.cleanupremoved);
					}
					else if( i<stock.selllist.size() && stock.selllist.get(i).qty==0)
					{
					
                        //output
						long tx=(System.currentTimeMillis()-stock.starttime)/100;
				//		System.out.println("Order removed from selllist");
						temp=stock.selllist.remove(i);  // expired
						test.cleanupout(tx,temp);
						
						test.cp();
						if(i>0)
						--i;
				//		System.out.println("test.cleanupremoved= "+test.cleanupremoved);
					}
					
				}
				
				
		/*if(test.cleanupremoved==stock.ls)
          {break;}*/
		
		
			}
			
		//	System.out.println("ans= "+stock.ans);
			 System.out.println(test.cleanupremoved);
		}
		}


public class test /*implements Runnable*/
{
//Thread wrapper class
	

static public int orderqueued=0,exchdequeued=0,cleanupremoved=0;

test()
{orderqueued=0;exchdequeued=0;cleanupremoved=0;}
static public void st()
{
	
	stock.starttime=System.currentTimeMillis();
	//System.out.println("ls= "+stock.ls);
	orderqueued=0;exchdequeued=0;cleanupremoved=0;
	
	ordergen thread1 = new ordergen();
	Thread tobj1= new Thread(thread1);
	tobj1.start(); 

	exch thread2 = new exch();
	Thread tobj2= new Thread(thread2);
	tobj2.start(); 

	cleanup thread3 = new cleanup();
	Thread tobj3= new Thread(thread3);
	tobj3.start();
	
	
	 try
	{
	tobj1.join();
	tobj2.join();
	tobj3.join();
	}
    catch(InterruptedException e)
	{
		e.printStackTrace();
	}
//	 System.out.println("test.cleanupremoved= "+test.cleanupremoved);
}

synchronized static public void cp()
{test.cleanupremoved++;}
synchronized static public void neq()
{test.orderqueued++;}


synchronized static public void cleanupout(long tx,stock.node o)
{
	try{
		
		FileOutputStream fs = new FileOutputStream ("cleanup.txt",true );
		PrintStream clean = new PrintStream (fs );
		if(o!=null)
		{		  clean.print(tx+" ");
			  clean.print(o.t0+" ");
			  clean.print(o.name+" ");
			  clean.print(o.texp+" ");
			  clean.print(o.type+" ");
			  clean.print(o.qty+" ");
			  clean.print(o.stck+" ");
			  clean.print(o.price+" ");
			  clean.println(o.partial+" ");
	}
			  clean.close();
	
	}
			catch(FileNotFoundException e)
			{}	
		  
}

static public void orderout(long tx,stock.node o)
{
	try{
		
		FileOutputStream fs = new FileOutputStream ("order.txt",true );
		PrintStream order = new PrintStream (fs );
			  order.print(tx+" ");
			  order.print(o.t0+" ");
			  order.print(o.name+" ");
			  order.print(o.texp+" ");
			  order.print(o.type+" ");
			  order.print(o.qty+" ");
			  order.print(o.stck+" ");
			  order.print(o.price+" ");
			  order.println(o.partial+" ");
			  order.close();
	
	}
			catch(FileNotFoundException e)
			{}	
		  
}

synchronized static public void badorderout(int index)
{
	try{
		
		FileOutputStream fs = new FileOutputStream ("order.txt",true );
		PrintStream order = new PrintStream (fs );
		      order.print("EXCEPTION ");
			  order.print((System.currentTimeMillis()-stock.starttime)/100+" ");
			  order.println(stock.allorders[index]);
			  order.close();
	
	}
			catch(FileNotFoundException e)
			{}	
		  
}

synchronized static public void exchout(String s,int status,stock.node o)
{
	try{
		
		FileOutputStream fs = new FileOutputStream ("Exchange.txt",true );
		PrintStream exch = new PrintStream (fs );
		      
		      exch.print(s+" ");
			  exch.print((System.currentTimeMillis()-stock.starttime)/100+" ");
			  exch.print(status+" ");
			  exch.print(o.t0+" ");
			  exch.print(o.name+" ");
			  exch.print(o.texp+" ");
			  exch.print(o.type+" ");
			  exch.print(o.qty+" ");
			  exch.print(o.stck+" ");
			  exch.print(o.price+" ");
			  exch.println(o.partial+" ");
			  exch.close();
	
	}
			catch(FileNotFoundException e)
			{}	
		  
}

}
		



