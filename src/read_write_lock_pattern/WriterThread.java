package read_write_lock_pattern;

import java.util.Random;

/**
 * Dataインスタンスへ書く処理を行うスレッドです。
 */
public class WriterThread extends Thread {
	
	private static final Random random = new Random();
	private final Data data;
	private final String filler;
	private int index = 0;
	
	public WriterThread(Data data, String filler) {
		this.data = data;
		this.filler = filler;
	}
	
	@Override
	public void run() {
		try {
			int count = 0;
			while (true) {
				count++;
				System.out.println("WriterThread : " + count);
				char c = nextchar();
				data.write(c);
				Thread.sleep(random.nextInt(50));
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
