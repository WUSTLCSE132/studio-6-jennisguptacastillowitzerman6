package studio6;

import jssc.*;


public class SerialComm {


	SerialPort port;

	public static void main(String args[]) throws SerialPortException{
		SerialComm port = new SerialComm("COM4");
		while(true) {
			if(port.available()) {
				char read = (char) port.readByte();
				System.out.println(read);
			}
		}
	}
	
	private boolean debug;  // Indicator of "debugging mode"
	
	// This function can be called to enable or disable "debugging mode"
	void setDebug(boolean mode) {
		debug = mode;
	}	
	


	// Constructor for the SerialComm class
	public SerialComm(String name) throws SerialPortException {
		port = new SerialPort(name);		
		port.openPort();
		port.setParams(SerialPort.BAUDRATE_9600,
			SerialPort.DATABITS_8,
			SerialPort.STOPBITS_1,
			SerialPort.PARITY_NONE);
		
		debug = false; // Default is to NOT be in debug mode
	}
		
	public void writeByte(byte Byte) {
		if(debug) {
			try {
				port.writeByte(Byte);
				System.out.println("0x"+ Integer.toHexString(Byte));
			} 
			catch (SerialPortException e) {
				e.printStackTrace();
			}	
		}
	}
	
	public boolean available() throws SerialPortException{
		if((port.getInputBufferBytesCount()) > 0)
			return true;
		else
			return false;
	}
	
	public byte readByte() throws SerialPortException{
		byte[] input = port.readBytes(1);
		return input[0];
	}
}


