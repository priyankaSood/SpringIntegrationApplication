package com.ibm.bluemix.samples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutboundServiceImpl implements OutboundService
{
    
    @Autowired
    private InboundService inboundGateway;

    public String send(String name)
    {
        return inboundGateway.getMessage( name );
    }
}
