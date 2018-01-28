public abstract class Timer {

	private int ms = 1000;

	public Timer(int ms) {
		this.ms = ms;

		Runnable r = new Runnable() {
			public void run() {
				while (true) {
					TimerBlock();
					try {
						Thread.sleep(ms);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		new Thread(r).start();

	}

	public abstract void TimerBlock();

}