package net.loco.di;

public class HelloWorldMessageProvider implements MessageProvider {

	@Override
	public String getMesggage() {
		return "Hello World!";
	}

}
