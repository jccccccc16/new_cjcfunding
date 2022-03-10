// 分页函数
function generatePage(){
    var pageInfo=getPageInfoRemote();
    fillTableBody(pageInfo);
}

// 获取pageInfo函数
function getPageInfoRemote(){

    var resultEntity=null;

    // 使用ajax调用后台获取role的pageInfo
    var ajaxResult = $.ajax({
        "url":"admin/get/page.json",
        "type":"post",
        "data":{
            "pageNum":window.pageNum,
            "pageSize":window.pageSize,
            "keyword":window.keyword
        },
        "dataType":"json",
        "async":false,
    });

    var status=ajaxResult.status;
    if(status!=200){
        layer.msg("请求失败，状态码："+status);
        return null;
    }

    resultEntity=ajaxResult.responseJSON;

    // 判断result是否成功
    if(resultEntity.message=="FAILED"){
        layer.msg(resultEntity.message);
        return null;
    }

    // 确认成功后 返回 pageInfo
    var pageInfo = resultEntity.data;
    return pageInfo;
}


// 填充表格函数
function fillTableBody(pageInfo){

    // 判断数据是否有效

    if(pageInfo==null||pageInfo==undefined || pageInfo.list==null || pageInfo.list.length==0){
        $("#roleTableBody").append("<tr><td colspan='4'>抱歉！没有查询到数据</td></tr>")
    }

    // 先清除页面旧数据
    $("#roleTableBody").empty();

    // 填充表格
    for(var i=0;i<pageInfo.list.length;i++){

        var role = pageInfo.list[i];
        var roleId=role.id;
        var roleName=role.name;

        var numberTd = "<td>"+roleId+"</td>";
        var roleNameTd="<td>"+roleName+"</td>";
        var checkBoxTd="<td><input type='checkbox'></td>";
        var checkButton= "<button type='button' class='btn btn-success btn-xs'><i class='glyphicon glyphicon-check'></i></button>"
        var pencilButton="<button type='button' class='btn btn-primary btn-xs'><i class='glyphicon glyphicon-pencil'></i></button>"
        var removeButton="<button type='button' class='btn btn-danger btn-xs'><i class='glyphicon glyphicon-remove'></i></button>"

        // 按钮td
        var buttonTd="<td>"+checkButton+pencilButton+removeButton+"</td>";

        // role数据和按钮组合成一个tr
        var tr  ="<tr>"+numberTd+checkBoxTd+roleNameTd+buttonTd+"</tr>";

        // 获取tableBody 元素，往元素中增加tr
        $("#roleTableBody").append(tr);

    }

    generateNavigator(pageInfo);




}

// 分页导航栏
function generateNavigator(pageInfo){
    var totalRecord =pageInfo.total;

    // 生成导航条需要设置的属性
    var properties = {
        num_edge_entries: 3,// 边缘页数
        num_display_entries: 4, // 主体页数
        callback: paginationCallBack, // 用户点击页面进行翻页时的回调函数，重新初始化？
        items_per_page:pageInfo.pageSize,
        current_page: pageInfo.pageNum-1, // pagePagination Cong ling kai shi
        prev_text: "上一页",
        next_text: "下一页",
    };


    // 生成页码导航条
    $("#Pagination").pagination(totalRecord, properties);

}

// 给pagigator用的回调函数
function paginationCallBack(pageIndex,JQuery){
    window.pageNum = pageIndex+1;
    generatePage();
    return false;

}