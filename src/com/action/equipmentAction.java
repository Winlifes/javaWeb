package com.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


import com.model.scheduled;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.dao.equipmentDAO;
import com.model.equipment;

import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * 管理员信息业务处理类
 *
 */
public class equipmentAction extends ActionSupport {
    public static int PAGE_IETM = 10; // 每页的记录数
    private boolean exist = true;
    @Autowired
    private equipmentDAO equipDAO;
    private String flag;
    /**
     * 设备id
     */
    private int id;
    /**
     * 设备名称
     */
    private String uname;
    /**
     * 设备状态
     */
    private String state;
    /**
     * 设备情况
     */
    private String situation;


    /**
     * 设备信息列表
     *
     * @return
     */
    public String list() {
        HttpServletRequest req = ServletActionContext.getRequest();
        int offset = 0; // 记录偏移量
        int counts = 0; // 总记录数
        try {
            offset = Integer.parseInt(req.getParameter("pager.offset") == null ? "0" : req.getParameter("pager.offset"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        equipment equip = new equipment();
        equip.setUname(uname);
        equip.setState(state);
        equip.setSituation(situation);
        // 获取总记录数
        counts = equipDAO.getCount(equip);
        // 查询本页数据
        List<equipment> list = equipDAO.findAll(equip, offset, PAGE_IETM);
        // 分页需要的参数
        req.setAttribute("itemSize", counts);
        int page_count = counts % PAGE_IETM == 0 ? counts / PAGE_IETM : counts / PAGE_IETM + 1;
        req.setAttribute("pageItem", PAGE_IETM);
        req.setAttribute("pageTotal", page_count);
        // 将本页数据放到request中到前台界面显示
        req.setAttribute("list", list);
        return "list";
    }

    /**
     * 跳转到新增界面
     *
     * @return
     */
    public String toAdd() {
        return "toAdd";
    }

    /**
     * 新增设备信息
     *
     * @return
     */
    public String add() {
        // 获取表单填写的数据，放到equipment对象中
        equipment equip = new equipment();
        equip.setUname(uname);
        equip.setState(state);
        equip.setSituation(situation);
        // 保存到数据库
        equipDAO.save(equip);
        return "success";
    }

    /**
     * 跳转到修改界面
     *
     * @return
     */
    public String toUpdate() {
        HttpServletRequest req = ServletActionContext.getRequest();
        // 根据ID从数据库查询出对应的数据放到request中，
        equipment equip = equipDAO.findById(id);
        req.setAttribute("equipment", equip);
        return "toUpdate";
    }


    /**
     * 查看详情
     *
     * @return
     */
    public String toView() {
        HttpServletRequest req = ServletActionContext.getRequest();
        // 根据ID从数据库查询出对应的数据放到request中
        equipment equip = equipDAO.findById(id);
        req.setAttribute("equipment", equip);
        return "toView";
    }

    /**
     * 修改设备信息
     *
     * @return
     */
    public String update() {
        // 根据ID从数据库查询出数据
        equipment equip = equipDAO.findById(id);
        // 将页面表单填写的数据放到对象中
        equip.setUname(uname);
        equip.setState(state);
        equip.setSituation(situation);
        // 更新到数据库
        equipDAO.update(equip);
        return "success";
    }

    /**
     * 删除管理员信息
     *
     * @return
     */
    public String delete() {
        // 根据ID从数据库查询出数据
        equipment equip = equipDAO.findById(id);
        // 删除
        equipDAO.delete(equip);
        return "success";
    }

    /**
     * 判断设备名称是否存在
     *
     * @return
     */
    public String unameExist() {
        equipment equip = new equipment();
        equip.setUname(uname);
        List list = equipDAO.findAll(equip);
        if (list != null && list.size() > 0) {
            exist = false; // 验证插件需要返回false 返回false时验证提示已存在
        }
        return "exist";
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUname() {
        return this.uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSituation() {
        return this.situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }


    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

}