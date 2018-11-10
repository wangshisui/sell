package com.xyz.sell.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xyz.sell.enums.ProductStatusEnum;
import com.xyz.sell.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**商品名称
 * @Author:zhangyx
 * @Date:Created in 15:532018/10/28
 * @Modified By:
 */
@Entity
@Data
@DynamicUpdate
public class ProductInfo {
    @Id
    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;

    private String productIcon;

    private Integer productStatus;

    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    //上架和下架的枚举
    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtil.getByCode(productStatus,ProductStatusEnum.class);
    }

}
