package info.calavera.example.rxfeedback.deferfeedback;

import io.reactivex.rxjava3.core.Observable;

public class MutableObservableHolder {
	private Observable<Message> observable;

	public Observable<Message> get() {
		return observable;
	}

	public void set(Observable<Message> observable) {
		this.observable = observable;
	}
	
}
