package com.xmomen.framework.web.exceptions;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.Iterator;

/**
 * Created by tanxinzheng on 17/5/14.
 */
@Deprecated
public class ArgumentValidException extends Exception{
    private final BindingResult bindingResult;

    public ArgumentValidException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return this.bindingResult;
    }

    public String getMessage() {
        StringBuilder sb = (new StringBuilder("Validation failed for argument at index ")).append(", with ").append(this.bindingResult.getErrorCount()).append(" error(s): ");
        Iterator var2 = this.bindingResult.getAllErrors().iterator();

        while(var2.hasNext()) {
            ObjectError error = (ObjectError)var2.next();
            sb.append("[").append(error).append("] ");
        }

        return sb.toString();
    }
}
