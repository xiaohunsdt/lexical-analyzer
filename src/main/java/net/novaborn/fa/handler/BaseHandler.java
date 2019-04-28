package net.novaborn.fa.handler;

/**
 * Created with IntelliJ IDEA
 * User: wangyong
 * Date: 2019-04-10
 * Time: 13:50
 * Description:
 */
public interface BaseHandler  {
    BaseHandler handle();

    Object getResult();
}
