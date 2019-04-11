package com.best.service.interfaces;

import com.best.entity.ReqImportClient;
import com.best.response.BusinessException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 类说明
 * </p>
 *
 * @author Alemand
 * @since 2018/3/19
 */
public interface ResolveExcelServiceI {


    /**
     * 解析Excel
     *
     * @param file 文件
     * @return 得到的结果
     * @throws BusinessException 业务异常统一处理
     */
    List<ReqImportClient> resolveExcel(MultipartFile file) throws BusinessException;


}
