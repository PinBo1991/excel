package com.dzpykj.excel.excel.controller;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dzpykj.excel.common.utils.ExcelUtil;
import com.dzpykj.excel.excel.service.IExcelService;

@Controller
@RequestMapping
public class ExcelController {
	
	@Autowired
	IExcelService excelService;
	
//	@RequestMapping(value="/queryForList",produces="application/json;charset=UTF-8")
	@RequestMapping(value="/queryForList",produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object queryForList(){
		return excelService.queryForList().toString();
	}
	
//	@RequestMapping(value="/queryForObject",produces="application/json;charset=UTF-8")
	@RequestMapping(value="/queryForObject",produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object queryForObject(){
		String str = excelService.queryForObject().toString();
		System.out.println(str);
		return str;
	}
	
	@RequestMapping(value="/createExcel",produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object createExcel(HttpServletRequest req){
		String filepath = req.getParameter("filepath");
		String sheetName = req.getParameter("sheetName");
		ExcelUtil.createExcel(filepath, sheetName);
		return "success";
	}
	
	@RequestMapping(value="/db2excel",produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object db2excel(HttpServletRequest req){
		String filepath = req.getParameter("filepath_db2excel");
		String sheetName = req.getParameter("sheetName_db2excel");
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> list = (List<Map<String,Object>>)excelService.queryForList();
		ExcelUtil.db2excel(filepath, sheetName, list);
		return "success";
	}
	
	@RequestMapping(value="/excel2db",produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object excel2db(HttpServletRequest req){
		String filepath = req.getParameter("filepath_excel2db");
		String sheetName = req.getParameter("sheetName_excel2db");
		excelService.excel2db(filepath,sheetName);
		return "success";
	}
	
	//方法1
//	@RequestMapping(value="/file2db",produces="text/html;charset=UTF-8")
//	@ResponseBody
//	public Object file2db(HttpServletRequest req){
//        //创建一个通用的多部分解析器
//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(req.getSession().getServletContext());  
//        //判断 request 是否有文件上传,即多部分请求
//        if(multipartResolver.isMultipart(req)){
//            //转换成多部分request
//            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)req;
//            //取得request中的所有文件名
//            Iterator<String> iter = multiRequest.getFileNames();
//            while(iter.hasNext()){
//                //记录上传过程起始时的时间，用来计算上传时间
////                int pre = (int) System.currentTimeMillis();
//                //取得上传文件
//                MultipartFile file = multiRequest.getFile(iter.next());
//                if(file != null){
//                    //取得当前上传文件的文件名称
//                    String myFileName = file.getOriginalFilename();
//                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在
//                    if(myFileName.trim() !=""){
//                        System.out.println(myFileName);  
//                        //重命名上传后的文件名  
//                        String fileName = file.getOriginalFilename();  
//                        //定义上传路径  
//                        String path = "d:/" + fileName;  
//                        File localFile = new File(path);  
//                        try {
//                            if(!localFile.exists()){  
//                            	localFile.mkdirs();  
//                            }
//							file.transferTo(localFile);
//						} catch (IllegalStateException | IOException e) {
//							e.printStackTrace();
//						}  
//                    }  
//                }  
//                //记录上传该文件后的时间  
////                int finaltime = (int) System.currentTimeMillis();  
////                System.out.println(finaltime - pre);  
//            }  
//        }  
//		return "success";
//	}
	
	
	//方法2
//	@RequestMapping(value="/file2db",produces="text/html;charset=UTF-8")
//	@ResponseBody
//	public Object file2db(@RequestParam("file_file2db") CommonsMultipartFile[] files,HttpServletRequest req){
////    public String addUser(@RequestParam("file") CommonsMultipartFile[] files,HttpServletRequest request){  
//		for(int i = 0;i<files.length;i++){  
//			System.out.println("fileName---------->" + files[i].getOriginalFilename());  
//			if(!files[i].isEmpty()){
//                int pre = (int) System.currentTimeMillis();  
//                try {
//                    //拿到输出流，同时重命名上传的文件  
//                    FileOutputStream os = new FileOutputStream("d:/" + files[i].getOriginalFilename());  
//                    //拿到上传文件的输入流  
//                    InputStream in = files[i].getInputStream();  
//                    //以写字节的方式写文件  
//                    int b = 0;  
//                    while((b=in.read()) != -1){  
//                        os.write(b);  
//                    }  
//                    os.flush();  
//                    os.close();  
//                    in.close();  
//                    int finaltime = (int) System.currentTimeMillis();  
//                    System.out.println(finaltime - pre);  
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//			}
//        }
//        return "success";
//    }
	
	
	//简化的请求 适用情况:1 单文件上传,2不用保存文件到服务器
//	@RequestMapping(value="/file2db",produces="text/html;charset=UTF-8")
//	@ResponseBody
//	public Object file2db(@RequestParam("file_file2db") CommonsMultipartFile file,HttpServletRequest req){
//		if(!file.isEmpty()){
//            try {
//                //拿到上传文件的输入流  
//                InputStream in = file.getInputStream();  
//                excelService.file2db(in);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//		}
//        return "success";
//    }
	
	
	//简化的请求 适用情况:1 单文件上传,2不用保存文件到服务器
	@RequestMapping(value="/file2db",produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object file2db(MultipartFile file_file2db,HttpServletRequest req){
		if(!file_file2db.isEmpty()){
            try {
                //拿到上传文件的输入流  
                InputStream in = file_file2db.getInputStream();  
                excelService.file2db(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
		}
        return "success";
    }
}