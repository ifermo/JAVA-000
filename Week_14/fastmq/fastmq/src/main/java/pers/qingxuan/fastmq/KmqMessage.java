package pers.qingxuan.fastmq;

import java.util.HashMap;

public class KmqMessage<T> {

    private HashMap<String,Object> headers;

    private T body;

}
