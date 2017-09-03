package read_write_lock_pattern;

import java.util.Random;

/**
 * Dataインスタンスへ書く処理を行うスレッドです。
 */
public class WriterThread extends Thread {
	
	private static final Random random = new Random();	// スレッド待機用
	private final Data data;							// データ
	private final String filler;						// 書き込み文字
	private int index = 0;								// インデックス
	
	public WriterThread(Data data, String filler) {
		this.data = data;
		this.filler = filler;
	}
	
	public void run() {
		try {
			while (true) {
				char c = nextchar();
				data.write(c);
				Thread.sleep(random.nextInt(1000));
			}
		} catch (InterruptedException e) {
		}
	}
	
	private char nextchar() {
		char c = filler.charAt(index);
		index++;
		if (index >= filler.length()) {
			index = 0;
		}
		return c;
	}
}
