layui.use(['form', 'table', 'element'], function () {
    var table = layui.table
        , element = layui.element
        , form = layui.form;
    element.render();

    var userTable = table.render({
        elem: '#menu-table'
        , url: common.url.prefix + '/menu/list'
        , page: true
        , limit: 10
        , height: 'full'
        , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        , cols: [[
            {field: 'menuId', width: 80, title: 'ID', sort: true}
            ,{field: 'parentId',title: '父菜单',edit: 'text'}
            ,{field: 'name', title: '菜单名称',edit: 'text'}
            ,{field: 'url',title: '菜单链接',edit: 'text'}
            ,{field: 'perms',title: '授权标识',edit: 'text'}
            ,{field: 'type',title: '菜单类型',edit: 'text'}
            , {
                field: 'createTime', title: '创建日期', sort: true, templet: function (d) {
                    return common.dateFormatter(d.createTime);
                }
            }
            , {title: '操作', width: 100, align: 'center', toolbar: '#menuBar'}
        ]]
    });

    table.on('sort(menu)', function (obj) {
        userTable.reload({
            initSort: obj
            , where: {
                sort: obj.field
                , order: obj.type
            }
        });
    });

    //监听单元格编辑
    table.on('edit(menu)', function (obj) {
        var value = obj.value;
        var data = obj.data;
        obj.data.createTime = new Date(obj.data.createTime);
        obj.data.updateTime = new Date(obj.data.updateTime);
        common.ajax(common.url.prefix + "/menu/edit", obj.data, function (json) {
            if (json.code === common.status.ok) {
                layer.msg('修改成功！<br/>' + '[ID: ' + data.id + '] 行字段更改为：' + value)
            } else {
                layer.msg("修改出错，错误信息：" + json.message);
            }
        })
    });

    //监听工具条
    table.on('tool(menu)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('真的删除么?', function (index) {
                common.ajax(common.url.prefix + "/menu/delete", {menuId: data.menuId}, function (json) {
                    common.okMsgHandle(json, "删除成功");
                    if (json.code === common.status.ok) obj.del();
                    layer.close(index);
                })
            });
        }
    });

    var $ = layui.$, active = {
        reload: function () {
            var name = $('#menu-search');
            //执行重载
            table.reload('menu-table', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    name: name.val()
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
                content: '/management/menu/toadd'
            });
        }
    };

    $('#menu-table-search').find('.layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

});







