package com.theOne.mapper;

import com.theOne.domain.Menu;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

public interface MenuMapper extends Mapper<Menu> {

    @Insert("insert into ROLE_MENU (ROLE_ID,MENU_ID,CREATE_TIME,MODIFY_TIME,REMARK) values (#{rid},#{mid},#{createTime},#{modifyTime},#{remark})")
    int insertRoleMenu(@Param("rid")String rid, @Param("mid")String mid, @Param("createTime")Date createTime,@Param("modifyTime") Date modifyTime,@Param("remark") String remark);

    @Select("select m.id,m.menu_name as menuname ,m.url,m.parent_id  as parentid,m.create_time as createtime,m.modify_time as modifytime,m.remark from  menus m left join role_menu rm on m.id = rm.menu_id where rm.role_id=#{rid}")
    List<Menu> findMenuByRoleId(@Param("rid")String rid);

}
