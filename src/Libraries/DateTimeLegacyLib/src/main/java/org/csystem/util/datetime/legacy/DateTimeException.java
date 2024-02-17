package org.csystem.util.datetime.legacy;

@Deprecated(since = "11.1.0", forRemoval = true)
public class DateTimeException extends RuntimeException  {
    public DateTimeException()
    {}

    public DateTimeException(String msg)
    {
        super(msg);
    }
}
