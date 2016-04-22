package ua.rd.pizzaservice.repository;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.domain.Pizza.PizzaType;

@Repository
public class PostgresPizzaRepository implements PizzaRepository {

	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert insertActor;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        this.insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("pizza")
                .usingGeneratedKeyColumns("id");
    }

	@Override
	public Integer addPizza(Pizza pizza) {
		
		Map<String, Object> parameters = new HashMap<String, Object>(2);
        parameters.put("name", pizza.getName());
        parameters.put("price", pizza.getPrice());
        parameters.put("pizza_type", pizza.getType().toString());
        Number newId = insertActor.executeAndReturnKey(parameters);
        pizza.setId(newId.intValue());
		
		return pizza.getId();
	}

	@Override
	public Pizza getPizzaByID(Integer id) {
		return jdbcTemplate.queryForObject("select * from pizza where id = ?", new Object[] {id}, new RowMapper<Pizza>() {

			@Override
			public Pizza mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Pizza(id, rs.getString("name"), rs.getDouble("price"), PizzaType.valueOf(rs.getString("pizza_type")));
			}
		});
	}

	@Override
	public int updatePizza(Pizza pizza) {
		
		return jdbcTemplate.update("update pizza set name = ?, price = ? where id = ? ",
				pizza.getName(), pizza.getPrice(), pizza.getId());

	}

	@Override
	public int deletePizza(Integer id) {
		return jdbcTemplate.update("delete from pizza where id = ?", id);
	}

}
