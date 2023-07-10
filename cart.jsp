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
        <link rel="stylesheet" href="./css/cart.css" />
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
                                <a class="item-menu-content" href="index.html">Trang chủ</a>
                            </div>
                            <div class="item">
                                <a class="item-menu-content" href="fooddetail.html">Sản phẩm</a>
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
                <h1 style="margin-bottom: 50px;">Giỏ hàng</h1>
                <hr/>
               <div id="cart">
                    <c:if test="${cartItems.size() == 0}">
                        <div style="font-size: 25px; font-weight: 700;">
                            Hiện giỏ hàng của người dùng chưa có sản phẩm ! <br><br> Xin vui lòng chọn món để đặt...
                        </div>
                    </c:if>
                    <c:if test="${cartItems.size() > 0}">
                        <div id="cart-items">
                            <ul>


                                <c:forEach items="${cartItems}" var="item" varStatus="loop">
                                    <li>
                                        <div class="item">
                                            <div class="item-details">
                                                <img src="${item.foodImg}" alt="Product Image" />
                                                <div class="item-info">
                                                    <div class="item-name">${item.foodName}</div>
                                                    <div class="item-price">Giá thành: 10.000 VNĐ</div>
                                                    <div class="item-quantity">
                                                        <button class="quantity-btn" onclick="decreaseQuantity(${loop.index + 1})">-</button>
                                                        <span id="quantity-${loop.index + 1}">${item.quantity}</span>
                                                        <button class="quantity-btn" onclick="increaseQuantity(${loop.index + 1})">+</button>
                                                        <button class="remove-btn" onclick="removeItem(${loop.index + 1})">Xóa</button>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                </c:forEach> 
                            </ul>
                        </div>
                        <hr/>


                        <div id="cart-summary">
                            <div id="total-amount">
                                <span>Tổng tiền:</span>
                                <span id="total-amount-value">${totalAmount} VNĐ</span>
                            </div>
                            <a href="checkout.html">
                                <button class="btn btn-primary">Thanh toán</button>
                            </a>
                        </div>
                    </c:if> 

                </div>
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
        <script>
            function decreaseQuantity(itemId) {
                const quantityElement = document.getElementById('quantity-' + itemId);
                let quantity = parseInt(quantityElement.textContent);
                if (quantity > 1) {
                    quantity--;
                    quantityElement.textContent = quantity;
                    updateTotalAmount();
                }
            }

            function increaseQuantity(itemId) {
                const quantityElement = document.getElementById('quantity-' + itemId);
                let quantity = parseInt(quantityElement.textContent);
                quantity++;
                quantityElement.textContent = quantity;
                updateTotalAmount();
            }

            function updateTotalAmount() {
                let totalAmount = 0;
                const itemElements = document.getElementsByClassName("item");
                for (let i = 0; i < itemElements.length; i++) {
                    const quantityElement = itemElements[i].querySelector(".item-quantity span");
                    const priceElement = itemElements[i].querySelector(".item-price");
                    const quantity = parseInt(quantityElement.textContent);
                    const price = parseInt(priceElement.textContent);
                    totalAmount += quantity * price;
                }
                document.getElementById("total-amount-value").textContent = totalAmount + " VNĐ";
            }
        </script>

    </body>
</html>
