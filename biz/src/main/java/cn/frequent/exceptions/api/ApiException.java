package cn.frequent.exceptions.api;

/**
 * <pre>
 *     api 接口的异常设计.
 *     捕获service 层抛出的异常，转化为api层异常，并设置状态码.
 * </pre>
 * 
 * Created by leslie on 2020/2/4.
 */
public class ApiException extends RuntimeException {

    protected Long   errorCode;
    protected Object data;

    public ApiException(Long errorCode, String message, Object data, Throwable e){
        super(message, e);
        this.errorCode = errorCode;
        this.data = data;
    }

    public ApiException(Long errorCode, String message, Object data){
        this(errorCode, message, data, null);
    }

    public ApiException(Long errorCode, String message){
        this(errorCode, message, null, null);
    }

    public ApiException(String message, Throwable e){
        this(null, message, null, e);
    }

    public ApiException(){

    }

    public ApiException(Throwable e){
        super(e);
    }

    public Long getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Long errorCode) {
        this.errorCode = errorCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
