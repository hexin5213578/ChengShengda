package com.yidian.chengshengda.main.bean;

import java.util.List;

public class ShopcarBean {

    /**
     * msg : 查询成功
     * type : OK
     * object : {"total":3,"list":[{"id":null,"stationId":2,"userId":2,"monthTime":9,"createTime":null,"stationMoney":800,"stationImg":"https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%9B%BE%E7%89%87%20%E5%94%AF%E7%BE%8E&step_word=&hs=2&pn=52&spn=0&di=155320&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=2099816045%2C3000912227&os=2339683149%2C4035639155&simid=4015731177%2C485474646&adpicid=0&lpn=0&ln=1578&fr=&fmq=1389861203899_R&fm=&ic=0&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=%E5%9B%BE%E7%89%87&objurl=http%3A%2F%2Fciv.ce.cn%2F2010main%2Fscroll%2F201008%2F17%2FW020130908507254151188.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3B4wsj451jsfrtvp76j_z%26e3BgjpAzdH3F%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25la%25Ec%25l8%25AD%25EE%25bm%25la%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25Ac%25Ec%25bl%25A0%25Ec%25Ad%25lmAzdH3F%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25la%25Ec%25l8%25AD%25EE%25bm%25la%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25Ac%25Ec%25bl%25A0%25Ec%25Ad%25lm_z%26e3Bip4s&gsm=34&rpstart=0&rpnum=0&islist=&querylist=&force=undefined,https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%9B%BE%E7%89%87%20%E5%94%AF%E7%BE%8E&step_word=&hs=2&pn=52&spn=0&di=155320&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=2099816045%2C3000912227&os=2339683149%2C4035639155&simid=4015731177%2C485474646&adpicid=0&lpn=0&ln=1578&fr=&fmq=1389861203899_R&fm=&ic=0&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=%E5%9B%BE%E7%89%87&objurl=http%3A%2F%2Fciv.ce.cn%2F2010main%2Fscroll%2F201008%2F17%2FW020130908507254151188.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3B4wsj451jsfrtvp76j_z%26e3BgjpAzdH3F%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25la%25Ec%25l8%25AD%25EE%25bm%25la%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25Ac%25Ec%25bl%25A0%25Ec%25Ad%25lmAzdH3F%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25la%25Ec%25l8%25AD%25EE%25bm%25la%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25Ac%25Ec%25bl%25A0%25Ec%25Ad%25lm_zhttps://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%9B%BE%E7%89%87%20%E5%94%AF%E7%BE%8E&step_word=&hs=2&pn=52&spn=0&di=155320&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=2099816045%2C3000912227&os=2339683149%2C4035639155&simid=4015731177%2C485474646&adpicid=0&lpn=0&ln=1578&fr=&fmq=1389861203899_R&fm=&ic=0&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=%E5%9B%BE%E7%89%87&objurl=http%3A%2F%2Fciv.ce.cn%2F2010main%2Fscroll%2F201008%2F17%2FW020130908507254151188.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3B4wsj451jsfrtvp76j_z%26e3BgjpAzdH3F%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25la%25Ec%25l8%25AD%25EE%25bm%25la%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25Ac%25Ec%25bl%25A0%25Ec%25Ad%25lmAzdH3F%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25la%25Ec%25l8%25AD%25EE%25bm%25la%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25Ac%25Ec%25bl%25A0%25Ec%25Ad%25lm_z%26e3Bip4s&gsm=34&rpstart=0&rpnum=0&islist=&querylist=&force=undefined,https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%9B%BE%E7%89%87%20%E5%94%AF%E7%BE%8E&step_word=&hs=2&pn=52&spn=0&di=155320&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=2099816045%2C3000912227&os=2339683149%2C4035639155&simid=4015731177%2C485474646&adpicid=0&lpn=0&ln=1578&fr=&fmq=1389861203899_R&fm=&ic=0&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=%E5%9B%BE%E7%89%87&objurl=http%3A%2F%2Fciv.ce.cn%2F2010main%2Fscroll%2F201008%2F17%2FW020130908507254151188.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3B4wsj451jsfrtvp76j_z%26e3BgjpAzdH3F%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25la%25Ec%25l8%25AD%25EE%25bm%25la%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25Ac%25Ec%25bl%25A0%25Ec%25Ad%25lmAzdH3F%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25la%25Ec%25l8%25AD%25EE%25bm%25la%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25Ac%25Ec%25bl%25A0%25Ec%25Ad%25lm_z%26e3Bip4s&gsm=34&rpstart=0&rpnum=0&islist=&querylist=&force=undefined","place":"七里光华岁月"},{"id":null,"stationId":11,"userId":2,"monthTime":10,"createTime":null,"stationMoney":999,"stationImg":null,"place":"七里生活广场"},{"id":null,"stationId":22,"userId":2,"monthTime":10,"createTime":null,"stationMoney":100,"stationImg":null,"place":"培风小区"}],"pageNum":1,"pageSize":10,"size":3,"startRow":1,"endRow":3,"pages":1,"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1}
     */

