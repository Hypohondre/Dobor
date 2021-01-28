<#ftl encoding="utf-8">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>registration</title>
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
        width: 600px;
        height: 600px;
    }

    .error {
        color: red;
    }
</style>

    <#if errorMessage??>
        <ul class="error">
            <#list errorMessage as item>
                <li>${item}</li>
            </#list>
        </ul>
    </#if>

    <div class="wrapper">
    <form method="post" action="/registration">
        <label>login</label>
        <input type="text" name="username">
        <label>password</label>
        <input type="password" name="password">
        <label>email</label>
        <input type="text" name="email">
        <label>birth</label>
        <input type="date" name="birth">
        <input type="submit">Подтвердить</input>
    </form>
    </div>
</body>
</html>