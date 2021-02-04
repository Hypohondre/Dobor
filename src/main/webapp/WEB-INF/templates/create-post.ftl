<#ftl encoding="utf-8">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>create-post</title>
</head>
<body>
<a href="/profile">profile</a>
<a href="/list">posts</a>
<form method="post" action="/create-post">
    <input name="name" type="text">
    <input name="text" type="text">
    <select name="category">
        <#list categories as category>
        <option>${category}</option>
            <#else>
        <option>No category</option>
            </#list>
    </select>
    <input type="submit" value="submit">
</form>
</body>
</html>