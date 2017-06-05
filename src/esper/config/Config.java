package esper.config;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import events.FollowEvent;
import events.NewPostEvent;
import events.NotificationEvent;
import events.UnFollowEvent;

public class Config {

    private EPServiceProvider engine;

    public Config() {
    }

    public void init() {
        engine = EPServiceProviderManager.getDefaultProvider();
        registerEvents();
    }

    private void registerEvents() {
        engine.getEPAdministrator().getConfiguration().addEventType(NotificationEvent.class);
        engine.getEPAdministrator().getConfiguration().addEventType(NewPostEvent.class);
        engine.getEPAdministrator().getConfiguration().addEventType(FollowEvent.class);
        engine.getEPAdministrator().getConfiguration().addEventType(UnFollowEvent.class);
    }

}
