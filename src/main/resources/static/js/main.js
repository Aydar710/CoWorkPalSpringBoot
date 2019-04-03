function addUser(name, age) {
    $.ajax({
        type: "POST",
        url: "/users/json",
        // The key needs to match your method's input parameter (case-sensitive).
        data: JSON.stringify({"name": name, "age": age}),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data) {
            alert(JSON.stringify(data));
        },
        failure: function (errMsg) {
            alert(errMsg);
        }
    });
}