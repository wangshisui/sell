<html>
<#include "../order/common/head.ftl">
<body>
<div id="wrapper" class="toggled">
<!--边栏-->
    <#include "../order/common/nav.ftl">

    <!--主体内容-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-condensed table-hover table-bordered">
                        <thead>
                        <tr>
                            <th>订单ID</th>
                            <th>  姓名</th>
                            <th>手机号</th>
                            <th>地址</th>
                            <th>金额</th>
                            <th> 订单状态</th>
                            <th> 支付状态</th>
                            <th> 创建时间</th>
                            <th colspan="2"> 操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderDTOPage.content as orderDTo>
                        <tr class="success">
                            <td>${orderDTo.orderId}</td>
                            <td>${orderDTo.buyerName}</td>
                            <td> ${orderDTo.buyerPhone}</td>
                            <td> ${orderDTo.buyerAddress}</td>
                            <td> ${orderDTo.orderAmount}</td>
                            <td>${orderDTo.getOrderStatusEnum().message}</td>
                            <td> ${orderDTo.getPayStatusEnum().message}</td>
                            <td> ${orderDTo.createTime}</td>
                            <td><a href="/sell/seller/order/detail?orderId=${orderDTo.orderId}">详情</a></td>
                            <td>
                                <#if orderDTo.getOrderStatusEnum().message =="新订单">
                                    <a href="/sell/seller/order/cancel?orderId=${orderDTo.orderId}">取消</a>
                                </#if>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
                <!--分页-->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                    <#if currentPage lte 1>
                        <li class="disabled"><a href="#">上一页</a></li>
                    <#else >
                        <li ><a href="/sell/seller/order/list?page=${currentPage-1}&size=${size}">上一页</a></li>
                    </#if>
                    <#--       <li>
                               <a href="#">上一页</a>
                           </li>-->
                    <#list 1..orderDTOPage.getTotalPages() as index>
                        <#if currentPage==index>
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else >
                            <li><a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                        </#if>
                    </#list>

                    <#if currentPage gte orderDTOPage.getTotalPages()>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else >
                        <li ><a href="/sell/seller/order/list?page=${currentPage+1}&size=${size}">下一页</a></li>
                    </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>

