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
                                        <div class="carts">
                                            <div class="item-details">
                                                <img src="${item.foodImg}" alt="Product Image" />
                                                <div class="item-info">
                                                    <div class="item-name">${item.foodName}</div>
                                                    <div class="item-price" id="item-price" data-price="${item.priceFinal}">Giá thành: <span class="item-price-value">${item.priceFinal}</span></div>
                                                    <div class="item-quantity">
                                                        <button class="quantity-btn" name="decrease" value="${item.foodId}"  data-quantity="${item.quantity - 1}" data-index="${loop.index}" class="quantity-btn" onclick="decreaseQuantity(${loop.index + 1},${item.foodId})">-</button>
                                                        <span id="quantity-${loop.index + 1}">${item.quantity}</span>
                                                        <button class="quantity-btn" name="increase" value="${item.foodId}" data-quantity="${item.quantity + 1}" class="quantity-btn" onclick="increaseQuantity(${loop.index + 1}, ${item.foodId})">+</button>
                                                        <!--<button class="remove-btn" onclick="removeItems(${customerId}, ${item.foodId})">Xóa</button>-->
                                                        <form action="cart" method="post">
                                                            <button type="submit" class="remove-btn" name="remove" value="${item.foodId}">Xóa</button>
                                                        </form>
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
                                <span id="total-amount-value"></span>
                            </div>
                            <form action="cart" method="post">
                                <input style="display:none" name="checkout" value="true"></input>
                                <button class="btn btn-primary">Thanh toán</button>
                            </form>
                        </div>
                    </c:if> 

                </div>
            </div>
            <div class="popup-dialog" style="display: none;">
                <!-- Đoạn mã HTML/JSP cho nội dung dialog -->
                <h2>Thông báo</h2>
                <p>Giỏ hàng sẽ không hiển thị các món mà danh sách <br> sản phẩm không có trong menu của ngày hôm nay:</p>
                <ul>
                    <li><b>${foodNames}</b></li>
                        <!--<li>${cartItemsNotInMenu}</li>-->
                </ul>

                <!-- Nút đóng dialog -->
            <button class="close-dialog"><i class="fas fa-times"></i></button>
            <button class="close-button">Đóng</button>

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

                     let totalAmount = 0;
                     function formatCurrency(amount) {
                         // Chuyển đổi giá trị số thành định dạng tiền tệ
                         return amount.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});
                     }


                     function decreaseQuantity(itemId, foodId) {
                         const quantityElement = document.getElementById('quantity-' + itemId);
                         let quantity = parseInt(quantityElement.textContent);
                         if (quantity > 1) {
                             quantity--;
                             quantityElement.textContent = quantity;
                             updateAvailableItem(foodId, quantity);
                             updateTotalAmount();
                         }
                     }
                     function increaseQuantity(itemId, foodId) {
                         const quantityElement = document.getElementById('quantity-' + itemId);//quantity-1
                         let quantity = parseInt(quantityElement.textContent);
                         quantity++;
                         quantityElement.textContent = quantity;
                         updateAvailableItem(foodId, quantity);
                         updateTotalAmount();
                     }

                     function updateTotalAmount() {
                         let totalAmount = 0;
                         const itemElements = document.getElementsByClassName("carts");
                         for (let i = 0; i < itemElements.length; i++) {
                             const quantityElement = itemElements[i].querySelector(".item-quantity span");
                             const priceElement = itemElements[i].querySelector(".item-price span");
                             const quantity = parseFloat(quantityElement.textContent);
                             let price = 0;
                             if (!isNaN(priceElement.textContent)) {
                                 price = parseFloat(priceElement.textContent);
                             } else {
                                 const ratio = priceElement.textContent.split(".").length - 1;
                                 const priceRatio = Math.pow(1000, ratio);
                                 price = parseFloat(priceElement.textContent.slice(0, -2)) * priceRatio;
                             }
                             totalAmount += quantity * price;
                         }
                         const formattedTotalAmount = formatCurrency(totalAmount);
                         document.getElementById("total-amount-value").textContent = formatCurrency(totalAmount);
                     }
                     var contextPath = "<%= request.getContextPath()%>";

                     function removeItems(customerId, foodId, index) {
                         $.ajax({
                             url: contextPath + "/cart", // URL của servlet
                             type: "POST", // Phương thức POST để gửi yêu cầu xóa
                             data: {foodId: foodId, functionId: "remove"}, // Tham số truyền đi
                             success: function (response) {
                                 window.location.href = contextPath + "/cart";
                             },
                             error: function (xhr, status, error) {
                                 // Xử lý lỗi (nếu có)

                             }
                         });
                     }

                     function updateAvailableItem(foodId, quantity) {
                         $.ajax({
                             url: contextPath + "/cart", // URL của servlet
                             type: "POST", // Phương thức POST để gửi yêu cầu xóa
                             data: {foodId: foodId, quantity: quantity, functionId: "add"}, // Tham số truyền đi
                             success: function (response) {

                             },
                             error: function (xhr, status, error) {
                                 // Xử lý lỗi (nếu có)
                             }
                         });
                     }

                     updateTotalAmount();

                     const itemPriceElements = document.querySelectorAll('.item-price-value');
                     itemPriceElements.forEach(function (element) {
                         const price = parseFloat(element.textContent);
                         element.textContent = formatCurrency(price);
                     });

            <c:forEach items="${cartItems}" var="item">
                     totalAmount += ${item.priceFinal};
            </c:forEach>
            //302-320
              $(document).ready(function() {
        // Hiển thị dialog khi trạng thái cartNotification là true
            var cartNotification = ${notification};
            if (cartNotification) {
                $(".popup-overlay").show();
                $(".popup-dialog").show();
            }

            // Ẩn dialog khi nhấn vào nút đóng
            $(".close-dialog").click(function() {
            $(".popup-dialog").hide();
            $(".popup-overlay").hide();
            });
            
            $(".close-button").click(function() {
            $(".popup-dialog").hide();
            $(".popup-overlay").hide();
            });
        });
        </script>

    </body>
</html>
