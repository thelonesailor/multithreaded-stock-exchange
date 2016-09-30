import java.io.IOException;
import java.util.*;

public class stock 
{


	//Perform I/O operation
public static long starttime;


static int f=0,temp,t0=0,texp=0,qty=0,price=0;
static String name="",type="",stck="",partial="";

public static int count=0,ans=0,ls=0;

synchronized void performAction(String as) throws java.io.IOException{
	//take string and enqueue it t=t0

	

int spaces=0,len=as.length(),i=0,flag=1;

//System.out.println((int)as.charAt(1));


for(i=0;i<len;++i)
{//System.out.print(as.charAt(i));
if(!(as.charAt(i)<=125 && 33<=as.charAt(i)))
{++spaces;//System.out.println(i);
while(i<len-1 && !(as.charAt(i)<=125 && 33<=as.charAt(i)))
{++i;}
}
}
//System.out.println(spaces);


try{
if(!(as.charAt(0)<='9' && '0'<=as.charAt(0)))
{
	flag=0;
    throw new java.io.IOException("invalid input");
}
}
catch (IOException e) {
	e.printStackTrace();
}


String T0="",name="",Texp="",type="",Qty="",stck="",Price="",partial="";
 
//typeoforder can be buy or sell

while(i<len-1 && !(as.charAt(i)<=125 && 33<=as.charAt(i)))
{++i;}

len=as.length();i=0;
for(;i<len;++i)
{if(as.charAt(i)<='9' && '0'<=as.charAt(i))
{T0+=as.charAt(i);}
else
{break;}
}
//System.out.println(" i= "+i+" "+T0+" ");

while(i<len-1 && !(as.charAt(i)<=125 && 33<=as.charAt(i)))
{++i;}
//System.out.print(" i= "+i);
for(;i<len;++i)
{if((as.charAt(i)<=125 && 33<=as.charAt(i)))
{name+=as.charAt(i);}
else
{break;}
}
while(i<len-1 && !(as.charAt(i)<=125 && 33<=as.charAt(i)))
{++i;}

try{
if(!(as.charAt(i)<='9' && '0'<=as.charAt(i)))  // 
{
	flag=0;
    throw new java.io.IOException("invalid input");
}
}
catch (IOException e) {
	e.printStackTrace();
}

for(;i<len;++i)
{if(as.charAt(i)<='9' && '0'<=as.charAt(i))
{Texp+=as.charAt(i);}
else
{break;}
}
while(i<len-1 && !(as.charAt(i)<=125 && 33<=as.charAt(i)))
{++i;}

for(;i<len;++i)
{if((as.charAt(i)<=125 && 33<=as.charAt(i)))
{type+=as.charAt(i);}
else
{break;}
}

type=type.toUpperCase();
//System.out.println(type+" "+type.charAt(0)+" "+type.length());

try{
if(!((type.charAt(0)=='B'&&type.charAt(1)=='U'&&type.charAt(2)=='Y') || (type.charAt(0)=='S'&&type.charAt(1)=='E'&&type.charAt(2)=='L'&&type.charAt(3)=='L')))
{
	flag=0;
    throw new java.io.IOException("invalid input");
}
}
catch (IOException e) {
	e.printStackTrace();
}

while(i<len-1 && !(as.charAt(i)<=125 && 33<=as.charAt(i)))
{++i;}

for(;i<len;++i)
{if(as.charAt(i)<='9' && '0'<=as.charAt(i))
{Qty+=as.charAt(i);}
else
{break;}
}




while(i<len-1 && !(as.charAt(i)<=125 && 33<=as.charAt(i)))
{++i;}

try{
if(!(as.charAt(i)<=125 && 33<=as.charAt(i)))  // qty>=0
{
	flag=0;
	System.out.println(as.charAt(i)+" "+(int)as.charAt(i));
    throw new java.io.IOException("invalid input");
}
}
catch (IOException e) {
	e.printStackTrace();
}


for(;i<len;++i)
{if((as.charAt(i)<=125 && 33<=as.charAt(i)))
{stck+=as.charAt(i);}
else
{break;}
}
while(i<len-1 && !(as.charAt(i)<=125 && 33<=as.charAt(i)))
{++i;}

int i2=i;
for(;i<len;++i)
{if((as.charAt(i)<='9' && '0'<=as.charAt(i)) || (as.charAt(i)=='-' && i==i2))
{Price+=as.charAt(i);}
else
{break;}
}
while(i<len-1 && !(as.charAt(i)<=125 && 33<=as.charAt(i)))
{++i;}

for(;i<len;++i)
{if((as.charAt(i)<=125 && 33<=as.charAt(i)))
{partial+=as.charAt(i);}
else
{break;}

partial=partial.toUpperCase();

try
{
if((partial.charAt(0)!='F' && partial.charAt(0)!='T') || partial.length()!=1)  // =='T'
{
	flag=0;
	System.out.println(partial+" "+partial.length());
    throw new java.io.IOException("invalid input");
}
}
catch (IOException e) {
	e.printStackTrace();
}


}

//System.out.print(" i= "+i+"  ");
// **!! have to catch all exeptions in the input file !!**


/*System.out.print(T0+" ");
System.out.print(name+" ");
System.out.print(Texp+" ");
System.out.print(type+" ");
System.out.print(Qty+" ");
System.out.print(stck+" ");
System.out.print(Price+" ");
System.out.print(partial+" ");
System.out.println();*/

//name,type,stck,partial : remain the same

Scanner s=new Scanner(as);

if(s.hasNext())
{T0=s.next();}

try
{
if(!('0'<=T0.charAt(0) && T0.charAt(0)<='9'))
{flag=0;
throw new java.io.IOException("invalid input");
	}
}
catch (IOException e) {
	e.printStackTrace();
}

if(s.hasNext())
{name=s.next();}

if(s.hasNext())
{Texp=s.next();}

if(s.hasNext())
{type=s.next();type=type.toUpperCase();}


if(s.hasNext())
{Qty=s.next();}

if(s.hasNext())
{stck=s.next();}

if(s.hasNext())
{Price=s.next();}

try{

if(s.hasNext())
{partial=s.next();}
else
{flag=0;
throw new java.io.IOException("invalid input");}
}
catch (IOException e) {
	e.printStackTrace();
}
s.close();

int temp,t0=0,texp=0,qty=0,price=0;


if(T0.charAt(0)<='9' && '0'<=T0.charAt(0))
{
len=T0.length();
temp=1;
for(i=len-1;i>=0;--i)
{
	t0+=(T0.charAt(i)-'0')*temp;
	temp*=10;
}
}
else
{flag=0;}

if(Texp.charAt(0)<='9' && '0'<=Texp.charAt(0))
{
len=Texp.length();
temp=1;
for(i=len-1;i>=0;--i)
{
	texp+=(Texp.charAt(i)-'0')*temp;
	temp*=10;
}
}

len=Qty.length();
temp=1;
for(i=len-1;i>=0;--i)
{
	qty+=(Qty.charAt(i)-'0')*temp;
	temp*=10;
}

len=Price.length();
temp=1;
for(i=len-1;i>=0;--i)
{
	if(Price.charAt(i)!='-')
	{price+=(Price.charAt(i)-'0')*temp;}
	else if(Price.charAt(i)=='-' && i==0)
	{price*=-1;}	
	temp*=10;
}

/*System.out.print(as.length()+" ");
System.out.print(t0+" ");
System.out.print(name+" ");
System.out.print(texp+" ");
System.out.print(type+" ");
System.out.print(qty+" ");
System.out.print(stck+" ");
System.out.print(price+" ");
System.out.println(partial+" ");
*/


stock.t0=t0;stock.name=name;
stock.texp=texp;stock.type=type;
stock.qty=qty;stock.stck=stck;
stock.price=price;stock.partial=partial;

node o= new node();
o.t0=t0;o.name=name;o.texp=texp;o.type=type;
o.qty=qty;o.stck=stck;o.price=price;o.partial=partial;
o.good=flag;

listoforders.add(o);
stock.allorders[stock.ls]=as;
++stock.ls;

//System.out.println(listoforders.size());


//System.out.println("count= "+count);
//System.out.println("time= "+(System.currentTimeMillis()-stock.starttime)/100);

}
public static Vector <node> listoforders=new Vector <node> ();


// make queue and two lists here

