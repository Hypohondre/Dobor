<#ftl encoding="utf-8">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>edit-post</title>
</head>
<body>
<a href="/list">list</a>
<a href="/profile">profile</a>
<form method="post" action="/edit-post?id=${id}">
    <input name="name" type="text" value="${name}">
    <input name="text" type="text" value="${text}">
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