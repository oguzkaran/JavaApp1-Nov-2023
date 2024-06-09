package org.csystem.app.service.networkinfo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Builder
public class NetworkInfoDTO {
    @Accessors(prefix = "m_")
    private String m_name;

    @Accessors(prefix = "m_")
    private String m_remoteHost;

    @Accessors(prefix = "m_")
    private String m_localAddress;

    @JsonProperty("ephemeralPort")
    @Accessors(prefix = "m_")
    private int m_remotePort;

    @Accessors(prefix = "m_")
    private int m_localPort;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonProperty("serviceRequestTime")
    @Accessors(prefix = "m_")
    private LocalDateTime m_serviceRequestTime;
}
