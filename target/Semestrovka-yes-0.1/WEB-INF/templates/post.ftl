<#ftl encoding="utf-8">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>post</title>
</head>
    <body>
        <a href="/profile">profile</a>
        <a href="/list">list</a>
        <a href="/create-post">create post</a>
        <hr>
        ${name}
    <hr>
        ${text}
    <hr>
        ${category} | ${creator}
    <hr>
    <a href="/edit-post?id=${id}">edit</a>
    </body>
</html>