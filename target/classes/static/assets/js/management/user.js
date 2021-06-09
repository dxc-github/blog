layui.use(['form', 'table', 'element'], function () {
    var table = layui.table
        , element = layui.element
        , form = layui.form;
    element.render();

    var userTable = table.render({
        elem: '#user-table'
        , url: common.url.prefix + '/user/list'
        , page: true
        , limit: 10
        , height: 'full'
        , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        , cols: [[
            {field: 'id', width: 80, title: 'ID', sort: true}
            ,{field: 'roleName',title: '角色'}
            , {
                field: 'username', title: '账号', templet: function (d) {
                    return d.username === '' || d.username === null ? 'QQ第三方注册' : d.username;
                }
            }
            ,{field: 'nickname',title: '昵称'}
            , {
                field: 'password', title: '密码', templet: function (d) {
                    return d.password === '' || d.password === null ? 'QQ第三方注册' : d.password;
                }
            }
            , {
                field: 'createTime', title: '创建日期', sort: true, templet: function (d) {
                    return common.dateFormatter(d.createTime);
                }
            }
            , {title: '状态', width: 100, align: 'center', toolbar: '#enableTpl'}
            , {title: '操作', width: 100, align: 'center', toolbar: '#userBar'}
        ]]
    });


    form.on('switch(enable)', function (obj) {
        common.ajax(common.url.prefix + "/user/edit/enable", {id: this.value, enable: obj.elem.checked}, function (json) {
            common.okMsgHandle(json);
            layer.tips('用户状态：' + ((obj.elem.checked) ? "正常" : "锁定"), obj.othis);
        });
    });

    table.on('sort(user)', function (obj) {
        userTable.reload({
            initSort: obj
            , where: {
                sort: obj.field
                , order: obj.type
            }
        });
    });

    //监听工具条
    table.on('tool(user)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('真的删除么?', function (index) {
                common.ajax(common.url.prefix + "/user/delete", {id: data.id}, function (json) {
                    common.okMsgHandle(json, "删除成功");
                    if (json.code === common.status.ok) obj.del();
                    layer.close(index);
                })
            });
        }
    });

    var $ = layui.$, active = {
            reload: function () {
                var nickname = $('#user-search');
                //执行重载
                table.reload('user-table', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    , where: {
                        nickname: nickname.val()
                    }
                });
            },
            insert: function () {
                layer.open({
                    type: 2,
                    title: '新增用户',
                    area: ['455px', '500px'],
                    maxmin: true,
                    shadeClose: true, //点击遮罩关闭层
                    content: '/management/user/toadd'
                });
            }
        };

        $('#user-table-search').find('.layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

});







