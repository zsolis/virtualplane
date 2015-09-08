package com.zsolis.virtualplane;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.zsolis.virtualplane.common.Position;

public class InfoReceiver {
	private ServerSocket servSocket;
	private String clientAddress = "";
	
	public InfoReceiver(int servPort) throws IOException {
		servSocket = new ServerSocket(servPort);
	}
	
	@SuppressWarnings("unchecked")
	public List<Position> receivePath() throws IOException, ClassNotFoundException {
		Socket clientSocket = servSocket.accept();
		clientAddress = clientSocket.getInetAddress().getHostAddress();
		System.out.println(clientSocket + "已连接");
		ObjectInputStream ois = new ObjectInputStream (clientSocket.getInputStream());
		List<Position> receivedPath = new ArrayList<Position>();
		receivedPath = (List<Position>) ois.readObject();
		return receivedPath;
	}
	
	public String getClientAddress () {
		return clientAddress;
	}
	/*
	public static void main (String arg[]) throws IOException, ClassNotFoundException {
		InfoReceiver myReceiver = new InfoReceiver(8111);
		List<Position> path = new ArrayList<Position>();
		path = myReceiver.receivePath();
		for(int i=0;i<path.size();i++) {
			Position node=  path.get(i);
			System.out.printf("第%d个点的横坐标是%d纵坐标是%d\n",i,node.getxOffset(),node.getyOffset());
		}
	}*/
}
