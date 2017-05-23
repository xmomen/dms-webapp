/**
 * Created by Jeng on 2016/1/8.
 */
define(function () {
    return ["$scope", "$modal", "$ugDialog", "$stateParams", "StockAPI", "ItemAPI", "$rootScope", "ItemCategoryAPI", "BeforehandPackageRecordAPI", function ($scope, $modal, $ugDialog, $stateParams, StockAPI, ItemAPI, $rootScope, ItemCategoryAPI, BeforehandPackageRecordAPI) {
        $scope.pageInfoBeforehandPackageRecordSetting = {
            pageSize: 50,
            pageNum: 1
        };

        $scope.getBeforehandPackageRecordList = function () {
            BeforehandPackageRecordAPI.query({
                limit: $scope.pageInfoBeforehandPackageRecordSetting.pageSize,
                offset: $scope.pageInfoBeforehandPackageRecordSetting.pageNum
            }, function (data) {
                $scope.beforehandPackageRecordList = data.data;
                $scope.pageInfoBeforehandPackageRecordSetting = data.pageInfo;
                $scope.pageInfoBeforehandPackageRecordSetting.loadData = $scope.getBeforehandPackageRecordList;
            });
        };

        $scope.getBeforehandPackageRecordList();

        $scope.itemList = [];
        $scope.pageInfoSetting = {
            pageSize: 1,
            pageNum: 1
        };
        $scope.queryParam = {};
        $scope.currentItem = {};
        $scope.getItemList = function (id) {
            ItemAPI.query({
                limit: $scope.pageInfoSetting.pageSize,
                offset: $scope.pageInfoSetting.pageNum,
                keyword: $scope.queryParam.itemCode
            }, function (data) {
                $scope.itemList = data.data;
                $scope.currentItem = $scope.itemList[0];
                if ($scope.currentItem.sellUnit == 0) {
                    $scope.pageInfoSetting = data.pageInfo;
                    $scope.pageInfoSetting.loadData = $scope.getItemList;
                    $("#weight").focus();
                    $("#weight").select();
                    $("#weight").val("");
                }
                else {
                    $scope.currentItem = {};
                    $ugDialog.warn("产品不为计重，不能预包装！");
                    $("#itemCode").focus();
                    $("#itemCode").select();
                    $("#itemCode").val("");
                }
            });
        };

        $scope.itemCategoryList = [];
        $scope.queryCategoryParam = {};
        $scope.getItemCategoryTree = function () {
            ItemCategoryAPI.query({
                id: $scope.queryCategoryParam.id
            }, function (data) {
                $scope.itemCategoryList = data;
                $rootScope.$broadcast("loadingTree");
            });
        };
        $scope.getItemCategoryTree();

        $scope.queryParam = {};
        $scope.pageInfoItemSetting = {
            pageSize: 100,
            pageNum: 1
        };
        $scope.getItemListCategory = function (categoryName) {
            var choseItemId = null;
            if ($scope.choseOrderItemList && $scope.choseOrderItemList.length > 0) {
                choseItemId = [];
                for (var i = 0; i < $scope.choseOrderItemList.length; i++) {
                    var obj = $scope.choseOrderItemList[i];
                    choseItemId.push(obj.id);
                }
            }
            if (categoryName) {
                $scope.queryParam.keyword = categoryName;
            }
            ItemAPI.query({
                limit: $scope.pageInfoItemSetting.pageSize,
                offset: $scope.pageInfoItemSetting.pageNum,
                keyword: $scope.queryParam.keyword,
                sellStatus: 1,
                sellUnit: "0"
            }, function (data) {
                $scope.itemList2 = data.data;
                $scope.pageInfoSetting = data.pageInfo;
                $scope.pageInfoSetting.loadData = $scope.getItemList;
            });
        };

        $scope.chooseItem = function (index) {
            var item = $scope.itemList2[index];
            $scope.queryParam.itemCode = item.itemCode;
            $scope.getItemList();
        }

        //回车查询商品信息
        $scope.getItemInfoEvent = function (e) {
            var keycode = window.event ? e.keyCode : e.which;
            if (keycode == 13) {
                $scope.getItemList();
            }
        }

        //回车生成条码
        $scope.printBarCodeEvent = function (e) {
            var keycode = window.event ? e.keyCode : e.which;
            if (keycode == 13) {
                $scope.printBarCode();
            }
        }

        $scope.printBarCode = function () {
            if (!$scope.currentItem.itemCode) {
                $ugDialog.warn("请输入商品编码！");
                return;
            }

            if ($scope.queryParam.weight == undefined) {
                $ugDialog.warn("请称重！");
                $("#weight").focus();
                $("#weight").select();
                $("#weight").val("");
                return;
            }
            var max = parseFloat($scope.currentItem.spec) + parseFloat(($scope.currentItem.spec * 0.1));
            var min = parseFloat($scope.currentItem.spec);
            if ($scope.queryParam.weight < min) {
                $ugDialog.warn("小于标准重量！");
                $("#weight").focus();
                $("#weight").select();
                $("#weight").val("");
                return;
            }
            if ($scope.queryParam.weight.length > 4) {
                $("#weight").focus();
                $("#weight").select();
                $("#weight").val("");
                return;
            }
            var barCode = $scope.currentItem.itemCode + "" + $scope.queryParam.weight + Math.floor(Math.random() * 10000);
            //库存自动添加
            StockAPI.beforehandPackageChangeStock({
                itemId: $scope.currentItem.id,
                changeStockNum: 1
            }, function (data) {
                debugger;
                if (data.result == 1) {
                    $scope.print(barCode);
                    $scope.currentItem.stockNum = $scope.currentItem.stockNum + 1;
                    $scope.getBeforehandPackageRecordList();
                }
                $ugDialog.alert(data.message);
            });
            $("#weight").focus();
            $("#weight").select();
            $("#weight").val("");
        }

        //回车批量生成条码
        $scope.printBatchBarCodeEvent = function (e) {
            var keycode = window.event ? e.keyCode : e.which;
            if (keycode == 13) {
                $scope.printBatchBarCode();
            }
        }

        $scope.printBatchBarCode = function () {
            if (!$scope.currentItem.itemCode) {
                $ugDialog.warn("请输入商品编码！");
                return;
            }

            if ($scope.queryParam.batch == undefined) {
                $ugDialog.warn("请输入批量打印数量！");
                $("#batch").focus();
                $("#batch").select();
                $("#batch").val("");
                return;
            }

            //库存自动添加
            StockAPI.beforehandPackageChangeStock({
                itemId: $scope.currentItem.id,
                changeStockNum: $scope.queryParam.batch
            }, function (data) {
                if (data.result == 1) {
                    for (var i = 0; i < $scope.queryParam.batch; i++) {
                        //重量默认商品规格
                        var barCode = $scope.currentItem.itemCode + "" + $scope.currentItem.spec + Math.floor(Math.random() * (9999 - 1000 + 1) + 1000);
                        $scope.print(barCode);
                    }
                    $scope.currentItem.stockNum = parseInt($scope.currentItem.stockNum) + parseInt($scope.queryParam.batch);
                }
                $ugDialog.alert(data.message);
            });

            $("#batch").focus();
            $("#batch").select();
            $("#batch").val("");
        }


        $scope.print = function (barCode) {
            var LODOP = getLodop();
            LODOP.PRINT_INITA(-6, 0, "56.3mm", "60.01mm", "商品条码打印");
            LODOP.ADD_PRINT_IMAGE(12, 128, 53, 45, "<img border='0' src='http://101.200.51.63:8080/group1/M00/00/00/rBFOZlkIfTOAOb6ZAAB0qe50dIM918.png' width='60' height='60'/>");
            LODOP.SET_PRINT_STYLEA(0, "Stretch", 2);
            LODOP.ADD_PRINT_BARCODE(68, 9, "50.96mm", "10.21mm", "128B", barCode);
            LODOP.ADD_PRINT_TEXT(138, 107, 91, 19, "采摘人:" + $scope.currentItem.caizaiUser);
            LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
            LODOP.SET_PRINT_STYLEA(0, "FontSize", 8);
            LODOP.ADD_PRINT_TEXT(135, 19, 78, 19, "检验人:" + $scope.currentItem.jianceUser);
            LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
            LODOP.SET_PRINT_STYLEA(0, "FontSize", 8);
            LODOP.ADD_PRINT_TEXT(109, 20, 158, 19, "产品名称:" + $scope.currentItem.itemName);
            LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
            LODOP.SET_PRINT_STYLEA(0, "FontSize", 8);
            LODOP.ADD_PRINT_TEXT(151, 19, 100, 20, "采摘点：" + ($scope.currentItem.yieldly == null ? "无" : $scope.currentItem.yieldly));
            LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
            LODOP.SET_PRINT_STYLEA(0, "FontSize", 8);
            LODOP.ADD_PRINT_TEXT(167, 19, 137, 20, "采摘时间：6:00-9:00");
            LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
            LODOP.SET_PRINT_STYLEA(0, "FontSize", 8);
            LODOP.ADD_PRINT_TEXT(123, 20, 119, 19, "检测结果：μb=" + $scope.currentItem.nongCanLv);
            LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
            LODOP.SET_PRINT_STYLEA(0, "FontSize", 8);

            // LODOP.PRINT_DESIGN();
            LODOP.PRINT();
        }
        var initialize = function () {
            $("#itemCode").focus();
            $("#itemCode").select();
            $("#itemCode").val("");
            $("#weight").val("");
            $("#batch").val("");
        }
        initialize();
    }];
});