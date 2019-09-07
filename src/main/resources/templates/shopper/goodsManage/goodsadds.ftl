<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>用户管理</title>
    <link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
          rel="stylesheet">
    <link href="/css/sb-admin-2.min.css" rel="stylesheet">
    <link href="/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
</head>
<body id="page-top">
<div id="wrapper">
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="toMain">
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-laugh-wink"></i>
            </div>
            <div class="sidebar-brand-text mx-3">后台管理</div>
        </a>

        <#list Session.myuser.powers as nav >
            <hr class="sidebar-divider">
            <li class="nav-item">
                <a class="nav-link" href="${nav.power}">
                    <i class="fas ${nav.css}"></i>
                    <span>${nav.name}</span></a>
            </li>
        </#list>
        <hr class="sidebar-divider d-none d-md-block">
        <div class="text-center d-none d-md-inline">
            <button class="rounded-circle border-0" id="sidebarToggle"></button>
        </div>
    </ul>
    <div id="content-wrapper" class="d-flex flex-column">
        <div id="content">
            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
                <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                    <i class="fa fa-bars"></i>
                </button>
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item dropdown no-arrow">
                        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="mr-2 d-none d-lg-inline text-gray-600 small">Valerie Luna</span>
                            <img class="img-profile rounded-circle" src="https://source.unsplash.com/QAB-WJcbgJk/60x60">
                        </a>
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="userDropdown">
                            <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                退出
                            </a>
                        </div>
                    </li>
                </ul>
            </nav>
            <div class="container-fluid">
                <h1 class="h3 mb-2 text-gray-800">${type}</h1>
                <div class="card shadow mb-4">
                    <div class="card-body">
                        <form id="goodsform" action="addgoods" method="post" enctype="multipart/form-data">
                            <input id="paramone" type="hidden" name="paramone" value="">
                            <input id="onevalue" type="hidden" name="onevalue" value="">
                            <input id="paramtwo" type="hidden" name="paramtwo" value="">
                            <input id="twovalue" type="hidden" name="twovalue" value="">
                            <input id="paramthree" type="hidden" name="paramthree" value="">
                            <input id="threevalue" type="hidden" name="threevalue" value="">
                            <div class="form-group ">
                                <label class="col-sm-2 control-label ">商品名：</label>
                                <div class="col-sm-8">
                                    <input class="form-control" name="name" type="text">
                                </div>
                            </div>
                            <div class="form-group ">
                                <label class="col-sm-2 control-label ">商品头像：</label>
                                <div class="col-sm-10">
                                    <input type="file" name="file" class="form-control"><span style="color: red">建议尺寸为350*350px</span>
                                </div>
                            </div>
                            <div class="form-group ">
                                <label class="col-sm-2 control-label ">商品描述：</label>
                                <div class="col-sm-10">
                                    <input type="file" name="file" class="form-control">
                                </div>
                            </div>

                            <div class="form-group ">
                                <label class="col-sm-2 control-label ">商品价格：</label>
                                <div class="col-sm-8">
                                    <input type="text" title="请输入正确的价格" pattern="^([1-9]\d*|0)(\.\d{1,2})?$"
                                           name="price"
                                           value="${goods.price}" class="form-control" placeholder="商品价格">
                                </div>
                            </div>
                            <div class="form-group " id="selecttype">
                                <label class="col-sm-2 control-label ">商品类别：</label>
                                <div class="col-sm-8">
                                    <select class="form-control" id="selID" onchange="selcity()" name="type">
                                        <option>--选择商品类别--</option>
                                        <option>男装</option>
                                        <option>女装</option>
                                        <option>童装</option>
                                        <option>鞋袜</option>
                                        <option>帽子</option>
                                        <option>箱包</option>
                                        <option>内衣</option>
                                        <option>配饰</option>
                                        <option>孕婴</option>
                                    </select>
                                    <select class="form-control" id="subID" name="subclass">
                                        <option>--选择子类--</option>
                                    </select>
                                </div>
                            </div>
                            <button onclick="test()" class="btn btn-primary">上传</button>
                            <#--                      </form>-->
                    </div>
                </div>
            </div>
        </div>
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                    <span>Copyright &copy; Your Website 2019</span>
                </div>
            </div>
        </footer>
    </div>
</div>
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">确定退出吗</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">取消</button>
                <a class="btn btn-primary" href="logout">确定</a>
            </div>
        </div>
    </div>
