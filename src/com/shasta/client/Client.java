package com.shasta.client;

import com.shasta.threaded.ClientRunnable;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;


/**
 * Client object that contains methods to handle connects, disconnects, and messages for one client.
 *
 * @author Chandler Severson
 * @since 2019-12-10
 * <p>Made as a starter project for the 2019 Shasta Networks/SOU CS Club Hackathon.</p>
 */
public class Client extends ClientRunnable {


    public Client(Socket clientSocket) {
        super(clientSocket);
        System.out.println(getClientSocket().getLocalAddress() + ": New Connection. Port: " + getClientSocket().getPort());
    }

    /**
     * Called when a client connects.
     */
    @Override
    public void handleConnect() {
    	try {
    		sendMessage("A simple program to create an html file.\r\n");
        	sendMessage("Each line you type will be converted to an html element.\r\n");
			sendMessage("Type any character and hit enter to begin\r\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * Called when a client disconnects
     */
    @Override
    public void handleDisconnect() {
    	Socket clientSocket = getClientSocket();
			try {
				sendMessage("Connection Closing...\r\n");
				clientSocket.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		
    }


    /**
     * Handle client input.
     * @param str The message to handle.
     */
    @Override
    protected void handleMessage(String str) {
    	
		String fileName = "echo.html";
		BufferedWriter writer;
		
		try {
			writer = new BufferedWriter(new FileWriter(fileName,true));
			writer.append(" ");
			writer.append("</br>");
			writer.append("<h1>"+str+"<h1>");
			sendMessage(str + ": has been added to your document.\r\n");
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
    	
    	
    }
}
