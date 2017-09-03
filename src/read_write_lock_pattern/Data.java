package read_write_lock_pattern;

/**
 * Dataクラスは自身が所有しているバッファのデータを読み書きすることができるクラスです。
 */
public class Data {

	private final char[] buffer;
	private final ReadWriteLock lock = new ReadWriteLock();
	
	/**
	 * <DT>Dataクラスコンストラクタ</DT>
	 * <DD>引数の数だけバッファを生成し、文字「*」を格納します。</DD>
	 * @param size バッファサイズ
	 */
	public Data(int size) {
		this.buffer = new char[size];
		for (int i = 0; i < buffer.length; i++) {
			buffer[i] = '*';
		}
	}
	
	/**
	 * <DT>バッファ読み込み処理</DT>
	 * <DD>自身が所有するバッファデータを取得します。</DD>
	 * @return char型配列のバッファデータ。
	 * @throws InterruptedException
	 */
	public char[] read() throws InterruptedException {
		try {
			lock.readLock();
			return doRead();
		} finally {
			lock.readUnlock();
		}
	}
	
	/**
	 * <DT>バッファ書き込み処理</DT>
	 * <DD>バッファに文字を書き込みます。</DD>
	 * @param c 書き込み文字
	 * @throws InterruptedException
	 */
	public void write(char c) throws InterruptedException {
		lock.writeLock();
		try {
			doWrite(c);
		} finally {
			lock.writeLock();
		}
	}
	
	/**
	 * <DT>バッファ読み込み実行処理</DT>
	 * <DD>バッファデータをコピーして、char型配列で返却します。</DD>
	 * @return char型配列のバッファデータ
	 */
	private char[] doRead() {
		char[] newbuf = new char[buffer.length];
		for (int i = 0; i < buffer.length; i++) {
			newbuf[i] = buffer[i];
		}
		slowly();
		return newbuf;
	}
	
	/**
	 * <DT>バッファ書き込み実行処理</DT>
	 * <DD>バッファ領域の全てに文字を書き込みます。</DD>
	 * @param c 書き込み文字
	 */
	private void doWrite(char c) {
		for (int i = 0; i < buffer.length; i++) {
			buffer[i] = c;
			slowly();
		}
	}
	
	/**
	 * <DT>スレッド待機処理</DT>
	 * <DD>スレッド50ミリ秒を待機させます。</DD>
	 */
	private void slowly() {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {	
		}
	}
}
