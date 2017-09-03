package read_write_lock_pattern;

public class Main {
	public static void main(String[] args) {
		Data data = new Data(10);
		new ReaderThread(data).start();
		new ReaderThread(data).start();
		new ReaderThread(data).start();
		new ReaderThread(data).start();
		new ReaderThread(data).start();
		new ReaderThread(data).start();
		new WriterThread(data, "ABCDEFGHIJKLNMOPQRSTUVWXYZ").start();
		new WriterThread(data, "abcdefghijklmnopqrstuvwxyz").start();
	}
}
