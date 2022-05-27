package com.example.demo.chainfilter;
/**
 * 责任链的主要处理器
 * */
public abstract class CoreHandle {
    private CoreHandle next;
    public CoreHandle setNext(CoreHandle next) {
        this.next = next;
        return next;
    }
    public CoreHandle getNext() {
        return next;
    }
    public void doNext(int leaveDays) {
       if(null != next){
           next.handleRequest(leaveDays);
       }
    }
    //处理请求的方法
    public abstract void handleRequest(int LeaveDays);
}
