<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>测试JdbcTemplate</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="resources/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="resources/js/jquery.form.js"></script>
	<style type="text/css">
		#message{
			color: blue;
			font-size: 14px
		}
	</style>
</head>
<body>
	
	<input type="button" name="listMap" id="listMap" value="查询List套Map"><br>
	<input type="button" name="listObject" id="listObject" value="查询List套Object"><br><br>
	
	<span>单纯测试创建excel</span>
	<form name="createExcelForm" id="createExcelForm" action="/createExcel">
		<input type="text" name="filepath" id="filepath" value="d:/1.xls"><br>
		<input type="text" name="sheetName" id="sheetName" value="第一页"><br>
		<input type="button" id="createExcelBtn" name="createExcelBtn" value="保存"><br><br>
	</form>
	
	<span>测试将数据库中的数据导入excel中</span>
	<form name="db2excelForm" id="db2excelForm" action="/db2excel">
		<input type="text" name="filepath_db2excel" id="filepath_db2excel" value="d:/1.xls"><br>
		<input type="text" name="sheetName_db2excel" id="filepath_db2excel" value="第一页"><br>
		<input type="button" id="createExcelBtn_db2excel" name="createExcelBtn_db2excel" value="保存"><br><br>
	</form>
	
	<span>测试将excel中的数据导入数据库中</span>
	<form name="excel2dbForm" id="excel2dbForm" action="/excel2db">
		<input type="text" name="filepath_excel2db" id="filepath_excel2db" value="d:/1.xls"><br>
		<input type="text" name="sheetName_excel2db" id="filepath_excel2db" value="第一页"><br>
		<input type="button" id="createExcelBtn_excel2db" name="createExcelBtn_excel2db" value="保存"><br><br>
	</form>
	
	<span>测试excel上传并且将数据导入数据库中</span>
	<form name="file2dbForm" id="file2dbForm" action="/file2db">
		<input type="file" name="file_file2db" id="file_file2db"><br>
		<input type="button" id="createExcelBtn_file2db" name="createExcelBtn_file2db" value="保存"><br><br>
	</form>
	
	<span id="message"></span><br>
	
	<script type="text/javascript">
		$("#listMap").click(function(){
			$("#message").text("111");
			$.ajax({
				url:"/queryForList",
				type:"post",
				data:{
					
				},
				beforeSend:function(){
					$("#message").text("正在查询,请稍后...");
					return true;
				},
				success:function(jsonObject){
					$("#message").text(jsonObject);
				}
			});
		});
	
		
		$("#listObject").click(function(){
			$("#message").text("222");
			$.ajax({
				url:"/queryForObject",
				type:"post",
				data:{
					
				},
				beforeSend:function(){
					$("#message").text("正在查询,请稍后...");
					return true;
				},
				success:function(jsonObject){
					$("#message").text(jsonObject);
				}
			});
		});
		
		
		$("#createExcelBtn").click(function(){
			$("#createExcelForm").submit();
		})
		$("#createExcelForm").ajaxForm({
			beforeSubmit:function(){
				alert("即将保存");
			},
			success:function(jsonObject){
				alert(jsonObject);
			}
		});
		
		
		$("#createExcelBtn_db2excel").click(function(){
			$("#db2excelForm").submit();
		})
		$("#db2excelForm").ajaxForm({
			beforeSubmit:function(){
				alert("即将保存");
			},
			success:function(jsonObject){
				alert(jsonObject);
			}
		});
		
		
		$("#createExcelBtn_excel2db").click(function(){
			$("#excel2dbForm").submit();
		})
		$("#excel2dbForm").ajaxForm({
			beforeSubmit:function(){
				alert("即将保存");
			},
			success:function(jsonObject){
				alert(jsonObject);
			}
		});
		
		
		$("#createExcelBtn_file2db").click(function(){
			$("#file2dbForm").submit();
		})
		$("#file2dbForm").ajaxForm({
			beforeSubmit:function(){
				alert("即将保存");
			},
			success:function(jsonObject){
				alert(jsonObject);
			}
		});
	</script>
</body>
</html>