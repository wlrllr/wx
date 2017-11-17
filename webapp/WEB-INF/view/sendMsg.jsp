<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta charset="UTF-8">
    <title>发送消息</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public/assets/bootstrap/css/bootstrap.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/public/assets/jquery.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/public/assets/bootstrap/js/bootstrap.min.js"></script>
    <style type="text/css">
        .panel{
            padding: 2px;
        }
    </style>
</head>
<body>
<div class="panel panel-default  col-md-3 col-sm-3 col-xs-3" >
    <div class="panel-heading title">发送消息</div>
    <div class="panel-body" style="display: none">
        <form>
            <div class="radio form-group">
                <label>
                    <input type="radio" name="toType" id="toType1" value="1" checked>
                    根据openId发送
                </label>
                <label>
                    <input type="radio" name="toType" id="toType2" value="2">
                    根据tagId发送
                </label>
            </div>
            <div class="form-group">
                <label for="msgType">消息类型</label>
                <select class="form-control" id="msgType">
                    <option value="text">文本</option>
                    <option value="image">图片</option>
                    <option value="mpnews">图文</option>
                    <option value="mpvideo">声音</option>
                </select>
            </div>
            <div class="form-group">
                <label for="to">发送对象</label>
                <input type="text" class="form-control" id="to" placeholder="发送对象">
            </div>
            <div class="form-group">
                <label for="content">发送内容</label>
                <input type="text" class="form-control" id="content" placeholder="发送内容">
            </div>
            <div class="form-group">
                <label for="contentFile">发送文件</label>
                <input name="contentFile" type="file" id="contentFile"/>
            </div>
            <button class="btn btn-default" id="sendMsgButton" type="button">发送</button>
        </form>
    </div>
</div>
<div class="panel panel-default  col-md-3 col-sm-3 col-xs-3">
    <div class="panel-heading title">上传素材</div>
    <div class="panel-body" style="display: none">
        <form id="uploadMediaForm">
            <div class="radio form-group">
                <label>
                    <input type="radio" name="uploadMediaMediaStyle" value="1" checked>
                    临时素材
                </label>
                <label>
                    <input type="radio" name="uploadMediaMediaStyle" value="2">
                    永久素材
                </label>
            </div>
            <div class="form-group">
                <label for="uploadMediaFormFile">发送文件</label>
                <input name="contentFile" type="file" id="uploadMediaFormFile"/>
            </div>
            <button class="btn btn-default" id="uploadMediaFormButton" type="button">上传</button>
        </form>
    </div>
</div>
<div class="panel panel-default  col-md-3 col-sm-3 col-xs-3">
    <div class="panel-heading title">获取素材</div>
    <div class="panel-body" style="display: none">
        <form id="getMediaForm">
            <div class="radio form-group">
                <label>
                    <input type="radio" name="getMediaStyle" value="1" checked>
                    临时素材
                </label>
                <label>
                    <input type="radio" name="getMediaStyle" value="2">
                    永久素材
                </label>
            </div>
            <div class="form-group">
                <label for="getMediaId">素材Id</label>
                <input type="text" class="form-control" name="getMediaId" id="getMediaId" placeholder="素材Id">
            </div>
            <button class="btn btn-default" id="getMediaFormButton" type="button">获取</button>
        </form>
    </div>
</div>
<div class="panel panel-default  col-md-3 col-sm-3 col-xs-3">
    <div class="panel-heading title">创建客服</div>
    <div class="panel-body" style="display: none">
        <form id="kfForm">
            <div class="form-group">
                <label for="getMediaId">客服账号</label>
                <input type="text" class="form-control" name="kfAccount" id="kfAccount" placeholder="客服账号">
            </div>
            <div class="form-group">
                <label for="getMediaId">客服昵称</label>
                <input type="text" class="form-control" name="kfNickName" id="kfNickName" placeholder="客服昵称">
            </div>
            <button class="btn btn-default" id="kfButton" type="button">创建</button>
        </form>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function(){
        $("#sendMsgButton").on('click', function () {
            var formData = new FormData();
            var files = $("#contentFile")[0].files;
            if(files.length>0){
                formData.append("file",files[0]);
            }
            formData.append("toType",$('input[name="toType"]:checked').val());
            formData.append("to",$('#to').val());
            formData.append("msgType",$('#msgType').val());
            formData.append("content",$('#content').val());
            $.ajax({
                url : "${pageContext.request.contextPath}/msg/sendMsg",
                type : 'POST',
                data : formData,
                processData : false,
                contentType : false,
                success : function(result) {
                    alert(result.msg);
                }
            });
        });

        $("#uploadMediaFormButton").on('click', function () {
            var formData = new FormData();
            var files = $("#uploadMediaFormFile")[0].files;
            if(files.length>0){
                formData.append("file",files[0]);
            }
            formData.append("mediaStyle",$('input[name="uploadMediaMediaStyle"]:checked').val());
            formData.append("mediaType",$('#mediaType').val());
            $.ajax({
                url : "${pageContext.request.contextPath}/msg/uploadMedia",
                type : 'POST',
                data : formData,
                processData : false,
                contentType : false,
                success : function(result) {
                    alert(result.msg);
                }
            });
        });

        $("#getMediaFormButton").on('click', function () {
            $.ajax({
                url : "${pageContext.request.contextPath}/msg/getMedia",
                type : 'POST',
                data : $('#getMediaForm').serialize(),
                processData : false,
                contentType : false,
                success : function(result) {
                    alert(result.msg);
                }
            });
        });

        $("#kfButton").on('click', function () {
            $.ajax({
                url : "${pageContext.request.contextPath}/msg/addKf",
                type : 'POST',
                data : $('#kfForm').serialize(),
                processData : false,
                contentType : false,
                success : function(result) {
                    alert(result.msg);
                }
            });
        });

        $('.title').on('click',function(){
            $(this).next().toggle(1500);
        })
    });

</script>
</html>