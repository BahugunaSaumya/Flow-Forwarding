//package a1;
//package a1;



import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashMap;


public class Router1 extends Node {
/*	public static  String R1 = "Rone";
	public static String R2 ="Rtwo";
	public   static String R3="Rthree";
	public  static String aone = "aone";
	public  static String atwo="atwo";
	public static String R4 ="Rfour";
	public static String R5 ="Rfive";
	public static String R6 ="Rsix";
    public static String R7 ="Rseven";
	public static String R8 ="Reight";
	public  static String athree = "athree";
	public  static String afour = "afour";*/
	public static final byte R1 = 1;
	public static final byte R2 = 2;
	public static final byte R3 = 3;
	public static final byte R4 = 4;
	public static final byte R5 = 5;
	public static final byte R6 = 6;
	public static final byte R7 = 7;
	public static final byte R8 = 8;
	public static final byte E1 = 9;
	public static final byte E2 = 10;
	public static final byte E3 = 11;
	public static final byte E4 = 12;
	public static HashMap<Byte,String> values;
	private int routerNumber;
	private byte[][] flowTable;
	private InetSocketAddress controllerAddress;
	private InetSocketAddress AppAddress;
	public static HashMap<InetSocketAddress,Byte> address;
	public static HashMap<Byte,InetSocketAddress> addr;