</div>
<script src="/vendor/jquery/jquery.min.js"></script>
<script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="/vendor/jquery-easing/jquery.easing.min.js"></script>
<script src="/js/sb-admin-2.min.js"></script>
<script src="/vendor/datatables/jquery.dataTables.min.js"></script>
<script src="/vendor/datatables/dataTables.bootstrap4.min.js"></script>
<script type="text/javascript">
    function selcity() {
        var arr = [["--选择子类--"], ["--选择子类--", "上衣", "裤子", "T恤", "衬衫", "套装"], ["--选择子类--", "上衣", "裤子", "T恤", "衬衫", "套装"],
            ["--选择子类--", "上衣", "裙子", "裤子", "套装"], ["--选择子类--", "男鞋", "女鞋", "童鞋", "袜子"], ["--选择子类--", "男士", "女士", "儿童"],
            ["--选择子类--", "纯牛皮包", "PU皮包", "帆布包", "塑料包"], ["--选择子类--", "男式", "女士", "儿童"], ["--选择子类--", "发饰", "颈饰", "耳饰", "胸饰", "手饰", "腰饰", "脚饰"]
            , ["--选择子类--", "幼儿装", "幼儿食品", "日用品", "幼儿车", "幼儿玩具", "幼教产品", "幼儿洗护", "孕妇专区"]];
        var index = document.getElementById("selID").selectedIndex;
        var subNode = document.getElementById("subID");
        var citys = arr[index];
        subNode.options.length = 0;   //清空之前选项
        for (var x = 0; x < citys.length; x++) {
            var optNode = document.createElement("option");
            optNode.innerText = citys[x];
            subNode.appendChild(optNode);  //选择添加到子选项中
        }
    }

    function test() {
        var paramone, paramtwo, paramthree;
        var aonevalue = [];
        var atwovalue = [];
        var athreevalue = [];
        var myparam = $(".myparam");
        var myvalue0 = $('.myvalue0');
        var myvalue1 = $('.myvalue1');
        var myvalue2 = $('.myvalue2');
        if (myparam.length === 1) {
            paramone = myparam[0].innerText;
            for (var i = 0; i < myvalue0.length; i++) {
                if (myvalue0[i].checked) {
                    aonevalue.push(myvalue0[i].value)
                }
            }
        }
        if (myparam.length === 2) {
            paramone = myparam[0].innerText;
            paramtwo = myparam[1].innerText;
            for (var i = 0; i < myvalue0.length; i++) {
                if (myvalue0[i].checked) {
                    aonevalue.push(myvalue0[i].value)
                }
            }
            for (var i = 0; i < myvalue1.length; i++) {
                if (myvalue1[i].checked) {
                    atwovalue.push(myvalue1[i].value)
                }
            }
        }
        if (myparam.length === 3) {
            paramone = myparam[0].innerText;
            paramtwo = myparam[1].innerText;
            paramthree = myparam[2].innerText;
            for (var i = 0; i < myvalue0.length; i++) {
                if (myvalue0[i].checked) {
                    aonevalue.push(myvalue0[i].value)
                }
            }
            for (var i = 0; i < myvalue1.length; i++) {
                if (myvalue1[i].checked) {
                    atwovalue.push(myvalue1[i].value)
                }
            }
            for (var i = 0; i < myvalue2.length; i++) {
                if (myvalue2[i].checked) {
                    athreevalue.push(myvalue2[i].value)
                }
            }
        }
        var onevalue = getString(aonevalue);
        var twovalue = getString(atwovalue);
        var threevalue = getString(athreevalue);
        $("#onevalue").val(onevalue.trim());
        $("#twovalue").val(twovalue.trim());
        $("#threevalue").val(threevalue.trim());
        $("#paramone").val(paramone.replace("：", "").trim());
        $("#paramtwo").val(paramtwo.replace("：", "").trim());
        $("#paramthree").val(paramthree.replace("：", "").trim());
        $("#goodsform").submit
    }

    function getString(arr) {
        var str = "";
        for (var i = 0; i < arr.length; i++) {
            str += arr[i] + ",";
        }
        //去掉最后一个逗号(如果不需要去掉，就不用写)
        if (str.length > 0) {
            str = str.substr(0, str.length - 1);
        }
        return str;
    }

    $(function () {
        $('#subID').on('change', function () {
            $(".params").empty();
            var type = $('#selID').val();
            var subclass = $('#subID').val();
            $.ajax({
                type: 'post',
                url: 'getparams',
                data: {'type': type, 'subclass': subclass},
                success: function (res) {
                    if (Object.keys(res).length > 0) {
                        var html = "";
                        for (var k = 0; k < Object.keys(res).length; k++) {
                            html += '<div class="col-sm-8 form-group params"><label  class="control-label myparam ">' + Object.keys(res)[k] + '：</label>'
                            for (var i = 0; i < res[Object.keys(res)[k]].length; i++) {
                                html += '<input class="myvalue' + k + '" type="checkbox"  value="' + res[Object.keys(res)[k]][i] + '" />' + res[Object.keys(res)[k]][i]
                            }
                            html += '</div>'
                        }
                        $("#selecttype").after(html)
                    }
                }
            })
        })
    })
</script>
</body>
</html>
