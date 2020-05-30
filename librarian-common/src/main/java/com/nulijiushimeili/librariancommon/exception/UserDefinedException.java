package com.nulijiushimeili.librariancommon.exception;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/******************************
 * @Project: librarian
 * @FileName: UserDefinedException.java
 * @ClassName: UserDefinedException
 * @time 2020/5/10 0:19
 * @version 1.00
 * @author nulijiushimeili
 * @Description: TODO
 ******************************/

@ToString
@Data
@Slf4j
public class UserDefinedException extends RuntimeException {

    /**
     * 异常代码
     */
    private Integer code;
    /**
     * 异常提示信息
     */
    private String info;

    public UserDefinedException(){
        super();
    }

    private UserDefinedException(String message){
        super(message);
    }

    private UserDefinedException(int code, String message) {
        super(message);
        this.info = message;
        this.code = code;
    }

    public UserDefinedException(int code, String message, String info){
        super(message);
        this.code = code;
        this.info = info;
    }


    public static Map<Integer,String> loadErrorsInfo(){

        String everyLine = null;

        // 读取类路径下的文件，当前是 resources 文件夹下的文件 errors.json
        try {
            InputStream resource = UserDefinedException.class.getClassLoader().getResourceAsStream("errors.json");

            BufferedReader br = new BufferedReader(new InputStreamReader(resource, StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            everyLine = sb.toString();
        }catch ( IOException e){
            log.error("读取异常配置文件失败！");
            e.printStackTrace();
            throw new RuntimeException("读取异常配置文件失败！");
        }

        return new Gson().fromJson(everyLine, (new TypeToken< Map<Integer,String>>() {
        }).getType());
    }


    /**
     * 自定义异常类型
     * @param code
     * @return
     */
    public static UserDefinedException except(int code){
        UserDefinedException userDefinedException = new UserDefinedException();
        userDefinedException.setCode(code);
        userDefinedException.setInfo(loadErrorsInfo().get(code));
        log.error(loadErrorsInfo().get(code));
        return userDefinedException;
    }


}
