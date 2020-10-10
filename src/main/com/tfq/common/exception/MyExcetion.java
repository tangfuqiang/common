package main.com.tfq.common.exception;

public class MyExcetion extends RuntimeException {

    private String code;
    private String mesage;

    public MyExcetion(String code, String mesage) {
        super(mesage);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
