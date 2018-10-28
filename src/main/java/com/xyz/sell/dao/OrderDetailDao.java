package com.xyz.sell.dao;

import com.xyz.sell.dataobject.OrderDetail;
import com.xyz.sell.dataobject.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author:zhangyx
 * @Date:Created in 22:432018/10/28
 * @Modified By:
 */
public interface OrderDetailDao extends JpaRepository<OrderDetail,String> {

    List<OrderMaster> findByOrderId(String orderId);
}
