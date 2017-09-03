package thread_per_message_pattern;

public class Helper {
	
	public void handle(int count, char c) {
		System.out.println("    handle(" + count + ", " + c + " ) BEGIN");
		for (int i = 0; i < count; i++) {
			slowly();
			System.out.print(c);
		}
		System.out.println("");
		System.out.println("    handle(" + count + ", " + c + " ) END");
	}
	
	/**
	 * <DT>スレッド待機</DT>
	 */
	private void slowly() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
