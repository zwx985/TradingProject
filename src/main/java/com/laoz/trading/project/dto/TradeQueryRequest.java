package com.laoz.trading.project.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TradeQueryRequest {

    @NotBlank(message = "名称不能为空")
    private String name;

}
