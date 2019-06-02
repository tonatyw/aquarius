package tonatyw.pool.examples;

import java.net.InetSocketAddress;

import tonatyw.pool.aquarius.annotation.BalanceFactory;
import tonatyw.pool.aquarius.annotation.HashBalance;
import tonatyw.pool.aquarius.annotation.handle.base.Balance;

public class HashBalanceDemoTwo {
	@HashBalance(clas=InetSocketAddress.class,str={"127.0.0.1,8081","127.0.0.1,8082","127.0.0.1,8083"},paramType={String.class,int.class})
	public static Balance<InetSocketAddress> isa;
	public static void main(String[] args) {
		try {
			isa = BalanceFactory.getInstance(HashBalanceDemoTwo.class, "isa");
			System.out.println(isa.get().getPort());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
