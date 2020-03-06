package com.erely.hystrix;

import com.netflix.hystrix.HystrixCommand;

public class UserCommand extends HystrixCommand<String> {

    public UserCommand(Setter setter){
        super(setter);
    }
    @Override
    protected String run() throws Exception {
        return "调用服务";
    }
}
