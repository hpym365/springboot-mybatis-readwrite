package com.example.habendemo.mapper;

import com.example.habendemo.TestEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Author: Haben
 * @Description:
 * @Date: 2017-11-28 21:09
 * @Version: 1.0
 **/
@Repository
public interface TestMapper {

	@Select("SELECT * FROM test WHERE id = #{id}")
	@Results({
			@Result(property = "name",  column = "name"),
			@Result(property = "age", column = "age")
	})
	TestEntity getOne(int id);

	@Insert("insert into test(name,age) values(#{name},#{age})")
	void add(TestEntity testEntity);


}
