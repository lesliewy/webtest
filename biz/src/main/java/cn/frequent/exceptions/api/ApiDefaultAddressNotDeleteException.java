package cn.frequent.exceptions.api;

/**
 * <pre>
 *     具体异常。
 *     指定各种异常的状态码.
 * </pre>
 * Created by leslie on 2020/2/4.
 */
public class ApiDefaultAddressNotDeleteException extends ApiException {

    public ApiDefaultAddressNotDeleteException(String message){
        super(AddressErrorCode.DefaultAddressNotDeleteErrorCode, message, null);
    }
}
