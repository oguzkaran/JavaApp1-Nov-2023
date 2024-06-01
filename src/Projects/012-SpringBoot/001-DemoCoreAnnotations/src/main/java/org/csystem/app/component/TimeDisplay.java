package org.csystem.app.component;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TimeDisplay {
    private TimeInfo m_timeInfo;

    public TimeInfo getTimeInfo()
    {
        return m_timeInfo;
    }

    @Autowired //setter injection
    public void setTimeInfo(TimeInfo timeInfo)
    {
        m_timeInfo = timeInfo;
    }

    @PostConstruct
    public void display()
    {
        m_timeInfo.displayLocalTime();
    }
}
