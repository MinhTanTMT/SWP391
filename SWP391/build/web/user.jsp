
<%@page import="java.util.logging.Logger"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
    </head>
</html>
<link rel="stylesheet" href="./css/fooddetail.css" />
<link rel="stylesheet" href="./css/homepage.css" />
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
</head>
<body>
    <div class="wrapper">
        <!-- Navbar -->
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
                            <a class="item-menu-content" href="foodmenu.html">Sản phẩm</a>
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
                        <form style="position: relative" action="search" method="post">      
                            <input
                                class="search-engine"
                                type="text"
                                placeholder="Tìm kiếm..."
                                style="height: 30px"
                                name="find"
                                />
                            <input class="fa-solid fa-magnifying-glass"
                                   style="position: absolute; right: 5px; top: 6px"
                                   type="submit" value="?">  
                            </input>
                        </form>
                    </div>
                    <div class="item">
                        <a href="login"><i class="fa-solid fa-user"></i></a>
                    </div>
                    <div class="item">
                        <a href="cart.html"><i class="fa-solid fa-cart-shopping"></i></a>
                    </div>
                </div>
            </div>
        </div>
        <!-- Main Content -->

        <c:set var="account" value="${sessionScope.account}"/>
        <c:set var="type" value="${requestScope.type}"/>
        <c:set var="c" value="${requestScope.info}"/>


        <c:if test="${type==1}">
            <div class="product-detail">
                <!-- Picture -->
                <div class="col-sm-5">
                    <img
                        class="img-product"
                        src="./images/use.png"
                        alt="user"
                        />
                </div>
                <div class="col-sm-1"></div>
                <!-- Information -->
                <form method="post" action="user" class="product-information col-sm-6" >
                    <!-- Header -->
                    <br />
                    <br />
                    <h1>Thông tin người dùng</h1>
                    <!-- Description -->
                    <h3 class="price">Tên người dùng: ${c.full_name}</h3>

                    <h3 class="price">Ngày sinh: ${c.dob}</h3>

                    <h3 class="price">Địa chỉ: ${c.address}</h3>

                    <h3 class="price">Điện thoại: ${c.phone}</h3>

                    <h3 class="price">Giới tính: ${c.gender}</h3>

                    <input style="display: none" name="foodId" value="${c.id_acc}"/>

                    <!-- Button Add -->
                    <br />

                    <button class="btn" type="submit">Chỉnh sửa thông tin</button>
                </form>
            </div>
        </c:if>


        <c:if test="${type==2}">
            <div class="product-detail">
                <!-- Picture -->
                <div class="col-sm-5">
                    <img
                        class="img-product"
                        src="./images/use.png"
                        alt="user"
                        />
                </div>
                <div class="col-sm-1"></div>
                <!-- Information -->
                <form method="post" action="useredit" class="product-information col-sm-6" >
                    <!-- Header -->
                    <br />  <br />
                    <h1>Thông tin người dùng</h1>
                    <!-- Description -->

                    <h3 class="price">Tên người dùng: <input
                            class="price"
                            type="text"
                            style="height: 30px"
                            name="full_name"
                            value="${c.full_name}"
                            /></h3>

                    <h3 class="price">Ngày sinh: <input
                            class="price"
                            type="date"
                            style="height: 30px"
                            name="dob"
                            value="${c.dob}"
                            /></h3>

                    <h3 class="price">Địa chỉ: <input
                            class="price"
                            type="text"
                            style="height: 30px"
                            name="address"
                            value="${c.address}"
                            /></h3>

                    <h3 class="price">Điện thoại: <input
                            class="price"
                            type=""
                            style="height: 30px"
                            name="phone"
                            value="${c.phone}"
                            /></h3>

                    <h3 class="price">Giới tính: 
                        <select class="price" name="gender" >
                            <option value="Nam">Nam</option>
                            <option value="Nữ">Nữ</option>  
                        </select>        
                        <!-- Button Add -->
                        <br /><br />

                        <button class="btn" type="submit">Chỉnh sửa thông tin</button>
                </form>
            </div>

            <center>
                <h1 style="color: red">${requestScope.error}</h1>
            </center>

        </c:if>
        
        
        <c:if test="${type==3}">
                        <div class="product-detail">
                <!-- Picture -->
                <div class="col-sm-5">
                    <img
                        class="img-product"
                        src="./images/use.png"
                        alt="user"
                        />
                </div>
                <div class="col-sm-1"></div>
                <!-- Information -->
                <form method="post" action="usercreate" class="product-information col-sm-6" >
                    <!-- Header -->
                    <br />  <br />
                    <h1>Thông tin người dùng</h1>
                    <!-- Description -->

                    <h3 class="price">Tên người dùng: <input
                            class="price"
                            type="text"
                            style="height: 30px"
                            name="full_name"
                            /></h3>

                    <h3 class="price">Ngày sinh: <input
                            class="price"
                            type="date"
                            style="height: 30px"
                            name="dob"
                            /></h3>

                    <h3 class="price">Địa chỉ: <input
                            class="price"
                            type="text"
                            style="height: 30px"
                            name="address"
                            /></h3>

                    <h3 class="price">Điện thoại: <input
                            class="price"
                            type=""
                            style="height: 30px"
                            name="phone"
                            /></h3>

                    <h3 class="price">Giới tính: 
                        <select class="price" name="gender" >
                            <option value="Nam">Nam</option>
                            <option value="Nữ">Nữ</option>  
                        </select>        
                        <!-- Button Add -->
                        <br /><br />

                        <button class="btn" type="submit">Tạo thông tin</button>
                </form>
            </div>

            <center>
                <h1 style="color: red">${requestScope.error}</h1>
            </center>
        </c:if>


        <!-- Footer -->
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
                        <a href="home">Trang chủ</a>
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
        >
    </script>
</body>
</html>
