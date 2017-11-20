package com.k9sv.util.serialnumber;  
  
public abstract class SerialNumber {  
    public synchronized String getSerialNumber() {  
        return process();  
    }  
  
    protected abstract String process();  
}  
