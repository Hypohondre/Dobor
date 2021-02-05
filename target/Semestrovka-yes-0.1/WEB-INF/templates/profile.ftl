<#ftl encoding="utf-8">
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>profile</title>
    <style>
        img {
            height: 150px;
            width: 100px;
        }
    </style>
</head>
<body>
<a href="/create-post">create post</a>
<a href="/list">posts</a>
<img src="${user.photo}" alt="no image">
    <h1>${user.username}</h1>
    <h3>${user.birthdate}</h3>
    <h3>${user.mail}</h3>
<a href="/edit-profile">edit</a>
</body>
</html>