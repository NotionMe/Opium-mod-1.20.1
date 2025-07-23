package net.notion.dvewl.yakasditch1;

import net.fabricmc.api.ClientModInitializer;
import net.notion.dvewl.yakasditch1.client.MouseScrollHandler;

public class YakasDitch1Client implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		MouseScrollHandler.register();
	}
}