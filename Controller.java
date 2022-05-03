//RfourRpackage a1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Controller extends Node {

//	public static ArrayList<ArrayList<Byte>> PRECONF_INFO = new ArrayList<ArrayList<Byte>>();
	public static HashMap<InetSocketAddress,Byte> address;
	public static HashMap<Byte,String> values;

	//private static  ;
	private static final byte[][] PRECONF_INFO = { { E1, E2, R1, E1, R4 }, { E1, E2, R4, R1, R7 },
			{ E1, E2, R7, R4, R8 }, { E1, E2, R8, R7, E2 }, { E1, E3, R1, E1, R2 }, { E1, E3, R2, R1, R5 },
			{ E1, E3, R5, R2, E3 }, { E1, E4, R1, E1, R3 }, { E1, E4, R3, R1, E4 }, { E2, E1, R8, E2, R7 },
			{ E2, E1, R7, R8, R4 }, { E2, E1, R4, R7, R1 }, { E2, E1, R1, R4, E1 }, { E2, E3, R8, E2, R7 },
			{ E2, E3, R7, R8, R5 }, { E2, E3, R5, R7, E3 }, { E2, E4, R8, E4, R6 }, { E2, E4, R6, R8, R3 },
			{ E2, E4, R3, R6, E4 }, { E3, E1, R5, E3, R2 }, { E3, E1, R2, R5, R1 }, { E3, E1, R1, R2, E1 },
			{ E3, E2, R5, E3, R7 }, { E3, E2, R7, R5, R8 }, { E3, E2, R8, R7, E2 }, { E3, E4, R5, E3, R2 },
			{ E3, E4, R2, R5, R1 }, { E3, E4, R1, R2, R3 }, { E3, E4, R3, R1, E4 }, { E4, E1, R3, E4, R1 },
			{ E4, E1, R1, R3, E1 }, { E4, E2, R3, E4, R6 }, { E4, E2, R6, R3, R8 }, { E4, E2, R8, R6, E2 },
			{ E4, E3, R3, E4, R6 }, { E4, E3, R6, R3, R4 }, { E4, E3, R4, R6, R7 }, { E4, E3, R7, R4, R5 },
			{ E4, E3, R5, R7, E3 } };
	
/*
 * 
 * 
 * 
 * A function to change the 2d array to  
 */
	
	
/*
	private static final String[][] PRECONF_INFO = { { "1", "2", R1, "", R4 }, { "1", "2", R4, R1, R7 },
			{ "1", "2", R7, R4, R8 }, { "1", "2", R8, R7, "2" }, { "1", "3", R1, "1", R2 }, { "1", "3", R2, R1, R5 },
			{ "1", "3", R5, R2, "3" }, { "1", "4", R1, "1", R3 }, { "1", "4", R3, R1, "4" }, { "2", "1", R8, "2", R7 },
			{ "2", "1", R7, R8, R4 }, { "2", "1", R4, R7, R1 }, { "2", "1", R1, R4, "1" }, { "2", "3", R8, "2", R7 },
			{ "2", "3", R7, R8, R5 }, { "2", "3", R5, R7, "3" }, { "2", "4", R8, "4", R6 }, { "2", "4", R6, R8, R3 },
			{ "2", "4", R3, R6, "4" }, { "3", "1", R5, "3", R2 }, { "3", "1", R2, R5, R1 }, { "3", "1", R1, R2, "1" },
			{ "3", "2", R5, "3", R7 }, { "3", "2", R7, R5, R8 }, { "3", "2", R8, R7, "2" }, { "3", "4", R5, "3", R2 },
			{ "3", "4", R2, R5, R1 }, { "3", "4", R1, R2, R3 }, { "3", "4", R3, R1, "4" }, { "4", "1", R3, "4", R1 },
			{ "4", "1", R1, R3, "1" }, { "4", "2", R3, "4", R6 }, { "4", "2", R6, R3, R8 }, { "4", "2", R8, R6, "2" },
			{ "4", "3", R3, "4", R6 }, { "4", "3", R6, R3, R4 }, { "4", "3", R4, R6, R7 }, { "4", "3", R7, R4, R5 },
			{ "4", "3", R5, R7, "3" } };*/


	/* Mainline. Contruct a new Controller and start its functionality. */
	public static void main(String[] args) {
		try {
			address= new HashMap<InetSocketAddress,Byte>();
			values= new  HashMap<Byte,String>();
			fillvalues();
			loadaddress();
			// = new ("Controller");
			(new Controller()).start();
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}
	}


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
	Controller() throws SocketException {
		// Initialise Controller
	//	Controller. = ;
		this.socket = new DatagramSocket(PORT_NUMBER);
		listener.go();
	/*	// Initialise Routeres
		Routers = new Router[5];
		for (byte i = R1; i <= 4; i++) {
			Routers[i] = new Router();
		}
		// Initialise Apps
		Apps = new App[2];
		for (byte j = 0; j < 2; j++) {
			Apps[j] = new App();
		}*/
	}

	/* Start the initial Router. Once the first Router has finished setup the second
	*  Router will start, and so on.
	*/
	public synchronized void start() throws Exception {
		System.out.println("Waiting for messages");
		this.wait();
				
	}

	/* Call the start method of the next Router, beginning its setup sequence
	*  where the Router will send a hello packet.
	*/
