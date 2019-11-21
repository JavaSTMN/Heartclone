package controller;

import java.io.IOException;

public interface IObserver {
	public void update();
	void setObservable(Observable obj);
}
