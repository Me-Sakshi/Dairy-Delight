package com.dairyproject.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorDto {

    private String errorMsg;
    private String statusCOde;

}
