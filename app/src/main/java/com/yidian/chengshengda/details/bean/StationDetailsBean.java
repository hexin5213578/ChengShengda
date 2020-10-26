package com.yidian.chengshengda.details.bean;

import java.util.List;

public class StationDetailsBean {

    /**
     * msg : 查询成功
     * type : OK
     * object : [{"id":1,"stationNum":"2","stationMoney":800,"lng":103.98536671612547,"lat":30.638966241746235,"createTime":"2020-10-16 18:29:44","stationImg":"https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%9B%BE%E7%89%87%20%E5%94%AF%E7%BE%8E&step_word=&hs=2&pn=52&spn=0&di=155320&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=2099816045%2C3000912227&os=2339683149%2C4035639155&simid=4015731177%2C485474646&adpicid=0&lpn=0&ln=1578&fr=&fmq=1389861203899_R&fm=&ic=0&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=%E5%9B%BE%E7%89%87&objurl=http%3A%2F%2Fciv.ce.cn%2F2010main%2Fscroll%2F201008%2F17%2FW020130908507254151188.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3B4wsj451jsfrtvp76j_z%26e3BgjpAzdH3F%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25la%25Ec%25l8%25AD%25EE%25bm%25la%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25Ac%25Ec%25bl%25A0%25Ec%25Ad%25lmAzdH3F%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25la%25Ec%25l8%25AD%25EE%25bm%25la%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25Ac%25Ec%25bl%25A0%25Ec%25Ad%25lm_z%26e3Bip4s&gsm=34&rpstart=0&rpnum=0&islist=&querylist=&force=undefined,https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%9B%BE%E7%89%87%20%E5%94%AF%E7%BE%8E&step_word=&hs=2&pn=52&spn=0&di=155320&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=2099816045%2C3000912227&os=2339683149%2C4035639155&simid=4015731177%2C485474646&adpicid=0&lpn=0&ln=1578&fr=&fmq=1389861203899_R&fm=&ic=0&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=%E5%9B%BE%E7%89%87&objurl=http%3A%2F%2Fciv.ce.cn%2F2010main%2Fscroll%2F201008%2F17%2FW020130908507254151188.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3B4wsj451jsfrtvp76j_z%26e3BgjpAzdH3F%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25la%25Ec%25l8%25AD%25EE%25bm%25la%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25Ac%25Ec%25bl%25A0%25Ec%25Ad%25lmAzdH3F%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25la%25Ec%25l8%25AD%25EE%25bm%25la%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25Ac%25Ec%25bl%25A0%25Ec%25Ad%25lm_zhttps://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%9B%BE%E7%89%87%20%E5%94%AF%E7%BE%8E&step_word=&hs=2&pn=52&spn=0&di=155320&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=2099816045%2C3000912227&os=2339683149%2C4035639155&simid=4015731177%2C485474646&adpicid=0&lpn=0&ln=1578&fr=&fmq=1389861203899_R&fm=&ic=0&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=%E5%9B%BE%E7%89%87&objurl=http%3A%2F%2Fciv.ce.cn%2F2010main%2Fscroll%2F201008%2F17%2FW020130908507254151188.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3B4wsj451jsfrtvp76j_z%26e3BgjpAzdH3F%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25la%25Ec%25l8%25AD%25EE%25bm%25la%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25Ac%25Ec%25bl%25A0%25Ec%25Ad%25lmAzdH3F%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25la%25Ec%25l8%25AD%25EE%25bm%25la%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25Ac%25Ec%25bl%25A0%25Ec%25Ad%25lm_z%26e3Bip4s&gsm=34&rpstart=0&rpnum=0&islist=&querylist=&force=undefined,https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%9B%BE%E7%89%87%20%E5%94%AF%E7%BE%8E&step_word=&hs=2&pn=52&spn=0&di=155320&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=2099816045%2C3000912227&os=2339683149%2C4035639155&simid=4015731177%2C485474646&adpicid=0&lpn=0&ln=1578&fr=&fmq=1389861203899_R&fm=&ic=0&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=%E5%9B%BE%E7%89%87&objurl=http%3A%2F%2Fciv.ce.cn%2F2010main%2Fscroll%2F201008%2F17%2FW020130908507254151188.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3B4wsj451jsfrtvp76j_z%26e3BgjpAzdH3F%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25la%25Ec%25l8%25AD%25EE%25bm%25la%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25Ac%25Ec%25bl%25A0%25Ec%25Ad%25lmAzdH3F%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25la%25Ec%25l8%25AD%25EE%25bm%25la%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25Ac%25Ec%25bl%25A0%25Ec%25Ad%25lm_z%26e3Bip4s&gsm=34&rpstart=0&rpnum=0&islist=&querylist=&force=undefined","status":2,"place":"七里光华岁月","nickName":null,"endTime":null,"stationInfo":"七里光华岁月"}]
     */

    private String msg;
    private String type;
    private List<ObjectBean> object;

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

    public List<ObjectBean> getObject() {
        return object;
    }

    public void setObject(List<ObjectBean> object) {
        this.object = object;
    }

