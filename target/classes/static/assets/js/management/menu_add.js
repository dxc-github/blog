layui.use(['element', 'form', 'upload'], function () {
    var form = layui.form;
    var element = layui.element;
    var upload = layui.upload;
    var $ = layui.$;
    element.render();
    form.render();

    //监听提交
    form.on('submit(menuForm)', function (data) {
        common.ajax(common.url.prefix + "/menu/save", data.field, function (json) {
            if (json.code === common.status.ok) {
                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                parent.layui.table.reload('menu-table',{page: {curr: 1}});
                parent.layer.close(index); //再执行关闭
                window.parent.location.reload();
            } else {
                layer.msg("新增菜单失败：" + json.message);
            }
        });
        return false;
    });

    // upload.render({
    //     elem: '#userImg' //绑定元素
    //     , url: common.url.prefix + '/blog/upload/cover' //上传接口
    //     , done: function (res) {
    //         if (res.code === 200) {
    //             $("#userImg").html('<p><img style="width: 144px;height: 90px;" src="' + res.data + '"></p>');
    //         }
    //         layer.msg(res.message);
    //     }
    //     , error: function () {
    //         layer.msg("上传失败！");
    //     }
    // });

});