	  public static class node
	  {
		  Integer t0=0,texp=0,qty=0,price=0,good=1;
		  String name="",type="",stck="",partial="";
		  
		  node(node o)
		  {
			  t0=o.t0;texp=o.texp;qty=o.qty;price=0;good=1;
			  name=o.name;type=o.type;stck=o.stck;partial=o.partial;
		  }
		  
		  node()
		  {
			  t0=0;texp=0;qty=0;price=0;good=1;
			  name="";type="";stck="";partial="";
		  }
		  
		  
		  public void print()
		  {
			  System.out.print(t0+" ");
			  System.out.print(name+" ");
			  System.out.print(texp+" ");
			  System.out.print(type+" ");
			  System.out.print(qty+" ");
			  System.out.print(stck+" ");
			  System.out.print(price+" ");
			  System.out.println(partial+" ");
		  }
		  
		  node next;
	  }
	  public static Vector <node> queue=new Vector <node> ();
	
	  synchronized public static void enq(node o)
	  {
		  
		  ++count;
	
		  queue.add(o);
	//	  System.out.println("count= "+count);
	//	  o.print();
		 		  
	  }
	 
	  //add(node o)
	  //remove(int i) //used i=0 always
	  //node get(int i)
	  
	  
	   public class list
	   {
	   
