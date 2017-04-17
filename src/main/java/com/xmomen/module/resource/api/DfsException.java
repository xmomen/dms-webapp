package com.xmomen.module.resource.api;

public class DfsException extends RuntimeException {

    private static final long serialVersionUID = 1497374765949093902L;

    /**
     * IOException 100 
     * FileNotFoundException 200 
     * Exception from DFS (example: from FastDFS, MyException) 300 
     * OtherException 400
     */
    private String code;

    private String message;

    public DfsException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
