package com.cjc.funding.util.utils;

/**
 * 用来作为统一ajax返回的数据
 */
public class ResultEntity<T> {

    private final static String SUCCESS="请求成功";
    private final static String FAILED="请求失败";

    //请求成功或者失败
    private String result;

    //信息，请求处理失败的信息
    private String message;

    //返回的具体数据
    private T data;

    /**
     * /成功返回，但没有数据
     * @param <Type>
     * @return
     */
    public static<Type> ResultEntity<Type> successWithoutData(){
        return new ResultEntity<Type>(SUCCESS,null,null);
    }




    /**
     * 请求成功，带有数据
     * @param data 返回的具体数据类型
     * @param <Type>
     * @return
     */
    public static<Type> ResultEntity<Type> successWithData(Type data){
        return new ResultEntity<Type>(SUCCESS,null,data);
    }

    public static<Type> ResultEntity<Type> successWithMessageNoData(String message){
        return new ResultEntity<Type>(SUCCESS,message,null);
    }

    /**
     * //请求失败
     * @param message  请求失败的信息
     * @param <Type>
     * @return
     */
    public static<Type> ResultEntity<Type> failed(String message){
        return new ResultEntity<Type>(FAILED,message,null);
    }





    public ResultEntity(){

    }

    public ResultEntity(String result, String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public static String getSUCCESS() {
        return SUCCESS;
    }

    public static String getFAILED() {
        return FAILED;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
