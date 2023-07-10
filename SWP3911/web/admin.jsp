<%-- 
    Document   : home
    Created on : Jun 4, 2023, 7:31:47 PM
    Author     : Trịnh Minh Tân
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Foodshop</title>
        <link rel="stylesheet" href="./css/homepage.css" />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
            />
        <style>
            table, th, td {
                border:1px solid black;
            }
        </style>
    </head>

    <body>
        <div id="wrapper">
            <div id="header">
                <div id="header-left">
                    <a href="" class="logo">
                        <img class="img-logo" src="./images/Foodshop-removebg.png" alt="" />
                    </a>
                    <div id="menu">
                        <div class="item">
                            <a class="item-menu-content" href="home">Trang chủ</a>
                        </div>
                        <div class="item">
                            <a class="item-menu-content" href="">Sản phẩm</a>
                        </div>
                        <div class="item">
                            <a class="item-menu-content" href="">Blog</a>
                        </div>
                        <div class="item">
                            <a class="item-menu-content" href="">Liên hệ</a>
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


            <c:set var="type" value="${requestScope.type}"/>
            <c:set var="account1" value="${sessionScope.delivery}"/>
            <c:set var="account2" value="${sessionScope.manager}"/>

            <c:if test="${type==2}">

                <form action="manageralldone?id=${account2.id}" method="post">
                    <input type="submit" value="AllOk">
                </form> &nbsp;
                <h2 style="font-size: 30px">&nbsp; Đơn chờ phê duyệt:</h2>     
                <table style="width:98%; border:5px solid black; border-radius: 10px; background-color: greenyellow; font-size: 20px; margin: 10px; color: #e67e22">
                    <tr>
                        <th>Name</th>
                        <th>Time</th>
                        <th>Quantity</th>
                        <th>Name food</th>
                        <th>Status</th>
                        <th>Select</th>
                    </tr>  
                    <c:forEach items="${requestScope.managerdone}" var="c">
                        <tr>
                            <td>${c.full_name}</td>
                            <td>${c.timegiao}</td>
                            <td>${c.quantity}</td>
                            <td>${c.name_food}</td>
                            <td>${c.status_order}</td>
                            <td style="display: flex"> &nbsp;
                                <form action="managerdone?id=${c.id}" method="post">
                                    <input type="submit" value="OK">
                                </form> &nbsp;
                                <form action="managernull?id=${c.id}" method="post">
                                    <input type="submit" value="Cancel">
                                </form> &nbsp;
                                <form action="managermoney?id=${c.price}" method="post">
                                    <input type="submit" value="AllComplete">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <h2 style="font-size: 30px">&nbsp; Chờ thanh toán:</h2>     
                <table style="width:98%; border:5px solid black; border-radius: 10px; background-color: greenyellow; font-size: 20px; margin: 10px; color: #e67e22">
                    <tr>
                        <th>Name</th>
                        <th>Time</th>
                        <th>Quantity</th>
                        <th>Name food</th>
                        <th>Status</th>
                        <th>Select</th>
                    </tr>  
                    <c:forEach items="${requestScope.managerok}" var="c">
                        <tr>
                            <td>${c.full_name}</td>
                            <td>${c.timegiao}</td>
                            <td>${c.quantity}</td>
                            <td>${c.name_food}</td>
                            <td>${c.status_order}</td>
                            <td style="display: flex"> &nbsp;
                                <form action="managerdone?id=${c.id}" method="post">
                                    <input type="submit" value="OK">
                                </form> &nbsp;
                                <form action="managernull?id=${c.id}" method="post">
                                    <input type="submit" value="Cancel">
                                </form> &nbsp;
                                <form action="managermoney?id=${c.price}" method="post">
                                    <input type="submit" value="AllComplete">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>


            <c:if test="${type==3}">
                <h2 style="font-size: 30px">&nbsp; Đơn hàng chưa xử lý:</h2> 
                <table style="width:98%; border:5px solid black; border-radius: 10px; background-color: greenyellow; font-size: 20px; margin: 10px; color: #e67e22">
                    <tr>
                        <th>Name</th>
                        <th>Address</th>
                        <th>Phone</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Name food</th>
                        <th>Select</th>
                    </tr>  
                    <c:forEach items="${requestScope.notdone}" var="c">
                        <tr>
                            <td>${c.full_name}</td>
                            <td>${c.address}</td>
                            <td>${c.phone}</td>
                            <td>${c.quantity}</td>
                            <td>${c.price}</td>
                            <td>${c.name_food}</td>
                            <td style="display: flex"> &nbsp;
                                <form action="deliverydone?id=${c.id}" method="post">
                                    <input type="submit" value="Done">
                                </form> &nbsp;
                                <form action="deliverycancel?id=${c.id}" method="post">
                                    <input type="submit" value="Cancel">
                                </form> &nbsp;
                                <form action="delivering?id=${c.id}" method="post">
                                    <input type="submit" value="Delivering">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>

                <h2 style="font-size: 30px">&nbsp; Đơn hàng đang giao:</h2>
                <table style="width:98%; border:5px solid black; border-radius: 10px; background-color: greenyellow; font-size: 20px; margin: 10px; color: #e67e22">
                    <tr>
                        <th>Name</th>
                        <th>Address</th>
                        <th>Phone</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Name food</th>
                        <th>Select</th>
                    </tr>  
                    <c:forEach items="${requestScope.deliver}" var="c">
                        <tr>
                            <td>${c.full_name}</td>
                            <td>${c.address}</td>
                            <td>${c.phone}</td>
                            <td>${c.quantity}</td>
                            <td>${c.price}</td>
                            <td>${c.name_food}</td>
                            <td style="display: flex"> &nbsp;
                                <form action="deliverydone?id=${c.id}" method="post">
                                    <input type="submit" value="Done">
                                </form> &nbsp;
                                <form action="deliverycancel?id=${c.id}" method="post">
                                    <input type="submit" value="Cancel">
                                </form> &nbsp;
                                <form action="delivering?id=${c.id}" method="post">
                                    <input type="submit" value="Delivering">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>

                <h2 style="font-size: 30px">&nbsp; Đơn hàng đã xử lý:</h2>
                <table style="width:98%; border:5px solid black; border-radius: 10px; background-color: greenyellow; font-size: 20px; margin: 10px; color: #e67e22">
                    <tr>
                        <th>Name</th>
                        <th>Address</th>
                        <th>Phone</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Name food</th>
                        <th>Time</th>
                        <th>Status</th>
                        <th>Select</th>
                    </tr>  
                    <c:forEach items="${requestScope.done}" var="c">
                        <tr>
                            <td>${c.full_name}</td>
                            <td>${c.address}</td>
                            <td>${c.phone}</td>
                            <td>${c.quantity}</td>
                            <td>${c.price}</td>     
                            <td>${c.name_food}</td>
                            <td>${c.timegiao}</td>
                            <td>${c.status_order}</td>
                            <td style="display: flex"> &nbsp;
                                <form action="deliverydone?id=${c.id}" method="post">
                                    <input type="submit" value="Done">
                                </form> &nbsp;
                                <form action="deliverycancel?id=${c.id}" method="post">
                                    <input type="submit" value="Cancel">
                                </form> &nbsp;
                                <form action="delivering?id=${c.id}" method="post">
                                    <input type="submit" value="Delivering">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>


            <div id="footer">
                <div class="box">
                    <div class="logo">
                        <img class="footer-logo" src="./images/Foodshop-removebg.png" alt="" />
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
                        <input type="text" placeholder="Địa chỉ email"/>
                        <button>Nhận tin</button>
                    </form>
                </div>
            </div>
        </div>
        <script src="./js/script.js"></script>
    </body>
</html>
