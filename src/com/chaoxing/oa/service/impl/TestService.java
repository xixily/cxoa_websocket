package com.chaoxing.oa.service.impl;

import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.chaoxing.oa.service.RoleMenuService;
import com.chaoxing.oa.service.RoleRightsService;
import com.chaoxing.oa.service.UserServiceI;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring2.xml","classpath:spring-hibernate2.xml"})
public class TestService {
	@Resource(name = "userService")
	private UserServiceI userService;
	@Resource(name = "roleMenuService")
	private RoleMenuService roleMenuService;
	@Resource(name = "roleRightsService")
	private RoleRightsService roleRights;
//	@Resource(name = "roleMenuService")
//	private RoleMenuServiceI roleMenuService;
	@Test
	public void test() {
//		UserName userName = new UserName();
//		UserInfo userInfo = new UserInfo();
//		userInfo.setEmail("guoyu@chaoxing.com");
//		userInfo.setPassword("");
//		String password = userInfo.getPassword();
//		userInfo = userService.findUser(userInfo);
//		if(userInfo == null){
//			System.out.println("用户不存在！");
//		}else{
//			
//			if(password == userInfo.getPassword()){
//				System.out.println("登陆成功！");
//			}else{
//				System.out.println("密码错误！");
//			}
//		}
		try {
//			List<PMenu> menuInfos = roleMenuService.findMenu(0);
//			for (PMenu menuInfo : menuInfos) {
//				if(menuInfo.getUls() == null){
//					System.out.println(menuInfo.toString());
//				}else{
//					System.out.println(menuInfo.toString());
//					Set<PUlList> uls = menuInfo.getUls();
//					for (PUlList ulList : uls) {
//						System.out.println(ulList.getText());
//					}
//				}
//			}
//			List<Object> lists = roleRights.getObjectList(1);
//			List<RoleRights> list = roleRights.getRightsbyId(1);
//			List<RoleRights> list3 = roleRights.getRightsbyRoleId(1);
//			for (RoleRights roleRights : list) {
//				System.out.println(roleRights.getMenuId());
//				System.out.println(roleRights.getMenuName());
//				System.out.println(roleRights.getRoleId().getRoleId());
//			}
//			for (RoleRights roleRights : list3) {
//				System.out.println(roleRights.getMenuId());
//				System.out.println(roleRights.getMenuName());
//				System.out.println(roleRights.getRoleId().getRoleId());
//			}
//			RoleRights roleRight = list3.get(8);
//			roleRight.setMenuName("为什么更新不成功呢！");
//			RoleRights newRolerights = new RoleRights();
//			BeanUtils.copyProperties(roleRight, newRolerights);
//			RoleRights rn = new RoleRights();
//			rn.setMenuId(roleRight.getMenuId());
//			rn.setMenuLevel(3);
//			rn.setMenuName("更新一次");
//			BeanUtils.copyProperties(rn, roleRight);
//			roleRights.updateRoleRight(roleRight);
//			roleRights.addRoleRight(newRolerights);
		} catch (Exception e) {
		}
		fail("======Not yet implemented=======");
	}
}
