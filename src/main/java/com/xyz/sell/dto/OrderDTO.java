package com.xyz.sell.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xyz.sell.dataobject.OrderDetail;
import com.xyz.sell.enums.OrderStatusEnum;
import com.xyz.sell.enums.PayStatusEnum;
import com.xyz.sell.utils.serializer.Data2LongSerializer;
import lombok.Data;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author:zhangyx
 * @Date:Created in 20:012018/10/29
 * @Modified By:
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private Integer orderStatus;

    private Integer payStatus;
     @JsonSerialize(using = Data2LongSerializer.class)
    private Date createTime;
    @JsonSerialize(using = Data2LongSerializer.class)
    private Date updateTime;
    private List<OrderDetail> orderDetailList;
}
