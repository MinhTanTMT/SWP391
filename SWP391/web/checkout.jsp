<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Foodshop</title>
        <link rel="stylesheet" href="./css/homepage.css" />
        <link rel="stylesheet" href="./css/checkout.css" />
        <!-- Font Awesome -->
        <link
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
            rel="stylesheet"
            />
        <!-- Google Fonts -->
        <link
            href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
            rel="stylesheet"
            />
        <!-- MDB -->
        <link
            href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.3.1/mdb.min.css"
            rel="stylesheet"
            />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
            />
    </head>
    <body>
        <div id="wrapper">
            <div id="header-cover">
                <div id="header">
                    <div id="header-left">
                        <a href="index.html" class="logo">
                            <img
                                class="img-logo"
                                src="./images/Foodshop-removebg.png"
                                alt=""
                                />
                        </a>
                        <div id="menu">
                            <div class="item">
                                <a class="item-menu-content" href="home">Trang chủ</a>
                            </div>
                            <div class="item">
                                <a class="item-menu-content" href="menu">Sản phẩm</a>
                            </div>
                            <div class="item">
                                <a class="item-menu-content" href="orderlookup.html"
                                   >Tra cứu đơn hàng</a
                                >
                            </div>
                            <div class="item">
                                <a class="item-menu-content" href="support.html">Hỗ trợ</a>
                            </div>
                        </div>
                    </div>
                    <div id="header-actions">
                        <div class="item">
                            <form style="position: relative">
                                <input
                                    class="search-engine"
                                    type="text"
                                    placeholder="Tìm kiếm..."
                                    style="height: 30px"
                                    />
                                <i
                                    class="fa-solid fa-magnifying-glass"
                                    style="position: absolute; right: 5px; top: 6px"
                                    ></i>
                            </form>
                        </div>
                        <div class="item">
                            <a href="login_register.html"><i class="fa-solid fa-user"></i></a>
                        </div>
                        <div class="item">
                            <a href="cart.html"><i class="fa-solid fa-cart-shopping"></i></a>
                        </div>
                    </div>
                </div>
            </div>
            <div id="wp-products">
                <h1 style="margin-bottom: 50px;">Thông tin đơn hàng</h1>
                <hr/>
                <div id="cart">
                    <c:if test="${cartItems.size() == 0}">
                        <div style="font-size: 25px; font-weight: 700;">
                            Hiện giỏ hàng của người dùng chưa có sản phẩm ! <br><br> Xin vui lòng chọn món để đặt...
                        </div>
                    </c:if>
                    <c:if test="${cartItems.size() > 0}">
                      <form method="post" action="checkout">
                        <div id="cart-items-checkout" style="display:block;">
                            <div class="cartItems" id="checkout" style="display:flex; margin-bottom: 10px"> 
                                <div style="font-size: 14px; font-weight: 700; width:25%;">
                                    Địa chỉ:
                                </div>
                                <div style="width: 75%;">
                                    <textarea type="text" name="address" style="font-size: 12px; padding: 10px; height: 150px; width: 100%">${defaultAddress}</textarea>
                                </div>
                            </div>
                            <div class="cartItems" id='checkout' style="display:flex; margin-bottom: 10px">
                                <div style="font-size: 14px; font-weight: 700; width:25%">
                                    Số điện thoại:
                                </div>
                                <div style="width: 75%;">
                                    <input type="text" name="phone" value="${defaultPhoneNumber}" style="font-size: 12px; padding: 10px; height: 30px;">
                                </div>
                            </div>
                            <div class="cartItems" id="checkout" style="display:flex; margin-bottom: 10px">
                                <div style="font-size: 14px; font-weight: 700; width:25%">
                                    Món ăn:
                                </div>
                                <div style="width: 75%;">
                                    <c:forEach items="${cartItems}" var="item" varStatus="loop">
                                        <div class="carts">
                                            <div class="item-details">
                                                <div class="item-info">
                                                    <span class="item-name">${item.foodName}</span> X <span id="quantity">${item.quantity}</span>
                                                    <span style="display:none;" class="item-price-value" id="item-price-value">${item.priceFinal}</span>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach> 
                                </div>
                            </div>

                        </div>

                        <hr/>
                        <div id="cart-summary">
                            <div id="total-amount">
                                <span>Tổng tiền:</span>
                                <span id="total-amount-value"></span>
                            </div>
                                <button type="submit" class="btn btn-primary">Xác nhận</button>     
                        </div>
                      </form>
                    </c:if> 

                </div>
            </div>

            <div id="footer">
                <div class="box">
                    <div class="logo">
                        <img  
                            class="footer-logo"
                            src="./images/Foodshop-removebg.png"
                            alt=""
                            />
                    </div>
                    <!-- <p>Cung cấp sản phẩm với chất lượng an toàn cho quý khách</p> -->
                </div>
                <div class="box">
                    <h3>NỘI DUNG</h3>
                    <ul class="quick-menu">
                        <div class="item">
                            <a href="">Trang chủ</a>
                        </div>
                        <div class="item">
                            <a href="">Sản phẩm</a>
                        </div>
                        <div class="item">
                            <a href="">Blog</a>
                        </div>
                        <div class="item">
                            <a href="">Liên hệ</a>
                        </div>
                    </ul>
                </div>
                <div class="box">
                    <h3>LIÊN HỆ</h3>
                    <form action="">
                        <input type="text" placeholder="Địa chỉ email" />
                        <button>Nhận tin</button>
                    </form>
                </div>
            </div>
        </div>
        <!-- MDB -->
        <!--        <script
                    type="text/javascript"
                    src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.3.1/mdb.min.js"
                ></script>
                <script src="./js/script.js"></script>
                <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>-->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
            function formatCurrency(amount) {
                return amount.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});
            }
            // Tính tổng giá tiền
            var totalPrice = 0;
            var itemPrices = document.getElementsByClassName("item-price-value");
            var quantities = document.querySelectorAll("#quantity");
            for (var i = 0; i < itemPrices.length; i++) {
                var price = parseFloat(itemPrices[i].textContent);
                var quantity = parseFloat(quantities[i].textContent);
                totalPrice += price * quantity;
            }
            // Hiển thị tổng tiền
            const formattedTotalAmount = formatCurrency(totalPrice);
            document.getElementById("total-amount-value").textContent = formatCurrency(totalPrice);
        </script>

    </body>
</html>
