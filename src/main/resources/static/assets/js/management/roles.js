layui.use(['table', 'element'], function () {
    var table = layui.table
        , element = layui.element
    element.render();

    table.render({
        elem: '#role-table'
        , url: common.url.prefix + '/role/list'
        , page: true
        , limit: 10
        , height: 'full'
        , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        , cols: [[
            {field: 'roleId', width: 150, title: 'ID', sort: true}
            , {field: 'roleCode', title: '角色标识', edit: 'text', sort: true}
            , {field: 'roleName', title: '角色名称', edit: 'text', sort: true}
            , {field: 'remark', title: '角色简介', edit: 'text', sort: true}
            , {fixed: 'right', title: '操作', width: 178, align: 'center', toolbar: '#roleTableBar'}
        ]]
    });

    //监听单元格编辑
    table.on('edit(role)', function (obj) {
        var value = obj.value;
        var data = obj.data;
        obj.data.createTime = new Date(obj.data.createTime);
        obj.data.updateTime = new Date(obj.data.updateTime);
        common.ajax(common.url.prefix + "/role/edit", obj.data, function (json) {
            if (json.code === common.status.ok) {
                layer.msg('修改成功！<br/>' + '[ID: ' + data.id + '] 行字段更改为：' + value)
            } else {
                layer.msg("修改出错，错误信息：" + json.message);
            }
        })
    });

    //监听工具条
    table.on('tool(role)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('真的删除么?', function (index) {
                common.ajax(common.url.prefix + "/role/delete", {id: data.roleId}, function (json) {
                    common.okMsgHandle(json, "删除成功");
                    if (json.code === common.status.ok) obj.del();
                    layer.close(index);
                })
            });
        }
        if(obj.event === 'menu'){
            layer.open({
                type: 2,
                title: '权限菜单',
                area: ['350px', '500px'],
                maxmin: true,
                shadeClose: true, //点击遮罩关闭层
                content: '/management/role/tree/' + data.roleId
            });
        }
    });



    $(".layui-btn[data-type=addRole]").on("click", function () {
        var index = layer.confirm($("#addRolePage").html(), {
            title: '新增角色'
            , type: 1
            , area: '480px'
        }, function () {
            common.ajax(common.url.prefix + "/role/add", {
                roleCode: $("input.layui-input[name=roleCode]").val()
                , roleName: $("input.layui-input[name=roleName]").val()
                , remark: $("input.layui-input[name=remark]").val()
            }, function (json) {
                common.okHandle(json, index, "cate-table");
                window.parent.location.reload();
            })
        });
    });

});







