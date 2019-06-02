package tonatyw.pool.aquarius.util;


/**
 * 
 * @ClassName ReflectUtil
 * @Description: 反射相关工具类
 * @author tonatyw
 */
public class ReflectUtil {
	/**
	 * 
	 * @Title: processMethod
	 * @Description: 根据对象和方法名称和参数调用方法
	 * @author tonatyw
	 * @param c 对象class
	 * @param methodName 方法名
	 * @param parameter 参数集
	 * @throws Exception
	 * @return Object
	 */
	public static Object processMethod(Class c,String methodName, Object... parameter)throws Exception{
		Class[] classArray = new Class[parameter.length];
		int len = parameter.length;
		for(int i=0;i<len;i++){
			classArray[i] = parameter[i].getClass();
		}
		return c.getMethod(methodName, classArray).invoke(c.newInstance(), parameter);
	}
	/**
	 * 
	 * @Title: getPrimitiveClass
	 * @Description: 根据基本类型名称获取对应class
	 * @author tonatyw
	 * @param typeName 基本类型名称
	 * @return Class<?>
	 */
	public static final Class<?> getPrimitiveClass(String typeName) {
	    if (typeName.equals("byte"))
	        return byte.class;
	    if (typeName.equals("short"))
	        return short.class;
	    if (typeName.equals("int"))
	        return int.class;
	    if (typeName.equals("long"))
	        return long.class;
	    if (typeName.equals("char"))
	        return char.class;
	    if (typeName.equals("float"))
	        return float.class;
	    if (typeName.equals("double"))
	        return double.class;
	    if (typeName.equals("boolean"))
	        return boolean.class;
	    if (typeName.equals("void"))
	        return void.class;
	    throw new IllegalArgumentException("Not primitive type : " + typeName);
	}
}
