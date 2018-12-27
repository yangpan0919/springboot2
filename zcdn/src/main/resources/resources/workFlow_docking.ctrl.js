app.controller('workFlowDockingCtrl', function ($scope, $http, validate, ajax, box, pagination, $filter, menuNav, globalParam) {
    var vm = this;
    vm.onInit = function () {
        vm.pageNo = 1;
        vm.pageSize = 15;

        vm.currentUrl = location.hash;

        vm.workFlowsQueryRequest = {
            "startTime": null,
            "endTime": null,
            "workFlowStatus": null,
            "bussinessStatus": null
        };

        vm.showRejectArchiveCondition = false;
        vm.workFlowStatusIsEnd = true;

        vm.bussinessStatus = null;
        vm.workFlowStatus = null;
        vm.wfTypeDicMap = {};



        if (vm.currentUrl.indexOf("getWorkFlowsDocking") != -1) {
            menuNav.checkMenu('getWorkFlowsDocking');
        }

        if(window.location.href.indexOf('?')>0){
            //针对含有？的url进行缓存查询处理
            var  arr = window.location.href.split('?');
            var time = arr[arr.length - 1];
            time = time.substring(12);
            var tempRequest = sessionStorage.getItem('workFlowsDockingSelf');
            if (tempRequest) {
                tempRequest = JSON.parse(tempRequest);
                if(tempRequest.time === Number(time)){
                    vm.workFlowStatusIsEnd = true;
                    if(tempRequest.workFlowStatus == 1){
                        vm.workFlowStatusIsEnd = false;
                    }
                    vm.pageNo = tempRequest.pageNo;
                    vm.pageSize = tempRequest.pageSize;
                    $('#startTimeBegin').val(tempRequest.startTime);
                    $('#startTimeEnd').val(tempRequest.endTime);
                    vm.startTime = tempRequest.startTime;
                    vm.endTime = tempRequest.endTime;
                    workFlowsDocking(tempRequest.bussinessStatus, tempRequest.workFlowStatus);
                }
            }
        }




    };

//日期+时间控件
    layui.use(['table', 'element', 'laydate'], function () {
        var laydate = layui.laydate;
        console.log(123);

        laydate.render({
            elem: '#startTimeBegin',
            type: 'datetime',
            done: function (value, date, endDate) {
            }
        });

        laydate.render({
            elem: '#startTimeEnd',
            type: 'datetime',
            done: function (value, date, endDate) {
            }
        });

    })

    vm.getWorkFlowsDocking = function (bussinessStatus, workFlowStatus) {
        vm.pageNo = 1;
        vm.workFlowStatusIsEnd = true;
        if(workFlowStatus == 1){
            vm.workFlowStatusIsEnd = false;
        }
        workFlowsDocking(bussinessStatus, workFlowStatus)
    }

    function workFlowsDocking(bussinessStatus, workFlowStatus){
        if(workFlowStatus != null && workFlowStatus == 1 ){
            vm.showRejectArchiveCondition = true;
        }
        vm.workFlowsQueryRequest.startTime = $('#startTimeBegin').val();
        vm.workFlowsQueryRequest.endTime = $('#startTimeEnd').val();
        vm.workFlowsQueryRequest.workFlowStatus = workFlowStatus;
        vm.workFlowsQueryRequest.bussinessStatus = bussinessStatus;

        ajax.http($http, box, '/workFlowQuery/getWorkFlowsDocking', $.extend({
            "pageNo": vm.pageNo,
            "pageSize": vm.pageSize
        }, vm.workFlowsQueryRequest), function (data) {
            //缓存查询条件，用于页面刷新使用
            var url = window.location.href;
            var myDate = new Date();
            var time = myDate.getTime();
            var tempRequest = {
                "time":time,
                "startTime": vm.workFlowsQueryRequest.startTime,
                "endTime": vm.workFlowsQueryRequest.endTime,
                "workFlowStatus": vm.workFlowsQueryRequest.workFlowStatus,
                "bussinessStatus": vm.workFlowsQueryRequest.bussinessStatus,
                "pageNo": vm.pageNo,
                "pageSize": vm.pageSize
            };
            sessionStorage.setItem('workFlowsDockingSelf', JSON.stringify(tempRequest));
            if(url.indexOf('?')>0){
                window.location.href = url.replace(url.substr(url.indexOf('?')),'?tempRequest='+time);
            }else{
                window.location.href = url+'?tempRequest='+time;
            }


            vm.workFlowlist = data.detailInfo;
            vm.workFlowlist = $filter('addIndexForList')(data.detailInfo, vm.pageNo, vm.pageSize);
            pagination.create('workFlowsDockingResultPagination', vm.pageNo, vm.pageSize, data.totalCount, true, function (pageNo, pageSize) {
                vm.pageNo = pageNo;
                vm.pageSize = pageSize;
                workFlowsDocking(bussinessStatus, workFlowStatus);
            });
        }, 'POST');
    }




    vm.goToWorkflowInfo = function (workFlowItem) {
        //跳转去其他页面，点击返回时的查询条件缓存
        var myDate = new Date();
        var time = myDate.getTime();
        var tempRequest = {
            "time":time,
            "startTime": vm.workFlowsQueryRequest.startTime,
            "endTime": vm.workFlowsQueryRequest.endTime,
            "workFlowStatus": vm.workFlowsQueryRequest.workFlowStatus,
            "bussinessStatus": vm.workFlowsQueryRequest.bussinessStatus,
            "pageNo": vm.pageNo,
            "pageSize": vm.pageSize
        };
        var currentWorkFlow = {
            "taskId":workFlowItem.taskId,
            "processInstanceId":workFlowItem.proInstId,
            "processKey":workFlowItem.workFlowType,
            "workFlowName":workFlowItem.workFlowTypeName,
            "workFlowQueryType":"getWorkFlowsDocking",
            "tempRequest":time,
            "isInit":0

        };
        if (sessionStorage.getItem('currentWorkFlow')) {
            sessionStorage.removeItem('currentWorkFlow');
        }
        sessionStorage.setItem('currentWorkFlow', JSON.stringify(currentWorkFlow));
        sessionStorage.setItem('workFlowsDockingSelf', JSON.stringify(tempRequest));

        $('.layui-nav dd').removeClass('layui-this');
        $('.self_info_nav').addClass('layui-this');
        window.location.href = 'index.html#/home/workflow/workflowContent?workflowName='+ workFlowItem.workFlowType;
    };

})