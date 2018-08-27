package kotlin.studio.com.myapplication.imageload.policy;


import kotlin.studio.com.myapplication.imageload.request.BitmapRequest;

/**
 * 加载策略
 * @author Jason
 * QQ: 1476949583
 * @date 2016年3月9日
 * @version 1.0
 */
public interface LoadPolicy {

	/**
	 * 两个BitmapRequest进行比较
	 * @param request1
	 * @param request2
	 * @return 小于0，request1 < request2，大于0，request1 > request2，等于
	 */
	public int compareTo(BitmapRequest request1, BitmapRequest request2);
	
}
