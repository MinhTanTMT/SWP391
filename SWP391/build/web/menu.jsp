


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>
    <head>
        <title>Foodshop</title>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" href="./css/styles.css" />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
            />
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
    </head>
    <body>
        <section id="header">
            <div id="header-left">
                <!-- img logo - redirect to homepage -->
                <img
                    style="margin-right: 10px"
                    src="./images/png-transparent-wolf-logo-symbol.png"
                    height="25"
                    alt="logo"
                    />
                <!-- List Menu Navbar -->
                <div>
                    <ul id="navbar">
                        <li>
                            <a href="foods.html">Đồ ăn</a>
                        </li>
                        <li>
                            <a href="drinks.html">Đồ uống</a>
                        </li>
                        <li>
                            <a href="snacks.html">Đồ ăn vặt</a>
                        </li>
                        <li>
                            <a href="fruits.html">Trái cây</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div id="header-right">
                <!-- Icon Searching -->
                <i class="fa-solid fa-magnifying-glass"></i>
                <!-- icon cart -->
                <i class="fa-solid fa-cart-shopping"></i>
                <!-- Icon account -->
                <i class="fa-solid fa-user"></i>
            </div>
        </section>

        <!-- Main Content -->
        <div id="mainer">
            <div class="foods-menu">
                <h1>MENU</h1>

                <div class="menu-list-group row">
                    <div class="items-1 col-md-1" style="border: 1px solid black">
                        Empty
                    </div>

                    <div
                        class="items-2 col-md-10 col-sm-12"
                        style="border: 1px solid black"
                        >
                        <!-- Phana loai/ Filter -->
                        <div class="filter">Phân Loại</div>
                        <!-- Menu Cards -->
                        <div class="menu-list">
                            <c:forEach items="${requestScope.foodsale}" var="c">
                                <!-- Card 1-->
                                <div class="card" style="width: 12rem">
                                    <img
                                        src="${c.img}"
                                        class="card-img-top"
                                        alt="anime"
                                        />
                                    <div class="card-body">
                                        <p class="card-text">${c.name_food}</p>
                                        <a href="fooddetail.html"><button class="btn btn-primary">Xem chi tiết</button></a>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>

                        <div class="menu-list">                                                        
                            <c:forEach items="${requestScope.foodcheap}" var="c">
                                <!-- Card 1-->
                                <div class="card" style="width: 12rem">
                                    <img
                                        src="${c.img}"
                                        class="card-img-top"
                                        alt="anime"
                                        />
                                    <div class="card-body">
                                        <p class="card-text">${c.name_food}</p>
                                        <a href="fooddetail.html"><button class="btn btn-primary">Xem chi tiết</button></a>
                                    </div>
                                </div>
                            </c:forEach>   
                        </div>
                    </div>

                    <div class="items-1 col-md-1" style="border: 1px solid black">
                        Items 3
                    </div>
                </div>
            </div>
        </div>
        <!-- Footer -->
        <footer>
            <div class="row">
                <div class="col-md-1"></div>
                <div class="footer-left col-md-5 d-flex align-items-center">
                    <!-- LOGO -->
                    <img src="./images/png-transparent-wolf-logo-symbol.png" height="45" alt="">
                    <!-- Address -->
                    <p>Address: Ha dong, Ha Noi</p>
                    <p>Phone: 0964766165</p>

                </div>
                <div class="footer-right col-md-5">
                    <!-- Social Media -->
                    <i class="fa-brands fa-facebook"></i>
                    <i class="fa-brands fa-instagram"></i>
                    <i class="fa-brands fa-shopify"></i>
                </div>
                <div class="col-md-1">

                </div>
            </div>
        </footer>
        <script src="./js/script.js"></script>
        <!-- MDB -->
        <script
            type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.3.1/mdb.min.js"
        ></script>
    </body>
</html>
