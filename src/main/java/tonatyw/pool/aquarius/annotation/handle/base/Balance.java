package tonatyw.pool.aquarius.annotation.handle.base;
/**
 * 
 * @ClassName Balance
 * @Description: 统一池接口 设计初衷为将负载算法加入池中，这里其他可以实现其他算法，暂取balance
 * @author tangjc
 */
public interface Balance<T> {
	/**
	 * 
	 * @Title: init
	 * @Description: 初始化
	 * @author tonatyw
	 * @param clas 内容对象class
	 * @param objs 内容对象实例化需要的参数 二维数组 一维长度自动解析为池的深度
	 * @param paramsType
	 * @return void
	 */
	public void init(Class<T> clas,Object[][] objs, Class[] paramsType);
	/**
	 * 
	 * @Title: get
	 * @Description: 获取内容对象
	 * @author tonatyw
	 * @return
	 * @return T
	 */
	public T get();
	/**
	 * 
	 * @Title: release
	 * @Description: 释放资源 一般用于队列 和不能共用的资源需要重写这个方法
	 * @author tonatyw
	 * @param t 需要释放的内容对象
	 * @return void
	 */
	public void release(T t);
}
