<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>系统设置</title>
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
                            <span class="mr-2 d-none d-lg-inline text-gray-600 small">admin</span>
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
                <h1 class="h3 mb-2 text-gray-800">系统设置</h1>

                <div class="card shadow mb-4">
                    <#--          <div class="card-header py-3">-->
                    <#--            <h6 class="m-0 font-weight-bold text-primary">DataTables Example</h6>-->
                    <#--          </div>-->
                    <div class="card-body">
                        <form class="form-horizontal" method="post" action="changepassword">
                            <fieldset>
                                <div id="legend" class="">
                                    <legend class="">${msg}</legend>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-group ">
                                        <div class="form-group ">
                                            <label class="col-sm-2 control-label ">原密码：</label>
                                            <div class="col-sm-10">
                                                <input type="text" name="pwd" class="form-control" placeholder="原密码">
                                            </div>
                                        </div>
                                        <div class="form-group ">
                                            <div class="form-group ">
                                                <label class="col-sm-2 control-label ">新密码：</label>
                                                <div class="col-sm-10">
                                                    <input type="text" name="newpwd"
                                                           class="form-control" placeholder="新密码">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <button type="submit" class="btn btn-primary">提交</button>
                                            </div>
                                        </div>
                            </fieldset>
                        </form>
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
</body>
</html>