    public static class ObjectBean {
        /**
         * id : 1
         * stationNum : 2
         * stationMoney : 800
         * lng : 103.98536671612547
         * lat : 30.638966241746235
         * createTime : 2020-10-16 18:29:44
         * stationImg : https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%9B%BE%E7%89%87%20%E5%94%AF%E7%BE%8E&step_word=&hs=2&pn=52&spn=0&di=155320&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=2099816045%2C3000912227&os=2339683149%2C4035639155&simid=4015731177%2C485474646&adpicid=0&lpn=0&ln=1578&fr=&fmq=1389861203899_R&fm=&ic=0&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=%E5%9B%BE%E7%89%87&objurl=http%3A%2F%2Fciv.ce.cn%2F2010main%2Fscroll%2F201008%2F17%2FW020130908507254151188.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3B4wsj451jsfrtvp76j_z%26e3BgjpAzdH3F%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25la%25Ec%25l8%25AD%25EE%25bm%25la%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25Ac%25Ec%25bl%25A0%25Ec%25Ad%25lmAzdH3F%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25la%25Ec%25l8%25AD%25EE%25bm%25la%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25Ac%25Ec%25bl%25A0%25Ec%25Ad%25lm_z%26e3Bip4s&gsm=34&rpstart=0&rpnum=0&islist=&querylist=&force=undefined,https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%9B%BE%E7%89%87%20%E5%94%AF%E7%BE%8E&step_word=&hs=2&pn=52&spn=0&di=155320&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=2099816045%2C3000912227&os=2339683149%2C4035639155&simid=4015731177%2C485474646&adpicid=0&lpn=0&ln=1578&fr=&fmq=1389861203899_R&fm=&ic=0&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=%E5%9B%BE%E7%89%87&objurl=http%3A%2F%2Fciv.ce.cn%2F2010main%2Fscroll%2F201008%2F17%2FW020130908507254151188.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3B4wsj451jsfrtvp76j_z%26e3BgjpAzdH3F%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25la%25Ec%25l8%25AD%25EE%25bm%25la%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25Ac%25Ec%25bl%25A0%25Ec%25Ad%25lmAzdH3F%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25la%25Ec%25l8%25AD%25EE%25bm%25la%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25Ac%25Ec%25bl%25A0%25Ec%25Ad%25lm_zhttps://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%9B%BE%E7%89%87%20%E5%94%AF%E7%BE%8E&step_word=&hs=2&pn=52&spn=0&di=155320&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=2099816045%2C3000912227&os=2339683149%2C4035639155&simid=4015731177%2C485474646&adpicid=0&lpn=0&ln=1578&fr=&fmq=1389861203899_R&fm=&ic=0&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=%E5%9B%BE%E7%89%87&objurl=http%3A%2F%2Fciv.ce.cn%2F2010main%2Fscroll%2F201008%2F17%2FW020130908507254151188.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3B4wsj451jsfrtvp76j_z%26e3BgjpAzdH3F%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25la%25Ec%25l8%25AD%25EE%25bm%25la%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25Ac%25Ec%25bl%25A0%25Ec%25Ad%25lmAzdH3F%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25la%25Ec%25l8%25AD%25EE%25bm%25la%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25Ac%25Ec%25bl%25A0%25Ec%25Ad%25lm_z%26e3Bip4s&gsm=34&rpstart=0&rpnum=0&islist=&querylist=&force=undefined,https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%9B%BE%E7%89%87%20%E5%94%AF%E7%BE%8E&step_word=&hs=2&pn=52&spn=0&di=155320&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=2099816045%2C3000912227&os=2339683149%2C4035639155&simid=4015731177%2C485474646&adpicid=0&lpn=0&ln=1578&fr=&fmq=1389861203899_R&fm=&ic=0&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=%E5%9B%BE%E7%89%87&objurl=http%3A%2F%2Fciv.ce.cn%2F2010main%2Fscroll%2F201008%2F17%2FW020130908507254151188.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3B4wsj451jsfrtvp76j_z%26e3BgjpAzdH3F%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25la%25Ec%25l8%25AD%25EE%25bm%25la%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25Ac%25Ec%25bl%25A0%25Ec%25Ad%25lmAzdH3F%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25la%25Ec%25l8%25AD%25EE%25bm%25la%25Ec%25Al%25bA%25Ed%25ba%25Bn%25En%25b8%25bl%25El%25bF%25b9%25E0%25bm%25Bb%25Em%25A0%25Am%25Em%25BE%25Bm%25Em%25BB%25bc%25Ed%25l9%25lm%25Em%25BE%25Bl%25E9%25BD%25BA%25E0%25b9%25bA%25El%25bD%25Ac%25Ec%25bl%25A0%25Ec%25Ad%25lm_z%26e3Bip4s&gsm=34&rpstart=0&rpnum=0&islist=&querylist=&force=undefined
         * status : 2
         * place : 七里光华岁月
         * nickName : null
         * endTime : null
         * stationInfo : 七里光华岁月
         */

        private int id;
        private String stationNum;
        private int stationMoney;
        private double lng;
        private double lat;
        private String createTime;
        private String stationImg;
        private int status;
        private String place;
        private Object nickName;
        private Object endTime;
        private String stationInfo;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStationNum() {
            return stationNum;
        }

        public void setStationNum(String stationNum) {
            this.stationNum = stationNum;
        }

        public int getStationMoney() {
            return stationMoney;
        }

        public void setStationMoney(int stationMoney) {
            this.stationMoney = stationMoney;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getStationImg() {
            return stationImg;
        }

        public void setStationImg(String stationImg) {
            this.stationImg = stationImg;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public Object getNickName() {
            return nickName;
        }

        public void setNickName(Object nickName) {
            this.nickName = nickName;
        }

        public Object getEndTime() {
            return endTime;
        }

        public void setEndTime(Object endTime) {
            this.endTime = endTime;
        }

        public String getStationInfo() {
            return stationInfo;
        }

        public void setStationInfo(String stationInfo) {
            this.stationInfo = stationInfo;
        }
    }
}
