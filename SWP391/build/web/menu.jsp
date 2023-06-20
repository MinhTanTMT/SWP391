


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
        <link rel="stylesheet" href="./css/menu.css" />
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
                <h1 style="margin-bottom: 50px;">MENU</h1>
                <div class="filter-all">
                    <!-- Category -->
                    <div class="btn-group">
                        <button type="button" class="btn btn-success dropdown-toggle" data-mdb-toggle="dropdown" aria-expanded="false">
                            Phân loại
                        </button>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="?category=3">Món chính</a></li>
                            <li><a class="dropdown-item" href="?category=2">Món phụ</a></li>
                            <li><a class="dropdown-item" href="?category=1">Đồ uống</a></li>
                            <li><hr class="dropdown-divider" /></li>
                            <li><a class="dropdown-item" href="?category=4">Tất cả</a></li>
                        </ul>
                    </div>

                    <!-- Giá -->
                    <div class="btn-group">
                        <button type="button" class="btn btn-info dropdown-toggle" data-mdb-toggle="dropdown" aria-expanded="false">
                            Giá
                        </button>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="?order=1">Dưới 100.000đ</a></li>
                            <li><a class="dropdown-item" href="?order=2">100.000đ - 200.000đ</a></li>
                            <li><a class="dropdown-item" href="?order=3">200.000đ - 300.000đ</a></li>
                            <li><a class="dropdown-item" href="?order=4">Trên 300.000đ</a></li>
                            <li><hr class="dropdown-divider" /></li>
                            <li><a class="dropdown-item" href="?order=5">Tất cả</a></li>
                        </ul>
                    </div>

                    <!-- Sắp xếp -->
                    <div class="btn-group dropend">
                        <button type="button" class="btn btn-primary">
                            <b> Top Bán chạy</b><i class="fas fa-fire"></i>
                        </button>
                       
                    </div>
                </div>
                <hr />

                <div id="wp-products" style="margin: 120px auto 0px auto !important;">
                    <ul id="list-products">
                        <c:forEach items="${foodmenu}" var="menu">
                            <div class="item">
                                <img src="${menu.img}" alt="" />  
                                <a href="detail?num=${menu.id}">
                                    <div class="name">${menu.name_food}</div>
                                </a>
                                <a href="detail?num=${menu.id}">
                                    <div class="name">${menu.price_sell}VNĐ</div>
                                </a>
                                <div class="price" href="fooddetail.html">
                                    <button class="btn btn-primary">Xem chi tiết</button>
                                </div>
                            </div>
                        </c:forEach>
                    </ul>
                </div>
                <div class="list-page">
                    <c:forEach begin="1" end="${totalPages}" varStatus="loop">
                        <div class="item">
                            <a href="?page=${loop.index}">${loop.index}</a>
                        </div>
                    </c:forEach>
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
        <script
            type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.3.1/mdb.min.js"
        ></script>
        <script src="./js/script.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
            function getListPage(page) {
                $.ajax({
                    url: 'your-controller-url',
                    type: 'GET',
                    data: {
                        page: page
                    },
                    success: function (response) {
                        // Xử lý dữ liệu trả về từ server
                        // Cập nhật danh sách bản ghi trong trang JSP
                        $('#list-products').html(response);
                    },
                    error: function () {
                        // Xử lý khi có lỗi xảy ra trong quá trình gửi yêu cầu
                        alert('Có lỗi xảy ra khi lấy danh sách bản ghi.');
                    }
                });
            }
        </script>
    </body>
</html>
