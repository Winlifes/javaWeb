package com.action;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;


import com.model.equipment;
import com.dao.equipmentDAO;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.dao.scheduledDAO;
import com.model.scheduled;
import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * 管理员信息业务处理类
 *
 */
public class scheduledAction extends ActionSupport {
    public static int PAGE_IETM = 10; // 每页的记录数
    private boolean exist = true;
    @Autowired
    private scheduledDAO scheDAO;
    @Autowired
    private equipmentDAO equipDAO;
    private String flag;
    /**
     * 申请id
     */
    private int id;
    /**
     * 预定设备
     */
    private String uname;
    /**
     * 设备id
     */
    private String uid;
    /**
     * 预定人姓名
     */
    private String name;
    /**
     * 预定时间
     */
    private String time;
    /**
     * 预定时长
     */
    private String standing;
    /**
     * 预定原因
     */
    private String reason;
    /**
     * 申请状态
     */
    private String state;
    /**
     * 用户名
     */
    private String tname;
    /**
     * 暂存姓名
     */
    private String xname;
    /**
     * 设备是否出问题
     */
    private boolean error;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getXname() {
        return xname;
    }

    public void setXname(String xname) {
        this.xname = xname;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

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
        System.out.println("tname = " + tname);
        System.out.println("xname = " + xname);
        System.out.println("name = " + name);

        if(Objects.equals(tname, "admin"))
        {
            System.out.println("管理员");
        }else{
            name = xname;
        }
        System.out.println("uid = " + uid);
        System.out.println("name = " + name);
        scheduled sche = new scheduled();
        sche.setUname(uname);
        sche.setUid(uid);
        sche.setName(name);
        sche.setState(state);
        // 获取总记录数
        counts = scheDAO.getCount(sche);
        // 查询本页数据
        List<scheduled> list = scheDAO.findAll(sche, offset, PAGE_IETM);
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
        scheduled sche = new scheduled();
        // 将页面表单填写的数据放到对象中
        sche.setUname(uname);
        sche.setUid(uid);
        sche.setName(name);
        sche.setTime(time);
        sche.setStanding(standing);
        sche.setReason(reason);
        sche.setState("申请中");
        // 更新到数据库
        scheDAO.save(sche);

        equipment equip = equipDAO.findById(Integer.valueOf(uid));
        equip.setSituation("已被预定");
        equipDAO.update(equip);
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
        scheduled sche = scheDAO.findById(id);
        req.setAttribute("scheduled", sche);
        return "toUpdate";
    }

    /**
     * 跳转到填写预定信息界面
     * @return
     */


    /**
     * 查看详情
     *
     * @return
     */
    public String toView() {
        HttpServletRequest req = ServletActionContext.getRequest();
        int offset = 0; // 记录偏移量
        int counts = 0; // 总记录数
        try {
            offset = Integer.parseInt(req.getParameter("pager.offset") == null ? "0" : req.getParameter("pager.offset"));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println("tname = " + tname);
//        System.out.println("xname = " + xname);
        if(tname==("admin"))
        {

        }else{
            name = xname;
        }
//        System.out.println("uid = " + uid);
        scheduled sche = new scheduled();
        sche.setUname(uname);
        sche.setUid(uid);
        sche.setName(name);
        //sche.setState(state);
        HttpServletRequest request = ServletActionContext.getRequest();
        // 获取总记录数
        counts = scheDAO.getCount(sche);
        // 查询本页数据
        List<scheduled> list = scheDAO.findAll(sche, offset, PAGE_IETM);
        // 分页需要的参数
        req.setAttribute("itemSize", counts);
        int page_count = counts % PAGE_IETM == 0 ? counts / PAGE_IETM : counts / PAGE_IETM + 1;
        req.setAttribute("pageItem", PAGE_IETM);
        req.setAttribute("pageTotal", page_count);
        // 将本页数据放到request中到前台界面显示
        request.setAttribute("list", list);
        return "toView";
    }

    /**
     * 修改设备信息
     *
     * @return
     */
    public String update() {
        // 根据ID从数据库查询出数据
        scheduled sche = scheDAO.findById(id);
        sche.setTime(time);
        sche.setStanding(standing);
        sche.setReason(reason);
        // 更新到数据库
        scheDAO.update(sche);
        return "success";
    }



    /**
     * 删除管理员信息
     *
     * @return
     */
    public String delete() {
        // 根据ID从数据库查询出数据
        scheduled sche = scheDAO.findById(id);
        // 删除
        scheDAO.delete(sche);
        return "success";
    }
    /**
     * 完成申请
     *
     * @return
     */
    public String complete() {
        // 根据ID从数据库查询出数据
        scheduled sche = scheDAO.findById(id);
        sche.setState("已完成");
        scheDAO.update(sche);
        System.out.println("scheduled.agree");
        System.out.println("uid = " + uid);
        System.out.println(Integer.valueOf(uid));
        System.out.println("error = " + error);
        System.out.println("xname = " + xname);
        equipment equip = equipDAO.findById(Integer.valueOf(uid));
        if(error)
        {
            equip.setState("设备故障");
            equip.setSituation("不可预定");
        }else{
            equip.setState("未使用");
            equip.setSituation("未被预定");
        }

        equipDAO.update(equip);
        System.out.println("equipment.agree");
        return "success";
    }

    /**
     * 同意申请
     *
     * @return
     */
    public String agree() {
        // 根据ID从数据库查询出数据
        scheduled sche = scheDAO.findById(id);
        sche.setState("申请成功");
        scheDAO.update(sche);
        System.out.println("scheduled.agree");
        System.out.println("uid = " + uid);
        System.out.println(Integer.valueOf(uid));
        equipment equip = equipDAO.findById(Integer.valueOf(uid));
        equip.setState("使用中");
        equipDAO.update(equip);
        System.out.println("equipment.agree");
        return "success";
    }

    /**
     * 拒绝申请
     *
     * @return
     */
    public String refuse() {
        // 根据ID从数据库查询出数据
        scheduled sche = scheDAO.findById(id);
        sche.setState("申请失败");
        scheDAO.update(sche);
        return "success";
    }

    /**
     * 判断设备名称是否存在
     *
     * @return
     */
    public String unameExist() {
        scheduled sche = new scheduled();
        sche.setUname(uname);
        List list = scheDAO.findAll(sche);
        if (list != null && list.size() > 0) {
            exist = false; // 验证插件需要返回false 返回false时验证提示已存在
        }
        return "exist";
    }

    /**
     * 更新管理员密码
     *
     * @return
     * @throws Exception
     */

//    public String upwd() {
//        HttpServletRequest request = ServletActionContext.getRequest();
//        String upwd = request.getParameter("upwd");
//        Tadmin admin = (Tadmin) request.getSession().getAttribute("cuser");
//        admin.setUpwd(upwd);
//        tadminDAO.update(admin);
//        request.getSession().setAttribute("cuser", admin);
//        return "toupwd";
//    }


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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStanding() {
        return standing;
    }

    public void setStanding(String standing) {
        this.standing = standing;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}