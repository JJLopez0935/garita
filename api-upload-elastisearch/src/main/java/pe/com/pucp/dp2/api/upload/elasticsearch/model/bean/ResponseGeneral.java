/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.pucp.dp2.api.upload.elasticsearch.model.bean;

/**
 *
 * @author johnny
 */
public class ResponseGeneral {
    
    private int code;
    private String msg;
    private String result;

    public ResponseGeneral(){}
    
    
    public ResponseGeneral(int code,
                    String msg,
                    String result){
        this.code = code;
        this.msg = msg;
        this.result = result;
    }
    
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
}
