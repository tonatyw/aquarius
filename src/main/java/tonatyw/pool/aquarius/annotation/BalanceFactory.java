package tonatyw.pool.aquarius.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;

import tonatyw.pool.aquarius.annotation.handle.base.Balance;
import tonatyw.pool.aquarius.constants.Sets;
import tonatyw.pool.aquarius.util.ReflectUtil;

/**
 * 
 * @ClassName BalanceFactory
 * @Description: 负载类型工厂类
 * @author tonatyw
 */
public class BalanceFactory {
	/** 注解包路径 暂不考虑文件配置 暂不考虑集成springboot */
	private static String base = "tonatyw.pool.aquarius.annotation.";
	/** 负载实现路径 */
	private static String handleBast = "tonatyw.pool.aquarius.annotation.handle.";
	/**
	 * 
	 * @Title: getInstance
	 * @Description: 根据对象和参数名读取注解生成池
	 * @author tangjc
	 * @param clas 注解所在类class
	 * @param field 注解所在字段名
	 * @return Balance 规定返回池接口
	 */
	public static Balance getInstance(Class clas,String field) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
		Field fie = clas.getField(field);
		Annotation[] an = fie.getAnnotations();
		String str = "";
		String balanceName = "";
		for(Annotation a:an){
			//定制一个规则
			String typeDetail = a.toString();
			//判断是否是annotation下的注解
			if(typeDetail.indexOf(base)!=-1){//路径符合 这里有bug哈
				str = typeDetail;
				balanceName = a.annotationType().getName();
				break;
			}
		}
		
		if(StringUtils.isNotEmpty(str)){
			//获取class
			String classPath = StringUtils.substringBetween(str, "class ", ",");
			String strs = StringUtils.substringBetween(str, "str=[", "]");
			String paramsStr = StringUtils.substringBetween(str, "paramType=[", "]");
			
			// 获取paramType
			String[] paramsArray = paramsStr.split(", ");
			Class[] paramType = new Class[paramsArray.length];
			for(int i=0;i<paramsArray.length;i++){
				String[] tmpArray = paramsArray[i].split(" ");
				String dataTypeClass = "";
				if(tmpArray.length<2){//基本数据类型
					paramType[i] = ReflectUtil.getPrimitiveClass(tmpArray[0]);
				}else{
					paramType[i] = Class.forName(tmpArray[1]);
				}
			}
			System.out.println(str);
			String[] strArray = strs.split(", ");
			String[][] ss = new String[strArray.length][strArray[0].split(",").length];
			Object[][] params = new Object[ss.length][ss[0].length];
			for(int i=0;i<strArray.length;i++){
				String[] s = strArray[i].split(",");
				Object[] objs = new Object[s.length];
				for(int j=0;j<s.length;j++){
					try{
						objs[j] = paramType[j].cast(s[j]);
					}catch(ClassCastException cce){
						//转换错误 一般为num型和string型之间的转换
						Class tmpClass = Class.forName(Sets.getBaseMap().get(paramType[j].getSimpleName()));
						Method method = tmpClass.getMethod("valueOf", String.class);
						objs[j] = method.invoke(tmpClass,String.valueOf(s[j]));
					}
				}
				params[i] = objs;
			}
			Class aimClass = Class.forName(classPath);
			// 获取名称
			String className = StringUtils.substringAfterLast(balanceName, ".");
			Class typeClass = Class.forName(handleBast.concat(className));
			Balance b = (Balance) typeClass.newInstance();
			
			b.init(aimClass, params, paramType);
			return b;
		}else{
			return null;
		}
	}
}
