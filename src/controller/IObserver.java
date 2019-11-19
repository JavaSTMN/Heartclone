package controller;

public interface IObserver {
	public void update();
	public void setObservable(IObservable obj);
}
