
$(function () {
    //基本的表格/按钮和下拉框的加载
    initTable();
    initButton();
    initSelect();
});

//关闭弹出的模态框时,清空form表单数据
$('#addModal').on('hide.bs.modal', function(){
    $('#addForm')[0].reset();
    $("#addRoleId").selectpicker('refresh');//清空boostrap-select选中
    $(this).removeData("bs.modal");

});
$('#editModal').on('hide.bs.modal', function(){
    $('#editForm')[0].reset();
    $("#editRoleId").selectpicker('refresh');//清空boostrap-select选中
    $(this).removeData("bs.modal");

});
$('#delModal').on('hide.bs.modal', function(){
    $(this).removeData("bs.modal");

});
//show.bs.modal	在调用 show 方法后触发。
//设置模态框的水平垂直方向的位置；
$('#addModal,#editModal,#delModal').on('show.bs.modal',centerModals);

//禁用空白处点击关闭
$('#addModal,#editModal,#delModal').modal({
    backdrop: 'static',
    keyboard: false,//禁止键盘
    show:false
});

$('#btn_reset').click(function(){
    $('#formSearch')[0].reset();
    $("#txt_rolename").selectpicker('refresh');//清空boostrap-select选中
});

//新增模态框点击确定按钮,提交表单数据
$('#addBtn').click(function(){
    //提交前验证
    if(!addValid()){
        return false;
    }
    var data = $('#addForm').serializeJSON();
    $.ajax({
        url:'register.do',
        type:'POST',
        data:JSON.stringify(data),
        dataType:'json',
        contentType: "application/json",
        cache:false,//不缓存
        async:true,//异步
        beforeSend:function(){
            $("#addBtn").attr({ disabled: "disabled" });//避免脏数据
        },
        success:function(result){
            //请求成功时
            if(result.status==0){
                $('#addModal').modal('hide');
                alert("添加成功！");
                //刷新table数据
                $('#tb_user').bootstrapTable('refresh');
            }else{
                alert(result.msg);
            }
        },
        complete:function(){
            $("#addBtn").removeAttr("disabled");//避免脏数据
        },
        error:function(){
            //请求失败时
            alert("程序出错,需看后台！");
        }
    });
});

//修改模态框点击确定按钮,提交表单数据
$('#editBtn').click(function(){
    //提交前验证
    if(!editValid()){
        return false;
    }
    var data = $('#editForm').serializeJSON();
    $.ajax({
        url:'updateUser.do',
        type:'POST',
        data:JSON.stringify(data),
        dataType:'json',
        contentType: "application/json",
        cache:false,//不缓存
        async:true,//异步
        beforeSend:function(){
            $("#editBtn").attr({ disabled: "disabled" });//避免脏数据
        },
        success:function(result){
            //请求成功时
            if(result.status==0){
                $('#editModal').modal('hide');
                alert("修改成功！");
                //刷新table数据
                $('#tb_user').bootstrapTable('refresh');
            }else{
                alert(result.msg);
            }
        },
        complete:function(){
            $("#editBtn").removeAttr("disabled");//避免脏数据
        },
        error:function(){
            //请求失败时
            alert("程序出错,需看后台！");
        }
    });
});

//删除模态框点击确定按钮,提交表单数据
$('#delBtn').click(function(){
    var submitData = 'id='+$('#delId').val()+'&rd='+Math.random();
    $.ajax({
        url:'deleteUser.do',
        data:submitData,
        method: 'post',
        dataType:'json',
        cache:false,
        async:true,
        beforeSend:function(){
            $("#delBtn").attr({ disabled: "disabled" });//避免脏数据
        },
        success:function(result){
            //请求成功时
            if(result.status==0){
                $('#delModal').modal('hide');
                alert("删除成功！");
                //刷新table数据
                $('#tb_user').bootstrapTable('refresh');
            }else{
                alert("删除失败！");
            }
        },
        complete:function(){
            $("#delBtn").removeAttr("disabled");//避免脏数据
        },
        error:function(){
            //请求失败时
            alert("程序出错,需看后台！");
        }
    });
});



function initTable() {
    $('#tb_user').bootstrapTable('destroy');
    $('#tb_user').bootstrapTable({
        url: 'queryPage.do',                //请求后台的URL（*）
        method: 'get',                      //请求方式（*）
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: false,                    //是否启用排序
        sortOrder: "asc",                   //排序方式
        queryParams: queryParams,           //传递参数（*）
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber:1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        showColumns: true,                  //是否显示所有的列
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        height: 400,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
        showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                  //是否显示父子表
        columns: [{                         //隐藏列visible
            field: 'id',
            title: 'id',
            align: 'center',
            visible: false
        }, {
            field: 'username',
            title: '用户名',
            align: 'center'
        }, {
            field: 'password',
            title: '密码',
            align: 'center'
        }, {
            field: 'stsName',
            title: '状态',
            align: 'center'
        }, {
            field: 'registeTime',
            title: '注册时间',
            align: 'center'
        }, {
            field: 'roleId',
            title: '角色',
            align: 'center',
            visible: false
        }, {
            field: 'role.roleName',
            title: '角色',
            align: 'center'
        }, {
            field: '#',
            title: '操作',
            align: 'center',
            formatter:function(value,row,index){
                return "<a href='javascript:;' onclick='openEditModal(\""+row.id+"\")'>修改</a> <a href='javascript:;' onclick='openDelModal(\""+row.id+"\")'>删除</a>";
            }
        }]
    });
};

