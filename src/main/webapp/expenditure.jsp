<%--
  Created by IntelliJ IDEA.
  User: Dai
  Date: 31/08/2021
  Time: 4:32 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="UTF-8">
    <link rel="stylesheet" href="/style.css">
</head>
<body>

<header class="row">
    <h1 style="float: left" class="col-8">LogoPage</h1>
    <button type="button" class="col-2" style="margin-top: 2%">Login</button>
    <button type="button" class="col-2" style="margin-top: 2%">Sign</button>
    <p>${message}</p>
</header>

<section>
    <div class="row">
        <button type="button" class="col-6 col-m-6">Thu</button>
        <button type="button" class="col-6 col-m-6">Chi</button>
    </div>

    <div id="menu" class="row">
        <ul class="col-12">
            <li><a href="#">Chi Tiết</a>
                <ul>
                    <li><a href="#">Theo ngày</a></li>
                    <li><a href="#">Theo tuần</a></li>
                    <li><a href="#">Theo tháng</a></li>
                </ul>
            </li>
            <li><a href="#">Hạn Mức</a>
                <ul>
                    <li><a href="#">Theo ngày</a></li>
                    <li><a href="#">Theo tháng</a></li>
                </ul>
            </li>
        </ul>
    </div>

    <div class="row">
        <div class="col-12">
            <div class="col-6">
                <button type="button" class="col-6">Thêm khoản thu</button>
            </div>
            <div class="col-6">
                <button type="button" class="col-6">Tìm kiếm theo mức thu</button>
            </div>
        </div>
    </div>
    <div>
        Bảng dữ liệu
    </div>
    <div class="row">
        <div class="col-12">
            <p>Biểu đồ</p>
        </div>
    </div>
</section>

<footer class="footer">
    <p>Liên hệ: 18008198.</p>
    <i style="font-size: 100px;color: #cc3333" class="bi bi-cloudy-fill"></i>
</footer>

</body>
</html>
