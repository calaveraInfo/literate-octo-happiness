package info.calavera.example.rxfeedback.deferfeedback;

public class FeedbackMessage extends SourceMessage {

	public FeedbackMessage(long payload) {
		super(payload);
	}

	@Override
	public String toString() {
		return "FeedbackMessage [payload=" + payload + "]";
	}
}
