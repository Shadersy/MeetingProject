<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset=utf-8">
    <title>USERS</title>
    <link rel="stylesheet" href="${rc.getContextPath()}/resources/dist/multi.min.css">
    <link rel="stylesheet" href="${rc.getContextPath()}/resources/dist/holiday.css">
    <script src="${rc.getContextPath()}/resources/dist/multi.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.."></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/p.."></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap..."></script>

</head>
<body>

<div style="margin-left: 1150px" >
WELCOME, <#list authIdUser as id>
    ${id.getUsername()}
</#list>
</div>
    <form method="post"  action="${rc.getContextPath()}/deleteMeetup">
        <div style="margin-left: 550px">
        <input  type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>

        <select name="deleteSelect">
            <#list meetupList as meetupList>
                <option value=${meetupList.meetUpId}>${meetupList.time}</option>
            </#list>
        </select>
        <button type="submit">Remove</button>
        </div>
    </form>

<br>
    <form method="post" action="${rc.getContextPath()}/createMeetup">
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>

        <input type="datetime-local" name="dateMeetUp" placeholder="Choose the date" ></input>
            <div class="container">
                <select
                required
                multiple="multiple"
                name="users"
                id="selectUsers"
                >
                    <br>
                    <#list userList as user>
                        <option value="${user.id}">${user.username}</option>
                    </#list>
                </select>
            </div>

            <div style="margin-left: 620px">
                <script>
                var select = document.getElementById("selectUsers");
                multi(select, {
                enable_search: false
                });
                </script>
            </div>
        <br>
            <button type="submit" style="margin-left: 650px" class="btn btn-dark">Submit</button>
    </form>

       <dl class="holiday">
            <#list MapResult as key, value>
                   <#list value as users>
                          <dt>${key}</dt>
                          <dd>${users.getUsername()}</dd>
                   </#list>
            </#list>
</body>
</html>
