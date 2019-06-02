package tonatyw.pool.aquarius.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @ClassName HashBalance
 * @Description: hash散列均衡池自定义注解
 * @author tonatyw
 */
@Documented
@Target(ElementType.FIELD)
@Inherited
@Retention(RetentionPolicy.RUNTIME )
public @interface HashBalance{
	/** 散列对象 */
	Class clas();
	/** 对象实例化需要的参数 请使用{"a,b","c,d","e,f"}方式传入*/
	String[] str();
	/** 参数类型 */
	Class[] paramType();
}
