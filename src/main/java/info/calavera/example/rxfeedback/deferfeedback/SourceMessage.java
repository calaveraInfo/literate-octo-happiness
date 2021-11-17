package info.calavera.example.rxfeedback.deferfeedback;

public class SourceMessage implements Message {
	
	protected long payload;

	public SourceMessage(long payload) {
		this.payload = payload;
	}

	@Override
	public long getPayload() {
		return payload;
	}

	@Override
	public String toString() {
		return "SourceMessage [payload=" + payload + "]";
	}
	
}
