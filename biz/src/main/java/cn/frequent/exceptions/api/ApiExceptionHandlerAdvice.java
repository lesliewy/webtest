package cn.frequent.exceptions.api;

/**
 * <pre>
 *     使用spring mvc 的异常框架。 将各种异常转为errorDTO, 返回.
 * </pre>
 * 
 * Created by leslie on 2020/2/4.
 */
/**
 * <pre>
 * &#64;ControllerAdvice(annotations = RestController.class)
 * public class ApiExceptionHandlerAdvice {
 * 
 *     &#64;ExceptionHandler(value = Exception.class)
 *     &#64;ResponseBody
 *     public ResponseEntity<ErrorDTO> exception(Exception exception, HttpServletResponse response) {
 *         ErrorDTO errorDTO = new ErrorDTO();
 *         if (exception instanceof ApiException) {// api异常
 *             ApiException apiException = (ApiException) exception;
 *             errorDTO.setErrorCode(apiException.getErrorCode());
 *         } else {// 未知异常
 *             errorDTO.setErrorCode(0L);
 *         }
 *         errorDTO.setTip(exception.getMessage());
 *         ResponseEntity<ErrorDTO> responseEntity = new ResponseEntity<>(errorDTO,
 *                                                                        HttpStatus.valueOf(response.getStatus()));
 *         return responseEntity;
 *     }
 * 
 *     &#64;Setter
 *     &#64;Getter
 *     class ErrorDTO {
 * 
 *         private Long errorCode;
 *         private String tip;
 *     }
 * }
 * </pre>
 */
