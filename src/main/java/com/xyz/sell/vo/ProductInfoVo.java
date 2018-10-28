package com.xyz.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**商品详情
 * @Author:zhangyx
 * @Date:Created in 17:022018/10/28
 * @Modified By:
 */
@Data
public class ProductInfoVo {
    @JsonProperty("id")
    private String productId;

    @JsonProperty
    private String productName;
    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("icon")
    private String productIcon;

}
