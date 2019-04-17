<html>
<head>
    <title>Участники</title>

    <title>Title</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
</head>
<body>



<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a href="#" class="navbar-brand">CoWorkPal</a>
        </div>

        <div>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/tasks">Задачи</a></li>
                <li><a href="/members">Участники</a></li>
                <li><a href="/admins">Администраторы</a></li>
                <li><a href="/usersProjects">Мои проекты</a></li>
            </ul>
        </div>

    </div>
</nav>
<div class="jumbotron">
    <div class="container">
        <h3>Участники проекта</h3>
    </div>

    <div class="container">
        <a href="/addMember" class="btn btn-primary" role="button">Добавить</a>
    </div>
</div>


<div class="container-fluid col-md-3" id="table">
    <table class="table">
        <thead>
        <tr>
            <th>Участники</th>
        </tr>
        </thead>
        <tbody>
        <#list members as member>
            <tr>
                <td>${member.name}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>

</body>
</html>
