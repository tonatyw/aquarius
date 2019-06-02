package tonatyw.pool.examples;

import java.net.DatagramSocket;

import tonatyw.pool.aquarius.annotation.BalanceFactory;
import tonatyw.pool.aquarius.annotation.HashBalance;
import tonatyw.pool.aquarius.annotation.handle.base.Balance;

public class HashBalanceDemo {
	@HashBalance(clas=DatagramSocket.class,str={"8081","8082","8083"},paramType={int.class})
	public static Balance<DatagramSocket> sockets;
	public static void main(String[] args) {
		try {
			sockets = BalanceFactory.getInstance(HashBalanceDemo.class, "sockets");
			System.out.println(sockets.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
