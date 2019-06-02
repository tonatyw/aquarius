package tonatyw.pool.aquarius.annotation.handle;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import tonatyw.pool.aquarius.annotation.handle.base.Balance;

/**
 * 
 * @ClassName HashBalance
 * @Description: hash散列实现
 * @author toantyw
 */
public class HashBalance<T> implements Balance<T>{
	private List<T> list = new ArrayList<T>();

	public T get() {
		// hash散列算法
		BigDecimal longBd = new BigDecimal(System.currentTimeMillis());
		BigDecimal longSize = new BigDecimal(list.size());
		return (T) list.get(longBd.divideAndRemainder(longSize)[1].intValue());
	}
	public void init(Class<T> clas, Object[][] objs, Class[] paramsType) {
		try {
			Constructor con = clas.getConstructor(paramsType);
			// 拼装参数
			for(Object[] objArray:objs){
				list.add(clas.cast(con.newInstance((Object[])objArray)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void release(T t) {
		
	}
}