/*	private synchronized void startRouter(int routerNumber) {
		Router s = Routers[routerNumber];
		s.start();
	}
*/
	
	private synchronized void sendTable(InetSocketAddress dstAddress) {
		Byte RouterNumber= address.get(dstAddress);
		String value=values.get(RouterNumber);
		// create a new flow mod table; the row corresponds to the Router number
	/*	String[] table = new String[C.length * PRECONF_INFO[0].length];
	for (int i = 0; i < PRECONF_INFO.length; i++) {
			if (RouterNumber == PRECONF_INFO[i][Router_INDEX]) {
			try {	int j = 0;
				while (!(table[j].equals(0))) {
					j++; // set j to an index where the value is zero
				}*/
				//int[][] data = new int[numberOfRows][numberOfCols];
				//int[] arrayCount = new int[numberOfRows * numberOfCols];
				int  numberOfCols;
				 int numberOfRows = PRECONF_INFO.length;
				    if (numberOfRows > 0) {
				        numberOfCols = PRECONF_INFO[0].length;
				    } else {
				        numberOfCols = 0;
				    }
				    byte[] table = new byte[numberOfRows * numberOfCols];
				    System.out.println("numberOfRows : "+numberOfRows);
				    System.out.println("numberOfCols : "+numberOfCols);

				    for (int row = 0, count = 0; row < numberOfRows; row++) {
				        for (int col = 0; col < numberOfCols; col++) {
				            table[count] =PRECONF_INFO [row][col];
				            count++;
				        }
				     
				    }
				   				
			/*	for (int k = 0; k <= OUTPUT_INDEX; k++) {
					table[j] = PRECONF_INFO[i][k];
					j++;
				}
			}catch(NullPointerException e) {
				e.printStackTrace();}
			}
		}*/
					//try{byte[] flowTable=convertToBytes(table);//Serialization.serialize(table);
					
		
		byte[] flowTable = new byte[table.length + 1];
		// set the first byte of the flowTable to be the type, a FLOW_MOD
		// packet
		for (int i = 0; i < table.length; i++) {
			flowTable[i + 1] = table[i];
	
		}
		// byte[] data2 = Arrays.copyOfRange(data, 3, data.length);
		/*
		try{byte[] data=Serialization.serialize(flowTable);
		String[] data1= Serialization.unserialize(data);*/
		// String[] data1= convertToStrings(data2);//Serialization.unserialize(data2);
	//	System.out.println("jpl "+data1.length);
	///	System.out.println("jll "+data.length);
	//	System.out.println("+2 "+data[1]+"+d"+data[2]);
		flowTable[0] = FLOW_MOD;
	//	data[1]=(byte)numberOfRows;
		//data[2]=(byte)numberOfCols;
	//	System.out.println("+2568 "+data[1]+" d ="+data[2]);
		
		DatagramPacket packet = new DatagramPacket(flowTable, flowTable.length);
		//InetAddress dstAddress= InetAddress.getByName(nextHop);
		//InetAddress dstAddress = new InetSocketAddress(LOCALHOST, BASE_PORT_NUMBER + RouterNumber);
		packet.setSocketAddress(dstAddress);
		try {
			this.socket.send(packet); // try to send the flow table to the Router
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	private synchronized void sendHello(InetSocketAddress dstAddress) {
		byte[] data = { HELLO };
		DatagramPacket hello = new DatagramPacket(data, data.length);
		hello.setSocketAddress(dstAddress);
		try {
			socket.send(hello);
			byte port = address.get(dstAddress);
			String value=values.get(port);
			System.out.println("Hello packet sent to Router number " + value + ".");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* Send a packet of type OPFT_FEATURES_REQUEST when passed the destination address.
	*/
	private synchronized void sendFeaturesRequest(InetSocketAddress dstAddress) {
		byte[] data = { FEATURES_REQUEST };
		DatagramPacket featuresRequest = new DatagramPacket(data, data.length);
		featuresRequest.setSocketAddress(dstAddress);
		try {
			socket.send(featuresRequest);
			byte port = address.get(dstAddress);
			String value=values.get(port);
			System.out.println("Features request sent to Router number  " + value + ".");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	

	@Override
	public synchronized void onReceipt(DatagramPacket packet) {
		byte[] data = packet.getData();
		InetAddress dst= packet.getAddress();
		InetSocketAddress dstAddress= new InetSocketAddress(dst,PORT_NUMBER);
		byte port = address.get(dstAddress);
		String value=values.get(port);
		byte type = getType(data);
		switch (type) {
		// Handle a Hello packet by replying with a Hello and a Features Request.		
		case HELLO:
			System.out.println("Hello packet received from Router number " + value + ".");
			sendHello((InetSocketAddress) packet.getSocketAddress());
			sendFeaturesRequest((InetSocketAddress) packet.getSocketAddress());
			break;
		// Handle a Features Reply by printing the features to the .
		case FEATURES_REPLY:
			System.out.println("Features reply received from Router number " + value + ".");
			if (data[1] == BASIC_FEATURES) {
				System.out.println("Router " + value + " has initial features");
			} else {
				System.out.println("Features of Router " + value + " unknown.");
			}
			sendTable(dstAddress);
			break;
		
	
		// Handle an unrecognised packet forwarded by a Router by telling that Router to 
		// drop the packet.
		case PACKET_IN:
			setType(data, FLOW_REMOVED);
			packet.setData(data);
			packet.setSocketAddress(packet.getSocketAddress());
			try {
				socket.send(packet);
				System.out.println("Told Router " + value + " to drop packet.");
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case FLOW_MOD_REP:
			System.out.println("A flow Mod reply recieved from " + value);
			break;
			
		}
	}

}