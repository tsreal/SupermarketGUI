package com.tgtiger.API;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tgtiger.Bean.Worker;
import com.tgtiger.Datas;
import com.tgtiger.utils.HttpUtils;

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




}