	   int sze=0;
	   node start,end;  // remove from front and add at back
	      
	      /*list()  // constructor
	      {start=null;
	      end=null;
	      sze=0;}*/
	      
	      synchronized public void add(node o)
	      {
	    	  if(sze==0)
	  	    {
	  	    	end=o;
	  	    	start=o;
	  	    	start.next=end.next=null;
	  	    }
	    	  else if(sze==1)
	  	    {
	    		start=end;
	  	    	start.next=o;
	  	    	 
	  		     end=o;
	  		     end.next=null;	
	  	    }
	    	  else if(sze>1)
	    {
	      
	      end.next=o;
	      end=end.next;
	      end.next=null;
	    } 
	  
	  
	      if(sze==0)
	      {start=end;}
	      
	      ++sze;
	      }
	      
	     
	      
	      synchronized public node remove (int index)
	      {
	   
	      if(index==0)
	      {stock.node temp;
	      temp=start;
	      --sze;
	      if(sze>0)
	      {start=start.next;}
	      
	      if(sze==0)
	      {end=start=null;}
	      
	      return temp;
	      }
	      else
	      {
	    	  node temp=start;
	          for(int i=0;i<index-1;++i)
	          {
	        	  temp=temp.next;
	          }
	          node u;
	          u=temp.next;
	          
	          if(temp.next!=null)
	          {temp.next=temp.next.next;}
	          else
	          {temp.next=null;}
	          return u;
	      }
	    	  
	    	
	    	  
	      }
	      
	      synchronized public node get(int index)
	      {
	
	      node temp;
	      temp=start;
	      
	      for(int i=0;i<index;++i)
	      {
	    	  if(temp!=null)
	    	  temp=temp.next;
	      }
	      if(temp!=null)
	      {return temp;}
	      else
	      return null;  
	      }
	      
	      
	      synchronized public int size()
	      {return sze;}
	      
	   }
	   
	
	   static stock x=new stock();
		//  public static list selllist=x.new list ();
	      public static Vector <node> selllist=new Vector <node> ();
		 
		  public static Vector <node> buylist=new Vector <node> ();
		  public static String[] allorders=new String [10000];
		
	  
}
