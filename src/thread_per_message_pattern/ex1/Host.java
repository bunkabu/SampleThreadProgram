package thread_per_message_pattern.ex1;

import java.util.concurrent.ThreadFactory;

/**
 * <DT>ホストクラス</DT>
 * <DD>要求に対してスレッドを生成するクラスです。</DD>
 */
public class Host {
	
	private final Helper helper = new Helper();	
	private final ThreadFactory threadFactory;
	
	public Host(ThreadFactory threadFactory) {
		this.threadFactory = threadFactory;
	}
	
	public void request(final int count, final char c) {
		System.out.println("    request(" + count + ", " + c + " ) BEGIN");
		threadFactory.newThread(new Runnable() {
			public void run() {
				helper.handle(count, c);
			}
		}).start();
		System.out.println("    request(" + count + ", " + c + " ) END");
	}
}
