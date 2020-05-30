package com.nulijiushimeili.librarianwebui.controller.menu;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.nulijiushimeili.librariancommon.beans.ResultEntity;
import com.nulijiushimeili.librariancommon.exception.UserDefinedException;
import com.nulijiushimeili.librarianwebui.beans.entity.SystemMenuEntity;
import com.nulijiushimeili.librarianwebui.config.PropertiesGetter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class MenuController {

    @Autowired
    private Gson gson;

    @Autowired
    private PropertiesGetter propertiesGetter;

    private SystemMenuEntity menuList;


    /**
     * 从json数据中获取菜单
     *
     * @return
     */
    @RequestMapping(value = "/loadMenu", method = RequestMethod.GET)
    public ResultEntity loadMenu() {

        if (menuList != null && menuList.getChildren().size() != 0) {
            return ResultEntity.success(menuList);
        }
        menuList = loadMenuFromJson();
        return ResultEntity.success(menuList);


    }


    /**
     * 从 json 数据中获取菜单
     *
     * @return
     */
    public SystemMenuEntity loadMenuFromJson() {

        String everyLine = null;

        // 读取类路径下的文件，当前是 resources 文件夹下的文件 menus.json
        try {
            InputStream resource = this.getClass().getClassLoader().getResourceAsStream("navs.json");

            BufferedReader br = new BufferedReader(new InputStreamReader(resource, StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            everyLine = sb.toString();
//                log.info("菜单信息：\n" + everyLine);

            menuList = gson.fromJson(everyLine, (new TypeToken<SystemMenuEntity<
                                SystemMenuEntity<SystemMenuEntity<SystemMenuEntity<
                                        SystemMenuEntity<SystemMenuEntity<SystemMenuEntity<
                                                SystemMenuEntity>>>>>>>>() {
            }).getType());
        } catch (IOException e) {
            throw UserDefinedException.except(110000);
        }


        System.out.println("离线模式菜单：\n" + menuList.toString());
        return menuList;
    }


    /**
     * 初始化菜单
     */
    public void initMenuFromJsonToDB() {
        SystemMenuEntity systemMenuEntity = loadMenuFromJson();
        List<SystemMenuEntity> systemMenuEntities = new ArrayList<>();
        systemMenuEntities.add(systemMenuEntity);
        recursionAddMenu(systemMenuEntities, 0);

        System.out.println("菜单的总个数：" + menuCount);
    }

    int menuCount = 0;
    String systemName = "";

    /**
     * 递归遍历json目录，获取菜单，插入到数据库
     *
     * @param systemMenuEntities
     */
    public void recursionAddMenu(List<SystemMenuEntity> systemMenuEntities, int pid) {

        for (SystemMenuEntity m : systemMenuEntities) {
            m.setParentId(pid);
            if (pid == 0 && m.getSystemName() != null) {
                systemName = m.getSystemName();
            } else {
                m.setSystemName(systemName);
            }

            // add menu to database
            System.out.println(m.getMenuName() + "===" + pid);

//            int res = securityService.addMenuList(m);
//            if (res > 0) {
//                menuCount++;
//            }

        }
        pid++;
        for (SystemMenuEntity menu : systemMenuEntities) {
            if (menu.getChildren() != null && menu.getChildren().size() > 0) {
                recursionAddMenu(menu.getChildren(), pid /*这里也可以写成： pid+1 */);
            }
        }

    }

}
