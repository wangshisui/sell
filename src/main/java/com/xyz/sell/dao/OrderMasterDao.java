package com.xyz.sell.dao;

import com.xyz.sell.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author:zhangyx
 * @Date:Created in 21:562018/10/28
 * @Modified By:
 */
public interface OrderMasterDao extends JpaRepository<OrderMaster ,String> {

    //1.按照人来查所有返回
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);




}
