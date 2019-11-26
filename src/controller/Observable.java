package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observer;

public class Observable {
	private ArrayList<IObserver> observers;
	
	public Observable() {
		this.observers = new ArrayList<IObserver>();
	}
	
	public void subscribe(IObserver observer) {
		observers.add(observer);
	}
	public void unsubscribe(IObserver observer) {
		observers.remove(observer);
	}
	public void notifyObservers() throws IOException {
		for(IObserver observer: observers) {
			observer.update();
		}
	}
}
