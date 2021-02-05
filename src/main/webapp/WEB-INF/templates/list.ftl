<#ftl encoding="utf-8">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>list of posts</title>
    <style>
        .post {
            height: 300px;
            width: 600px;
            margin: auto;
            border: 2px solid black;
        }


    </style>
</head>
    <body>

<#list posts as post>
        <div class="post">
            ${post.name}
            <hr>
            ${post.text}
            <hr>
            ${post.category}
            <hr>
            ${post.creator}
            <hr>
            <a href="/post?id=${post.id}">full</a>
        </div>
</#list>

    </body>
</html>