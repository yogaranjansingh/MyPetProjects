package ObserverExample.ObserverExample;

import java.util.Observable;
import java.util.Observer;

public class ObserverDemo implements Observer {
	
	private ObservableDemo weatherUpdate;

	public void update(Observable o, Object arg) {
		weatherUpdate = (ObservableDemo) o;
		System.out.println("Weather Report : /n  Live weather : its  "+weatherUpdate.getWeather()+" in patna today");
	}
	
	public static void main(String[] args) {
		
		
		ObservableDemo observable = new ObservableDemo(null);
		ObserverDemo observer = new ObserverDemo();
		observable.addObserver(observer);
		observable.setWeather("Bright and Sunny..");
		observable.setWeather("Raining cats and dogs");
		
	}
	


}
