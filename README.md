# aquarius
一个对象池相关的小项目，设计初衷为可以在从池中取对象时使用各种负载算法，这里可以根据需要自行扩展  
## 结构
* HashBalance  hash散列负载池  
## 适用范围
各种连接对象  
* es
* redis
* socket
* database

## 简单使用

### 单参数
```Java
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
```

### 多参数
```Java
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
```

## 问题
1. 未做日志
2. 注释不是特别全
3. 不保证适合所有场景
4. 现在只做了一个算法demo
5. 由于时间关系 各种字符未做常量重用 直接写在程序里
6. 参数没能在文件里配置，不能在注解里传值，只能写死，如果有知道怎么实现往注解传值的大大请联系我

# 后续
1. 完善对象支持类型
2. 实现负载加权随机算法、加权轮询算法、最小连接数法
3. 考虑结合spring做文件配置（由于考虑轻量化，这里不是移植，最多在保证原功能情况下，支持在spring里做文件配置）
