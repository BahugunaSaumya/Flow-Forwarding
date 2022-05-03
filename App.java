//package a1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class App extends Node {
	Scanner sc= new Scanner(System.in);
		private InetAddress dstAddress;
		private byte socketNumber;
		private static final String RECEIVE = "REC";
		private static final String SEND = "SEN";
		static  byte src;
		static  byte dst;
		public static HashMap<InetSocketAddress,Byte> address;
		public static HashMap<Byte,InetSocketAddress> addr;
		public static HashMap<Byte,String> values;
        //private InetSocketAddress Iaddress;
		 public static void fillvalues() {
			 values.put(R1,"Rone");
			 values.put(R2,"Rtwo");
			 values.put(R3,"Rthree");
			 values.put(R4,"Rfour");
			 values.put(R5,"Rfive");
			 values.put(R6,"Rsix");
			 values.put(R7,"Rseven");
			 values.put(R8,"Reight");
			 values.put(E1,"aone");
			 values.put(E2,"atwo");
			 values.put(E3,"athree");
			 values.put(E4,"afour");
			
				
		 }
		public static void loadaddr() throws UnknownHostException {
			InetSocketAddress a=add(values.get(R1));
			addr.put(R1,a);
			InetSocketAddress b=add(values.get(R2));
			addr.put(R2,b);
			InetSocketAddress c=add(values.get(R3));
			addr.put(R3,c);
			InetSocketAddress d=add(values.get(R4));
			addr.put(R4,d);
			InetSocketAddress e=add(values.get(R5));
			addr.put(R5,e);
			InetSocketAddress f=add(values.get(R6));
			addr.put(R6,f);
			InetSocketAddress g=add(values.get(R7));
			addr.put(R7,g);
			InetSocketAddress h=add(values.get(R8));
			addr.put(R8,h);
			InetSocketAddress i=add(values.get(E1));
			addr.put(E1,i);
			InetSocketAddress j=add(values.get(E2));
			addr.put(E2,j);
			InetSocketAddress k=add(values.get(E3));
			addr.put(E3,k);
			InetSocketAddress l=add(values.get(E4));
			addr.put(E4,l);
			
			
		}
	public static void loadaddress() throws UnknownHostException {
		InetSocketAddress a=add(values.get(R1));
		address.put(a,R1);
		InetSocketAddress b=add(values.get(R2));
		address.put(b,R2);
		InetSocketAddress c=add(values.get(R3));
		address.put(c,R3);
		InetSocketAddress d=add(values.get(R4));
		address.put(d,R4);
		InetSocketAddress e=add(values.get(R5));
		address.put(e,R5);
		InetSocketAddress f=add(values.get(R6));
		address.put(f,R6);
		InetSocketAddress g=add(values.get(R7));
		address.put(g,R7);
		InetSocketAddress h=add(values.get(R8));
		address.put(h,R8);
		InetSocketAddress i=add(values.get(E1));
		address.put(i,E1);
		InetSocketAddress j=add(values.get(E2));
		address.put(j,E2);
		InetSocketAddress k=add(values.get(E3));
		address.put(k,E3);
		InetSocketAddress l=add(values.get(E4));
		address.put(l,E4);
		//countinuation is not shown in the listing for size reasons
		
	}
	public static InetSocketAddress add(String s) throws UnknownHostException {
		InetAddress dst= InetAddress.getByName(s);
		InetSocketAddress dstaddress= new InetSocketAddress(dst, PORT_NUMBER);
	return dstaddress;	
	}
		App() throws SocketException, UnknownHostException {
			
				
		
			this.socket = new DatagramSocket(51510);
			
			
			try {this.socketNumber = (byte)1;
			}catch(NumberFormatException e) {
				e.printStackTrace();
			}
			
			this.dstAddress = InetAddress.getByName("Rone");
			listener.go();
	
			
		}

		@Override
		public void onReceipt(DatagramPacket packet) {
			System.out.println("Got a packet.");
		/*	if (getType(packet.getData()) == NODE_INITIALISE_SWITCH) {
				dstAddress = (InetSocketAddress) packet.getSocketAddress();
				.println("Connected to switch " + (packet.getPort() - BASE_PORT_NUMBER) + ".");
				
			}*/ 
			if (getType(packet.getData()) ==MES) {
				byte[] data = packet.getData();
				byte src = getMessageSource(data);
				String src1= values.get(String.valueOf(src));
				int len=(int)data[1];
				String message = getMessageContent(data);
				System.out.println("The length of the message is " +len);
				System.out.println("New message from Sender " + src1+ ": " + message);
			}
			else if(getType(packet.getData()) == COM ){
			  System.out.println("Got a combination message ");
			
			  byte[] data = packet.getData();
			  int leng= data[1];
				byte src = getMessageSource(data);
				
				String src1= values.get(src);
				System.out.println("New message from Sender " + src1+ ": ");
				System.out.println("Recieved number of messages " +leng);
			//	List<String[]> com = new ArrayList<String[]>();
				
				int c=4;
				int j=0;
				int temp=0;
				do{
					
	/*add d*/		int len=data[c];
	String[] str= new String[len];
	
	System.out.println("length "+len);
				for(int i=0;i<len;i++)
				{
					//len=data[c];
					
					str[i]= new String(new byte[]{ (data[c+i+1])});
			//		System.out.println("messgae2 "+str[i]);
					temp+=len;
					
					
				}
				 c++;
					c=c+len;
				//com.add(str);
				for(int i=0;i<str.length;i++)
				{
					System.out.print(str[i]);
				}
				System.out.println("");
				j++;
		
				//System.out.println("ppp423 "+getMessageContent(data).length());
			}while(j<leng);
				
			
		/*	for(int i=0;i<message.length;i++)
			{
				System.out.print("message: "+message[i]);
			}*/
			}
			else if(getType(packet.getData())== NODE_INITIALISE_SWITCH) {
				System.out.println("An initialization packet recieved from "+values.get(address.get(packet.getSocketAddress())));
			}
			this.start();
		}
		public synchronized void start() {
			while (true) {
				System.out.println("Please enter SEND or Recieve to continue: ");
				String chosenState= sc.next().toUpperCase();
				System.out.println("Please enter SEND or RECIEVE to continue: " + chosenState);
				if (chosenState.contains(RECEIVE)) {
					/*try {
						//sendRe();
					} catch (IOException e) {
						e.printStackTrace();
					}*/
					System.out.println("Wating for messages.");
				try {	this.wait();
				}catch(InterruptedException e ) {
					e.printStackTrace();
				}
				} else if (chosenState.contains(SEND)) {
					sendMessage();
				} else {
					System.out.println("Invalid input.");
				}
			}
		}

	
		private synchronized void sendMessage() {
			String dest;
			byte finalDst ;
			boolean validInput = false;
			byte[] data = new byte[PACKETSIZE];
			setType(data, MES);
			/*InetSocketAddress adde= new InetSocketAddress (socket.getInetAddress(),PORT_NUMBER);
			Byte prev = address.get(adde);
			System.out.println(adde);*/
			setSrc(data, E1);
			do {
				
				System.out.println("Enter the reciever number ");
				dest= sc.next();
				switch(dest) {
				case "2":
					validInput = true;
					finalDst = E2;
					setDst(data, finalDst);
					break;
				case "3":
					validInput = true;
					 finalDst = E3;
					setDst(data, finalDst);
					break;
				case "4":
					validInput = true;
					 finalDst = E4;
					setDst(data, finalDst);
					break;
				case "5":
					validInput = true;
					 finalDst = E5;
					setDst(data, finalDst);
					break;
					default:
						System.out.println("Invalid input.");
				}
			/*if (dest.equals("1") || dest.equals("2") || dest.equals("3") || dest.equals("4")) {
					validInput = true;
					byte finalDst = E2;
					setDst(data, finalDst);
				} else {
					System.out.println("Invalid input.");
				}*/
			} while (!validInput);
			System.out.println("Please enter a message to send: ");
			String stringMessage = sc.next();
			
			setMessage(data, stringMessage);
			DatagramPacket message = new DatagramPacket(data, data.length,dstAddress,PORT_NUMBER);
			
			try {
				socket.send(message);
				System.out.println("Message sent.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
 /*private void sendRe() throws IOException {
	// String dest = "172.30.0.3";
	 byte[] data = new byte[PACKETSIZE];
	 setType(data,REC);
	 InetAddress Iaddress= InetAddress.getByName("Rthree");
	 DatagramPacket packet= new DatagramPacket(data,data.length,Iaddress,PORT_NUMBER);
	 socket.send(packet);
}*/
		private synchronized void setSrc(byte[] data, byte src) {
			data[2] = src;
			this.src=src;
			
		}

		private synchronized void setDst(byte[] data, byte dst) {
			data[3] = dst;
			this.dst=dst;
		}

		private synchronized void setMessage(byte[] data, String message) {
			if(message.contains("#"))
			{
				data[0]=COM;
				
String[] parts = message.split("#", -2);
for(int i=0;i<parts.length;i++)
{
System.out.println("The Combination message is "+parts[i]+" of length"+parts[i].length());
			}
data[1]= (byte)parts.length;
int i=0;
int c=4;
do{
	
	byte[] message1=(byte[]) parts[i].getBytes();
	//for(int i = 0;i<parts.length;i++)
	
		String str= parts[i];
		System.out.println(i+1+"="+str);
		data[c]=(byte)str.length();
		int len =data[c];
		c++;
		byte[] content = (byte[]) str.getBytes();
		for(int j = 0;j<len;j++)
		{
			
			
				data[c+j] = content[j];
			}
		
		c=c+len;
		
	/*List<String> list = new ArrayList<String>(Arrays.asList(parts));
	list.remove(i);
	parts=list.toArray(new String[0]);
	*/
	i++;
	
	
	}while(i<data[1]);
}
			else {
			data[1]=(byte)message.length();
			byte[] content = (byte[]) message.getBytes();
			for (int i = 0; i < content.length; i++) {
				data[i + 4] = content[i];
			}
			}
			}

	public static void main(String[]args) {
		try {
			address= new HashMap<InetSocketAddress,Byte>();
			values= new  HashMap<Byte,String>();
			addr= new  HashMap<Byte,InetSocketAddress>();
			fillvalues();
			loadaddress();
			loadaddr();
			new App().start();
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}
	}

			
		}


