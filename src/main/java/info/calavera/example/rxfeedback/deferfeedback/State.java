package info.calavera.example.rxfeedback.deferfeedback;

public class State implements Message {
	private long sum;
	private boolean addNextSumAgainWithDelay;
	public State() {
		this.sum = 0;
		this.addNextSumAgainWithDelay = false;
	}

	private State(long sum, boolean addNextSumAgainWithDelay) {
		this.sum = sum;
		this.addNextSumAgainWithDelay = addNextSumAgainWithDelay;
	}
	
	public State update(Message m) {
		System.out.println("Updating state with: " + m);
		return new State(this.sum + m.getPayload(), m.getPayload() % 3 == 0);
	}

	public boolean isAddNextSumAgainWithDelay() {
		return addNextSumAgainWithDelay;
	}

	@Override
	public long getPayload() {
		return sum;
	}

	@Override
	public String toString() {
		return "State [sum=" + sum + "]";
	}
}
