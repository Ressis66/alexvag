package ru.batchproject.alexvag;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class MyCustomReader extends JdbcCursorItemReader<User> implements ItemReader<User>{

  public MyCustomReader(@Autowired DataSource primaryDataSource) {
    setDataSource(primaryDataSource);
    setSql("SELECT id, name FROM users");
    setFetchSize(100);
    setRowMapper(new UserRowMapper());
  }

  public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
      User user  = new User();
      user.setId(rs.getInt("id"));
      user.setName(rs.getString("name"));
      return user;
    }
  }
}

