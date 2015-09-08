package com.zsolis.virtualplane;

import java.io.IOException;
import java.util.List;

import com.zsolis.virtualplane.common.Position;

public class CmdExecutor {
	public static void main (String arg[]) throws IOException, ClassNotFoundException {
		InfoReceiver infoReceiver = new InfoReceiver(8000);
		while (true) {
			System.out.println("等待连接...");
			List<Position> receivedList = infoReceiver.receivePath();
			String phoneAddress = infoReceiver.getClientAddress();
			if (receivedList != null && receivedList.size() != 0) {
				for (Position position : receivedList) {
					System.out.printf("横坐标%d 纵坐标%d\n", position.getxOffset(),position.getyOffset());
				}
				InfoSender infoSender = new InfoSender(phoneAddress, 8111);
				infoSender.sendPath(receivedList);
				infoSender = null;
			}
		}
	}
}
