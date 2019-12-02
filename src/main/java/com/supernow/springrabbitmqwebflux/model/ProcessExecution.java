package com.supernow.springrabbitmqwebflux.model;

import lombok.Data;

@Data
public class ProcessExecution {

    private String processId;
    private String status;

}
