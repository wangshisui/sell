package com.xyz.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**类目
 * @Author:zhangyx
 * @Date:Created in 13:282018/10/28
 * @Modified By:
 */
@Entity
@DynamicUpdate
@Data
public class ProductCategory {
    @Id
    @GeneratedValue
    private Integer categoryId;
    //类目名字
    private String categoryName;
    //类目类型
    private Integer categoryType;

}
