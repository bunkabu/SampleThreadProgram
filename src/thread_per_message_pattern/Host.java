package thread_per_message_pattern;

/**
 * <DT>ホストクラス</DT>
 * <DD>要求に対してスレッドを生成するクラスです。</DD>
 */
public class Host {
	
	private final Helper helper = new Helper();
	
	public void request(final int count, final char c) {
		System.out.println("    request(" + count + ", " + c + " ) BEGIN");
		// 無名インナークラスを使用して、スレッドを起動します。
		new Thread() {
			public void run() {
				helper.handle(count, c);
			}
		}.start();
		
/* ThreadクラスのコンストラクタにRunnableクラスの無名インタークラスを与えます。
		new Thread(
			new Runnable() {
				public void run() {
					helper.handle(count, c);
				}
			}
		).start();
*/
	
/* 下記のように変数runnableを作成してから、Threadクラスのコンストラクタに与える方法もあり。
		Runnable runnable = new Runnable() {
			public void run() {
				helper.handle(count, c);
			}
		};
		new Thread(runnable).start();
*/	
		
		System.out.println("    request(" + count + ", " + c + " ) END");
	}
}