    private String msg;
    private String type;
    private ObjectBean object;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ObjectBean getObject() {
        return object;
    }

    public void setObject(ObjectBean object) {
        this.object = object;
    }

    public static class ObjectBean {
        /**
         * total : 3
         * list : [{"id":null,"stationId":2,"userId":2,"monthTime":9,"createTime":null,"stationMoney":800,"stationImg":"https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%9B%BE%E7%89%87%20%E5%94%AF%E7%BE%8E&step_word=&hs=2&pn=52&spn=0&di=155320&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=2099816045%2C3000912227&os=2339683149%2C4035639155&simid=4015731177%2C485474646&adpicid=0&lpn=0&ln=1578&fr=&fmq=1389861203899_R&fm=&ic=0&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=%E5%9B%BE%E7%89%87&objurl=http%3A%2F%2Fciv.ce.cn%2F2010main%2Fscroll%2F201008%2F17%2FW020130908507254151188.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3B4wsj451jsfrtvp76j_z%26e3BgjpAzdH3F%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25la%25Ec%25l8%25AD%25EE%25bm%25la%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25Ac%25Ec%25bl%25A0%25Ec%25Ad%25lmAzdH3F%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25la%25Ec%25l8%25AD%25EE%25bm%25la%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25Ac%25Ec%25bl%25A0%25Ec%25Ad%25lm_z%26e3Bip4s&gsm=34&rpstart=0&rpnum=0&islist=&querylist=&force=undefined,https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%9B%BE%E7%89%87%20%E5%94%AF%E7%BE%8E&step_word=&hs=2&pn=52&spn=0&di=155320&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=2099816045%2C3000912227&os=2339683149%2C4035639155&simid=4015731177%2C485474646&adpicid=0&lpn=0&ln=1578&fr=&fmq=1389861203899_R&fm=&ic=0&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=%E5%9B%BE%E7%89%87&objurl=http%3A%2F%2Fciv.ce.cn%2F2010main%2Fscroll%2F201008%2F17%2FW020130908507254151188.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3B4wsj451jsfrtvp76j_z%26e3BgjpAzdH3F%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25la%25Ec%25l8%25AD%25EE%25bm%25la%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25Ac%25Ec%25bl%25A0%25Ec%25Ad%25lmAzdH3F%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25la%25Ec%25l8%25AD%25EE%25bm%25la%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25Ac%25Ec%25bl%25A0%25Ec%25Ad%25lm_zhttps://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%9B%BE%E7%89%87%20%E5%94%AF%E7%BE%8E&step_word=&hs=2&pn=52&spn=0&di=155320&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=2099816045%2C3000912227&os=2339683149%2C4035639155&simid=4015731177%2C485474646&adpicid=0&lpn=0&ln=1578&fr=&fmq=1389861203899_R&fm=&ic=0&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=%E5%9B%BE%E7%89%87&objurl=http%3A%2F%2Fciv.ce.cn%2F2010main%2Fscroll%2F201008%2F17%2FW020130908507254151188.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3B4wsj451jsfrtvp76j_z%26e3BgjpAzdH3F%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25la%25Ec%25l8%25AD%25EE%25bm%25la%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25Ac%25Ec%25bl%25A0%25Ec%25Ad%25lmAzdH3F%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25la%25Ec%25l8%25AD%25EE%25bm%25la%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25Ac%25Ec%25bl%25A0%25Ec%25Ad%25lm_z%26e3Bip4s&gsm=34&rpstart=0&rpnum=0&islist=&querylist=&force=undefined,https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%9B%BE%E7%89%87%20%E5%94%AF%E7%BE%8E&step_word=&hs=2&pn=52&spn=0&di=155320&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=2099816045%2C3000912227&os=2339683149%2C4035639155&simid=4015731177%2C485474646&adpicid=0&lpn=0&ln=1578&fr=&fmq=1389861203899_R&fm=&ic=0&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=%E5%9B%BE%E7%89%87&objurl=http%3A%2F%2Fciv.ce.cn%2F2010main%2Fscroll%2F201008%2F17%2FW020130908507254151188.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3B4wsj451jsfrtvp76j_z%26e3BgjpAzdH3F%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25la%25Ec%25l8%25AD%25EE%25bm%25la%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25Ac%25Ec%25bl%25A0%25Ec%25Ad%25lmAzdH3F%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25la%25Ec%25l8%25AD%25EE%25bm%25la%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25Ac%25Ec%25bl%25A0%25Ec%25Ad%25lm_z%26e3Bip4s&gsm=34&rpstart=0&rpnum=0&islist=&querylist=&force=undefined","place":"七里光华岁月"},{"id":null,"stationId":11,"userId":2,"monthTime":10,"createTime":null,"stationMoney":999,"stationImg":null,"place":"七里生活广场"},{"id":null,"stationId":22,"userId":2,"monthTime":10,"createTime":null,"stationMoney":100,"stationImg":null,"place":"培风小区"}]
         * pageNum : 1
         * pageSize : 10
         * size : 3
         * startRow : 1
         * endRow : 3
         * pages : 1
         * prePage : 0
         * nextPage : 0
         * isFirstPage : true
         * isLastPage : true
         * hasPreviousPage : false
         * hasNextPage : false
         * navigatePages : 8
         * navigatepageNums : [1]
         * navigateFirstPage : 1
         * navigateLastPage : 1
         */

