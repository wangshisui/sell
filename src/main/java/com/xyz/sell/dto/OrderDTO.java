package com.xyz.sell.dto;

import com.xyz.sell.dataobject.OrderDetail;
import com.xyz.sell.enums.OrderStatusEnum;
import com.xyz.sell.enums.PayStatusEnum;
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
public class OrderDTO {
    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private Integer orderStatus;

    private Integer payStatus;

    private Date createTime;

    private Date updateTime;
    private List<OrderDetail> orderDetailList;
}
