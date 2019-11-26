package controller;

import java.util.Observer;

public interface IObservable {
	
	public void subscribe(Observer obj);
	public void unsubscribe(Observer obj);
	
	public void notifyObservers();

	public Object getUpdate();
	
}