	/** Constructor of Router. Initialises the , datagram socket and listener.
	 * @throws UnknownHostException 
	*/
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

	}
	public static InetSocketAddress add(String s) throws UnknownHostException {
		InetAddress dst= InetAddress.getByName(s);
		InetSocketAddress dstaddress= new InetSocketAddress(dst, PORT_NUMBER);
	return dstaddress;	
	}
	Router1() throws SocketException, UnknownHostException {
		this.routerNumber = PORT_NUMBER ;
		socket = new DatagramSocket(addr.get(R1));
		//System.out.println("listening on "+addr.get(R1)+ "container="+values.get(address.get(addr.get(R1))));
		//this. = new ("Switch " + routerNumber);
		InetAddress cont= InetAddress.getByName("con");
		controllerAddress = new InetSocketAddress(cont,PORT_NUMBER );
		AppAddress = null;
		listener.go();
	}

	public synchronized void start() throws InterruptedException {
		sendHello();
		while(true) {this.wait();}
	}

	
	@Override
	public synchronized void onReceipt(DatagramPacket packet)  {
		System.out.println("Got a packet: ");
		if (packet.getSocketAddress().equals(controllerAddress)) {
		try {	handleControllerPacket(packet);}catch(UnknownHostException e) {e.printStackTrace();}
		} else {
			try {	handleAppPacket(packet);}catch(UnknownHostException e) {e.printStackTrace();}	
		}
	}

	private synchronized void handleControllerPacket(DatagramPacket packet) throws UnknownHostException {
		byte[] data = packet.getData();
		switch (getType(data)) {
		// If the packet is a hello, print to the .
		case HELLO:
			System.out.println("Hello packet received from controller.");
			break;
		// If the packet is a features request, reply that the Router has basic
		// features in a features reply.
		case FEATURES_REQUEST:
			byte[] reply = { FEATURES_REPLY, BASIC_FEATURES };
			DatagramPacket featuresReply = new DatagramPacket(reply, reply.length);
			featuresReply.setSocketAddress(controllerAddress);
			try {
				socket.send(featuresReply);
				System.out.println("Sent a features reply to controller.");
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		// If the packet is a flow mod packet, parse the Router's flow table from
		// the packet into a two-dimensional array.
		case FLOW_MOD:
			System.out.println("Flow mod packet received from controller.");
			byte[] flatTable = Arrays.copyOfRange(data, 1, data.length);
			updateFlowtable(flatTable);
			for (int j = 0; j < flowTable.length; j++) {
				for (int k = 0; k < flowTable[0].length; k++) {
					System.out.print("" + values.get(flowTable[j][k]) + " ");
				}
				System.out.println("");
			}
			
			packet.setSocketAddress(controllerAddress);
			byte[] rep =packet.getData();
			setType(rep,FLOW_MOD_REP);
			packet.setData(rep);
			try {
				socket.send(packet);
				System.out.println("Flow mod confirmation sent back to controller.");
			} catch (IOException e) {
				e.printStackTrace();
			}
			setAppAddress();
			break;
		// If the packet tells the Router to drop the packet, do nothing.
		case FLOW_REMOVED:
			System.out.println("Packet from end node dropped at instruction of controller.");
		}
	}

	/* Update the Router's flowtable given a one-dimensional array 
	 * representing it.
	 */
	/*private synchronized void updateFlowtable(byte[] flatFlow) {
	//int rows=(int)flatFlow[0];
	//int col=(int)flatFlow[1];
	System.out.println("jll" +flatFlow.length);
	byte[] flatFlows = Arrays.copyOfRange(flatFlow, 2, flatFlow.length);
	System.out.println("rows="+rows+"coloumn="+col+"length" + flatFlows.length);
	/*
}*/           
		//String[] flatFlowtable=convertToStrings(flatFlows);//Serialization.unserialize(flatFlows);
		
	/*	int rowCount;
		
		for (rowCount = 0; !(flatFlowtable[rowCount * 5].equals(0)); rowCount++)
			;
		flowTable = new String[rowCount][OUTPUT_INDEX + 1];
		int i = 0;
		for (int j = 0; j < flowTable.length; j++) {
			for (int k = 0; k < flowTable[0].length; k++) {
				flowTable[j][k] = flatFlowtable[i];
				i++;
			}
		}*/
		/*System.out.println("jkk "+flatFlowtable.length);
		//if (flatFlowtable.length != (rows*col))
	      //  throw new IllegalArgumentException("Invalid array length");

	     flowTable =  new String [rows][col];
	    for ( int i = 0; i < rows; i++ ) 
	        System.arraycopy(flatFlowtable, (i*col), flowTable[i], 0, col);
	    
	    
	    //return bidi;
	}*/
		private synchronized void updateFlowtable(byte[] flatFlowtable) {
			int rowCount;
			for (rowCount = 0; flatFlowtable[rowCount * 5] != 0; rowCount++)
				;
			flowTable = new byte[rowCount][OUTPUT_INDEX + 1];
			int i = 0;
			for (int j = 0; j < flowTable.length; j++) {
				for (int k = 0; k < flowTable[0].length; k++) {
					flowTable[j][k] = flatFlowtable[i];
					i++;
				}
			}
		}
	/* Send a hello packet to the controller.
	*/
	private synchronized void sendHello() {
		byte[] data = { HELLO };
		DatagramPacket hello = new DatagramPacket(data, data.length);
		hello.setSocketAddress(controllerAddress);
		try {
			socket.send(hello);
			System.out.println("Hello packet sent to controller.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	private synchronized void setAppAddress() throws UnknownHostException {
		boolean addressSet = false;
		int i = 0;
		while (i < flowTable.length && !addressSet) {
			
			if (flowTable[i][Router_INDEX]==R1 && (values.get(flowTable[i][INPUT_INDEX]).contains("a"))) {
				byte portNumber = flowTable[i][INPUT_INDEX] ;
				String value= values.get(portNumber);
				InetAddress dst= InetAddress.getByName(value);
				this.AppAddress = new InetSocketAddress(dst, PORT_NUMBER);
				addressSet = true;
				System.out.println(
						"This Router is connected to app " + ( value ) + ".");
			} else if (flowTable[i][Router_INDEX]==R1 &&flowTable[i][OUTPUT_INDEX] > NUM_SWITCHES) {
				byte portNumber = flowTable[i][OUTPUT_INDEX];
				String value= values.get(portNumber);
				InetAddress dst= InetAddress.getByName(value);
				this.AppAddress = new InetSocketAddress(dst, PORT_NUMBER);
				addressSet = true;
				System.out.println(
						"This Router is connected to app " + (value) + ".");
			}
			i++;
		}
		if (!addressSet) {
			System.out.println("This Router is not connected to an APP in the network.");
		} else {
			byte[] data = { NODE_INITIALISE_SWITCH };
			DatagramPacket initialisation = new DatagramPacket(data, data.length);
			initialisation.setSocketAddress(AppAddress);
			try {
				socket.send(initialisation);
				System.out.println("Router req sent to the connected App." + values.get(address.get(AppAddress)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	private synchronized void handleAppPacket(DatagramPacket packet) throws UnknownHostException {
		byte[] data = packet.getData();
		if ((getType(data) == MES ||getType(data) == COM )) {
			System.out.println("Message from app received.");
			byte nextHop = checkFlowtable(data, packet.getAddress());
			if (nextHop ==0){
				System.out.println("Next hop not in flow table.");
				byte[] unrecognised = new byte[data.length];
				setType(unrecognised, PACKET_IN);
				for (int i = 1; i < unrecognised.length; i++) {
					unrecognised[i] = data[i - 1];
				}
				DatagramPacket packetIn = new DatagramPacket(unrecognised, unrecognised.length);
				packetIn.setSocketAddress(controllerAddress);
				try {
					socket.send(packetIn);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				String Value= values.get(nextHop);
				InetAddress dst=InetAddress.getByName(Value);
				InetSocketAddress outputAddress = new InetSocketAddress(dst, PORT_NUMBER);
				packet.setSocketAddress(outputAddress);
				try {
					socket.send(packet);
					if (values.get(nextHop).contains("R")) {
						System.out.println(
								"Packet forwarded to Router " + nextHop + " on port " + outputAddress.getPort() + ".");
					} else {
						System.out.println("Packet forwarded to app " + nextHop + " on port "
								+ outputAddress.getPort() + ".");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


	private synchronized byte checkFlowtable(byte[] data, InetAddress port) {
		assert (getType(data) == MES);
		byte src = getMessageSource(data);
		String src1= values.get(src);
		

		System.out.println("Source  = " + src1 + ".");
		byte dst = getMessageDest(data);
		String dst1= values.get(dst);

		System.out.println("Destination " + dst1 + ".");
		InetSocketAddress adde= new InetSocketAddress(port,PORT_NUMBER);
		
		Byte prev = address.get(adde);
		String value= values.get(prev);
		
		
		System.out.println("Previous " + value  + ".");
		int nextHop = 0;
		int i = 0;
		while (i < flowTable.length && nextHop==0) {
		/*	System.out.println("flo "+flowTable[i][SRC_INDEX]);
			System.out.println("flo2 "+flowTable[i][DST_INDEX]);
			System.out.println("fl3 "+flowTable[i][INPUT_INDEX]);*/

			if (src==flowTable[i][SRC_INDEX] && dst==flowTable[i][DST_INDEX] && prev==flowTable[i][INPUT_INDEX]) {
				nextHop =flowTable[i][OUTPUT_INDEX];
			}
			i++;
		}
		return (byte)nextHop;
	}
/*private synchronized String checkFlowtable(byte[] data, int port) {
		assert (getType(data) == MES);
		byte src = getMessageSource(data);
		String src1= String.valueOf(src);

		System.out.println("Source Port Number123 = " + src1 + ".");
		byte dst = getMessageDest(data);
		String dst1= String.valueOf(dst);

		System.out.println("Destination Port Number = " + dst1 + ".");
		String prev = R1;
		String nextHop = "0";
		int i = 0;
		while (i < flowTable.length && nextHop.equals("0")) {
		/*	System.out.println("flo "+flowTable[i][SRC_INDEX]);
			System.out.println("flo2 "+flowTable[i][DST_INDEX]);
			System.out.println("fl3 "+flowTable[i][INPUT_INDEX]);*/

		/*	if (src1.equals(flowTable[i][SRC_INDEX]) && dst1.equals(flowTable[i][DST_INDEX]) && prev.equals(flowTable[i][INPUT_INDEX])) {
				nextHop =flowTable[i][OUTPUT_INDEX];
				//System.out.println("fl5 " +nextHop);
			}
			i++;
		}
		return nextHop;
	}*/	
	public static void main(String[] args) {
		try {
			address= new HashMap<InetSocketAddress,Byte>();
			values= new  HashMap<Byte,String>();
			addr= new  HashMap<Byte,InetSocketAddress>();
			fillvalues();
			loadaddress();
			loadaddr();
			new Router1().start();
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}
	}
}
