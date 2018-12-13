 package com.example.demo.config;


 import com.example.demo.bean.ResultBean;
 import com.example.demo.component.ExceptionQueueComponent;
 import com.example.demo.enums.ErrorEnum;
 import com.example.demo.exception.ErrorCodeArgException;
 import com.example.demo.exception.ErrorCodeException;
 import org.apache.tomcat.util.http.fileupload.FileUploadBase;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.web.bind.annotation.ControllerAdvice;
 import org.springframework.web.bind.annotation.ExceptionHandler;
 import org.springframework.web.bind.annotation.ResponseBody;
 import org.springframework.web.context.request.NativeWebRequest;
 import org.springframework.web.multipart.MultipartException;

 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;

 /**
  * spring controller的异常扑捉类，统一处理异常状态的响应
  */
 @ControllerAdvice
 public class BaseControllerAdvice {

     protected Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    ExceptionQueueComponent exceptionQueueComponent;

     @ExceptionHandler(value = ErrorCodeException.class)
     @ResponseBody
     public ResultBean handleException(ErrorCodeException ex, NativeWebRequest nativeWebRequest) {
         HttpServletRequest httpServletRequest = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
         ResultBean bean = null;
         if (ex instanceof ErrorCodeArgException) {
             ErrorCodeArgException errorCodeArgException = (ErrorCodeArgException)ex;
             bean = new ResultBean(ex.getError(),errorCodeArgException.getArgs());
         } else {
             bean = new ResultBean(ex.getError());
         }

         try {
//             logger.warn(JsonUtil.formatLog(httpServletRequest, "Exception", ex.toString(), "response", bean.toString()));
         }catch (Exception e) {
             logger.error("捕捉异常时报错，e", e);
         }
         return bean;

     }


//     @ExceptionHandler(value = BaseException.class)
//     @ResponseBody
//     public ResultBean handleBaseException(BaseException ex, NativeWebRequest nativeWebRequest) {
//         HttpServletRequest httpServletRequest = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
//         ResultBean bean = new ResultBean(ex.getError());
//         try {
////             logger.warn(JsonUtil.formatLog(httpServletRequest, "Exception", ex.toString(), "response", bean.toString()));
//         }catch (Exception e) {
//             logger.error("捕捉异常时报错，e", e);
//         }
//         return bean;
//
//     }
     @ExceptionHandler(value = MultipartException.class)
     @ResponseBody
     public ResultBean handleMultipartException(MultipartException ex, NativeWebRequest nativeWebRequest) {
         HttpServletRequest httpServletRequest = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
         if(ex.getCause()!=null &&(ex.getCause() instanceof FileUploadBase.FileSizeLimitExceededException||ex.getCause() instanceof FileUploadBase.SizeLimitExceededException)){
             ResultBean bean = new ResultBean(ErrorEnum.ERROR_OSS_FILE_OUT_MAX_SIZE);
             return  bean;
         }else if(ex.getCause().getCause()!=null&&(ex.getCause().getCause() instanceof FileUploadBase.FileSizeLimitExceededException||ex.getCause().getCause() instanceof FileUploadBase.SizeLimitExceededException)){
             ResultBean bean = new ResultBean(ErrorEnum.ERROR_OSS_FILE_OUT_MAX_SIZE);
             return  bean;
         }else if(ex.getCause().getCause().getCause()!=null&&(ex.getCause().getCause().getCause() instanceof FileUploadBase.FileSizeLimitExceededException||ex.getCause().getCause().getCause() instanceof FileUploadBase.SizeLimitExceededException)){
             ResultBean bean = new ResultBean(ErrorEnum.ERROR_OSS_FILE_OUT_MAX_SIZE);
             return  bean;
         }
         ResultBean bean = new ResultBean(ErrorEnum.SERVER_ERROR);
//         logger.error(JsonUtil.formatLog(httpServletRequest, "Exception", ex.toString(), "response", bean.toString(),
//                 "request", httpServletRequest.getRequestURI()));
         errorMail(ex, httpServletRequest);
         return bean;

     }

     @ExceptionHandler(value = Exception.class)
     @ResponseBody
     public ResultBean handleIOException(Exception e,NativeWebRequest nativeWebRequest) {
         HttpServletRequest httpServletRequest = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
         ResultBean bean = new ResultBean(ErrorEnum.SERVER_ERROR);
         try {
             if (!(e instanceof ErrorCodeException)){
                 try{
                     exceptionQueueComponent.put(e);
                 }catch (Exception ex){
                    logger.error("异常队列已满", ex);
                 }
             }
             logger.error("Exception",e);
//             logger.error(JsonUtil.formatLog(httpServletRequest, "Exception", e.toString(), "response", bean.toString(),
//                     "request", httpServletRequest.getRequestURI()));
             errorMail(e, httpServletRequest);
         } catch (Exception e1) {
             logger.error("异常，e", e);
         }
         return bean;
     }



     /**
      * 错误日志邮件提醒
      * @param e
      * @param httpServletRequest
      */
     private void errorMail(Exception e,HttpServletRequest httpServletRequest) {

     }
 }