//得到查询的参数
function queryParams(params) {
    var p_limit = params.limit;
    var p_offset = params.offset;
    if(typeof(p_limit)=="undefined"){
        p_limit = -1;
    }
    if(typeof(p_offset)=="undefined"){
        p_offset = -1;
    }
    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        limit: p_limit,   //页面大小
        offset: p_offset,  //页面大小起始值
        username: $("#txt_username").val(),
        roleId: $("#txt_rolename").val()
    };
    //alert("limit is :"+temp.limit);
    //alert("offset is :"+temp.offset);
    return temp;
};

function initButton () {
    //初始化页面上面的按钮事件
    $('#btn_query').click(function(){
        initTable();
    });
};

//加载角色下拉框
function initSelect(){
    $.ajax({
        // get请求地址
        url: "queryRoleList.do",
        dataType: "json",
        success: function (data) {
            var optArr = [];
            $('.selectpicker').append("<option value=''>请选择</option>");
            for (var i = 0; i < data.length; i++) {
                $('.selectpicker').append("<option value=" + data[i].roleId + ">" + data[i].roleName + "</option>");
            }
            // 缺一不可
            $('#txt_rolename').selectpicker('refresh');
            $('#txt_rolename').selectpicker('render');
            $('#addRoleId').selectpicker('refresh');
            $('#addRoleId').selectpicker('render');
            $('#editRoleId').selectpicker('refresh');
            $('#editRoleId').selectpicker('render');
        }
    });
    var roleNameSize = $('#txt_rolename').attr("size");
    if(roleNameSize>10){
        $('.selectpicker').css("height","300px");
        $('.selectpicker').css("overflow-y","scroll");
    }
}

//设置模态框水平垂直方向的居中
function centerModals(){
    $('#addModal,#editModal,#delModal').each(function(i){
        var $clone = $(this).clone().css('display','block').appendTo('body');
        var top = Math.round(($clone.height() - $clone.find('.modal-content').height()) / 2);
        top = top > 0 ? top : 0;
        $clone.remove();
        $(this).find('.modal-content').css("margin-top", top);
    });
};

//点击修改超链接弹出模态框页面
function openEditModal(id){
    var data = 'id='+id+'&rd='+Math.random();
    //submitData是解码后的表单数据，结果同上
    var submitData=decodeURIComponent(data,true);
    $.ajax({
        url:'queryUserBean.do',
        data:submitData,
        dataType:'json',
        cache:false,
        async:true,
        success:function(result){
            //请求成功时
            $("#editModal").modal().on("shown.bs.modal", function () {
                $('#editId').val(result.id);
                $('#editUsername').val(result.username);
                $('#editPassword').val(result.password);
                $('#editPwdRepeat').val(result.password);
                $('#editRoleId').selectpicker('val', result.roleId);
            });
            $('#editModal').modal('show');
        },
        error:function(){
            //请求失败时
            alert("程序出错,需看后台！");
        }
    });

};

//点击删除超链接弹出模态框页面
function openDelModal(id){
    $("#delModal").modal().on("shown.bs.modal", function () {
        $('#delId').val(id);
    });
    $('#delModal').modal('show');
};

//提交新增表单前验证
function addValid(){
    var reg = new RegExp(/^\w+$/);
    var addUsername = $('#addUsername').val();
    var addPassword = $('#addPassword').val();
    var addPwdRepeat = $('#addPwdRepeat').val();
    var addRoleId = $('#addRoleId').val();
    if(addUsername == null || addUsername == "" || addUsername.trim() == ""){
        alert("请输入用户名！");
        return false;
    }
    if(!reg.test(addUsername)){
        alert("请确保用户名中只有英文/数字/下划线,无其它！");
        return false;
    }
    if(addPassword == null || addPassword == ""){
        alert("请输入登录密码！");
        return false;
    }
    if(addPassword.length<8){
        alert("密码请至少输入8位！");
        return false;
    }
    if(addPwdRepeat == null || addPwdRepeat == ""){
        alert("请输入确认密码！");
        return false;
    }
    if(addPwdRepeat!=addPassword){
        alert("两次密码输入不一致！");
        return false;
    }
    if(addRoleId == null || addRoleId == ""){
        alert("请选择用户角色！");
        return false;
    }
    return true;
};

//提交更新表单前验证
function editValid(){
    var reg = new RegExp(/^\w+$/);
    var editUsername = $('#editUsername').val();
    var editPassword = $('#editPassword').val();
    var editPwdRepeat = $('#editPwdRepeat').val();
    var editRoleId = $('#editRoleId').val();
    if(editUsername == null || editUsername == "" || editUsername.trim() == ""){
        alert("请输入用户名！");
        return false;
    }
    if(!reg.test(editUsername)){
        alert("请确保用户名中只有英文/数字/下划线,无其它！");
        return false;
    }
    if(editPassword == null || editPassword == ""){
        alert("请输入密码！");
        return false;
    }
    if(editPassword.length<8){
        alert("密码请至少输入8位！");
        return false;
    }
    if(editPwdRepeat == null || editPwdRepeat == ""){
        alert("请输入确认密码！");
        return false;
    }
    if(editPwdRepeat!=editPassword){
        alert("两次密码输入不一致！");
        return false;
    }
    if(editRoleId == null || editRoleId == ""){
        alert("请选择用户角色！");
        return false;
    }
    return true;
};
