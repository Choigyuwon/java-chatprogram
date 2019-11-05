package chatprogram;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
public class CalculatorServer {
	public enum BiOperatorModes { 
		normal, add, min, product, div
	}

	private Double num1, num2;
	private BiOperatorModes mode = BiOperatorModes.normal;
	private DatagramPacket sendPacket, receivePacket;
	private DatagramSocket socket;
	private String Operate="";
	public CalculatorServer() {
		try {
			socket = new DatagramSocket(5000); 
		} catch (SocketException se) {
			se.printStackTrace();
			System.exit(1);
		}
	}

	public void waitForPackets() {
		while (true) {
			try { 
				byte data[] = new byte[20];
				receivePacket = new DatagramPacket(data, data.length);
				socket.receive(receivePacket);
				String str = new String(receivePacket.getData());
				System.out.print(str.charAt(0));
				if(str.charAt(0)=='+') {
					calculateBi(mode,Double.parseDouble(Operate));
					Operate = "";
					mode = BiOperatorModes.add;
				}else if(str.charAt(0)=='-') {
					calculateBi(mode,Double.parseDouble(Operate));
					Operate = "";
					mode = BiOperatorModes.min;
				}else if(str.charAt(0)=='*') {
					calculateBi(mode,Double.parseDouble(Operate));
					Operate = "";
					mode = BiOperatorModes.product;
				}
				else if(str.charAt(0)=='/') {
					calculateBi(mode,Double.parseDouble(Operate));
					Operate = "";
					mode = BiOperatorModes.div;
				}else if(str.charAt(0)=='C') {
					reset();
					
				}
				else if(str.charAt(0)=='=') {
					 byte data1[] = calculateBi(mode,Double.parseDouble(Operate)).toString().getBytes(); 
			         sendPacket=new DatagramPacket(data1, data1.length, InetAddress.getLocalHost(), 4000);
			         socket.send( sendPacket );
			         Operate = "";
			         System.out.println("");
			         System.out.println("계산 완료");
				}else {
					Operate+= str.charAt(0);
				}
			} catch (IOException io) {
				io.printStackTrace();
			}
		}
	}

	private Double calculateBiImpl() {
		if (mode == BiOperatorModes.normal) {
			return num2;
		}
		if (mode == BiOperatorModes.add) {
			return num1 + num2;
		}
		if (mode == BiOperatorModes.min) {
			return num1 - num2;
		}
		if (mode == BiOperatorModes.product) {
			return num1 * num2;
		}
		if (mode == BiOperatorModes.div) {
			return num1 / num2;
		}
		throw new Error();
	}

	public Double calculateBi(BiOperatorModes newMode, Double num) {
		if (mode == BiOperatorModes.normal) {
			num2 = 0.0;
			num1 = num;
			mode = newMode;
			return Double.NaN;
		} else {
			num2 = num;
			num1 = calculateBiImpl();
			mode = newMode;
			return num1;
		}
	}

	public Double calculateEqual(Double num) {
		return calculateBi(BiOperatorModes.normal, num);
	}

	public Double reset() {
		num2 = 0.0;
		num1 = 0.0;
		mode = BiOperatorModes.normal;

		return Double.NaN;
	}

	public static void main(String args[]) {
		CalculatorServer s = new CalculatorServer();
		s.waitForPackets();
	}

	class WinListener extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	}

}
