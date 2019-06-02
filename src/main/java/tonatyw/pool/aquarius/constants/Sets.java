package tonatyw.pool.aquarius.constants;

import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @ClassName Sets
 * @Description: 常量类
 * @author tonatyw
 */
public class Sets {
	/** 基本类型名称与包装类型class路径 */
	private static Map<String,String> baseMap = new HashMap<String,String>();
	static{
		//加载基本数据类型
		baseMap.put("int", "java.lang.Integer");
		baseMap.put("double", "java.lang.Double");
		baseMap.put("float", "java.lang.Float");
	}
	public static Map<String, String> getBaseMap() {
		return baseMap;
	}
	public static void setBaseMap(Map<String, String> baseMap) {
		Sets.baseMap = baseMap;
	}
}
