package fr.esiea;

public class HelloWorld {
	
	public String getMessage(boolean bigger) {
		if (bigger) {
			return "Hello codecov!";
		} else {
			return "Hello World!";
		}
	}

}