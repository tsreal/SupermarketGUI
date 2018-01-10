package com.tgtiger.API;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tgtiger.Bean.Bill;
import com.tgtiger.Bean.Worker;
import com.tgtiger.Datas;
import com.tgtiger.utils.HttpUtils;

import java.util.Date;
import java.util.List;

public class Server {
    //用户密码返回
    /*
        if (dao.usrExist(worker_rec.getPhone())) {
            if (dao.getPassword(worker_rec.getPhone()).equals(worker_rec.getPassword())) {
                System.out.println("密码匹配成功");
                Worker worker = dao.getWorkerInfo(worker_rec.getPhone());
                json.put("name", worker.getName());
                json.put("level", worker.getLevel());
                json.put("workerNo", worker.getWorkerNo());
                json.put("info", "验证登录成功");
                json.put("task", true);
            } else {
                json.put("info", "请检查你的密码");
                json.put("task", false);
            }
        } else {
            json.put("info","帐号不存在,请先注册");
            json.put("task", false);
     */
    public Worker workerLogin(String phone, String password) {
        Worker worker = new Worker();
        worker.setPhone(phone);
        worker.setPassword(password);
        JSONObject json_send = new JSONObject();
        json_send.put("phone", phone);
        json_send.put("password", password);
        String result = HttpUtils.post(Datas.serverIp+"login", json_send.toString());

        if (result == null) {
            worker.setYes(3);
            return worker;
        }

        JSONObject json_rec = JSON.parseObject(result);
        if (json_rec.getBoolean("task")) {
            System.out.println(json_rec.getString("info"));
            worker.setYes(0);
            worker.setLevel(json_rec.getInteger("level"));
            worker.setWorkerNo(json_rec.getString("workerNo"));
            return worker;
        } else {
            System.out.println(json_rec.getString("info"));
            worker.setYes(json_rec.getInteger("status"));
            return worker;
        }

    }


    public String addWorker(String phone, String password, int level, String name) {
        JSONObject json_send = new JSONObject();
        json_send.put("phone", phone);
        json_send.put("password", password);
        json_send.put("level", level);
        json_send.put("name",name);
        return HttpUtils.post(Datas.serverIp + "addworker", json_send.toString());
    }

    public String checkVip(String memberNo) {
        JSONObject json_send = new JSONObject();
        json_send.put("memberNo", memberNo);
        return HttpUtils.post(Datas.serverIp + "checkvip", json_send.toString());
    }

    public String getBill(List<Bill.BillsEntity> bill, Boolean b,String memberNo) {
        JSONObject json_send = new JSONObject();
        json_send.put("bills", bill);
        json_send.put("vip", b);
        json_send.put("memberNo", memberNo);
        return HttpUtils.post(Datas.serverIp + "getbill", json_send.toString());
    }

    public String getProduct(String barcode) {
        JSONObject json_send = new JSONObject();
        json_send.put("barCode", barcode);
        return HttpUtils.post(Datas.serverIp + "getproduct", json_send.toString());
    }


    public String addProduct(String a) {
        return HttpUtils.post(Datas.serverIp + "addproduct", a);
    }


    public String getMember(String phone) {
        JSONObject json_send = new JSONObject();
        json_send.put("phone", phone);
        return HttpUtils.post(Datas.serverIp + "getmember", json_send.toString());
    }

    public String getAllMember() {
        JSONObject json_send = new JSONObject();
        json_send.put("request", true);
        return HttpUtils.post(Datas.serverIp + "getmemberlist", json_send.toString());
    }

    public String addMember(String phone, String name) {
        JSONObject json_send = new JSONObject();
        json_send.put("phone", phone);
        json_send.put("name", name);
        return HttpUtils.post(Datas.serverIp + "addmember", json_send.toString());
    }

    public String getAllWorker() {
        JSONObject json_send = new JSONObject();
        json_send.put("request", true);
        return HttpUtils.post(Datas.serverIp + "getworkerlist", json_send.toString());
    }

    public String getWorker(String phone) {
        JSONObject json_send = new JSONObject();
        json_send.put("phone", phone);
        return HttpUtils.post(Datas.serverIp + "getworker", json_send.toString());
    }

    public String getAnalysis(int status, String date) {
        JSONObject json_send = new JSONObject();
        json_send.put("status", status);
        json_send.put("date", date);
        return HttpUtils.post(Datas.serverIp + "getanalysis", json_send.toString());
    }

    public String getRemain() {
        JSONObject json_send = new JSONObject();
        json_send.put("request", true);
        return HttpUtils.post(Datas.serverIp + "getremain", json_send.toString());

    }


}
