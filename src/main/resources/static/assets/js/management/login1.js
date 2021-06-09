layui.use('form', function () {
    var form = layui.form;

    form.on('submit(submit)', function () {
        var username = document.getElementById("username").value;
        var password = document.getElementById("password").value;
        var rememberMe = document.getElementById("rememberMe").checked;
        password = md5(password)
        var data = {
            "username" : username,
            "password" : password,
            "rememberMe" : rememberMe
        }
        common.ajax("/login", data, function (resp) {
                if (resp.code === common.status.ok) {
                    layer.msg("登录成功！");
                    setTimeout(function () {
                        location.href = common.url.manage_index;
                    }, 1000);
                } else {
                    layer.msg(resp.message);
                }
            }
        );
        return false;
    });

});