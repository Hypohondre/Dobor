<#ftl encoding="utf-8">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>login</title>
</head>
<body>

<style>
    body {
        background-color: grey;
    }

    .wrapper {
        margin: auto;
        border: 3px solid black;
        background-color: grey;
    }

    .error {
        color: red;
    }
</style>

<#if message?has_content>
    <p class="error">${message}</p>
</#if>

<div class="wrapper">
    <form method="post" action="/login">
        <label>login input</label>
        <input type="text" name="mail" placeholder="email"/>
        <input type="password" name="password" placeholder="password"/>
        <label>remember me</label>
        <input type="checkbox" name="remember">
        <input type="submit">submit</input>
    </form>
</div>

</body>
</html>