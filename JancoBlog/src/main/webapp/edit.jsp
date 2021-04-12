<%--
  Created by Jancoyan.
  User: Jancoyan
  Date: 2021/4/12
  Time: 19:36
 --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" +
            request.getServerName() + ":" + request.getServerPort() +
            request.getContextPath() + "/";
%>

<!DOCTYPE html>
<html>
<head>
    <title>编辑博客</title>
    <base href="<%=basePath%>"/>
	<link rel="stylesheet" href="./static/js/editor.md/css/editormd.min.css" />
	<script src="static/js/editor.md/editormd.min.js"></script>
	<script type="text/javascript">
		//初始化编辑器
		var testEditor;
		$(function () {
			testEditor = editormd("txtblogcontent", {
				width: "100%",
				height: 740,
				path: '/Content/editormd/lib/',
				theme: "dark",
				previewTheme: "dark",
				editorTheme: "pastel-on-dark",
				markdown: "",
				codeFold: true,
				//syncScrolling : false,
				saveHTMLToTextarea: true,    // 保存 HTML 到 Textarea
				searchReplace: true,
				//watch : false,                // 关闭实时预览
				htmlDecode: "style,script,iframe|on*",            // 开启 HTML 标签解析，为了安全性，默认不开启    
				//toolbar  : false,             //关闭工具栏
				//previewCodeHighlight : false, // 关闭预览 HTML 的代码块高亮，默认开启
				emoji: true,
				taskList: true,
				tocm: true,         // Using [TOCM]
				tex: true,                   // 开启科学公式TeX语言支持，默认关闭
				flowChart: true,             // 开启流程图支持，默认关闭
				sequenceDiagram: true,       // 开启时序/序列图支持，默认关闭,
				//dialogLockScreen : false,   // 设置弹出层对话框不锁屏，全局通用，默认为true
				//dialogShowMask : false,     // 设置弹出层对话框显示透明遮罩层，全局通用，默认为true
				//dialogDraggable : false,    // 设置弹出层对话框不可拖动，全局通用，默认为true
				//dialogMaskOpacity : 0.4,    // 设置透明遮罩层的透明度，全局通用，默认值为0.1
				//dialogMaskBgColor : "#000", // 设置透明遮罩层的背景颜色，全局通用，默认为#fff
				imageUpload: true,
				imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
				// imageUploadURL: "/Center/RichTextUpload",
				onload: function () {
					//console.log('onload', this);
					//this.fullscreen();
					//this.unwatch();
					//this.watch().fullscreen();
	
					//this.setMarkdown("#PHP");
					//this.width("100%");
					//this.height(480);
					//this.resize("100%", 640);
				}
			});
		});
	
		//获取编辑器内容
		var blogcontent = encodeURIComponent(testEditor.getMarkdown());
	</script>
</head>
<body>
	
	
</body>
</html>