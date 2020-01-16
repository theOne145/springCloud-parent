package com.theOne.mapper;

import com.theOne.domain.Roles;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

public interface RolesMapper extends Mapper<Roles> {

    @Insert("INSERT INTO USER_ROLE (USER_ID,ROLE_ID,CREATE_TIME,MODIFY_TIME,REMARK) VALUES(#{uid},#{rid},#{createTime},#{modifyTime},#{remark}) ")
    int insertUserRole(@Param("uid") String uid, @Param("rid") String rid, @Param("createTime") Date createTime, @Param("modifyTime") Date modifyTime, @Param("remark") String remark);

    @Select("SELECT R.ID AS ID ,R.ROLE_NAME AS roleName ,R.CREATE_TIME AS createTime ,R.MODIFY_TIME AS modifyTime ,R.REMARK AS REMARK FROM  ROLES R  LEFT JOIN USER_ROLE UR ON R.ID=UR.ROLE_ID WHERE UR.USER_ID=#{uid}")
    List<Roles> findRolesByUserId(@Param("uid") String uid);
}
