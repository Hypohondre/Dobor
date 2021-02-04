<#ftl encoding="utf-8">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>profile</title>
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
<#--    <#list names as name>-->
<#--    <#list texts as text>-->
<#--    <#list categories as category>-->
<#--    <#list creators as creator>-->
<#list posts as post>
        <div class="post">
            ${post.name}
            <hr>
            ${post.text}
            <hr>
            ${post.category}
            <hr>
            ${post.creator}
        </div>
</#list>
<#--    </#list>-->
<#--    </#list>-->
<#--    </#list>-->
<#--    </#list>-->
    </body>
</html>