package com.example.outbreak.service;

import com.example.outbreak.model.OutbreakData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OutbreakDAO {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public OutbreakDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //list all new outbreaks reported
    public List<OutbreakData> list() {
        String sql = "SELECT * FROM outbreak";
        List<OutbreakData> outbreakDataList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(OutbreakData.class));


        return outbreakDataList;
    }

    //insert a new outbreak occurence
    public void save(OutbreakData outbreak) {
        SimpleJdbcInsert insertAction = new SimpleJdbcInsert(jdbcTemplate);
        insertAction.withTableName("outbreak").usingColumns("disease", "region", "cases");

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(outbreak);
        //automatically creates an sql insert command for you
        insertAction.execute(param);
    }

    //find an outbreak reported by id
    public OutbreakData get(int id) {
        String sql = "SELECT * FROM outbreak WHERE id = ?";
        Object[] args = {id};
        OutbreakData outbreakData = jdbcTemplate.queryForObject(sql, args, BeanPropertyRowMapper.newInstance(OutbreakData.class));

        return outbreakData;
    }

    public void update(OutbreakData outbreak) {
        String sql = "UPDATE outbreak SET disease=:disease, region=:region, cases=:cases WHERE id=:id";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(outbreak);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

        template.update(sql, param);
    }

    public void delete(int outbreakId) {
        String sql = "DELETE from outbreak WHERE id = ?";
        jdbcTemplate.update(sql, outbreakId);
    }
}
