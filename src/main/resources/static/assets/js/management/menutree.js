layui.use(['form', 'tree'], function () {
    var form = layui.form;
    var tree = layui.tree;
    var $ = layui.$;

    //渲染
    var menutree = tree.render({
        elem: '#menutree'  //绑定元素
        ,data: JSON.parse($("#trees").val())
        ,id: 'tree1'
        ,showCheckbox: true  //是否显示复选框
        ,isJump: true //是否允许点击节点时弹出新窗口跳转
        // ,oncheck: function(obj){
        //     console.log(obj.data)
        //     // var data = obj.data;  //获取当前点击的节点数据
        //     // layer.msg('状态：'+ obj.state + '<br>节点数据：' + JSON.stringify(data));
        // }
    });


    //监听提交
    form.on('submit(menuTreeForm)', function (data) {
        var checkData = tree.getChecked('tree1');
        var nodeIds = new Array();
        nodeIds = getCheckedId(checkData);
        data = {
            roleId:$("#roleId").val(),
            nodeIds:nodeIds
        }
        common.ajax(common.url.prefix + "/menu/tree/edit", data, function (json) {
            if (json.code === common.status.ok) {
                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                parent.layer.close(index); //再执行关闭
            } else {
                layer.msg("权限编辑失败：" + json.message);
            }
        });
        return false;
    });

    //获取所有选中的节点id
    function getCheckedId(data) {
        var id = "";
        $.each(data, function (index, item) {
            if (id != "") {
                id = id + "," + item.id;
            }
            else {
                id = item.id;
            }
            //item 没有children属性
            if (item.children != null) {
                var i = getCheckedId(item.children);
                if (i != "") {
                    id = id + "," + i;
                }
            }
        });
        return id;
    }


})
