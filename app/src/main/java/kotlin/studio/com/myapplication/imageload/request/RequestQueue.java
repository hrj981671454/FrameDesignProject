package kotlin.studio.com.myapplication.imageload.request;

import android.util.Log;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 请求队列，所有图片加载的请求保存在该队列中
 * @author Jason
 * QQ: 1476949583
 * @date 2016年3月9日
 * @version 1.0
 */
public class RequestQueue {
	//队列
	//多线程的数据共享
	//阻塞队列
	//生成效率和消费效率相差甚远，处理好兼顾效率和线程安全问题
	//concurrent出现了
	//优先级的阻塞队列
	//1.当队列中没有产品时，消费者被阻塞
	//2.使用优先级，优先级高的产品会被优先消费
	//前提：每个产品的都有一个编号（实例化出来的先后顺序）
	//优先级的确定，受产品编号的影响，但是由加载策略所决定
	
	private BlockingQueue<BitmapRequest> mRequestQueue = new PriorityBlockingQueue<BitmapRequest>();
	//转发器的数量
	private int                 threadCount;
	//一组转发器
	private RequestDispatcher[] mDispachers;

	//i++ ++i线程不安全
	//线程安全
	private AtomicInteger ai = new AtomicInteger(0);

	/**
	 * 构造函数
	 * @param threadCount 指定线程的数量（转发器的数量）
	 */
	public RequestQueue(int threadCount) {
		this.threadCount = threadCount;
	}


	/**
	 * 添加请求
	 * @param request
	 */
	public void addRequest(BitmapRequest request){
		if(!mRequestQueue.contains(request)){
			//给请求编号
			request.setSerialNO(ai.incrementAndGet());
			mRequestQueue.add(request);
			Log.d("jason", "添加请求"+request.getSerialNO());
		}else{
			Log.d("jason", "请求已经存在"+request.getSerialNO());
		}
	}


	/**
	 * 开始
	 */
	public void start(){
		//先停止，再启动
		stop();
		startDispatchers();
	}


	private void startDispatchers() {
		mDispachers = new RequestDispatcher[threadCount];
		//初始化所有的转发器
		for (int i = 0; i < threadCount; i++) {
			RequestDispatcher p = new RequestDispatcher(mRequestQueue);
			mDispachers[i] = p;
			//启动线程
			mDispachers[i].start();
		}
	}
	
	/**
	 * 停止
	 */
	public void stop(){
		if(mDispachers != null && mDispachers.length > 0){
			for (int i = 0; i < mDispachers.length; i++) {
				//打断
				mDispachers[i].interrupt();
			}
		}
	}
	
}
