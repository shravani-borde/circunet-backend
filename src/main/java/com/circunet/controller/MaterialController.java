package com.circunet.controller;
import org.springframework.jdbc.core.JdbcTemplate; import org.springframework.web.bind.annotation.*; import java.util.*; import com.circunet.exception.ApiException;
@RestController @RequestMapping("/api/v1/materials") public class MaterialController {private final JdbcTemplate db;public MaterialController(JdbcTemplate db){this.db=db;}
 @GetMapping public List<Map<String,Object>> all(){return db.queryForList("SELECT id,name,category,default_unit AS \"defaultUnit\" FROM materials ORDER BY name");}
 @GetMapping("/{id}") public Map<String,Object> one(@PathVariable long id){var x=db.queryForList("SELECT id,name,category,default_unit AS \"defaultUnit\",emission_factor_kg_per_tonne AS \"emissionFactorKgPerTonne\",factor_source AS \"factorSource\",factor_note AS \"factorNote\" FROM materials WHERE id=?",id);if(x.isEmpty())throw ApiException.missing("Material not found");return x.get(0);}
}
