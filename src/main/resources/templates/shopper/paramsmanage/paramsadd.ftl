<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>商品管理</title>
    <link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
    <link href="/css/sb-admin-2.min.css" rel="stylesheet">
    <link href="/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    <link href="/jquery.steps.css" rel="stylesheet">

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
                        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="mr-2 d-none d-lg-inline text-gray-600 small">Valerie Luna</span>
                            <img class="img-profile rounded-circle" src="https://source.unsplash.com/QAB-WJcbgJk/60x60">
                        </a>
                        <!-- Dropdown - User Information -->
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">

                            <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                退出
                            </a>
                        </div>
                    </li>

                </ul>

            </nav>
            <div class="container-fluid">
                <form id="form" action="addparams" method="post">
                <div id="example-basic">
                    <h3>选择商品类型</h3>
                    <section>
                        <p>请选择要为哪种类型商品添加参数</p>
                        <select id="selID" onchange="selcity()" name="type">
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
                        <select id="subID" name="subclass">
                            <option>--选择子类--</option>
                        </select>

                    </section>
                    <h3>添加参数</h3>
                    <section>
                        <p>请为该商品添加参数，每种参数值用逗号隔开，最多支持添加三种参数，参数可以不用全部填写</p>
                        <div style="width: 100%;">
                            参数一：<span>例如:尺寸</span>
                            <input name="paramone" class="form-control" placeholder="参数名">
                            参数值：<span>例如：X,XL,XXL</span>
                            <textarea id="onevalue" name="onevalue" class="form-control" placeholder="参数值"></textarea>
                        </div>
                        <div style="width: 100%;">
                            参数二:<input name="paramtwo" class="form-control" placeholder="参数名">
                            参数值：<textarea id="twovalue" name="twovalue" class="form-control" placeholder="参数值"></textarea>
                        </div>
                        <div style="width: 100%;">
                            参数三：<input name="paramthree" class="form-control" placeholder="参数名">
                            参数值：<textarea id="threevalue" name="threevalue" class="form-control" placeholder="参数值"></textarea>
                        </div>
                    </section>
<#--                    <h3>确认</h3>-->
<#--                    <section>-->
<#--                        <p>您添加的参数为：</p>-->
<#--                        <div id="params">-->

<#--                        </div>-->
<#--                    </section>-->
                </div>
                </form>
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
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
<script src="/js/demo/jquery.steps.js"></script>
<script type="text/javascript">
    function selcity(){
        var arr = [["--选择子类--"],["上衣","裤子","T恤","衬衫","套装"],["上衣","裤子","T恤","衬衫","套装"],
            ["上衣","裙子","裤子","套装"],["男鞋","女鞋","童鞋","袜子"],["男士","女士","儿童"],
            ["纯牛皮包","PU皮包","帆布包","塑料包"],["男式","女士","儿童"],["发饰","颈饰","耳饰","胸饰","手饰","腰饰","脚饰"]
            ,["幼儿装","幼儿食品","日用品","幼儿车","幼儿玩具","幼教产品","幼儿洗护","孕妇专区"]];
        var index = document.getElementById("selID").selectedIndex;
        var subNode = document.getElementById("subID");
        var citys = arr[index];
        subNode.options.length = 0;   //清空之前选项
        for(var x=0;x<citys.length;x++){
            var optNode = document.createElement("option");
            optNode.innerText = citys[x];
            subNode.appendChild(optNode);  //选择添加到子选项中
        }
    }
    $("#example-basic").steps({
        headerTag: "h3",
        bodyTag: "section",
        transitionEffect: "slideLeft",
        autoFocus: true,
        onFinishing:function(event,currentIndex){
            $('#onevalue').val($('#onevalue').val().replace(/，/ig,','));
            $('#twovalue').val($('#twovalue').val().replace(/，/ig,','));
            $('#threevalue').val($('#threevalue').val().replace(/，/ig,','));
            return true;
        },
        onFinished:function (event,currentIndex) {
            $("#form").submit();
        }
    });
</script>
</body>
</html>
