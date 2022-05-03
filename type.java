//package a1;

public interface type {
	 static final byte SEND = 35;
	static final byte REC = 36;
     static final byte MES = 3;
     static final byte COM=9;
	
	 static final int PACKETSIZE = 500;
	 static final int PORT_NUMBER = 51510;
	 static final String LOCALHOST = "172.30.0.0";
	
	 static final int SRC_INDEX = 0;
	 static final int DST_INDEX = 1;
	// static final int ROUTER_INDEX = 2;
	
	static final int OUTPUT_INDEX = 4;
	 static final byte HELLO = 0;
//	 static final byte ERROR = 1;
	 
	// static final byte EXPERIMENTER = 4;
	/* Switch configuration messages.*/ 
	 static final byte FEATURES_REQUEST = 5;
	 static final byte FEATURES_REPLY = 6;
	/* static final byte GET_CONFIG_REQUEST = 7;
	 static final byte GET_CONFIG_REPLY = 8;
	 static final byte SET_CONFIG = 9;
	/* Asynchronous messages. */
	 static final byte PACKET_IN = 10;
	 static final byte FLOW_REMOVED = 11;
	// static final byte PORT_STATUS = 12;
	// static final byte PACKET_OUT = 13;
	 static final byte FLOW_MOD = 14;
	 static final byte FLOW_MOD_REP = 117;
	// static final byte GROUP_MOD = 15;
	// static final byte PORT_MOD = 16;
	// static final byte TABLE_MOD = 17;
	/* Multipart messages. */
	// static final byte MULTIPART_REQUEST = 18;
	// static final byte MULTIPART_REPLY = 19;
	/* Barrier messages. */
	// static final byte BARRIER_REQUEST = 20;
	// static final byte BARRIER_REPLY = 21;
	/* Controller role change request messages. */
//	 static final byte ROLE_REQUEST = 22;
//	 static final byte ROLE_REPLY = 23;
	/* Asynchronous message configuration. */
//	 static final byte GET_ASYNC_REQUEST = 24;
	// static final byte GET_ASYNC_REPLY = 25;
	// static final byte SET_ASYNC = 26;
	/* Meters and rate limiters configuration messages. */
//	 static final byte METER_MOD = 27;
	/* Controller role change event messages. */
//	 static final byte ROLE_STATUS = 28;
	/* Asynchronous messages. */
	 //static final byte TABLE_STATUS = 29;
	/* Request forwarding by the switch. */
	// static final byte REQUESTFORWARD = 30;
	/* Bundle operations. */
	// static final byte BUNDLE_CONTROL = 31;
//	 static final byte BUNDLE_ADD_MESSAGE = 32;
	/* Controller Status async message. */
	// static final byte CONTROLLER_STATUS = 33;
	 static final byte NODE_INITIALISE_SWITCH = 34;
		
		 static final int INPUT_INDEX = 3;
		/** Other constants. */
	
		/** The index for the terms in preconfInfo */
		
		 static final int Router_INDEX = 2;
		 
		;
		// the features of the switch
		 static final byte BASIC_FEATURES = 0;

		public static final byte NUM_SWITCHES = 8;
		public static final byte NUM_END_NODES = 4;
}

