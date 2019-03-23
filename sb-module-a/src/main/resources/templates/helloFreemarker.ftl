<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>First Thymeleaf Html</title>
</head>
<body>
<h3>${message}</h3>
<form action="/qrCodeTracking/importQrExcelFile" method="post" enctype="multipart/form-data">
    <input type="file" name="file">
    <input type="submit" value="上传">
</form>
</body>
</html>