package com.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.dao.TadminDAO;
import com.model.Tadmin;
import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * 管理员信息业务处理类
 *
 */
public class TadminAction extends ActionSupport {
	public static int PAGE_IETM = 10; // 每页的记录数
	private boolean exist = true;
	@Autowired
	private TadminDAO tadminDAO;
	private String flag;
	private int id;
	/**
	 * 用户名
	 */
	private String uname;
	/**
	 * 密码
	 */
	private String upwd;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 联系电话
	 */
	private String tel;

	/**
	 * 管理员信息列表
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
		Tadmin tadmin = new Tadmin();
		tadmin.setUname(uname);
		tadmin.setUpwd(upwd);
		// 获取总记录数
		counts = tadminDAO.getCount(tadmin);
		// 查询本页数据
		List<Tadmin> list = tadminDAO.findAll(tadmin, offset, PAGE_IETM);
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
	 * 新增管理员信息
	 *
	 * @return
	 */
	public String add() {
		// 获取表单填写的数据，放到Tadmin对象中
		Tadmin tadmin = new Tadmin();
		tadmin.setUname(uname);
		tadmin.setUpwd(upwd);
		tadmin.setName(name);
		tadmin.setTel(tel);
		// 保存到数据库
		tadminDAO.save(tadmin);
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
		Tadmin tadmin = tadminDAO.findById(id);
		req.setAttribute("tadmin", tadmin);
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
		Tadmin tadmin = tadminDAO.findById(id);
		req.setAttribute("tadmin", tadmin);
		return "toView";
	}

	/**
	 * 修改管理员信息
	 *
	 * @return
	 */
	public String update() {
		// 根据ID从数据库查询出数据
		Tadmin tadmin = tadminDAO.findById(id);
		// 将页面表单填写的数据放到对象中
		tadmin.setUname(uname);
		tadmin.setUpwd(upwd);
		tadmin.setName(name);
		tadmin.setTel(tel);
		// 更新到数据库
		tadminDAO.update(tadmin);
		return "success";
	}

	/**
	 * 删除管理员信息
	 *
	 * @return
	 */
	public String delete() {
		// 根据ID从数据库查询出数据
		Tadmin tadmin = tadminDAO.findById(id);
		// 删除
		tadminDAO.delete(tadmin);
		return "success";
	}

	/**
	 * 判断用户名是否存在
	 *
	 * @return
	 */
	public String unameExist() {
		Tadmin tadmin = new Tadmin();
		tadmin.setUname(uname);
		List list = tadminDAO.findAll(tadmin);
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

	public String upwd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String upwd = request.getParameter("upwd");
		Tadmin admin = (Tadmin) request.getSession().getAttribute("cuser");
		admin.setUpwd(upwd);
		tadminDAO.update(admin);
		request.getSession().setAttribute("cuser", admin);
		return "toupwd";
	}

	public String login() {
		HttpServletRequest request = ServletActionContext.getRequest();
		flag = "false";
		String uname = request.getParameter("uname");
		String upwd = request.getParameter("upwd");
		Tadmin tadmin = new Tadmin();
		tadmin.setUname(uname);
		tadmin.setUpwd(upwd);
		List<Tadmin> tadminList = tadminDAO.findAll(tadmin);
		if (tadminList != null && tadminList.size() > 0) {
			Tadmin admin = tadminList.get(0);
			request.getSession().setAttribute("cuser", admin);
			flag = "true";
		}
		return "flag";
	}

	public String loginout() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().setAttribute("cuser", null);
		request.getSession().setAttribute("utype", null);
		request.getSession().invalidate();
		return "login";
	}

	public String register()
	{
		Tadmin tadmin = new Tadmin();
		tadmin.setUname(uname);
		tadmin.setUpwd(upwd);
		tadmin.setName(name);
		tadmin.setTel(tel);
		// 保存到数据库
		tadminDAO.save(tadmin);
		return "login";
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

	public String getUpwd() {
		return this.upwd;
	}

	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
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