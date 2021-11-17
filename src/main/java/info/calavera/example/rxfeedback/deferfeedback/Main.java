package info.calavera.example.rxfeedback.deferfeedback;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		// simulate hot observable emitting external events
		final Observable<Message> sourceStream = Observable
				.interval(200, MILLISECONDS)
				.map(signal -> new SourceMessage(signal));
		
		// feedback needs to be referencable through final variable in the
		// next step, but we also can't define it yet, so prepare a mutable
		// container for it
		final MutableObservableHolder feedback = new MutableObservableHolder();
		
		// accumulate source and feedback messages in the state
		final Observable<State> stateStream = sourceStream
				.mergeWith(Observable.defer(() -> feedback.get()))
				.scan(new State(), (state, message) -> state.update(message))
				.share(); // prevents infinite subscription loops
		
		feedback.set(stateStream
				.flatMap(state -> {
					if (state.isAddNextSumAgainWithDelay()) {
						return stateStream
								.take(1)
								.map(nextState -> new FeedbackMessage(nextState.getPayload()))
								.delay(100, MILLISECONDS);
					} else {
						return Observable.empty();
					}
				}));
		
		Disposable subscription = stateStream
			.take(10)
			.subscribe(System.out::println);
		
		while (!subscription.isDisposed()) {
			Thread.sleep(1000);
		}
	}
}
