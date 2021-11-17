package com.uire.snowhistory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.uire.snowhistory.entity.Count;
import com.uire.snowhistory.entity.DetailCount;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author uire
 * @date 2021/11/05 10:13
 **/
public interface CountMapper extends BaseMapper<Count> {

    @Select("SELECT\n" +
            "\tcount( 1 ) as count,\n" +
            "\tFROM_UNIXTIME( time, '%Y-%m-%d' ) as date\n" +
            "FROM\n" +
            "\tmetadata \n" +
            "WHERE\n" +
            "\tuser_id = #{userId} \n" +
            "\tAND group_id = #{groupId} \n" +
            "GROUP BY\n" +
            "\tFROM_UNIXTIME( time, '%Y-%m-%d' ),\n" +
            "\tgroup_id")
    List<DetailCount> getDetail(@Param("userId") String userId, @Param("groupId") String groupId);
}
