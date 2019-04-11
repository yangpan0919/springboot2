package com.best.controller;

import com.best.excel.EXCEL_TYPE;
import com.best.excel.ResolveExcel;
import com.best.response.ApiResponse;
import com.best.response.BusinessException;
import com.best.service.interfaces.ResolveExcelServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 类说明
 * </p>
 *
 * @author Alemand
 * @since 2018/3/19
 */
@RestController
@RequestMapping("/resolve")
public class ResolveExcelController {


    @Autowired
    private ResolveExcelServiceI resolveExcelService;
    @Autowired
    ResolveExcel resolveExcel;

    /**
     * 文件上传
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ApiResponse uploadExcel(@RequestParam("file") MultipartFile file) {
        Object result =null;
        try {
            List<List<Map<String, String>>> lists = resolveExcel.resolveExcelString(file.getInputStream(), EXCEL_TYPE.SUFFIX_2007);

            result = resolveExcelService.resolveExcel(file);
            //如果需要将文件放到服务其中加以下代码
           /* try {
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(file.getOriginalFilename())));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        } catch (BusinessException e) {
            e.printStackTrace();
            return ApiResponse.failOf(-1, e.getErrMsg());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ApiResponse.successOf(result);
    }


}
