//package a1;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

public abstract class Node implements type {
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
	public static final byte E5 = 13;
	public static HashMap<Byte,String> values;
	public static HashMap<InetSocketAddress,Byte> address;
	public static HashMap<Byte,InetSocketAddress> addr;
	// the features of the switch

	DatagramSocket socket;
	Listener listener;
	CountDownLatch latch;

	Node() {
		latch = new CountDownLatch(1);
		listener = new Listener();
		listener.setDaemon(true);
		listener.start();
	/*	values= new HashMap<Byte,String>();
		address= new HashMap<InetSocketAddress,Byte>();
		addr= new  HashMap<Byte,InetSocketAddress>();
*/	}

	public abstract void onReceipt(DatagramPacket packet);



	protected byte getType(byte data[]) {
		return data[0];
	}
	
	protected void setType(byte[] data, byte type){
		data[0] = type;
	}
	

	protected byte getMessageSource(byte data[]){
		assert(getType(data)==MES);
		return data[2];
	}
	
	protected byte getMessageDest(byte data[]){
		assert(getType(data)==MES);
		return data[3];
	}
	
	protected String getMessageContent(byte data[]){
		assert(getType(data)==MES);
		byte[] content = Arrays.copyOfRange(data, 3, data.length);
		String messageContent = new String(content).trim();
		return messageContent;
	}

	
	class Listener extends Thread {

		public void go() {
			latch.countDown();
		}

	
		public synchronized void run() {
			try {
				latch.await();
				
				while (true) {
					DatagramPacket packet = new DatagramPacket(new byte[PACKETSIZE], PACKETSIZE);
					socket.receive(packet);
					onReceipt(packet);
				}
			} catch (Exception e) {
				if (!(e instanceof SocketException))
					e.printStackTrace();
			}
		}
	}
/*	 public static byte[] convertToBytes(String[] strs) throws IOException {
		 final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		 final ObjectOutputStream objectOutputStream =
		     new ObjectOutputStream(byteArrayOutputStream);
		 objectOutputStream.writeObject(strs);
		 objectOutputStream.flush();
		 objectOutputStream.close();

		 final byte[] bytes = byteArrayOutputStream.toByteArray();
		/*
		 byte[] b = new byte[strs.length];

		 for (int i=3; i<strs.length; i++) {
		     b[i] = (byte) Short.parseShort(strs[i], 16);
		 }
		 /*  byte[] bytes=new byte[strs.length];
	        for (int i=1; i<strs.length; i++) {
	         try {   bytes[i]=Byte.parseByte(strs[i]);
	         }catch (NumberFormatException nfe) {
	             nfe.printStackTrace();
	         }
	         }
	        return bytes;
	    }
	 /*final String[] stringArray = { "foo", "bar", "baz" };

final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
final ObjectOutputStream objectOutputStream =
    new ObjectOutputStream(byteArrayOutputStream);
objectOutputStream.writeObject(strs);
objectOutputStream.flush();
objectOutputStream.close();

final byte[] bytes = byteArrayOutputStream.toByteArray();*/
	 
/*	 public static String[] convertToStrings(byte[] byteStrings) {
		    String[] data = new String[byteStrings.length];
		    for (int i = 0; i < byteStrings.length; i++) {
		        data[i] = String.valueOf(byteStrings[i]);

		    }
		    return data;
		}*/
}