        private int total;
        private int pageNum;
        private int pageSize;
        private int size;
        private int startRow;
        private int endRow;
        private int pages;
        private int prePage;
        private int nextPage;
        private boolean isFirstPage;
        private boolean isLastPage;
        private boolean hasPreviousPage;
        private boolean hasNextPage;
        private int navigatePages;
        private int navigateFirstPage;
        private int navigateLastPage;
        private List<ListBean> list;
        private List<Integer> navigatepageNums;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public boolean isIsFirstPage() {
            return isFirstPage;
        }

        public void setIsFirstPage(boolean isFirstPage) {
            this.isFirstPage = isFirstPage;
        }

        public boolean isIsLastPage() {
            return isLastPage;
        }

        public void setIsLastPage(boolean isLastPage) {
            this.isLastPage = isLastPage;
        }

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public int getNavigatePages() {
            return navigatePages;
        }

        public void setNavigatePages(int navigatePages) {
            this.navigatePages = navigatePages;
        }

        public int getNavigateFirstPage() {
            return navigateFirstPage;
        }

        public void setNavigateFirstPage(int navigateFirstPage) {
            this.navigateFirstPage = navigateFirstPage;
        }

        public int getNavigateLastPage() {
            return navigateLastPage;
        }

        public void setNavigateLastPage(int navigateLastPage) {
            this.navigateLastPage = navigateLastPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        public static class ListBean {
            /**
             * id : null
             * stationId : 2
             * userId : 2
             * monthTime : 9
             * createTime : null
             * stationMoney : 800
             * stationImg : https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%9B%BE%E7%89%87%20%E5%94%AF%E7%BE%8E&step_word=&hs=2&pn=52&spn=0&di=155320&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=2099816045%2C3000912227&os=2339683149%2C4035639155&simid=4015731177%2C485474646&adpicid=0&lpn=0&ln=1578&fr=&fmq=1389861203899_R&fm=&ic=0&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=%E5%9B%BE%E7%89%87&objurl=http%3A%2F%2Fciv.ce.cn%2F2010main%2Fscroll%2F201008%2F17%2FW020130908507254151188.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3B4wsj451jsfrtvp76j_z%26e3BgjpAzdH3F%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25la%25Ec%25l8%25AD%25EE%25bm%25la%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25Ac%25Ec%25bl%25A0%25Ec%25Ad%25lmAzdH3F%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25la%25Ec%25l8%25AD%25EE%25bm%25la%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25Ac%25Ec%25bl%25A0%25Ec%25Ad%25lm_z%26e3Bip4s&gsm=34&rpstart=0&rpnum=0&islist=&querylist=&force=undefined,https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%9B%BE%E7%89%87%20%E5%94%AF%E7%BE%8E&step_word=&hs=2&pn=52&spn=0&di=155320&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=2099816045%2C3000912227&os=2339683149%2C4035639155&simid=4015731177%2C485474646&adpicid=0&lpn=0&ln=1578&fr=&fmq=1389861203899_R&fm=&ic=0&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=%E5%9B%BE%E7%89%87&objurl=http%3A%2F%2Fciv.ce.cn%2F2010main%2Fscroll%2F201008%2F17%2FW020130908507254151188.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3B4wsj451jsfrtvp76j_z%26e3BgjpAzdH3F%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25la%25Ec%25l8%25AD%25EE%25bm%25la%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25Ac%25Ec%25bl%25A0%25Ec%25Ad%25lmAzdH3F%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25la%25Ec%25l8%25AD%25EE%25bm%25la%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25Ac%25Ec%25bl%25A0%25Ec%25Ad%25lm_zhttps://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%9B%BE%E7%89%87%20%E5%94%AF%E7%BE%8E&step_word=&hs=2&pn=52&spn=0&di=155320&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=2099816045%2C3000912227&os=2339683149%2C4035639155&simid=4015731177%2C485474646&adpicid=0&lpn=0&ln=1578&fr=&fmq=1389861203899_R&fm=&ic=0&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=%E5%9B%BE%E7%89%87&objurl=http%3A%2F%2Fciv.ce.cn%2F2010main%2Fscroll%2F201008%2F17%2FW020130908507254151188.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3B4wsj451jsfrtvp76j_z%26e3BgjpAzdH3F%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25la%25Ec%25l8%25AD%25EE%25bm%25la%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25Ac%25Ec%25bl%25A0%25Ec%25Ad%25lmAzdH3F%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25la%25Ec%25l8%25AD%25EE%25bm%25la%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25Ac%25Ec%25bl%25A0%25Ec%25Ad%25lm_z%26e3Bip4s&gsm=34&rpstart=0&rpnum=0&islist=&querylist=&force=undefined,https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%9B%BE%E7%89%87%20%E5%94%AF%E7%BE%8E&step_word=&hs=2&pn=52&spn=0&di=155320&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=2099816045%2C3000912227&os=2339683149%2C4035639155&simid=4015731177%2C485474646&adpicid=0&lpn=0&ln=1578&fr=&fmq=1389861203899_R&fm=&ic=0&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=%E5%9B%BE%E7%89%87&objurl=http%3A%2F%2Fciv.ce.cn%2F2010main%2Fscroll%2F201008%2F17%2FW020130908507254151188.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3B4wsj451jsfrtvp76j_z%26e3BgjpAzdH3F%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25la%25Ec%25l8%25AD%25EE%25bm%25la%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25Ac%25Ec%25bl%25A0%25Ec%25Ad%25lmAzdH3F%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25la%25Ec%25l8%25AD%25EE%25bm%25la%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25Ac%25Ec%25bl%25A0%25Ec%25Ad%25lm_z%26e3Bip4s&gsm=34&rpstart=0&rpnum=0&islist=&querylist=&force=undefined
             * place : 七里光华岁月
             */

            private Object id;
            private int stationId;
            private int userId;
            private int monthTime;
            private Object createTime;
            private int stationMoney;
            private String stationImg;
            private String place;
            // 有没有被勾选
            private boolean isPersonChecked = false;

            public boolean isPersonChecked() {
                return isPersonChecked;
            }

            public void setPersonChecked(boolean personChecked) {
                isPersonChecked = personChecked;
            }

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public int getStationId() {
                return stationId;
            }

            public void setStationId(int stationId) {
                this.stationId = stationId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getMonthTime() {
                return monthTime;
            }

            public void setMonthTime(int monthTime) {
                this.monthTime = monthTime;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public int getStationMoney() {
                return stationMoney;
            }

            public void setStationMoney(int stationMoney) {
                this.stationMoney = stationMoney;
            }

            public String getStationImg() {
                return stationImg;
            }

            public void setStationImg(String stationImg) {
                this.stationImg = stationImg;
            }

            public String getPlace() {
                return place;
            }

            public void setPlace(String place) {
                this.place = place;
            }
        }
    }
}
