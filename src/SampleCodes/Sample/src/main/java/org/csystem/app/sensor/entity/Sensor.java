package org.csystem.app.sensor.entity;

public class Sensor {
    private int m_id;
    private String m_name;
    private String m_host;
    private int m_port;

    public Sensor(int id, String name, String host, int port)
    {
        m_id = id;
        m_name = name;
        m_host = host;
        m_port = port;
    }

    public int getId()
    {
        return m_id;
    }

    public void setId(int id)
    {
        m_id = id;
    }

    public String getName()
    {
        return m_name;
    }

    public void setName(String name)
    {
        m_name = name;
    }

    public String getHost()
    {
        return m_host;
    }

    public void setHost(String host)
    {
        m_host = host;
    }

    public int getPort()
    {
        return m_port;
    }

    public void setPort(int port)
    {
        m_port = port;
    }

    //...

    @Override
    public String toString()
    {
        return String.format("%d, %s, %s:%d", m_id, m_name, m_host, m_port);
    }
}
