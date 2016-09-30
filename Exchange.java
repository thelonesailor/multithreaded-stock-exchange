public class Exchange{
	//match orders

//maintain two lists one for buy order and one for sell orders

//the sell list should be in increasing order of prices[0 to n-1]
//the buy list should be in decreasing order of prices[0 to n-1]

static public Boolean sorted=false;
static int n; 

synchronized public static void mleo()  // maintain order and execute orders
{
			
	// take orders from the queue and execute it
	// an order can be either
		/*
		 * 1.full satisfied : don't add to any queue
		 * 2.partially satisfied : edit order and add to list 
		 */
		
	//!!Remember to edit the partial orders in the list 
	 while(stock.queue.size()>0)
	{int i;
	//	 stock x=new stock();
		stock.node temp=new stock.node();
		if(stock.queue.size()>0)
		{
			temp=stock.queue.remove(0);
		//	System.out.println("Order removed from queue");
			test.exchdequeued++;
		
		}
		
		if((System.currentTimeMillis()-stock.starttime)/100>temp.t0+temp.texp) //expired
		{continue;}
		try
		   {
		   Thread.sleep(1);  // be careful !!! 
		   }
		   catch(InterruptedException e)
		   {}
		
		if(temp.type.charAt(0)=='B')  //order is of buy type
		{
		
			//TODO
			//search sell list 
			 			
	/*		synchronized(stock.selllist)
			{
				synchronized(stock.buylist)
				{*/
			
			if(stock.selllist.size()==0)  // sell list empty
			{
			//	System.out.println("order added to buy list");
				stock.buylist.add(temp);	
				test.exchout("P",0,temp);
			}
			else  
			{
				long min=10000000000000000L;
				int r=temp.qty,mini=-1;
				for(i=0;i<stock.selllist.size();i++)
				{
				
if(stock.selllist.get(i)!=null && i<stock.selllist.size() && (System.currentTimeMillis()-stock.starttime)/100<=stock.selllist.get(i).t0+stock.selllist.get(i).texp)
{
if(i<stock.selllist.size() && stock.selllist.get(i).stck.equals(temp.stck) && min>stock.selllist.get(i).price && stock.selllist.get(i).qty>0)
{
if(i<stock.selllist.size() && stock.selllist.get(i).price<temp.price && (stock.selllist.get(i).qty<=r || (stock.selllist.get(i).qty>r && stock.selllist.get(i).partial.equals("T"))))
    {
							min=stock.selllist.get(i).price;
							mini=i;
	}
}
				}		
				}
				
				// min is the sell price
				
				// mini has been picked
				if(min<10000000000000000L && mini>=0)  
				{
					if(temp.partial.equals("F"))
					{
						
						if(stock.selllist.get(mini).partial.equals("F"))
						{
														
							if(stock.selllist.get(mini).qty==temp.qty)
							{
							//output
								stock.ans+=(temp.price-stock.buylist.get(mini).price)*(temp.qty);
								
								test.exchout("T",temp.qty,temp);
								test.cp();
								
								stock.node t2=stock.selllist.remove(mini); // order from sell list has to be removed
								t2.qty=0;
								stock.selllist.add(t2);
								
								
							}
							else
							{stock.buylist.add(temp);
							test.exchout("P",0,temp);}
							
						}
						else if(stock.selllist.get(mini).partial.equals("T"))
						{
							if(stock.selllist.get(mini).qty>=temp.qty)
							{
								stock.ans+=(temp.price-stock.selllist.get(mini).price)*(temp.qty);
								//temp is fully satisfied, don't add to any list 
								test.exchout("T",temp.qty,temp);
								test.cp();
								
								stock.node t2=stock.selllist.remove(mini);
								t2.qty-=temp.qty;
								stock.selllist.add(t2);
								
								
							}
							else
							{stock.buylist.add(temp);
							test.exchout("P",0,temp);}
						}
						//stock.ans+=(temp.price-stock.buylist.get(maxi).price)*(temp.qty);
						
						
						
					}
					else if(temp.partial.equals("T"))
					{
						if(stock.selllist.get(mini).partial.equals("F"))
						{
							if(stock.selllist.get(mini).qty<temp.qty)
							{
								stock.ans+=(temp.price-stock.selllist.get(mini).price)*(stock.selllist.get(mini).qty);
								test.exchout("T",stock.selllist.get(mini).qty,temp);
								
								//output temp partially satisfied
								temp.qty-=stock.selllist.get(mini).qty;
								stock.buylist.add(temp);
								test.exchout("P",0,temp);
								
								stock.node t2=stock.selllist.remove(mini);
								t2.qty=0; //output this satisfied order 
								stock.selllist.add(t2);
								test.cp();
							}
							else if(stock.selllist.get(mini).qty==temp.qty)
							{
								stock.ans+=(temp.price-stock.selllist.get(mini).price)*(temp.qty);
								// output temp, temp is fully satisfied
								test.exchout("T",temp.qty,temp);
								
								stock.node t2=stock.selllist.remove(mini);
								t2.qty=0;
								stock.selllist.add(t2);
								
								test.cp();
							}
							else
							{stock.buylist.add(temp);
							test.exchout("P",0,temp);}
						}
						else if(stock.selllist.get(mini).partial.equals("T"))
						{
							if(stock.selllist.get(mini).qty<temp.qty)
							{
								stock.ans+=(temp.price-stock.selllist.get(mini).price)*(stock.selllist.get(mini).qty);
								
							test.exchout("T",stock.selllist.get(mini).qty,temp);
							//output temp partially satisfied
							temp.qty-=stock.selllist.get(mini).qty;
							stock.buylist.add(temp);
							test.exchout("P",0,temp);
							
							stock.node t2=stock.selllist.remove(mini);
							t2.qty=0; //output this satisfied order 
							stock.selllist.add(t2);
							
							}
							else if(stock.selllist.get(mini).qty>=temp.qty)
							{
								stock.ans+=(temp.price-stock.selllist.get(mini).price)*(temp.qty);
								//output temp fully satisfied
								test.exchout("T",temp.qty,temp);
								
								stock.node t2=stock.selllist.remove(mini);
							t2.qty-=temp.qty; //output this satisfied order (can be fully or partially)
							stock.selllist.add(t2);
							
							
							test.cp();
							
							}
					
						}
					}
					
				}
				else  // add to buy list
				{
				//	System.out.println("Order added to buy list");
					stock.buylist.add(temp);
					test.exchout("P",0,temp);
				}
			}
			//}}
			}
			
		
			
			
		//TODO
		else if(temp.type.charAt(0)=='S')
		{
			//search buy list 
			 			
			/*synchronized(stock.selllist)
			{
				
				synchronized(stock.buylist)
				{
					*/
			
			if(stock.buylist.size()==0)  // buy list empty
			{
				stock.selllist.add(temp);		
				test.exchout("S",0,temp);
				
			}
			else  
			{
				int max=0,maxi=-1;
				for(i=0;i<stock.buylist.size();++i)
				{
				int r=temp.qty;
				
if(/*i<stock.buylist.size() &&*/ (System.currentTimeMillis()-stock.starttime)/100<=stock.buylist.get(i).t0+stock.buylist.get(i).texp)
{
if(stock.buylist.get(i).stck.equals(temp.stck) && max<stock.buylist.get(i).price && stock.buylist.get(i).qty>0)
{
if(stock.buylist.get(i).price>temp.price && (stock.buylist.get(i).qty<=r || (stock.buylist.get(i).qty>r && stock.buylist.get(i).partial.equals("T"))))
    {
							max=stock.buylist.get(i).price;
							maxi=i;
	}
}
}
				}
				
				// max is the buy price
			//	System.out.println("maxi= "+maxi+" "+max);
				
				// maxi has been picked
				if(max>0 && maxi>=0)  
				{
					if(temp.partial.equals("F"))
					{
						// temp is satisfied
						if(stock.buylist.get(maxi).partial.equals("F"))
						{
							//output
							
							if(stock.buylist.get(maxi).qty==temp.qty)
							{
							//output
								stock.ans+=(stock.buylist.get(maxi).price-temp.price)*(temp.qty);
								
								test.exchout("T",temp.qty,temp);
								test.cp();
								
								stock.node t2=stock.buylist.remove(maxi); // order from buy list has to be removed
								t2.qty=0;
								stock.buylist.add(t2);
								
								
							}
							else
							{stock.selllist.add(temp);
							test.exchout("S",0,temp);}
							
						}
						else if(stock.buylist.get(maxi).partial.equals("T"))
						{
							if(stock.buylist.get(maxi).qty>=temp.qty)
							{
								stock.ans+=(stock.buylist.get(maxi).price-temp.price)*(temp.qty);
								//temp is fully satisfied, don't add to any list 
								test.exchout("T",temp.qty,temp);test.cp();
								
								stock.node t2=stock.buylist.remove(maxi);
								t2.qty-=temp.qty;
								stock.buylist.add(t2);
								
								
							}
							else
							{stock.selllist.add(temp);
							test.exchout("S",0,temp);}
						}
						//stock.ans+=(temp.price-stock.buylist.get(maxi).price)*(temp.qty);
						
						
						
					}
					else if(temp.partial.equals("T"))
					{
						if(stock.buylist.get(maxi).partial.equals("F"))
						{
							if(stock.buylist.get(maxi).qty<temp.qty)
							{
								stock.ans+=(stock.buylist.get(maxi).price-temp.price)*(stock.buylist.get(maxi).qty);
								
							
								
								//output temp partially satisfied
								
								test.exchout("T",stock.buylist.get(maxi).qty,temp);
								temp.qty-=stock.buylist.get(maxi).qty;
								
								test.exchout("S",0,temp);
								stock.selllist.add(temp);
								
								stock.node t2=stock.buylist.remove(maxi);
								t2.qty=0; //output this satisfied order 
								stock.buylist.add(t2);
							}
							else if(stock.buylist.get(maxi).qty==temp.qty)
							{
								stock.ans+=(stock.buylist.get(maxi).price-temp.price)*(temp.qty);
								stock.node t2=stock.buylist.remove(maxi);
								t2.qty=0;
								stock.buylist.add(t2);
								// output temp, temp is satisfied
								
							}
							else
							{stock.selllist.add(temp);
							test.exchout("S",0,temp);}
						}
						else if(stock.buylist.get(maxi).partial.equals("T"))//TODO
						{
							
							if(stock.buylist.get(maxi).qty<temp.qty)
							{
							stock.ans+=(stock.buylist.get(maxi).price-temp.price)*(stock.buylist.get(maxi).qty);
							
							temp.qty-=stock.buylist.get(maxi).qty;
							
							
							test.exchout("T",stock.buylist.get(maxi).qty,temp);
							//output temp partially satisfied
							
							stock.selllist.add(temp);
							test.exchout("S",temp.qty,temp);
							stock.node t2=stock.buylist.remove(maxi);
							t2.qty=0; //output this satisfied order 
							stock.buylist.add(t2);
							
					//		System.out.println("2..maxi= "+maxi+" "+stock.ans);
							}
							else if(stock.buylist.get(maxi).qty>=temp.qty)
							{
								stock.ans+=(stock.buylist.get(maxi).price-temp.price)*(temp.qty);
								
								
								//output temp fully satisfied
								test.exchout("T",temp.qty,temp);
								
							stock.node t2=stock.buylist.remove(maxi);
							t2.qty-=temp.qty; //output this satisfied order (can be fully or partially)
							stock.buylist.add(t2);
											
							
							}
					
						}
					}
					
				}
				else  // add to sell list
				{
					stock.selllist.add(temp);
					test.exchout("S",0,temp);
				}
				
			}
				}
			}
		//}}
	}
	
	


}
