package com.achinthagunasekara.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

import com.achinthagunasekara.checks.Check;

/* 
 * @author Achintha Gunasekara
 * @email contact@achinthagunasekara.com.au
 */

public class Client {
	
	private final int clientPort = 9090;
	private final String user = "admin";
	private final String password = "admin";
	private final String keystore = "/Users/archieg/Documents/Git_Workspace/wolfeye/certs/keystore.jks";
	private final String keystorePassword = "test123";
	private HashMap<String, Check> checks;
	
	public static void main(String args[]) {
		
		Client c = new Client();
		
		try {
			c.listen();
		}
		catch(IOException ioEx) {
			System.out.println(ioEx.getMessage());
		}
	}
	
	public Client() {
		this.checks = new HashMap<String, Check>();
		this.checks.put("check_1", new Check("check_1"));
		this.checks.put("check_2", new Check("check_2"));
		this.checks.put("check_3", new Check("check_3"));
		this.checks.put("check_4", new Check("check_4"));
		this.checks.put("check_5", new Check("check_5"));
	}
	
	public void listen() throws IOException {

		System.setProperty("javax.net.ssl.keyStore",this.keystore);
		System.setProperty("javax.net.ssl.keyStorePassword",this.keystorePassword);
		
		System.out.println("Listening on port " + clientPort + "...");

		ServerSocketFactory ssocketFactory = SSLServerSocketFactory.getDefault();
		SSLServerSocket listener = (SSLServerSocket) ssocketFactory.createServerSocket(clientPort);
		
		try {
			while (true) {
				SSLSocket socket = (SSLSocket) listener.accept();
				try {
					PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
					BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					out.println("USER");
					String userIn = in.readLine();
					out.println("PASSWORD");
					String passwrodIn = in.readLine();
					
					if(validateUser(userIn, passwrodIn)) {
						
						for(String checkName : checks.keySet()) {
							out.println(checks.get(checkName).toString());
						}
						
					}
					else {
					
						out.println("Invalid User. Good Bye!");
					}
				}
				finally {
					socket.close(); 
				}
			}
		}
		finally {
			listener.close();
		}
	}
	
	private boolean validateUser(String user, String password) {	
		if(user.equals(this.user) && password.equals(this.password)) {
			return true;
		}
		return false;
	}
}