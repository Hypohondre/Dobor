<#ftl encoding="utf-8">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>edit-profile</title>
</head>
    <body>
        <form method="post" action="/edit-profile" enctype="multipart/form-data">
            <input type="file" name="photo">
            <input name="username" type="text">
            <input name="password" type="text">
            <input name="birth" type="date">
            <input name="email" type="text">
            <input type="submit">
        </form>
    </body>
</html>